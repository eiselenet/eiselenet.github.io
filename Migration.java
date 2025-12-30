///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.7.6
//DEPS org.jsoup:jsoup:1.18.3

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Command(name = "Migration", mixinStandardHelpOptions = true, version = "1.0", description = "Migrates blog posts from Atom feed to Quarkus Roq")
public class Migration implements Callable<Integer> {

    @Option(names = { "-f", "--feed" }, description = "Path to feed.atom", defaultValue = "old_blog/feed.atom")
    private File feedFile;

    @Option(names = { "-i", "--images" }, description = "Path to images directory", defaultValue = "old_blog/images")
    private Path imagesDir;

    @Option(names = { "-o", "--output" }, description = "Output directory for posts", defaultValue = "content/posts")
    private Path outputDir;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-ZZZZZ");
    private static final DateTimeFormatter DIR_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FRONT_MATTER_DATE_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss Z");

    public static void main(String... args) {
        int exitCode = new CommandLine(new Migration()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        if (!feedFile.exists()) {
            System.err.println("Feed file not found: " + feedFile);
            return 1;
        }

        if (!Files.exists(imagesDir)) {
            System.err.println("Images directory not found: " + imagesDir);
            return 1;
        }

        System.out.println("Parsing feed: " + feedFile);
        Document doc = Jsoup.parse(Files.readString(feedFile.toPath()), "", Parser.xmlParser());
        Elements entries = doc.select("entry");

        int count = 0;
        for (Element entry : entries) {
            if (processEntry(entry)) {
                count++;
            }
        }

        System.out.println("Migrated " + count + " posts.");
        return 0;
    }

    private boolean processEntry(Element entry) throws IOException {
        String type = getTagValue(entry, "blogger:type");
        if (!"POST".equalsIgnoreCase(type)) {
            return false; // Skip comments and other types
        }

        String title = getTagValue(entry, "title");
        String published = getTagValue(entry, "published");
        String contentHtml = getTagValue(entry, "content");
        String originalPath = getTagValue(entry, "blogger:filename");

        List<String> tags = entry.select("category[scheme='http://www.w3.org/2005/Atom']") // Standard Atom tag usually
                .stream().map(e -> e.attr("term")).collect(Collectors.toList());

        // Also check blogger scheme if standard ones aren't valid, but user data showed
        // they have scheme 'tag:blogger.com...'
        if (tags.isEmpty()) {
            tags = entry.select("category").stream()
                    .filter(e -> !e.attr("term").contains("kind#")) // filter out kind#post if present
                    .map(e -> e.attr("term"))
                    .collect(Collectors.toList());
        }

        if (originalPath == null || originalPath.isEmpty()) {
            System.err.println("Skipping post with no filename: " + title);
            return false;
        }

        Instant pubInstant = Instant.parse(published);
        String dirDate = DIR_DATE_FORMATTER.format(pubInstant.atZone(ZoneId.of("UTC")));
        String fmDate = FRONT_MATTER_DATE_FORMATTER.format(pubInstant.atZone(ZoneId.of("UTC")));

        // Extract slug from /2008/01/enterprise-ajax.html -> enterprise-ajax
        String slug = originalPath.substring(originalPath.lastIndexOf('/') + 1).replace(".html", "");

        // Target Directory: content/posts/{YYYY}/{MM}/
        String year = dirDate.substring(0, 4);
        String month = dirDate.substring(5, 7);
        Path targetPostDir = outputDir.resolve(year).resolve(month);
        Files.createDirectories(targetPostDir);

        // Process Content and Images
        Document contentDoc = Jsoup.parseBodyFragment(contentHtml);

        // Handle Images
        Elements images = contentDoc.select("img");
        for (Element img : images) {
            handleAsset(img, "src", targetPostDir);
        }

        // Handle Anchors that link to images
        Elements anchors = contentDoc.select("a");
        for (Element a : anchors) {
            handleAsset(a, "href", targetPostDir);
        }

        String processedContent = contentDoc.body().html();
        // Escape Qute delimiters
        processedContent = processedContent.replace("{", "\\{").replace("}", "\\}");

        // FrontMatter
        StringBuilder sb = new StringBuilder();
        sb.append("---\n");
        sb.append("title: \"").append(escapeYaml(title)).append("\"\n");
        sb.append("date: ").append(fmDate).append("\n");
        sb.append("layout: post\n");
        if (!tags.isEmpty()) {
            String quotedTags = tags.stream()
                    .map(t -> "\"" + escapeYaml(t) + "\"")
                    .collect(Collectors.joining(", "));
            sb.append("tags: [").append(quotedTags).append("]\n");
        }
        sb.append("slug: \"").append(dirDate).append("-").append(slug).append("\"\n");
        sb.append("url: ").append(originalPath).append("\n");
        sb.append("---\n\n");
        sb.append(processedContent);

        // Target File: {slug}.md
        Path targetFile = targetPostDir.resolve(slug + ".md");
        Files.writeString(targetFile, sb.toString());

        System.out.println("Created: " + targetFile);
        return true;
    }

    private void handleAsset(Element element, String attr, Path targetDir) throws IOException {
        String url = element.attr(attr);
        if (url == null || url.isEmpty())
            return;

        // Extract filename from URL (take the last part)
        // e.g. http://..../s320/nb-7-beta.jpg -> nb-7-beta.jpg
        String filename = getFilenameFromUrl(url);

        // Check if exists in old_loop/images
        Path sourceImg = imagesDir.resolve(filename);
        if (Files.exists(sourceImg)) {
            // Copy to target dir
            Path targetImg = targetDir.resolve(filename);
            if (!Files.exists(targetImg)) {
                Files.copy(sourceImg, targetImg, StandardCopyOption.REPLACE_EXISTING);
                // System.out.println("Copied image: " + filename);
            }

            // Update attribute to be local
            element.attr(attr, filename);
        } else {
            // Debug logging
            if (filename.contains("Screenshot")) {
                System.out.println("DEBUG: Image not found: '" + filename + "' derived from URL: " + url);
                System.out.println("DEBUG: Checked path: " + sourceImg.toAbsolutePath());
            }
        }
    }

    private String getFilenameFromUrl(String url) {
        String filename = url;
        if (url.contains("/")) {
            filename = url.substring(url.lastIndexOf('/') + 1);
        }
        return java.net.URLDecoder.decode(filename, java.nio.charset.StandardCharsets.UTF_8);
    }

    private String getTagValue(Element parent, String tagName) {
        Elements els = parent.getElementsByTag(tagName);
        if (!els.isEmpty()) {
            return els.first().text();
        }
        return null;
    }

    private String escapeYaml(String s) {
        return s.replace("\"", "\\\"");
    }
}

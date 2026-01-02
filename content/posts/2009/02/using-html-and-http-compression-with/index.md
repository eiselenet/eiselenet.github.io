---
title: "Using HTML and HTTP Compression with Weblogic Server"
date: 2009-02-10 06:06:00 +0000
layout: post
tags: ["filter", "weblogic server", "http", "gzip", "html", "compression"]
slug: "using-html-and-http-compression-with"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2009/02/using-html-and-http-compression-with.html
---

Have you ever wondered about the extra spaces in your HTML output from JSP Pages or Servlets? Even the ones, dealing with webdesign on more or less older browsers did :)
<br>
Beside the formatting this could also have an impact to overall application performance, if you are delivering big pages.
<br>
The first thing, that came to my mind was the gzip compressed HTTP response. Apache 2.0 comes with the mod_deflate, which adds a filter to Gzip the content. Filters can be blanket — in Internet Explorer everything is compressed — or selective — compressing only specific MIME types (determined by examining the header generated, either automatically by Apache or a CGI or other dynamic component. 
<br>
To enable blanket compression, set the SetOutputFilter directive to a Web site or Directory container, for example:
<br>
<br>
&lt;Directory "/your/path/to/htmlroot"&gt;
<br>
 SetOutputFilter Deflate
<br>
&lt;/Directory&gt;
<br>
<br>
You can take advantage of this using WLS if you use the WLS Apache Plugin, too. Used in combination, you get a gzip'ed http stream, that reduces the amount of data transfered to the clients.
<br>
<br>
If you don't have an apache in front of your server, you can still have a servlet filter installed, which could do the gzip compression of the content for you.
<br>
There is a quick samle available from bea (download: <a href="http://ftpna2.bea.com/pub/downloads/Gzipfilter_war.zip">http://ftpna2.bea.com/pub/downloads/Gzipfilter_war.zip</a>). Simply add the weblogicx-gzip.jar included in this distribution into your war's WEB-INF/lib directory. Register the gzip 
<br>
filter in your web.xml as shown below:
<br>
<br>
 &lt;filter&gt;
<br>
 &lt;filter-name&gt;GZIPFilter&lt;/filter-name&gt;
<br>
 &lt;filter-class&gt;weblogicx.servlet.gzip.filter.GZIPFilter&lt;/filter-class&gt;
<br>
 &lt;/filter&gt;
<br>
<br>
After this, you have to map all resources which can benefit from compression such as
<br>
.txt, .log, .html and .htm as a filter mapping.. You can also use the filter to compress output from jsps and other dynamic content. Typically compressing certain image types does not prove to be advantageous since they are already compressed
<br>
so make sure they are not mapped to the gzipfilter.
<br>
<br>
 &lt;filter-mapping&gt;
<br>
 &lt;filter-name&gt;GZIPFilter&lt;/filter-name&gt;
<br>
 &lt;url-pattern&gt;/*.html&lt;/url-pattern&gt;
<br>
 &lt;/filter-mapping&gt;
<br>
<br>
Of corse there are plenty other solutions out there. You could even write your own gzip filter. Find the right solution for you. This should not be a productive solution anyway. It consumes too much server ressources and is something the mod_deflate could do much more efficient. But it could be handy for tests and debugging.
<br>
<br>
To optimize the output of your HTML code, you can use the newly added feature from the Weblogic Server (&gt;=10.x). It's called HTML Template Compression. To use it, you simply have to add the following code to your weblogic.xml:
<br>
<br>
&lt;weblogic-web-app&gt;
<br>
&lt;jsp-descriptor&gt;
<br>
&lt;compress-html-template&gt;
<br>
true
<br>
&lt;/compress-html-template&gt;
<br>
&lt;/jsp-descriptor&gt;
<br>
&lt;/weblogic-web-app&gt;
<br>
<br>
This removes any extra whitespaces from the generated HTML output. For example:
<br>
<br>
&lt;html&gt;
<br>
&lt;body&gt;
<br>
&lt;text&gt;
<br>
&lt;/text&gt;
<br>
&lt;/body&gt;
<br>
&lt;/html&gt;
<br>
<br>
will be rewitten as:
<br>
&lt;html&gt;&lt;body&gt;&lt;text&gt;&lt;/text&gt;&lt;/body&gt;&lt;/html&gt;
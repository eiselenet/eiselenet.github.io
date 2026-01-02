---
title: "Test driving Java API for Processing JSON with GlassFish 4.0"
date: 2013-02-26 11:37:00 +0000
layout: post
tags: ["glassfish", "json", "javaee7"]
slug: "test-driving-java-api-for-processing"
link: "2013/02/test-driving-java-api-for-processing.html"
url: /2013/02/test-driving-java-api-for-processing.html
---

Writing and contributing to a specification is one thing. Working with it and looking into real examples a pre-condition if you want to give valuable feedback. The latest promoted GlassFish builds contain the renaming to 4.0 and I thought it might be a good time to give the Java API for Processing JSON (JSON-P) a test drive.
<br>
<br><b>Get Java EE 7 enabled GlassFish 4.0&nbsp;</b>
<br>
 First thing to do is to grab a copy of latest Java EE 7 enabled GlassFish 4.0 from the promoted builds. I am using the GlassFish Server Open Source Edition 4.0 (<a href="http://dlc.sun.com.edgesuite.net/glassfish/4.0/promoted/glassfish-4.0-b77.zip">build 77</a>) which seems to be quite stable. But in general, if you are trying this please keep in mind, that the promoted builds are basically development and unstable versions of ongoing work for GlassFish 4.0. It wouldn't make much sense to complain about them.
<br>
 unzip the download into a suitable location.
<br>
 For a later step you need to update the JSON-P RI within the modules directory. Follow Arun's Blog about getting and building the <a href="https://blogs.oracle.com/arungupta/entry/json_p_java_api_for" target="_blank">JSON-P RI</a>&nbsp;and copy the&nbsp;jsonp~git\impl\target\javax.json-1.0-SNAPSHOT.jar to the&nbsp;glassfish4\glassfish\modules\javax.json.jar. Make sure to make a copy of the original in case you do something wrong in that step. If you are feeling&nbsp;uncomfortable&nbsp;with that you can also skip it and select a different dependency later on ... The fact is, that the JSON-P API changed that much over the past few months, that the GlassFish included b02 isn't appropriate anymore to show you anything. So, for now, we have to tweak it a bit.
<br>
 Afterwards you are all set to integrate your fresh GlassFish install into your favorite IDE which could be <a href="" target="_blank">NetBeans</a>.
<br>
<br><b>Create a new Java EE 7 Projekt</b>
<br>
 The Java EE 7 archetype is located in the codehaus.org snapshot repository. In order to use it via NetBeans effectively you have to configure the repository on the "Services" tab under "Maven Repositories". The repository URL is https://nexus.codehaus.org/content/repositories/snapshots/. It might take a while to process the index. After that, proceed with the "New Project &gt; Maven &gt; Project from Archetype" wizard and enter "webapp-javaee7" into the search box. Select the 0.1-SNAPSHOT and click "Finish". Alternatively you can always go with the following command line:
<br>
<br>
<pre>mvn -DarchetypeGroupId=org.codehaus.mojo.archetypes -DarchetypeArtifactId=webapp-javaee7 -DarchetypeVersion=0.1-SNAPSHOT -DarchetypeRepository=https://nexus.codehaus.org/content/repositories/snapshots/ -DgroupId=net.eisele.sample -DartifactId=javaee7-jsonp -Dversion=1.0-SNAPSHOT -Dpackage=net.eisele.javaee7jsonp -Darchetype.interactive=false --batch-mode --update-snapshots archetype:generate </pre>
<br>
 Now open the project and edit the pom.xml. Change the scope of the javaee-web-api to provided and add the json-api dependency as shown below:
<br>
<br>
<pre class="brush:xml">&nbsp;&lt;dependencies&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;dependency&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;groupId&gt;javax.json&lt;/groupId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;artifactId&gt;javax.json-api&lt;/artifactId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;version&gt;1.0-SNAPSHOT&lt;/version&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;scope&gt;provided&lt;/scope&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/dependency&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;dependency&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;groupId&gt;javax&lt;/groupId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;artifactId&gt;javaee-web-api&lt;/artifactId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;version&gt;7.0-b72&lt;/version&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;scope&gt;provided&lt;/scope&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/dependency&gt; &nbsp; &nbsp; &lt;/dependencies&gt; </pre>
<br>
 Please make sure to use the version 1.0-SNAPSHOT and _not_ the<a href="https://wikis.oracle.com/display/GlassFish/Java+EE+7+Maven+Coordinates" target="_blank"> officially documented 1.0-b02</a>. We want to use the latest snapshot we build in the first paragraph with all the new APIs :) If you decided not to go the "build it your own way" you can simply use the&nbsp;1.0-b04 from maven central. This also works. Please make sure to have exactly this order of dependencies. If not, you will use the old b02 which is included with the javaee-web-api dependency :( Did someone say, Maven is easy? :)
<br>
 That's it for now. Let's create a simple JAX-RS endpoint.
<br>
<br><b>Adding a JAX-RS Person Resource</b>
<br>
 First thing to do is to write the basic JAX-RS resource. You can do this via the NetBeans' "RESTful Web Services from Pattern" wizard or yourself by simply outlining a brief class like the following:
<br>
<br>
<pre class="brush:java">@Path("person") public class PersonResource \{ &nbsp; &nbsp; public PersonResource() \{ &nbsp; &nbsp; \} &nbsp; &nbsp; @GET &nbsp; &nbsp; @Produces("application/json") &nbsp; &nbsp; public String getJson() \{ &nbsp; &nbsp; &nbsp; &nbsp; return "[]"; &nbsp; &nbsp; \} </pre> This class needs to be registered. You can either use Jerseys servlet mechanism to do this or register it yourself with the application specific ApplicationConfig: 
<br>
<br>
<pre class="brush:java">@javax.ws.rs.ApplicationPath("webresources") public class ApplicationConfig extends Application \{ &nbsp; &nbsp; @Override &nbsp; &nbsp; public Set&lt;Class&lt;?&gt;&gt; getClasses() \{ &nbsp; &nbsp; &nbsp; &nbsp; Set&lt;Class&lt;?&gt;&gt; resources = new java.util.HashSet&lt;&gt;(); &nbsp; &nbsp; &nbsp; &nbsp; resources.add(net.eisele.javaee7jsonp.PersonResource.class); &nbsp; &nbsp; &nbsp; &nbsp; return resources; &nbsp; &nbsp; \} \}</pre>
<br>
 Wow .. that should be all for now. You should give it a&nbsp;test drive. Deploy it to your domain and try&nbsp;http://localhost:8080/javaee7-jsonp/webresources/person. It should simply print the empty brackets []. Now it is time to get some JSON-P into the mix.
<br>
<br><b>Building JSON Objects with JSON-P</b>
<br>
 We are going to build a JSON representation of a person with the DOM-based API. Replace the return statement in the PersonResource with the following code:
<br>
<br>
<pre class="brush.java">&nbsp;JsonObjectBuilder builder = Json.createObjectBuilder(); &nbsp; &nbsp; &nbsp; &nbsp; builder.add("person", Json.createObjectBuilder() &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; .add("firstName", "Markus") &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; .add("lastName", "Eisele")); &nbsp; &nbsp; &nbsp; &nbsp; JsonObject result = builder.build(); &nbsp; &nbsp; &nbsp; &nbsp; StringWriter sw = new StringWriter(); &nbsp; &nbsp; &nbsp; &nbsp; try (JsonWriter writer = Json.createWriter(sw)) \{ &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; writer.writeObject(result); &nbsp; &nbsp; &nbsp; &nbsp; \} &nbsp; &nbsp; &nbsp; &nbsp; return sw.toString(); </pre>
<br>
 And now lets use my most favorite Chrome extension to look at what we've got: <a href="jsonp.PNG" imageanchor="1"><img border="0" src="jsonp.PNG"></a>
<br>
 It obviously works. Turning this the other way round would mean to read&nbsp;incoming&nbsp;JSON. This could look like the following:
<br>
<br>
<pre class="brush:java">&nbsp; &nbsp; &nbsp; &nbsp; String json = "\{\n" &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; + " &nbsp; &nbsp;\"person\": \{\n" &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; + " &nbsp; &nbsp; &nbsp; &nbsp;\"firstName\": \"Markus\",\n" &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; + " &nbsp; &nbsp; &nbsp; &nbsp;\"lastName\": \"Eisele\"\n" &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; + " &nbsp; &nbsp;\}\n" &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; + "\}"; &nbsp; &nbsp; &nbsp; &nbsp; JsonReader jr = Json.createReader(new StringReader(json)); &nbsp; &nbsp; &nbsp; &nbsp; JsonValue value = jr.readObject(); &nbsp; &nbsp; &nbsp; &nbsp; jr.close(); </pre> Beside the DOM-API you also have a Streaming-API which uses a
<br>
<br>
<pre class="brush:java">JsonGenerator generator = Json.createGenerator(new FileWriter(..)) JsonParser parser = Json.createParser(new StringReader(...)); </pre> to generate and parse JSON. Have a look at the <a href="http://json-processing-spec.java.net/nonav/releases/1.0/pfd-draft/javadocs/index.html" target="_blank">latest JavaDoc</a> for a complete reference. Go ahead and test drive yourself. The EG is still looking for feedback, so it might be a good idea to jump on the<a href="http://java.net/projects/json-processing-spec/lists" target="_blank"> users list </a>and send along your thoughts. I am also happy to read your ideas here!
<br>
<br>
<br>
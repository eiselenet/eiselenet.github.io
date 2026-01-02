---
title: "Generating Static WAR Content Archive with Maven"
date: 2009-07-21 06:48:00 +0000
layout: post
tags: ["Java EE", "static content", "Web", "maven", "jee", "example"]
slug: "generating-static-war-content-archive"
link: "2009/07/generating-static-war-content-archive.html"
url: /2009/07/generating-static-war-content-archive.html
---

You may have seen my simple <a href="http://www.eisele.net/blog/2009/07/quickstart-maven-jee5-project.html">JEE5 maven project setup</a> already. I am trying to add more and more features during the next few weeks that satisfy the needs of enterprise software development.
<br>
<br>
First add-on is the possibility to have a separate static-web.zip generated, which contains all relevant content, that should be deployed to a separate webserver.
<br>
<br>
All you have to do is to add the following to your war/pom.xml
<br>
<br>
&lt;plugin&gt;
<br>
 &lt;!-- assamble static content --&gt;
<br>
 &lt;artifactId&gt;maven-assembly-plugin&lt;/artifactId&gt;
<br>
 &lt;configuration&gt;
<br>
 &lt;descriptors&gt;
<br>
 &lt;descriptor&gt;src/main/assembly/static.xml&lt;/descriptor&gt;
<br>
 &lt;/descriptors&gt;
<br>
 &lt;/configuration&gt;
<br>
 &lt;executions&gt;
<br>
 &lt;execution&gt;
<br>
 &lt;id&gt;make-assembly&lt;/id&gt;
<br>
 &lt;phase&gt;package&lt;/phase&gt;
<br>
 &lt;goals&gt;
<br>
 &lt;goal&gt;single&lt;/goal&gt;
<br>
 &lt;/goals&gt;
<br>
 &lt;/execution&gt;
<br>
 &lt;/executions&gt;
<br>
 &lt;/plugin&gt;
<br>
<br>
Next step is to add a new folder in the web project (src\main\assembly) and create the needed assembly file (static.xml)
<br>
<br>
&lt;assembly&gt;
<br>
 &lt;id&gt;static&lt;/id&gt;
<br>
 &lt;formats&gt;
<br>
 &lt;format&gt;zip&lt;/format&gt;
<br>
 &lt;/formats&gt;
<br>
 &lt;includeBaseDirectory&gt;false&lt;/includeBaseDirectory&gt;
<br>
 &lt;fileSets&gt;
<br>
 &lt;fileSet&gt;
<br>
 &lt;directory&gt;src/main/webapp&lt;/directory&gt;
<br>
 &lt;includes&gt;
<br>
 &lt;include&gt;**/*.html&lt;/include&gt;
<br>
 &lt;include&gt;**/*.htm&lt;/include&gt;
<br>
 &lt;include&gt;**/*.jpg&lt;/include&gt;
<br>
 &lt;include&gt;**/*.gif&lt;/include&gt;
<br>
 &lt;include&gt;**/*.css&lt;/include&gt;
<br>
 &lt;include&gt;**/*.js&lt;/include&gt;
<br>
 &lt;/includes&gt;
<br>
 &lt;excludes&gt;
<br>
 &lt;exclude&gt;WEB-INF/*&lt;/exclude&gt;
<br>
 &lt;/excludes&gt;
<br>
 &lt;outputDirectory&gt;/static&lt;/outputDirectory&gt;
<br>
 &lt;/fileSet&gt;
<br>
 &lt;/fileSets&gt;
<br>
&lt;/assembly&gt;
<br>
<br>
<br>
If you now run maven clean install than you get a new static-web.zip file generated, which contains all the defined files and folders.
---
title: "GlassFish v3, CDI, Maven, Eclipse"
date: 2010-01-20 06:51:00 +0000
layout: post
tags: ["glassfishv3", "JSR-299", "maven", "Java EE 6", "eclipse"]
slug: "glassfish-v3-cdi-maven-eclipse"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2010/01/glassfish-v3-cdi-maven-eclipse.html
---

I am disaffected with NetBeans. It looks fancy, but I still can't get back the love I had using the 3.x version :) Seems as if I am converted to an Eclipse lover.
<br>
 This was the point where I started to try getting CDI examples to work with GlassFish v3, CDI/Weld, Maven and Eclipse.
<br>
 For those of you, haven't made it. Here is the how-to (I am using versions with the numbers in brackets):
<br>
<br>
 1) Get:
<br>
 - <a href="" target="_blank">Maven </a>(2.2.1)
<br>
 - <a href="" target="_blank">Eclipse </a>(3.6M2)
<br>
 - <a href="" target="_blank">GlassFish</a> (v3 build 74.2)
<br>
<br>
 2) Create a maven project using:
<br><code><br>
  mvn archetype:create -DgroupId=com.mycompany.app -DartifactId=cditests -DarchetypeArtifactId=maven-archetype-webapp<br></code>
<br>
<br>
 3) Add a my-webapp\src\main\java folder to it
<br>
 4) Add the following dependencies to the my-webapp\pom.xml
<br><code><br>
  &lt;dependency&gt;<br>
  &lt;groupId&gt;javax.inject&lt;/groupId&gt;<br>
  &lt;artifactId&gt;javax.inject&lt;/artifactId&gt;<br>
  &lt;scope&gt;provided&lt;/scope&gt;<br>
  &lt;version&gt;1&lt;/version&gt;<br>
  &lt;/dependency&gt;<br><br>
  &lt;dependency&gt;<br>
  &lt;groupId&gt;javax.enterprise&lt;/groupId&gt;<br>
  &lt;artifactId&gt;cdi-api&lt;/artifactId&gt;<br>
  &lt;scope&gt;provided&lt;/scope&gt;<br>
  &lt;version&gt;1.0&lt;/version&gt;<br>
  &lt;/dependency&gt;<br><br>
  &lt;dependency&gt;<br>
  &lt;groupId&gt;javax.annotation&lt;/groupId&gt;<br>
  &lt;artifactId&gt;jsr250-api&lt;/artifactId&gt;<br>
  &lt;version&gt;1.0&lt;/version&gt;<br>
  &lt;scope&gt;provided&lt;/scope&gt;<br>
  &lt;/dependency&gt; <br></code>
<br>
<br>
 5) Add an empty file named beans.xml to my-webapp\src\main\webapp\WEB-INF
<br>
 6) run mvn eclipse:eclipse for making this an eclipse based project
<br>
 7) add your code 
<br>
 8) run mvn clean install (compile / package)
<br>
 9) deploy it to your GlassFish domain
<br>
 10) think about enhancing this with JSF 2.0 ;)
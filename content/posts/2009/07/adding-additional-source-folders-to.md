---
title: "Adding additional Source Folders to Maven Project"
date: 2009-07-22 04:13:00 +0000
layout: post
tags: ["Java EE", "src folder", "jee", "maven", "example"]
slug: "adding-additional-source-folders-to"
link: "2009/07/adding-additional-source-folders-to.html"
url: /2009/07/adding-additional-source-folders-to.html
---

Most projects today have at least one part, that is generated in anyway. This is not too easy to integrate with maven. Mostly the generator's build relys on ant. Therefore it's always a discussion, where to place the build and how to trigger it. The most easiest way is to have a separate java eclipse project that builds with ant and copying the generated ressources to a /gen/ folder within your maven project. Beside the disadvantage, that you have a possible stale version of the generated sources checked in with your repository there are also some advantages, too.
<br>
Even if the majority of your team memvers do not have the generator project setup, they can still develop. Beside this, you skip the mostly tricky part of shipping a bunch of additional eclipse plugins to the rest of the team and you can keep the model itself under control of a selected group of persons. Anyway, if you have such a setup, it is no good idea to copy the generated sources to your /main/java folder. It should be obvious to the developers, that there are generated sources in the project. A practical workaround for this is to have different java src folders in your projects. This could be a /main/gen folder for example. In order to tell maven about it, you need to add additional resources to your projects pom-file.
<br>
Resource folder are ment for resources and not for java files. Therefore this is quite a bit like a workaround. But it works :)
<br>
The following snippet assumes three src folders in your project.
<br>
<br>
&lt;resources&gt;
<br>
 &lt;resource&gt;
<br>
 &lt;directory&gt;src/main/java&lt;/directory&gt;
<br>
 &lt;includes&gt;
<br>
 &lt;include&gt; **/*.java &lt;/include&gt;
<br>
 &lt;include&gt; **/*.properties &lt;/include&gt;
<br>
 &lt;include&gt; **/*.xml &lt;/include&gt;
<br>
 &lt;/includes&gt;
<br>
 &lt;/resource&gt;
<br>
 &lt;resource&gt;
<br>
 &lt;directory&gt;src/main/gen&lt;/directory&gt;
<br>
 &lt;includes&gt;
<br>
 &lt;include&gt; **/*.java &lt;/include&gt;
<br>
 &lt;include&gt; **/*.properties &lt;/include&gt;
<br>
 &lt;include&gt; **/*.xml &lt;/include&gt;
<br>
 &lt;/includes&gt;
<br>
 &lt;/resource&gt;
<br>
 &lt;resource&gt;
<br>
 &lt;directory&gt;src/main/test&lt;/directory&gt;
<br>
 &lt;includes&gt;
<br>
 &lt;include&gt; **/*.java &lt;/include&gt;
<br>
 &lt;include&gt; **/*.properties &lt;/include&gt;
<br>
 &lt;include&gt; **/*.xml &lt;/include&gt;
<br>
 &lt;/includes&gt;
<br>
 &lt;/resource&gt;
<br>
 &lt;/resources&gt;
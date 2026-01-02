---
title: "Using Maven Dependency Projects"
date: 2009-07-24 06:17:00 +0000
layout: post
tags: ["Java EE", "jee", "maven", "dependency projects", "example"]
slug: "using-maven-dependency-projects"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2009/07/using-maven-dependency-projects.html
---

Maven is number one in managing dependencies. This could get even simpler, if you use plugins for your favourite ide (e.g. m2eclipse).
<br>
Anyway, dependency management is the most challinging part in maven projects. If you have a growing project, you should sit back and think about the project structure and the needed dependencies a bit.
<br>
Best practice building jee applications for me is to use so called "dependency projects". If you have your web project you simply add another project called web-deps of type pom and link a dependency to it from your web project. Now you have a single place, where you can manage all your dependencies for your web project. 
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/maven-dep-projects-789077.png"><img style="display:block; margin:0px auto 10px; text-align:center;cursor:pointer; cursor:hand;width: 137px; height: 103px;" src="http://www.eisele.net/blog/uploaded_images/maven-dep-projects-789076.png" border="0" alt=""></a>
<br>
<br>
There are several advantages of this approach:
<br>
* One central place to manage all dependencies of your web/ejb projects. You can have some web-tools projects that depend on the deps. This helps preventing circles. 
<br>
* Assign one responsible person in your project managing the deps. 
<br>
* Easier change container dependencies. By only adding new deps projects and not breaking the actuall build. This gives you the option to have one deps project per container for example (e.g. web-deps-wls, web-deps-was).
<br>
<br>
<br><strong><br>
 Follow the other parts of this series:</strong>
<br>
<ul>
 <br>
 <li><a href="http://www.eisele.net/blog/2009/07/adding-additional-source-folders-to.html">Adding additional Source Folders to Maven Project</a></li>
 <br>
 <li><a href="http://www.eisele.net/blog/2009/07/generating-static-war-content-archive.html">Generating Static WAR Content Archive with Maven</a></li>
 <br>
 <li><a href="http://www.eisele.net/blog/2009/07/quickstart-maven-jee5-project.html">Quickstart Maven JEE5 Project</a></li>
 <br>
</ul>
<br>
<br>
Download the complete <a href="http://www.eisele.net/jar/jee-maven-project.zip">Quickstart Maven JEE5 Project</a>.
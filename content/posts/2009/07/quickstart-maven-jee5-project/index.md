---
title: "Quickstart Maven JEE5 Project"
date: 2009-07-21 05:33:00 +0000
layout: post
tags: ["Java EE", "jee", "maven", "example"]
slug: "quickstart-maven-jee5-project"

url: /2009/07/quickstart-maven-jee5-project.html
---

I had to start several JEE5 projects recently and always needed to setup a new maven project structure over and over again. Therefore I decided to build a basic maven project setup for a default JEE5 application containing:
<br>
- one EAR project
<br>
- one EJB project
<br>
- one WAR project
<br>
<br>
You can <a href="http://www.eisele.net/jar/jee-maven-project.zip" onclick="javascript: pageTracker._trackPageview('/download/jee-maven-project');">download the project setup</a> and give it a try. All projects have the base setup from the JEE5 facets of the related maven plugins. Therefore: Don't expect any magic behind this. The only intention of this was to have a quickstart and don't waste any time on creating the same four poms and folder structures over and over again.
<br>
<br>
Have a look at the general project structure:
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/project_structure-766146.png"><img style="display:block; margin:0px auto 10px; text-align:center;cursor:pointer; cursor:hand;width: 114px; height: 200px;" src="http://www.eisele.net/blog/uploaded_images/project_structure-766144.png" border="0" alt=""></a>
---
title: "Software Quality: JSF Component Libraries - The Candidates, #primefaces, #icefaces, #richfaces"
date: 2009-12-09 11:19:00 +0000
layout: post
tags: ["xdepend", "components", "icefaces", "primefaces", "richfaces", "software quality", "jsf"]
slug: "software-quality-jsf-component_09"
link: "2009/12/software-quality-jsf-component_09.html"
url: /2009/12/software-quality-jsf-component_09.html
---

We did a lot of theory about static code analysis in <a href="http://www.eisele.net/blog/2009/12/software-quality-jsf-component.html">part I</a> and <a href="http://www.eisele.net/blog/2009/12/software-quality-basics-ii-ccd-acd-ca.html">part II</a> of my software quality posts.
<br>
 Now it is time to start over with the real work. In this post (part III) we will look at the candidates in more details. I have choosen to examine three of the most popular JSF component libraries today. 
<br>
<br><b>Preparation and preface</b>
<br>
 In preperation for the analysis I did a checkout from the project repositories. After that I setup the local build and made shure, that all projects could be compiled and packaged locally. 
<br>
 The review itself was done by some tools. One is the already mentioned msg java measuring station (JMP). The second one is <a href="" target="_blank">XDepend</a> which I was thankfully given a license from it's creator (thanks for that!!). I am not going to publish the complete reports (If the project leads are interested, I will hand them out to them, of course. Let me know!). The goal of this series is to provide a brief overview of the projects and highlight some hotspots. This is not going to be a beauty contest nor am I going to bash anybody with this. It should give my readers a brief understanding about static code analysis and the typical findings. 
<br>
 The NCSS results from the JMP and XDepend vary a bit in some places. This is because of the fact, that XDepend does a more complete analysis than the JMP does. Further more the configuration of the JMP is not that straight forward. This gets even worser, if you have lot's of subprojects (e.g. RichFaces, one project per component...). Therefore I decided to focus on the core projects with the JMP analysis and do a complete analysis with XDepend.
<br>
<br><b>PrimeFaces</b>
<br><a href="" target="_blank">PrimeFaces</a> is an open source component suite for Java Server Faces featuring 70+ Ajax powered rich set of JSF components. Additional TouchFaces module features a UI kit for developing mobile web applications. 
<br>
 It is a quite fresh and new library which grows very quickly. It caught my attention cause I am using FacesTrace since some time. 
<br>
 With every result I publish here about primefaces, you should have in mind, that his is a SNAPSHOT release and not intendet for productive use. Therefore it may not be representative. 
<br>
<br><i>Basics:</i>
<br>
 Version: 1.0.0-SNAPSHOT (readonly checkout at 27.11.09)
<br>
 Jar Name: primefaces-1.0.0-SNAPSHOT.jar
<br>
 Jar Size: 1,60 MB (1.682.956 Bytes)
<br>
 Dependent libraries: 30 with a total size of 7,56 MB (7.929.734 Bytes)
<br>
 JMP included subprojects: complete primefaces-read-only
<br>
 XDepend jars: primefaces-1.0.0-SNAPSHOT.jar
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/visualndependview-primefaces-701677.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="320" src="http://www.eisele.net/blog/uploaded_images/visualndependview-primefaces-701642.png" width="320"></a>
<br>
<br><i>Size (JMP):</i>
<br>
 NCSS - Lines of code: 8340
<br>
 Number of packages: 86
<br>
 Number of classes (w/o inner classes): 160
<br>
 Number of functions: 796
<br>
<br><i>Metrics (Xdepend):</i>
<br>
 Number of ByteCode instructions: 74741
<br>
 Number of lines of code: 15784
<br>
 Number of lines of comment: 245
<br>
 Percentage comment: 1%
<br>
 Number of jars: 1
<br>
 Number of classes: 306
<br>
 Number of types: 324
<br>
 Number of abstract classes: 3
<br>
 Number of interfaces: 17
<br>
 Number of value types: 0
<br>
 Number of exception classes: 0
<br>
 Number of annotation classes: 0
<br>
 Number of enumerations classes: 1
<br>
 Number of generic type definitions: 0
<br>
 Number of generic method definitions: 0
<br>
 Percentage of public types: 99,07% 
<br>
 Percentage of public methods: 85,04% 
<br>
 Percentage of classes with at least one public field: 26,54% 
<br>
<br><b>RichFaces</b>
<br><a href="http://www.jboss.org/richfaces" target="_blank">RichFaces </a> is a component library for JSF and an advanced framework for easily integrating AJAX capabilities into business applications. 
<br>
 It is one of the more mature libraries around. Mature in terms of age. Have seen it in a couple of projects and it is the favorite implementation for some of my employer's customers.
<br>
 This is the latest GA version of RichFaces.
<br>
<br><i>Basics:</i>
<br>
 Version: 3.3.3 (readonly checkout at 27.11.09)
<br>
 Jar Name: richfaces-api-3.3.3-SNAPSHOT.jar and richfaces-impl-3.3.3-SNAPSHOT.jar
<br>
 Jar Size: 1,65 MB (1.737.826 Bytes)
<br>
 Dependent libraries: 20 with a total size of 4,50 MB (4.726.588 Bytes)
<br>
 JMP included subprojects: framework/impl and framework/api
<br>
 XDepend jars: richfaces-api-3.3.3-SNAPSHOT.jar and richfaces-impl-3.3.3-SNAPSHOT.jar
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/visualndependview-richfaces-763929.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="320" src="http://www.eisele.net/blog/uploaded_images/visualndependview-richfaces-763903.png" width="320"></a>
<br>
<br><i>Size (JMP):</i>
<br>
 NCSS - Lines of code: 9186 
<br>
 Number of packages: 21 
<br>
 Number of classes (w/o inner classes): 225 
<br>
 Number of functions: 1526 
<br>
<br><i>Metrics (Xdepend):</i>
<br>
 Number of ByteCode instructions: 166694
<br>
 Number of lines of code: 35227
<br>
 Number of lines of comment: 34938
<br>
 Percentage comment: 49%
<br>
 Number of jars: 2
<br>
 Number of classes: 790
<br>
 Number of types: 938
<br>
 Number of abstract classes: 69
<br>
 Number of interfaces: 133
<br>
 Number of value types: 0
<br>
 Number of exception classes: 11
<br>
 Number of annotation classes: 1
<br>
 Number of enumerations classes: 15
<br>
 Number of generic type definitions: 0
<br>
 Number of generic method definitions: 0
<br>
 Percentage of public types: 74,09% 
<br>
 Percentage of public methods: 74,73% 
<br>
 Percentage of classes with at least one public field: 9,49% 
<br>
<br>
<br><b>ICEfaces</b>
<br><a href="" target="_blank">ICEfaces </a> as a leading open source Ajax framework, ICEfaces is more than a Ajax JSF component library, it's an J2EE Ajax framework for developing and deploying rich enterprise applications (REAs).
<br>
 To be honest, I have not seen it in the wild too much. It was suggested to include this via a fellow twitter follower and I thought three is better than two :)
<br>
 This is the latest GA version of ICEFaces.
<br>
<br><i>Basics:</i>
<br>
 Version: 1.7.1 (readonly checkout at 27.11.09)
<br>
 Jar Name: icefaces.jar and icefaces-comps.jar
<br>
 Jar Size: 2,64 MB (2.770.861 Bytes)
<br>
 Dependent libraries: 42 with a total size of 10,4 MB (10.993.491 Bytes)
<br>
 JMP included subprojects: icefaces/core and icefaces/component
<br>
 XDepend jars: icefaces.jar and icefaces-comps.jar
<br>
<br><a href="http://www.eisele.net/blog/uploaded_images/visualndependview-icefaces-700961.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="320" src="http://www.eisele.net/blog/uploaded_images/visualndependview-icefaces-700933.png" width="320"></a>
<br>
<br><i>Size (JMP):</i>
<br>
 NCSS - Lines of code: 38109 
<br>
 Number of packages: 67 
<br>
 Number of classes (w/o inner classes): 521 
<br>
 Number of functions: 5606 
<br>
<br><i>Metrics (Xdepend):</i>
<br>
 Number of ByteCode instructions: 177269
<br>
 Number of lines of code: 41710
<br>
 Number of lines of comment: 16161
<br>
 Percentage comment: 27%
<br>
 Number of jars: 2
<br>
 Number of classes: 664
<br>
 Number of types: 714
<br>
 Number of abstract classes: 29
<br>
 Number of interfaces: 50
<br>
 Number of value types: 0
<br>
 Number of exception classes: 5
<br>
 Number of annotation classes: 0
<br>
 Number of enumerations classes: 0
<br>
 Number of generic type definitions: 0
<br>
 Number of generic method definitions: 0
<br>
 Percentage of public types: 82,77% 
<br>
 Percentage of public methods: 82,52% 
<br>
 Percentage of classes with at least one public field: 14,15% 
<br>
<br>
<br>
 This is all for now. Part IV will cover the details for checkstyle, findbugs and simian for all candidates. Stay tuned.
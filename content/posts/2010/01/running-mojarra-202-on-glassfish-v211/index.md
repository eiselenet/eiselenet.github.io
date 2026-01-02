---
title: "Running Mojarra 2.0.2 on GlassFish v2.1.1"
date: 2010-01-11 06:44:00 +0000
layout: post
tags: ["v2.1.1", "glassfish", "jsf", "Mojarra", "2.0.2"]
slug: "running-mojarra-202-on-glassfish-v211"

url: /2010/01/running-mojarra-202-on-glassfish-v211.html
---

First of all: Happy new year to all of my readers. I had a quite busy time during the start of the new year. But now, everything seems back to normal operation and I can start over blogging about my fav topics.
<br>
<br>
 A few days ago, a co-worker asked me, about running JSF 2.0 on GlassFish v2.x. I realy was not shure about this first. The JSF 2.0 spec is part of JEE 6 and should work with JDK 1.6. But what about JEE 5 and JDK 1.5? Reading the spec clearly states that it is based on Java 2 Platform, Standard Edition, version 5.0. Ok. This should work according to the spec. 
<br>
<br>
 Let's start.
<br>
 Grep the latest Mojarra 2.0 Download from the <a href="https://javaserverfaces.dev.java.net/servlets/ProjectDocumentList" target="_download">projects websites</a>. If you are there already, also get the sources. We will need them later ;)
<br>
 Extract the binary distribution (mojarra-2.0.2-FCS-binary.zip/.tar) and setup the example build. I was trying to get the "guessNumber" example (mojarra-2.0.2-FCS\samples\guessNumber) working and will use this further on, too.
<br>
 If you have everything in place, switch to your favorite JDK 1.5 version (I was using the latest 1.5.0_22) and give it a try (mvn clean install).
<br>
 Without chaning anything, you will notice, that this will not work:
<br>
<br>
 [INFO] Compilation failure
<br>
 X:\mojarra-2.0.2-FCS\samples\guessNumber
<br>
 \src\main\java\guessnumber\ClientSideValidatorHandler.java:[42,-1] cannot access
<br>
 javax.faces.component.UIComponent
<br>
 bad class file: ~.m2\repository\javax\faces\jsf
<br>
 -api\2.0\jsf-api-2.0.jar(javax/faces/component/UIComponent.class)
<br>
 class file has wrong version 50.0, should be 49.0
<br>
<br>
 What does this version thing mean? It states, that the UIComponent.class was compiled with JDK 1.6 and you are trying to use it with JDK 1.5. Fail :|
<br>
<br>
 Lets correct this.
<br>
 Next is to extract the sources from the download (mojarra-2.0.2-FCS-source.zip/.tar) and compile a JDK 1.5 compliant jsf-api project. Thank god, the JSF guys know how to setup a build. Copy the build.properties.glassfish.orig to a build.properties file and add your jsf.build.home. Beeing behind a corp firewall forces you to add the needed proxy configurations to your ~/.m2/settings.xml file next. Now call ant and build Mojarra 2.0.2.
<br>
<br>
 Now we are going to use our two new jar files. Find
<br>
 the jsf-api.jar in \mojarra-2.0.2-FCS-source\jsf-api\build\lib and the 
<br>
 the jsf-impl.jar in mojarra-2.0.2-FCS-source\jsf-ri\lib and install them to your local maven repository. I was using other version numbers but same group and artifact ids for testing.
<br>
<br>
 mvn install:install-file -DgroupId=javax.faces -DartifactId=jsf-api -Dversion=2.0.2 -Dpackaging=jar -Dfile=jsf-api.jar
<br>
<br>
 mvn install:install-file -DgroupId=javax.faces -DartifactId=jsf-impl -Dversion=2.0.2 -Dpackaging=jar -Dfile=jsf-impl.jar
<br>
<br>
 Now you have to change the pom.xml of your guessNumber example to reflect the changes. If you are not using a JEE5 SDK, you also have to add the javax.annotation dependency. 
<br>
<br>
 &lt;dependency&gt;
<br>
 &lt;groupId&gt;javax.faces&lt;/groupId&gt;
<br>
 &lt;artifactId&gt;jsf-api&lt;/artifactId&gt;
<br>
 &lt;version&gt;2.0.2&lt;/version&gt;
<br>
 &lt;/dependency&gt;
<br>
<br>
<br>
 &lt;dependency&gt;
<br>
 &lt;groupId&gt;com.sun.faces&lt;/groupId&gt;
<br>
 &lt;artifactId&gt;jsf-impl&lt;/artifactId&gt;
<br>
 &lt;version&gt;2.0.2&lt;/version&gt;
<br>
 &lt;/dependency&gt;
<br>
<br>
 Almost finished now. Next step is to add a sun-web.xml to the webapp (guessNumber\src\main\webapp\WEB-INF). It should contain:
<br>
<br>
 &lt;class-loader delegate="false"/&gt; 
<br>
 &lt;property name="useBundledJsf" value="true" /&gt;
<br>
<br>
 Now you are ready to go. Call mvn clean install and deploy your guessNumber\target\guessNumber.war to your GlassFish v2.1.1 domain. And you are done!
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/guessNumber-734986.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="320" src="http://www.eisele.net/blog/uploaded_images/guessNumber-734984.png" width="320"></a>
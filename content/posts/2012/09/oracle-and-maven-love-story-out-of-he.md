---
title: "Oracle and Maven - a love story out of the ordinary."
date: 2012-09-22 18:19:00 +0000
layout: post
tags: ["love", "oracle", "maven", "story"]
slug: "oracle-and-maven-love-story-out-of-he"
link: "2012/09/oracle-and-maven-love-story-out-of-he.html"
url: /2012/09/oracle-and-maven-love-story-out-of-he.html
---

It is weekend. Not my typical time for publishing blog-posts. I tend to write them but publishing is done during the week. This might be a different kind of post because I read the Javalobby post about "<a href="http://java.dzone.com/articles/oracle-and-maven" target="_blank">Oracle and Maven</a>" this morning and I was wondering if that is actually what everybody thinks out there.
<br>
<br><b>Where is Maven within Oracle?</b>
<br>
 Short answer: Everywhere! Need a short list? Here we go:
<br>
 GlassFish is build with Maven (compare <a href="https://wikis.oracle.com/display/GlassFish/FullBuildInstructions" target="_blank">FullBuildInstructions</a>). Until v3 it was 2.2.1 but the tunk now is on 3.0.3. There is still some Ant around but most of the stuff has been using Maven since a long time.
<br>
 You have a sufficient support with plugins. The <a href="" target="_blank">GlassFish Maven plugin</a>, the <a href="http://docs.sun.com/app/docs/doc/821-1208/gijhs?l=en&amp;a=view" target="_blank">GlassFish Embedded Plugin</a> and last but not least the <a href="http://cargo.codehaus.org/Glassfish+3.x" target="_blank">GlassFish support</a> with Maven's Cargo Plugin. On top of that you have <a href="" target="_blank">remote and local container adapters</a>&nbsp;for <a href="" target="_blank">Arquillian</a> here.
<br>
<br><a href="http://oracle.com/weblogic" target="_blank">WebLogic</a> is a different kind of story. The server and the distribution itself is still build with Ant as far as I know. But there also is a growing Maven support over here. We have support from a <a href="http://docs.oracle.com/cd/E21764_01/web.1111/e13702/maven_deployer.htm" target="_blank">WebLogic Maven Plugin since 10.3.5</a>&nbsp;and with the <a href="http://blog.eisele.net/2011/12/installing-and-using-new-weblogic-12.html" target="_blank">latest 12c the features grew</a> a lot. Weird here is, that the plugins don't reside in central. You have to install them locally and make them available for every developer through your own proxy. Even the plugin-group registration has to be done manually. Not very convenient to use, but after all: We have it. Further on you can distribute complete weblogic installations via the plugin based on the zip installer.
<br>
 Maven has <a href="http://wiki.netbeans.org/MavenBestPractices" target="_blank">first class support</a> with <a href="" target="_blank">NetBeans</a> and I find it quite handy at all. If you find a complaint here, let me know. You can use the build in version (3.0.3) which is nearly the&nbsp;latest&nbsp;but nobody stops you from using your custom version.
<br>
 Oracle's brand new and upcoming offering, the <a href="" target="_blank">Java Cloud Service</a> has a decent <a href="http://blog.eisele.net/2012/09/oracle-public-cloud-java-service.html" target="_blank">Maven support</a>. And so are many many other projects and reference implementations.
<br>
<br>
 To me this doesn't sound like someone is having "a strategy for avoiding Maven" (quote from the Javalobby-Post). In fact it feels like the process I have seen a lot with other projects and technologies. Adoption. Obviously not early adoption but tell me about the few products doing premature stuff at a decent risk. That doesn't sounds like a way a very successful company would go.
<br>
<br><b>What could be improved?</b>
<br>
 I agree on the annoyance with the javaee*-api artifacts in repo1.maven.org. That is completely uncomprehending and I don't have a clue why that is still the fact. I hope that this post will put this back on any of the priority lists. I for myself am using the Java EE BOM from JBoss here. Another idea is to pull in the glassfish-embedded-all:3.1.2.2 dependency which also works but obviously offers more than you probably want to have. Another annoyance is the fact, that&nbsp;<a href="" target="_blank">EclipseLink</a>&nbsp;as the JPA reference implementation isn't on central. You still have to use the Eclipse Maven repo for that. That complicates stuff unnecessary. Every complaint to change that wasn't successful. But I still have hope. But it might be, that the reason for that is that EclipseLink itself still is using Ant to build. Even if there are some maven dependencies and plugins available. Even if they are not officially supported. (e.g.&nbsp;<a href="" target="_blank">Maven plugin for Eclipselink static weaving</a>)
<br>
<br class="Apple-interchange-newline"><b>What is wrong with the Javalobby post?</b>
<br>
 Different things. First of all I don't like the tone. And I don't like if somebody doesn't do his homework and simply states: " ... the Oracle artifacts are ..."&nbsp;(quote from the Javalobby-Post). Generalization isn't helpful because nobody knows what to change. And with respect to this I understand Oracle (if this is a valid generalization on my side) that nobody actually feels responsible for changing anything. So, if you have things that sucks: Get them out and tell the world. But don't blame a brand for "doing things wrong". There are people responsible for that. Find out who they are and give honest feedback.
<br>
 But beside this, the content is wrong. At least partly. I looked around and searched maven central. I did some spot tests (GlassFish, Mojarra, Metro) if they have souces and the javadoc jars are available. They have at least the sources. Partly javadoc. There isn't a complete coverage on every artifact. And especially those ones developers are looking for (e.g. <a href="" target="_blank">jsf-api</a>) don't provide javadocs. There is room for improvement and I hope that someone from the middleware group is reading this and starts getting the priorities right here. Haven complete dependencies in central is developer productivity. Nothing more and nothing less. And this is something the&nbsp;stewards&nbsp;of Java EE should take care of.
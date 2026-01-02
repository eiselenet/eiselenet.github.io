---
title: "Java EE 7 - what's coming up for 2012? First hints."
date: 2011-01-12 06:12:00 +0000
layout: post
tags: ["Java EE", "timeline", "future"]
slug: "java-ee-7-whats-coming-up-for-2012"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2011/01/java-ee-7-whats-coming-up-for-2012.html
---

Even if the actual Java EE 6 version is still not too widespread, we already have seen the first signs of the next EE 7 version written to the sky. Roberto Chinnici did a <a href="http://blog.eisele.net/2010/09/java-eenext-cloud-exalogic-some.html">first presentation about the general future</a> at JavaONE, September last year. At Devoxx we saw a talk about <a href="http://www.parleys.com/#st=5&amp;id=2126" target="_blank">The Future Roadmap of Java EE</a> done by Jérôme Dochez, Linda DeMichiel and Paul Sandoz. And finally there is a GlassFish Podcast <a href="http://blogs.sun.com/glassfishpodcast/entry/episode_071_java_ee_7" target="_blank">Episode #071 - Java EE 7 discussion with Roberto Chinnici</a>. The latest addition could be the "<a href="http://www.slideshare.net/jeromedochez/java-one-brazilkeynotedochez" target="_blank">Future of Java EE</a>" keynote by Jerome Dochez seen at JavaONE Brazil.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="java_ee7.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="131" src="java_ee7.jpg" width="200"></a>
</div><b>The theme - cloud</b>
<br>
 The theme for Java EE 7 is the cloud. Clouds tend to be elastic, and most of the current computing centers are not. In terms of Java EE 7 we're talking about multi-tenancy, application versioning, support for NRDBMS stores but also about modularity, trying to leverage what will be done at the JavaSE level. The basis is a virtualization layer. Followed by state management for scalability and a number of services on top. What is called a "Java service" in the stack should be the equivalent to the existing containers. That's what runs your Java code. But there will be more services that will be formalized to provide a comprehensive platform.
<br>
 As already expected during <a href="" target="_blank">Devoxx</a>, JAX-RS and JPA seemed to be the closest to being filed as JSRs.
<br>
<br><b>First JSRs</b>
<br>
 And that's what happened yesterday (11/01/11). <a href="http://jcp.org/en/jsr/detail?id=339" target="_blank">JSR 339</a>: JAX-RS 2.0: The Java API for RESTful Web Services and <a href="http://jcp.org/en/jsr/summary?id=338" target="_blank">JSR 338</a>: Java Persistence 2.1. Even if we are still missing the umbrella JSR for Java EE 7, taking a closer look at the two gives some brief hints on when to expect it. 
<br>
<br><b>JAX-RS 2.0</b>
<br>
 The JAX-RS 2.0 <a href="http://markmail.org/thread/wgm3hj3rrva3j6jo" target="_blank">draft JSR</a> has been around since the end of November 2010. And even Paul Sandoz's slide deck from Devoxx session about <a href="http://blogs.sun.com/sandoz/resource/Devoxx2010_JAX_RS_Session.pdf">"JAX-RS, Java EE6 and the Future" (PDF)</a> points out some of the enhancements coming.
<br>
 The proposed draft goes into more details. The key points are: Two client APIs, Hypermedia support, JAX-RS compatible MVC architecture (with JSPs as one kind of views), Parameter validation, Integration with JSR 330 (DI), a simple asynchronous request processing model, server side content negotiation and some minor tweaks like bug fixes.
<br>
 The Reference Implementation will be developed as part of Project Jersey.
<br>
<br><b>JPA 2.1</b>
<br>
 It was speculated, that JPA has a long list of potential enhancements, ranging from dynamic definition of PU, more control on persistence context synchronization, mapping between JPQL and criteria queries and much more. And here we go: Additional support for the use of custom types and transformation methods, "fetch groups" and/or "fetch plans", immutable attributes and readonly entities, user-configurable mapping naming strategies, more flexibility in the use of generated values, additional mapping metadata, support for multitenancy, additional event listeners and callbacks, dynamic definition of persistence unit, JPQL support for stored procedures, and some more.
<br>
 The Java Persistence RI is being developed under the open source EclipseLink project and is being made available through GlassFish. 
<br>
<br>
 As usual, both already filled JSRs are proposals. The EGs will have to negotiate on them. And even if they will be included as a required part of the Java Platform, Enterprise Edition version 7 aligning the timeline of both JSRs with that of Java EE 7 may therefore impact the scope. So the next step is to wait for the JSR Review Ballot which will end on 24 Jan, 2011 for both.
<br>
<br><b>What's the timeline?</b>
<br>
 Both mention Q3/Q4 2012 as schedule for the final release. Early drafts will be available at the end of 2011. All this could point to the end of 2012 as the release date for Java EE 7. 
<br>
<br>
 To be honest, both JSRs are not the hottest ones for EE 7. At last to me. There is a lot of stuff to come. I am personally looking forward to see the revised module and take a deeper look at the integration with the new <a href="http://blog.eisele.net/2010/09/java-se-7-8-9-road-ahead.html">Java SE 7, 8, 9</a> features.
---
title: "Java SE 7 - new features in detail"
date: 2010-10-12 02:40:00 +0000
layout: post
tags: ["java SE 7", "features"]
slug: "2010-10-12-java-se-7-new-features-in-detail"
url: /2010/10/java-se-7-new-features-in-detail.html
---

A lot has been talked about the planned roadmap. And up to now it seems as if it is still not sure, that we will have a new Java SE 7 as planned (Mid 2011), but I thought it may be time to take a deeper look at what's in it and give you a collection of further readings on the separate topics. Following the post by Mark Reinhold from yesterday, where he states details about <a href="http://blogs.sun.com/mr/entry/plan_b_details" target="_blank">plan-b</a> this is a more detailled collection with aggregated links and further readings.
<br>
 The umbrella JSR for Java SE 7 should be submittet during the next weeks. Mark points out, that there might be changes to the list:
<br>
<blockquote>
 These lists should be considered neither exhaustive nor ﬁnal—they are merely the starting points at which the Expert Groups for the Umbrella JSRs will begin their deliberations. 
 <br>
  (M. Reinhold)
</blockquote>
<br><!--more-->
<br><b>InvokeDynamic byte code + supporting features</b>
<br>
 VM and language extensions to support the implementation of dynamically-typed languages at performance levels near to that of the Java language itself. This feature, which implements JSR 292: Supporting Dynamically Typed Languages on the Java Platform, is the logical follow-on to JSR 223: Scripting for the Java Platform. Support for JSR 223 was provided as part of Java SE 6 and implemented in JDK 6. 
<br>
<br>
 Lead : <a href="http://blogs.sun.com/jrose" target="_blank">John Rose</a>
<br>
 JSR : <a href="http://jcp.org/en/jsr/detail?id=292" target="_blank">292</a>
<br>
 Project : <a href="http://openjdk.java.net/projects/mlvm" target="_blank">mlvm</a>
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#f353" target="_blank">f353</a>
<br>
<br>
 Articles:
<br><a href="" target="_blank">New JDK 7 Feature: Support for Dynamically Typed Languages in the Java Virtual Machine</a>
<br>
<br><b>Concurrency and Collections APIs</b>
<br>
 A lightweight fork/join framework, flexible and reusable synchronization barriers, transfer queues, a concurrent-reference HashMap, and thread-local pseudo-random number generators. 
<br>
<br>
 Lead : <a href="http://gee.cs.oswego.edu/dl" target="_blank">Doug Lea</a>
<br>
 JSR : <a href="http://jcp.org/en/jsr/detail?id=166" target="_blank">166</a>
<br>
 Project : <a href="http://gee.cs.oswego.edu/dl/concurrency-interest/index.html" target="_blank">jsr166y</a>
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#f515" target="_blank">f515</a>
<br>
<br><b>Small Language Enhancements (Project "Coin")</b>
<br>
 A set of small language changes intended to simplify common, day-to-day programming tasks: <a href="http://mail.openjdk.java.net/pipermail/coin-dev/2009-February/000001.html" target="_blank">Strings in switch statements</a>, <a href="http://mail.openjdk.java.net/pipermail/coin-dev/2009-February/000011.html" target="_blank">automatic resource management</a>, <a href="http://mail.openjdk.java.net/pipermail/coin-dev/2009-February/000009.html" target="_blank">improved type inference for generic instance creation ("diamond")</a>, <a href="http://mail.openjdk.java.net/pipermail/coin-dev/2009-March/000217.html" target="_blank">simplified varargs method invocation</a>, better integral literals, and <a href="http://mail.openjdk.java.net/pipermail/coin-dev/2009-February/000003.html" target="_blank">improved exception handling (multi-catch)</a>
<br>
<br>
 Lead : <a href="http://blogs.sun.com/darcy" target="_blank">Joe Darcy</a>
<br>
 JSR : TBD
<br>
 Project : <a href="http://openjdk.java.net/projects/coin" target="_blank">coin</a>
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#f618" target="_blank">f618</a>
<br>
<br><b>Upgrade class-loader architecture</b>
<br>
 Modifications to the ClassLoader API and implementation to avoid deadlocks in non-hierarchical class-loader topologies.
<br>
<br>
 Lead : Karen Kinnear
<br>
 Project: <a href="http://openjdk.java.net/groups/core-libs/ClassLoaderProposal.html" target="_blank">core-libs</a>
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#f352" target="_blank">f352</a>
<br>
<br><b>Method to close a URLClassLoader</b>
<br>
 A method that frees the underlying resources, such as open files, held by a URLClassLoader.
<br>
<br>
 Lead : <a href="http://blogs.sun.com/michaelmcm" target="_blank">Michael McMahon</a>
<br>
 Project : <a href="http://blogs.sun.com/michaelmcm/entry/closing_a_urlclassloader" target="_blank">core</a>
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#f584" target="_blank">f584</a>
<br>
<br><b>Unicode 6.0</b>
<br>
 Upgrade the supported version of Unicode to 6.0 which adds 2,088 characters,new properties and data files, corrects character properties for existing characters and provides format improvements.
<br>
<br>
 Lead : Yuka Kamiya
<br>
 Specification : <a href="" target="_blank">Unicode 6.0</a>
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#f497" target="_blank">f497</a>
<br>
<br><b>Locale enhancement </b>
<br>
 The Java Locale has fallen out of date, and needs to be enhanced to avoid loss of data. Relatively small changes to Locale can update it to modern standards, and avoid significant problems for companies using Java. The Java community should agree to extend the model in Locale to add the features of IETF BCP 47 and UTR 35 (CLDR/LDML).
<br>
<br>
 Project : <a href="" target="_blank">Locale Enhancement Project</a> | <a href="" target="_blank">Locale Enhancement Project Home</a>
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#fa535895" target="_blank">fa535895</a>
<br>
<br><b>New I/O APIs (NIO.2)</b>
<br>
 New APIs for filesystem access, scalable asynchronous I/O operations, socket-channel binding and configuration, and multicast datagrams.
<br>
<br>
 Lead : <a href="http://blogs.sun.com/alanb" target="_blank">Alan Bateman</a>
<br>
 JSR : <a href="http://jcp.org/en/jsr/detail?id=203" target="_blank">203</a>
<br>
 Project: <a href="http://openjdk.java.net/projects/nio" target="_blank">nio</a>
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#f250" target="_blank">f250</a>
<br>
<br>
 Articles:
<br><a href="http://today.java.net/pub/a/today/2008/07/03/jsr-203-new-file-apis.html" target="_blank">java.nio.file</a>
<br>
<br><b>Transport Layer Security (TLS) 1.2</b>
<br>
 The TLS protocol provides communications security over the Internet. The protocol allows client/server applications to communicate in a way that is designed to prevent eavesdropping, tampering, or message forgery. Java SE 7 will have support for TLS 1.2, which was standardized in 2008 as RFC 5246
<br>
<br>
 Lead : <a href="http://blogs.sun.com/wetmore" target="_blank">Brad Wetmore</a>
<br>
 Specification : <a href="http://tools.ietf.org/search/rfc5246" target="_blank">rfc5246</a>
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#fa534339" target="_blank">fa534339</a>
<br>
<br><b>Elliptic-Curve Cryptography (ECC)</b>
<br>
 Elliptic curve cryptography (ECC) is an approach to public-key cryptography based on the algebraic structure of elliptic curves over finite fields. Java SE 7 will have a portable implementation of the standard Elliptic Curve Cryptographic (ECC) algorithms, so that all Java applications can use ECC out-of-the-box.
<br>
<br>
 Lead : Vincent Ryan
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#f73" target="_blank">f73</a>
<br>
<br><b>JDBC 4.1</b>
<br>
 Upgrade to JDBC 4.1 and Rowset 1.1
<br>
<br>
 Lead : <a href="http://www.java.net/blogs/lancea" target="_blank">Lance Andersen</a>
<br>
 JSR : <a href="http://jcp.org/en/jsr/detail?id=221" target="_blank">221</a>
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#fa539110" target="_blank">fa539110</a>
<br>
<br><b>Create new platform APIs for 6u10 graphics features</b>
<br>
 Create new platform APIs for features originally implemented in the 6u10 release: Translucent and shaped windows, heavyweight/lightweight mixing, and the improved AWT security warning.
<br>
<br>
 Lead : Anthony Petrov
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#f650" target="_blank">f650</a>
<br>
<br><b>Nimbus look-and-feel for Swing</b>
<br>
 Nimbus is the name of a look-and-feel designed by Sun for the Java Desktop System; it's implemented as a GTK theme in the latest Solaris 11 pre-release builds.
<br>
<br>
 Lead : <a href="" target="_blank">Jasper Potts</a>
<br>
 Project : <a href="" target="_blank">mibus</a>
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#f244" target="_blank">f244</a>
<br>
<br><b>Swing JLayer component</b>
<br>
 Add the SwingLabs JXLayer component decorator to the platform. With help of JXLayer you can easily decorate your compound components and catch all Mouse, Keyboard and FocusEvent for all its subcomponents.
<br>
 JXLayer is very friendly to your applications, it uses only public Swing API and doesn't change any global settings like RepaintManager, EventQueue or frame's glassPane. 
<br>
<br>
 Lead : <a href="http://weblogs.java.net/blog/alexfromsun" target="_blank">Alexander Potochkin</a>
<br>
 Project : <a href="" target="_blank">JXLayer project</a>
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#f652" target="_blank">f652</a>
<br>
<br><b>Updated XML Stack (JAXP, JAXB, &amp; JAX-WS)</b>
<br>
 Upgrade the JAXP, JAXB, and JAX-WS APIs to the most recent stable versions.
<br>
<br>
 Lead : Joe Wang
<br>
 JAXP JSR : <a href="http://jcp.org/en/jsr/detail?id=206" target="_blank">206</a>
<br>
 JAXP Project : <a href="" target="_blank">jaxp</a>
<br>
 JAXB JSR : <a href="http://jcp.org/en/jsr/detail?id=222" target="_blank">222</a>
<br>
 JAXB Project : <a href="" target="_blank">jaxb</a>
<br>
 JAX-WS JSR : <a href="http://jcp.org/en/jsr/detail?id=224" target="_blank">224</a>
<br>
 JAX-WS Project : <a href="" target="_blank">jax-ws</a>
<br>
 OpenJDK : <a href="http://openjdk.java.net/projects/jdk7/features/#f568" target="_blank">f568</a>
<br>
<br>
 Some items in the OpenJDK 7 feature list are not present because they concidered as speciﬁc to the JDK rather than general Java SE Platform. There will also be some enhancements to the HotSpot JVM. As Oracle stated during JavaOne2010 there will be a converged JVM which contains the best features from both HotSpot and JRockit. There are no further details available on this up to now.
<br>
 If you have links to further readings on any on the above topics, feel free to leave a comment and I will add them.
---
title: "Happy 8th Birthday Java!"
date: 2014-03-19 06:59:00 +0000
layout: post
tags: ["Java8"]
slug: "happy-8th-birthday-java"
link: "2014/03/happy-8th-birthday-java.html"
url: /2014/03/happy-8th-birthday-java.html
---

It's been another longer journey but yesterday, exactly two years, seven months, and eighteen days after the release of Java 7 we now have production-ready builds of <a href="http://www.oracle.com/technetwork/java/javase/downloads/index.html" target="_blank">Java 8 available for download</a>!&nbsp;This new major release contains several new features and enhancements that increase the performance of existing applications, make it easier to develop applications for modern platforms, and increase maintainability of code. Here is a brief overview about my personal highlights.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;" target="_blank"><img border="0" src="se8.PNG" height="229" width="320"></a>
</div><b>What's new overall?</b>
<br>
 Several areas of the language itself, profiles, security, JavaFX, tooling, scripting, Date and Time, concurrency and a more or less lengthy list of things changed or got added. More than 8.000 bugs and features have been addressed and be incorporated into this release. The complete list can be found in the <a href="http://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html" target="_blank">official release-notes</a>.
<br>
<br><b>Java Mission Control 5.3</b>
<br>
 One of the highlights is the new Java Mission Control release. It is bundled with the JDK and comes with a separate list of enhancements in it's <a href="http://www.oracle.com/technetwork/java/javase/jmc53-release-notes-2157171.html" target="_blank">own release-notes</a>. Find the <a href="" target="_blank">complete documentation</a> as part of the official Java SE 8 documentation.
<br>
<br><b>Compact Profiles</b>
<br>
 The newly introduced profiles enable reduced memory footprint for applications that do not require the entire Java platform. The javac compiler has a -profile option, which allows the application to be compiled using one of the <a href="http://docs.oracle.com/javase/8/docs/technotes/guides/compactprofiles/index.html" target="_blank">supported profiles</a>.&nbsp;The three are additive layers, so that each Profile contains all of the APIs in profiles smaller than itself. The compact profiles feature is useful in small devices with less hardware power.
<br>
 And yes, I am personally still totally frustrated that Jigsaw will probably never see the light. But I am happy to be proven wrong on that.
<br>
<br><b>Java Date-Time Packages</b>
<br>
 Thank you Stephen! Thank you for this great piece of work. <a href="http://docs.oracle.com/javase/8/docs/technotes/guides/datetime/index.html" target="_blank">TimesTen </a>is the new Date and Time API in Java 8. Clear, Fluent, Immutable, Extensible.&nbsp;There are two basic ways to represent time. One way represents time in human terms, referred to as human time, such as year, month, day, hour, minute and second. The other way, machine time, measures time continuously along a timeline from an origin, called the epoch, in nanosecond resolution. See the <a href="http://docs.oracle.com/javase/tutorial/datetime/overview/index.html" target="_blank">official tutorial</a> for more examples and usages.
<br>
<br><b>Good Bye PermGen!</b>
<br>
 Removal of PermGen. Full stop. It's gone! YES!
<br>
<br><b>Security Enhancements</b>
<br>
 More and stronger Algorithms, TLS 1.2 default and <a href="http://docs.oracle.com/javase/8/docs/technotes/guides/security/enhancements-8.html" target="_blank">further enhancements</a>.
<br>
<br><b>Nashorn</b>
<br>
 Java 8 is co-bundled with the <a href="" target="_blank">Nashorn</a>, which is an implementation of the ECMAScript Edition 5.1 Language Specification. See the <a href="http://docs.oracle.com/javase/8/docs/technotes/guides/scripting/nashorn/index.html" target="_blank">user-guide</a> for all the details.
<br>
<br><b>Happy 8th Birthday Java!</b>
<br>
 Congratulations to everybody involved. Especially the Adopt-OpenJDK community which was driven by the London-Java-Community and supported the Date-Time efforts by providing tests.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG_20140315_144923.jpg" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" src="IMG_20140315_144923.jpg" height="166" width="320"></a>
</div>
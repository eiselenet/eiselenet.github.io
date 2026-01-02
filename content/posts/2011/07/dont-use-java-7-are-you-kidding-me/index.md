---
title: "Don't Use Java 7? Are you kidding me?"
date: 2011-07-29 17:55:00 +0000
layout: post
tags: ["bug", "java7"]
slug: "dont-use-java-7-are-you-kidding-me"

url: /2011/07/dont-use-java-7-are-you-kidding-me.html
---

Java 7 was released yesterday and some guys from the Apache Lucene &amp; Apache Solr community quickly came up with a couple of issues which lead them to the point where they are actively rejecting Java 7 and advice anybody else to to likewise. Even a <a href="http://www.gossamer-threads.com/lists/lucene/java-user/131387">general warning</a> was issued&nbsp;by Apache Lucene PMC Member Uwe Schindler. But what exactly is wrong with Java 7 and why shouldn't you use it after waiting nearly five years for it? Let's look at this.
<br>
<br><b>It's not about Java 7 but about the JVM</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG-20110729-00016.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="320" src="IMG-20110729-00016.jpg" width="239"></a>
</div> First of all, it's not about Java 7 in general but about the HotSpot JVM. The GA release contains three bugs ( <a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7070134">7070134</a>, <a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7044738">7044738 </a>and <a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7068051">7068051</a>) which could affect the users with either JVM crashes or wrong calculations.
<br>
<br><b>Hotspot crashes with sigsegv from PorterStemmer</b>
<br><a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7070134">The first one</a> is about a wrong compiler optimization that changed the loop optimizations. The problem is, that this JVM feature is on by
<br>
 default, so you have to explicitly disable it by adding -XX:-UseLoopPredicate as an argument. If you are willing to try this by your own, grep the <a href="http://tartarus.org/~martin/PorterStemmer/java.txt">Stemmer.java</a>, a reasonable thick word database (there are some out there) and compile and run it against the text file. What you will see is, that your JVM crashes with a fatal error.
<br>
<br>
<pre># A fatal error has been detected by the Java Runtime Environment: # # EXCEPTION_ACCESS_VIOLATION (0xc0000005) at pc=0x00000000026536da, pid=5432, t id=6568 # # JRE version: 7.0-b135 # Java VM: Java HotSpot(TM) 64-Bit Server VM (21.0-b05 mixed mode windows-amd64 compressed oops) # Problematic frame: # J Stemmer.step4()V </pre>
<br>
 It directly happens during code execution, so you will not experience this with JDK 1.6. Especially Lucene has some more recent work going on using a more flexible indexing mechanism based on an algorithm
<br>
 called <a href="http://blog.mikemccandless.com/2010/06/lucenes-pulsingcodec-on-primary-key.html">PulsingCodec</a>&nbsp;especially this is heavily affected by the bug. 
<br>
<br><b>Loop unroll optimization causes incorrect result</b>
<br><a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7044738">This bug</a> refers to the "wrong calculations" part of my introductory section. In very rare situations when OSR (On-Stack Replacement)
<br>
 compilation is done for nested loops, the control flow breaks and the memory dependencies are not taken into account. That leads to duplicated clones which alter results. (If you like to know more about
<br>
 the compilation details, have a look at this <a href="http://draft.blogger.com/goog_1542805045">older overview</a>&nbsp;<a href="http://www.cs.princeton.edu/picasso/mats/HotspotOverview.pdf">(PDF)</a>
<br>
 A minimal workaround is to add -XX:LoopUnrollLimit=1 as an argument.
<br>
<br><b>Clone loop predicate during loop unswitch</b>
<br>
 This <a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7068051">is a bug</a> which relates to an older <a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7004535">feature request</a>. It's
<br>
 introduction finally lead to a new bug. Invalidated jvm stats lead to a jvm crash with loop optimizations again.
<br>
<br><b>Bottom Line</b>
<br>
 You could be affected. At the moment basically only if you have some parts in your software that make big use of the optimization methods which are broken. But for the average use cases this will not affect you. In general this also will affect Java 6 users but only if they use the optimization options, which are on by default with Java 7 (-XX:+OptimizeStringConcat or -XX:+AggressiveOpts) These problems were detected only 5 days before the official Java 7 release, so Oracle had no time to fix those bugs. At the moment it seems as if they are trying to get this into either the next or the second service release. And last but not least, the source code is open so anyone stubborn enough to dig into it can make a fix.
<br>
<br>
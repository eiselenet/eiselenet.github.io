---
title: "Perf4J: Performance Analysis for Enterprise Java"
date: 2009-01-22 05:41:00 +0000
layout: post
tags: ["Java EE", "performance", "java", "enterprise", "Perf4J", "jee"]
slug: "perf4j-performance-analysis-for"

url: /2009/01/perf4j-performance-analysis-for.html
---

<a href="" target="_blank">Perf4J</a> was announced yesterday. It is a new open-source performance logging, monitoring and analysis library for enterprise Java applications. For developers who are familiar with common Java logging frameworks like log4j, the team likes to describe Perf4J with an analogy:
<br>
<br>
Perf4J is to System.currentTimeMillis() as log4J is to System.out.println()
<br>
<br>
Some highlights of Perf4J's functionality include:
<br>
<ul>
 <br>
 <li>A simple stop watch mechanism for succinct timing statements.</li>
 <br>
 <li>A command line tool for parsing log files that generates aggregated statistics and performance graphs.</li>
 <br>
 <li>Easy integration with the most common logging frameworks and facades: log4j, java.util.logging, Apache Commons Logging and SLF4J.</li>
 <br>
 <li>Custom log4j appenders to generate statistics and graphs in a running application.</li>
 <br>
 <li>The ability to expose performance statistics as JMX attributes, and to send notifications when statistics exceed specified thresholds.</li>
 <br>
 <li>A servlet for exposing performance graphs in a web application.</li>
 <br>
 <li>A Profiled annotation and a set of custom aspects that allow unobstrusive timing statements when coupled with an AOP framework such as AspectJ or Spring AOP.</li>
 <br>
</ul>
<br>
<br>
(Taken from the <a href="http://www.theserverside.com/news/thread.tss?thread_id=53149" target="_blank">original theserverside.com announcement</a>)
<br>
<br>
For more details have a look at the <a href="http://perf4j.codehaus.org/devguide.html" target="_blank">developer documentation</a>.
<br>
<br>
You can integrate Perf4J with maven. It is available from the codehaus repository (http://repository.codehaus.org/org/perf4j/perf4j/).
<br>
<br>
&lt;dependency&gt;
<br>
 &lt;groupId&gt;org.perf4j&lt;/groupId&gt;
<br>
 &lt;artifactId&gt;perf4j&lt;/artifactId&gt;
<br>
 &lt;version&gt;0.9.7&lt;/version&gt;
<br>
&lt;/dependency&gt;
<br>
<br>
This seems to be a good thing to start over a new article on :)
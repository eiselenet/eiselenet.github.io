---
title: "Optimize GlassFish Performance in a Production Environment"
date: 2009-10-26 11:40:00 +0000
layout: post
tags: ["glassfish", "performance", "tuning"]
slug: "optimize-glassfish-performance-in"
link: "2009/10/optimize-glassfish-performance-in.html"
url: /2009/10/optimize-glassfish-performance-in.html
---

I recently came across some projects, that in fact needed some performance tunings. Doing so, I was also looking for some best practices and white papers around.
<br>
 Found this one: <a href="http://www.scribd.com/doc/19454833/Optimize-Glass-Fish-Performance" target="_blank">Optimize GlassFish Performance in a Production Environment, Performance White Paper, February 2009</a> (Sun)
<br>
<br>
 It is designed for users requiring general guidance for initial tuning values in order to improve the application performance on the Sun GlassFish application server v2.
<br>
<br>
 This paper provides the Introductory Steps to achieving better performance for the Sun GlassFish application server v2 application with descriptions of the Eleven tuning parameters; General Recommendations, which can be used to optimize the performance of the application server; and Data from a benchmarking exercise that demonstrates the effectiveness of the tuning changes. 
<br>
<br><b>Contents</b>
<br><i>Tip 1: Java Version</i>
<br>
 Obious .. change to latest JVM if possible. 
<br>
<br><i>Tip 2: JVM Mode</i>
<br>
 GF runs with -client ootb. change to -server 
<br>
<br><i>Tip 3: Java Heap Size</i>
<br>
 Give your server, hat it needs. 
<br>
 By default, the GlassFish application server is set to 512 MB
<br>
 and the limit for a 32 bit JVM version- is approximately 3.5GB on Solaris, and 1.5
<br>
 GB on Windows without any operating system tweaks.
<br><a href="http://java.sun.com/performance/reference/whitepapers/tuning.html#section4.1.2." target="_blank">Further information is available in this JVM Tunin White Paper!</a>
<br>
<br><i>Tip 4: Tune Java Garbage Collection</i>
<br>
 A deeper understanding is necessary when tinkering with GC tuning options and it is strongly recommended to read <a href="http://java.sun.com/docs/hotspot/gc5.0/gc_tuning_5.html" target="_blank">Tuning Garbage Collection with the Java Virtual<br>
  Machine</a> for more details and GC strategies.
<br>
<br><i>Tip 5: HTTP acceptor threads </i>
<br>
 HTTP acceptor threads accept new incoming connections and schedule new
<br>
 requests for the existing connections. The default number of acceptor threads is one! It is recommended to have 1 thread per 1-4 core.
<br>
<br><i>Tip 6: HTTP request processing threads</i>
<br>
 This pool of threads retrieve and process incoming HTTP requests. The default
<br>
 number of request processing threads is 5 but a starting rule of thumb is to tune
<br>
 the number of HTTP request processing threads to the number of CPUs.
<br>
<br><i>Tip 7: Optimize Keep Alive subsystem</i>
<br>
 This subsystem prevents the server from being overloaded with connections. A
<br>
 waiting keep alive connection has completed processing the previous request,
<br>
 and is waiting for a new request to arrive on the same connection.
<br>
 Three factors describe the Keep-Alive behavior. Keep-Alive timeout, Keep-Alive max connections and thread-count. Performance tuning in this subsystem highly depends on your applications needs. 
<br>
<br><i>Tip 8: Optimize static content delivery</i>
<br>
 If your application contains static files, it is recommended to enable HTTP file
<br>
 cache to optimize performance. This should not be relevant for clusters running httpd with mod_proxy or similar in front.
<br>
<br><i>Tip 9: Use default-web.xml to disable dev features</i>
<br>
 The default-web.xml file defines features such as filters and security constraints
<br>
 that apply to all web applications. You should use it, to define some common constraints that are performance critic to all deployed applications. Most common two are this:
<br>
<br>
 &lt;init-param&gt;
<br>
 &lt;param-name&gt;development&lt;/param-name&gt;
<br>
 &lt;param-value&gt;false&lt;/param-value&gt;
<br>
 &lt;/init-param&gt;
<br>
 &lt;init-param&gt;
<br>
 &lt;param-name&gt;genStrAsCharArray&lt;/param-name&gt;
<br>
 &lt;param-value&gt;true&lt;/param-value&gt;
<br>
 &lt;/init-param&gt;
<br>
<br><i>Tip 10: JDBC Tuning</i>
<br>
 Tune the steady-pool-size and the max-pool-size, and set them to the same
<br>
 value. This will avoid unnecessary resizing of the pool. A general
<br>
 rule of thumb is to tune the value for max-pool-size and steady-pool-size to the
<br>
 same number of HTTP request processing threads.
<br>
<br><i>Tip 11: Disable Access Logging </i>
<br>
 To avoid unnecessary I/O activity, disable the access
<br>
 logging.
<br>
<br>
 Further readings:
<br><a href="http://docs.sun.com/app/docs/doc/819-3681" target="_blank">Sun Java System Application Server 9.1 Performance Tuning Guide</a>
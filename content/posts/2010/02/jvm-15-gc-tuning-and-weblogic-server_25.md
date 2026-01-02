---
title: "JVM 1.5 GC Tuning and WebLogic Server - Part II garbage collection algorithms"
date: 2010-02-25 07:54:00 +0000
layout: post
tags: ["jvm", "tuning", "garbage collection", "weblogic server"]
slug: "jvm-15-gc-tuning-and-weblogic-server_25"
link: "2010/02/jvm-15-gc-tuning-and-weblogic-server_25.html"
url: /2010/02/jvm-15-gc-tuning-and-weblogic-server_25.html
---

You now know a bit more about the <a href="http://blog.eisele.net/2010/02/jvm-15-gc-tuning-and-weblogic-server.html">heap sizes (part I)</a>. Next is to dive into the actuall garbage collection algorithms. 
<br>
<br><b>Choosing the garbage collection algorithm</b>
<br>
 The Java HotSpot JM (up to version 5) includes four garbage collectors. All the collectors are generational. Memory in the Java HotSpot virtual machine is organized into three generations: a young generation, an old generation, and a permanent generation. When the young generation fills up, a young generation collection (sometimes referred to as a minor collection) of just that generation is performed. When the old or permanent generation fills up, what is known as a full collection (sometimes referred to as a major collection) is typically done.
<br>
 Commonly, the young generation is collected first, because it is usually the most efficient way to remove garbage from the young generation. Then what is referred to as the old generation collection algorithm for a given collector is run on both the old and permanent generations.
<br>
<br><i>Serial Collector</i>
<br>
 With the serial collector, both young and old collections are done serially (using a single CPU), in a stop-the-world fashion. That is, application execution is halted while collection is taking place.
<br>
<br>
 In the J2SE 5.0 release, the serial collector is automatically chosen as the default garbage collector on machines that are not server-class machines. The VM does an autodetection on this depending on the processors and the physical memory. Or your could even explicitly define a server or client VM by using the 
<br><code>-server or<br>
  -client</code>
<br>
 The serial collector can be explicitly requested by using the 
<br><code>-XX:+UseSerialGC</code>
<br>
 command line option.
<br>
 As you can guess, this is NOT the right collector for high volume server side applications running on 64-bit linux with lot of heap. If you compare this behaviour to the problem that was described in the first part, I bet you now know about the cause for it. But looking at the Weblogic startup script makes it clear. This is not the garbage collection algorithm that is used. So, let's go on.
<br>
<br><i>Parallel/Throughput Collector</i>
<br>
 But what to do with bigger applications? It is simple: try the
<br>
 parallel collector. It is also known as the throughput collector and was developed in order to take advantage of available CPUs rather than leaving most of them idle while only one does garbage collection work.
<br>
 The parallel collector uses a parallel version of the young generation collection algorithm utilized by the serial collector. It is still a stop-the-world collector, but performing the young collection in parallel with many CPUs. The old generation garbage collection is still done using the same collection algorithm as the serial collector. And the behaviour is still the same. If you have bigger heaps this is probably also not the right collector for you. Applications that can benefit from the parallel collector are those that do not have pause time constraints, since infrequent, but potentially long, old generation collections will occur.
<br>
<br>
 The parallel collector is automatically chosen as the default garbage collector on
<br>
 -server class maschines. It can be explicitly requested with the following option: <code>-XX:+UseParallelGC</code>
<br>
<br><i>Parallel Compacting Collector</i>
<br>
 The parallel compacting collector was introduced in J2SE 5.0 update 6. It enhances the parallel collector with a new algorithm for old generation garbage collection.
<br>
 With the parallel compacting collector, the old and permanent generations are collected in a stop-theworld, but mostly parallel fashion with sliding compaction.
<br>
 As with the parallel collector, the parallel compacting collector is beneficial for applications that are run on machines with more CPUs. In addition, the parallel operation of old generation collections makes it more suitable for applications that have pause time constraints. You can even change the number of threads used for garbage collection via the following option:
<br><code>–XX:ParallelGCThreads=n</code>
<br>
 If you want to use this collector, you have to specify the following option:
<br><code>-XX:+UseParallelOldGC</code>
<br>
<br><i>Concurrent Mark-Sweep (CMS)/low-latency Collector</i>
<br>
 For many applications, end-to-end throughput is not as important as fast response time. Young generation collections do not typically cause long pauses. However, old generation collections, though infrequent, can impose long pauses, especially when large heaps are involved. To address this issue, the HotSpot JVM includes a collector called the concurrent mark-sweep (CMS) collector.
<br>
 The CMS collector collects the young generation in the same manner as the parallel collector. Most of the collection of the old generation using the CMS collector is done concurrently with the execution of the application. The CMS collector is the only collector that is non-compacting. That is, after it frees the space that was occupied by dead objects, it does not move the live objects to one end of the old generation. Another disadvantage the CMS collector has is a requirement for larger heap sizes than the other collectors. Unlike the other collectors, the CMS collector does not start an old generation collection when the old generation becomes full. Instead, it attempts to start a collection early enough so that it can complete before that happens. Otherwise, the CMS collector reverts to the more time-consuming stop-the-world algorithm used by the parallel and serial collectors. To avoid this, the CMS collector starts at a time based on statistics regarding previous collection times and how quickly the old generation becomes occupied. It will also start a collection if the occupancy of the old generation exceeds something called the initiating occupancy. The value of the initiating occupancy is set by the option
<br><code>–XX:CMSInitiatingOccupancyFraction=n</code>
<br>
 n is the percentage of the old generation size. Defaulting to 68.
<br>
<br>
 Compared to the parallel collector, the CMS collector decreases old generation pauses at the expense of longer young generation pauses, some reduction in throughput, and extra heap size requirements. Use the CMS collector if your application needs shorter garbage collection pauses and can afford to share processor resources with the garbage collector when the application is running. Applications with a relatively large set of long-lived data running on multi CPU maschines tend to benefit from the CMS collector.
<br>
<br>
 If you want the CMS collector to be used, you must explicitly select it by specifying the option
<br><code>-XX:+UseConcMarkSweepGC</code>
<br>
 An incremental mode can be enabled via 
<br><code>–XX:+CMSIncrementalMode</code>
<br>
<br><b>Further options</b>
<br>
 Now you basically know everything about the four garbage collection algorithms. To be honest, there are plenty of additional options and tuning methods implemented in the JVM. You can for example tune the behaviour of the parallel collectors to fit definied goals (pause time, throughput or footprint) or you can even enable special tuning parameters for defined operating systems. A complete list of <a href="http://java.sun.com/javase/technologies/hotspot/vmoptions.jsp" target="_blank">Java VM options</a> is available online.
<br>
 From now on, you are going to explore your system from a runtime view. There is one basic principle that should guide you:
<br><b>"Premature optimization is the root of all evil." (Donald Knuth)</b>
<br>
 There is only one cause for switching or tuning the JVM GC in general. You are running into problems. In common cases this is a "java.lang.OutOfMemoryError" exception. But also very long gc pause times in a stop-the-world fashion could force you to get your hands on it.
<br>
<br><b>More tools</b>
<br>
 I allready pointed you to some diagnostic and monitoring tools in the first part. But there are more available. If you are on unix/linux based systems, you can take advantage of <a href="http://java.sun.com/j2se/1.5.0/docs/tooldocs/share/jmap.html" target="_blank">jmap </a>and <a href="http://java.sun.com/j2se/1.5.0/docs/tooldocs/share/jstat.html" target="_blank">jstat</a>. Both provide in depth details about your running JVMs and can assist you in digging deeper into the problem. <a href="http://java.sun.com/developer/technicalArticles/Programming/HPROF.html" target="_blank">HPROF</a> and <a href="" target="_blank">HAT</a> can also assist you.
<br>
<br>
 If you look around for further documents and best practices, you will notice a lot of stuff out there. The best place to start are the following two documents. Choose the one, fitting your JVM version:
<br>
 - <a href="http://java.sun.com/docs/hotspot/gc5.0/gc_tuning_5.html">Tuning Garbage Collection with the 5.0 Java[tm] Virtual Machine</a>
<br>
 - <a href="http://java.sun.com/docs/hotspot/gc5.0/gc_tuning_5.html">Java SE 6 HotSpot[tm] Virtual Machine Garbage Collection Tuning </a>
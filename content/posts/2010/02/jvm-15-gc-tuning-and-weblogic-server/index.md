---
title: "JVM 1.5 GC Tuning and WebLogic Server - Part I Heap"
date: 2010-02-24 05:28:00 +0000
layout: post
tags: ["jvm", "tuning", "garbage collection", "weblogic server"]
slug: "jvm-15-gc-tuning-and-weblogic-server"

url: /2010/02/jvm-15-gc-tuning-and-weblogic-server.html
---

The Java SE is used for a wide variety of applications. Beginning with small applets on desktops to web services or Java EE applications on large servers. In support of this diverse range of deployments, the HotSpot virtual machine implementation provides multiple garbage collectors, each designed to satisfy different requirements. However, users, developers and administrators that need high performance are burdened with the extra step of selecting the garbage collector that best meets their needs. Because the standard is not always the best choice. This is all about tuning and configuring the JVM to fit your needs. What to do and where to look. If you read on, I assume that you have a basic understanding of the memory management in the jvm. If not, you can read more about it in the <a href="http://java.sun.com/javase/technologies/hotspot/gc/memorymanagement_whitepaper.pdf" target="_blank">Memory Management in the Java HotSpot Virtual Machine whitepaper (PDF)</a>.
<br>
 Because this is a slightly complex area, I sliced this article into two parts. This is part one. All about sizing the heap. 
<br>
<br>
 All the above is especially true for Java 1.5 (they are still out there ;)) and for very large systems with a lot of hits/second. We recently came across a nice system. 16 cores, 32 GB of RAM. All running on a 64-bit Linux. It should have been a sufficient box for our small WebLogic Server (10.0) running on top of it. But starting with a lot of heap and the default -server jvm in place showed some very nasty effects. Everything went smooth up to a certain point, where the JVM started with full garbage collection. The single run took up to 69.6645960 secs. Wow. Disappointing. What happend? What next?
<br>
<br>
 First step is to understand what happens. Find out everything you need. Have a look at my previous post about <a href="http://blog.eisele.net/2010/02/application-server-unresponsive-or.html">Application Server unresponsive or stuck? Take a deeper look!</a>. Gather every information necessary. If nothing leads you into the opposite direction, have a deeper look at the JVM.
<br>
 There are some tools out there. Let's start with the JConsole. You enable it by adding the following parameters to your JVM:
<br>
<br><code><br>
  -Dcom.sun.management.jmxremote.port=50050 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false <br></code>
<br>
<br>
 Now you can have a more detailed look at what's happening.
<br><img border="0" src="fullgc.png">
<br>
 If you are looking at the above example, you can see the system doing a stop-the-world garbage collection ("Full Garbage Collection"). In this case, it freed about 3GB heap and took nearly 30 seconds. If you do not have any "fancy" tools at hand, you probably need this small java option:
<br><code><br>
  -verbose:gc<br></code>
<br>
 It prints every action of the garbage collector out to the console. This should be enough for analysis, even if you do not have such a nice graphic.
<br><code><br>
  [GC 325407K-&gt;83000K(776768K), 0.2300771 secs]<br>
  [Full GC 267628K-&gt;83769K(776768K), 1.8479984 secs]<br></code>
<br>
<br>
 In this example, you see two minor collections and one major one. The numbers before and after the arrow indicate the combined size of live objects before and after garbage collection. The number in parenthesis is the total available space, not counting the space in the permanent generation, which is the total heap minus one of the survivor spaces. The minor collection took about a quarter of a second.
<br>
 You need more information
<br><code><br>
  -XX:+PrintGCDetails <br></code>
<br>
 The flag prints additional information about the collections. An example of the output for the J2SE Platform version 1.5 using the serial garbage collector:
<br><code><br>
  [GC [DefNew: 64575K-&gt;959K(64576K), 0.0457646 secs] 196016K-&gt;133633K(261184K), 0.0459067 secs]]<br></code>
<br>
 At each garbage collection the virtual machine chooses a threshold number of times an object can be copied before it is tenured. This threshold is chosen to keep the survivors half full. It is helpful to show this threshold and the ages of objects in the new generation. Enable it using the following option:
<br><code><br>
  -XX:+PrintTenuringDistribution<br></code>
<br>
 The output looks like this:
<br><code><br>
  Desired survivor size 1015808 bytes, new threshold 1 (max 15)<br>
  - age 1: 2031616 bytes, 2031616 total<br></code>
<br>
<br>
 What next? Can someone have done any mistake in here? If you are concerned that your application might have hidden calls to System.gc() buried in libraries, you should invoke the JVM with the 
<br><code><br>
  -XX:+DisableExplicitGC<br></code>
<br>
 option to prevent calls to System.gc() and triggering a garbage collection. If this does not change a bit, move on.
<br>
<br>
 All you have to do now is to change the generation sizes and choose the correct garbage collection algorithm. Of course, you could also think about moving to a different JVM first. Beside Hotspot you also can look at JRockit. In my case, I will stick to Hotspot.
<br>
<br><b>Choosing the Heap Size</b>
<br>
 The JVM heap size determines how often and how long the VM spends collecting garbage. An acceptable rate for garbage collection is application-specific. If you set a large heap size, full garbage collection is slower, but it occurs less frequently. If you set your heap size in accordance with your memory needs, full garbage collection is faster, but occurs more frequently. The goal of tuning your heap size is to minimize the time that your JVM spends doing garbage collection while maximizing the number of clients that your application can handle at a given time. 
<br>
 As a rule of thumb, you should have 4GB per WebLogic instance running on a 64-bin OS. You set this with the following options. Set them to a multiple of 1024 that is greater than 1MB. Setting min and max values to the same saves some time for extending the heap during runtime. 
<br><code><br>
  -Xms 4096 <br>
  -Xmx 4096 <br></code>
<br>
<br>
 You should adjust the size of the young generation to be one-fourth the size of the maximum heap size. Again this is a multiple of 1024 that is greater than 1MB. 
<br>
 Increasing the young generation becomes counterproductive at half the total heap or less (whenever the young generation guarantee cannot be met). 
<br><code><br>
  -XX:NewSize 1024<br>
  -XX:MaxNewSize 1024<br></code>
<br>
 The New generation area is divided into three sub-areas: Eden, and two survivor spaces that are equal in size. Configure the ratio of the Eden/survivor space size with the following option.
<br><code><br>
  -XX:SurvivorRatio=8 <br></code>
<br>
<br>
 Part II will cover the garbage collection algorithms which best fit your needs. Stay tuned.
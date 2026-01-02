---
title: "Java SE 6 release 21, the Java Hotspot VM 17.0 and G1"
date: 2010-07-09 05:04:00 +0000
layout: post
tags: ["\"Java SE 6 release 21\"", "G1", "GC", "jvm 17"]
slug: "java-se-6-release-21-java-hotspot-vm"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2010/07/java-se-6-release-21-java-hotspot-vm.html
---

Oracle released the <a href="http://java.sun.com/javase/6/webnotes/6u21.html" target="_blank">Java SE 6 (1.6.0_21)</a> build a few days ago. Unexpected this presents a bunch of new features and updates. Here are the major ones in short and a more detailed look at the also included Java Hotspot VM 17.0.
<br>
<br><b>Supported Systems</b>
<br>
 Beside additional framework support for Oracle Enterprise Linux (4.8 -5.5), Red Hat Enterprise Linux (5.4, 5.5) and the Oracle VM 2.2.0.0.0 this one also supports the 4.0 branch of Google's Chrome. 
<br>
 Look at thecomplete <a href="http://java.sun.com/javase/6/webnotes/install/system-configurations.html" target="_blank">list of all supported system configurations</a> for more details.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="CustomProgressIndicator_Webstart.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" src="CustomProgressIndicator_Webstart.jpg"></a>
</div><b>Customized Loading Progress Indicators</b>
<br>
 You can now enhance the loading experience of an application by providing a customized loading progress indicator/progress bar. You can replace the default loading progress indicator with a custom implementation that provides regular feedback about how the RIA is loading. To do this, you have to create a class that implements the <a href="http://download.oracle.com/docs/cd/E17409_01/javase/7/docs/jre/api/javaws/jnlp/javax/jnlp/DownloadServiceListener.html" target="_blank">DownloadServiceListener </a>interface. Create and update the progress indicator in the following methods based on the overallPercent argument. These methods are invoked regularly by the Java Plug-in software to communicate progress of the applet's download. Java Plug-in software will always send a message when download and validation of resources is 100% complete. 
<br><code><br>
  public void progress(URL url, String version, long readSoFar,<br>
  long total, int overallPercent) \{ ... \}<br><br>
  public void upgradingArchive(java.net.URL url,<br>
  java.lang.String version,<br>
  int patchPercent,<br>
  int overallPercent) \{ ... \}<br><br>
  public void validating(java.net.URL url,<br>
  java.lang.String version,<br>
  long entry,<br>
  long total,<br>
  int overallPercent) \{ ...\}<br></code>
<br>
 For mor details look at the new <a href="http://download.oracle.com/docs/cd/E17409_01/javase/tutorial/deployment/doingMoreWithRIA/customizeRIALoadingExperience.html" target="_blank">Customizing the Loading Experience tutorial</a>. 
<br>
<br><b>Java VisualVM Updates</b>
<br>
 Now Java VisualVM based on VisualVM 1.2.2 is included. It introduces all related features. Look at the <a href="https://visualvm.dev.java.net/relnotes.html#changes" target="_blank">release notes</a> for a list of bugfixes and new features.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="bugfixes.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="320" src="bugfixes.png" width="231"></a>
</div><b>268 bugfixes</b>
<br>
 Java SE 6 Update 21 does not contain any additional fixes for security vulnerabilities compared toUpdate 20. So there is basically no need to update. Beside this, there are 268 issues fixed with the new update. Most of them related to "hotspot" category the "runtime system" and the garbage collector. For details please refer to the <a href="http://java.sun.com/javase/6/webnotes/BugFixes6u21.html">complete list of bugfixes</a>. 
<br>
<br><b>Java Hotspot VM 17.0</b>
<br>
 The new JVM includes version 17.0 of the Java HotSpot Virtual Machine. This has overall improvements to quality and features such as compressed object pointers, escape analysis-based optimization, code cache management. The most interesting part is the successor of the Concurrent Mark-Sweep garbage, the Garbage First (G1) garbage collector (GC). The G1 is a new GC that is being introduced in the Java HotSpot VM in JDK 7. An first experimental version of G1 has already been released in Java SE 6 Update 14. VM 17.0 now again contains an improved version.
<br>
<br>
 G1 is a "server-style" GC and has the following attributes.
<br><i>Parallelism and Concurrency.</i> G1 takes advantage of the parallelism that exists in hardware today. It uses all available CPUs (cores, hardware threads, etc.) to speed up its "stop-the-world" GC pauses. It also works concurrently with running Java threads.
<br>
<br><i>Generational.</i> Like the other HotSpot GC's, G1 is generational, meaning it treats newly-allocated (aka young) objects and objects that have lived for some time (aka old) differently. But there is no physical separation between the young and old generations. Instead, there is a single contiguous heap which is split into same-sized regions. The young generation is a set of potentially non-contiguous regions, and the same is true for the old generation. This allows G1 to flexibly move resources as needed from the old to the young generation, and vice versa.
<br>
<br><i>Compaction.</i> Unlike CMS, G1 performs heap compaction over time. Compaction eliminates potential fragmentation problems to ensure consistent long-running operation.
<br>
<br><i>Predictability.</i> G1 is expected to be more predictable than CMS. This is largely due to the elimination of fragmentation issues that can negatively affect stop-the-world pause times in CMS. Additionally, G1 has a pause prediction model that, in many situations, allows it to often meet (or rarely exceed) a pause time target.
<br>
<br>
 G1 is still considered experimental and is available in Early Access only. It can be enabled with the following two parameters:
<br><code><br>
  -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC<br></code>
<br>
 To set a GC pause time goal:
<br><code><br>
  -XX:MaxGCPauseMillis =50 (for a pause time target of 50ms)<br></code>
<br>
 With G1, a time interval can be specified during which a GC pause should last no longer than the time given above:
<br><code><br>
  -XX:GCPauseIntervalMillis =200 (for a pause interval target of 200ms)<br></code>
<br>
 The above two options represent goals. They are not promises or guarantees. They might work well in some situations but not in others.
<br>
<br>
 Alternatively, the size of the young generation can be specified:
<br><code><br>
  -XX:+G1YoungGenSize=512m (for a 512 megabyte young generation)<br></code>
<br>
 There are also comparable survivor spaces, which are, a set of (potentially non-contiguous) regions. Their size can be specified with the usual parameters (e.g., <code>-XX:SurvivorRatio=6</code>).
<br>
<br>
 To run G1 at its full potential use these two parameters. They are currently disabled by default because they may uncover a rare race condition:
<br><code><br>
  -XX:+G1ParallelRSetUpdatingEnabled -XX:+G1ParallelRSetScanningEnabled<br></code>
<br>
 My tests with the latest 1.6.0_21 showed, that both parameters are not working. If you are willing to test them, switch back to 1.6.0_20!
<br>
<br>
 One more thing to note is that G1 is very verbose compared to other HotSpot GCs when <code>-XX:+PrintGCDetails</code> is set. This is because it prints per-GC-thread timings and other information very helpful in profiling and trouble-shooting. If you want a more concise GC log, please switch to using <code>-verbosegc</code>.
<br>
 If you try this with WebLogic Server 11g you get a first impression what this means:
<br>
<pre>&lt;08.07.2010 18:45 Uhr MESZ&gt; &lt;Info&gt; &lt;WebLogicServer&gt; &lt;BEA-000377&gt; &lt;Starting WebLogic Server with Java HotSpot(TM) Server VM Version 17.0-b16 from Sun Microsystems Inc.&gt; [GC pause (young), 0.05034187 secs] [Parallel Time: 47.8 ms] [Update RS (Start) (ms): 83057.1 83058.3] [Update RS (ms): 1.0 0.0 Avg: 0.5, Min: 0.0, Max: 1.0] [Processed Buffers : 15 0 Sum: 15, Avg: 7, Min: 0, Max: 15] [Ext Root Scanning (ms): 35.6 33.4 Avg: 34.5, Min: 33.4, Max: 35.6] [Mark Stack Scanning (ms): 0.0 0.0 Avg: 0.0, Min: 0.0, Max: 0.0] [Scan-Only Scanning (ms): 0.0 0.0 Avg: 0.0, Min: 0.0, Max: 0.0] [Scan-Only Regions : 0 0 Sum: 0, Avg: 0, Min: 0, Max: 0] [Scan RS (ms): 0.3 0.0 Avg: 0.2, Min: 0.0, Max: 0.3] [Object Copy (ms): 10.8 9.9 Avg: 10.3, Min: 9.9, Max: 10.8] [Termination (ms): 0.0 0.0 Avg: 0.0, Min: 0.0, Max: 0.0] [Other: 2.3 ms] [Clear CT: 0.1 ms] [Other: 2.4 ms] [ 200M-&gt;26M(256M)] [Times: user=0.08 sys=0.03, real=0.06 secs] [...] [Full GC (System.gc()) 65M-&gt;27M(256M), 0.4923866 secs] [Times: user=0.53 sys=0.00, real=0.50 secs] </pre>See the <a href="http://java.sun.com/javase/technologies/hotspot/gc/g1_intro.jsp" target="_blank">G1 technology page</a> for more information.
<br>
 There has been a JavaOne session about the G1 in 2008. Find some <a href="http://developers.sun.com/learning/javaoneonline/j1sessn.jsp?sessn=TS-5419&amp;yr=2008" target="_blank">slides and more on this page</a>.
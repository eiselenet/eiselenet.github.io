---
title: "Running GlassFish with JRockit"
date: 2011-01-30 19:30:00 +0000
layout: post
tags: ["glassfish", "JRockit"]
slug: "running-glassfish-with-jrockit"
link: "2011/01/running-glassfish-with-jrockit.html"
url: /2011/01/running-glassfish-with-jrockit.html
---

Since GlassFish 3.0.1 the JDK support was extended. You now also could run GlassFish with Oracle JRockit 6 Update 17 R28.0.0+. This is a great choice in general and I must admit, that this announcement slipped through and missed my attention until today. 
<br>
<br><b>Getting started</b>
<br>
 Get the latest <a href="http://glassfish.java.net/downloads/3.0.1-final.html" target="_blank">GlassFish 3.0.1</a> and the latest <a href="http://www.oracle.com/technetwork/middleware/jrockit/downloads/index.html" target="_blank">Oracle JRockit 28.1</a>. Start with installing the JRockit to a suitable location. After that, start the GlassFish installer and do the same. Select the JRockit installation folder if you are asked to select the JDK. Finish the GlassFish installation. and change to <code>%GF_HOME$/bin</code> and type <code>asadmin start-domain</code>.
<br>
 You will see something like this:
<br>
<pre>Waiting for DAS to start .Error starting domain: domain1. The server exited prematurely with exit code 1. Before it died, it produced the following output: [WARN ][jrockit] MaxPermSize=192m ignored: Not a valid option for JRockit [WARN ][jrockit] NewRatio=2 ignored: Not a valid option for JRockit Could not create the Java virtual machine. Unknown option or illegal argument: -XX:+LogVMOutput. Please check for incorrect spelling or review documentation of startup options. </pre>
<br><b>Configure your Domain</b>
<br>
 Both JVM options are invalid for JRockit. So you have to get a hand on your <code>%GF_HOME%/glassfish/domains/domain1/config/domain.xml</code> and comment out or remove the following three lines in the &lt;java-config&gt; section:
<br><code><br>
  &lt;jvm-options&gt;-XX:+LogVMOutput&lt;/jvm-options&gt;<br>
  &lt;jvm-options&gt;-XX:MaxPermSize=192m&lt;/jvm-options&gt; <br>
  &lt;jvm-options&gt;-XX:LogFile=$\{com.sun.aas.instanceRoot\}/logs/jvm.log&lt;/jvm-options&gt;</code> After that try restarting your domain and you will see, it works.
<br>
 If you run the admin console and select "Enterprise Server &gt; JVM Report" you will see, that the Oracle JRockit is now used.
<br>
 This seems to be a <a href="http://java.net/jira/browse/GLASSFISH-11986" target="_blank">know issue and will be fixed</a> in the next GlassFish 3.1 release.
<br>
<br><b>Why JRockit?</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="jmrc_gf.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="146" src="jmrc_gf.png" width="200"></a>
</div>There are a couple of reasons. <a href="http://blog.eisele.net/2010/11/review-oracle-jrockit-definitive-guide.html">It's simple the most powerful JVM on earth.</a> But my favorite is the Mission Control Tooling. includes tools to monitor, manage, profile, and eliminate memory leaks in your Java application. You can simply browse any resources on the server and have a very handy tooling for identifying problems. The most powerful thing is the Flight Recording feature. You can monitor your JVM like a blackbox and collect runtime information with minimal overhead. The flight recording option is enabled by default. You simple need to take your probe for as long as you need it and analyze it with the JRMC.
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="flight_recording_gf.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="120" src="flight_recording_gf.png" width="200"></a>
</div>JRockit Flight Recorder does all this by being tightly integrated into the JVM and by being very conscious of its performance overhead. It provides a wealth of information on the inner workings of the JVM as well as on the Java program running in the JVM. You can use this information for profiling and for root cause analysis of problems. It can be enabled at all times, without causing performance overhead—even in heavily loaded, live production environments.
<br>
 If you are looking for more details also compare my <a href="http://blog.eisele.net/2010/07/jrockit-mission-control-suite-and.html">Flight Recorder</a> post. There are some more experimental features available which you could install by simply selecting "Help &gt; Install Plugins". A WebLogic plugin is available. I would love to have a separate for GlassFish also.
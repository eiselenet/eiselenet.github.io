---
title: "Getting Started With WildFly 10 - Fast, Lightweight and Powerful Java EE 7"
date: 2015-11-02 13:42:00 +0000
layout: post
tags: ["javaee7", "wildfly", "Jboss"]
slug: "getting-started-with-jboss-wildfly-10"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2015/11/getting-started-with-jboss-wildfly-10.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;" target="_blank"><img alt="Get Latest And Greatest WildFly 10" border="0" height="166" src="download_wildfly_10.jpg" title="Download WildFly 10" width="320"></a>
</div> The latest version of the WildFly Application Server has been released. The version 10 marks a milestone, as many individual pieces have been tweaked and upgraded to the latest implementations.
<br>
<br><b>What Is New?</b>
<br>
 A lot of improvements are available. Beside nearly 900 bug fixes and feature implementations, the biggest new topics features in the release can be summarized as follows:
<br>
<ul>
 <li>Java 8 is now required as a runtime. While Java 9 is still in development, WildFly 10 runs on the current development snapshots.</li>
 <li>WildFly 10 includes the ActiveMQ Artemis project as its JMS broker, and due to the protocol compatibility, it fully replaces the HornetQ project.</li>
 <li>In addition to the offline CLI support (since WildFly 9) for standalone mode, you can now launch a host-controller locally within the CLI.&nbsp;</li>
 <li>The Admin Console has been revamped and has a new look and feel.</li>
 <li>WildFly 10 includes the Undertow JS project, which allows you to write server side scripts that can pull in CDI beans and JPA Entity Beans. Learn more in this blog-post by Stuard Douglas.</li>
 <li>WildFly 10 adds the ability to deploy a given application as a "singleton deployment" with automatic failover to another node in case of failure.</li>
 <li>&nbsp;HA Singleton MDBs and MDB Delivery Groups.</li>
 <li>WildFly now pools stateless session beans by default, using a pool size that is computed relative to the size of the IO worker pool, which is itself auto-tuned to match system resources.&nbsp;</li>
 <li>Migration Operations for old subsystems such as jbossweb (AS 7.1), jacorb (WildFly 8), and hornetq (WildFly 9)</li>
 <li>Hibernate 5 included</li>
</ul><b>Getting Started</b>
<br>
 WildFly 10 provides single distributions available in zip or tar file formats. &nbsp;The main ones are the Java EE 7 Web &amp; Full Profile distributions and the Servlet-Only Distribution. You can download the one you want to work with from&nbsp;<a href="" rel="nofollow" target="_blank">http://wildfly.org/downloads/</a>. If you are unsure, just get started with the Java EE 7 Web Profile. Simply extract your chosen download to the directory of your choice which will be called $JBOSS_HOME.
<br>
 To start WildFly 10 using the default web profile configuration in "standalone" mode, change directory to $JBOSS_HOME/bin.
<br>
<br>
 ./standalone.sh|bat|ps1
<br>
<br>
 Note: The directory contains additional ps1 (Powershell) files for Windows. Those might replace the well known .bat files in upcoming versions.
<br>
<br>
 After executing one of the above commands, you should see output similar to what's shown below:
<br>
<br>
 ===============================================================================
<br>
<br>
 &nbsp; JBoss Bootstrap Environment
<br>
<br>
 &nbsp; JBOSS_HOME: "D:\wildfly-10.0.0.CR4"
<br>
<br>
 &nbsp; JAVA: "D:\jdk1.8.0_60"\bin\java"
<br>
<br>
 &nbsp; JAVA_OPTS: "-Dprogram.name=standalone.bat -Xms64M -Xmx512M -Djava.net.preferIPv4Stack=true -Djboss.modules.system.pkgs=org.jboss.byteman"
<br>
<br>
 ===============================================================================
<br>
<br>
 14:33:54,890 INFO &nbsp;[org.jboss.modules] (main) JBoss Modules version 1.4.4.Final
<br>
 14:33:56,786 INFO &nbsp;[org.jboss.msc] (main) JBoss MSC version 1.2.6.Final
<br>
 14:33:56,842 INFO &nbsp;[org.jboss.as] (MSC service thread 1-6) WFLYSRV0049: WildFly Full 10.0.0.CR4 (WildFly Core 2.0.0.CR8) starting
<br>
<br>
 As with previous releases, you can point your browser to <a href="" rel="nofollow" target="_blank">http://localhost:8080</a> and see the welcome screen:
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="wildfly10_welcome.jpg" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img alt="Welcome to WildFly 10 - Your WildFly is Running" border="0" height="363" src="wildfly10_welcome.jpg" title="WildFly 10 Welcome Screen" width="400"></a>
</div>
<br>
 By default WildFly is distributed with security enabled for the management interfaces, this means that before you connect using the administration console or remotely using the CLI you will need to add a new user, this can be achieved simply by using the add-user.sh|bat|ps1 script in the $JBOSS_HOME/bin folder.
<br>
<br>
<br><b>WildFly Is The Technology Backbone For EAP&nbsp;</b>
<br>
 While <a href="https://www.redhat.com/en/technologies/jboss-middleware/application-platform" target="_blank">Red Hat JBoss Enterprise Application Platform</a> retains all of the innovation of the Wildlfy community project, only a subscription to JBoss Enterprise Application Platform meets the demanding requirements for mission-critical applications, including the assurance of SLA-based support, patches, updates, and multi-year maintenance policies. &nbsp;WildFly constantly pushing forward in terms of new features and cutting edge innovation, and JBoss EAP following with a focus on enterprise level performance and stability, long term maintenance and first class professional support. As
<br>
<br>
 Further Readings And How To Contribute
<br>
<ul>
 <li><a href="https://docs.jboss.org/author/display/WFLY10/Documentation" target="_blank">WildFly 10 Documentation</a></li>
 <li><a href="https://lists.jboss.org/mailman/listinfo/wildfly-dev" target="_blank">Subscribe to wildfly-dev mailinglist</a> and participate in the discussion</li>
 <li>Ask your <a href="https://community.jboss.org/en/wildfly" target="_blank">questions on WildFly</a> forum</li>
 <li>Follow us on Twitter at <a href="https://www.twitter.com/WildFlyAS" target="_blank">@WildFlyAS</a></li>
</ul>
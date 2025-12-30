---
title: "WildFly 10 CR 2 Released - Java EE 7, Java 8, Hibernate 5, JavaScript Support with Hot Reloading"
date: 2015-09-25 06:20:00 +0000
layout: post
tags: ["javaee7", "wildfly", "EAP7"]
slug: "2015-09-25-wildfly-10-cr-2-released-java-ee-7-java"
url: /2015/09/wildfly-10-cr-2-released-java-ee-7-java.html
---

Yesterday the WildFly team released the <a href="" target="_blank">latest version of WildFly 10</a>. The CR2 will most likely be the last before the final release which is expected in October. Many new features made it into this release, even if the mainly supported Java EE specification is 7 as with WildFly 8 and WildFly 9 which now makes three server versions, which implement the Java EE 7 Full and Web Profile standards. Ultimately WildFly 10 will lead to <a href="" target="_blank">Red Hat JBoss Enterprise Application Platform</a> (EAP) 7, the supported Java EE offering of Red Hat.
<br>
 Learn more about JBoss EAP 7 in the <a href="https://videos.cdn.redhat.com/summit2015/presentations/12186_red-hat-jboss-enterprise-application-platform-7-roadmap-new-features.pdf" target="_blank">Summit presentation (PDF)</a> by Bilge Ozpeynirci &nbsp;(Sr. Product Manager) and Dimitris Andreadis (Sr. Engineering Manag)
<br>
<br><b>New Features At A Glance</b>
<br>
<ul>
 <li>Java 7 support has been discontinued allowing for deeper integration with the Java 8 runtime. While Java 9 is still in development, this release runs on the current development snapshots.</li>
 <li>&nbsp;WildFly 10 CR2 includes the ActiveMQ Artemis project as its JMS broker, and due to the protocol compatibility, it fully replaces the HornetQ project.</li>
 <li>In addition to the offline CLI support (WildFly 9) for standalone mode, you can now launch a host-controller locally within the CLI.&nbsp;</li>
 <li>WildFly 10 includes the Undertow JS project, which allows you to write server side scripts that can pull in CDI beans and JPA Entity Beans. Learn more in this <a href="" target="_blank">blog-post by Stuard Douglas</a>.</li>
 <li>WildFly 10 adds the ability to deploy a given application as a "singleton deployment" with automatic failover to another node in case of failure.</li>
 <li>&nbsp;HA Singleton MDBs and MDB Delivery Groups.</li>
 <li>WildFly now pools stateless session beans by default, using a pool size that is computed relative to the size of the IO worker pool, which is itself auto-tuned to match system resources.&nbsp;</li>
 <li>Migration Operations for&nbsp;old subsystems such as jbossweb (AS 7.1), jacorb (WildFly 8), and hornetq (WildFly 9)</li>
 <li>Hibernate 5 included</li>
</ul><b>Getting Started</b>
<br>
 Download <a href="" target="_blank">WildFly CR2 from the wildfly.org</a> download site. Unpack into a folder of your choice and unzip the distribution. Change to the bin directory and type:
<br>
<pre class="code"><code>$ standalone.sh|bat</code></pre> Which will start WildFly lightning fast:
<br>
<pre class="code"><code>08:09:58,353 INFO &nbsp;[org.jboss.as] (Controller Boot Thread) Full 10.0.0.CR2 (WildFly Core 2.0.0.CR5) started in 3686ms</code></pre> Access the main page with your browser at http://localhost:8080 and see the new admin console at http://localhost:9990
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="wf-admin-console.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="201" src="wf-admin-console.PNG" width="320"></a>
</div>
<br>
 Please give it a try with all your latest projects and let the team know, what you need or missing. Reach out to them via:
<br>
<ul>
 <li>The <a href="" target="_blank">Developer Mailinglist</a></li>
 <li>The <a href="https://www.hipchat.com/gW90m6pIs" target="_blank">HipChat Channel</a></li>
 <li>The <a href="https://community.jboss.org/en/wildfly?view=discussions" target="_blank">User Forums on JBoss.org</a></li>
 <li>Or via Twitter <a href="https://twitter.com/wildflyas" target="_blank">@WildFlyAS</a></li>
</ul>
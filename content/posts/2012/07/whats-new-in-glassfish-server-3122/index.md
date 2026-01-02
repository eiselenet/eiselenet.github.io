---
title: "What's New in the GlassFish Server 3.1.2.2 Release?"
date: 2012-07-18 11:23:00 +0000
layout: post
tags: ["glassfish", "release"]
slug: "whats-new-in-glassfish-server-3122"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2012/07/whats-new-in-glassfish-server-3122.html
---

You might have heard that the latest GlassFish 3.1.2.2 is out. You can download it from both <a href="http://glassfish.java.net/downloads/3.1.2.2-final.html">glassfish.org</a> and <a href="http://www.oracle.com/technetwork/middleware/glassfish/downloads/index.html">oracle.com</a> . According to the <a href="http://www.java.net/forum/topic/glassfish/glassfish/announcing-upcoming-glassfish-micro-release">announcement in the GlassFish forums</a> it covers three bugs which have been categorized by feedback from the community. These are:
<br>
<br>
 - wsimport Ant tasks causes NoClassDefFoundError from many places from within some java app (<a href="http://java.net/jira/browse/JAX_WS-1059">JAX_WS-1059</a>)
<br>
 - NoClassDefFoundError: org/openide/util/Enumerations$1RDupls due to use of WebServices (NetBeans <a href="http://netbeans.org/bugzilla/show_bug.cgi?id=211962">Bug 211962</a>)
<br>
 - JK listener with Apache + mod_ajp_proxy causes truncated downloads (<a href="http://java.net/jira/browse/GLASSFISH-18446">GLASSFISH-18446</a>) 
<br>
<br>
 According to <a href="http://java.net/projects/glassfish/lists/users/archive/2012-07/message/42" target="_blank">an email</a> to the <a href="http://java.net/projects/glassfish/lists/users/archive" target="_blank">http://java.net/projects/glassfish/lists/users/archive</a>&nbsp;there are some more details about what exactly has been fixed:
<br>
<br><b>Grizzly:</b>
<br>
 - AJP connector can not recover after unexpected EOF (<a href="http://java.net/jira/browse/GRIZZLY-1284">GRIZZLY-1284</a>)
<br>
 - This error occurs occasionally for no apparent reason, causing glassfish does not deliver all packets. (<a href="http://java.net/jira/browse/GRIZZLY-1254">GRIZZLY-1254</a>)
<br>
 - Errow when posting data with "Expect: 100-continue" header via ajp with mod_proxy_ajp in apache (<a href="http://java.net/jira/browse/GRIZZLY-1267">GRIZZLY-1267</a>)
<br>
 - CPing/CPong doesn' work (<a href="http://java.net/jira/browse/GRIZZLY-1276">GRIZZLY-1276</a>)
<br>
 - NPE when attempting to get the session from a request associated with a websocket. (<a href="http://java.net/jira/browse/GRIZZLY-1270">GRIZZLY-1270</a>) - Add memory tuning option for MimeHeaders (<a href="http://java.net/jira/browse/GRIZZLY-1285">GRIZZLY-1285</a>) - Incorrect timeout switch logic may cause unexpected interruption of a thread (<a href="http://java.net/jira/browse/GRIZZLY-1286">GRIZZLY-1286</a>) 
<br>
<br><b>GlassFish:</b>
<br>
 - JK listener with Apache + mod_ajp_proxy causes truncated downloads (<a href="http://java.net/jira/browse/GLASSFISH-18446">GLASSFISH-18446</a>) 
<br>
 - Windows Services - can't handle paths with spaces (<a href="http://java.net/jira/browse/GLASSFISH-18546">GLASSFISH-18546</a>) 
<br>
 - install-node-dcom does not abide by --windowsdomain parameter (<a href="http://java.net/jira/browse/GLASSFISH-18327">GLASSFISH-18327</a>)
<br>
 - Incompatibel breaking changes to getParameter() / getPart() probably for Ticket GLASSFISH-16740 (<a href="http://java.net/jira/browse/GLASSFISH-18444">GLASSFISH-18444</a>)
<br>
<br><b>Metro/JAX-WS:</b>
<br>
 - java.lang.ClassCastException: org.glassfish.gmbal.ManagedObjectManagerNOPImpl cannot be cast to com.sun.xml.ws.server.WSEndpointMOMProxy (<a href="http://java.net/jira/browse/WSIT-1619">WSIT-1619</a>)
<br>
 - wsimport Ant tasks causes NoClassDefFoundError from many places from within some java app (<a href="http://java.net/jira/browse/JAX_WS-1059">JAX_WS-1059</a>)
<br>
<br><b>And more news:</b>
<br>
 And also if you compare the two distributions (zip archives of both&nbsp;<a href="http://glassfish.java.net/downloads/3.1.2-final.html" target="_blank">GF 3.1.2</a> and <a href="http://glassfish.java.net/downloads/3.1.2.2-final.html" target="_blank">GF 3.1.2.2</a>) you notice some more changes.
<br>
 - It seems as if&nbsp;JBoss Logging 3 (&nbsp;3.1.0.GA) is now part of GlassFish. Find the jboss-logging.jar in the modules folder.
<br>
 - Grizzlyhas been updated to 1.9.50
<br>
 - Both org.apache.felix.shell.jar and *.shell.tui.jar have been removed.
<br>
 - The Rest interface for GlassFish Management and Monitoring got an update to&nbsp;3.1.2.1-SNAPSHOT
<br>
 - The Weld implementation is now at&nbsp;1.1.8.Final (yeahhaaa :-))
<br>
 - Metro has been updated to 2.2.0-1 (which actually isn't a "version" in their jira (wondering)
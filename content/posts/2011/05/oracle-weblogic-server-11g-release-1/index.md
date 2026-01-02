---
title: "Oracle WebLogic Server 11g Release 1 (10.3.5) Patch Set 4 seems to be out!"
date: 2011-05-06 13:58:00 +0000
layout: post
tags: ["weblogic", "OEPE"]
slug: "oracle-weblogic-server-11g-release-1"

url: /2011/05/oracle-weblogic-server-11g-release-1.html
---

Right ahead of the weekend I came across a new PS release for WebLogic server. After I still see the 10.3.4 releases being distributed from the official download page, I guess we are a bit ahead of time and the rest of the FMW PS4 is still to be released during the day.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="wls1035.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="25" src="wls1035.png" width="320"></a>
</div>
<br>
 Ok. Here we go: download the latest <a href="http://bit.ly/ixsoSF">win32 version</a> or get the <a href="http://bit.ly/jGkcBX">generic zip installer</a>. Both container WebLogic Server 10.3.5 and the Oracle Enterprise Pack for Eclipse (11.1.1.7.2). Together with the latest Java(TM) SE Runtime Environment (build 1.6.0_24-b07) and the Oracle JRockit(R) (build R28.1.3-11-141760-1.6.0_24-20110301-1429-windows-ia32,compiled mode). The <a href="http://download.oracle.com/docs/cd/E21764_01/index.htm">documentation is also available</a> online. Find the <a href="http://download.oracle.com/docs/cd/E21764_01/wls.htm" target="_blank">WebLogic documentation in a separate book</a>. In general there is not much to see. The "<a href="http://download.oracle.com/docs/cd/E21764_01/web.1111/e13852/toc.htm">what's new section</a>" is nearly empty and I honestly don't see any breathtaking news. There is even still an old Coherence 3.6 version bundled with the download. Odd. 
<br>
 Looking at the WL_HOME/wlserver_10.3/bugsfixed/bugsfixed.htm you can see, that they fixed four (4) bugs ;) 
<br>
<br>
 10155450
<br>
 EJB: A NullPointerException sometimes occurs when an EJB client performs a JNDI lookup for an EJB application that is deployed on a cluster.
<br>
 10191490 
<br>
 Web services: Unable to attach a custom WS-policy to a JAX-RPC Web service.
<br>
 10258751
<br>
 JMS: A JMS Messaging Bridge fails to connect to a secure destination.
<br>
 11664124
<br>
 Core components: libstackdump.so is missing from the software distribution.
<br>
<br>
 Nice. This could be considered as the last stable release here. Now we are all waiting for the first Java EE 6 version to come out.
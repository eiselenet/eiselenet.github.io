---
title: "What can you administrate in 60 Minutes?"
date: 2009-03-25 06:16:00 +0000
layout: post
tags: ["cluster", "high availability", "weblogic server"]
slug: "what-can-you-administrate-in-60-minutes"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2009/03/what-can-you-administrate-in-60-minutes.html
---

Inspired by Adam's Blogpost to his famous "<a href="http://www.adam-bien.com/roller/abien/entry/what_you_can_build_in1" target="_blank">What You Can Build In 50 Minutes With Java EE 5/6?</a>" Session, I tried the other side of software development yesterday. My talk at the <a href="" target="_blank">DOAG</a> SIG Fusion Middleware was about clustering and scaling Oracle Weblogic server. After a (too long) slide oriented presentation about some basic concepts I tried to setup a Weblogic cluster in 60 minutes. Two nodes and one administrative server. Guess what? I did not finish. Maybe, I talked too much and clicked to less ;) But anyway .. it was quite an experience and the only two tasks missing were to define replication groups and start the cluster over to deploy the sample applications. 
<br>
<br>
<div style="width:425px;text-align:left" id="__ss_1194135">
 <a style="font:14px Helvetica,Arial,Sans-serif;display:block;margin:12px 0 3px 0;text-decoration:underline;" href="http://www.slideshare.net/guest4758536/sig-middleware-weblogicserver-cluster?type=powerpoint" title="Sig Middleware Weblogicserver Cluster">Sig Middleware Weblogicserver Cluster</a><object style="margin:0px" width="425" height="355"><param name="movie" value="http://static.slidesharecdn.com/swf/ssplayer2.swf?doc=sigmiddlewareweblogicservercluster-090325013128-phpapp01&amp;stripped_title=sig-middleware-weblogicserver-cluster"><param name="allowFullScreen" value="true"><param name="allowScriptAccess" value="always"><embed src="http://static.slidesharecdn.com/swf/ssplayer2.swf?doc=sigmiddlewareweblogicservercluster-090325013128-phpapp01&amp;stripped_title=sig-middleware-weblogicserver-cluster" type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true" width="425" height="355"></object>
 <div style="font-size:11px;font-family:tahoma,arial;height:26px;padding-top:2px;">
  View more <a style="text-decoration:underline;" href="">presentations</a> from <a style="text-decoration:underline;" href="http://www.slideshare.net/guest4758536">guest4758536</a>.
 </div>
</div>
<br>
<br>
During the research for this session I examined the examples delivered with the Weblogic server. You can find them in your local installation at <bea_home>
 \wlserver_10.3\samples\server\examples\src\examples.
 <br>
 There are two cluster examples available. First one is about a statefull session bean and the second one about HTTP Session Failover.
 <br>
 If you run the statefull session bean example, you will notice that you don't see any message about a happening failover. I need to digg into this a bit deeper, cause the documentation states, that you should (!) see a message. Anyway: If you would like to find out, on which node you are running, you can simply use a weblogic server MBean to find out:
 <br>
 <br>
 <blockquote>
  <br>
  InitialContext ic = new InitialContext();
  <br>
  MBeanHome mbeanHome = (MBeanHome) ic.lookup("weblogic.management.home.localhome");
  <br>
  String nodeName = mbeanHome.getMBeanServer().getServerName();
  <br>
 </blockquote>
 <br>
 <br>
 And yes, I know, that the MBeanHome interface is deprecated since the 9.0.0.0 ;) But it's a quite short way to figure it out. It was replaced by standard JMX design patterns in which clients use the javax.management.MBeanServerConnection interface to discover MBeans, attributes, and attribute types at runtime.
</bea_home>
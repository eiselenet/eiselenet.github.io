---
title: "LogDigger - Gives wings to your Java application logging"
date: 2008-11-25 13:37:00 +0000
layout: post
tags: ["firebug", "development", "firefox", "log4j"]
slug: "logdigger-gives-wings-to-your-java"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2008/11/logdigger-gives-wings-to-your-java.html
---

<a href="" target="_blank">LogDigger </a>provides Java web application developers with instant access to application log messages directly from Firefox.
<br>
<br>
LogDigger is an extension for <a href="" target="_blank">Firefox </a>and <a href="" target="_blank">Firebug </a>that collects <a href="" target="_blank">Log4j </a>log messages related to requested application URL and shows them in Firebug.
<br>
<br>
All you have to do is to put some LogDigger jar-Files into your /WEB-INF/lib and configure a special filter-mapping 
<br>
<br>
<blockquote>
 <br><filter>
  <br>
  <filter-name>
   LogDigger Servlet Filter
  </filter-name>
  <br>
  <filter-class>
   org.logdigger.servlet.filter.LogDiggerServletFilter
  </filter-class>
  <br>
 </filter>
 <br>
</blockquote>
<br>
<br>
for your webapplication. After that you have to install Firebug and the LogDigger Firefox extension. After restarting Firefox you should see LogDigger label next to the Firebug’s icon in the Firefox status bar. When LogDigger receives log messages the label changes to event counter (by log level) that you can click on to open log console.
<br>
<br>
If your code instruments Log4J logmessages, you can review them within your browser window. Perfect for development on remote instances!
<br>
<br>
<br>
LogDigger 0.2 is FREE for both personal and commercial use.
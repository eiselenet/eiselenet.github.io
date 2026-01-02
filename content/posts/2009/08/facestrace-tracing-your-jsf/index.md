---
title: "FacesTrace - tracing your JSF applications"
date: 2009-08-27 09:41:00 +0000
layout: post
tags: ["FacesTrace", "jsf", "weblogic server"]
slug: "facestrace-tracing-your-jsf"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2009/08/facestrace-tracing-your-jsf.html
---

<a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/faces_trace-775426.png"><img style="float:right; margin:0 0 10px 10px;cursor:pointer; cursor:hand;width: 195px; height: 200px;" src="http://www.eisele.net/blog/uploaded_images/faces_trace-775424.png" border="0" alt=""></a> Ever wondered, where your JSF app spends it's evenings? FacesTrace aims to enhance the traceability of JavaServer Faces based applications. It collects several trace information and performance metrics and presents them on the
<br>
page being traced. It is related to but not dependent on <a href="http://primefaces.prime.com.tr" target="_blank">PrimeFaces</a>.
<br>
The main metrics and information covered are:
<br>
<br>
• JSF LifeCycle visualizer
<br>
• Performance Tracker
<br>
• Scoped Attributes
<br>
• Log4J appender
<br>
• FacesMessage Lister
<br>
• Component Tree visualizer
<br>
<br>
I tryed it with weblogic 11g and it works fine.
<br>
FacesTrace has a single jar called facestrace-\{version\}.jar, it has no dependency to any other PrimeFaces module and can be used seperately. The only other dependency you need is a recent commons-logging-\{version\}.jar.
<br>
FacesTrace setup is simply about having the facestrace jar in your classpath. No other configuration beside the *.jsf mapping needed. FacesTrace is both compatible with Facelets and JSP. In order to display the trace information, simply add trace=true request parameter to the page youʼre tracing.
<br>
<br><code><br>
 http://localhost:7001/myapp/mypage.jsf?trace=true<br></code>
<br>
<br><span style="font-weight:bold;">Performance Tracker</span>
<br>
The performance tracker keeps track of how long each jsf phase takes and displays the execution times.
<br>
<br><span style="font-weight:bold;">Lifecycle Tracker</span>
<br>
Visualizes the JSF Request Lifecycle and displays each phases's state in different color. Green means, the phase is executed with success. Red means, the phase caused an error. Gray indicates, that the phase was never executed.
<br>
<br><span style="font-weight:bold;">Variables</span>
<br>
Displays each key-value pair in different scopes like application, session, request, cookies,params and more.
<br>
<br><span style="font-weight:bold;">Faces Messages</span>
<br>
Displays FacesMessages added to the FaceContext for the request being processed.
<br>
<br><span style="font-weight:bold;">Logs</span>
<br>
FacesTrace has it's own appender, setting the log4j to use this appender will alow
<br>
FacesTrace to display the logs at the page
<br>
<br><span style="font-weight:bold;">Component Tree</span>
<br>
The component tree of the view, can be traversed using the dhtml tree view of the
<br>
FacesTrace.
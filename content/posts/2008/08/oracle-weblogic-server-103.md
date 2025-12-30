---
title: "Oracle Weblogic Server 10.3"
date: 2008-08-08 06:08:00 +0000
layout: post
tags: ["bea oracle", "weblogic server"]
slug: "2008-08-08-oracle-weblogic-server-103"
url: /2008/08/oracle-weblogic-server-103.html
---

Es ist passiert :) Der erste Oracle Weblogic Server ist draussen:
<br><a href="https://mix.oracle.com/group_messages/43550-download-oracle-weblogic-server-10-3" target="_blank">Oracle Download Link</a>
<br>
<br>
Ein Überblick über die neuen Features:
<br><b>Lightweight WebLogic Server</b>
<br>
<br>
The first enhancement area is making WebLogic Server more "lightweight." The term "quot;lightweight" means different things to different people, including characteristics such as quot;faster download,quot; quot;smaller disk footprint,quot; quot;less memory consumption,quot; quot;faster deployment,quot; or quot;faster server startup.quot; 
<br>
<br>
<br>
The primary underlying requirement is to enable developers to be more productive by reducing the resources and time consumed by the server and server-related actions. In WebLogic Server 10.3, we're delivering improvements in all of the areas cited above. Here are some examples:
<br>
<ul>
 <br>
 <li><strong>Download time and installation footprint</strong> — Over the years, we have included complementary technologies and tools in the WebLogic Server download: JVMs, the Workshop IDE, the Domain and Upgrade Wizards, database drivers, the WebLogic Server console, and more. These optional tools add to WebLogic Server download and installation time and installation footprint. In this Technology Preview release we are giving you more installation options, making the above and other technologies selectable at time of installation. Look for download options as well as we get closer to general availability (GA).</li>
 <br>
 <li><strong>Application development round-trip time</strong> — Many WebLogic Server developers go through a full application redeployment (or a server reboot) during iterative development whenever they find a problem and make an application change (however small). This can be disruptive and time-consuming to the development process. This Technology Preview includes a feature called quot;FastSwapquot; that leverages Java SE features and enables developers to make changes to a single class or set of classes, recompile and save these changes, and have these changes immediately reflected in a running server without loss of server context. This is a much more responsive and seamless experience than before.</li>
 <br>
 <li><strong>Optional service startup</strong> — Many developers use WebLogic Server for development of Web applications, and don't use EJB or JMS services. These services are always started by the server, with minor but non-zero impact on server startup and memory consumption. The Technology Preview enables you to specify whether you want to start these services. This capability is a result of ongoing progress in making the WebLogic Server server more modular, to give you more control over which pieces of the server you use.</li>
 <br>
 <li><strong>Console performance improvements</strong> — The WebLogic Server console offers a rich set of functionality, supporting WebLogic Server configuration and deployment tasks with substantial flexibility for end-user customization. In recent releases, the implementation supporting these capabilities made the console less responsive. In this release you will see notable performance improvements relative to WebLogic Server 9.2 and 10.0 when starting and using the console—on the order of two to three times more responsive. The console look and feel is also much improved.</li>
 <br>
 <li><strong>Startup and runtime performance</strong> — We have continued to invest in startup time reductions and server throughput improvements to make the WebLogic Server environment more responsive for developers, administrators, and end users.</li>
 <br>
</ul>
<br><b>Programming Model and API Support</b>
<br>
<br>
The second enhancement area is support for new developer APIs. The primary WebLogic Server value proposition is providing reliability, availability, scalability, and performance (RASP) benefits for enterprise-class Java applications and services. Java EE is the primary programming model used with WebLogic Server; we remain the technology leader in adoption of Java EE standards, and the Technology Preview contains further enhancements to our existing Java EE 5 support, particularly EJB and JPA support.
<br>
However, we want to extend the value of our RASP infrastructure to other Java programming models and frameworks used by developers. Here is some of the additional support found in the Technology Preview:
<br>
<ul>
 <br>
 <li><strong>SOA Server-Web services/SCA support</strong> — WebLogic Server provides an environment for developing and hosting SOA services, and is the foundation for BEA's SOA offering. WebLogic Server 10.3 delivers new features for developing services and application for Service-Oriented Architectures. First we're enhancing Web services standards support for both JAX-RPC (J2EE 1.4) and JAX-WS (Java EE 5) Web services. Service Component Architecture (SCA) support, which will enable standards-based development of composite applications, is coming soon. This will be made available in coming months in preview form as an add-on to the WebLogic Server 10.3 Technology Preview. We have not yet finalized the packaging for this technology; it may not be included with WebLogic Server 10.3 when WebLogic Server 10.3 is generally available.</li>
 <br>
 <li><strong>Spring enhancements</strong> — WebLogic Server has supported development and execution of Spring applications since WebLogic Server 9.0, and we continue to provide enhancements in this area. WebLogic Server 10.3 provides improvements to distributed management of Spring applications, and improved integration of Spring security with WebLogic Server. The Technology Preview release currently supports Spring 2.0.2; we intend to upgrade this support to Spring 2.1 by GA.</li>
 <br>
 <li><strong>Web 2.0 support</strong> — WebLogic Server 10.3 will support the development of AJAX-enabled applications with WebLogic Server through Dojo client support. In addition, we are leveraging Dojo client technology in the implementation of a publish/subscribe infrastructure for HTTP clients. This feature will enable browser and Java clients to establish persistent sessions with WebLogic Server servers, and subscribe to messages published to the server messaging infrastructure. Using this technology, clients can send and receive lightweight data-driven updates on topics and information of interest, without requiring a full HTTP request/response update.</li>
 <br>
</ul>
<br>
<br><b>Technology Integration and Standards</b>
<br>
The third enhancement area is enterprise technology integration and standards updates. WebLogic Server applications must coexist and interoperate with other technologies via de facto or de jure standards to support development and execution of secure, high-performance, and high-availability enterprise applications. We've updated our support to meet key customer and developer requirements in this area: 
<br>
<ul>
 <br>
 <li><strong>C# JMS client for .NET integration</strong> — In WebLogic Server 9.2, BEA delivered major improvements to the WebLogic Server JMS subsystem. For example, we delivered huge performance improvements for use cases involving persistent messaging. As a result of this and many other improvements, we're seeing increased adoption of WebLogic Server JMS for enterprise messaging infrastructure, and consequent demand for better direct integration with non-WebLogic Server technologies, like .NET. To meet these requirements in WebLogic Server 10.3, we've developed C# JMS client APIs that will enable .NET applications to interface directly with the JMS subsystem, without requiring any Java on the .NET client.</li>
 <br>
 <li><strong>SAML 2.0</strong> — The Security Assertion Markup Language (SAML) is the standard for exchange of security information to enable single sign-on across security domains. This WebLogic Server 10.3 Technology Preview supports the SAML 2.0 standard (and brings forward existing SAML 1.1 support) to enable single sign-on for Web apps as well as Web services.</li>
 <br>
 <li><strong>Web Services standards</strong> — As noted above, WebLogic Server 10.3 includes new and updated support for Web services standards, especially OASIS WS-* standards such as WS-Security, WS-Policy, WS-Reliable Messaging, and WS-Addressing.</li>
 <br>
 <li><strong>Java SE 6</strong> — WebLogic Server 10.3 supports and leverages Java SE 6, the latest release of the Java SE platform.</li>
 <br>
 <li><strong>Eclipse support</strong> — We intend to provide an updated version of BEA Workshop for WebLogic, our Eclipse-based IDE, to support the GA release of WebLogic Server 10.3. For this Technology Preview, we've updated our WebLogic Server Tools (plug-ins for the Eclipse IDE) on Dev2Dev to enable use of Eclipse for developing applications with the Technology Preview.</li>
 <br>
 <li><strong>Other goodies</strong> — We continue to provide ongoing innovation and improvement to meet enterprise requirements for manageability and availability. For example, the WebLogic Diagnostic Framework first introduced in WebLogic Server 9.2 provides powerful capabilities for monitoring WebLogic Server servers and applications and diagnosing problems. We're delivering further improvements in the Technology Preview. The Technology Preview also delivers improvements to JMS Service-Level migration. See the Technology Preview documentation.</li>
 <br>
</ul>
<br>
<br>
<br>
(Quelle:
<br><a href="http://www.oracle.com/technology/pub/articles/dev2arch/2007/11/weblogic-server-10-tech-preview.html" target="_blank">weblogic-server-10-tech-preview Highlights</a>)
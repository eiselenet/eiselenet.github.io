---
title: "Enterprise Modules Project (Gemini), oracle, springsource and weblogic server"
date: 2009-11-23 11:54:00 +0000
layout: post
tags: ["OSGi", "oracle", "Spring", "weblogic server"]
slug: "enterprise-modules-project-gemini"

url: /2009/11/enterprise-modules-project-gemini.html
---

This happened three days ago. <a href="" target="_blank">SpringSource</a> and <a href="http://www.oracle.com" target="_blank">Oracle </a>proposed the <a href="" target="_blank">Enterprise Modules Project (Gemini)</a> to the Eclipse community, whose primary goal is to provide access to standard enterprise technology implementations within a modular framework. The project will include important <a href="http://www.osgi.org/EEG/HomePage" target="_blank">OSGi EEG</a> implementations (Blueprint Service Implementation and Web Container Integration) contributed by SpringSource as well as contributed code by Oracle. All sub projects will be consumable as modules (or OSGi bundles) and will provide implementations for important and popular enterprise standards.
<br>
<br>
 Up to now, there is no official feedback available in the <a href="http://www.eclipse.org/forums/eclipse.gemini" target="_blank">community forums</a>. Even if the announcement did fire up some <a href="http://www.google.com/search?q=Eclipse+Gemini" target="_blank">press comments (google.com</a>. You can find the <a href="http://www.springsource.org/node/2178" target="_blank">SpringSource press release</a> here.
<br>
<br>
 The overall lead for the project is <a href="http://www.oracle.com/innovation/innovator-mike-keith.html" target="_blank">Mike Keith</a>. The project mentors are <a href="" target="_blank">Wayne Beaton</a>, <a href="http://wiki.eclipse.org/User:Douglas.clarke.oracle.com" target="_blank">Doug Clarke</a> and <a href="http://www.springsource.com/about/management" target="_blank">Adrian Colyer</a>. 
<br>
<br>
 Gemini is organized under 6 subprojects:
<br>
 * RFC 66 — Web Container
<br>
 * RFC 98 — Transactions
<br>
 * RFC 122 — Database Access
<br>
 * RFC 124 — Blueprint Services
<br>
 * RFC 139 — JMX Integration
<br>
 * RFC 142 — JNDI Integration
<br>
 * RFC 143 — JPA Integration
<br>
 * RFC 146 — JCA Connector Integration
<br>
<br>
 As you can see, some important JEE specs are still missing (e.g. EJB &amp; JMS).
<br>
<br>
 Most notably, this project would continue the recently presented invests taken by Oracle in <a href="http://www.eisele.net/blog/2009/10/preview-weblogic-dm-server-weblogic.html">OSGi and a WebLogic DM server</a>. It could lead to a defined standard ways of building and running enterprise technologies (such as Java EE) in OSGi. The EEG has a wide participation: beside Oracle and SpringSource you can find IBM, IONA/Progress, Tibco, ProSyst, Siemens and LinkedIn on the list.
<br>
<br>
 On a midrange timeframe both technologies could grow together. While Java EE defines a suite of application-level APIs, OSGi specifies the infrastructure obligations, needed running them. With the power of OSGi some of the many existing Java EE issues could be solved:
<br>
 * Application versioning
<br>
 * Facilitates application module reuse
<br>
 * Hot upgrades/patches
<br>
<br>
 So OSGi could be the enabler for the next generation of Java server infrastructure.
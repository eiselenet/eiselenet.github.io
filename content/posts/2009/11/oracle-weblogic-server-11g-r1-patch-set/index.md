---
title: "Oracle WebLogic Server 11g R1 Patch Set 1 available (WLS 10.3.2.0), contains JScaLite!"
date: 2009-11-13 03:10:00 +0000
layout: post
tags: ["oracle", "11gR1", "patch set", "weblogic server"]
slug: "oracle-weblogic-server-11g-r1-patch-set"

url: /2009/11/oracle-weblogic-server-11g-r1-patch-set.html
---

The Patch Set 1 release of WebLogic Server 11g Release 1 was release yesterday. Up to know, there is no detailed readme or my oracle support information available about the contained bugfixes.
<br>
<br>
 This Patch Set includes a Technical Preview of WebLogic SCA. When Oracle introduced the new version of the Oracle SOA Suite 11g, it introduced a new abstraction layer, the <a href="http://www.oracle.com/technology/tech/standards/sca/index.html" target="_blank">Service Component Architecture (SCA)</a>. The SCA can be seen from a technical point of view, as a container that holds a set of SOA components. These components interact with each other and can be exposed to the outside world as a service. The components itself can call external services, these are called references. In WebLogic SCA, you can write Java applications using Plain Old Java Objects (POJOs) and expose components as SCA references and services, using SCA semantics configured in a Spring application context. WebLogic SCA applications run in WebLogic Server (via the WebLogic SCA Runtime) and can be used as components in Oracle SOA composites. You select the SCR Runtime during installation.
<br>
<br>
 The runtime itself is called JscaLite and is JscaLite a lightweight Spring 2.5 (or higher) SCA container for standalone WLS. JScaLite aligns itself with the Spring C&amp;I specification 1.1 except for SCA Properties. 
<br>
<br><a href="http://www.oracle.com/technology/software/products/ias/htdocs/wls_main.html" target="_blank">Download it directly from Oracle</a>
<br>
 You can find the <a href="http://download.oracle.com/docs/cd/E12839_01/wls.htm" target="_blank">online documentation here</a>.
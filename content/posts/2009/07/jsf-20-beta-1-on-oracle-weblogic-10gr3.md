---
title: "JSF 2.0-BETA 1 on Oracle Weblogic 10gR3"
date: 2009-07-07 09:03:00 +0000
layout: post
tags: ["10gR3", "oracle", "weblogic server", "jsf 2.0"]
slug: "jsf-20-beta-1-on-oracle-weblogic-10gr3"
link: "2009/07/jsf-20-beta-1-on-oracle-weblogic-10gr3.html"
url: /2009/07/jsf-20-beta-1-on-oracle-weblogic-10gr3.html
---

As you probably may have heard: JSF2.0 is final now. Time to give the available distributions a shot. I was curious to find out, that the actual available builds of the RI are still in BETA. Therefore I had to try it with the <a href="https://javaserverfaces.dev.java.net/nonav/rlnotes/2.0.0/index.html" target="_blank">2.0.0 Beta1 (26 May 2009)</a>.
<br>
<br>
 To deploy new JSF libraries to the Oracle Weblogic server, you have to provide a separate application library. This is after all quite simple, but you have to take some actions at all.
<br>
 - grep a sample from %ORACLE_HOME%\wlserver_10.3\common\deployable-libraries
<br>
 - extract the .war (e.g.jsf-1.2.war) and rename the folder appropriate (e.g. jsf-2.0.1)
<br>
 - change the libraries in WEB-INF/lib (you need the jsf-api.jar, jsf-impl.jar and the jstl-1.2.jar)
<br>
 - change the META-INF/MANIFEST.MF to reflect the appropriate version informations:
<br>
 Specification-Version: 2.0
<br>
 Specification-Vendor: Sun Microsystems, Inc.
<br>
 Implementation-Title: JSF Reference Implementation (Mojarra 2.0.0 BETA1)
<br>
 Implementation-Version: 2.0.1
<br>
 - add a WEB-INF/classes folder and add the com.bea.faces.WeblogicInjectionProvider.class class to it
<br>
 - last part is to zip everything together again and have a jsf-2.0.1.war
<br>
<br>
 Now you are able to deploy the new library to the WLS. Make shure to add the managed library to your applications weblogic.xml.
<br>
<br>
 &lt;wls:library-ref&gt;
<br>
 &lt;wls:library-name&gt;jsf&lt;/wls:library-name&gt;
<br>
 &lt;wls:specification-version&gt;2.0&lt;/wls:specification-version&gt;
<br>
 &lt;wls:implementation-version&gt;2.0.1&lt;/wls:implementation-version&gt;
<br>
 &lt;wls:exact-match&gt;false&lt;/wls:exact-match&gt;
<br>
 &lt;/wls:library-ref&gt;
<br>
<br>
 and try out some samples. I came across several issues:
<br>
 - It seems as if neither the com.sun.faces.taglib.jsf_core.BeanValidatorTag nor the com.sun.faces.taglib.jsf_core.RequiredValidatorTag are part of the distribution at the moment. Trying to start the samples app, leads to a class not found error. Only fix for now is to remove the Tags from the jsf_core.tld. 
<br>
 - Not all new @Annotations are processed with the WeblogicInjectionProvider for now. Therefore, even the simplest examples do not work as they should with this version. 
<br>
<br>
 Let's see, what happens with the final release, and how we could deploy this to the WLS :)
<br>
<br>
 [UPDATE: 14.07.09]
<br>
 Even the latest Oracle Fusion Middleware and within it the Oracle Weblogic 10.3.1.0 still only supports JSF 1.2.9.0. At the moment it is simply not possible to run JSF 2.0 on top of Weblogic Server.
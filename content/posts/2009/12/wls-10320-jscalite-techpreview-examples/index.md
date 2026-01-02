---
title: "WLS 10.3.2.0, JScaLite TechPreview .. examples ... working :)!"
date: 2009-12-04 07:41:00 +0000
layout: post
tags: ["sca", "JScaLite", "Spring", "weblogic server"]
slug: "wls-10320-jscalite-techpreview-examples"

url: /2009/12/wls-10320-jscalite-techpreview-examples.html
---

I had some quite <a href="http://www.eisele.net/blog/2009/11/wls-10320-jscalite-techpreview-examples.html">dissapointing trials</a> running the new JScaLite examples, shipped with the Oracle WebLogic Server 11g R1 Patch Set 1 (WLS 10.3.2.0).
<br>
 Seems as if my calls were heared at Oracle and they started to write some blogposts about the new Weblogic SCA container tech preview. 
<br>
 Raghav Srinivasan posted <a href="http://blogs.oracle.com/WebLogicServer/2009/12/getting_started_with_weblogic.html" target="_blank">"Getting Started with SCA"</a> and this finaly gave me the missing hints about what to do, getting the examples running.
<br>
<br>
 Here is the complete HowTo:
<br>
<br>
 Follow the steps, already described in my <a href="http://www.eisele.net/blog/2009/11/wls-10320-jscalite-techpreview-examples.html">previous post</a>
<br>
 1) Install WLS 10.3.2.0 (with examples)
<br>
 2) setup environment variables
<br>
 3) change example.properties
<br>
 4) deploy the weblogic-sca-1.0.war
<br>
 5) point the war files to the library (weblogic.xml)
<br>
<br><b>Now, here comes the music:</b>
<br>
 6) Change the spring-context.xml files
<br>
 one located in both example war files:
<br>
 - JSca_GetTotQty_EAR\JSca_GetTotQty_WAR\WEB-INF\classes\META-INF\jsca
<br>
 - JSca_GetTotPrice_EAR\JSca_GetTotPrice_WAR\WEB-INF\classes\META-INF\jsca
<br>
<br>
 Replace the &lt;beans tag at the beginning with the following:
<br>
<br>
 &lt;beans xmlns="http://www.springframework.org/schema/beans"
<br>
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
<br>
 xmlns:sca="http://xmlns.oracle.com/weblogic/weblogic-sca"
<br>
 xmlns:wlsb="http://xmlns.oracle.com/weblogic/weblogic-sca-binding"
<br>
 xmlns:wsp="http://schemas.xmlsoap.org/ws/2004/09/policy"
<br>
 xsi:schemaLocation="http://www.springframework.org/schema/beans
<br>
 http://www.springframework.org/schema/beans/spring-beans-2.0.xsd
<br>
 http://xmlns.oracle.com/weblogic/weblogic-sca
<br>
 http://xmlns.oracle.com/weblogic/weblogic-sca/1.0/weblogic-sca.xsd
<br>
 http://xmlns.oracle.com/weblogic/weblogic-sca-binding
<br>
 http://xmlns.oracle.com/weblogic/weblogic-sca-binding/1.0/weblogic-sca-binding.xsd"&gt;
<br>
<br>
 replace all &lt;binding with &lt;wlsb:binding
<br>
<br>
 7) Now your are ready to build the examples (ant build)
<br>
 8) and deploy them (ant deploy)
<br>
<br>
 Working behind a corporate proxy leads to a fancy warning:
<br>
<br>
 &lt;Warning&gt; &lt;org.springframework.beans.factory.xml.XmlB
<br>
 eanDefinitionReader&gt; &lt;BEA-000000&gt; &lt;Ignored XML validation warning
<br>
 org.xml.sax.SAXParseException: schema_reference.4: Failed to read schema documen
<br>
 t 'http://xmlns.oracle.com/weblogic/weblogic-sca-binding/1.0/weblogic-sca-wlsb:b
<br>
 inding.xsd', because 1) could not find the document; 2) the document could not b
<br>
 e read; 3) the root element of the document is not &lt;xsd:schema&gt;.
<br>
<br>
 This is because of the fact, the the WLS does not have access to the needed schema files. Therefore the deployment takes quite some time until the timeouts appear.
<br>
<br>
 Anyhow, if you switch your configuration and use a proxy in your setDomainEnv.bat/.sh JAVA_PROPERTIES
<br>
 like this: -Dhttp.proxyHost=myproxyserver.com -Dhttp.proxyPort=80
<br>
<br>
 you run into another problem, a SAX Parser exception:
<br>
<br>
 org.xml.sax.SAXParseException: White spaces are required between publicId and systemId
<br>
<br>
 That is not too funny, as the deployment will fail with this. Therefore, you better accept the warnings and get the app deployed :)
<br>
<br>
<br>
 9) You can run the examples from the command line (ant run) this opens up your favorite browser and
<br>
 points it to the URL http://&lt;host&amp;gt;:&lt;port&amp;gt;/ShoppingCartCtx/ShoppingCart
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/jscalite-714747.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="320" src="http://www.eisele.net/blog/uploaded_images/jscalite-714745.png" width="320"></a>
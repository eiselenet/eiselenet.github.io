---
title: "Scaling up to WebLogic 12c Server from GlassFish 3.x"
date: 2011-12-12 23:00:00 +0000
layout: post
tags: ["weblogic", "glassfish"]
slug: "scaling-up-to-weblogic-12c-server-from"

url: /2011/12/scaling-up-to-weblogic-12c-server-from.html
---

One of the main goals of Oracle's strategy for GlassFish server was to "integrate with Fusion Middleware and Products" (source: <a href="http://kenai.com/downloads/glassfish-media/CommunityUpdate-25Mar2010.pdf" target="_blank">Community Roadmap May, 2010</a>). Back in this year you heard a lot of fears and rumors about the two servers becoming one. Seeing both products moving forward in terms of features and releases it gets clearer what that strategy could be. Beginning with GlassFish's support for a <a href="http://docs.oracle.com/cd/E18930_01/html/821-2417/gkiot.html" target="_blank">limited set of weblogic</a> specific deployment descriptors, Oracle also moved on with WebLogic to do the same. Beginning with 10.3.6 WebLogic Server adds support for reading and using GlassFish's web deployment descriptors. These are glassﬁsh-web.xml and sun-web.xml. This is useful for providing speciﬁc GlassFish behavioral settings and mappings for resources and security to WebLogic Server. The goal behind that obviously is to allow a GlassFish application to be deployed more easily to WebLogic Server and vice verse.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="wls_gf.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="139" src="wls_gf.png" width="320"></a>
</div>
<br>
<br><b>What WebLogic knows about GlassFish</b>
<br>
 WebLogic Server detects the presence of GlassFish web deployment descriptors in WAR files and parses them. Known entries are parsed into WebLogic server settings and applied at runtime via WebLogic MBeans (weblogic.j2ee.descriptor.wl.WeblogicWebAppBean). 
<br>
 WebLogic always will use an existing weblogic.xml instead of the GlassFish deployment descriptors if it is present and WebLogic applies the settings at runtime which means, that no weblogic.xml is actually generated.
<br>
<br>
<table border="0" cellpadding="0" cellspacing="0">
 <tbody>
  <tr>
   <td>
    <table border="0" cellpadding="0" cellspacing="1">
     <colgroup>
      <col width="50%">
     </colgroup>
     <colgroup>
      <col width="50%">
     </colgroup>
     <tbody>
      <tr bgcolor="#C0C0C0">
       <th align="left" class="columncaption" scope="col" valign="top"><b><tt>glassfish-web.xml</tt> Element Name</b></th>
       <th align="left" scope="col" valign="top"><b>Weblogic Support (<tt>weblogic.xml</tt>)</b></th>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>context-root</tt></td>
       <td align="left" valign="top"><tt>context-root</tt></td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>security-role-mapping</tt> <br><tt>role-name</tt><br><tt>principle-name</tt><br><tt>group-name</tt></td>
       <td align="left" valign="top"><tt>security-role-assignment</tt><br><tt>role-name</tt><br><tt>principle-name</tt><br><tt>principle-name</tt></td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>session-config</tt><br>
         session-manager:manager-properties:<tt>reapIntervalSeconds</tt><br>
         session-manager:manager-properties:<tt>maxSessions</tt><br>
         session-manager:manager-properties:<tt>directory</tt><br>
         session-manager:manager-properties:<tt>timeoutSeconds</tt></td>
       <td align="left" valign="top"><tt>session-descriptor</tt><br><tt>invalidation-interval-seconds </tt><br><tt>max-in-memory-sessions </tt><br><tt>persistentOstoreOdir </tt><br><tt>timeout-secs</tt></td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>ejb-ref</tt><br><tt>ejb-ref-name</tt><br><tt>jndi-name</tt></td>
       <td align="left" valign="top"><tt>ejb-ref-description</tt><br><tt>ejb-ref-name</tt><br><tt>jndi-name</tt></td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>resource-ref</tt><br><tt>res-ref-name</tt><br><tt>jndi-name</tt></td>
       <td align="left" valign="top"><tt>resource-description</tt><br><tt>res-ref-name</tt><br><tt>jndi-name</tt></td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>resource-env-ref</tt><br><tt>resource-env-ref-name</tt><br><tt>jndi-name</tt></td>
       <td align="left" valign="top"><tt>resource-env-description</tt><br><tt>resource-env-ref-name</tt><br><tt>jndi-name</tt></td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>class-loader</tt><br><tt>delegate</tt></td>
       <td align="left" valign="top"><tt>container-descriptor</tt><br><tt>prefer-web-inf-classes</tt></td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>jsp-config</tt><br><tt>checkInterval</tt><br><tt>keepgenerated</tt><br><tt>scratchdir</tt></td>
       <td align="left" valign="top"><tt>jsp-descriptor</tt><br><tt>page-check-seconds</tt><br><tt>keepgenerated</tt><br><tt>working-dir</tt></td>
      </tr>
     </tbody>
    </table></td>
  </tr>
 </tbody>
</table>
<br>
 If you deploy a GlassFish web-application to WebLogic you get some log messages with INFO level and you can follow what is happening:
<br>
<br><tt> &lt;Info&gt; &lt;HTTP&gt; &lt;BEA-101392&gt;...<br>
  &lt;Glassfish Descriptor element &lt;glassfish-web-app&gt; is not supported&gt; <br>
 &lt;Glassfish Descriptor element &lt;context-root&gt; was successfully parsed and applied&gt; <br>
 &lt;Glassfish Descriptor element &lt;idempotent-url-pattern&gt; is not supported&gt;<br>
 &lt;Glassfish Descriptor element &lt;property&gt; is not supported&gt;<br>
 &lt;Glassfish Descriptor element &lt;reapIntervalSeconds&gt; was successfully parsed and applied&gt;<br>
 &lt;Glassfish Descriptor element &lt;res-ref-name&gt; was successfully parsed and applied&gt;<br>
 &lt;Glassfish Descriptor element &lt;jndi-name&gt; was successfully parsed and applied&gt;<br>
 &lt;Glassfish Descriptor element &lt;delegate&gt; was successfully parsed and applied&gt;<br>
 &lt;Glassfish Descriptor element &lt;keepgenerated&gt; was successfully parsed and applied&gt;</tt>
<br>
<br>
 Compared to what GlassFish knows about WebLogic, this is still a very limited set of parameters. But it covers the most needed ones. And we are still looking forward to even less xml configuration with further Java EE versions. But let's look at the other side.
<br>
<br><b>What GlassFish knows about WebLogic</b>
<br>
 GlassFish Server offers limited support for the weblogic-application.xml, weblogic.xml, and weblogic-webservices.xml deployment descriptor files. The only element in weblogic-application.xml that GlassFish Server supports is security. The equivalent element in the glassfish-application.xml file is security-role-mapping.
<br>
<br>
<table border="0" cellpadding="0" cellspacing="0">
 <tbody>
  <tr>
   <td>
    <table border="0" cellpadding="0" cellspacing="1" class="vatop" datatable="1" summary="<tt>weblogic.xml</tt> Support in GlassFish Server">
     <colgroup>
      <col width="50%">
     </colgroup>
     <colgroup>
      <col width="50%">
     </colgroup>
     <tbody>
      <tr bgcolor="#C0C0C0">
       <th align="left" class="columncaption" scope="col" valign="top"><b><tt>weblogic.xml</tt> Element Name</b></th>
       <th align="left" scope="col" valign="top"><b>GlassFish Server Support</b></th>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>role-name</tt> under <tt>security-role-assignment</tt></td>
       <td align="left" valign="top"><tt>role-name</tt> under <tt>security-role-mapping</tt> <tt>glassfish-web.xml</tt> equivalent</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>principal-name</tt> under <tt>security-role-assignment</tt></td>
       <td align="left" valign="top"><tt>principal-name</tt> under <tt>security-role-mapping</tt> <tt>glassfish-web.xml</tt> equivalent</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>resource-description</tt></td>
       <td align="left" valign="top"><tt>resource-ref</tt> <tt>glassfish-web.xml</tt> equivalent, but <tt>resource-link</tt> not supported</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>resource-env-description</tt></td>
       <td align="left" valign="top"><tt>resource-env-ref</tt> <tt>glassfish-web.xml</tt> equivalent, but <tt>resource-link</tt> not supported</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>ejb-reference-description</tt></td>
       <td align="left" valign="top"><tt>ejb-ref</tt> <tt>glassfish-web.xml</tt> equivalent</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>service-reference-description</tt></td>
       <td align="left" valign="top"><tt>service-ref</tt> <tt>glassfish-web.xml</tt> equivalent</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>timeout-secs</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top"><tt>timeoutSeconds</tt> property of <tt>session-properties</tt> <tt>glassfish-web.xml</tt> equivalent</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>invalidation-interval-secs</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top"><tt>reapIntervalSeconds</tt> property of <tt>manager-properties</tt> <tt>glassfish-web.xml</tt> equivalent</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>max-in-memory-sessions</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top"><tt>maxSessions</tt> property of <tt>manager-properties</tt> <tt>glassfish-web.xml</tt> equivalent</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>persistent-store-dir</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top"><tt>directory</tt> property of <tt>store-properties</tt> <tt>glassfish-web.xml</tt> equivalent</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>prefer-web-inf-classes</tt> under <tt>container-descriptor</tt></td>
       <td align="left" valign="top"><tt>delegate</tt> attribute of <tt>class-loader</tt> <tt>glassfish-web.xml</tt> equivalent</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>context-root</tt></td>
       <td align="left" valign="top"><tt>context-root</tt> <tt>glassfish-web.xml</tt> equivalent</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>cookies-enabled</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top">Servlet 3.0</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>cookie-name</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top">Servlet 3.0</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>cookie-path</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top">Servlet 3.0</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>cookie-domain</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top">Servlet 3.0</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>cookie-comment</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top">Servlet 3.0</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>cookie-secure</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top">Servlet 3.0</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>cookie-max-age-secs</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top">Servlet 3.0</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>cookie-http-only</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top">Servlet 3.0</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>url-rewriting-enabled</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top">Servlet 3.0</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>persistent-store-cookie-name</tt> under <tt>session-descriptor</tt></td>
       <td align="left" valign="top">Cookie-based persistence is supported</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>keepgenerated</tt> under <tt>jsp-descriptor</tt></td>
       <td align="left" valign="top"><i>keepgenerated</i> init parameter of <tt>JspServlet</tt></td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>working-dir</tt> under <tt>jsp-descriptor</tt></td>
       <td align="left" valign="top"><i>scratchdir</i> init parameter of <tt>JspServlet</tt></td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>compress-html-template</tt> under <tt>jsp-descriptor</tt></td>
       <td align="left" valign="top"><i>trimSpaces</i> init parameter of <tt>JspServlet</tt></td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>index-directory-enabled</tt> under <tt>container-descriptor</tt></td>
       <td align="left" valign="top"><i>listings</i> init parameter of <tt>DefaultServlet</tt></td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>index-directory-sort-by</tt> under <tt>container-descriptor</tt></td>
       <td align="left" valign="top"><i>sortedBy</i> init parameter of <tt>DefaultServlet</tt></td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>save-sessions-enabled</tt> under <tt>container-descriptor</tt></td>
       <td align="left" valign="top">Same as <tt>asadmin redeploy</tt> <tt>--keepstate=true</tt> or <tt>keep-state</tt> in <tt>glassfish-web.xml</tt></td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>run-as-principal-name</tt> under <tt>servlet-descriptor</tt></td>
       <td align="left" valign="top"><tt>principal-name</tt> under <tt>servlet</tt> <tt>glassfish-web.xml</tt> equivalent</td>
      </tr>
     </tbody>
    </table></td>
  </tr>
 </tbody>
</table>
<br>
<br>
<table border="0" cellpadding="0" cellspacing="0" datatable="0">
 <tbody>
  <tr>
   <td>
    <table border="0" cellpadding="0" cellspacing="1">
     <colgroup>
      <col width="50%">
     </colgroup>
     <colgroup>
      <col width="50%">
     </colgroup>
     <tbody>
      <tr bgcolor="#C0C0C0">
       <th align="left" scope="col" valign="top"><tt>weblogic-webservices.xml</tt> Element Name</th>
       <th align="left" class="columncaption" scope="col" valign="top">GlassFish Server Support</th>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>webservice-type</tt></td>
       <td align="left" valign="top">Possible values are <tt>JAXRPC</tt> or <tt>JAXWS</tt>. GlassFish Server does not support JAX-RPC web services with JSR 181 annotations. The use of this element is limited, because the container can find out if the type is JAX-WS or JAX-RPC based on presence of JSR 181 annotations.</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>wsdl-publish-file</tt></td>
       <td align="left" valign="top">Same as <tt>wsdl-publish-location</tt> in <tt>glassfish-web.xml</tt></td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>service-endpoint-address</tt></td>
       <td align="left" valign="top">Similar to <tt>endpoint-address-uri</tt> in <tt>glassfish-web.xml</tt>, except that <tt>webservice-contextpath</tt> and <tt>webservice-serviceuri</tt> are specified separately</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>j2ee:login-config</tt></td>
       <td align="left" valign="top">Same as <tt>login-config</tt> in <tt>glassfish-web.xml</tt></td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>j2ee:transport-guarantee</tt></td>
       <td align="left" valign="top">Same as <tt>transport-guarantee</tt> in <tt>glassfish-web.xml</tt></td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>exposed</tt> under <tt>wsdl</tt></td>
       <td align="left" valign="top">Accepts <tt>true</tt> or <tt>false</tt>, defaults to <tt>true</tt>. Controls the publishing of WSDL to clients.</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>stream-attachments</tt></td>
       <td align="left" valign="top">Accepts <tt>true</tt> or <tt>false</tt>, defaults to <tt>true</tt>. Only for JAX-WS web services. Configures the JAX-WS runtime to send attachments in streaming fashion.</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>validate-request</tt></td>
       <td align="left" valign="top">Accepts <tt>true</tt> or <tt>false</tt>, defaults to <tt>false</tt>. Only for JAX-WS web services. Configures the JAX-WS runtime to validate that request messages are as the WSDL definitions specify.</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>http-response-buffersize</tt></td>
       <td align="left" valign="top">Property of <tt>ReliabilityMessagingFeature</tt> configuration, similar to <tt>ReliableMessagingFeature.setDestinationBufferQuota()</tt></td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>reliability-config</tt></td>
       <td align="left" valign="top">Partially supported. Subelements map to Metro's <tt>ReliabilityMessagingFeature</tt>.</td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>inactivity-timeout</tt> under <tt>reliability-config</tt></td>
       <td align="left" valign="top">Maps to <tt>ReliableMessagingFeature. getSequenceInactivityTimeout()</tt></td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>base-retransmission-interval</tt> under <tt>reliability-config</tt></td>
       <td align="left" valign="top">Maps to <tt>ReliableMessagingFeature. getMessageRetransmissionInterval()</tt></td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>retransmission-exponential-backoff</tt> under <tt>reliability-config</tt></td>
       <td align="left" valign="top">Maps to <tt>ReliableMessagingFeature. getRetransmissionBackoffAlgorithm()</tt>. Returns <tt>enum</tt> values, one of them is <tt>exponential</tt>.</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>acknowledgement-interval</tt> under <tt>reliability-config</tt></td>
       <td align="left" valign="top">Maps to <tt>ReliableMessagingFeature. getAcknowledgementTransmissionInterval()</tt></td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>sequence-expiration</tt> under <tt>reliability-config</tt></td>
       <td align="left" valign="top">Maps to <tt>ReliableMessagingFeature. getSequenceInactivityTimeout()</tt>. In WebLogic Server this value applies regardless of activity. In Metro it applies only to inactive sequences.</td>
      </tr>
      <tr bgcolor="#C0C0C0">
       <td align="left" scope="row" valign="top"><tt>buffer-retry-count</tt> under <tt>reliability-config</tt></td>
       <td align="left" valign="top">Maps to <tt>ReliableMessagingFeature. getMaxMessageRetransmissionCount()</tt></td>
      </tr>
      <tr>
       <td align="left" scope="row" valign="top"><tt>buffer-retry-delay</tt> under <tt>reliability-config</tt></td>
       <td align="left" valign="top">Maps to <tt>ReliableMessagingFeature. getMessageRetransmissionInterval()</tt></td>
      </tr>
     </tbody>
    </table></td>
  </tr>
 </tbody>
</table>
<br>
<br><b>But for what is all that good for?</b>
<br>
 Good question. There are some possible interpretations for what is happening.
<br>
 1) GlassFish could be positioned as a certified, lightweight development platform for Oracle's FMW stack based on WebLogic server. If this would be the main goal, I wouldn't expect WebLogic to understand any of the GF DDs but GF knowing about all&nbsp;tweaks&nbsp;and settings of WLS.
<br>
 2) Easy re-deployment of GF apps on WLS. This is what you find on the official launch slides. If you are running GF and you need to scale up to WLS you have a more easier migration path.
<br>
 3) Both teams are trying to get hands on the concepts and switches of the other side. The GF roadmaps from the past highlight a "Common Server Platform" for WLS and GF. So knowing each other could be an easy and obvious first step for the teams.
<br>
 As always, a bit of everything might be true. So there is nothing else left for now than simply to be happy about and watch how both excellent servers come closer together and to be open for future possibilities.
<br>
<br>
<br>
<br>
---
title: "WebLogic 10.3.4.0 + OEPE + Maven + Primefaces 2.2RC"
date: 2011-01-21 13:06:00 +0000
layout: post
tags: ["weblogic", "primefaces", "OEPE"]
slug: "weblogic-10340-oepe-maven-primefaces"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2011/01/weblogic-10340-oepe-maven-primefaces.html
---

The new WebLogic is there since a few days and we are right in front of another new release of another component: Primefaces 2.2 has RC state at the moment and we are all waiting for a final release happening hopefully the next time. If you are willing to give the existing RC a test drive you can follow this short blog in which I try to shortly summarize the steps necessary to take to get one of my favorite development setups up an running with WebLogic and Primefaces.
<br>
<br><b>What you need</b>
<br>
 You need the latest WebLogic 10.3.4.0, Maven 3.0 and Primefaces 2.2RC. The first two get installed by the WebLogic installer and you can head over to install the <a href="http://m2eclipse.sonatype.org/installing-m2eclipse.html">m2eclipse</a> plugin next. You also should install m2eclipse Extras which makes live a lot easier if you are working with web apps and maven. Everything is finished by restarting the OEPE. Now go and grep <a href="">Apache Maven</a> and install it to a location of your choice.
<br>
<br><b>Creating and configuring your project</b>
<br>
 Fire up the OEPE. Go to preferences and add your Maven installation to the configured Maven / Installations. Right click "New &gt; Other", select "Maven &gt; Maven Project". Click "Next &gt;". Select the "org.apache.maven.archetypes.maven-archetype-webapp". Click "Next". Enter a valid Group Id, Artifact Id and a package. Click "Finish". You now have a Dynamic Webproject in your Workspace (thanks to m2eclipse Extras). Open the pom and add two repositories:
<br><code><br>
  &lt;repositories&gt;<br>
  &lt;repository&gt;<br>
  &lt;id&gt;prime-repo&lt;/id&gt;<br>
  &lt;name&gt;Prime Technology Maven Repository&lt;/name&gt;<br>
  &lt;url&gt;http://repository.prime.com.tr&lt;/url&gt;<br>
  &lt;layout&gt;default&lt;/layout&gt;<br>
  &lt;/repository&gt;<br>
  &lt;repository&gt;<br>
  &lt;id&gt;maven2-repository.dev.java.net&lt;/id&gt;<br>
  &lt;name&gt;Java.net Repository for Maven&lt;/name&gt;<br>
  &lt;url&gt;http://download.java.net/maven/2/&lt;/url&gt;<br>
  &lt;layout&gt;default&lt;/layout&gt;<br>
  &lt;/repository&gt;<br>
  &lt;/repositories&gt;<br></code>
<br>
 After that you have to add the following two dependencies:
<br><code><br>
  &lt;dependency&gt;<br>
  &lt;groupId&gt;org.primefaces&lt;/groupId&gt;<br>
  &lt;artifactId&gt;primefaces&lt;/artifactId&gt;<br>
  &lt;version&gt;2.2.RC2&lt;/version&gt;<br>
  &lt;/dependency&gt;<br>
  &lt;dependency&gt;<br>
  &lt;groupId&gt;javaee&lt;/groupId&gt;<br>
  &lt;artifactId&gt;javaee-api&lt;/artifactId&gt;<br>
  &lt;version&gt;5&lt;/version&gt;<br>
  &lt;scope&gt;provided&lt;/scope&gt;<br>
  &lt;/dependency&gt;<br></code>.
<br>
 Done with maven. Next is to configure the webapplication. Oben the WEB-INF/web.xml and add the jsf servlet:
<br><code><br>
  &lt;param-value&gt;Development&lt;/param-value&gt;<br>
  &lt;/context-param&gt;<br>
  &lt;servlet&gt;<br>
  &lt;servlet-name&gt;Faces Servlet&lt;/servlet-name&gt;<br>
  &lt;servlet-class&gt;javax.faces.webapp.FacesServlet&lt;/servlet-class&gt;<br>
  &lt;load-on-startup&gt;1&lt;/load-on-startup&gt;<br>
  &lt;/servlet&gt;<br>
  &lt;servlet-mapping&gt;<br>
  &lt;servlet-name&gt;Faces Servlet&lt;/servlet-name&gt;<br>
  &lt;url-pattern&gt;/faces/*&lt;/url-pattern&gt;<br>
  &lt;/servlet-mapping&gt;<br></code>
<br>
 Now create a WEB-INF/weblogic.xml and add the JSF 2.0 library references to your project.
<br><code><br>
  &lt;library-ref&gt;<br>
  &lt;library-name&gt;jsf&lt;/library-name&gt;<br>
  &lt;specification-version&gt;2.0&lt;/specification-version&gt;<br>
  &lt;implementation-version&gt;1.0.0.0_2-0-2&lt;/implementation-version&gt;<br>
  &lt;exact-match&gt;false&lt;/exact-match&gt; <br>
  &lt;/library-ref&gt;<br></code>
<br>
<br><b>Creating and configuring your WebLogic Domain</b>
<br>
 Go ahead and configure your local WebLogic Server instance with OEPE. Goto "Server View" and select "New". Select the Oracle WebLogic Server 11gR1 PatchSet 3 server adapter and select or add a Server runtime environment. Click "Next &gt;". Select an existing or create a new Domain Directory. Right click the server entry and "Start" the server. Visit http://localhost:7001/console, select "Deployments &gt; Install". Navigate to WL_HOME/wlserver_10.3/common/deployable-libraries and select the jsf-2.0.war. Click "Next". Accept the "Install this deployment as a library" selection and click "Next". review the settings (nothing to do here for us today) and click "Finish". Now you should see a jsf(2.0,1.0.0.0_2-0-2) deployed on your instance. Fine. Done.
<br>
<br><b>Writing some code</b>
<br>
 Create an index.xhtml file in your WebContent folder and add some functionality:
<br><code><br>
  &lt;?xml version='1.0' encoding='UTF-8' ?&gt;<br>
  &lt;!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"&gt;<br>
  &lt;html xmlns="http://www.w3.org/1999/xhtml"<br>
  xmlns:p="http://primefaces.prime.com.tr/ui"<br>
  xmlns:h="http://java.sun.com/jsf/html"&gt;<br>
  &lt;h:head&gt;<br>
  &lt;title&gt;Facelet Title&lt;/title&gt;<br>
  &lt;/h:head&gt;<br>
  &lt;h:body&gt;<br>
  &lt;p:panel header="Hello From"&gt;<br>
  &lt;p:button outcome="test" value="Next"/&gt;<br>
  &lt;/p:panel&gt;<br>
  &lt;/h:body&gt;<br>
  &lt;/html&gt;<br></code>
<br>
 Fine. Don't forget to add another page called test.xhtml with some more content, if you are copying this example :)
<br>
 Now build and install your application. If you are using the <a href="http://blog.eisele.net/2011/01/using-and-installing-weblogic-10340.html">WebLogic 10.3.4.0 Maven Plug-In for Deployment</a> simply type <code>mvn weblogic:deploy</code> to get your primefaces app up and running (or better use m2eclipse ;) and define a new Run as... target).
<br>
 And that's what the nice stuff is looking like:
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="primefaces.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="149" src="primefaces.png" width="320"></a>
</div>
<br><b>What's left?</b>
<br>
 If you are running without any further changes, you probably see some warnings in your logfiles about missing mime-types like the following. 
<br><code>&lt;Notice&gt; &lt;StdErr&gt; &lt;BEA-000000&gt;: com.sun.faces.context.ExternalContextImpl getMimeType<br>
  WARNING: JSF1091: No mime type could be found for file skins/sam/images/ui-default.png. To <br>
  resolve this, add a mime-type mapping to the applications web.xml.</code>
<br>
<br>
 In order to avoid this, you have to add the mime type mapping to your web.xml configuration.
<br><code><br>
  &lt;mime-mapping&gt;<br>
  &lt;extension&gt;png&lt;/extension&gt;<br>
  &lt;mime-type&gt;image/png&lt;/mime-type&gt;<br>
  &lt;/mime-mapping&gt;<br></code>
<br>
<br>
 Thats's all. Now your are running the latest Primefaces 2.2RC on you JSF 2.0 based WebLogic Server. Congratulations!
<br>
 For those of you, interested: Here is the complete <a href="http://www.eisele.net/jar/primefaces.zip">primefaces.zip (4KB)</a> with the configuration. Have fun ...
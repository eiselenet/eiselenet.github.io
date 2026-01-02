---
title: "WLS 10.3.2.0, JScaLite TechPreview .. examples ... broken :("
date: 2009-11-13 06:09:00 +0000
layout: post
tags: ["sca", "JScaLite", "oracle", "11gR1", "weblogic server"]
slug: "wls-10320-jscalite-techpreview-examples"
link: "2009/11/wls-10320-jscalite-techpreview-examples.html"
url: /2009/11/wls-10320-jscalite-techpreview-examples.html
---

Released only yesterday and now on my harddrive. The WLS 10.3.2.0 (Oracle WebLogic Server 11g R1 Patch Set 1). 
<br>
 Beside the fact, that the release notes are missing and the documentation is not updated to the latest version, everything looks quite the same. 
<br>
 There was time for me to look at the Technical Preview Parts of the Oracle Weblogic SCA implementation called JScaLite.
<br>
 As a more or less frequent reader of my blog, you know, that I like to work with the provided samples. They are basically a good start for understanding new features and provide a basic skeletton for your own trials.
<br>
 Shipped with the WLS 10.3.2.0 you can find a sca example. It is based on a shopping cart application. The following figure (taken from the examples documentation) explains the high level architecture:
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/instructions_clip_image001-737174.gif" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" src="http://www.eisele.net/blog/uploaded_images/instructions_clip_image001-737173.gif"></a>
<br>
<br>
 This example demos the most important features of JscaLite container. The demoed features include:
<br>
<br>
 •EJB 3.0 Service Bindings 
<br>
 •Web Service Bindings
<br>
 •EJB 2.0 Reference Bindings
<br>
 •EJB 3.0 Reference Bindings
<br>
 •Web Service Reference Bindings
<br>
 •Default Binding
<br>
 •JSCA Binding
<br>
 •Local Reference Resolution (References to services that are part of the same JSca application)
<br>
 •JSCA application deployment / undeployment
<br>
<br>
 Ok. Let's start over again. First step is to install the package. After you downloaded the 815 MB installer (windows) you are guided through the installation process, as usual. After that, you create your domain and start adjusting the example.properties (%SERVER_HOME%\wlserver_10.3\samples\server\examples\src).
<br>
 Now you should setup your environment variables (setDomainEnv.cmd is a good start) and start building the examples (ant build).
<br>
 Up to this, everything works fine. If you try to deploy (ant deploy) you come across some problems:
<br>
 - the build.xml does not make use of the example.properties =&gt; change it
<br>
 - the deployable library for sca (weblogic-sca-1.0.war) is not deployed =&gt; deploy it
<br>
 - the weblogic.xml dd does not point to the correct library =&gt; change it
<br>
<br>
 So far, so good :) Anyhow .. this is where the little journey ends. From now on, I did not manage to fix the remaining issues.
<br>
<br>
 you get a 
<br><code>java.io.FileNotFoundException: class path resource [META-INF/spring-sca.xsd] cannot be opened because it does not exist<br></code>
<br>
 if you try to deploy it. Even providing the file within the WEB-INF/classes/META-INF/ this does not solve the problems. A redeployment, ended up with a 
<br>
<br><code><br>
  org.xml.sax.SAXParseException: cvc-complex-type.2.1: Element 'sca:service' must have no character or element information item [children], because the type's content type is empty.<br></code>
<br>
<br>
 Played around a bit with different approaches and finally gave up on this. If anybody got this working ... let me know.
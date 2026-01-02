---
title: "WebLogic Server 10.3.3.0 released. It now has JSF 2.0 support!!"
date: 2010-04-28 06:30:00 +0000
layout: post
tags: ["howto", "weblogic server", "jsf 2.0"]
slug: "weblogic-server-10330-released-it-now"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2010/04/weblogic-server-10330-released-it-now.html
---

Believe it or not. Here it is. WebLogic Server finaly supports JSF 2.0 with it's latest release 10.3.3.0.
<br>
 And here is the short howto:
<br>
<br>
 - Download and install one of the latest <a href="http://www.oracle.com/technology/software/products/ias/htdocs/wls_main.html" target="_blank">Oracle WebLogic Server 11g Rel 1 (10.3.3) Installers</a> from OTN. (Give the ZIP Installer a try. Aweseome lightweight!)
<br>
 - Create a new sample domain (call it whatever you want) and start the admin server
<br>
 - Open the administration console (http://localhost:7001/console/)
<br>
 - deploy the JSF 2.0 library (Deployments - Install - wlserver_10.3\common\deployable-libraries\jsf-2.0.war
<br>
 - Find your favorite JSF 2.0 sample (I'll take the guessNumber thing from the mojarra-2.0.2 distribution)
<br>
 - Add a weblogic.xml file to the WEB-INF/ folder with the following content:
<br>
 &lt;?xml version="1.0" encoding="UTF-8"?&gt;
<br>
 &lt;weblogic-web-app&gt;
<br>
 &lt;library-ref&gt;
<br>
 &lt;library-name&gt;jsf&lt;/library-name&gt;
<br>
 &lt;specification-version&gt;2.0&lt;/specification-version&gt;
<br>
 &lt;implementation-version&gt;1.0.0.0_2-0-2&lt;/implementation-version&gt;
<br>
 &lt;exact-match&gt;true&lt;/exact-match&gt;
<br>
 &lt;/library-ref&gt;
<br>
 &lt;/weblogic-web-app&gt;
<br>
<br>
 - Package the guessNumber app 
<br>
 - Deploy the app to the WebLogic server 
<br>
 - give it a try: http://localhost:7001/guessNumber
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="jsf2.0wls.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="200" src="jsf2.0wls.png" width="320"></a>
</div>
<br>
 As you can see, the new JSF 2.0 features are available :) great work! 
<br>
 And the best of all, this is not even explicitly mentioned in the "what's new documentation for WLS" :) A single line indicates JSF support for 2.0, 1.2, 1.1.
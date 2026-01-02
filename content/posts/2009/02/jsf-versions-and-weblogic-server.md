---
title: "JSF Versions and Weblogic Server"
date: 2009-02-27 10:46:00 +0000
layout: post
tags: ["oracle", "jsf", "weblogic server"]
slug: "jsf-versions-and-weblogic-server"
link: "2009/02/jsf-versions-and-weblogic-server.html"
url: /2009/02/jsf-versions-and-weblogic-server.html
---

We were playing with the JSF containers in WLS and stumbled upon some problems. I'll try to summarize the findings in one place here. Hope, this is helpfull for you.
<br>
<br>
If you create a new Weblogic Server Domain it does not come with any JSF libraries active. If your try to ship your webapplication with a separate JSF implementation, you are free to do this. Keep in mind, that the dependency injection mechanisms does not work with managed beans, if you do not use any of the WLS provided JSF containers.
<br>
<br>
Declaring something like this:
<br>
@EJB
<br>
private MySession session;
<br>
<br>
will lead to a java.lang.NullPointerException because of the missing depencency injection mechanisms. Would love to know any details about this. Maybe I'll find out more in the future..
<br>
<br>
<br>
The following ones are available with WLS 10gR3:
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<table>
 <tbody>
  <tr>
   <td><b>LIBRARY</b></td>
   <td><b>NAME</b></td>
   <td><b>IMPLEMENTATION VERSION</b></td>
  </tr>
  <tr>
   <td>jsf</td>
   <td>Sun Reference</td>
   <td>1.2-b20-FCS - or 1_2_03-rc2</td>
  </tr>
  <tr>
   <td>jsf-ri</td>
   <td>Sun Reference</td>
   <td>1.1.1</td>
  </tr>
  <tr>
   <td>jsf-myfaces</td>
   <td>MyFaces</td>
   <td>1.1.3</td>
  </tr>
  <tr>
   <td>jsf-myfaces</td>
   <td>MyFaces</td>
   <td>1.1.1</td>
  </tr>
 </tbody>
</table>
<br>
<br>
If you want to use any of them in your own webapplikation, you have to deploy the libraries and refer to them with the weblogic.xml depolyment descriptor:
<br>
<br>
<ol>
 <br>
 <li>In the administration console navigate to the Deployments page using the left hand menu.</li>
 <br>
 <li>Then select the Install button at the top of the deployments table</li>
 <br>
 <li>Using the directory browser navigate to the $BEA_HOME/wlserver_10.3/common/deployable-libraries directory. Then select the apropriate war (e.g. jsf-1.2.war), and click the Next button.</li>
 <br>
 <li>Make sure that the Install this deployment as a library is selected. Click the Next button on the Install Application Assistant page.</li>
 <br>
 <li>Click the Next button on the Optional Settings page.</li>
 <br>
 <li>Make sure that the Yes, take me to the deployment's configuration screen. is selected. Click the Finish button on the Review your choices and click Finish page.</li>
 <br>
 <li>On the Settings for jsf(1.2,1.2.3.1) page set the Deployment Order to 99 so that it is deployed prior to auto deployed applications. Then click the Save button.<br></li>
 <br>
</ol>
<br>
<br>
After this, you have to ensure that your application uses the right libraries by entering the needed parts into the deployment descriptor (weblogic-application.xml for an ear and weblogic.xml for any war file)
<br>
<br>
 &lt;library-ref&gt;
<br>
 &lt;library-name&gt;jsf&lt;/library-name&gt;
<br>
 &lt;specification-version&gt;1.2&lt;/specification-version&gt;
<br>
 &lt;implementation-version&gt;1.2&lt;/implementation-version&gt;
<br>
 &lt;exact-match&gt;false&lt;/exact-match&gt;
<br>
 &lt;/library-ref&gt;
<br>
<br>
If you are wondering, if there is a posiblity to just deploy the .jar File as a Library: theoreticaly yes :) But: There is a restriction in place that prevents webarchives from accessing deployed .jar libraries. You can only access .jar libraries from ear or ejb.jar files. Webapplications are only allowed to access .war libraries. If you try it, you will get an error like this:
<br>
<br>
<blockquote>
 "xxxx" library reference is not allowed in the weblogic.xml file. 
 <br>
 Only WAR libraries are allowed. 
 <br>
 Unknown WebLogic Shared Library Framework Validation Problem
 <br>
</blockquote>
<br>
<br>
<ul>
 <br>
 <dl>
  Links:
 </dl>
 <br>
 <li><a href="http://edocs.bea.com/wls/docs103/webapp/configurejsfandjtsl.html" target="_blank">Configure JSF/JSTL in WLS</a></li>
 <br>
 <li><a href="http://download.oracle.com/technology/products/weblogic/portal/weblogic-portal-jsf-whitepaper.pdf" target="_blank">Using JSF in Weblogic Portal</a></li>
 <br>
 <li><a href="http://edocs.bea.com/wls/docs103/programming/libraries.html" target="_blank">Custom shared libraries</a></li>
 <br>
</ul>
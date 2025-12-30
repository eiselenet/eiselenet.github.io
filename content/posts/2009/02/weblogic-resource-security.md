---
title: "WebLogic Resource Security"
date: 2009-02-20 07:48:00 +0000
layout: post
tags: ["oracle", "Security Realm", "weblogic server"]
slug: "2009-02-20-weblogic-resource-security"
url: /2009/02/weblogic-resource-security.html
---

Yesterday a coworker dropped in and asked me about the Weblogic security concept. He was trying to deploy the JEE example applikation Dukes Bank on the 10.x version and got in trouble with the changed resource protection.
<br>
The webapplication has a single web.xml DD and a separate weblogic.xml DD is missing. If you have something like this in your web.xml
<br>
<br>
&lt;security-constraint&gt;
<br>
 &lt;web-resource-collection&gt;
<br>
 &lt;web-resource-name&gt;Success&lt;/web-resource-name&gt;
<br>
 &lt;url-pattern&gt;/welcome.jsp&lt;/url-pattern&gt;
<br>
 &lt;http-method&gt;GET&lt;/http-method&gt;
<br>
 &lt;http-method&gt;POST&lt;/http-method&gt;
<br>
 &lt;/web-resource-collection&gt;
<br>
 &lt;auth-constraint&gt;
<br>
 &lt;role-name&gt;webuser&lt;/role-name&gt;
<br>
 &lt;/auth-constraint&gt;
<br>
 &lt;/security-constraint&gt;
<br>
 &lt;login-config&gt;
<br>
 &lt;auth-method&gt;BASIC&lt;/auth-method&gt;
<br>
 &lt;realm-name&gt;default&lt;/realm-name&gt;
<br>
 &lt;/login-config&gt;
<br>
 &lt;security-role&gt;
<br>
 &lt;role-name&gt;webuser&lt;/role-name&gt;
<br>
 &lt;/security-role&gt;
<br>
<br>
Than you need to tweek the default behaviour of the Weblogic Server to get this up and running. You have <a href="http://download.oracle.com/docs/cd/E12840_01/wls/docs103/secwlres/secejbwar.html" target="_blank">different options in wls to secure your resources</a>:
<br>
<br>
<br>
<ul>
 <br>
 <li>Deployment Descriptor Only (Java EE standard)</li>
 <br>
 <li>Custom Roles</li>
 <br>
 <li>Custom Roles and Policies</li>
 <br>
 <li>Advanced</li>
 <br>
</ul>
<br>
<br>
If you choose the first option, you need a weblogic.xml DD to define the roles to principle/group mapping. 
<br>
If you choose the Custom Roles you can configure the role mappings from a role mapping provider that you configure for the security realm. You can use the Administration Console to configure the provider. Any role mappings in the deployment descriptors are ignored. The model uses the policies that are defined in the web.xml and ejb-jar.xml deployment descriptors. 
<br>
If you choose Custom Roles and Rolicies, you configure a role mapping provider and an authorization provider for your security realm. You can use the Administration Console to configure the providers. Any role mappings or policies in the deployment descriptors are ignored. 
<br>
If you want to import the basic information from the DD and configure Roles and Policies on this basis, you need to choose Advanced.
<br>
<br>
First step is to configure your realm:
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/realmsettings-785839.png"><img style="display:block; margin:0px auto 10px; text-align:center;cursor:pointer; cursor:hand;width: 200px; height: 101px;" src="http://www.eisele.net/blog/uploaded_images/realmsettings-785836.png" border="0" alt=""></a>
<br>
<br>
Don't forget to 
<br>
a) Disable the Combined Role Mapping and 
<br>
b) change the "Check Roles and Policies" to "All Web applications and EJBs". If you are finished, you defenetely need a server restart. Even if the wls is happy and working. 
<br>
<br>
After this, you have to install your deployment. 
<br>
Carefull: You can override the realm settings during deployment time. So, don't change anything here.
<br>
If the deployment is installed, you can browse with the admin console to your deployment and have a look at the security settings. In this case, you can see, that the security2.war has a resource Role "webuser" assigned to the URL Pattern "/".
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/security2-737679.png"><img style="display:block; margin:0px auto 10px; text-align:center;cursor:pointer; cursor:hand;width: 200px; height: 146px;" src="http://www.eisele.net/blog/uploaded_images/security2-737674.png" border="0" alt=""></a>
<br>
<br>
The only thing left to do, is to create the needed user or group. The role condition inported from the DD states, that a "webuser" could be a "Group : webuser" or a "User : webuser". Therefore you have to go back to your realm and add whatever fits your plans. 
<br>
a) a User with the name "webuser" or 
<br>
b) a Group with the name "webuser" and don't forget to assign some users to the group
<br>
<br>
And after this, you are done with the show and see the welcome screen:
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/welcome-751371.png"><img style="display:block; margin:0px auto 10px; text-align:center;cursor:pointer; cursor:hand;width: 200px; height: 135px;" src="http://www.eisele.net/blog/uploaded_images/welcome-751368.png" border="0" alt=""></a>
<br>
<br>
If you like, you can <a href="/jar/security2.ear">download the sample ear</a> file for your configuration tests.
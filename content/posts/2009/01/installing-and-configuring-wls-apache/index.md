---
title: "Installing and Configuring the WLS Apache Plugin"
date: 2009-01-11 08:44:00 +0000
layout: post
tags: ["apache", "oracle", "plugin", "weblogic server"]
slug: "installing-and-configuring-wls-apache"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2009/01/installing-and-configuring-wls-apache.html
---

There were some questions recently reguarding the Weblogic Apache plugin. I just want to show you in this quick example, how to configure and test the plugin in some easy steps:
<br>
<br>
1) Download the Apache plugins for WLS from the <a href="http://www.oracle.com/technology/products/weblogic/index.html" target="_blank">Oracle OTN website</a>
<br>
2) Identify the right plugin for your <a href="http://e-docs.bea.com/wls/docs90/plugins/apache.html#128335" target="_blank">platform</a> and <a href="http://e-docs.bea.com/wls/docs90/plugins/apache.html#126678" target="_blank">apache </a>version.
<br>
You can decide to use a regular strength or a 128-bit encryption version of the modules.
<br>
3) Install the right plugin to your apache folder (<srv_home>
 /modules)
 <br>
 For this example I use the server103_apacheplugins.zip\win\32\mod_wl_20.so
 <br>
 4) Configure your apache to use it by editing httpd.conf and add the line :
 <br>
 LoadModule weblogic_module modules/mod_wl_20.so
 <br>
 5) Define any additional parameters for the Apache HTTP Server Plug-In.
 <br>
 The Apache HTTP Server Plug-In recognizes the parameters listed in 
 <a href="http://e-docs.bea.com/wls/docs90/plugins/plugin_params.html#1143055" target="_blank">General Parameters for Web Server Plug-Ins</a>. To modify the behavior of your Apache HTTP Server Plug-In, define these parameters:
 <br>
 - In a Location block, for parameters that apply to proxying by path, or
 <br>
 - In an IfModule block, for parameters that apply to proxying by MIME type.
 <br>
 A simple example would be:
 <br>
 <br>
 &lt;IfModule mod_weblogic.c&gt;
 <br>
  WebLogicHost localhost
 <br>
  WebLogicPort 7001
 <br>
  MatchExpression *.jsp
 <br>
 &lt;/IfModule&gt;
 <br>
 <br>
 6) Start/Restart the Apache server
 <br>
 7) Start your Weblogic Server
 <br>
 8) Test the plugin with an appropriate url (for the above example: 
 <br>
 http://myapache.com/test.jsp
 <br>
 <br>
 Additional features:
 <br>
 1) Custom error-pages for the plugin. 
 <br>
 If something goes wrong during the wls server or cluster request, you can redirect to a custom error page using this configuration directive:
 <br>
 &lt;IfModule mod_weblogic.c&gt;
 <br>
  WebLogicHost localhost
 <br>
  WebLogicPort 7001
 <br>
 <i>ErrorPage http://myerrorpage1.mydomain.com</i>
 <br>
  MatchExpression *.jsp
 <br>
 &lt;/IfModule&gt;
 <br>
 <br>
 2) Turn debugging on:
 <br>
 You can turn on debugging for the plugin using this directive:
 <br>
 &lt;IfModule mod_weblogic.c&gt;
 <br>
  WebLogicHost localhost
 <br>
  WebLogicPort 7001
 <br>
 <i> Debug ON<br>
   WLLogFile c:/tmp/global_proxy.log </i>
 <br>
 MatchExpression *.jsp
 <br>
 &lt;/IfModule&gt;
 <br>
 <br>
 3) Turn on debug config info:
 <br>
 In addition to the debug log you can also turn on a separate debug configuration information using this switch:
 <br>
 &lt;IfModule mod_weblogic.c&gt;
 <br>
  WebLogicHost localhost
 <br>
  WebLogicPort 7001
 <br>
 <i> Debug ON<br>
   DebugConfigInfo On<br></i>
 <br>
 WLLogFile c:/tmp/global_proxy.log 
 <br>
 MatchExpression *.jsp
 <br>
 &lt;/IfModule&gt;
 <br>
 <br>
 If you enable this switch, you can access the configuration information of the plugin with 
 <br>
 <br>
 http://myapache.com/test.jsp?__WebLogicBridgeConfig
 <br>
 <br>
 4) You can change the name of the session cookie used by weblogic server:
 <br>
 <br>
 &lt;IfModule mod_weblogic.c&gt;
 <br>
  WebLogicHost localhost
 <br>
  WebLogicPort 7001
 <br>
 <i>CookieName MYCOOKIENAME</i>
 <br>
  MatchExpression *.jsp
 <br>
 &lt;/IfModule&gt;
 <br>
 <br>
 <style type="text/css" media="screen"><br /> .yiggbutton \{<br /> float:left;<br /> padding:3px 5px 5px 5px;<br /> \}<br /> </style>
 <br>
 <br>
 <div class="yiggbutton">
  <br>
  <script><br /> yigg_url = \'http://www.eisele.net/blog/2009/01/installing-and-configuring-wls-apache.html\';<br /> </script>
  <br>
  <script src="http://static.yigg.de/v6/js/embed_button.js"></script>
  <br>
 </div>
</srv_home>
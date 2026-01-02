---
title: "Static content serving with WebLogic Server"
date: 2010-07-13 08:21:00 +0000
layout: post
tags: ["weblogic", "static content"]
slug: "static-content-serving-with-weblogic"
link: "2010/07/static-content-serving-with-weblogic.html"
url: /2010/07/static-content-serving-with-weblogic.html
---

If you need to server any kind of static content you always have to decide how to do this. This post describe the different ways of static content serving within a WebLogic server installation and gives you a brief idea where to look at and what your options are.
<br>
<br><b>The problem</b>
<br>
 Imagine your brand new application. It is hype and provides large amounts of video and full quality images to your users. The filesize varies from few kb up to hundreds of megabyte. Imagine you have 1000 different resources available the size quickly rises up to hundreds of gigabyte. If you think about the time it takes to build and copy an application with such an amount of resources, you quickly notice that you are not willing to put them into your archive.war! But what are the options?
<br>
<br><b>Static content through a webserver</b>
<br>
 The most obvious solution would be to skip the static content and leave it's handling to those optimized for it. Apache is the most well known representative from the group of professionals handling static content. You can use the WebLogic Webserver Plugins to decide on redirection rules. The static content will be served from the Apache and the configured dynamic content will be redirected to the WebLogic.
<br>
 A simple example of packaging a separate content archive from your build with maven can be found in an <a href="http://blog.eisele.net/2009/07/generating-static-war-content-archive.html">earlier blogpost of mine</a>.
<br>
<br><i>Adv:</i>
<br>
 - fast content serving
<br>
 - no war bundling 
<br>
 - option to do runtime changes
<br>
<br><i>Disadv:</i>
<br>
 - no direct access from wls
<br>
 - no wls security
<br>
<br><b>Static content as library</b>
<br>
 Next option is to use weblogic shared libraries to bundle the static content. All you have to do is to package a content.war according to the <a href="http://download.oracle.com/docs/cd/E12839_01/web.1111/e13706/libraries.htm#WLPRG325" target="_blank">shared libraries packaging conventions</a> and deploy it to your server. Now you can reference the library from your application and use the contained resources. Read more about a simple but complete example <a href="http://blogs.oracle.com/jamesbayer/2009/11/weblogic_server_shared_librari.html" target="_blank">in this blog post</a>. 
<br>
<br><i>Adv:</i>
<br>
 - simple packaging and deployment
<br>
 - use of all library features (e.g. versioning)
<br>
 - wls security
<br>
<br><i>Disadv:</i>
<br>
 - no direct access from wls
<br>
 - large deployment
<br>
<br><b>Static content as virtual directory</b>
<br>
 There is the weblogic specific web application deployment descriptor (<a href="http://download.oracle.com/docs/cd/E11035_01/wls100/webapp/weblogic_xml.html" target="_blank">weblogic.xml</a>) that enables you to specify so called virtual directories. You can use the virtual-directory-mapping element to specify document roots other than the default document root of the Web application for certain kinds of requests. They do not necessarily have to be within your webpplication. You can specify any kind of directory on your server machine. This could look like this:
<br><code><br>
  &lt;wls:virtual-directory-mapping&gt;<br>
  &lt;wls:local-path&gt;E:/virtualDirectory/content&lt;/wls:local-path&gt;<br>
  &lt;wls:url-pattern&gt;/video/*&lt;/wls:url-pattern&gt;<br>
  &lt;/wls:virtual-directory-mapping&gt;<br></code>
<br>
 The WebLogic Server implementation of virtual directory mapping requires that you have a directory that matches the url-pattern of the mapping. The video example requires that you create a directory named E:/virtualDirectory/content/video.
<br>
<br><i>Adv:</i>
<br>
 - fast content serving
<br>
 - no war bundling 
<br>
 - option to do runtime changes
<br>
 - wls security
<br>
<br><i>Disadv:</i>
<br>
 - 
<br>
<br><b>Static content through a Dispatching Servlet</b>
<br>
 You can of course write your own resources servlet for delivering content, that lie in any filesystem. Of even find some examples on the web (e.g. Spring ResourceServlet). 
<br>
<br><i>Adv:</i>
<br>
 - no war bundling 
<br>
 - option to do runtime changes
<br>
 - wls security
<br>
<br><i>Disadv:</i>
<br>
 - wasting precious resources
<br>
<br><b>Conclusion</b>
<br>
 Which solution to choose highly depends on you infrastructure and requirements. There are plenty of more advanced solutions (e.g. traffic cluster, caching) to optimize static content delivery which are not covered in this article. The most widespread solution to deliver large amounts of static content is the Webserver approach. As the word static denotes, it is all about non frequent changing content. This is nothing that should belong to the application server at all. But if you are forced to deliver through the appserver, you should try to use the most efficient method. I did not do any research on the performance of any of the approaches, but it seems obvious that the build in virutal-directory solution is the fastest one in terms of delivery.
---
title: "Project Avatar - What's in it for Java EE 8?"
date: 2013-12-12 07:00:00 +0000
layout: post
tags: ["JavaEE8", "Avatar", "JavaScript"]
slug: "project-avatar-whats-in-it-for-java-ee-8"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2013/12/project-avatar-whats-in-it-for-java-ee-8.html
---

With Java EE 7 out the door since a while it is already time to start planning for the next revision of the platform which will most likely be called Java EE 8. Oracle set up a&nbsp;<a href="https://www.surveymonkey.com/s/KGV7RWS" target="_blank">community survey</a>&nbsp;with which they would like to get input from the community on the relative importance of some of the features they intend to add. First of all this should encourage the interested reader to participate and&nbsp;<a href="https://www.surveymonkey.com/s/KGV7RWS" target="_blank">take the survey</a>. One particular point in it is all about support for HTML 5 based, dynamic applications. While WebSocket and JSON Processing already made it into Java EE 7 there is another feature particularly in GlassFish and WebLogic (via&nbsp;<a href="https://jersey.java.net/documentation/latest/sse.html" target="_blank">Jersey</a>) at the moment which has some potential to be standardized. The Server Sent Events (SSE). It already is part of the&nbsp;<a href="http://www.w3schools.com/html/html5_serversentevents.asp" target="_blank">HTML5 spec</a>&nbsp;and would be a very good candidate for standardization. But Oracle is thinking even further and is asking if there would be enough support and interest to actually standardize the use of JavaScript on &nbsp;Java EE servers. When this kind of question started to appear at JavaOne 2012 it was basically referred to as&nbsp;<a href="https://groups.google.com/forum/#!topic/nodejs/750fF6ruAdY" target="_blank">Node.jar</a>&nbsp;on&nbsp;<a href="" target="_blank">Nashorn</a>. Since this September we know the real name:&nbsp;<a href="" target="_blank">Project Avatar</a>.
<br>
<br><b>Avatar At A Glance</b>
<br>
 Project Avatar provides a JavaScript services layer zeroed in on supporting REST, WebSockets and Server-Sent Events, and a rich client side framework that assumes very minor JavaScript knowledge. The services side is focused on building data services using JavaScript, while the optional client side is entirely focused on supporting HTML5 and TSA (Thin Server Architecture).
<br>
<br>
<br>
<table align="center" cellpadding="0" cellspacing="0" class="tr-caption-container" style="margin-left: auto; margin-right: auto; text-align: center;">
 <tbody>
  <tr>
   <td><a href="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEglFg-cb0VWZ4BStxn30TRSQT5G_aBTlYUNLQPtG40RwQl1pdzYIbBHVFuV1h4MQZcGlm1zPgiQBxygF5eGQR06f15b5G6dWeUWREu5U8mEgAmv2BehzpNqdl8lKqhIA8DfyrYn7vDeoZY/s1600/avatar-arch.png" imageanchor="1" style="margin-left: auto; margin-right: auto;"><img border="0" height="148" src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEglFg-cb0VWZ4BStxn30TRSQT5G_aBTlYUNLQPtG40RwQl1pdzYIbBHVFuV1h4MQZcGlm1zPgiQBxygF5eGQR06f15b5G6dWeUWREu5U8mEgAmv2BehzpNqdl8lKqhIA8DfyrYn7vDeoZY/s1600/avatar-arch.png" width="320"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="font-size: 13px;">Project Avatar (Source:&nbsp;<a href="https://avatar.java.net/essentials.html" target="_blank">avatar.java.net</a>)</td>
  </tr>
 </tbody>
</table>
<br><b>Thin Server Architecture</b>
<br>
 With the introduction of HTML5, CSS3 and fast JavaScript engines, modern browsers have become a powerful platform. In the advent of the so called single-page application (SPA), also known as single-page interface (SPI) the design of modern user interfaces shifted away from server side generation to web applications or web sites that fits on a single web page with the goal of providing a more fluid user experience akin to a desktop application. An SPA moves logic from the server to the client. This results in the role of the web server evolving into a pure data API or web service.&nbsp;This architectural approach has been coined "Thin Server Architecture" to indicate that complexity moved from the server to the client while reducing overall system complexity.
<br>
<br><b>What is really in Avatar?</b>
<br>
 But Avatar is far more than just a TSA approach. It basically consists of three parts. The foundation is build by the upcoming Java 8 and its included JavaScript implementation called Nashorn. On top of it is the Avatar Runtime including an additional compiler layer and a Servlet API based server. Those abstractions &nbsp;enable it to be hosted on various runtimes.&nbsp;Avatar applications are comprised of either client side ‘views’, server side ‘services’ or both. Importantly, there is no inherent coupling between them which enables views to communicate with existing services and for services to be consumed by any type of client. An application ‘archive’ is deployed on the server and is compiled to generate JavaScript tailored to the application. Generated JavaScript for views handles data-binding with the DOM/UI as well as data-management to remote services and local storage. The Avatar archives are somewhat similar to Java EE web-archives. They consist of a WEB-INF folder, an&nbsp;avatar.properties file in the root and either a views directory and/or a service directory. Both file-system directories or .zip (.war) files are supported by Avatar.
<br>
<br><b>All that is left on the Server - Services</b>
<br>
 A service extends either a REST, WebSocket or Push (SSE) abstraction and it's life-cycle is completely handled by the framework. Service implementations can leverage built-in Node modules as well as most third-party modules. Thanks to Nashorn's support for direct invocation of Java code you can also use most Java libraries directly.
<br>
<br><b>The Client Side - Views</b>
<br>
 A view is the HTML required by the browser. It also contains custom 'data-' HTML extensions and simple JavaScript models. Those views get translated by a (server-side) compiler before sending the complete SPA HTML file including the custom generated JavaScript to the browser.
<br>
<br><b>Putting it all together. A Simple Example.</b>
<br>
 Oracle provides a decent set of examples and documentation for Avatar which basically is enough to get you started. First thing to try it out yourself is a&nbsp;<a href="https://jdk8.java.net/download.html" target="_blank">JDK 8 Early Access Build</a>&nbsp;for your OS. At the time of writing I tried it with the latest Developer Preview b118. After installing you need to get the latest&nbsp;<a href="https://glassfish.java.net/download.html" target="_blank">GlassFish Open Source Edition 4.0</a>&nbsp;server and unzip it into a suitable location. Make sure it runs with the installed JDK 8 by either putting &lt;jdk8&gt;/bin to your PATH environment variable or adding the following entry to the &lt;gf4&gt;/glassfish/config asenv.bat/conf
<br>
<br>
<pre class="prettyprint" style="background-color: white; border: 1px solid rgb(136, 136, 136); color: #222222; font-size: 13px; max-height: 700px; overflow: auto; padding: 10px; width: auto; word-wrap: normal;"><span style="color: black; font-family: 'Times New Roman'; font-size: small; white-space: normal;">set AS_JAVA=&lt;jdk8&gt;</span></pre>
<br>
 Now go ahead to download latest&nbsp;<a href="https://avatar.java.net/download.html" target="_blank">avatar-1.0-ea.zip</a>&nbsp;and expand it to the &lt;gf4&gt;/glassfish/ directory. After that you need to&nbsp;set a AVATAR_HOME environment variable and point it to the &lt;gf4&gt; install directory. And make sure to add %AVATAR_HOME%\glassfish\bin to your PATH environment variable.
<br>
 If all of that is done you can switch to your workspace folder and issue the following command to have Avatar creating a simple example app for you:
<br>
<br>
<pre class="prettyprint" style="background-color: white; border: 1px solid rgb(136, 136, 136); color: #222222; font-size: 13px; max-height: 700px; overflow: auto; padding: 10px; width: auto; word-wrap: normal;"><span style="color: black; font-family: 'Times New Roman'; font-size: small; white-space: normal;">avatar new --example=hello</span></pre>
<div>
 <span style="color: black; font-family: 'Times New Roman'; font-size: small;"><br></span>
</div> It creates a basic "hello" application which only consists of a view. It is a good place to start exploring and you can directly start your Glassfish instance afterwards and deploy the application as you would with every typical Java EE application:
<br>
<br>
<pre class="prettyprint" style="background-color: white; border: 1px solid rgb(136, 136, 136); color: #222222; font-size: 13px; max-height: 700px; overflow: auto; padding: 10px; width: auto; word-wrap: normal;"><div style="color: black; font-family: 'Times New Roman'; font-size: medium; white-space: normal;"> asadmin start-domain</div> <div style="color: black; font-family: 'Times New Roman'; font-size: medium; white-space: normal;"> asadmin deploy hello</div> </pre>
<div>
 <br>
 <table cellpadding="0" cellspacing="0" class="tr-caption-container" style="float: right; margin-left: 1em; text-align: right;">
  <tbody>
   <tr>
    <td style="text-align: center;"><a href="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhmUQ2AnHStDuX3_tdldpKeYhAMrFLsWSFUgNa7FmdU2mMyJwS_Y7ujOiaF-B5e64v1b42_3YvK6jkwnVla14iCOT6YmXlVpFoZZk_RJJqsl72Lio4FM0ZHdXuUYyAjiiennKiKT9kED_Y/s1600/hello-example.PNG" imageanchor="1" style="clear: right; margin-bottom: 1em; margin-left: auto; margin-right: auto;"><img border="0" src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhmUQ2AnHStDuX3_tdldpKeYhAMrFLsWSFUgNa7FmdU2mMyJwS_Y7ujOiaF-B5e64v1b42_3YvK6jkwnVla14iCOT6YmXlVpFoZZk_RJJqsl72Lio4FM0ZHdXuUYyAjiiennKiKT9kED_Y/s1600/hello-example.PNG"></a></td>
   </tr>
   <tr>
    <td class="tr-caption" style="font-size: 13px; text-align: center;">The "hello-example" app.</td>
   </tr>
  </tbody>
 </table> Pointing your browser to&nbsp;<a href="http://localhost:8080/hello" target="_blank">http://localhost:8080/hello</a>&nbsp;will display the application. You can start editing the view located at &lt;workspace&gt;/hello/view/src/hello.html directly. While the server is running Avatar takes care of re-compiling it directly for the next request. This ensures a rapid development. This behavior is controlled by the "debug=true" property in the&nbsp;avatar.properties file and can be altered for productive systems. Further on, setting it to false causes all .js and .css files to be minimized before delivery.
 <br>
  At least if you first download and add the required YUI Compressor (<a href="https://github.com/yui/yuicompressor/releases/download/v2.4.8/yuicompressor-2.4.8.jar" target="_blank">jar download</a>) to Avatar first. Due to license restrictions it hasn't been bundled. Simply put it into &lt;gf4&gt;/glassfish/modules/avatar-ext/lib. You might need to create that directory first.
 <br>
 <br>
  Beside this very simple example without any service at all there are more complex ones available with the distribution at %AVATAR_HOME%/Project-Avatar-examples/. For convenience reasons there is a complete examples.ear available which can be directly deployed and tested. To access the samples simply prefix the example name with "demo-". So the "rest" example can be accessed via&nbsp;<a href="" target="_blank">http://localhost:8080/demo-rest/</a>.
 <br>
 <table cellpadding="0" cellspacing="0" class="tr-caption-container" style="float: right; margin-left: 1em; text-align: right;">
  <tbody>
   <tr>
    <td style="text-align: center;"><a href="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEj4Pa190GiuBfIkof_vEYSG8StQSkuw94JJV5BX9FCWPii1Gtonr6E0IGtIsXBm63Emy2KyuBCjmcpNyZwmkVDGwtuz39rZCPPvAhsAFeIZpMHAGbvEuC0ugN_P_-7a4mE5_kVpHtDtILk/s1600/todoapp.PNG" imageanchor="1" style="clear: right; margin-bottom: 1em; margin-left: auto; margin-right: auto;"><img border="0" height="209" src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEj4Pa190GiuBfIkof_vEYSG8StQSkuw94JJV5BX9FCWPii1Gtonr6E0IGtIsXBm63Emy2KyuBCjmcpNyZwmkVDGwtuz39rZCPPvAhsAFeIZpMHAGbvEuC0ugN_P_-7a4mE5_kVpHtDtILk/s1600/todoapp.PNG" width="320"></a></td>
   </tr>
   <tr>
    <td class="tr-caption" style="font-size: 13px; text-align: center;">TodoMVC - Example App with Avatar</td>
   </tr>
  </tbody>
 </table> You have to look out for different features to be showcased in the different examples. If you want to know how Avatar handles persistence you can exactly look into the rest example which uses a FileDataProvider to save the items. Another interesting example is the TodoMVC based app (<a href="" target="_blank">http://localhost:8080/demo-todo/</a>). It showcases a more complex view which also has been styled.
 <br>
 <br><b>What is next?</b>
 <br>
  As of today nobody knows what is going to happen with Avatar. The community feedback hasn't been tremendously loud or excited in general. I guess the reason for it is that nobody really knows what to do with it. The biggest drawback as of today is, that it only runs with GlassFish which will further limit adoption and community interest. Given the fact, that SSE isn't part of the Java EE specification it might make it even more difficult to port it to other app-server. Even if the license (<a href="https://avatar.java.net/license.html" target="_blank">GPL2 with Classpath Exception</a>) should allow it. Back to the initial survey and the thought about specifying a server side JavaScript integration with Java EE, you know at least have a brief idea about what Oracle is talking if they are talking about Avatar. Go ahead and&nbsp;<a href="https://www.surveymonkey.com/s/KGV7RWS" target="_blank">take that survey</a>&nbsp;and tell them what you are thinking about having that kind of stuff in Java EE.
 <br>
 <br>
</div><em>Meta: this post is part of the&nbsp;<a href="">Java Advent Calendar</a>&nbsp;and is licensed under the&nbsp;<a href="">Creative Commons 3.0 Attribution</a>&nbsp;license. If you like it, please spread the word by sharing, tweeting, FB, G+ and so on!</em>
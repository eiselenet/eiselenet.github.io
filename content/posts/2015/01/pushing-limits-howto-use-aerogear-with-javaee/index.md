---
title: "Pushing the Limits - Howto use AeroGear Unified Push for Java EE and Node.js"
date: 2015-01-13 11:00:00 +0000
layout: post
tags: ["AeroGear", "xPaaS", "Jboss"]
slug: "pushing-limits-howto-use-aerogear-with-javaee"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2015/01/pushing-limits-howto-use-aerogear-with-javaee.html
---

At the end of 2014 the <a href="">AeroGear team</a> announced the availability of the Red Hat JBoss Unified Push Server on <a href="https://aerogear.org/news/2014/11/20/aerogear-unified-push-xpaas/index.html">xPaaS</a>. Let's take a closer look!
<br><b><br></b> <b>Overview</b>
<br>
 The Unified Push Server allows developers to send native push messages to Apple's Push Notification Service (APNS) and Google's Cloud Messaging (GCM). It features a built-in administration console that makes it easy for developers to create and manage push related aspects of their applications for any mobile development environment. Includes client SDKs (iOS, Android, &amp; Cordova), and a REST based sender service with an available Java sender library. The following image shows how the Unified Push Server enables applications to send native push messages to Apple's Push Notification Service (APNS) and Google's Cloud Messaging (GCM): 
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="images_solutions_unifiedpush_overview-image-1.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" src="images_solutions_unifiedpush_overview-image-1.png" height="220" width="400"></a>
</div><b><br></b> <b>Architecture</b>
<br>
 The xPaaS offering is deployed in a managed EAP container, while the server itself is based on standard Java EE APIs like:
<br>
<ul>
 <li>JAX-RS&nbsp;</li>
 <li>EJB&nbsp;</li>
 <li>CDI&nbsp;</li>
 <li>JPA&nbsp;</li>
</ul> Another critical component is <a href="" target="_blank">Keycloak</a>, which is used for user management and authentication. The heart of the Unified Push Server are its <a href="https://aerogear.org/docs/specs/aerogear-unifiedpush-rest-1.0.x/overview-index.html" target="_blank">public RESTful endpoints</a>. These services are the entry for all mobile devices as well as for 3rd party business applications, when they want to issue a push notification to be delivered to the mobile devices, registered with the server.
<br>
<br><b>Backend integration</b>
<br>
 Being based on the JAX-RS standard makes integration with any backend platform very easy. It just needs to speak HTTP...
<br>
<br><b>Java EE</b>
<br>
 The project has a <a href="https://github.com/aerogear/aerogear-unifiedpush-java-client" target="_blank">Java library</a> to send push notification requests from any Java-based backend. The fluent builder API is used to setup the integration with the desired Unified Push Server, with the help of CDI we can extract that into a very simple factory: 
<br>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; color: #333333; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 13px; line-height: 1.42857143; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><code style="background-color: transparent; border-radius: 0px; box-sizing: border-box; color: inherit; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: inherit; padding: 0px; white-space: pre-wrap;"> @Produces public PushSender setup() \{ PushSender defaultPushSender = DefaultPushSender.withRootServerURL("http://localhost:8080/ag-push") .pushApplicationId("c7fc6525-5506-4ca9-9cf1-55cc261ddb9c") .masterSecret("8b2f43a9-23c8-44fe-bee9-d6b0af9e316b") .build(); \} </code></pre> Next we would need to inject the `PushSender` into a Java class which is responsible to send a push request to the Unified Push Server: 
<br>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; color: #333333; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 13px; line-height: 1.42857143; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><code style="background-color: transparent; border-radius: 0px; box-sizing: border-box; color: inherit; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: inherit; padding: 0px; white-space: pre-wrap;"> @Inject private PushSender sender; ... public void sendPushNotificationRequest() \{ ... UnifiedMessage unifiedMessage....; sender.send(unifiedMessage); \} </code></pre> The API for the `UnifiedMessage` is leveraging the builder pattern as well: 
<br>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; color: #333333; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 13px; line-height: 1.42857143; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><code style="background-color: transparent; border-radius: 0px; box-sizing: border-box; color: inherit; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: inherit; padding: 0px; white-space: pre-wrap;"> UnifiedMessage unifiedMessage = UnifiedMessage.withMessage() .alert("Hello from Java Sender API!") .sound("default") .userData("foo-key", "foo-value") ... .build(); </code></pre>
<br><b>Node.js</b>
<br>
 Being a restful server does not limit the integration to traditional platforms like Java EE. The AeroGear also has a <a href="https://github.com/aerogear/aerogear-unifiedpush-nodejs-client" target="_blank">Node.js library</a>. Below is a short example how to send push notifications from a Node.js based backend: 
<br>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; color: #333333; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 13px; line-height: 1.42857143; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><code style="background-color: transparent; border-radius: 0px; box-sizing: border-box; color: inherit; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: inherit; padding: 0px; white-space: pre-wrap;"> // setup the integration with the desired Unified Push Server var agSender = require( "unifiedpush-node-sender" ), settings = \{ url: "http://localhost:8080/ag-push", applicationId: "c7fc6525-5506-4ca9-9cf1-55cc261ddb9c", masterSecret: "8b2f43a9-23c8-44fe-bee9-d6b0af9e316b" \}; // build the push notification payload: message = \{ alert: "Hello from Node.js Sender API!", sound: "default", userData: \{ foo-key: "foo-value" \} \}; // send it to the server: agSender.Sender( settings ).send( message, options ).on( "success", function( response ) \{ console.log( "success called", response ); \}); </code></pre>
<br><b>What's next ? </b>
<br>
 The Unified Push Server on on <a href="https://aerogear.org/news/2014/11/20/aerogear-unified-push-xpaas/index.html" target="_blank">xPaaS</a> is supporting Android and iOS at the moment, but the AeroGear team is looking to enhance the service for more mobile platforms. The community project is currently supporting the following platforms:
<br>
<ul>
 <li>Android</li>
 <li>Chrome Packaged Apps</li>
 <li>iOS</li>
 <li>SimplePush / Firefox OS</li>
 <li>Windows&nbsp;</li>
</ul> There are plans for adding support for Safari browser and <a href="https://issues.jboss.org/browse/AGPUSH-457" target="_blank">Amazon's Device Messaging (ADM)</a>.
<br>
<br><b>Getting started</b> To see the Unified Push Server in action, checkout the video below: <iframe allowfullscreen frameborder="0" height="315" src="//www.youtube.com/embed/ZFZlphKlGqM" width="560"></iframe>
<br>
<br>
 The xPaaS release comes with different demos for Android, iOS and Apache Cordova clients as well as a Java EE based backend demo. You can find the <a href="" target="_blank">downloads here</a>.
<br>
 More information can be found on the <a href="" target="_blank">Unified Push homepage</a>.
<br>
 You can reach out to the AeroGer team via <a href="" target="_blank">IRC or email</a>.
<br>
 Have fun and enjoy! 
<br>
<br>
 If you find some more time and need a #coffee+++ make sure to watch the <a href="http://blog.eisele.net/2014/10/developer-interview-di-7-matthias-wessendorf.html" target="_blank">developer interview with Matthias about&nbsp;Openshift, Aerogear and how to bring Java EE to Mobiles</a>.
<br>
<br>
 _______________________
<br><i>This is a guest post by Matthias Wessendorf&nbsp;(<a href="https://twitter.com/mwessendorf" target="_blank">@mwessendorf</a>, <a href="" target="_blank">blog</a>). He is working at Red Hat where he is leading the AeroGear project. Previously, he was the PMC Chair of the Apache MyFaces project. Matthias is a regular conference speaker. Thank you, Matthias!</i>
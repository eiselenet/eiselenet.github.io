---
title: "A Refresher - Top 5 Java EE 7 Frontend"
date: 2016-01-12 18:57:00 +0000
layout: post
tags: ["javaee7", "frontend", "EdBurns"]
slug: "a-refresher-top-5-java-ee-7-frontend"

url: /2016/01/a-refresher-top-5-java-ee-7-frontend.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="ee-7-overview.PNG" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="184" src="ee-7-overview.PNG" width="320"></a>
</div> The series continues. After the <a href="http://blog.eisele.net/2015/11/a-refresher-java-ee-7-at-glance.html" target="_blank">initial overview</a> and <a href="http://blog.eisele.net/2015/12/a-refresher-top-10-java-ee-7-backend.html" target="_blank">Arjan's post about the most important backend features</a>, I am now very happy to have Ed Burns (<a href="https://twitter.com/edburns" target="_blank">@edburns</a>) finish the series with his favorite Java EE 7 frontend features.
<br>
<br>
 Thanks to Markus Eisele for giving me the opportunity to guest post on his very popular blog. Markus and I go way back to 2010 or so, but I've not yet had the pleasure of guest posting. &nbsp;Markus asked me to cover the Java EE 7 Web Tier. &nbsp;Since EE 7 is a mature release of a very mature
<br>
 platform, much has already been published about it. &nbsp;Rather than rehash what has come before, I'm going to give my opinion about what I think are the important bits and a very high level overview of each.
<br>
<br>
 If you're interested in learning more about this first-hand, please consider attending my full day training at JavaLand 2016. &nbsp;I'm giving the training with modern finance and HTML5 expert Oliver Szymanski. &nbsp;For details, <a href="" target="_blank">please visit the javaland website</a>.
<br>
<br>
 First, a bit of historical perspective. &nbsp;Markus asked me to write about the Java EE 7 Web Tier. &nbsp;Let's take a look at that term, "web tier" or "presentation tier" as it is also called. &nbsp;If one is to believe the hype surrounding newer ideas such as microservices, the term itself is starting to sound a bit dated because it implies a three tier architecture, with the other two tiers being "business logic" and
<br>
 "persistence". &nbsp;Surely three tiers is not micro enough, right? &nbsp;Well, the lines between these tiers are becoming more and more blurred over time as enterprises tinker with the allocation of responsibilities in pursuit of delivering the most business value with their software. &nbsp;In any case, Java EE has always been a well integrated collection of enterprise technologies for the Java platform, evolved using a consensus based open development practice (the Java Community Process or JCP) with material participation from leading stake holders. &nbsp;The "web tier" of this platform is really just the set of technologies that one might find useful when developing the "web tier" of your overall solution. &nbsp;This is a pretty big list:
<br>
<br>
 WebSocket 1.0 JSR-356
<br>
 JavaServer Faces 2.2 JSR-344
<br>
 Servlet 3.1 JSR-340
<br>
 JSON Processing 1.0 JSR-353
<br>
 REST (JAX-RS) 2.0 JSR 339
<br>
 Bean Validation 1.1 JSR-349
<br>
 Contexts and Dependency Injection 1.1 JSR-346
<br>
 Dependency Injection for Java 1.0 JSR-330
<br>
 Concurrency Utilities for Java EE 1.0 JSR-236
<br>
 Expression Language 3.0 JSR-341
<br>
<br>
 For the purposes of this blog entry, let's take a look at the first five: WebSocket, JSF, Servlet, JSON, and JAX-RS. &nbsp;While the second five are surely essentail for a professional web tier, it is beyond the scope of this blog entry to look at them.
<br>
<br><b>WebSocket</b>
<br>
 JSF and WebSocket are the only two Java EE 7 specs that have a direct connection to the W3C HTML5 specification. &nbsp;In the case of WebSocket, there are actually three different standards bodies in play. &nbsp;WebSocket, the network protocol, is specified by RFC-6455 from the IETF. &nbsp;WebSocket
<br>
 the JavaScript API is specified as a sub-spec of HTML5 from the W3C. WebSocket the Java API is specified by JCP under JSR-356. &nbsp;In all aspects of WebSocket, the whole point is to provide a message based reliable full-duplex client-server connection.
<br>
<br>
 JSR-356 lets you use WebSocket in both client and server capacities from your Java SE and EE applications.
<br>
<br>
 On the server side, it allows you to expose a WebSocket endpoint such that browsers can connect to it using their existing support for the WebSocket JavaScript API and network protocol. &nbsp;You declare your endpoints to the system either by annotating some POJOS, or by imperatively calling bootstrapping APIs from java code, say from a ServletContextListener. &nbsp;Once the connection is established, the server can send and receieve messages from/to any number of clients that happen
<br>
 to be connected at the same time. &nbsp;The runtime automatically handles connection setup and tear down.
<br>
<br>
 The WebSocket java client API allows java SE applications to talk to WebSocket endpoints (Java or otherwise) by providing a Java analog to the W3C JavaScript WebSocket API.
<br>
<br><b>Java Server Faces (JSF)</b>
<br>
 In JSF 2.2 we added many new features but I will only cover three of them here.
<br>
<br>
 HTML5 Friendly Markup enables writing your JSF pages in almost pure HTML (must be well formed), without the need for the XML namespaces that some see as clumsy and difficult to understand. &nbsp;This is possible because the underlying HTML Basic JSF RenderKit (from JSF 1.0) provides all the necessary primitives to adopt mapping conventions from an arbitrary
<br>
 piece of HTML markup to a corresponding JSF UIComponent. &nbsp;For example, this is a valid JSF form
<br>
<pre class="code"><code> &nbsp; &nbsp; &nbsp; &nbsp; &lt;form jsf:id="form"&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&lt;input jsf:id="name" type="tel" jsf:value="#\{complex.name\}" /&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&lt;progress jsf:id="progress" max="3" value="#\{complex.progress\}" /&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/form&gt; </code></pre> The only catch is the need to flag the element as a JSF component by use of a namespaced attribute. &nbsp;This means you must declare at least one namespace in the &lt;html&gt; tag:
<br>
<pre class="code"><code> &lt;!DOCTYPE html&gt; &lt;html xmlns="http://www.w3.org/1999/xhtml" &nbsp; &nbsp; &nbsp; xmlns:jsf="http://xmlns.jcp.org/jsf"&gt; </code></pre> Faces Flows is a standardization of the page flow concept from ADF Task Flows and Spring Web Flow. &nbsp;Flows gives you the ability to group pages together that have some kind of logical connection and need to share state. &nbsp;A flow defines a logical scope that becomes active when the the flow is entered and made available for garbage collection when the flow is exited. &nbsp;There is a rich syntax for describing flows, how they are entered, exited, relate to each other, pass parameters to each other,
<br>
 and more. &nbsp;There are many conveniences provided thanks to the Flows feature being implemented on top of Contexts and Dependency Injection (CDI). &nbsp;Flows can be packaged as jar files and included in your web application, enabling modularization of sub-sections of your web app.
<br>
<br>
 Just as Flows enable modularizing behavior, Resource Library Contracts (RLC) enable modularizing appearance. &nbsp;RLC provides a very flexible skinning system that builds on Facelets and lets you package skins in jar files, effectively allowing modularizing appearance.
<br>
<br><b>Servlet</b>
<br>
 The most important new feature in Servlet 3.1 is the additional support for non-blocking IO. &nbsp;This builds on top of the major feature of Servlet 3.0 (from Java EE 6): async io. &nbsp;The rapid rise of reactive programming indicates that Java apps can no longer afford to block for IO, ever. The four concerns of reactive programming: responsiveness, elasticity, resiliency, and event basis are founded on this premise. &nbsp;Prior to non-blocking IO in Servlet 3.1, it was very difficult to avoid blocking in Servlet apps.
<br>
<br>
 The basic idea is to allow the Servlet runtime to call your application back when IO can be done safely without blocking. &nbsp;This is accomplished by virtue of new listener interfaces, ReadListener and WriteListener, instances of which can be registered with methods on ServletInputStream and ServletOutputStream.
<br>
<br>
 When you add this feature to the async-io feature added in Servlet 3.0, it is possible to write Servlet based apps that can proudly sport the "We Are Reactive" banner.
<br>
<br><b>JSON</b>
<br>
 From the outside perspective, the ability to parse and generate JSON in Java is certainly nothing new. &nbsp;Even before Java EE 7, there were many solutions to this basic need. &nbsp;Hewing close to the principle that standards are not for innovation, but to confer special status upon existing ideas, the JSON support in Java EE 7 provides the capability to parse and generate JSON with a simple Java API. &nbsp;Reading can be done in a streaming fashion, with JsonParser, or in a bulk fashion using JsonReader. &nbsp;Writing can be done in a streaming fashion with JsonGenerator. &nbsp;Writing can be done in a bulk style with JsonBuilderFactory and JsonWriter.
<br>
<br><b>JAX-RS</b>
<br>
 It is hard to overstate the importance of REST to the practice of modern enterprise software development for non-end-user facing software. &nbsp;I'd go so far as to say that gone are the days when people go to the javadoc (or JSDoc or appledoc etc) to learn how to use an API. &nbsp;Nowadays if your
<br>
 enterprise API is not exposed as a RESTful web service, you probably will not even be considered. JAX-RS is how REST is done in Java. JAX-RS has been a part of Java EE since Java EE 6, but it received the 2.0 treatment in Java EE 7. &nbsp;The big ticket features in 2.0 include:
<br>
<br>
<ul>
 <li>&nbsp;Client support<br>
  In my opinion, the most useful application of this feature is in using &nbsp; JUnit to do automated testing of RESTful services without having to &nbsp;resort to curl from continuous integration. &nbsp;Of course, you could use it for service-to-service interaction as well.</li>
 <li>&nbsp;Seamless integration with JSON<br>
  In most cases a simple @Produces("application/json") annotation on &nbsp;your HTTP method endpoint is sufficient to output JSON. &nbsp;Data arriving &nbsp;at your service in JSON format is also automatically made available to &nbsp;you in an easy to consume format from Java.</li>
 <li>&nbsp;Asynchronous support (Reactive again)<br>
  This feature gives you the ability to hand off the processing required &nbsp;to generate a response to another Thread, allowing the original thread to return immediately so no blocking happens. &nbsp;The async thread is free to respond when it is ready.</li>
</ul>
<br>
 Naturally, this only scratches the surface of the Java EE 7 web tier. For more details, a great place to start is the official <a href="https://www.youtube.com/playlist?list=PL74xrT3oGQfCCLFJ2HCTR_iN5hV4penDz" target="_blank">Java EE 7</a>&nbsp;<a href="https://www.youtube.com/playlist?list=PL74xrT3oGQfCCLFJ2HCTR_iN5hV4penDz" target="_blank">launch webinars</a>.
<br>
<br>
 I hope to see you at <a href="" target="_blank">JavaLand</a>!
<br>
<br>
 Thank you Ed for taking the time to write this post. If you haven't now is the time to to play around with Java EE 7. Here are some resources to get you started with JBoss EAP 7 and WildFly:
<br>
<br>
<ul>
 <li><a href="http://blog.eisele.net/2015/11/getting-started-with-eap-7-alpha-and.html" target="_blank">Getting Started With EAP 7 Alpha and Java EE 7</a></li>
 <li><a href="http://blog.eisele.net/2015/12/a-java-ee-7-application-on-openshift-3.html" target="_blank">A Java EE 7 Application on OpenShift 3 With JBoss Tools</a></li>
 <li><a href="https://github.com/javaee-samples/javaee7-samples" target="_blank">Java EE 7 Samples on GitHub</a></li>
 <li><a href="http://blog.eisele.net/2015/12/getting-started-with-jboss-eap-7.html" target="_blank">Getting Started With The JBoss EAP 7 Quickstarts</a></li>
</ul>
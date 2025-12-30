---
title: "About the Java EE 6 Web Profile und the Future"
date: 2010-05-03 10:19:00 +0000
layout: post
tags: ["Java EE", "glassfishv3", "future", "Java EE 6"]
slug: "2010-05-03-about-java-ee-6-web-profile-und-java-ee"
url: /2010/05/about-java-ee-6-web-profile-und-java-ee.html
---

I was thinking about the new Java EE 6 web profile recently. Beside some current presentations at the jax2010 it seems as if more and more of the well know "webcontainer" products move into this direction (<a href="https://cwiki.apache.org/GMOxDEV/road-map-for-java-ee6-web-profile.html" target="_blank">Apache Web Profile</a>, <a href="http://blog.caucho.com/?p=384" target="_blank">Resin</a> and others I did not mention here ...).
<br>
 If you look at the new web profile in more detail, you see that it is a specified minimal configuration targeted for small footprint servers that should support something called "typical" web applications. 
<br>
 It is thought of as a minimal specification, so a vendor is free to add additional services in their concrete implementation. The required elements of the web profile are:
<ul>
 <li>Java EE 6 (JSR-316)</li>
 <li>Servlet 3.0 (JSR-315)</li>
 <li>JavaServer Pages (JSP) 2.2 (JSR-245)</li>
 <li>Expression Language (EL) 2.2 (JSR-245)</li>
 <li>Debugging Support for Other Languages 1.0 (JSR-45)</li>
 <li>Standard Tag Library for JavaServer Pages (JSTL) 1.2 (JSR-52)</li>
 <li>JavaServer Faces (JSF) 2.0 (JSR-314)</li>
 <li>Common Annotations for Java Platform 1.1 (JSR-250)</li>
 <li>Enterprise JavaBeans (EJB) 3.1 Lite (JSR-318)</li>
 <li>Java Transaction API (JTA) 1.1 (JSR-907)</li>
 <li>Java Persistence API (JPA) 2.0 (JSR-317)</li>
 <li>Dependency Injection for Java 1.0 (JSR-330)</li>
 <li>Contexts and Dependency Injection for Java EE platform 1.0 (JSR-299)</li>
 <li>Bean Validation 1.0 (JSR-303)</li>
 <li>Managed Beans 1.0 (JSR-316)</li>
 <li>Interceptors 1.1 (JSR-318)</li>
</ul>Clear from a technology point for view. For me this was simply a new way of prunning; getting rid of some older specs and finding a way to have more Java EE certified servers again (<a href="http://java.sun.com/javaee/overview/compatibility.jsp">compare this</a>). 
<br>
 Even if I am not shure, if the contained technologies do make up a good and simple profile (What about Mail and JSP?) it could be a step into the right direction. 
<br>
 But what I realy do not get is the actual hype (and that's what I am feeling) around this. What about the full blown Java EE 5/6 servers? Geronimo, GlassFish, JBoss, or whatever else is out there are completely usable to me. Times where J2EE servers were hard to install and administrate belong to the past! It's quite easy to setup, update and configure for example the latest GlassFish v3. Where is the added value with the web profile? Nobody forces you to use any kind of API in your applications. Further on I strongly believe that you should take some actions to prevent this. Adam Bien told me, that:
<br>
<br>
 @AdamBien
<br>
 http://twitter.com/AdamBien/statuses/13177648403
<br>
 @myfear webprofile + JAX-RS + JMS -&gt; should be sufficient for 90% of all projects.
<br>
<br>
 But that's exactly the point. The defined Java EE 6 web profile is a good start. It's something I would love to call the new webcontainer standard. But it is still some steps away from defining a lightweight new Java Enterprise Edition. And beside the fact, that it's easier to have a certified product with a minimal set of technolgies I am missing the usecase for this.
<br>
<br>
 Could this all simply be about saving time and effort for new starting Java developers? Hope not. Even if I probably like to call the selection of specifications a adequate starting point. There is nothing that will prevent a future Java EE developer from knowing about most of the specifications defined by the full blown Java EE 6. Show me one, that did not came across Webservices, JMS and Mail ...
<br>
<br>
 Does it make a real difference how many technologies you are running in an instance? In terms of performance? In terms of administration? What is more important to me is, that the instances only make use of container services and component models that are required by the application. And that is the direction I would love to see Java EE move in the future. Don't try to drive those profile things any further. 
<br>
 Focus on a defined, clear and up to date set of technolgies. Find a simple and convenient way to drop support of older standards or versions, make container services available to pojos and don't force pojos to implement container services or component models. And last but not least find a general way to allow plugins for Java EE. 
<br>
<br>
 What are your thoughts about this? Would love to know more about them...
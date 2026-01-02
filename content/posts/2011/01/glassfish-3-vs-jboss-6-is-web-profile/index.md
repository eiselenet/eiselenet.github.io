---
title: "GlassFish 3 vs. JBoss 6 - Is the Web Profile ready for the Enterprise?"
date: 2011-01-07 09:47:00 +0000
layout: post
tags: ["glassfish", "javaee6", "Jboss"]
slug: "glassfish-3-vs-jboss-6-is-web-profile"

url: /2011/01/glassfish-3-vs-jboss-6-is-web-profile.html
---

Wow. We still are in the holiday season and the emotions rise with the announcement of the latest JBoss AS 6 release. My valued friend Arun Gupta did a post about "<a href="http://blogs.sun.com/arungupta/entry/which_java_ee_6_app">Which Java EE 6 App Server ? - JBoss 6.0 or GlassFish 3.x</a>" a few days ago an today Anil responded with <a href="http://anil-identity.blogspot.com/2011/01/response-jboss-as6-vs-glassfish-3x.html">JBoss AS6 vs Glassfish 3.x</a>. It's obvious that even both names GlassFish and JBoss are mentioned in the topics and the discussion seems to be about having clustering support or a well documented users guide, I believe, that all this is basically about the value of the Java EE 6 Web Profile for Enterprise Applications.
<br>
<br><b>What's the Web Profile?</b>
<br>
 If you refer <a href="http://blog.eisele.net/2010/05/about-java-ee-6-web-profile-und-java-ee.html">to my post from last year</a>, its thought of as a minimal specification, so a vendor is free to add additional services in their concrete implementation. A more complete comparison about what's contained in it could be found on <a href="http://glassfish.java.net/downloads/3.0.1-final.html">glassfish.org</a>. 
<br>
 Some of the most basic parts of Java EE are missing in it. Some very important parts are:
<br>
 - JAX-WS 2.2
<br>
 - JMS 1.1
<br>
 - JavaMail 1.4
<br>
 - Full EJB 3.1 API
<br>
 Even if @AdamBien <a href="http://twitter.com/#!/AdamBien/statuses/13177648403">states</a>, that "webprofile + JAX-RS + JMS -&gt; should be sufficient for 90% of all projects." I personally believe, that there is more missing. One very simple example are the TimerBeans, that simply are not contained in the EJB 3.1 lite version. Same is true for JavaMail. Tell me about any application not sending some kind of mail to it's users. 
<br>
<br><b>What does Enterprise Software development mean?</b>
<br>
 Beside the technical parts, there obviously is more to enterprise grade software development. Essential parts are:
<br>
 Having ...
<br>
 - a stable Vendor
<br>
 - a strong history and many references
<br>
 - a suitable documentation for the products
<br>
 - the option to buy any kind of support with according SLAs
<br>
 - the option to integrate the products into the existing environments.
<br>
 And many others. I know about comparison sheets containing more than 500 points for one individual enterprise.....
<br>
<br><b>So, now: What's the right decision: JBoss or GlassFish?</b>
<br>
 Having all this in mind and trying to compare both distributions, it simply is clear, that the JBoss AS 6 release is a Web Profile only version. GlassFish 3.x is a Java EE 6 full stack implementation. Beside this there are obvious differences in the other parts, I consider important for enterprise grade usage. Everybody is free to make it's own comparison on the relevant parts for both servers. Without being able to make this too detailed for public reference I can state, that for now and the actual available releases of both servers GlassFish is the leader in terms of being suitable for an enterprise grade production environment relying on Java EE 6. And this is explicitly not a comment on technical quality or even the vendor. It's simply my own opinion about Web Profile vs. Full Stack and their use within the enterprise.
<br>
<br>
 For me, this would be a great point to focus again. JBoss guys on quickly developing and delivering their full stack version and Oracle to proof that they are willing to stabilize the Java EE and GlassFish community and push their product forward.
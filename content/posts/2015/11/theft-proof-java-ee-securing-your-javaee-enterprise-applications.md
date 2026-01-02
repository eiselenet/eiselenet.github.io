---
title: "Theft-Proof Java EE - Securing Your Java EE Enterprise Applications"
date: 2015-11-09 06:27:00 +0000
layout: post
tags: ["javaee", "security"]
slug: "theft-proof-java-ee-securing-your-javaee-enterprise-applications"
link: "2015/11/theft-proof-java-ee-securing-your-javaee-enterprise-applications.html"
url: /2015/11/theft-proof-java-ee-securing-your-javaee-enterprise-applications.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="http://de.slideshare.net/myfear/theftproof-java-ee-securing-your-java-ee-applications" imageanchor="1" rel="nofollow" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;" target="_blank"><img alt="An overview about the holistic approach to application security" border="0" height="208" src="holistic_security.PNG" title="Holistic Security" width="320"></a>
</div><a href="" target="_blank">Øredev</a> is just a couple of days away, and I was invited to give two presentations. One of them is about my favorite topic: Security and Java EE. It is designed to fulfill two goals. On the one side to introduce the typical Java EE developer to the overall application security process and main objectives. But also to look at the details about what Java EE has to offer when it comes to typical requirements. Those are two different things and the first has been addressed before in my presentation about "<a href="http://blog.eisele.net/2013/12/my-vjug-session-dont-be-that-guy.html" rel="nofollow" target="_blank">Developer Security Awareness</a>". This talk is meant to do a match between today's security requirements and the features available in the platform as of the Java EE 7 release.
<br>
<br><b>Securing Applications Isn't Just About Secure Code</b>
<br>
 All the common attacks we've known about for a very long time are still out there, and people still regularly make mistakes regarding them. If you want to write secure Java EE code and use the technology securely, you're on the right track. But there is a lot more to learn and know about securing your applications. There are also people and processes and how they best work together to build a suitable secure system. Derived from practice for use with everyday development such a system can make the Java EE space a little more secure.
<br>
<br>
<div class="video-container">
 <iframe allowfullscreen frameborder="0" height="355" marginheight="0" marginwidth="0" scrolling="no" src="//de.slideshare.net/slideshow/embed_code/key/8HmWk7MryjWBtv" style="border-width: 1px; border: 1px solid #CCC; margin-bottom: 5px; max-width: 100%;" width="425"> </iframe>
</div>
<br><b>Application Security Needs A Holistic Approach</b>
<br>
 To build a secure Java EE application, a holistic approach to application security is required and security must be applied at all layers and services. Even if everything else beside our own applications typically does not bother developers at all it becomes more important. Last but not least with the advent of DevOps. So, it doesn't hurt to be at least aware of everything that is going on around us. Let's start with the layer the farthest away. Secure applications rely on secure networks first of all. This includes everything from routeres, switches, firewalls or even application level firewalls. They need to be patched and correctly configured. Watch out for standard ports and passwords for admin consoles. Next is the operating system layer. Every part of your applications runs on a separate host eventually (Applicationserver, Database, Message Brokers), so you will have to watch out for latest patches and updates for all of them. But shared filesystems and possibly risky daemon processes are also something to watch out for. When you're done with that it is time to look at the platform services. This is where the JDK base-install fits in but also the application-server. Check policy files and default passwords for remote connections and web-based admin consoles.
<br>
<br><b>From Here On, Application Security Starts&nbsp;</b>
<br>
 When the infrastructure is taken care of, we can look into application security. And it is typically divided up into six components: Authentication, Authorization, Auditing, Confidentiality, Integrity, Availability. All those components prevent your system from being exploited. A threat is a potential event that may affect your system. An attack exploits a vulnerability in your system. And ultimately this is what we want to prevent. Always adhere to the best practices for designing secure applications:
<br>
<ul>
 <li>Compartmentalize</li>
 <li>Use least privilege</li>
 <li>Apply defense in depth</li>
 <li>Do not trust user input</li>
 <li>heck at the gate</li>
 <li>Fail securely</li>
 <li>Secure the weakest link</li>
 <li>Create secure defaults</li>
 <li>Reduce your attack surface</li>
</ul><b>Get Started With Java EE Security</b>
<br>
 Watch the full talk on Vimeo to learn what Java EE has to offer around application security. And make sure to provide feedback in the comments and let me know, if there is anything else, you'd like me to talk about in the future.
<br>
<br>
<div class="video-container">
 <iframe allowfullscreen frameborder="0" height="281" mozallowfullscreen="" src="https://player.vimeo.com/video/145044502" webkitallowfullscreen="" width="500"></iframe>
</div>
<br>
 Further Reading
<br>
<br>
<ul>
 <li><a href="" rel="nofollow" target="_blank">JBoss Keycloak</a></li>
 <li><a href="https://blogs.oracle.com/theaquarium/entry/jsr_375_java_ee_security" target="_blank">JSR 375: Java EE Security API</a></li>
 <li><a href="https://docs.oracle.com/javaee/7/tutorial/security-intro001.htm" rel="nofollow" target="_blank">Overview Of Java EE 7 Security</a></li>
 <li><a href="http://www.theserverside.com/tip/Learn-what-the-new-Java-EE-security-API-means-for-developers" rel="nofollow" target="_blank">Learn what the new Java EE 8 security API means for developers</a></li>
</ul>
---
title: "Java EE, Docker, WildFly and Microservices on Docker"
date: 2015-01-12 11:00:00 +0000
layout: post
tags: ["wildfly", "Docker", "javaee", "Jboss"]
slug: "java-ee-docker-wildfly-and-microservices-on-docker"
link: "2015/01/java-ee-docker-wildfly-and-microservices-on-docker.html"
url: /2015/01/java-ee-docker-wildfly-and-microservices-on-docker.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="docker-whale-home-logo.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" src="docker-whale-home-logo.png" height="181" width="320"></a>
</div> If one thing survived all the New Year parties, it is Docker. It was hot at the end of 2014 and it looks like it is getting even hotter in 2015. And Red Hat is one of the key drivers behind the adoption of this amazing container technology. This is a short summary blog post about a bunch of resources to get you started with Java EE, WildFly and Microservices on Docker.
<br>
<br><b>Get A First Impression - Introduction to Docker</b>
<br>
 Red Hat's developer blog offers a <a href="" target="_blank">practical introduction to Docker</a>. If the numbers of articles, meetups, talk submissions at different conferences, tweets, and other indicators are taken into consideration, then seems like Docker is going to solve world hunger. This is how Arun Gupta starts his <a href="" target="_blank">introductory blog-post</a> about Docker basics.
<br>
<br><b>Take the Lab - Docker for Java Developers&nbsp;</b>
<br><a href="https://github.com/burrsutter/docker_tutorial" target="_blank">This lab</a> offers developers an intro-level, hands-on session with Docker, from installation (including boot2docker on Windows/Mac), to exploring Docker Hub, to crafting their own images, to adding Java apps and running custom containers. This is a BYOL (bring your own laptop) session, so bring your Windows, OSX, or Linux laptop and be ready to dig into a tool that promises to be at the forefront of our industry for some time to come.
<br>
 The <a href="" target="_blank">Docker Common Commands Cheatsheet</a> by Arun might also help a bit here.
<br>
<br><b>Learn More - About how to use Docker on Windows with Maven</b>
<br>
 As many middleware developers are running Windows, I thought I give it a try myself and also give some more tips along the way <a href="http://blog.eisele.net/2014/12/docker-for-java-ee-developers-on-windows-with-maven.html" target="_blank">about how to build and run images</a> with the least possible amount of struggle with Docker containers, hosts and guests and command line options.
<br>
<br><b>Get Your Hands Dirty - Working With Docker Images</b>
<br>
 Now that you've learned how to manage the basics, it is time to either <a href="" target="_blank">create your own images the Docker-way</a> and <a href="" target="_blank">push them to the Registry</a>. If you're struggling with multiple images and dependencies on your machine it is <a href="" target="_blank">handy to know how to remove them</a>.
<br>
<br><b>Take the Lab Again - Docker with WildFly and Java EE7 HOL</b>
<br><a href="https://github.com/javaee-samples/javaee7-hol" target="_blank">The&nbsp;Java EE 7 Hands-on Lab</a> has been delivered all around the world and is a pretty standard application that shows design patterns and anti-patterns for a typical Java EE 7 application. Arun took some time to Docker-ize it. <a href="" target="_blank">Learn how to use it and take it again</a>.
<br>
<br><b>Setting Up You Own WildFly - On Docker Of Course</b>
<br>
 A pretty standard setup is to have different containers for your database and your Java EE server. Learn how to <a href="" target="_blank">setup MySQL and WildFly on separate containers and link them</a>. Or jump directly into setting up a <a href="http://mluksa.blogspot.ca/2015/01/deploying-wildfly-cluster-on-openshift.html" target="_blank">WildFly cluster on OpenShift Origin v3</a> (which is full of Docker).
<br>
 You can also have a <a href="https://github.com/wildfly-extras/wildfly-camel-book/blob/2.1/cloud/openshift.md" target="_blank">WildFly version which contains Apache Camel as subsystem</a> and use this instead of a plain WildFly on Origin. But you can of course also <a href="https://github.com/wildfly-extras/wildfly-camel-book/blob/2.1/cloud/docker.md" target="_blank">use it in plain Docker</a>.
<br>
 And you also need to know how to <a href="" target="_blank">expose the WildFly admin console</a> to the host.
<br>
<br><b>Learn How To Test on Docker - Java EE with Arquillian and Cube</b>
<br>
 Everything is setup now and you know how to operate your Docker containers and images, it is time to get your hands on tests. <a href="" target="_blank">Arquillian supports Docker with the Cube</a> extension.
<br>
<br>
 If I missed an important link, I would be happy to know about and read your comments or experiences. Also, please let me know if there's something special, you are missing.
<br>
<br>
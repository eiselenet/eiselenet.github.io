---
title: "Your first Lagom service - getting started with Java Microservices"
date: 2016-04-07 07:07:00 +0000
layout: post
tags: ["java", "microservices"]
slug: "2016-04-07-your-first-lagom-service-getting-started-with-java-microservices"
url: /2016/04/your-first-lagom-service-getting-started-with-java-microservices.html
---

I've been heads-down in writing my next O'Reilly report and didn't had enough time to blog in a while. Time to catch up here and give you a real quick start into the new microservices framework named Lagom. It is different to what you might know from Java EE or other application frameworks. And this is both a challenge and opportunity for you to learn something new. If you can wait for a couple of more days, <a href="http://www.oreilly.com/programming/free/developing-reactive-microservices-signup.html" target="_blank">register to be notified</a> when my new report will be available and learn everything about the story behind Lagom and how to get started. I will walk you through an example application and introduce the main concepts to you in more detail than I could in a blog post. This post is for the unpatient that want to get started today and figure everything out themselves.
<br>
<br><b>Some background</b>
<br><a href="https://www.oreilly.com/ideas/how-to-make-just-right-easier-with-microservices" target="_blank">Microservices are everywhere</a>&nbsp;these days and more and more is unveiled about what it takes to build a complex distributed system with the existing middleware stacks. And there are far better alternatives and concepts to implement an application as a microservices based architecture.&nbsp;The core concepts of reactive microservices have been introduced by Jonas Bonér in his report <a href="https://www.lightbend.com/reactive-microservices-architecture" target="_blank">Reactive Microservices Architecture</a> which is available <b>for free</b> after registration. <a href="http://lightbend.com/lagom" target="_blank">Lagom</a> is the implementation of the described concepts. It uses technologies that you might have heard about but probably rarely used before as a Java EE developer: Mainly <a href="" target="_blank">Akka</a> and <a href="" target="_blank">Play</a>. But for now, let's just forget about them because Lagom provides you with a great abstraction on top and gives you everything you need to get started.
<br>
<br><b>Prerequisites</b>
<br>
 Have <a href="http://www.lagomframework.com/documentation/1.0.x/Installation.html" target="_blank">activator&nbsp;and Java 8</a> installed. Activator is something that you probably also haven't heard about. It is build on top of sbt and helps you <a href="https://www.lightbend.com/blog/typesafe-activator-what-is-it" target="_blank">getting started with your projects and much more</a>. A Lagom system is typically made up of a set of sbt builds, each build providing multiple services. The easiest way to get started with a new Lagom system is to create a new project using the lagom Activator template. No need for anything else right now. You probably want to have an IDE installed. IntelliJ or Eclipse should be good for now.
<br>
<br><b>Setting up your first project</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="lagom-in-eclipse.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="227" src="lagom-in-eclipse.png" width="320"></a>
</div> Time to get to see some code. Let's generate a simple example from the lagom-java template:
<br>
<pre class="code"><code>$ activator new first-lagom lagom-java</code></pre>
<br>
 Change into the newly generated folder "fist-lagom" and issue the sbt command to create an eclipse project.
<br>
<pre class="code"><code>$ activator eclipse</code></pre>
<br>
 A bunch of dependencies are downloaded and after the succesful execution you can open Eclipse and use the Import Wizard to import Existing Projects into your Workspace. Note, that if you are using the Immutables library with Eclipse, you need to <a href="http://www.lagomframework.com/documentation/1.0.x/ImmutablesInIDEs.html#Eclipse" target="_blank">set this up</a>, too.
<br>
<br>
 Lagom includes a development environment that let you start all your services by simply typing runAll in the activator console. Open the terminal and cd to your Lagom project:
<br>
<pre class="code"><code>$ activator runAll</code></pre> The output looks similar to this: 
<br>
<pre>[info] Loading project definition from /Users/myfear/projects/first-lagom/project [info] Set current project to first-lagom (in build file:/Users/myfear/projects/first-lagom/) [info] Starting embedded Cassandra server ........ [info] Cassandra server running at 127.0.0.1:4000 [info] Service locator is running at http://localhost:8000 [info] Service gateway is running at http://localhost:9000 [info] Compiling 2 Java sources to /Users/myfear/projects/first-lagom/helloworld-api/target/scala-2.11/classes... [info] Compiling 1 Java source to /Users/myfear/projects/first-lagom/hellostream-api/target/scala-2.11/classes... [info] Compiling 2 Java sources to /Users/myfear/projects/first-lagom/hellostream-impl/target/scala-2.11/classes... [info] Compiling 6 Java sources to /Users/myfear/projects/first-lagom/helloworld-impl/target/scala-2.11/classes... [info] application - Signalled start to ConductR [info] application - Signalled start to ConductR [info] Service hellostream-impl listening for HTTP on 0:0:0:0:0:0:0:0:26230 [info] Service helloworld-impl listening for HTTP on 0:0:0:0:0:0:0:0:24266 [info] (Services started, use Ctrl+D to stop and go back to the console...) </pre> Now go and try out your first service by visiting http://localhost:9000/api/hello/World. Now you're all set for the next blog posts, where I am going to walk you through the example in more detail. If you can't wait, go ahead and read in the <a href="http://www.lagomframework.com/documentation/1.0.x/GettingStarted.html#Getting-started-with-Lagom" target="_blank">Lagom Getting Started</a> guide.
---
title: "From Microservices to Distributed Systems - Survival guide for Java Developers"
date: 2017-02-16 09:15:00 +0000
layout: post
tags: ["distributed", "java", "systems", "microservices"]
slug: "from-microservices-to-distributed-systems-java"
link: "2017/02/from-microservices-to-distributed-systems-java.html"
url: /2017/02/from-microservices-to-distributed-systems-java.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="1339539199_67bfdb8356_z.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="320" src="1339539199_67bfdb8356_z.jpg" width="290"></a>
</div> It kind of feel like the hype for Microservices is slowly coming down to earth and our industry is starting to realise, that a system according to the architectural paradigms behind Microservices can't easily be created by just exposing some HTTP interfaces on top of existing components. We do seem to have agreement on the necessity of having service optimised infrastructures, cultural and organisational changes and last but not least the outer architecture or orchestration for these architectures. The parts, that many Java developers still seem to struggle with are the concrete system architecture and the fact, that Microservices are nothing else than Distributed Systems. Unfortunately it's exactly these knowledge areas that decide about success of failure of your project. For a little bit of background, I suggest reading the <a href="https://www.infoq.com/news/2017/02/microservices-design-observe" target="_blank">wonderful InfoQ interview with Uwe and Adrian</a> done by Daniel Bryant.
<br>
<br><b>Why Microservices again? Can't I just be happy and write EJBs and Servlets?</b>
<br>
 The key idea with microservices are the properties that support independence of the rest of the application landscape and quick evolvability. Additionally, they should scale independently and require less resources than application server based applications. In a world with constantly changing business requirements and growing number of application clients, centralised infrastructures are getting way to expensive to operate and scale towards unpredictable load or load peaks. If we all would be stuck with application servers, we wouldn't have Netflix, Twitter or Amazon. So: No. You can't just stay where you are.
<br>
<br><b>Microservices are Distributed Systems. What's so special about them?</b>
<br>
 The original definition of a distributed system: "A distributed system is a model in which components located on networked computers communicate and coordinate their actions by passing messages." (<a href="https://en.wikipedia.org/wiki/Distributed_computing" target="_blank">Wikipedia</a>) And this is exactly what happens in Microservices based architectures. The individual services are deployed to cloud instances, physically running somewhere and they exchange messages. This is a big difference to how we used to build centralised applications. Instead of having a bunch of servers in our datacenter that handle all kinds of synchronisation, transaction and failover scenarios on our behalf, we now have individual services which evolve independently and aren't tied to each other. There are some fundamental challenges that are unique to distributed computing. Among them is fault tolerance, synchronisation, self healing, backpressure, network splits, and many more.
<br>
<br><b>Aren't Distributed Systems what everybody calls Reactive Systems?</b>
<br>
 It's more complicated than that. And honestly, there is a lot going on with the word "Reactive" itself these days.&nbsp;To build an application or system out of individual Microservices, you need to use a set of design principles to make them Reactive, Resilient, Elastic and Message Driven. If that sounds familiar, you are probably right. The definition from the&nbsp;<a href="" target="_blank">Reactive Manifesto</a>. A distributed system which implements the four traits of the Reactive Manifesto is what should be called a <a href="https://www.lightbend.com/reactive-programming-versus-reactive-systems#reactive-systems-and-architecture" target="_blank">Reactive System</a>. You can read more about the <a href="http://www.oreilly.com/programming/free/reactive-microservices-architecture.html" target="_blank">design principles of Reactive Microservices Systems</a> in Jonas' book. The <a href="" target="_blank">Lagom framework</a> is build on those principles, but let me be clear, that you don't necessarily need a specific framework or product to build these kind of applications. Some of them just make you a hell lot more productive and your operations more effective. Hugh McKee has another <a href="https://www.lightbend.com/blog/designing-reactive-systems-with-the-actor-model-free-oreilly-book-by-hugh-mckee" target="_blank">free book on design principles for Actor based systems</a>.
<br>
<br><b>What are the options to build a Microservice based System?</b>
<br>
 I personally see two different trends of solving the problems related to Microservices today. First is to push the problems down to orchestration or datacenter operating or cloud systems like DC/OS, OpenShift, Cloudfoundry, and alike. &nbsp;The second solution is to natively handle them on the application or framework level (Akka, Vert.x, et al).
<br>
<br><b>One container per service, or why an Anaconda&nbsp;</b><b>shouldn't&nbsp;</b><b>swallow a horse.</b>
<br>
 Let's look a little more detailed at the first approach. Write a microservice, package it together with the runtime in a little container and push it to the cloud. As we're all full stack, DevOps developers these days, it's easy to create the meta information needed for cloud based runtimes. Thanks to my bootiful service, all relevant&nbsp;monitoring information is exposed already and I can easily detect failing services and restart them. And this for sure works. You can even use a full blown application server as Microservice runtime. Plus, there are a lot of magic frameworks (NetflixOSS) which help with fighting the distributed systems challenges. The drawback for me personally is the tight coupling with the infrastructure in this case. Your system won't be able to run on anything else but the platform of choice. Further on, they suggest that you just need to use containers to solve all problems in the Microservices world. Looking back at the Reactive Manifesto, these type of systems won't help you with the requirement to use&nbsp;<a href="http://www.reactivemanifesto.org/#message-driven" target="_blank">messaging</a> between services.
<br>
<br><b>Microservices without Containers? That's Peanut without Butter!</b>
<br>
 True. Containers do one thing very well. Package the complete stack in a controllable way into a deployable unit. They are isolation mechanisms on the infrastructure level. And having a container standard might actually be a good thing. So, keep your containers. But you need more. &nbsp;<a href="https://www.lightbend.com/reactive-programming-versus-reactive-systems" target="_blank">So the key to building Resilient, self-healing systems</a> is to allow failures to be: contained, reified as messages, sent to other components (that act as supervisors), and managed from a safe context outside the failed component. Here, being Message-driven is the enabler: moving away from strongly coupled, brittle, deeply nested synchronous call chains that everyone learned to suffer through…or ignore. The idea is to decouple the management of failures from the call chain, freeing the client from the responsibility of handling the failures of the server. No container or orchestration tooling will help you to integrate this. You are looking at Event Sourcing. &nbsp;The <a href="https://www.infoq.com/articles/microservices-aggregates-events-cqrs-part-2-richardson" target="_blank">design concepts</a> for an event-driven architecture, using event sourcing, align well with Microservices architecture patterns.
<br>
<br><b>Reactive Programming, Systems, Streams: Isn't that all the same?</b>
<br>
 Reactive has become an overloaded term and is now being associated with several different things to different people—in good company with words like “streaming”, “lightweight”, and “real-time. ”Reactive Programming offers productivity for Developers—through performance and resource efficiency—at the component level for internal logic and dataflow management. Reactive Systems offers productivity for Architects and DevOps—through resilience and elasticity—at the system level, for building Cloud Native or other large-scale distributed systems. You should really take the time and read how Jonas Bonér and Viktor Klang&nbsp;<a href="https://www.lightbend.com/reactive-programming-versus-reactive-systems" target="_blank">explain the individual differences between them</a>.
<br>
<br><b>Where can I learn more about how to design Reactive Microservices?</b>
<br>
 James Roper did a great talk at last year's Reactive Summit and took a hands on look at how the architecture of a system, including the flow of data, the types of communication used, and the way the system is broken down into components, will need to change as you decompose a monolith into a reactive microservice based system.
<br>
<br>
<div class="video-container">
 <iframe allowfullscreen frameborder="0" height="315" src="https://www.youtube.com/embed/ofxbypDz4h8?rel=0" width="560"></iframe>
</div>
<br>
 I did a talk at the CJUG about <a href="http://blog.eisele.net/2016/07/cqrs-with-java-and-lagom.html" target="_blank">CQRS for Java Developers</a> which gives you an intro. If you have particular topics that you are interested in, please let me know in the comments.
<br>
<br><b>More Reading for you&nbsp;</b>
<br>
<br>
<ul>
 <li>Jonas Bonér &amp; Viktor Klang Explain <a href="http://www.lightbend.com/blog/lightbend-podcast-reactive-programming-vs-reactive-systems-explained" target="_blank">Reactive Programming vs Reactive Systems in 20 min</a></li>
 <li>Konrad did a webinar lately about <a href="http://www.lightbend.com/blog/exploring-reactive-integrations-java8-akka-streams-alpakka-kafka" target="_blank">Reactive Integrations in Java 8 with Akka Streams, Alpakka and Kafka</a></li>
 <li><a href="http://www.lightbend.com/blog/the-basics-of-reactive-system-design-for-traditional-java-enterprises" target="_blank">The Basics Of Reactive System Design For Traditional Java Enterprises</a></li>
 <li>Duncan DeVore on <a href="http://www.lightbend.com/blog/understand-reactive-architecture-design-and-programming-in-less-than-12-minutes" target="_blank">Reactive Architecture, Design, And Programming In Less Than 12 Minutes</a></li>
</ul>
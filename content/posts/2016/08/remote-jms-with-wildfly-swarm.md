---
title: "Remote JMS with WildFly Swarm"
date: 2016-08-05 07:16:00 +0000
layout: post
tags: ["JMS", "wildfly swarm", "remoting"]
slug: "remote-jms-with-wildfly-swarm"
link: "2016/08/remote-jms-with-wildfly-swarm.html"
url: /2016/08/remote-jms-with-wildfly-swarm.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="screenshot.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="214" src="screenshot.png" width="320"></a>
</div> I'm blogging about WildFly swarm again? Short version is: I needed a test for remote JMS access and refused to setup something complex like a complete application server. The idea was to have a simple WildFly Swarm application which has a queue and a topic configured. Both should be accessible remotely from a standalone Java application. While the topic receives messages a Message Driven Bean (MDB) dumps the output to the console. The queue is filled with random text+timestamp messages by a singleton timer bean.
<br>
 Turned out, that WildFly Swarm can do it, but for now only in the snapshot release.
<br>
<br><b>The code</b>
<br>
 Find the complete code on my <a href="https://github.com/myfear/wfs-jms" target="_blank">GitHub repository</a>. It's not the most beautiful thing I have written but it actually shows you the <a href="https://github.com/myfear/wfs-jms/blob/master/src/main/java/sample/Main.java#L22" target="_blank">complete configuration of Swarm</a> with the relevant security settings, and the construction of the queue and the topic. In short the MessagingFraction needs the relevant security settings with remote access enabled and it also needs to define the remote topic. The NamingFraction needs to enable the remote naming service and finally the ManagamentFraction needs to define authorization handler.
<br>
<br><b>How to run the example</b>
<br>
 To run the server, you can just use 'mvn wildfly-swarm:run' after the startup, you see the timer bean starting to emit messages to the queue:
<br>
<br>
<div class="p1">
 <span class="s1">2016-08-05 08:44:48,003 INFO&nbsp; [sample.SampleQueueTimer] (EJB default - 5) Send: Test 1470379488003</span>
</div>
<div class="p1">
 <span class="s1">2016-08-05 08:44:49,005 INFO&nbsp; [sample.SampleQueueTimer] (EJB default - 6) Send: Test 1470379489005</span>
</div>
<br>
 If you point your browser to <a href="" target="_blank">http://localhost:8080/</a> you can trigger a <a href="https://github.com/myfear/wfs-jms/blob/master/src/main/java/sample/SampleTopicMDB.java#L24" target="_blank">single message being send to the topic</a>. This also get's logged to the console:
<br>
<br>
<div class="p1">
 <span class="s1">2016-08-05 08:44:36,220 INFO&nbsp; [sample.SampleTopicMDB] (Thread-250 (ActiveMQ-client-global-threads-859113460)) received: something</span>
</div>
<br>
 The real magic happens, when you look at the <a href="https://github.com/myfear/wfs-jms/blob/master/src/main/java/sample/client/HelloWorldJMSClient.java" target="_blank">standalone Java client</a>. It performs the <a href="https://github.com/myfear/wfs-jms/blob/master/src/main/java/sample/client/HelloWorldJMSClient.java#L50" target="_blank">relevant JNDI lookups</a>&nbsp;and <a href="https://github.com/myfear/wfs-jms/blob/master/src/main/java/sample/client/HelloWorldJMSClient.java#L61" target="_blank">creates the JMS connection</a> with user and password, the session and the producer and finally <a href="https://github.com/myfear/wfs-jms/blob/master/src/main/java/sample/client/HelloWorldJMSClient.java#L71" target="_blank">produces and sends a text message</a>.
<br>
 More about the "<a href="https://www.lightbend.com/blog/nine-lost-opportunities-when-using-java-ee-for-microservices" target="_blank">why the hell does he needs Java EE again</a>" in some upcoming blog posts ;)
<br>
<br><b>Credits</b>
<br>
 A super big thank you goes out to <a href="https://twitter.com/kenfinnigan" target="_blank">Ken Finnigan</a> who fixed <a href="https://github.com/wildfly-swarm/wildfly-swarm/commit/f2d02dcda71a41362d44e2c94f57ffca732a093e" target="_blank">the issue I ran into</a> literally over night!
<br>
<br>
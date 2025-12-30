---
title: "Application Server unresponsive or stuck? Take a deeper look!"
date: 2010-02-16 10:36:00 +0000
layout: post
tags: ["Java EE", "appserver", "monitoring", "unresponsive", "stuck"]
slug: "2010-02-16-application-server-unresponsive-or"
url: /2010/02/application-server-unresponsive-or.html
---

If you work with Java EE projects, you have probably seen this before. You have a big machine. Some GB of RAM, a couple of cores. And a "normal" application. Nothing to worry about. Profilling is unremarkable and everything works fine on your dev and test environments. This can change, if you do your first load test runs. 
<br>
 What the hell is happening? To figure this out, you have to take a deeper look at all parts of the system. Here is a very brief overview about what and where to look. Don't get me wrong. This is not a handbook or a guide. You have to attend more than a couple of optimizing session to be able to fully track down the problems and solve them. Anyway this is a good overview and could be extended to some kind of checklist.
<br>
<br><b>Your infrastructure</b>
<br>
 First thing is to make a map of your infrastructure. If you are doing load tests and you experience any kind of too low throughput or even unresponsive applications, you have to make shure you know everything about your setting. Ask yourself questions like:
<br>
 - How is the network structure? How fast is the network? How is the load?
<br>
 - Which components are in between your load agents and your server? (switches, router, dispatcher, httpd, etc.)
<br>
 - What about the database? (Separate maschine? Separate network? How is it's load?)
<br>
 - What about your appserver? (How many cores? How many RAM? How many HDD? eth cards?)
<br>
 - How is the cluster setup? (Loadbalancing? Failover?)
<br>
 There are probably many more information you should have. Try to figure out as much as possible. Even the slightest piece of information is valuable.
<br>
<br><b>Access</b>
<br>
 Before taking a deeper look at the details, I strongly advice you to request full access to the systems you are going to examine. You should for example have transparent access to any ports (JMX/Debug), the shell, the httpd status monitor, the appserver management utilities and so on. Without beeing able to gather all required information during the runs, you will not be able to find a solution. Working in such a setting is stupid and could even guide you in the wrong direction.
<br>
 This should be no problem for systems up to the integration stage. If you have to solve problems occuring in already productive systems you should think about different approaches. Beeing on-site working with operations probably is the best approach here. But let's stick to the test environment here.
<br>
<br><b>Reproduce the situation</b>
<br>
 If you know anything about your infrastructure and have access to any relevant component in it, you should take some effort in reproducing the situation. I have seen different cases, when this was not too easy. Try to play around with the load scenarios. Try different combinations of usecases, different load, try shorter ramp up times, try to overload the system. Without this, you will not be able to solve the issue.
<br>
<br><b>Collect metrics</b>
<br>
 If you finally reproduced your situation, you can start collecting the relevant metrics. First approach is to do this without any changes to the system. Depending on the infastructure, there are a couple of things to look at:
<br>
 - Appserver console/monitoring (JVM, DB Pools, Thread Usage, Pending Requests, and more)
<br>
 - Apache mod_status, mod_proxy (Thread Usage, Dispatching Behaviour, and more)
<br>
 - Database Monitoring (Connections, Usage, Load, Performance, and more)
<br>
 - System monitoring (I/O, Network, HDD, CPU, and more) 
<br>
<br>
 No.1 suspects are always any kind of external ressources. So, you should look after the db connections first. After that, look at the system ressources. Heap, Memory, CPU and further. Depending on your findings, you are able to eliminate the bottleneck.
<br>
<br><b>Extended metrics</b>
<br>
 If the basic metrics did not show any problems, you have to dig deeper. This is the point, where you start enabling external monitoring and extended tracing. 
<br>
 - Enable <a href="http://java.sun.com/javase/6/docs/technotes/guides/management/agent.html">JMX Management Agent </a> and connect via <a href="http://java.sun.com/developer/technicalArticles/J2SE/jconsole.html">JConsole </a>or your favorite JMX monitor
<br>
 - Enable <a href="http://java.sun.com/javase/technologies/hotspot/gc/gc_tuning_6.html">verbose GC output</a>
<br>
 - Enable extended diagnostic in your appserver (e.g. <a href="http://www.oracle.com/technology/pub/articles/cico-wldf.html">Oracle Weblogic WLDF</a>)
<br>
 - Use other visualizing/tracing tools available
<br>
<br><b>set screws</b>
<br>
 If you have all your metrics, you are basically on your own. There is nothing like a cookbook for solving your problems. But you did not really expected this, right? :)
<br>
 Anyway, there are a couple of things to do. First is to identify the ressource, that is causing the trouble. You do this by watching out for any hint for full or close to full ressource usage. This could be a connection pool or the JVM heap. Simplest case is to experiment with increasing the size. 
<br>
 Some of the extended metrics support you in identifying more special situations (e.g. stuck threads, memory leak). 
<br>
 If none of the above works, you are going to become a specialist in optimizing or performance tuning for your environment. This means, you have to look at the product documentation and other information around to find the things to change.
<br>
<br><b>time-consuming team game</b>
<br>
 Anyway, this is a team game. A time-consuming one. You have to work closely with operations, the dev team and the guys doing the load test. It is not too unusual that it takes some time. A typical load tests lasts about 60 minutes. Including ramp up and down, analysis, configuration changes and redeployment it could last 2 hours. Given an eight hour work day, this gives your time for four runs. Not too many, if you do not have a clue where to look.
<br>
<br><a class="lightbox" href="jconsole.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" src="jconsole.png"></a>
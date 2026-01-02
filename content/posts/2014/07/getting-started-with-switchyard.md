---
title: "Getting started with SwitchYard 2.0.0.Alpha1 on WildFly 8.1.0.Final"
date: 2014-07-29 08:57:00 +0000
layout: post
tags: ["wildfly", "SwitchYard"]
slug: "getting-started-with-switchyard"
link: "2014/07/getting-started-with-switchyard.html"
url: /2014/07/getting-started-with-switchyard.html
---

I've been sticking my head into some hot RedHat technologies lately and among the many interesting parts I found <a href="" target="_blank">SwitchYard</a>. Without being disrespectful towards everybody wrapping their heads around SOA and service oriented architectures in the past, this has always been kind of weird to me as a Java EE developer.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;" target="_blank"><img border="0" src="switchyard_icon_256px.png" height="200" width="200"></a>
</div> In the past I've been building component oriented applications with what I had at hand. Mostly driven by the features available in the Java EE standard to be "portable" and easy to use. Looking back this has been a perfect fit for many customers and applications. With an increasing demand for highly integrated applications which use already available services and processes from all over the place (departmental, central or even cloud services) this approach starts to feel more and more outdated. And this feel does not come from a technology perspective but from all the requirements around it. Having this in mind this post is the starting point of a series of how-to's and short tutorials which aim to showcase some more diverse ways of building (Java EE) applications that fit better into today's requirements and landscapes.
<br>
<br><b>What is SwitchYard?</b>
<br>
 It is a component-based development framework for integration applications using the design principles and best practices of Service Oriented Architecture. If you're expecting kind of a full-blown fancy BPMN/SOA buzz-word suite you're off by a bit. This is for developers and should make it comparably straight forward to use. It's been around for a while now and starting with latest 2.0.0.Alpha1 it is compatible with WildFly 8. Reasons enough for me to get you excited about it.
<br>
<br><b>Installing SwitchYard into latest WildFly 8.1.0.Final</b>
<br>
 Download both, the&nbsp;<a href="http://switchyard.jboss.org/downloads" target="_blank">switchyard-2.0.0.Alpha1-wildfly</a> bundle and <a href="" target="_blank">WildFly 8.1.0.Final</a> from the project websites. Install WildFly 8 by unzipping it into a folder of your choice (e.g.&nbsp;D:\wildfly-8.1.0.Final\). Now unzip the SwitchYard bundle into the WildFly folder.&nbsp;Depending on the zip utility in use, you may be prompted whether existing files should be replaced. &nbsp;Answer yes/all for all files being unzipped.
<br>
 It's an alpha so you have to tweak the configuration a bit because of&nbsp;<a href="https://issues.jboss.org/browse/SWITCHYARD-2158" target="_blank">SWITCHYARD-2158</a>. Open "JBOSS_HOME/standalone/configuration/standalone.xml" and search for "org.switchyard.component.camel.<b>atom</b>.deploy.CamelRSSComponent" and change the package from "atom" to "rss". Now go ahead and start the server with "JBOSS_HOME/bin/standalone.sh/.bat".
<br>
 If everything worked correctly you should see a message like this:
<br>
<pre>09:18:25,857 INFO [org.jboss.as] (Controller Boot Thread) JBAS015874: WildFly 8.1.0.Final "Kenny" started in 3712ms - Started 210 of 259 services (81 services are lazy, passive or on-demand) </pre>
<br><b>Building and Deploying the Bean Service Quickstart</b>
<br>
 If you want to get your hands dirty you can easily start with the packaged applications in the "JBOSS_HOME/quickstarts/" directory of the distribution. A simple one is the bean-service example. It makes use of one of the core components of SwitchYard, the <a href="https://docs.jboss.org/author/display/SWITCHYARD/Bean" target="_blank">Bean Component</a>.&nbsp;It allows Java classes (or beans) to provide and consume services.&nbsp;And therefore you can implement a service by simply annotating a Java class or consume one by injecting a reference directly into your Java class.
<br>
 And because the Bean Component is a standard CDI Extension, there is no need to learn a new programming model to use it. It's just a standard CDI Bean with a few more annotations.
<br>
 For existing Java EE applications this means, that you can expose existing CDI-based beans in your application as services to the outside world or consume services within your bean by just adding some more annotations.
<br>
 First things first. We need to tweak around a bit in the project pom.xml to make this work. Go to the build section and replace the "jboss-as-maven-plugin" with the latest version of the
<br>
<pre class="brush:xml">&lt;groupId&gt;org.wildfly.plugins&lt;/groupId&gt; &lt;artifactId&gt;wildfly-maven-plugin&lt;/artifactId&gt; &lt;version&gt;1.0.2.Final&lt;/version&gt; </pre> Now run "mvn package" to download all dependencies and execute the tests. It should just work fine and state:
<br>
<pre>Tests run: 6, Failures: 0, Errors: 0, Skipped: 0 </pre> Let's deploy it to our WildFly instance by issuing "mvn -Pdeploy install". The WildFly console finally lets you know about the successful execution:
<br>
<pre>10:19:44,636 INFO [org.jboss.as.server] (management-handler-thread - 1) JBAS018559: Deployed "switchyard-bean-service.jar" (runtime-name : "switchyard-bean-service.jar") </pre>
<br><b>Quick Test For The Application</b>
<br>
 A very quick test is to execute mvn exec:java which will execute the BeanClient class and fire a SOAP request towards the deployed service. The output should be:
<br>
<pre class="brush:xml">SOAP Reply: &lt;soap:envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope"&gt;&lt;env:header xmlns:env="http://www.w3.org/2003/05/soap-envelope"&gt;&lt;/env:header&gt;&lt;soap:body&gt;&lt;ord ers:submitorderresponse="" xmlns:orders="urn:switchyard-quickstart:bean-service:1.0 "&gt;&lt;orderack&gt;&lt;orderid&gt;PO-19838-XYZ&lt;/orderid&gt;&lt;accepted&gt;true&lt;/accepted&gt;&lt;status&gt;Orde r Accepted [intercepted]&lt;/status&gt;&lt;/orderack&gt;&lt;/ord&gt;&lt;/soap:body&gt;&lt;/soap:envelope&gt;</pre>
<br>
 That is it for today. The next parts will examine the sample application in a bit more detail and install the tooling and introduce you to various other components. If you can't wait, check out:
<br>
<ul>
 <li>the <a class="jive-link-external-small" href="https://docs.jboss.org/author/display/SWITCHYARD/Home" rel="nofollow" target="_blank">SwitchYard Documentation</a> which contains a whole bunch of useful stuff.</li>
 <li>some awesome videos and learn all about SwitchYard in our new <a class="jive-link-wiki-small" data-containerid="2239" data-containertype="14" data-objectid="48285" data-objecttype="102" href="https://community.jboss.org/docs/DOC-48285" target="_blank">SwitchYard Video Series</a>.</li>
 <li>the other&nbsp;<a class="jive-link-external-small" href="https://github.com/jboss-switchyard/quickstarts" style="color: #355491;" target="_blank">Quickstart applications.</a>&nbsp;&nbsp;</li>
</ul>
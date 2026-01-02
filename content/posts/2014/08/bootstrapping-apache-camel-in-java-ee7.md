---
title: "Bootstrapping Apache Camel in Java EE7 with WildFly 8"
date: 2014-08-30 11:30:00 +0000
layout: post
tags: ["wildfly", "javaee7", "Camel"]
slug: "bootstrapping-apache-camel-in-java-ee7"
link: "2014/08/bootstrapping-apache-camel-in-java-ee7.html"
url: /2014/08/bootstrapping-apache-camel-in-java-ee7.html
---

Since Camel version 2.10 there is support for CDI (JSR-299) and DI (JSR-330). This offers new opportunities to develop and deploy Apache Camel projects in Java EE &nbsp;containers but also in standalone Java SE or CDI containers. Time to try it out and get familiar with it.
<br>
<br><b>What exactly is Camel?</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="camel-box-small.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" src="camel-box-small.png"></a>
</div> Camel is an integration framework. Some like to call it ESB-lite. But in the end, it is a very developer and component focused way of being successful at integration projects. You have more than 80 pre-build components to pick from and with that it basically contains a complete coverage of the Enterprise Integration Pattern which are well known and state of the art to use. With all that in mind, it is not easy to come up with a single answer. If you need one, it could be something like this: It is messaging technology glue with routing. It joins together messaging start and end points allowing the transference of messages from different sources to different destinations.
<br>
<br><b>Why Do I Care?</b>
<br>
 I'm obviously excited about enterprise grade software. But always been a fan of more pragmatic solutions. There's been some good blog posts, about when to use <a href="" target="_blank">Apache Camel</a> and with the growing need to integrate different systems over very heterogeneous platforms it is always handy to have a mature solutions at hand. Most of the samples out there start with bootstrapping the complete Camel magic, including the XML based Spring DSL and with it the mandatory dependencies. That blows everything up to a extend I don't want to accept. Knowing that there has to be a lightweight way of doing it (Camel-Core is 2.5 MB at Version 12.13.2) I was looking into how to bootstrap it myself. And use some of it's CDI magic.
<br>
<br><b>The Place to Look for Ideas first</b>
<br>
 Is obviously the&nbsp;<a href="" target="_blank">Java EE samples project on GitHub</a>. Some restless community members collected an awesome amount of examples for you to get started with.&nbsp;The ultimate goal here is to be a reference for how to use the different specifications within the Java EE umbrella. But even <a href="https://github.com/javaee-samples/javaee7-samples/tree/master/extra" target="_blank">some first extra bits</a> have been included and showcase an example from different areas like NoSQL, Twitter, Quartz Scheduling and last but not least Camel integration. If you run it as it is in latest WildFly 8.1 it is not working. The cdi extension of Camel makes it a bit tricky to do it, but as mentioned in the <a href="https://github.com/javaee-samples/javaee7-samples/issues/36" target="_blank">corresponding issue</a>, there is a way to get rid of the&nbsp;ambiguous CDI dependency by just creating a custom veto extension. The <a href="https://issues.apache.org/jira/browse/CAMEL-7760" target="_blank">issue is filed with Camel</a> and I heard, that they are looking into improving the situation. If you want to to try out the example, go to my GitHub repository and look for the <a href="https://github.com/myfear/CamelEE7" target="_blank">CamelEE7</a> project.
<br>
<br><b>How Did I Do It?</b>
<br>
 The <a href="https://github.com/myfear/CamelEE7/blob/master/src/main/java/org/javaee7/extra/camel/Bootstrap.java" target="_blank">Bootstrap.java</a> is a @Singleton EJB which is loaded on application startup (remember, there are different <a href="http://blog.eisele.net/2010/12/seven-ways-to-get-things-started-java.html" target="_blank">ways to start up things in Java EE</a>) and by @Inject ing an&nbsp;org.apache.camel.cdi.CdiCamelContext you get access to Camel. The tiny example uses another <a href="https://github.com/myfear/CamelEE7/blob/master/src/main/java/org/javaee7/extra/camel/HelloCamel.java" target="_blank">HelloCamel</a> bean to show how to work with payload in the CDI integration.
<br>
 Make sure to look at the&nbsp;<a href="https://github.com/myfear/CamelEE7/blob/master/src/main/java/org/javaee7/extra/camel/CamelCdiVetoExtension.java" target="_blank">CamelCdiVetoExtension.java</a>&nbsp;and how it is configured in the META-INF folder. Now you're ready to go. Happy Coding.
<br>
<br><b>And The Best For Last</b>
<br>
 Camel 12.14 is <a href="http://www.davsclaus.com/2014/08/apache-camel-214-on-way.html" target="_blank">on the horizon already</a>, scheduled to be released in September. If you have issues or wishes you want to see in it, now is the <a href="http://camel.465427.n5.nabble.com/Release-date-of-Camel-2-14-0-td5754561.html" target="_blank">time to speak up</a>!
<br>
 Excerpt of the awesome new features, that are upcoming:
<br>
<br>
<ul>
 <li><a href="http://camel.apache.org/metrics-component.html" target="_blank">Metrics Component</a></li>
 <li><a href="http://camel.apache.org/rest-dsl" target="_blank">DSL for rest services</a></li>
 <li><a href="http://camel.apache.org/swagger" target="_blank">Swagger Component</a></li>
</ul>
<br>
 Time to get excited!
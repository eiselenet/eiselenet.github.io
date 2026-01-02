---
title: "Hibernate Statistics with Hawtio and Jolokia"
date: 2014-07-31 08:39:00 +0000
layout: post
tags: ["Hibernate", "jpa", "Jolokia", "Hawtio"]
slug: "hibernate-statistics-with-hawtio-and"
link: "2014/07/hibernate-statistics-with-hawtio-and.html"
url: /2014/07/hibernate-statistics-with-hawtio-and.html
---

A huge part of enterprise Java deals with data. Among all the different ways of working with data in enterprise settings, there is still the proven and widely taught approach to use O/R mapping of any kind. The JPA standard makes this comparably easy to use for everybody and it should also be portable. But let's not talk about migration details. The biggest drawback of O/R mapping is, that a developer tend to lose contact with what's happening on the database or even to which exact SQL statements get issued against it. This is the number one reason that those projects run into performance issues. If you're there, you need to analyze the root causes and drill down to the problems. I recently found a nice feature of Hibernate which makes this comparably easy.
<br>
<br><b>Available Statistics And Ways To Get Them.</b>
<br>
 Hibernate up to 4.2 ships with a statistics and metrics API that allows you to figure out a lot about what is happening under the covers. All available counters are described in the <a href="http://docs.jboss.org/hibernate/core/3.5/api/index.html?org/hibernate/stat/Statistics.html" target="_blank">Statistics interface API</a>, in three categories:
<br>
<ul>
 <li>Metrics related to the general Session usage, such as number of open sessions, retrieved JDBC connections, etc.</li>
 <li>Metrics related to the entities, collections, queries, and caches as a whole (aka global metrics).</li>
 <li>Detailed metrics related to a particular entity, collection, query or cache region.</li>
</ul> For example, you can check the cache hit, miss, and put ratio of entities, collections and queries, and the average time a query needs. Be aware that the number of milliseconds is subject to approximation in Java. Hibernate is tied to the JVM precision and on some platforms this might only be accurate to 10 seconds.
<br>
<br>
 Simple getters are used to access the global metrics (i.e. not tied to a particular entity, collection, cache region, etc.). You can access the metrics of a particular entity, collection or cache region through its name, and through its HQL or SQL representation for queries. Please refer to the Statistics, <a href="http://docs.jboss.org/hibernate/core/3.5/api/index.html?org/hibernate/stat/EntityStatistics.html" target="_blank">EntityStatistics</a>, <a href="http://docs.jboss.org/hibernate/core/3.5/api/index.html?org/hibernate/stat/CollectionStatistics.html" target="_blank">CollectionStatistics</a>, <a href="http://docs.jboss.org/hibernate/core/3.5/api/index.html?org/hibernate/stat/SecondLevelCacheStatistics.html" target="_blank">SecondLevelCacheStatistics</a>, and <a href="http://docs.jboss.org/hibernate/core/3.5/api/index.html?org/hibernate/stat/QueryStatistics.html" target="_blank">QueryStatistics </a>API Javadoc for more information.
<br>
<br>
 All you have to do is enable statistics for the session factory you're interested in and retrieve the statistics to analyze them. There are plenty of examples out there how to use this feature with Spring. The reason is pretty simple: Spring comes with a legendary&nbsp;MBeanExporter which exposes JMX MBeans as Java Objects. And guess what: Hibernate Statistics provides an easy way of exposing them through JMX. But there is no need to use Spring if you just put together some more RedHat magic :)
<br>
 You basically have two different ways of enabling the statistics in your configured setting. The easiest way is to add a property to your persistence-unit configuration:
<br>
<pre>&nbsp; &nbsp;&lt;property name="hibernate.generate_statistics" value="true"/&gt;</pre> But it is also possible to enable them manually. More details on how to do that can be found on the <a href="https://community.jboss.org/wiki/PublishingStatisticsThroughJMX" target="_blank">community wiki</a>&nbsp;and in the performance-monitoring section in the <a href="http://docs.jboss.org/hibernate/core/3.5/reference/en/html/performance.html#performance-monitoring" target="_blank">Hibernate documentation</a>.
<br>
<br><b>Enabling and Exposing Statistics By Example</b>
<br>
 I created a little example standalone Hibernate application with two entities and a main class which is working with hibernate and initializing everything you need to know. Get your hands on it instantly by <a href="https://github.com/myfear/HibernateStatistics" target="_blank">forking it on GitHub</a>. Here is the little walk-through:
<br>
 There are the two mandatory entities (Department and Employee) and the META-INF/persistence.xml. This is the basic setting. There is not much magic in here. You can see where to enable the statistics (potentially) in the <a href="https://github.com/myfear/HibernateStatistics/blob/master/src/main/resources/META-INF/persistence.xml" target="_blank">persistence.xml</a>. The example enables them in the main class <a href="https://github.com/myfear/HibernateStatistics/blob/master/src/main/java/net/eisele/hibernatestatistics/jpa/JpaTest.java" target="_blank">JpaTest</a>. But let's start at the beginning. The main method performs the following steps in order:
<br>
<ol>
 <li>Create the EntityManager to use.</li>
 <li>&nbsp;Register the Statistics Mbean we need.</li>
 <li>Initialize the Jolokia Server to expose JMX via JSON for Hawtio</li>
 <li>Does something with the entities.</li>
</ol>
<div>
 The magic starts in step two which is in the&nbsp;registerHibernateMBeans(EntityManager manager) method. It get's hand on the PlatformMBeanServer, registers the relevant Hibernate JMX Mbean, set's the Session Factory in which we're interested in to it and enables the statistics. That is easy. Now you have a JMX MBean "Hibernate" with the attribute "statistics" registered. If you have access to the server via JConsole or Mission Control or VisualVM you can simply connect to the process and browse through the statistics:
</div>
<div>
 <br>
</div>
<table align="center" cellpadding="0" cellspacing="0" class="tr-caption-container" style="margin-left: auto; margin-right: auto; text-align: center;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="jconsole.PNG" imageanchor="1" style="margin-left: auto; margin-right: auto;"><img border="0" src="jconsole.PNG" height="199" width="320"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;">Hibernate MBean in JConsole</td>
  </tr>
 </tbody>
</table>
<div>
 In production environments this typically isn't possible at all. So you would need to find a way to access this via http/https. This is where I found it handy to try out <a href="" target="_blank">Hawtio</a>&nbsp;as a&nbsp;a modular web console for managing your Java stuff. Burned down to the basics it is a web-console with plugins. It has a <a href="http://hawt.io/plugins/index.html" target="_blank">ton of plugins</a> and can be customized and extended to fit your needs. Today we're looking at a very simple plugin, the <a href="" target="_blank">JMX plugin</a>.&nbsp;It gives you a raw view of the underlying JMX metric data, allowing access to the entire JMX domain tree of MBeans. But in order to make this happen, we first need to find a way to expose the JMX features to Hawtio. This is where <a href="" target="_blank">Jolokia</a> comes in. There is a <a href="http://jolokia.org/agent/jvm.html" target="_blank">JVM agent</a> in it who can expose JMX MBeans via JSON. All you have to do is to init and start the server like this:
</div>
<br>
<pre>JolokiaServerConfig config = new JolokiaServerConfig(new HashMap&lt;String, String&gt;()); JolokiaServer jolokiaServer = new JolokiaServer(config, true); jolokiaServer.start();</pre>
<br>
<div class="separator" style="clear: both; text-align: center;">
</div> Now you're ready to try out the Hawtio console. Have a look at the <a href="http://hawt.io/getstarted/index.html" target="_blank">quickstart </a>to see what is possible. For this example I just use the Google Chrome Extension which you just have to download and drag into your extensions page in Chrome. This looks like:
<br>
<br>
<table align="center" cellpadding="0" cellspacing="0" class="tr-caption-container" style="margin-left: auto; margin-right: auto; text-align: center;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="hawtio-chrome.PNG" imageanchor="1" style="margin-left: auto; margin-right: auto;"><img border="0" src="hawtio-chrome.PNG" height="183" width="320"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;">Hawtio Extension in Chrome</td>
  </tr>
 </tbody>
</table> If you configure "localhost", "8778" and path "jolokia" you're all set to start browsing your results. After you click "connect" you can look through the dashboard or switch to the JMX view and navigate to the Hibernate MBean:
<br>
<br>
<table align="center" cellpadding="0" cellspacing="0" class="tr-caption-container" style="margin-left: auto; margin-right: auto; text-align: center;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="hawtio-jmx.PNG" imageanchor="1" style="margin-left: auto; margin-right: auto;"><img border="0" src="hawtio-jmx.PNG" height="167" width="320"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;">Browsing JMX MBeans with Hawtio</td>
  </tr>
 </tbody>
</table> There is a more comprehensive introduction to Hawtio by Stan Lewis from DevNation 2014 waiting for you to watch it:
<br>
<br><iframe allowfullscreen frameborder="0" height="260" src="//www.youtube.com/embed/Bxgk9--_WzE" width="460"></iframe> That was the short example. Go ahead and look at the GitHub source-code and feel free to look into Hawtio a bit more: 
<br>
<ul>
 <li>Read the <a href="http://hawt.io/getstarted/index.html" target="_blank">getting started guide</a> to find out how to download and install&nbsp;Hawtio&nbsp;in your own environment.</li>
 <li><span style="background-color: white; color: #333333; font-family: 'Source Sans Pro'; font-size: 16px; line-height: 22.85714340209961px;">Read up on how to <a href="http://hawt.io/configuration/index.html" target="_blank">configure&nbsp;<span style="color: black; font-family: 'Times New Roman'; font-size: small; line-height: normal;">Hawtio&nbsp;</span>in various environments</a>, such as configuring security and where&nbsp;<span style="color: black; font-family: 'Times New Roman'; font-size: small; line-height: normal;">Hawtio&nbsp;</span>stores stuff.</span></li>
 <li><span style="color: #333333; font-family: Source Sans Pro;"><span style="background-color: white; line-height: 22.85714340209961px;">See how to <a href="http://www.christianposta.com/blog/?p=403" target="_blank">configure Hawtio on WildFly</a>.</span></span></li>
 <li><span style="color: #333333; font-family: Source Sans Pro;">We prefer to use the <a href="https://github.com/hawtio/hawtio/issues?state=open" target="_blank">issue tracker for dealing with ideas and issues</a>, but if you just want to chat about all things&nbsp;<span style="color: black; font-family: 'Times New Roman';">Hawtio</span><span style="color: black; font-family: 'Times New Roman';">&nbsp;</span>please join us on the <a href="https://groups.google.com/forum/#!forum/hawtio" target="_blank">mailing list</a>.</span></li>
 <li><span style="color: #333333; font-family: Source Sans Pro;">Find the <a href="http://github.com/hawtio/hawtio" target="_blank">Hawtio source-code on GitHub</a>.</span></li>
</ul>
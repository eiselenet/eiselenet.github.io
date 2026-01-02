---
title: "Monitoring DevOps Style With WildFly 9 And Jolokia"
date: 2015-07-20 12:30:00 +0000
layout: post
tags: ["DevOps", "wildfly", "metrics", "Jolokia"]
slug: "monitoring-devops-style-with-wildfly-9"

url: /2015/07/monitoring-devops-style-with-wildfly-9.html
---

DevOps is among the hottest topic these days. And the wide range of topics around it makes it hard to actually find a complete description or something that covers everything on a decent granularity. One thing is for sure: One of the most important parts is to deliver the correct metrics and and information for monitoring of the application.
<br>
<br><b>Java EE and JMX</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="visual-vm.PNG" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="144" src="visual-vm.PNG" width="320"></a>
</div> The standard way of monitoring Java EE servers is JMX. This is possible with tools like JConsole, VisualVM or the Oracle Mission-Control Suite. There are a bunch of advantages to this approach and most of the operation teams actually used this a lot in the past. But it doesn't exactly works the DevOps-way. It is a separate tooling and the DevOps-teams don't have a good way to actually script this without having all the tooling and operational systems (Nagios, etc.) installed. Today it feels a lot more natural and is easier to use to have http endpoints which expose configuration and runtime information.
<br>
<br><b>Jolokia - JMX To HTTP With JSON</b>
<br>
 A very convenient way to do this for JMX is to use <a href="" target="_blank">Jolokia</a>.&nbsp;Jolokia is a JMX-HTTP bridge giving an alternative to JSR-160 connectors. It is an agent based approach with support for many platforms. In addition to basic JMX operations it enhances JMX remoting with unique features like bulk requests and fine grained security policies. It comes bundled with a lot of JBoss projects lately (e.g. WIldFly-Camel subsystem) and can be easily used in your own applications.
<br>
<br><b>A Simple Java EE 7 App Equipped With Jolokia</b>
<br>
 Just create a simple Java EE 7 project (maybe with <a href="http://www.adam-bien.com/roller/abien/entry/setting_up_java_ee_7" target="_blank">Adam Bien's maven artifact</a>) and add one dependency to it:
<br>
<pre class="code"><code>&lt;dependency&gt; &nbsp; &nbsp; &lt;groupId&gt;org.jolokia&lt;/groupId&gt; &nbsp; &nbsp; &lt;artifactId&gt;jolokia-core&lt;/artifactId&gt; &nbsp; &nbsp; &lt;version&gt;1.3.1&lt;/version&gt; &lt;/dependency&gt; </code></pre> The next step is to configure the Jolokia AgentServlet in your web.xml and map it to a pattern which suits your needs:
<br>
<pre class="code"><code>&nbsp; &lt;servlet&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;servlet-name&gt;jolokia-agent&lt;/servlet-name&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;servlet-class&gt;org.jolokia.http.AgentServlet&lt;/servlet-class&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;load-on-startup&gt;1&lt;/load-on-startup&gt; &nbsp; &nbsp; &lt;/servlet&gt; &nbsp; &nbsp; &lt;servlet-mapping&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;servlet-name&gt;jolokia-agent&lt;/servlet-name&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;url-pattern&gt;/metrics/*&lt;/url-pattern&gt; &nbsp; &nbsp; &lt;/servlet-mapping&gt; </code></pre> Build your application as usual and access the relevant metrics as you need them. The complete .<a href="https://jolokia.org/reference/html/protocol.html#jolokia-operations" target="_blank">Jolokia reference explains the different operations and types</a>.
<br>
<br><b>Deploy Your Application To WildFly 9</b>
<br><a href="" target="_blank">Download and unzip WildFly 9</a> to a folder of your choice. Startup with bin/standalone.xml.
<br>
<br><b>Example Metrics</b>
<br>
 While you can access every JMX MBean, that is defined in the server, here is a list of metrics, that might help you out of the box.
<br>
<br>
 Heap memory usage:
<br>
 http://localhost:8080/javaee-devops/metrics/read/java.lang:type=Memory/HeapMemoryUsage
<br>
<pre>\{ &nbsp; &nbsp; "request": \{ &nbsp; &nbsp; &nbsp; &nbsp; "mbean": "java.lang:type=Memory", &nbsp; &nbsp; &nbsp; &nbsp; "attribute": "HeapMemoryUsage", &nbsp; &nbsp; &nbsp; &nbsp; "type": "read" &nbsp; &nbsp; \}, &nbsp; &nbsp; "value": \{ &nbsp; &nbsp; &nbsp; &nbsp; "init": 67108864, &nbsp; &nbsp; &nbsp; &nbsp; "committed": 241696768, &nbsp; &nbsp; &nbsp; &nbsp; "max": 477626368, &nbsp; &nbsp; &nbsp; &nbsp; "used": 141716336 &nbsp; &nbsp; \}, &nbsp; &nbsp; "timestamp": 1437392335, &nbsp; &nbsp; "status": 200 \}</pre> Overview over your server environment:
<br>
 http://localhost:8080/javaee-devops/metrics/read/jboss.as:core-service=server-environment
<br>
<br>
 You could not only read JMX attributes but also execute operations, like accessing the latest 10 lines of the server.log file:
<br>
 http://localhost:8080/javaee-devops/metrics/exec/jboss.as.expr:subsystem=logging/readLogFile/server.log/UTF-8/10/0/true
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="wildfly-read-logfile.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="90" src="wildfly-read-logfile.PNG" width="400"></a>
</div>
<br>
<br><b>Securing The Endpoint</b>
<br>
 As you would have expected, the AgentServlet is accessible like your application is. In order to prevent this, you will have to secure it. Good news is, that this is possible with basic authentication and the application realm in WildFly. Fist step is to add a user to the application realm. This can be done with the bin/add-user.sh|bat script. Make sure to add the role "SuperUser". Now add the following to your web.xml:
<br>
<pre class="code"><code>&nbsp; &nbsp; &lt;security-constraint&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;display-name&gt;Metrics Pages&lt;/display-name&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;web-resource-collection&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;web-resource-name&gt;Protected Metrics Site&lt;/web-resource-name&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;description&gt;Protected Metrics Site&lt;/description&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;url-pattern&gt;/metrics/*&lt;/url-pattern&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/web-resource-collection&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;auth-constraint&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;description/&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;role-name&gt;SuperUser&lt;/role-name&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/auth-constraint&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;user-data-constraint&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;transport-guarantee&gt;NONE&lt;/transport-guarantee&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/user-data-constraint&gt; &nbsp; &nbsp; &lt;/security-constraint&gt; &nbsp; &nbsp; &nbsp;&lt;login-config&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;auth-method&gt;BASIC&lt;/auth-method&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;realm-name&gt;ApplicationRealm&lt;/realm-name&gt; &nbsp; &nbsp; &lt;/login-config&gt; &nbsp; &nbsp; &lt;security-role&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;role-name&gt;SuperUser&lt;/role-name&gt; &nbsp; &nbsp; &lt;/security-role&gt; </code></pre> One last thing to do here is to add a file to WEB-INF/ called jboss-web.xml. This will just contain three lines:
<br>
<pre class="code"><code>&lt;jboss-web&gt; &nbsp; &nbsp; &lt;security-domain&gt;other&lt;/security-domain&gt; &lt;/jboss-web&gt; </code></pre> Whenever you try to access the metrics endpoint the server now challenges you with a basic authentication request.
<br>
<br><b>Looking For More?</b>
<br>
 This is just a simple example for now based on the standard JMX metrics, which WildFly exposes. You can for sure register your own MBeans or expand this by aggregating the individual calls into one single. Another option is, to use <a href="" target="_blank">hawt.io</a> as a ready to use, extensible UI which already provides all kinds of metrics for WildFly and many other subsystems. But this is a very straight forward way. Next major release of Jolokia might feature some more to make the DevOps ride a lot more convenient.
---
title: "PrimeFaces Push with Atmosphere on GlassFish 3.1.2.2"
date: 2012-09-06 06:20:00 +0000
layout: post
tags: ["atmosphere", "glassfish", "primefaces"]
slug: "primefaces-push-with-atmosphere-on"
link: "2012/09/primefaces-push-with-atmosphere-on.html"
url: /2012/09/primefaces-push-with-atmosphere-on.html
---

<a href="http://blog.primefaces.org/?p=2160" target="_blank">PrimeFaces 3.4</a> came out three days ago. Beside the usual awesomeness of new and updated components it also includes the new PrimeFaces Push framework. Based on <a href="" target="_blank">Atmosphere</a> this is providing easy push mechanisms to your applications.&nbsp;Here is how to configure and run it on latest GlassFish 3.1.2.2.
<br>
<br><b>Preparations</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="push_demo.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="174" src="push_demo.png" width="320"></a>
</div> As usual you should have some <a href="http://www.oracle.com/technetwork/java/javase/overview/index.html" target="_blank">Java</a>, <a href="" target="_blank">Maven</a> and <a href="" target="_blank">GlassFish</a> installed. If you need it out of one hand give <a href="" target="_blank">NetBeans 7.2</a> a try. It is the latest and greatest and comes with all the things you need for this example. Install the parts or the whole to a location of your choice and start with creating a new GlassFish domain:
<br>
<pre>asadmin create-domain pf_push </pre> accept the default values and start your domain
<br>
<pre>asadmin start-domain pf_push</pre> Now you have to <a href="http://docs.oracle.com/cd/E18930_01/html/821-2418/ggrgt.html" target="_blank">enable Comet support</a> for your domain. Do this either by using the http://&lt;host&gt;:4848/ admin ui or with the following command:
<br>
<pre>asadmin set server-config.network-config.protocols.protocol.http-1.http.comet-support-enabled="true"</pre> That is all you have to do to configure your domain.
<br>
<br><b>The Maven Project Setup</b>
<br>
 Now switch to your IDE and create a new&nbsp;Maven based Java EE 6 project. Add the primefaces repository to the &lt;repositories&gt; section and add the primefaces dependency to your project &lt;dependencies&gt; section or your project's pom.xml:
<br>
<br>
<pre class="brush:xml">&nbsp; &lt;repository&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;url&gt;http://repository.primefaces.org/&lt;/url&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;id&gt;primefaces&lt;/id&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;layout&gt;default&lt;/layout&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;name&gt;Repository for library PrimeFaces 3.2&lt;/name&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/repository&gt; &nbsp;&lt;dependency&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;groupId&gt;org.primefaces&lt;/groupId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;artifactId&gt;primefaces&lt;/artifactId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;version&gt;3.4&lt;/version&gt; &lt;/dependency&gt; </pre> Additionally we need the latest Atmosphere dependency (Contrats to <a href="https://plus.google.com/112015192190630480539" target="_blank">JeanFrancois Arcand</a> for this release)
<br>
<pre class="brush:xml">&lt;dependency&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;groupId&gt;org.atmosphere&lt;/groupId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;artifactId&gt;atmosphere-runtime&lt;/artifactId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;version&gt;1.0.0&lt;/version&gt; &lt;/dependency&gt; </pre> It is using Log4j and if you need to have some more output it is a good idea to also include the corresponding configuration or bridge it to JUL with slf4j. To do the later, simply include the following to your pom.xml: 
<br>
<pre class="brush:xml">&nbsp;&lt;dependency&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;groupId&gt;org.slf4j&lt;/groupId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;artifactId&gt;slf4j-api&lt;/artifactId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;version&gt;1.6.6&lt;/version&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/dependency&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;dependency&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;groupId&gt;org.slf4j&lt;/groupId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;artifactId&gt;slf4j-jdk14&lt;/artifactId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;version&gt;1.6.6&lt;/version&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/dependency&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;dependency&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;groupId&gt;org.slf4j&lt;/groupId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;artifactId&gt;log4j-over-slf4j&lt;/artifactId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;version&gt;1.6.6&lt;/version&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/dependency&gt; </pre> There is only one thing left to do. The PrimePush component needs to have its servlet channel registered. So, open your web.xml and add the following to it: 
<br>
<pre class="brush:xml">&lt;servlet&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;servlet-name&gt;Push Servlet&lt;/servlet-name&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;servlet-class&gt;org.primefaces.push.PushServlet&lt;/servlet-class&gt; &lt;/servlet&gt; &lt;servlet-mapping&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;servlet-name&gt;Push Servlet&lt;/servlet-name&gt; &nbsp; &nbsp; &nbsp; &nbsp; &lt;url-pattern&gt;/primepush/*&lt;/url-pattern&gt; &lt;/servlet-mapping&gt;</pre> That was it! On to the code! 
<br>
<br><b>The Code</b>
<br>
 I'm going to use the example referred to in the <a href="http://www.primefaces.org/documentation.html" target="_blank">PrimeFaces users guide</a>. A very simple example which has a global counter which could be incremented.
<br>
<pre class="brush:java">import java.io.Serializable; import javax.faces.bean.ManagedBean; import javax.faces.bean.SessionScoped; import org.primefaces.push.PushContext; import org.primefaces.push.PushContextFactory; /** &nbsp;* Counter is a global counter where each button click increments the count &nbsp;* value and new value is pushed to all subscribers. &nbsp;* &nbsp;* @author eiselem &nbsp;*/ @ManagedBean @SessionScoped public class GlobalCounterBean implements Serializable \{ &nbsp; &nbsp; private int count; &nbsp; &nbsp; public int getCount() \{ &nbsp; &nbsp; &nbsp; &nbsp; return count; &nbsp; &nbsp; \} &nbsp; &nbsp; public void setCount(int count) \{ &nbsp; &nbsp; &nbsp; &nbsp; this.count = count; &nbsp; &nbsp; \} &nbsp; &nbsp; public synchronized void increment() \{ &nbsp; &nbsp; &nbsp; &nbsp; count++; &nbsp; &nbsp; &nbsp; &nbsp; PushContext pushContext = PushContextFactory.getDefault().getPushContext(; &nbsp; &nbsp; &nbsp; &nbsp; pushContext.push("/counter", String.valueOf(count)); &nbsp; &nbsp; \} \} </pre> The PushContext contains the whole magic here. It is mainly used to publish and schedule messages and manage listeners and more. It is called from your facelet. This looks simple and familiar:
<br>
<pre class="brush:xml">&lt;h:form id="counter"&gt; &lt;h:outputText id="out" value="#\{globalCounterBean.count\}" styleClass="display" /&gt; &lt;p:commandButton value="Click" actionListener="#\{globalCounterBean.increment\}" /&gt; &lt;/h:form&gt; </pre> This basically does nothing, except incrementing the counter. So you have to add some more magic for connecting to the push channel. Add the following below the form:
<br>
<pre class="brush:xml">&lt;p:socket channel="/counter" &gt; &lt;p:ajax event="message" update="counter:out" /&gt; &lt;/p:socket&gt; </pre> &lt;p:socket /&gt; is the PrimeFaces component that handles the connection between the server and the browser. It does it by defining a communication channel and a callback to handle the broadcasts. The contained &lt;p:ajax /&gt; component listens to the message event and updates the counter field in the form. This however requires and additional server round-trip. You could also shortcut this by using a little java-script and binding the onMessage attribute to it to update the output field:
<br>
<br>
<pre class="brush:xml">&lt;script type="text/javascript"&gt; function handleMessage(data) \{ $('.display').html(data); \} &lt;/script&gt;</pre>
<pre class="brush:xml">&lt;p:socket onMessage="handleMessage" channel="/counter" /&gt; </pre> That is all for now. Congratulations to your first PrimeFaces Push example. Have fun playing around with it!
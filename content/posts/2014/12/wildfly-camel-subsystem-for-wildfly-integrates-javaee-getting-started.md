---
title: "Camel Subsystem for WildFly 8 integrates Java EE - Getting Started"
date: 2014-12-05 14:00:00 +0000
layout: post
tags: ["wildfly", "Camel", "apache", "Jboss", "Hawtio"]
slug: "wildfly-camel-subsystem-for-wildfly-integrates-javaee-getting-started"
link: "2014/12/wildfly-camel-subsystem-for-wildfly-integrates-javaee-getting-started.html"
url: /2014/12/wildfly-camel-subsystem-for-wildfly-integrates-javaee-getting-started.html
---

Just three days ago, the team around&nbsp;Thomas Diesler (<a href="https://twitter.com/tdiesler" target="_blank">@tdiesler</a>) released the&nbsp;2.0.0.CR1 version of the <a href="https://github.com/wildfly-extras/wildfly-camel" target="_blank">WildFly-Camel subsystem</a> it allows you to add Camel Routes as part of the WildFly configuration. Routes can be deployed as part of JavaEE applications. JavaEE components can access the Camel Core API and various Camel Component APIs.
<br>
 This release in particular&nbsp;added a number of <a href="http://wildflyext.gitbooks.io/wildfly-camel/content/components/README.html" target="_blank">new camel components</a> to the subsystem and added support for the WildFly domain mode. Other than packaging and bootstrapping Camel in Java EE 7 yourself, this subsystem installs Camel directly into your WildFly 8.x server. As far as I know it even works with the latest 8.2.0.Final but isn't tested against it. Not, that there is a very light and easy way to <a href="http://blog.eisele.net/2014/08/bootstrapping-apache-camel-in-java-ee7.html" target="_blank">bootstrap Camel in Java EE 7</a>, but this approach enables even more integrations with Java EE standards. The ultimate goal for the subsystem is to provide Camel features as a directly usable option in WildFly without the need to configure or deploy anything. This is the main difference between a simple module and a complete subsystem.
<br>
<br><b>Getting Started</b>
<br>
 Install WildFly 8.x by downloading the "Java EE7 Full &amp; Web Distribution" from <a href="" target="_blank">wildfly.org</a>. Install by just extracting it into a folder of your choice. Next step is to download the <a href="https://repository.jboss.org/nexus/content/groups/public-jboss/org/wildfly/camel/wildfly-camel-patch/2.0.0.CR1/wildfly-camel-patch-2.0.0.CR1.tar.gz" target="_blank">distribution patch</a>&nbsp;(53 MB, tar.gz) for WildFly from the JBoss Maven repository. After the download, navigate to the WildFly folder and extract the archive into it. That's it. Now start either standalone or domain mode with the respective *-camel.xml:
<br>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; color: #333333; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 13px; line-height: 1.42857143; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><code style="background-color: transparent; border-radius: 0px; box-sizing: border-box; color: inherit; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: inherit; padding: 0px; white-space: pre-wrap;">$ bin/standalone.sh|bat -c standalone-camel.xml</code></pre>
<br><b>Some More Configuration</b>
<br>
 After we did that, you need to add some more users. First of all the management user.
<br>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; color: #333333; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 13px; line-height: 1.42857143; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><code style="background-color: transparent; border-radius: 0px; box-sizing: border-box; color: inherit; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: inherit; padding: 0px; white-space: pre-wrap;">$ bin/add-user.sh|bat </code></pre>
<div>
 Call it whatever you like. For convenience reasons I tend to name it admin with the password admin. But please keep in mind, that this is the worst practice you could apply ;)
</div>
<div>
 After you're done with that, add another application user. To make it simple, we just name it the same with the same, insecure password.
</div>
<div>
 <br>
</div><b>Exploring What's There</b>
<br>
 After you integrated the subsystem into your WildFly you can find the Apache Camel and JBoss modules in the module folder. The standalone\deployments folder contains two war-files. One of them is <a href="" target="_blank">HawtIo </a>and the other one is the <a href="https://github.com/wildfly-extras/wildfly-camel/tree/master/webapp/src" target="_blank">wildfly-camel.war</a> which is basically the <a href="https://github.com/wildfly-extras/wildfly-camel/tree/master/examples/camel-cdi" target="_blank">camel-cdi example</a> but more on that later. First of all, point your browser to:&nbsp;<a href="" target="_blank">http://localhost:8080/hawtio/</a> and login with the application user you created.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="hawtio-camel.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" src="hawtio-camel.PNG" height="195" width="400"></a>
</div>
<br>
 You can see, that the ActiveMQ and Camel tabs are enabled, which means both modules have been deployed and discovered by HawtIo. Drilling down into the Camel tab you can see two deployed routes. The versions used for the integration are Apache Camel&nbsp;2.14.0, HawtIo&nbsp;1.4.22 and Arquillian 1.1.2.Final. 
<br>
<br><b>High Level Features</b>
<br>
 The strategy of wildfly-camel is, that a user can “just use” the camel core/component APIs in deployments that WildFly supports already. In other words, Camel should “just work” in standard Java EE deployments. The binaries are be provided by the platform. The deployment should not need to worry about module/wiring details.
<br>
 Defining and Deploying Camel Contexts can be done in different ways. You either can directly define a <a href="http://wildflyext.gitbooks.io/wildfly-camel/content/features/context-definitions.html" target="_blank">Context in your standalone-camel.xml</a> server configuration or deploy it as part of your <a href="http://wildflyext.gitbooks.io/wildfly-camel/content/features/context-deployments.html" target="_blank">web-app</a> either as a single XML file with a predefined -camel-context.xml file suffix or&nbsp;as part of another WildFly supported deployment as META-INF/jboss-camel-context.xml file.
<br>
 The WildFly Camel test suite uses the WildFly Arquillian managed container. This can connect to an already running WildFly instance or alternatively start up a standalone server instance when needed. A number of test enrichers have been implemented that allow you have these WildFly Camel specific types injected into your Arquillian test cases; you can inject a&nbsp;CamelContextFactory or a CamelContextRegistry as an &nbsp;@ArquillianResource.
<br>
<br><b>Next Steps</b>
<br>
 You can simply undeploy the wildfly-camel.war and start over with your own application. You can either dig into the existing <a href="https://github.com/wildfly-extras/wildfly-camel/tree/master/examples" target="_blank">examples on GitHub</a> or wait for my next blog-post to walk you through that a bit.
<br>
 If you don't want to mess around with installing and patching, you can look at the ready available docker images which were published at <a href="" target="_blank">wildflyext/wildfly-camel</a>.
<br>
<br><b>More Information</b>
<br><a href="" target="_blank">Apache Camel Website</a>
<br><a href="" target="_blank">WildFly Camel Integration GitBook</a>
<br><a href="https://github.com/wildfly-extras/wildfly-camel/wiki/Roadmap" target="_blank">Planned Roadmap</a>
<br>
<br>
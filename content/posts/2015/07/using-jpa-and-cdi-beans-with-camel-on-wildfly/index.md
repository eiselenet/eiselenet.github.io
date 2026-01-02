---
title: "Using JPA And CDI Beans With Camel on WildFly"
date: 2015-07-17 11:30:00 +0000
layout: post
tags: ["wildfly-camel", "Demo", "javaee"]
slug: "using-jpa-and-cdi-beans-with-camel-on-wildfly"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2015/07/using-jpa-and-cdi-beans-with-camel-on-wildfly.html
---

I didn't really plan for it, but with a conference free month, I had the chance to dig around a little more and show you even more of the Camel on WildFly magic, that the WildFly-Camel subsystem provides.
<br>
<br><b>The Business Background</b>
<br>
 The demo is derived from one on <a href="http://jbossdemocentral.org/#/fuse" target="_blank">JBoss Demo-Central</a> by Christina Lin. She&nbsp;demonstrates the use of File and JDBC connectors in Camel and also added the use of Spilt pattern and Exception handling method.&nbsp;The scenario of the demo is to mimic the transaction process between bank accounts. The input is a batch XML file which contains a bunch of transactions. Those can either be cash deposit, cash withdraw or transfer information of bank accounts. Depending on the type of transaction, they are spilt up and each transaction retrieves relevant information from a database, does the transaction and calculates the transaction fee before placing them back into the database. You can find the full <a href="https://github.com/jbossdemocentral/jboss-fuse-component-file-jdbc" target="_blank">original source code on GitHub</a>.
<br>
<br><b>Why Did I Touch It</b>
<br>
 Some reasons: I actually don't want to think about new business cases. And don't just want to show you something in nitty-gritty details on a technical level. So, I thought it is a quick win to just take the scenario from Christina. Second of all, she is doing everything in Fuse, based on Karaf and using the XML DSL for the route definitions. I am just a poor Java guy, and learned to hate XML. Plus, she is using a couple of components, which I wouldn't in a Java EE context.
<br>
<br><b>Prerequisites - Getting The App Deployed</b>
<br>
 Before you begin, playing around with the demo, please make sure to have&nbsp;<a href="http://blog.eisele.net/2015/07/using-camel-routes-in-java-ee-components.html" target="_blank">WildFly 8.2.0.Final installed together with the WildFly-Camel subsystem patch 2.2.0</a>. Now feel free to fork the&nbsp;<a href="https://github.com/myfear/camel-javaee-bankdemo" target="_blank">demo repository on my github account</a>&nbsp;into a directory of your choice. It is nothing more than a maven Java EE 7 project with some additional dependencies. Just do a
<br>
<pre class="code"><code>mvn clean install</code></pre> and deploy the resulting target/javaee-bankdemo-1.0-SNAPSHOT.war to your WildFly server.
<br>
 There isn't any UI in this example, so you basically have to watch the logfile and copy an xml file around. The&nbsp;src\main\in-data folder contains a bank.xml, which you need to copy over to your&nbsp;standalone\data\inbox folder. The second this is done, camel starts it's magic.
<br>
<br><b>The CustomerStatus</b>
<br>
 Everything begins with the standard Java EE app. The Entity <a href="https://github.com/myfear/camel-javaee-bankdemo/blob/master/src/main/java/net/eisele/camel/jpa/CustomerStatus.java" target="_blank">CustomerStatus</a> holds account information (ID, VipStatus, Balance). It also has some NamedQueries on it. Doesn't look Camel specific at all. The in-memory H2 database, which WildFly uses as the default db, get's pre-populated with the help of three scripts which are configured as schema-generation properties in the <a href="https://github.com/myfear/camel-javaee-bankdemo/blob/master/src/main/resources/META-INF/persistence.xml" target="_blank">persistance.xml</a>. I'm working with two customers here, named A01 and A02.
<br>
<br><b>Camel And Java EE</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="camel-routes.PNG" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="320" src="camel-routes.PNG" width="274"></a>
</div> The Camel bootstrapping is quite simple in this case. The <a href="https://github.com/myfear/camel-javaee-bankdemo/blob/master/src/main/java/net/eisele/camel/jpa/bank/BankRouteBuilder.java" target="_blank">BankRouteBuilder</a> has a&nbsp;@ContextName("cdi-context") annotation and is itself an application scoped startup-bean which contains all the needed routes for the little demo. Feel free to re-read and learn about<a href="http://blog.eisele.net/2015/07/using-camel-routes-in-java-ee-components.html" target="_blank"> other potential options to deploy / configure routes</a>. The hawt.io console (http://localhost:8080/hawtio/) displays all of them nicely. The application has five routes.
<br>
 ReadFile is the first one, which basically only ready the xml file and pushes the individual entries (<a href="https://github.com/myfear/camel-javaee-bankdemo/blob/master/src/main/java/net/eisele/camel/jpa/bank/BankRouteBuilder.java#L46" target="_blank">split by an xPath expression</a>) to the processTransaction route.
<br>
 This one decides on whether it is a "Cash" transaction or a "Transfer" transaction. Respectively ending in "<a href="https://github.com/myfear/camel-javaee-bankdemo/blob/master/src/main/java/net/eisele/camel/jpa/bank/BankRouteBuilder.java#L161" target="_blank">direct:doTransfer</a>" or "<a href="https://github.com/myfear/camel-javaee-bankdemo/blob/master/src/main/java/net/eisele/camel/jpa/bank/BankRouteBuilder.java#L126" target="_blank">direct:processCash</a>". I left all of the original xml route definitions in the BankRouteBilder as comments. Might be helpful, if you search for a particular solution.
<br>
<br><b>Differences To The Fuse Demo</b>
<br>
 Christina used the <a href="https://camel.apache.org/jdbc.html" target="_blank">Camel JDBC component</a> a lot. It does all the heavy lifting and even the <a href="https://github.com/jbossdemocentral/jboss-fuse-component-file-jdbc/blob/3ec08d1dc2e6eb15d9ecaceebc6674a054366fd6/project/bankdemo/src/main/resources/OSGI-INF/blueprint/blueprint.xml#L35" target="_blank">initial database</a> setup. This is nothing we want to do anywhere, but especially not in a Java EE environment where we have all the JPA magic ready to use. In fact, there is a <a href="https://camel.apache.org/jpa.html" target="_blank">Camel JPA componente</a>, but it is very limited and doesn't really support NamedQueries or alike.
<br>
 A very powerful way to work around that is to use the <a href="https://camel.apache.org/bean.html" target="_blank">Camel Bean component</a> with all the bean binding and the cdi component, which is already integrated. All the database access is managed via the <a href="https://github.com/myfear/camel-javaee-bankdemo/blob/master/src/main/java/net/eisele/camel/jpa/bank/CustomerStatusService.java#L20" target="_blank">CustomerStatusService</a>. Which is basically a @Named bean which get's an EntityManager injected and knows how to load CustomerStatus entities. It get's injected into the RouteBuilder by simply referencing it in the bean endpoint:
<br>
<pre class="code"><code>.to("bean:customerService?method=loadCustomer")</code></pre> I agree, that there is a lot of magic happening behind the scenes and the fact, that the CustomerStatusService depends on Camel classes is another thing, that I dislike. But this could be easily worked around by just @Inject-ing the service into the route and referencing it alike. I decided to not do this, because I wanted to keep the initial flow of Christina's demo alive. She is working with the Exchanges a lot and relies on them. So, I stayed closer to her example.
<br>
<br><b>A Word On Transactions</b>
<br>
 I am actually using an extended persistent context in this example and <a href="https://github.com/myfear/camel-javaee-bankdemo/blob/master/src/main/java/net/eisele/camel/jpa/bank/CustomerStatusService.java#L53" target="_blank">marked the updateCustomer method in the service as @Transactional</a>. This is a very simple way of merging complete and updated CustomerStatus entities back into the database. The whole <a href="https://github.com/myfear/camel-javaee-bankdemo/blob/master/src/main/java/net/eisele/camel/jpa/bank/BankRouteBuilder.java#L161" target="_blank">doTransfer </a>route isn't transactional right now. Even if the second customer isn't in the system, the amount would still be withdrawn from the first customer account. I want to cover this at a later stage and a separate blog-post.
<br>
<br>
 That's it for now. Enjoy your weekend and playing with Camel and the WildFly Camel subsystem. Happy to receive your ideas or questions via <a href="http://www.twitter.com/myfear" target="_blank">@myfear</a> or as a comment on the blog-post.
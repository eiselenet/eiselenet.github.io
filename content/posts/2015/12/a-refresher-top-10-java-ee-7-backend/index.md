---
title: "A Refresher - Top 10 Java EE 7 Backend Features"
date: 2015-12-22 06:53:00 +0000
layout: post
tags: ["javaee7", "Top10"]
slug: "a-refresher-top-10-java-ee-7-backend"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2015/12/a-refresher-top-10-java-ee-7-backend.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="ee-7-overview.PNG" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="184" src="ee-7-overview.PNG" width="320"></a>
</div> This is the second part in my little <a href="http://blog.eisele.net/2015/11/a-refresher-java-ee-7-at-glance.html" target="_blank">Java EE 7 refresher</a> series. After a first introduction with a brief overview, I decided to ask <a href="http://stackoverflow.com/users/472792/arjan-tijms" target="_blank">Arjan Tijms</a> to write about his favorite new backend features in Java EE 7. You will know Arjan if you're following the Java EE space. He is a long time Java EE developer, JSF and Security EG member and he created <a href="" target="_blank">OmniFaces</a> together with <a href="http://blog.eisele.net/2012/07/the-heroes-of-java-bauke-scholtz.html" target="_blank">Bauke Scholtz</a> (aka BalusC) and helps building <a href="" target="_blank">zeef.com</a>.
<br>
<h3>1. App Provided Administrative Objects</h3> Java EE has long had the concept of an “administrative object”. This is a kind of resource that is defined on the application server instead of by the application. For some classes of applications using these is a best practice, for others it’s not such a good practice.
<br>
 Java EE 6 started a small revolution with the introduction of @DataSourceDefinition, which lets an application define its own data source. Java EE 7 expands on this with @MailSessionDefinition (JavaMail 1.5), @ConnectionFactoryDefinition &amp; @AdministeredObjectDefinition (JCA 1.7) and @JMSConnectionFactoryDefinition &amp; @JMSDestinationDefinition (JMS 2.0).
<br>
 In practice, many applications already used the programmatic API of JavaMail to create mail sessions, and JCA usage is relatively rare. JMS is much more widely used though and lacked an (EE compatible) programmatic API to create destinations (queues and topics).
<br>
 The importance of this seemingly small feature is that for the first time in the history of JMS it can be used in a fully standard way, without requiring &nbsp;vendor specific xml files in the application archive or vendor specific configuration in the application server.
<br>
 Note that none of these application provided resource definitions strongly tie the rest of the application code to these. That application code still only sees a JNDI name, and doesn’t depend on whether the resource is put in JNDI by a standard annotation, standard XML file, proprietary XML file or with proprietary config on the application server.
<br>
<br>
 Further reading:
<br><a href="https://abhirockzz.wordpress.com/2015/11/27/automated-provisioning-of-jms-resources-in-java-ee-7" target="_blank">Automated provisioning of JMS resources in Java EE 7</a>
<br>
<h3>2. Default resources</h3> Closely related to app provided administrative objects, Java EE also introduced the notion of several default resources.
<br>
 In the case of a default resource, the Java EE platform provides a ready to use resource of a specific type. Java EE 7 introduced defaults for a data source, the platform’s default JMS connection factory, and the default thread pool.
<br>
 What characterizes these defaults is that they can’t be further configured in any standardized way. You have to do with whatever is provided by your server.
<br>
 In the case of a data source, this means you get “something” to which you can send SQL, but there are no further guarantees with respect to performance or even durability (the data base the data source accesses could be fully memory based, although it practice it’s almost always a file in a server specific directory).
<br>
<br>
 For the JMS connection factory, you get a connection to the default JMS provider of the server. Since JMS, unlike a SQL database, is a mandated part of Java EE you usually have a very good idea of what you get here. E.g. if the server in question is a production ready server, the default JMS provider is practically always a production ready one either.
<br>
<br>
 Finally several actual resources such as a ManagedExecutorService gives you access to what is essentially the system’s default thread pool. Such thread pool can be used in much the same way as you would use the @Asynchronous annotation from Java EE 6. You don’t exactly know how many threads are in the pool or whether the ManagedExecutorService is backed by the same pool as @Asynchronous, but for simple ad-hoc multi-threaded work the defaults are typically good enough.
<br>
<br>
 A particular nice aspect of the default resources is that in several situations you don’t even have to say that you want the default. The default data source that a JPA persistence unit for instance uses if you don’t specify any is well, the default data source.
<br>
<br>
 Further reading:
<br><a href="https://blogs.oracle.com/arungupta/entry/default_datasource_in_java_ee" target="_blank">Default DataSource in Java EE 7: Lesser XML and More Defaults&nbsp;</a>
<br><a href="" target="_blank">Defaults in Java EE 7</a>
<br>
<h3>3. App provided and portable authentication mechanisms</h3> Next to the administrative objects mentioned above, another thing that traditionally had to be defined and configured on the application server side are authentication mechanisms and identity stores (both known by many alternative names).
<br>
<br>
 The Servlet spec does define 4 standardised authentication mechanisms that an application can choose from via its web.xml deployment descriptor (FORM, BASIC, DIGEST, CLIENT-CERT), but did not standardise the actual classes or interfaces for these and subsequently didn’t standardise any API/SPI for custom authentication mechanisms. Furthermore, there’s nothing in the spec about the actual location where caller names/credentials/groups are stored.
<br>
<br>
 Just like with @DataSourceDefinition, Java EE 6 started a small revolution by standardising an API/SPI for authentication mechanisms as well as a programmatic API to register these from within the application: JASPIC 1.0.
<br>
<br>
 Unfortunately, the Java EE 6 version of JASPIC had a few critical omissions that made it hard to actually use those portable authentication mechanisms. The most important of those were addressed in Java EE 7.
<br>
<br>
 Just as with the app provided administrative objects, an app provided authentication mechanism does not tie the rest of the application code to these and they can be transparently swapped out for container provided ones.
<br>
<br>
 Further reading:
<br><a href="http://arjan-tijms.omnifaces.org/2013/04/whats-new-in-java-ee-7s-authentication.html" target="_blank">What's new in Java EE 7's authentication support?</a>
<br>
<h3>4. CDI based @Transactional</h3> Before Java EE 7, high level declarative transactions were the domain of EJB. In this model, EJB was intended as a universal facade for a lot of functionality that the platform offers. While EJB evolved from an arcane heavyweight spec in J2EE 1.4 to something that’s actually quite lightweight in Java EE 6, the model of one spec functioning as a facade was not seen as ideal anymore.
<br>
<br>
 While Java EE 6 brought the biggest change with if of actually introducing CDI, Java EE 7 started another small revolution where other specs starting to depend on CDI instead. With this the model of one bean type being a facade started to change to the competing model of one bean type functioning as a base and other specs providing extensions on top of that.
<br>
<br>
 Specifically setting this in motion was JTA 1.2 with the introduction of @Transactional and @TransactionScoped. These are based on an interceptor from the Interceptors spec and a scope from the CDI spec. Both are mainly applicable to CDI beans. The way that this turns the model around is that with EJB, JTA was invisibly used under the hood, while with CDI, JTA (somewhat less invisibly) uses CDI under the hood.
<br>
<br>
 Further reading:
<br><a href="https://blogs.oracle.com/theaquarium/entry/jta_1_2_it_s" target="_blank">JTA 1.2 - It's not your Grandfather's Transactions anymore!</a>
<br><a href="https://javaee7.zeef.com/arjan.tijms#block_7579_jta-1-2" target="_blank">JTA 1.2 on Arjan's ZEEF page</a>
<br>
<h3>5. Method validation</h3> Perhaps one of the most versatile and cross layer spec in Java EE is the bean validation spec. Bean validation makes it possible to put validation constraints on various beans, such as CDI beans and JPA entities.
<br>
<br>
 But, those validation constraints only worked at the field level and by extension of that on the class level (which effectively validates multiple fields).
<br>
<br>
 In Java EE 7 the applicability of bean validation took a huge leap by the ability of placing such constraints on methods as well, aptly called method validation. More precisely, constraints can now be put on the input parameters of a method as well as on its return value, and the input constraints can be on individual parameters as well as on multiple parameters.
<br>
<br>
 Whereas field level constraints are validated at a specific moment, e.g. when the JPA entity manager persists an entity or after a postback in JSF, method validation takes place every time a method is called by arbitrary code.In Java EE this works when the method is in a (proxied) CDI managed bean, and the method is indeed accessed via the proxy.
<br>
<br>
 Further reading:
<br><a href="" target="_blank">Bean Validation 1.1 Feature Spotlight - Method validation</a>
<br><a href="https://javaee7.zeef.com/arjan.tijms#block_235_bean-validation-1-1" target="_blank">Bean Validation 1.1 on Arjan's ZEEF page</a>
<br>
<h3>6. Expression language can be used everywhere</h3> Expression language is a mini script language that’s used within Java EE. It has a long history, from being specifically to JSTL, to being natively incorporated in JSP, natively incorporated in JSF and later on unified between JSP and JSF.
<br>
<br>
 In Java EE 7 this expression language took its biggest leap ever and became a totally independent spec that’s fully usually outside JSP and JSF, and even outside Java EE.
<br>
<br>
 This means that expression language can be used in things like annotations, email templates, configuration files and much more. Just as with the introduction of CDI in Java EE 6, the introduction of a separately usable expression language has the potential to be used by many other specs in the future.
<br>
<br>
 Further reading:
<br><a href="https://web.archive.org/web/20150923201252/https://weblogs.java.net/blog/swchan2/archive/2013/07/01/standard-deviation-illustration-expression-language-30-servlet-environment" target="_blank">Standard Deviation: An Illustration of Expression Language 3.0 in Servlet Environment</a>
<br><a href="https://javaee7.zeef.com/arjan.tijms#block_228_expression-language-3-0" target="_blank">EL 3.0 on Arjan's ZEEF page</a>
<br>
<h3>7. Greatly simplified JMS API</h3> One of the older specs in Java EE is JMS, which is about (asynchronous) messaging. JMS is also one of the specs that hadn’t been updated for a very long time (not since 2002!), and although still surprisingly usable its age did begin to show a little.
<br>
<br>
 With JMS 2.0 Java EE 7 brought one of the biggest changes to JMS ever; a thoroughly and greatly simplified API. Part of these simplifications piggy back on the default resources mentioned above, but it also takes advantage of Java SE 7’s auto closeable feature and many smart defaults to minimize the amount of objects a user has to manage and juggle with for simple things like sending a message.
<br>
<br>
 Further reading:
<br><a href="http://www.oracle.com/technetwork/articles/java/jms20-1947669.html" target="_blank">What's New in JMS 2.0, Part One: Ease of Use</a>
<br><a href="https://javaee7.zeef.com/arjan.tijms#block_236_jms-2-0" target="_blank">JMS 2.0 on Arjan's ZEEF page</a>
<br>
<h3>8. Entity graphs in JPA</h3> Arguably one of the most important specs next to CDI in Java EE is JPA. Whether a Java EE application is a JSF based MVC one, or a JAX-RS based web-service, they pretty much always have some persistence requirements.
<br>
<br>
 One of the difficulties in persistence is determining what is just the right amount of data to be fetched. This should obviously not be too little, but also not be too much since that typically comes with great performance implications.
<br>
<br>
 An important tuning parameter of JPA has always been the eager and lazy loading of specifically relations. This choice is primarily structurally and hard coded on the entities itself. The problem with this is that in different situations the same entity may be required with more or less data. E.g. in an overview of all users you may only want to display the user name, while in a detail view also the address and other contact details.
<br>
<br>
 Before Java EE 7 this could be done without fetching too little or too much data for each case by means of writing separate queries. While this solves the problem it’s not optimal, especially not when it concerns large queries and the only difference is how much associated data is fetched for some entity.
<br>
<br>
 With JPA 2.1, Java EE 7 introduced the concept of entity graphs for this. Via a (named) graph, it’s now possible to determine exactly what data needs to be fetched in a graph style notion. These graphs are defined separately and can be associated at runtime with many different queries.
<br>
<br>
 Further reading
<br><a href="" target="_blank">JPA 2.1 Entity Graph – Part 1: Named entity graphs</a>
<br><a href="https://javaee7.zeef.com/arjan.tijms#block_232_jpa-2-1" target="_blank">JPA 2.1 on Arjan's ZEEF page</a>
<br>
<h3>9. Access to managed thread pools</h3> Briefly mention above when the default resources were discussed is that in Java EE 7 access is provided to the default thread pool.
<br>
<br>
 The support actually goes a bit further than just that and Java EE 7 introduced an entire specification behind this; the concurrency utils for Java EE spec. With this spec you can not just get that default thread pool, but also obtain and work with separate thread pools. This is important for QoS use cases, and specifically to prevent a number of dead locking cases if work that depends on each other is scheduled to the same pool.
<br>
<br>
 Unfortunately the practical usability of these additional pools is somewhat limited by that fact that it’s not possible to actually define those pools in a standard way. This somewhat contradicts the “App provided administrative objects” item in the beginning of this overview.
<br>
<br>
 Despite that issue, for somewhat lower level asynchronous and parallel programming this spec opens up a world of options.
<br>
<h3>10. Etc; CDI tuning, Interceptors spec, Batching</h3> In Java EE 7 the Interceptors spec was split off from CDI paving the road for CDI to focus more on being the core bean model of Java EE, while simultaneously making interceptors more generally reusable throughout the platform.
<br>
<br>
 CDI itself didn’t get a major overhaul or a really major new feature, but instead got an assortment of smaller but very welcome features such as a (much) easier way to programmatically obtain bean instances, and events that fire when scopes are activated and de-activated.
<br>
<br>
 Automatic enablement of CDI (CDI activated without needing a beans.xml) should have been a major feature, but appeared to be of rather limited use in practice. Without a beans.xml file only beans with so-called “bean defining annotations” are scanned, and especially for beginning users this is not always clear.
<br>
<br>
 Java EE traditionally deals mostly with requests and responses that are generally rather short running. There’s a timer service available for background jobs, but that’s a relatively basic facility. There’s hardly any notion of job management, check-pointing or restarting.
<br>
<br>
 In Java EE a brand new spec was introduced that specifically addresses these concerns; the Batch Applications for Java Platform 1.0 spec. This resolves around XML files in which jobs are specified, which themselves contains so-called steps that perform the actual sequential application logic.
<br>
<br>
 Further reading
<br><a href="https://javaee7.zeef.com/arjan.tijms#block_233_cdi-1-1" target="_blank">CDI 1.1</a>
<br><a href="https://javaee7.zeef.com/arjan.tijms#block_47031_interceptors-1-2" target="_blank">Interceptors 1.2</a>
<br><a href="https://javaee7.zeef.com/arjan.tijms#block_239_batch-applications-for-java-platform-1-0" target="_blank">Batch 1.0</a>
<br>
<br>
 Thank you Arjan for taking the time to compile all of this. The next post is going to cover the top 10 features of the fronted technologies and will also feature a prominent guest blogger. Until then, there is plenty of time to play around with Java EE 7. Here are some resources to get you started with JBoss EAP 7 and WildFly: 
<br>
<ul>
 <li><a href="http://blog.eisele.net/2015/11/getting-started-with-eap-7-alpha-and.html" target="_blank">Getting Started With EAP 7 Alpha and Java EE 7</a></li>
 <li><a href="http://blog.eisele.net/2015/12/a-java-ee-7-application-on-openshift-3.html" target="_blank">A Java EE 7 Application on OpenShift 3 With JBoss Tools</a></li>
 <li><a href="https://github.com/javaee-samples/javaee7-samples" target="_blank">Java EE 7 Samples on GitHub</a></li>
</ul>
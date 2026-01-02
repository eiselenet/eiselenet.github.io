---
title: "Spring 3.0.0.M3 on Google Appengine with JPA"
date: 2009-07-31 07:10:00 +0000
layout: post
tags: ["google", "GAE", "jpa", "DataNucleus", "Spring"]
slug: "spring-300m3-on-google-appengine-with"
link: "2009/07/spring-300m3-on-google-appengine-with.html"
url: /2009/07/spring-300m3-on-google-appengine-with.html
---

I am working on a private project these days. It should give myfear.com and myfear.de a new meaning. Therefore I am trying out Google's Appengine.
<br>
Beeing forced to more or less full blown JEE5 in my dayly work, I wanted to try out something new. Having worked with older Spring versions some time ago, I feelt it was time to give this another shot. And so I ended up, trying Spring's newest milestone release on the GAE.
<br>
<br>
At the end of the day, I solved many problems on the road and everything worked fine. Here is a brief summary of what I had to change/find out:
<br>
<br>
1) GAE does not support full blown JEE. Therefore you have to be very sensible for using the right combination of technologies. If you try to use spring completely you will fail. The following list of jar files did the job for me:
<br>
antlr-3.0.1.jar
<br>
aopalliance-1.0.jar
<br>
asm-2.1.jar
<br>
asm-commons-2.1.jar
<br>
commons-beanutils-1.7.0.jar
<br>
commons-collections-3.1.jar
<br>
commons-lang.jar
<br>
commons-logging.jar
<br>
org.springframework.aop-3.0.0.M3.jar
<br>
org.springframework.asm-3.0.0.M3.jar
<br>
org.springframework.aspects-3.0.0.M3.jar
<br>
org.springframework.beans-3.0.0.M3.jar
<br>
org.springframework.context-3.0.0.M3.jar
<br>
org.springframework.core-3.0.0.M3.jar
<br>
org.springframework.expression-3.0.0.M3.jar
<br>
org.springframework.jdbc-3.0.0.M3.jar
<br>
org.springframework.orm-3.0.0.M3.jar
<br>
org.springframework.transaction-3.0.0.M3.jar
<br>
org.springframework.web-3.0.0.M3.jar
<br>
org.springframework.web.servlet-3.0.0.M3.jar
<br>
<br>
2) Setting up the persistance.xml is straight forward but remember to rename the persistance-unit in jdoconfig.xml. Both should have different names!
<br>
<br>
 &lt;persistence-unit name="transactions-optional"&gt;
<br>
 &lt;provider&gt;org.datanucleus.store.appengine.jpa.DatastorePersistenceProvider&lt;/provider&gt;
<br>
 &lt;properties&gt;
<br>
 &lt;property name="datanucleus.NontransactionalRead" value="true" /&gt;
<br>
 &lt;property name="datanucleus.NontransactionalWrite" value="true" /&gt;
<br>
 &lt;property name="datanucleus.ConnectionURL" value="appengine" /&gt;
<br>
 &lt;/properties&gt;
<br>
 &lt;/persistence-unit&gt;
<br>
<br>
3) Defining the entity manager in dispatcher-servlet.xml
<br>
<br>
&lt;bean id="entityManagerFactory"
<br>
 class="org.springframework.orm.jpa.LocalEntityManagerFactoryBean"
<br>
 lazy-init="true"&gt;
<br>
 &lt;property name="persistenceUnitName" value="transactions-optional" /&gt;
<br>
 &lt;/bean&gt;
<br>
 &lt;bean name="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager"&gt;
<br>
 &lt;property name="entityManagerFactory" ref="entityManagerFactory" /&gt;
<br>
 &lt;/bean&gt;
<br>
<br>
Don't forget to add the following two, if you want to use JPA @Annotations in your Entities and Spring @Annotations in your DAOs and Services.
<br>
<br>
&lt;tx:annotation-driven /&gt;
<br>
<br>
&lt;bean
<br>
 class="org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor" /&gt;
<br>
<br>
<br>
4) Injecting the EntityManager in your @Repository Daos:
<br>
<br>
<pre><br>private EntityManager entityManager;<br><br>@PersistenceContext<br>public void setEntityManager(EntityManager entityManager) \{<br>this.entityManager = entityManager;<br>\}<br></pre>
<br>
<br>
5) If you try to use the injected entityManager without any transaction, then you would most likely get an NucleusUserException: Object Manager has been closed. You can prevent this, using @Service and @Transactional in your service layer.
<br>
<br>
6)Nearly the same could happen, if you do not call queryResult.size() on your query.getResultList(). The error was reported several times. There seems to exist only this "workaround".
<br>
<br>
7) Using EL Expressions in your JSP forces you to use a little addon to the jsp page definition:
<br>
<br>
&lt;%@ page language="java" contentType="text/html; charset=UTF-8"
<br>
 pageEncoding="UTF-8" <strong>isELIgnored="false"</strong> %&gt;
<br>
<br>
8) If you would like to use the &lt;fmt:formatDate jstl tags, you have to enable session management in your appengine-web.xml. &lt;sessions-enabled&gt;true&lt;/sessions-enabled&gt;
<br>
<br>
9) I came across several problems using a JRE with eclipse and GAE local server. You should always use a JDK!
<br>
<br>
All for now ... more to come :)
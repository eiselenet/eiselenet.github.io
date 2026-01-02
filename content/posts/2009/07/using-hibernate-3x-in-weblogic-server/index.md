---
title: "Using Hibernate 3.x in Weblogic Server 10gR3 and 11g"
date: 2009-07-10 06:43:00 +0000
layout: post
tags: ["Hibernate", "oracle", "weblogic server"]
slug: "using-hibernate-3x-in-weblogic-server"

url: /2009/07/using-hibernate-3x-in-weblogic-server.html
---

If you ever tried using a different O/R mapper than the ones provided by Oracle, you may have come across several issues. The most common one is a problem with the ANTLR packages. Hibernate3 uses ANTLR for the new query parser. Oracle Weblogic includes a version of ANTLR in the system classpath which will be loaded before any application libraries. If you are using Hibernate in your Webapplication you will need to package the hibernate.jar and all dependencies within you WEB-INF/lib directory. The parent ear file should have an additional weblogic-application.xml descriptor to which the configuration for the FilteringClassLoader is added. 
<br>
<br>
<blockquote>
 <br>
 &lt;prefer-application-packages&gt;
 <br>
 &lt;package-name&gt;antlr.*&lt;/package-name&gt;
 <br>
 &lt;/prefer-application-packages&gt;
 <br>
</blockquote>
<br>
<br>
If you use Hibernate as JPA provider and package your Entities in the ejb.jar files, you should only add the jars to the APP-INF/lib directory of the ear file. This works without any further problems.
<br>
<br>
Don't try changing or removing the distributed modules (BEA_HOME/modules)
<br>
com.bea.core.antlr.runtime_2.7.7.jar or 
<br>
com.bea.core.antlr_2.7.7.jar
<br>
You will get in deeper trouble (e.g. admin console not completely working).
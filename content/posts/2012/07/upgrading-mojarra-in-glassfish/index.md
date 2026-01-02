---
title: "Upgrading to latest Mojarra in GlassFish 3.1.x"
date: 2012-07-09 06:26:00 +0000
layout: post
tags: ["glassfish", "Mojarra"]
slug: "upgrading-mojarra-in-glassfish"

url: /2012/07/upgrading-mojarra-in-glassfish.html
---

I was playing around with latest&nbsp;Mojarra and in order not to replace any existing versions at a server module level I tried to package it with my apps. I shouldn't have done that. Here is a short story.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG-20120709-00215.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="240" src="IMG-20120709-00215.jpg" width="320"></a>
</div> I do love application servers. Especially GlassFish and WebLogic. You know that. But there is one thing that starts to bug me more and more with latest releases. It's the release cycle of bundled components. Do you remember the good old days where you simply threw a newer library into your app bundle and (nearly) everything worked fine? Those days belong to the past. As of today you experience a lot of trouble if you try to upgrade and replace single reference implementations or components of your appserver by bundling them to your apps. The recent example was&nbsp;<span style="background-color: white;">Mojarra. Some bugs in the&nbsp;</span><span style="background-color: white;">Mojarra 2.1.5 which is shipped with the latest GlassFish 3.1.2-b22 prevented me from upgrading an application. So I thought I give the latest&nbsp;</span><span style="background-color: white;">2.1.10 (05/31/2012) a shot which promised to solve those issues. I am very thankful for the frequent releases here and was very very looking forward having this solved in literally a few seconds.</span>
<br><span style="background-color: white;">Googling around a bit for the specifics of the upgrade process brought me to Arun's blog. A <a href="https://blogs.oracle.com/arungupta/entry/totd_59_alternative_jsf_implementations" target="_blank">four year old entry</a> pointed me to the basics. Bundle impl and api jars to your application. Add some lines to your sun-web.xml.</span>
<br>
 There you go. That should be everything. Fine. Obviously the name of the deployment descriptor changed. It's new name is glassfish-web.xml and also the property name changed from useMyFaces to useBundledJsf. Not a big surprise and very welcome. 
<br>
<pre class="brush:xml">&amp;lt;class-loader delegate=&amp;quot;false&amp;quot;/&amp;gt; &amp;lt;property name=&amp;quot;useBundledJsf&amp;quot; value=&amp;quot;false&amp;quot;/&amp;gt; </pre> If you now try to find the jsf-impl and jsf-api dependencies for the 2.1.10 you start struggling. With 2.1.3 Mojarra started adopting the Oracle <a href="http://wikis.oracle.com/display/GlassFish/Maven+Versioning+Rules" target="_blank">Java EE Maven and OSGi naming and versioning scheme</a>. This basically means, you now have only one dependency for both: 
<br>
<pre class="brush:xml">&lt;dependency&gt; &lt;groupId&gt;org.glassfish&lt;/groupId&gt; &lt;artifactId&gt;javax.faces&lt;/artifactId&gt; &lt;version&gt;2.1.10&lt;/version&gt; &lt;scope&gt;compile&lt;/scope&gt; &lt;/dependency&gt; </pre> Also fine to me. Let's give that a test-drive. Ups: 
<br>
<pre>java.lang.Exception: java.lang.IllegalStateException: ContainerBase.addChild: start: org.apache.catalina.LifecycleException: java.lang.RuntimeException: com.sun.faces.config.ConfigurationException: CONFIGURATION FAILED! org.glassfish.weld.jsf.WeldFacesConfigProvider cannot be cast to com.sun.faces.spi.ConfigurationResourceProvider </pre> That did not work!
<br>
 What happened?
<br>
 The issue seems to be that there are two versions of ConfigurationResourceProvider on the class path: one at the web app level (your bundled jar file) and a second at the parent class loader level (provided by GlassFish). WeldFacesConfigProvider extends ConfigurationResourceProvider at the parent class loader, but it ends up casting this to ConfigurationResourceProvider at the web app class loader.
<br>
<br><b>Workaround: Replacing Mojarra in GlassFish</b>
<br>
 How to work around that? Easy if you like it to be easy. Simply <a href="http://javaserverfaces.java.net/download.html" target="_blank">download the latest stable version</a> of Mojarra from it's java.net project and replace the&nbsp;glassfish3\glassfish\modules\javax.faces.jar with the downloaded version. Make sure to keep a backup of the original file and also don't forget to delete the osgi cache&nbsp;glassfish3\glassfish\osgi for the changes to take effect. Also make sure to only use a 2.1 version of&nbsp;<span style="background-color: white;">Mojarra</span><span style="background-color: white;">&nbsp;and don't try a 2.2 even if it is there. First of all because it implements the new EE 7 features and second because it is still a snapshot. Keep away here! For now.</span>
<br><span style="background-color: white;">Why you might not like this approach. Imagine you are a paying Oracle GlassFish Server Customer. What do you think? Do you still get support if you are replacing a core module with a newer version? I haven't checked the licensing and support terms. But I believe you will not. And that might be right from a product point of view because you simply can't test every possible combination of modules for every release.&nbsp;</span>
<br>
<br><b>Workaround: Adding the missing dependency to your app</b>
<br>
 If you can't cast across class loaders you have to make the missing part available to the right class-loader. Not replacing the javax.faces.jar in your server's modules leads to adding the magic WeldFacesConfigProvider to your app. It is contained in the glassfish3\glassfish\modules\weld-integration.jar and by simply putting that as an additional library to your application you also solve the issue. 
<br>
 There is even a mavenized version on the web. But this is drawing in a lot of additional dependencies which you would have to exclude and it also seems to be a snapshot build only. So you will end up putting this jar to your local or corporate maven repository. Going this way you should still receive support for your commercial appserver version. Even if the weld-integration obviously was not thought of being deployed with your applications every time over and over again. But with 77KB this also isn't a too big issue.
<br>
<br><b>The good news</b>
<br>
 The JSF and GlassFish team is working on this. And I hope they are getting this fixed with one of the upcoming GlassFish releases. Thanks to <a href="" target="_blank">Andy</a> and <a href="" target="_blank">Ed</a> for the support here and for taking care.
<br>
<br><b>Modularization and Rolling Upgrades</b>
<br>
 As I said in the introduction, this is only one example. I believe you could come up with more. And the voices are getting louder which call for a better modularization everywhere. And they are right. If I or you would have developed a complex project with all those cross dependencies we would have been kicked hardly. Either by our customers or by our coworkers. So, even if we dropped that from the scope of EE 7 I am very looking forward having the possibility to integrate this into EE 8 latest. Another point here are the release cycles and the update policies for vendors. Even if GlassFish isn't broken by default and it is a very stable server in general you have other examples out there: Let's look at the latest WebLogic 12c release. It has been released quite late comparing to other EE 6 compliant servers. Bringing in some bugs and forcing Oracle to deliver a <a href="http://blog.eisele.net/2012/03/updated-oracle-weblogic-server-12110.html">repackaged distribution</a> later on. And even this isn't completely usable (<a href="http://buttso.blogspot.de/2012/07/jsf-managed-beans-postconstruct-and-cdi.html" target="_blank">Issue</a>, <a href="http://buttso.blogspot.de/2012/06/multipart-file-upload-with-wls-12c.html" target="_blank">Issue</a>) forcing you to be either a paying customer to receive the fixes or stay off the product. Bottom line? I don't know. Something is wrong the way it is today. We need to work harder to make EE a specification which can be used, updated and worked with. And I believe the next release shouldn't focus on first level developer experience but on vendors and quality. What is a car worth that is easy to drive for everyone but breaks down frequently with bugs and missing&nbsp;spare parts?
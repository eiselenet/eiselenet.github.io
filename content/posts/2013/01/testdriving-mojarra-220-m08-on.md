---
title: "Testdriving Mojarra 2.2.0-m08 on GlassFish 3.1.2.2"
date: 2013-01-10 08:16:00 +0000
layout: post
tags: ["glassfish", "javaee7", "jsf"]
slug: "testdriving-mojarra-220-m08-on"
link: "2013/01/testdriving-mojarra-220-m08-on.html"
url: /2013/01/testdriving-mojarra-220-m08-on.html
---

We just slipped into 2013 and after a wonderful holiday season it is time to kick off the new posting season on my blog. With all the Java EE 7 specifications moving forward it is finally time to test-drive some of them and give feedback. If you are brave enough you could indeed take the latest GlassFish 4.0 nightly or promoted builds and test the complete integrated suite of specifications. If you have limited time and want some more reliable setup to look at you could also test-drive stuff on the latest GlassFish 3.1.2.2 release.
<br>
<br><b>Installing JSF 2.2</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="jsf_logo.gif" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" src="jsf_logo.gif"></a>
</div> As expected this isn't as convenient as we are used to it. Instead of simply packaging the snapshot dependency into your web-app and turning the classloader around, you will have to replace the bundled module in&nbsp;glassfish/modules for the complete server. I opened a bug on this and hope to get this fixed in the near future. JSF 2.2 is backward compatible with Java EE 6 containers and it should be able to package it in your app. For now just get the latest &nbsp;<a href="" target="_blank">Mojarra JSF 2.2.0 Milestone 8</a> release (javax.faces-2.2.0-m08.jar) and drop it into the glassfish/modules folder. Now rename or move the original&nbsp;javax.faces.jar to a save place. Don't forget to empty the osgi-cache folder of your domain (e.g.&nbsp;glassfish\domains\domain1\osgi-cache). Start your domain and keep an eye on the log-file to spot the Mojarra version:
<br>
 Mojarra 2.2.0-m08 (-SNAPSHOT 20130107-2105 https://svn.java.net/svn/mojarra~svn/tags/2.2.0-m08@11337)
<br>
<br><b>Test-driving new features</b>
<br>
 Now go ahead and create a new web project in your favorite IDE. If you are using Maven declare the needed dependency as provided and go ahead implementing some of the new features.
<br>
<pre class="brush:xml"> &lt;dependency&gt; &lt;groupId&gt;org.glassfish&lt;/groupId&gt; &lt;artifactId&gt;javax.faces&lt;/artifactId&gt; &lt;version&gt;2.2.0-m08&lt;/version&gt; &lt;scope&gt;provided&lt;/scope&gt; &lt;/dependency&gt; </pre> There is a great overview post <a href="" target="_blank">What’s new in JSF 2.2?</a> which is a great starting point. Don't forget to check back with the linked JIRA issues to see the latest implementation status of all the features.
<br>
 Mr. JSF Ed Burns himself gave a great one hour introduction at last years JavaOne titled "<a href="https://oracleus.activeevents.com/connect/sessionDetail.ww?SESSION_ID=3870" target="_blank">What’s New in JSF: A Complete Tour of JSF 2.2</a>" which includes links to all the relevant information and gives a comprehensive overview about the different feature categories. Find the slides and the captured presentation for free on the JavaOne Content Catalog page.
<br>
<br><b>Feature Summary</b>
<br>
 Six Big Ticket Features (<a href="http://bit.ly/JCP_20120911_BIG_TICKET">JIRA</a>):&nbsp;HTML5 Friendly Markup Support ,&nbsp;Faces Flows,&nbsp;Cross Site Request Forgery Protection,&nbsp;Loading Facelets via ResourceHandler,&nbsp;File Upload Component ,&nbsp;Multi-Templating
<br>
<br>
 28 Medium Sized Features (<a href="http://bit.ly/JCP_20120911_MEDIUM" target="_blank">JIRA</a>) in the following areas: Components/Renderers, Facelets, Lifecycle, Managed Beans, Resources
<br>
<br>
 44 Bug Fixes (<a href="http://bit.ly/JCP_20120911_BUG_FIXES" target="_blank">JIRA</a>) in different areas: Components/Renderers, Ajax, Specification errors and clarifications, EL, Facelets, Lifecycle, Resources
<br>
<br><b>Keep an eye on the progress</b>
<br>
 It is easy to follow the changes in the JSF space. Most likely because of the very transparent and open way the spec lead is driving it. There are public java.net projects for both the <a href="" target="_blank">specification</a> and the <a href="" target="_blank">implementation</a> and you are free to join the s<a href="http://java.net/projects/javaserverfaces-spec-public/lists" target="_blank">pecification mailing-lists</a>. There are also issue tracker for both the <a href="" target="_blank">specification</a> and the <a href="" target="_blank">implementation</a>&nbsp;and you can also have a look at the updated planning by visiting <a href="" target="_blank">http://jsf-spec.java.net/planning/</a>.
<br>
 Further on it is always a good idea to follow <a href="" target="_blank">Manfred Riem</a>&nbsp;and <a href="http://twitter.com/edburns" target="_blank">Ed Burns</a> on twitter. The jsf specification also has its own twitter handle (<a href="http://twitter.com/jsf_spec" target="_blank">@jsf_spec</a>)
<br>
<br><b>Give Feedback</b>
<br>
 Most important is to give feedback. Send comments regarding the specification to the users mailing-list and vote on issues you want to see solved. Another good idea is to help the project by following the <a href="" target="_blank">Adopt-a-JSR for Java EE 7</a>&nbsp;guidelines for <a href="http://glassfish.java.net/adoptajsr/jsr344.html" target="_blank">JavaServer Faces 2.2 (JSR 344)</a>
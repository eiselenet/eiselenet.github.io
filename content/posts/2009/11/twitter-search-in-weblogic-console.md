---
title: "Twitter Search in #weblogic console"
date: 2009-11-02 08:10:00 +0000
layout: post
tags: ["console", "twitter", "extenstion", "weblogic server"]
slug: "2009-11-02-twitter-search-in-weblogic-console"
url: /2009/11/twitter-search-in-weblogic-console.html
---

A need to have "toy" for all twitter addicted WebLogic admins out there :-)
<br>
 This is a quite lean console-extension, that searches for the #weblogic hashtag on twitter and displays it's results on top of every page of the wls admin console.
<br>
<br>
 This example again makes use of <a href="http://www.winterwell.com/software/jtwitter.php" target="_blank">JTwitter 1.2</a>.
<br><a href="http://www.blogger.com/jar/twitterconsole.war">Download the war file</a> and drop it to your domains console-ext directory. 
<br>
 If needed, add -DproxySet=true -DproxyHost=proxy -DproxyPort=1234 to your setDomainEnv scripts JAVA_PROPERTIES.
<br>
<br>
 Now restart the server and access the console via http://host:port/console/
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/console-extension-796522.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="320" src="http://www.eisele.net/blog/uploaded_images/console-extension-796520.png" width="320"></a>
<br>
<br>
 If you don't want to console to be deployed everytime you access it, you should switch of "on-demand deployment of internal applications". (Your domain =&gt; Configuration =&gt; General.
<br>
<br>
 Howto write your own console-extension:
<br>
 Have a look at the <a href="http://download.oracle.com/docs/cd/E12839_01/web.1111/e13745.pdf" target="_blank">Extending the Administration Console for Oracle WebLogic Server Guide (PDF)</a> and give it a try.
<br>
<br>
 Brief summary:
<br>
 - Setup WAR Project with your fav IDE
<br>
 - Create ext_jsp folder
<br>
 - Create portlets folder
<br>
 - Create WEB/INF/netuix-extension.xml file
<br>
 - Create portlets/yourPortletFile.portlet
<br>
 - Create ext_jsp/yourJSPpage.jsp
<br>
<br>
 As you might have guessed, this is a realy simple example. Only one JSP. No backend logic at all. You might think of different enhancements. But there was no time left for me, trying this out.
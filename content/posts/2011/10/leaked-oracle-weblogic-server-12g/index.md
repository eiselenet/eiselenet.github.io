---
title: "Leaked: Oracle WebLogic Server 12g"
date: 2011-10-11 11:13:00 +0000
layout: post
tags: ["oracle", "Java EE 6", "12g", "weblogic", "CDI", "leaked"]
slug: "leaked-oracle-weblogic-server-12g"

url: /2011/10/leaked-oracle-weblogic-server-12g.html
---

JavaOne is nearly one week behind us already and I am still working on the detailed blog posts about it. One thing I was really surprised of is the fact, that I didn't see a single mention about an update to my favorite application server out there. Yes, I love the WebLogic product. Since the beginning. Even if Oracle is making this a hard love for me since the acquisition of BEA. 
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="_MG_1527-13.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="213" src="_MG_1527-13.jpg" width="320"></a>
</div> Especially the late adoption path for&nbsp;Java EE 6&nbsp;was a point that forced me to look into other servers. Thank god, we have GlassFish as the reference implementation. Sticking to WebLogic with all the little Java EE 6 preview stuff in it wouldn't be satisfying at all.
<br>
<br><b>WebLogic at OpenWorld and JavaOne</b>
<br>
 Have you seen it? You probably have. But the server versions running on the laptops in the DemoGrounds were some reasonable recent versions of the 10.3.x.x also referred to as 11g. And this is still the old Java EE 5 version of it. And even the latest bug fix release was <a href="http://blog.eisele.net/2011/05/oracle-weblogic-server-11g-release-1.html">shipped way back in May</a>. So I was really looking forward to see a 12g somewhere. Even if it was under the table or in a dark room in the back. Nothing like this happened. At least not officially. But something else hit my inbox a few days back. A little demo video or better a&nbsp;screen-cast. But have a look and tell me, what you think:
<br>
<br><iframe allowfullscreen frameborder="0" height="349" src="http://www.youtube.com/embed/ICrvQi7yeP8?hl=en&amp;fs=1" width="425"></iframe>
<br>
<br><b>In 30 Seconds - WLS 12.1.1.0 Web Project with NetBeans</b>
<br>
 The video starts with a normal Java EE 6 Web profile based web-project with enabled CDI.
<br>
<br><b>Servlet without web.xml</b>
<br>
 After the project is created, you see (someone) creating a WebServlet called DemoServlet with path mappings to different URL-Pattern. The package names indicate, that this was thought of as an Oracle OpenWorld presentation. The WLS is obviously running on a 64-bit HotSpot Java SE Milestone Build (1.6.0_26). Even if the WLS start up time seems to be comparable to what I see with 11g today, I wouldn't bet on this to be the fact for a final GA release.Running a browser against the app shows the servlet responding accordingly. Changes to the servlet code seems to be hot deployed to the server. So no surprises here.
<br>
<br><b>Context and Dependency Injection</b>
<br>
 After 3 minutes and 20 seconds a simple POJO is created with a simple getter returning a smiley. This little smiley is injected into the servlet. Here we go: CDI rocks! Furter on, the Smiley interface is introduced and we get a couple of different flavours of smiles injected via custom qualifyers. An exception reveals, that JBoss Weld is in use. No further details about the version. Around minute 7 a javax.enterprise.event.Event is introduced which is a simple String event getting fired by servlet access. A corresponding EventCapcha class receives the events via an @Observer method, adds them to a list and prints it to out. The EventCapcha class is exposed as session scoped bean and gives access to the events list. 
<br><b><br>
 JSF 2.0 with facelets</b>
<br>
 Ok. This is not new. We have JSF 2.0 since a while with WLS. Now we have the full power with CDI integration. Around minute 11 a simple JSF template with a h:dataTable component is created which shows the fired events.
<br>
<br><b>Conclusion</b>
<br>
 The video ends at exactly 13 minutes and don't show any of the other Java EE 6 goddess. But it's by far more than we had seen until now about Java EE 6 running on WLS. And quite impressive to see WLS moving again. Don't ask me about anything beyond this post. I DON'T KNOW about timelines, I DON'T KNOW about versions (the ones statet here were taken from the screen-cast) and I especially DON'T KNOW about when you finally will be able to test drive this new WebLogic 12g by your own. What I do know is, that I am surprised to see this "leaked" way after Open World. It seems as if it simply doesn't fit into whatever strategy decisions have been made. I loved to watch this little screen-cast and I am badly looking forward having this in my own hands and giving it a test-drive with all the stuff we have created with GlassFish until now.
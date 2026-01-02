---
title: "Mark Little about Enterprise and IoT"
date: 2015-02-03 13:00:00 +0000
layout: post
tags: ["enterprise", "JavaLand", "IoT"]
slug: "mark-little-about-enterprise-and-iot"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2015/02/mark-little-about-enterprise-and-iot.html
---

<div class="separator" style="clear: both; text-align: center;">
</div>
<div class="separator" style="clear: both; text-align: center;">
 <a href="JavaLand_Keynote_Little.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" src="JavaLand_Keynote_Little.jpg" height="198" width="200"></a>
</div> Just a few more weeks to go, until the&nbsp;<a href="" target="_blank">JavaLand</a>&nbsp;conference is opening it's gates again. The 2015 edition has a packed <a href="https://www.doag.org/konferenz/konferenzplaner/b.php?id=483801&amp;locS=1" target="_blank">two-day schedule</a> with awesome speakers and also adds an <a href="" target="_blank">additional workshop</a> day. Main topic will be "celebrating 20 years of Java" and this is going to be highlighted by two exceptional keynotes. One will be delivered by Oracle's Marcus Lagergren and the other one by Mark Little (<a href="http://www.twitter.com/nmcl" target="_blank">@nmcl</a>), who is Red Hat's VP of Engineering and CTO of JBoss Middleware. I got a chance to talk to Mark about his keynote and the general direction about IoT and how everything comes together with enterprise capabilities underneath.
<br>
<br><b>Question:</b> "<i>Mark, you're going to give a keynote at JavaLand this year. Title: '<a href="https://www.doag.org/konferenz/konferenzplaner/konferenzplaner_details.php?locS=1&amp;id=483801&amp;vid=491907" target="_blank">Enterprise and IoT</a>'. Both well-known buzz words on their own. What made you combine them?"</i>
<br>
<br>
<div>
 <b>Answer:</b>&nbsp;"There were a number of things behind this, but I'll concentrate on just one. I'd been doing quite a bit with IoT and constrained devices such as the Raspberry Pi and various Android phones; during that time I'd started to think about how applications more complex than games would do some of the things we take for granted elsewhere, such as reliable messaging, fault tolerance, security, peer-to-peer interactions etc. As the types of applications being developed increase in complexity and importance to people (bank accounts are more important to many people than Flappy Birds), the need for enterprise capabilities will only increase. The IoT developer community is as bright as any developer community, so of course they'd be able to develop solutions to these problems. But I think that if there are perfectly good solutions out there already then we should try to use them first."
</div>
<div>
 <br>
</div>
<div>
 <b>Q:</b> "<i>Java EE is bloated and heavyweight. Especially servers are. Many companies are looking at lightweight JVM based solutions. Do we still need Java EE as container models? Why?</i>"
</div>
<div>
 <br>
</div>
<div>
 <b>A:</b> "I don't agree that Java EE is bloated and heavyweight. The standard doesn't say you have to build a bloated implementation - that's a design choice. Any container, whether it's something like docker, the JVM or even a Java EE application server, shouldn't really get in your way as a developer but should also offer you the capabilities you need for building and running your applications in a way that is easy to use, understand and manage. If you think about it, your laptop is a container. The operating system is a container. We take these for granted because over the years we've gotten really really good at building them to be unobtrusive. Yet if you look under the covers at your typical operating system, it's doing a lot of hard work for you and offering you capabilities such as process forking and scheduling, that you don't know you need but you do need them. A good Java EE application server should fit into the same category: providing you things such as messaging, transactions, cacheing etc. in an unobtrusive manner and with a minimal footprint."
</div>
<div>
 <br>
</div>
<div>
 <div>
  <b>Q:</b> "<i>Everybody can run anything on IoT hardware these days. The biggest problems are still in other areas, like orchestration and registry and zero configuration. Is there something that you can see in the near future to solve those issues?</i>"
 </div>
</div>
<div>
 <br>
</div>
<div>
 <b>A:</b> "One of the big areas that IoT lacks at the moment is consistency and agreement with the various hardware and software groups involved. I think things such as Project Atomic and Docker could help in some of this with easier packaging of applications, but that doesn't mean much if it only works on a subset of the devices because the industry hasn't agreed on standards. I'm a firm believer that we shouldn't standardise too early, but I also think we should at least recognise the problem and start to think about what it would mean for everyone to come together on an agreed platform. Problems such as orchestration, registry etc. are solvable (we've done it several times in various other waves of technology)."
</div>
<div>
 <br>
</div>
<div>
 <div>
  <b>Q:</b> "<i>How does Security fit into all of the above? I'm afraid, that someday someone can control my house. Isn't it time for a standardized solution? Is Red Hat contributing to any efforts around this already?</i>"
 </div>
</div>
<div>
 <br>
</div>
<div>
 <b>A:</b> "Yes security, or lack thereof, is a worry for many people around IoT. Obviously it's a worry outside of IoT too and we still see examples of security breaches almost on a weekly basis with banks, film companies etc. Security is one of those core features that I think must be in any computing platform, whether it's local or distributed. Standards really help here for similar reasons to what we've previously discussed: if I'm adding new devices to my home network I really want to be sure they're secured together and I also don't want to worry about learning multiple security protocols."
</div>
<div>
 <br>
</div>
<div>
 Thank you Mark for taking the time to answer my questions! I'm looking forward to seeing you in JavaLand. And for the fellow visitors: <a href="" target="_blank">There's still time to register</a>!&nbsp;
</div>
<div>
 <br>
</div>
<div>
 <br>
</div>
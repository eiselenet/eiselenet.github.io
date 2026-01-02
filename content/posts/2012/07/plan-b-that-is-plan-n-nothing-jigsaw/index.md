---
title: "Plan B? That is Plan N ... Nothing. Jigsaw follows in 2015."
date: 2012-07-18 07:56:00 +0000
layout: post
tags: ["2015", "Jigsaw", "Java9"]
slug: "plan-b-that-is-plan-n-nothing-jigsaw"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2012/07/plan-b-that-is-plan-n-nothing-jigsaw.html
---

what a day. When the typical European is winding down people in the States are starting with coffee. This is why I had a good night sleep over the recent news by Mark Reinhold. In a post titled "<a href="http://mreinhold.org/blog/late-for-the-train">Project Jigsaw: Late for the train</a>" he proposes to "defer Project Jigsaw to the next release, Java 9." With the modularization efforts being one of the key topics of Java's future on recent conferences and blog-posts this was a quite surprising move. Yesterday everybody was speculating about if there will be an JSR for Jigsaw or not. Today we know why this didn't happen.&nbsp;And I am disappointed about that. Here is why.
<br>
<br><b>Early notification? No - it's salami slicing! Or?&nbsp;</b>
<br>
 My first impression was: Hey, you guys don't get it. Dropping features late in the timeline isn't good for the community. But Donald made me realize that Java 8 is scheduled for May 2013. 
<br>
<blockquote class="twitter-tweet tw-align-center" data-in-reply-to="225309098579722241">
 @<a href="https://twitter.com/myfear">myfear</a> @<a href="https://twitter.com/jponge">jponge</a> @<a href="https://twitter.com/alexismp">alexismp</a> Again, I'm truly sorry that an 18 month in advance proposal isn't good enough for you.
 <br>
  — DonaldOJDK (@DonaldOJDK) <a data-datetime="2012-07-17T19:22:19+00:00" href="https://twitter.com/DonaldOJDK/status/225309476033527808">July 17, 2012</a>
</blockquote>
<script charset="utf-8" src="//platform.twitter.com/widgets.js"> </script> That basically means, we are informed 18 months ahead. But you guessed right. The reason for me being disappointed isn't the time. It's about the way the <a href="http://blog.eisele.net/2011/10/java-se-7-8-9-moving-java-forward.html" target="_blank">future of Java</a> has been communicated and used for marketing. Bert Ertmann naild it for me with his tweet:
<br>
<blockquote class="twitter-tweet">
 Plan B was promised for fall '12. Then became fall '13 and now one of its key features becomes fall '15. Boy, what a mess! <a href="https://twitter.com/search/%2523jigsaw">#jigsaw</a>
 <br>
  — Bert Ertman (@BertErtman) <a data-datetime="2012-07-17T20:02:08+00:00" href="https://twitter.com/BertErtman/status/225319493096845312">July 17, 2012</a>
</blockquote>
<script charset="utf-8" src="//platform.twitter.com/widgets.js"> </script>
<br>
 It seems to be a pattern here. Slicing everything until nothing relevant remains. But wait. Haven't we all seen the save harbor slides? Have we been ignoring them? Or aren't we aware of their real importance? Could all this be an agile planning process which simply isn't communicated in the right way? The community as the most important stakeholder (beside Oracle internal interests) obviously wasn't aware of the true reliability of the statements and plans. I have seen that before. And struggled with the same approach. Outlining the planning a bit more or even adding a burn down chart for the progress would be a very helpful instrument for a sneak into what's actually happening with the development. No, I'm not proposing to see all the little numbers, but I would love to have an indicator about stuff that is working like it was planned and stuff that is ... being postponed.
<br>
<br>
 I don't want to miss the chance to say thanks to Donald and Mark and also Dalibor and the many others from the OpenJDK/Oracle team for listening to the community. I am thankful to see them on Twitter, Email, Blogs, Forums and everywhere around to gather feedback and try to work on the way Oracle is communicating proposals and decisions.
<br>
<br><b>The real reasons?</b>
<br>
 Are there any more reasons behind that than the ones Mark expressed in his blog? "some significant technical challenges remain" and there is "not enough time left for the broad evaluation, review, and feedback which such a profound change to the Platform demands." Following Mark's twitter stream also reveals some more insights here. "Started on a shoestring at Sun, barely survived integration into Oracle, only fully staffed about a year ago …" (<a href="https://twitter.com/mreinhold/statuses/225316209971830784">@mreinhold</a>) For the external person the news sounded like ... wow that stuff has been started years ago and nobody was actually coding there? With the insights from Mark about I hope he is doing another blog-post about this does actually sound a little different. It might be that the truth is much simpler here. And it also would be good to know what the community can do to help. Mark: Go on! Keep lifting the former secret parts and try to&nbsp;facilitate&nbsp;what &nbsp;the community has to offer!
<br>
<br><b>Dreams of Java on iOS over?</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="_MG_1691.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="213" src="_MG_1691.jpg" width="320"></a>
</div> Do you remember what has been said at last JavaOne? The iOS and Android versions of JavaFX? Mobile goddess is back with Java since Java ME never really lifted up? Awesome. One of the most prominent requirements for that to happen was the ability to repackage the JDK to the right size for the job. Jigsaw was the idea behind that. As of today Mark proposes to introduce "one or more compact Profiles in the Java SE 8 spech <a href="http://mail.openjdk.java.net/pipermail/java-se-8-spec-observers/2012-July/000001.html">http://mail.openjdk.java.net/pipermail/java-se-8-spec-observers/2012-July/000001.html</a> to solve the missing module system. This in fact wouldn't be a "module" system but simply "just different ways to build the JDK, resulting in different-sized JREs." (<a href="https://twitter.com/mreinhold/status/225317655886839808">@mreinhold</a>).&nbsp;Yeah. Ok. And asked for the implications that might have the answer was: "We’ve already been preparing for the complexity of building and testing a modular platform." (<a href="http://twitter.com/mreinhold/statuses/225319412134187009">@mreinhold</a>) Seems as if the building blocks of that proposal are in place and no additional overhead is needed to get the mobile promises on the road.
<br>
 So we will not have to fear &gt;100 MB downloads for the JavaFX based apps. I don't know if they will meet the proposed distribution size starting at 10 MB. But anyway I expect it to be at a reasonable size.
<br>
<br><b>We don't need Jigsaw!?</b>
<br>
 Really? We already have OSGI, JBoss Modules, HK2 Kernel abstraction. A lot of stuff is in place and Jigsaw would only have helped the JDK. Really? I'm looking at it from a slightly different perspective. Even if it is true that a module system would have helped the JDK in the first place, the dependent platform specifications (like Java EE) also are in big need for a module system. And Java simply hasn't anything to over here. At least nothing that is in the reach of the JCP. So, looking for modularization approaches as of today would mean to embrace non JCP technologies. And we all know that this will not happen. So, looking at Java EE 7 and beyond we are quite sure that this proposal is putting a lot of pressure on the internal discussions. Not to forget about the additional years the competitors gain in entering and deciding the field. If you ask me, the worst thing that could happen is that Jigsaw ends up with being used JDK internally only. There is a good chance for exactly that to happen.
<br>
<br><b>What is left of Java 8?</b>
<br>
 With Jigsaw being stripped of the Java 8 time-frame the most important question here is about the what is left. Even still under the save harbor statements that's basically:
<br>
 - Project Lambda (JSR 335) will bring closures to the Java programming language.
<br>
 - New Date/Time API (JSR 310)
<br>
 - Type Annotations (JSR 308) 
<br>
 - A couple of <a href="http://openjdk.java.net/jeps/0">smaller feature</a>
<br>
 With the new scope Java 8 will ship on time, around September 2013 according to Mark. 
<br>
<br><b>Feeling better now?</b>
<br>
 I don't know. Even a good night sleep didn't bring back that&nbsp;comfy feeling I had a few days ago talking about modularization with Java.&nbsp;But I think I have to get over it and this is still another one of those "bad-hair" days which don't have a real reason for feeling sad. Seems as if I personally have to look at the alternatives. Waiting until 2015 is not an option. OSGI, JBoss Modules ... Here I come.
<br>
<br><b>Update 20.07.12</b>
<br>
 Alexis has put up an <a href="" target="_blank">interesting piece</a> about motivation and the true debacle behind Jigsaw:
<br>
<blockquote>
 As I wrote above, Oracle has the resources to declare Jigsaw a strategic goal. I can agree that it may be hard to deliver by late 2013 but waiting for 2016 is effectively killing Jigsaw and encouraging everyone to look at alternatives which will jeopardize yet even more Jigsaw’s chances of ever seeing the light of day. In fact, even Oracle is considering profiles in Java 8, an ugly band-aid if you ask me. One you’ll need to painfully tear off to get proper modularity in the platform. Jigsaw really shouldn’t be seen as “a new feature”, to me it’s really the Java reboot some people have been calling for a long time. Only a compatible one.
</blockquote>
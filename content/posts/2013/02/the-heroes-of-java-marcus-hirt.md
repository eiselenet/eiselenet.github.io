---
title: "The Heroes of Java: Marcus Hirt"
date: 2013-02-11 06:54:00 +0000
layout: post
tags: ["HoJ", "heroes", "Marcus Hirt"]
slug: "the-heroes-of-java-marcus-hirt"
link: "2013/02/the-heroes-of-java-marcus-hirt.html"
url: /2013/02/the-heroes-of-java-marcus-hirt.html
---

Lets continue the "<a href="http://blog.eisele.net/2011/09/heroes-of-java.html" target="_blank">Heroes of Java</a>" series. Today's interview has been planned nearly since the launch of the series and I knew that it would be a tough one to get. I know Marcus since a few years now and he is always busy providing the best diagnostic tools to Java developers. Thanks for finally joining, Marcus! It is a pleasure to have you here!
<br>
<br><b>Marcus Hirt</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG_0497-21.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="320" src="IMG_0497-21.jpg" width="239"></a>
</div> is one of the founders of Appeal Virtual Machines, the company that created the JRockit JVM. He is currently working as Team Lead for the Java Mission Control team. In his spare time he enjoys coding on his many pet projects, composing music, and scuba diving. Marcus has contributed JRockit related articles, whitepapers, tutorials, and webinars to the JRockit community, and has been an appreciated speaker at various conferences, such as Oracle Open World and Java One. He is also one of the two authors behind a popular <a href="http://blog.eisele.net/2010/11/review-oracle-jrockit-definitive-guide.html" target="_blank">book about JVM technology</a>&nbsp;(link to my review).
<br>
<br><b>General Part</b>
<br><i>Who are you?</i>
<br>
 I am a computer aficionado with a strikingly unmodern and lengthy romance with typed languages, profiling and diagnostics. I have three kids and a lovely wife, so right now there isn't much spare time to go around. When there was, I used to compose music, play the piano, scuba dive and do martial arts.
<br>
<br><i>Your official job title at your company?</i>
<br>
 Consulting Member of Technical Staff
<br>
<br><i>Do you care about it?</i>
<br>
 I care about being appreciated for my work. The title itself means nothing.
<br>
<br><i>Do you speak foreign languages? Which ones?</i>
<br>
 Swedish is my native&nbsp;tongue&nbsp; and my preferred language for anything that is not computer related. That said, since most of the terminology in our business is in English, I actually prefer English when talking shop. I am half Swiss, and I did spend some time at Real Gymnasium Kirchenfeld in Bern. I haven't used my German since then though, so it is beyond rusty.
<br>
<br><i>How long is your daily "bootstrap" process? (Coffee, news, email)</i>
<br>
 If you don't count email, it is almost non-existent. However, in my role as a team leader, email is chewing up a good portion of the morning these days. Thanks to the excellent mass transit system in Stockholm, that is usually taken care of before I arrive at the office. At least during the winters. During the summers I usually drive my motorcycle to work.
<br>
<br><b>Twitter</b>
<br><i>You have a twitter handle? Why?</i>
<br>
 I indiscriminately sign up for all social services. Then I find that I don't use most of them. Twitter is a bit of a exception, since I do tend to read what others write. When I do tweet it is mostly about new obscure and/or unsupported features in the Hotspot JDK. My twitter handle is <a href="https://twitter.com/hirt" target="_blank">@hirt</a>.
<br>
<br><i>Whom are you following in general?</i>
<br>
 I mostly follow people that I know and respect in the Java community.
<br>
<br><i>Do you have a personal "policy" for twitter?</i>
<br>
 I try to avoid it at work.
<br>
<br><i>Does your company restrict or encourage you with your twitter usage?</i>
<br>
 Oracle has neither actively encouraged nor restricted my twitter usage. The only time I can recall a company actively encouraging me to engage in some official social capacity was some years ago, when BEA tried to encourage people to blog. I’ve since moved away from the official company blog, because of a tooling issue.
<br>
<br><b>Work</b>
<br><i>What's your daily development setup? (OS/IDE/VC/other Tools)</i>
<br>
 Since the first target platform for JRockit was Windows, I've stuck with Windows at work. I am using Windows 7/Eclipse/Perforce and Visual Studio. At home I am using Mac OS X/Eclipse/Git&amp;Perforce and XCode.
<br>
<br><i>Which is the tool providing most productivity to your work?</i>
<br>
 These days: Eclipse. No doubt.
<br>
<br><i>Your preferred way of interacting with co-workers?</i>
<br>
 Face to face for longer discussions. IM is good for smaller things, since you can choose when to handle the interrupt, whilst still being fairly interactive.
<br>
<br><i>What's your favorite way of managing your todo's?</i>
<br>
 Pen and paper. Stone age, right?
<br>
<br><i>If you could make a wish for a job at your favorite company: What would that be?</i>
<br>
 Whichever would give me the resources to attack some of the high impact development projects on my "want to do" list. Oracle is currently quite a good place to be.
<br>
<br><b>Java</b>
<br><i>You're programming in Java. Why?</i>
<br>
 To be honest, I am not exclusively programming in Java. When I do, it is because it is one of the programming environments in which I find myself to be the most productive. It may not be the least verbose or most elegant of languages, but the tooling and debugging capabilities are top notch. Not to mention that some intrinsic features of the language itself, such as the memory management, makes it easier to write error free code. Also, since there has been competition around the JVM for more than a decade, the JVMs for Java are really quite sophisticated. Not to mention fast.
<br>
<br><i>What's least fun with Java?</i>
<br>
 It is unnecessarily verbose (more type inference please), type erasure (ever sent in a class to your generic type to have a chance of knowing what runtime type it is?), and any and all things that makes the illusion of an all powerful runtime break down. In a perfect world, a Java programmer should not have to worry about the details of the JVM configuration. For instance, why should I need to estimate how much space I will need for constants and class metadata (perm gen)? Thankfully there is work being done on this as we speak; the perm gen is scheduled for removal in JDK 8. I think there is a lot to be said for improving the usability of the JVM.
<br>
<br><i>If you could change one thing with Java, what would that be?</i>
<br>
 There are many who want Java to be everything to everyone. I don't subscribe to that view. Instead, let's make it easy to run whatever language you want on the JVM. That said, if I could change something about the implementation, I would probably want a thread local garbage collector. One with insanely good heuristics as to when to back off and stop handling an object thread locally. Then there are some other things, but since I may start working on them soon, I would rather keep them to myself for now. :)
<br>
<br><i>What's your personal favorite in dynamic languages?</i>
<br>
 Ruby is cute. I especially like the implementation on the JVM (JRuby).
<br>
<br><i>Which programming technique has moved you forwards most and why?</i>
<br>
 When I first started my education at the Royal Institute of Technology, I had already programmed in various languages, such as Pascal, C and assembly. I really thought I had things figured out, until I came to the first computer science course. There I got confronted with SICP and Scheme. That was IMHO a genius move by the computer science department. All the cocky kids with prior experience, such as myself, got a rich serving of humble-pie. Functional programming taught me very elegant ways of expressing myself. Kudos to MIT and Sussman et al.
<br>
<br><i>What was the biggest project you've ever worked on?</i>
<br>
 JRockit and JRockit Mission Control. I was one of the founders of Appeal (Appeal Virtual Machines &amp; Appeal Software Solutions), a big project in itself.
<br>
<br><i>Which was the worst programming mistake you did?</i>
<br>
 Well, maybe not strictly a programming mistake, but one of the worst red-face issues I've done is when a JRockit performance counter was slightly misspelled - the 'o' was accidentally dropped from *count. The bug report stated that "the customer was not amused". I must admit I was though.
<br>
<br>
 Another fun, deliberate, "mistake" was when I added the following three lines to one of the Mission Control property files:
<br>
 ------
<br>
 # :) We just felt that we needed this one translated...
<br>
 # /The MC team
<br>
 Template_DEFAULT_TEMPLATE_NAME=All your base are belong to us!
<br>
 ------
<br>
 I was hoping to get a cynical remark back from the translation team, but they just translated it the best they could. Heh.
<br>
<br>
 Finally, one of the worst programming mistakes in recent history was in a small start-up project. A hash code calculation error caused some subtle errors to one of many data points in a running production system. I finally solved the problem when I got fiber installed at home and got bold. In desperation I started a node with jdwp turned on, and I then proceeded to set break points and evaluate code remotely over an ssh tunnel. The latency was so low that it almost felt like a local debugging session. Crazy, but you gotta love Java for providing you with options. ;)
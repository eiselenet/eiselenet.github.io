---
title: "The Heroes of Java: Coleen Phillimore"
date: 2013-01-21 11:16:00 +0000
layout: post
tags: ["Coleen Phillimore", "HoJ", "java"]
slug: "the-heroes-of-java-coleen-phillimore"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2013/01/the-heroes-of-java-coleen-phillimore.html
---

The "<a href="http://blog.eisele.net/2011/09/heroes-of-java.html" target="_blank">Heroes of Java</a>" series took a short break. After the first 20 it is time to start over in 2013. This time it is kind of an unexpected hero. During my ongoing search for the real heroes of Java I stumbled upon Coleen.
<br>
<br><b>Coleen&nbsp;Phillimore</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="coleen_phillimore.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="320" src="coleen_phillimore.jpg" width="240"></a>
</div> is a Hotspot veteran and hers and the work of many others build the cornerstones of every single line of Java ever written. I'm very happy that she joined my little series. Thanks to <a href="http://blog.eisele.net/2011/09/heroes-of-java-marcus-lagergren.html">Marcus</a> for getting me in touch!
<br>
<br><b>General Part</b>
<br><i>Who are you?</i>
<br>
 I am a software engineer working on the Hotspot Java Virtual Machine at Oracle. I work in the runtime group and we work on startup and footprint optimizations, the interpreter, class file parsing, verification, internal class data representation, and interfaces to GC and the compilers. I've been doing this for a long time - 12 years at Sun then Oracle and about 30 years in the industry on various compilers for other languages.
<br>
<br><i>Your offical job title at your company?</i>
<br>
 My official title is "principle engineer" although most of us talk about our titles as IC&lt;something&gt;, where I think IC is "Individual Contributor".
<br>
<br><i>Do you care about it? (the title)</i>
<br>
 I don't care a whole lot about titles. &nbsp;Most of my co-workers are quite senior and have the same or one better title.
<br>
<br><i>Do you speak foreign languages? Which ones?</i>
<br>
 I had a high school French. &nbsp; I still remember some of the words but that's it.
<br>
<br><i>How long is your daily "bootstrap" process? (Coffee, news, email)</i>
<br>
 My daily bootstrap process takes a couple hours. &nbsp; Sometimes it takes longer so I don't make it into the office and work from home instead to save the driving. &nbsp; I usually drink 3-4 cups of coffee while checking personal email and organizing some activity for my teenage son. &nbsp; I also have my Oracle email configured in Thunderbird, so when I run out of home email, I start answering these emails. &nbsp; I might do some code reviews and reply to colleagues in Stockholm or Australia or an earlier time zone.
<br>
<br><b>Twitter</b>
<br><i>You have a twitter handle? Why?</i>
<br>
 No, I really haven't figured out why I would have a twitter account or follow anyone on it (sorry this is boring).
<br>
<br><b>Work</b>
<br><i>What's your daily development setup? (OS/IDE/VC/other Tools)</i>
<br>
 I have just installed Ubuntu 12.04 on some Dell 4 way system in my office. We have racks of different types of virtual machines at Oracle somewhere that we use to test on different platforms. It's all virtualized now.
<br>
<br><i>Which is the tool providing most productivity to your work?</i>
<br>
 In general, I am as about as low tech as it gets. &nbsp;I mostly use vim, make, gcc and gdb. &nbsp; grep -r is my IDE.
<br>
<br><i>Your prefered way of interacting with co-workers?</i>
<br>
 My co-workers are all over the world. We have very long email threads. I do have some coworkers in my office and we actually talk in person and can draw pictures on whiteboards to explain things. &nbsp; This is my preference but it's impractical for everyone. Some of us use IM which is better than email.
<br>
<br><i>What's your favorite way of managing your todo's?</i>
<br>
 I keep lists with arrows in front of these items in a spiral notebook. &nbsp; Was this supposed to be some high tech gadget? &nbsp; I'm the boringest tech person ever.
<br>
<br><i>If you could make a wish for a job at your favorite company: What would that be?</i>
<br>
 Well, working at Oracle on the JVM is really fun. We don't seem to run out of ideas for improvements and code to write. &nbsp; My husband's company Abinitio would be great to work at because they all went to the Galapagos but they won't say what they do.
<br>
<br><b>Java</b>
<br><i>You're programming in Java. Why?</i>
<br>
 I am actually not coding in Java. The Hotspot JVM is written in C++. There's an openjdk project to write the JVM, or at least parts of it in Java, called Graal. That would be cool to work on when it's closer to a product.
<br>
<br><i>What's your personal favorite in dynamic languages?</i>
<br>
 I haven't had time to learn and use dynamic languages yet, but all the runtime support underneath these dynamic languages is in the JVM. We've had to change assumptions that we've made for Java to accommodate these new languages. It's totally cool that there's this whole world on top of the JVM but it's also a lot of pressure to make it faster, better, smaller and more reliable.
<br>
<br><i>What was the biggest project you've ever worked on?</i>
<br>
 Last summer, a few of us completed a project where we moved Java class metadata from the Java heap, aka PermGen, to a different heap area. This probably seems counter intuitive to a Java programmer because now we have to use explicit memory management and not let the garbage collector do the work. There were a lot of problems with managing our JVM memory through the garbage collectors and the memory for class metadata was limited by this. We were finding many applications that load too many classes and would get OutOfMemoryError on PermGen which wasn't recoverable. This project changed most of the JVM and removed a ton of code, which is sort of my favorite thing to do.
<br>
<br><i>Which was the worst programming mistake you did?</i>
<br>
 I tend to be a code environmentalist - reduce, reuse and recycle and all that. Most of my programming mistakes have been to remove code and find out that it was in fact needed.
<br>
<div>
 <br>
</div>
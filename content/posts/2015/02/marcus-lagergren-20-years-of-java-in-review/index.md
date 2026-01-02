---
title: "Marcus Lagergren 20 years of Java In Review"
date: 2015-02-19 14:00:00 +0000
layout: post
tags: ["java", "20 years", "JavaLand"]
slug: "marcus-lagergren-20-years-of-java-in-review"

url: /2015/02/marcus-lagergren-20-years-of-java-in-review.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;" target="_blank"><img border="0" src="csm_JavaLand_Keynote_Lagergren_1fb3b64023.jpg"></a>
</div> We know each other since some years now. And I am very very pleased to see, that we finally could make it happen to have him speaking at <a href="" target="_blank">JavaLand</a> as a Keynoter. And what would be better for a JVM veteran than talking about this years anniversary: Happy Birthday Java!
<br>
 Thank you Marcus, for taking the time!
<br>
<br>
 I am looking forward to a beer or two at JavaLand! Follow his rumblings about dynamic languages on the JVM and surgeries on Twitter <a href="https://twitter.com/lagergren" target="_blank">@lagergren</a>.
<br>
 But it's obviously best to see him live on stage at JavaLand! So, if you haven't <a href="" target="_blank">go ahead and register</a>!
<br>
<br>
 Marcus has worked with Java since 1995 and with runtimes since 1999. He started out with JVMs as one of the founders of Appeal Virtual Machines, the company that created the JRockit JVM. His <a href="https://www.doag.org/konferenz/konferenzplaner/konferenzplaner_details.php?id=483801&amp;locS=0&amp;vid=491690" target="_blank">JavaLand keynote</a> will be a historical trip down memory lane - going through 20 years of Java and JVM implementations. This will be partly from Marcuss own perspective as one of the architects behind JRockit, with plenty of stories from the trenches, like the benchmarking competitions between BEA/Oracle, Sun and IBM. We will see how Java and the JVM turned into the robust high performance server side platform that it is today, partly through personal observations and partly through a more objective what a long strange trip this has been. He will take you back to 1995 in a time machine and slowly bring you to 2014, going over the major Java releases and the runtime innovations made in the JVM side to get us here. Finally, we will do a short trip into the future, even though the future is always fuzzy, to explore what might be coming next. With Java 8, the biggest release in Java history, Java is more vibrant and alive than ever before. This is an evolution that wont be stopped.
<br>
<br><b>Question:</b> "<i>20 Years of Java. Wow! How long do you know Java? And I know, you've been writing the JRockit JVM back in the days .. does writing a JVM actually means knowing the language it is written for? The JVM is written in C/C++, correct?</i>"
<br>
<br><b>Answer:</b> "I first started working with Java when I was a research intern at Ericsson Medialab in 1995-1996. This was Java 1.0 and its betas. Back in those days the JDK fit on one floppy. Yes. Floppy. We had those then. 
<br>
 I first started working with runtimes in 1998-1999, when I joined Appeal Virtual Machines on the JRockit project.
<br>
 The JVM is written in C++ (and metaprogrammed in Java, and with some hardcoded assembly for different silicons).
<br>
 I would say that you can get away with a lot of gaps in your language knowledge if you are a runtime implementor for the language in question. Right now, one of the things I am working on is getting the
<br>
 JVM to run dynamic languages, and even though I am implementing parts of a JavaScript and TypeScript runtime, I don’t know all the details of those languages. When it comes to Java, I have been known to answer programming questions with “I am not a Java programmer” quite frequently. Ironically I am on the Java language team ;-)
<br>
 Java is also a bit different from, say, C, because it has such a large API library in the JDK. I need an IDE when I program Java to remember what all the library functions are called, and get auto completion and so on. Otherwise, I'm an Emacs dude. So, I wouldn't call myself a Java expert, at least not on the libraries, but I get stuff done. Especially these last three years when the majority of my programming time has been in Java for the first time in my life.
<br>
 Usually, when I have a Java question in my job, I ask someone else in the Java language team, often my poor colleague Joel Borggren-Franck.
<br>
 When it comes to C, it's my native language, so there, you won't catch me off guard with anything. When it comes to C++, I don't think anyone fully knows that horror. Not even Bjarne Stroustrup. It's rapidly turning into a Frankenstein’s monster pseudo-runtime language as well, so I hope it will be put out of its misery soon."
<br>
<br><b>Q:</b> <i>"What is your favorite memory about Java? Did you ever meet James Gosling and won an argument against him? Or something else unknown and funny?"</i>
<br>
<br>
 A: "I have indeed met Gosling several times, but I've never had a chance to have a deep, long conversation with him. Usually, we agree on what small issues we have discussed.
<br>
 My favourite memory of Java has to be either the early JavaOne:s at the end of last century or the beginning of this century, and pushing JRockit there both on the exhibition floor and elsewhere. Otherwise, I'd pick any triumphant moment when we were leading the benchmark wars between 2000-2005, competing in server side performance with Sun HotSpot and IBM J9. Once, we were 30% better than anyone else, and I seem to remember this was due to us being the first to implement
<br>
 comperssed references. That was an awesome moment. I actually remember that being met with some hostility by some people from Sun then, who were bad sports. But usually, we had a great time and lots of great conversation with our competitors.
<br>
 All in all, the best job of my life was when I was JIT lead for JRockit and shared an office with equally driven compiler engineers. It’s great now too, but we had something special in the underdog position against Sun and IBM, and the ability to completly reshape our code base whenever we needed to. Those were great days. We listened to massive heavy metal all the time. Now, I work in a
<br>
 cubicle farm, so it’s headphones instead. Not the same feeling :)
<br>
<br><b>Q:</b> <i>"Looking at JVM technology today compared with the JRockit beginnings: If I'd ask you to name the most significant changes in a couple of sentences, how would that look like?"</i>
<br>
<br><b>A:</b> "If you are talking about JVM technology and not the Java language, I'd say low latency GC technology, invokedynamic and the ability to do in-production monitoring with very low overhead, which Java Mission Control enables. If it's Java, Java 8 is the biggest and most
<br>
 revolutionary feature... ever... I think. "
<br>
<br><b>Q:</b> <i>"I think it is safe to assume, that working in the language team at Oracle is one of the most amazing places. Surrounded by extremely smart people working on the future of what so many people use on a daily base. Do you personally feel this as pressure?"</i>
<br>
<br><b>A:</b> "No I don't. I feel that it's an asset, and it's the main benefit of my job and the reason that I love it.. There is always someone around who is smarter than me that I can ask for help, and the team I am on is so senior that it's virtually self managing. There is no doubt as to what I should do when I sit down at my desk in the morning, and the competence level of the engineers turns everything into a well oiled machine. I am very blessed to be in such an environment.
<br>
 I _would_ feel pressure if I were the smartest engineer in a place of low performers, not the other way around. The language team is a place where you continuously learn stuff from your
<br>
 colleagues, and I love that. Picturing myself as, say, a web frontend consultant, always makes me shudder. (Disclaimer: I don't have anything against web frontend consultants, or think they are stupid - I just don't think I'd get to solve complex algorithmic problems every day there, which is what I live and breathe for)."
<br>
<br><b>Q:</b> "What are you looking forward for in this year of celebrating the 20 year success story?"
<br><b>A: </b>"I want Java 8 to fully take off, that would be awesome. I would also like to ship a version of our Nashorn runtime that is more language agnostic and can be used as the base for more dynamic languages on the JVM than TypeScript and JavaScript, and start to see it being put to industry use. (I'll also get some serious startup time improvements done, it'll be awesome, hopefully)"
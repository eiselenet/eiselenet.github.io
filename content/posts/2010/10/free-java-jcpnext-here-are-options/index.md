---
title: "Free Java? jcp.next? Here are the options!"
date: 2010-10-04 07:06:00 +0000
layout: post
tags: ["java", "oracle", "future", "jcp"]
slug: "free-java-jcpnext-here-are-options"

url: /2010/10/free-java-jcpnext-here-are-options.html
---

A lot has been written on this topic over the last few weeks. And it was one of those things that depressed me during JavaONE. Nobody spend an offical word on all those rumors, lawsuits and a possible jcp.next. But the voices calling for a change in the treatment of Java hasn't become silent. 
<br>
 Seems as if it is time to step back and order the thoughts. That is what I was trying to do this weekend. And here is a brief analysis about the "publicly known" facts and the options for the future in my eyes. Why? I guess, Eduardo was right with his response on my tweeted fears. He said:
<br>
<blockquote>
 my point: easy to say free it when not owned nor paid for; change places &amp; well see
 <br>
  (Source: <a href="http://twitter.com/pelegri/status/26205382284" target="_blank">twitter</a>)
</blockquote>
<br><!--more-->
<br><b>History of the JCP</b>
<br>
 On December 8, 1998, Sun Microsystems introduced the Java Community Process Program for the development and revision of Java technology specifications. This formal process was designed to be fast, flexible, and adaptable to a wide variety of working styles present in the community today. A draft version of the process was circulated to licensees and others for their review and comment on October 8, 1998. The goals were: 
<br>
<ul>
 <li>Enable the broader Java Community to participate in the proposal, selection, and development of Java APIs by establishing a means for both licensees and non-licensees to participate.</li>
 <li>Enable members of the Java Community to propose and carry-out new API development efforts without the need for Sun engineers to be actively involved.</li>
 <li>Ensure that the process is faithfully followed by all participants each time it is used by enabling auditing at key milestones.</li>
 <li>Ensure that each specification is backed by both a reference implementation and the associated suite of conformance tests.</li>
 <li>Help foster a good liaison between the Java Community and other bodies such as consortia, standards bodies, academic research groups, and non-profit organizations.</li>
</ul>
<br>
 On June 2, 2000, JCP 2.0 replaced the previous JCP 1.0 version for new submissions. Further refinements to the voting rules resulted in JCP 2.1, introduced on July 10, 2001. A major revision of the licensing rules for the Spec, RI and TCK as well as IP policy changes and process changes is put in place by JCP 2.5, launched on October 29, 2002. The process was revised again in May 2006 with the release of JCP 2.6. The current version of the process is <a href="http://jcp.org/en/procedures/jcp2" target="_blank">JCP 2.7</a>, introduced in May 2009. The JCP itsel is developed under it's own <a href="http://jcp.org/en/jsr/summary?id=215" target="_blank">JSR-215</a>. 
<br>
<br><b>Most prominent trouble</b>
<br>
 The most prominent, let's call it "opponent", to the jcp was the ASF. Beginning in 2002 they were <a href="http://jakarta.apache.org/site/jspa-position.html" target="_blank">fighting with the JSPA</a> (Java Specification Participation Agreement) (<a href="http://jcp.org/aboutJava/communityprocess/maintenance/jsr099/JSPA2_0_1-Jan11.pdf" target="_blank">PDF</a> / <a href="http://jcp.org/aboutJava/communityprocess/final/jsr171/index.html" target="_blank">web</a>). The JSPA is the legal agreement members sign with Sun when joining the JCP. This document defines many of the rules of the JCP and is responsible for allowing -- and in some cases requiring -- many of the restrictions which have hindered open source implementations of JSR specification. Beside the licensing costs itself, the fact that the JSR spec license prohibits other compatible independent open source licenses for the implementation of a JSR were the biggest complaints by the ASF. The conflict escalated in 2007 with the <a href="http://www.apache.org/jcp/sunopenletter.html" target="_blank">Open Letter to Sun Microsystems</a> about acquireing an acceptable license for the Java SE 5 technology compatibility kit (TCK) needed by the Apache Harmony project. But they never have been the only ones having issues with the jcp and especially the licensing options available. All others (RedHat, IBM, SAP, et al) also made comments on the "field of use restrictions", that are possible within the licenses. It seems as if the 
<br>
<blockquote>
 JCP has stalemated for the past year on Java 7 over Oracle wanting to add a "restricted field of use condition" to it restricting the OpenJDK [ed: and other JSRs?] to desktop and server, not mobile. (Source: <a href="" target="_blank">Greg Luck's Blog</a>
</blockquote>According to <a href="" target="_blank">the Register</a> earlier this month, the JCP passed a resolution calling on Oracle to spin the group out as an as a independent, vendor-neutral body where all members are equal. The article also states, that there are ongoing discussions about the future of the jcp. Reading through it, it really seems, that the relations are strained. 
<br>
<br><b>Free Java? Closed Java?</b>
<br>
 If you step back from the current press around this and look at it from a neutral point of view, the goal of the jcp was to share and let the community contribute but maintain compatibility among Java language based products and protect and promote certain trademarks used in connection with Java technology. As you might guess, this always has been and ever will be a balancing act.
<br>
 But at the end of the day, this worked. Java is by far the biggest programming language in terms of developers, products implementing and using it. And now? What's next? Java has grown up. It's widespread. Does it still need a maintainer? A watchdog? A steward? Someone careing for it? The options for the future are endless and open. Until anybody from the JCP members or Oracle itself speaks officially about Kurians proposals to the JCP on its future this is what the solution space covers. And as always, there are plenty pros and cons on any decision. 
<br>
<br><i>a) Free Java!</i>
<br>
 Offer all material, RI, TCK and sources under an approved opensource license. Forget about the jcp and push everything to a public repository. This could probably even be achieved by a complete Java Fork, as mentioned by many voices in the past few weeks. But this also would cause the end to the industrial standard, we all have been working with. If you recall for example the J2EE 1.2/1.3 times and think about the effort taken to do vendor/container abstraction back than, you probably have a good idea about what this option holds for the future. We probably would end up with at very micro language kernel and lot's of product and vendor specific implementations. Oracle will never ever get any money back on their big investment into Sun and Java and they would have to find a way integrating their own ideas into all this. 
<br>
<br><i>b) Free the JCP!</i>
<br>
 Open the jcp and make it independent from Oracle. No "restricted field of use conditions" or anything else given by Oracle. A simple democratic process like it is in use with many other bigger opensource projects (e.g. eclipse, w3c). 
<br>
 Eclipse could be a good example of course in general. After the initial donation from Oracle they are free to contribute but do no longer have any extra influence on the process at all. The language and specifications itself are governed by the new organisation. This would probably be a big boost for Java. We would see it around more often and it could get onto even more devices and plattforms than it already is today. But the overall progress will probably slow down. Without having hardly any commercial drive behind this, it's more or less up to the companies supporting how fast everything will develop. At the end of the day, there are open questions. Not saying it is not doable, but it's complicated.
<br>
<br><i>c) Java as a product</i>
<br>
 Within Oracle. Simple product licensing for 3rd parties. No jcp. An option Sun was pointing to during the trouble finalizing Java EE 6 allready. Probably the fastest and most promissing way for the language and specification features itself. Oracle proved, that they have the power to execute. But this will be the worst solution for the community and the adoption of the platform in general. Even if there would be an educational licensing and the commercial licenses were affordable, this will be the death to Java in the same way it would be if it were completely free. Only Oracle's ideas would be the path into the future and all those visions and innovations from Google, RedHat et al will never be respected. This will also lead to a flood of lawsuits and the overall adoption will decrease.
<br>
<br><i>d) Anything in between a)-c) and any mixtures</i>
<br>
 This is probably the most likely solution we will see. All involved companies have their smartest folks working and talking on this. And after beeing able to attend the <a href="http://blog.eisele.net/2010/09/day-1-aced-briefing-and-future-of-java.html" target="_blank">passionate talk from Thomas Kurian</a> during our ACE Directors briefing one thing is clear to me: Oracle is passionate about Java. Probably even more than anybody else involved. And I still hope, that this passion not only is about making money. But about having the future of one of the most important and widespread languages in their hands.
<br>
<br>
 From a Java and comunity point of view I would like to make an advise to all the involved parties:
<br>
 Please, for the sake of Java and it's future: Step back from the past. Forget about it and find a sustainable solution from which everybody could earn added value! And please stop publicly bashing each other. Decide to either:
<br>
 a) be completely transparent about the process or
<br>
 b) keep silence and come up with a solution
<br>
 Anything else does harm the community. And it's strong. You should not play with them.
<br>
<br>
 Further readings:
<br>
 Stephen Colebourne about <a href="http://www.jroller.com/scolebourne/entry/oracle_and_the_business_of" target="_blank">Oracle and the Business of Java</a>
<br>
<br>
 [UPDATE: Further readings from today!]
<br>
 Sacha Labourey about <a href="" target="_blank">Time to Fork Java? si vis pacem, para bellum</a>
<br>
 James Governor about <a href="" target="_blank">Java: The Unipolar Moment, On distributed governance for distributed software</a>
---
title: "JCP Approves the Java EE 6 Public Review (JSR-316)"
date: 2009-02-25 05:00:00 +0000
layout: post
tags: ["Java EE", "JSR-299", "Java EE 6", "jee", "JSR-316", "jee6"]
slug: "jcp-approves-java-ee-6-public-review"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2009/02/jcp-approves-java-ee-6-public-review.html
---

The <a href="http://jcp.org/en/participation/committee#SEEE" target="_blank">Executive Committee for EE/SE</a> has approved the Public Review for JSR 316, the Java EE 6 Specification.
<br>
<br>
The vote was 12 YES, 1 NO, 1 ABSTAIN, and 2 NO-VOTED;<a href="http://jcp.org/en/jsr/results?id=4821" target="_blank"> see Ballot Results</a>. You can <a href="http://jcp.org/aboutJava/communityprocess/pr/jsr316/index.html" target="_blank">download the PRD draft</a>, and, as always, your feedback to the Executive Committee and to the JSR316 Expert Group are very welcome. 
<br>
<br>
Most interesting comments from the ballot results are the comments from SAP and Springsource.
<br>
<br>
<blockquote>
 <br>
 SAP AG voted Yes with the following comment:
 <br>
 SAP votes YES but would like to content that more work is required for the integration of JSR 299 into the platform to be useful and successful. We also would like the Spec Lead to consider putting more emphasis on architectural rigor regarding a single consolidated and extensible component model to be used across the platform - right now there are three (EJB, JSF and JSR 299).
 <br>
</blockquote>
<br>
<br>
SAP also has different component models in place already. And none of them is truely widespread in the java corner of SAP at the moment. If you want to get more information on this, look the "<a href="https://www.sdn.sap.com/irj/sdn/nw-development?rid=/webcontent/uuid/60af152d-1744-2a10-70ae-cab6e6e9e6b4" target="_blank">getting started with application development</a>" guide on the SAP community network.
<br>
I am a big fan of SAP's JavaAS &gt;=7. But I am not shure, if JEE is all about one common component model. Even if we have three ore more in place. The only thing that should be forced is the seamless interaction between them.
<br>
<br>
<blockquote>
 <br>
 SpringSource voted Abstain with the following comment:
 <br>
 The introduction of profiles is a step in the right direction. However, we are disappointed not to see a minimal web profile, especially as this has become the choice of most enterprise Java users.
 <br>
 <br>
 As with previous releases, the inclusion of unproven technology is a risk, especially in JSR 299 and EJB concurrency annotations. The number of substantial changes late in the process also gives us concern about the maturity of the result--especially, the impact of the scope creep of JSR 299 on other specifications.
 <br>
 <br>
 Experience has shown that tying dependency injection features to a server environment does not match user requirements, as injection is common to all application types. We would have preferred to see a dependency injection model for SE, as we proposed in 2007.
 <br>
 <br>
 Finally, we are not convinced that the end result matches the goals of Java EE 6 as defined in the original specification request, which we strongly supported.
 <br>
</blockquote>
<br>
<br>
Ok. This is a more serious comment. I am worried about the missing profiles, too. As I have seen the "Webprofile" for the first time I was wondering about what someone could call "web" :-) The requested "minimal webprofile" could be something similar to the JEE5 Webcontainer. Anyway: It is missing and I do expect that we get in bigger trouble with this doing our first projects on JEE6. Beside that I would love to see some more specialized profiles (e.g. portal, sip, etc). 
<br>
<br>
Not beeing a member of the EG, I am not shure about the "inclusion of unproven technology" and the "number of substantial changes late in the process". But I am shure that both can result in problems. Looking at the different EG members with all their different interests it's like a miracle to me, seeing a new consolidated version of the JEE Spec "nearly" in time ;) What seems to be clear is, that if the inventors of Spring (aka the non JEE component guys :-)) have reservations on the impact of the JSR-299 it's worth looking at.
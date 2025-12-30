---
title: "Basic Thoughts on Architecture and Design Reviews"
date: 2010-03-04 06:51:00 +0000
layout: post
tags: ["review", "architecture", "design"]
slug: "2010-03-04-basic-thoughts-on-architecture-and"
url: /2010/03/basic-thoughts-on-architecture-and.html
---

I did some architecture reviews in the past. Most of them internal but also for my company's clients. At the end of the day this always has been and probably always will be a thankless job. For many reasons. This post was thought of as an incomplete list of things to keep in mind while doing this kind of reviews and should give you some advice.
<br>
 If you have your own experiences on this, I would love to read about them in the comments.
<br>
<br><b>You the reviewer?</b>
<br>
 The bottom line for all kinds of reviews is, that you take a deep look the thoughts of other people and rate the implementation based on them (documentation or code). It is normal, that you are external to the project team. You probably do not even know them before and they don't know you. This is the main reason, you should make your approach and overall goals transparent to them before looking into the details. Point out, that you are not there to blaim anybody (you are not!). If you are working on internal projects you could also point out, that your work helps the team members getting better for the next projects. The sensitivities raise with the project size and your distance from the project team. Work professional; have your opinions in place; rest in yourself. And most important: never work alone! You always do any kind of review with a peer.
<br>
<br><b>Why doing the review?</b>
<br>
 There are a couple of reasons, you should do reviews on projects. The simplest case could be some kind of pre production check. For this you have to proof, that the software complies to the company's specifications. Mostly you also have to pass some kind of gateways in the used software development process where reviews are mandatory. You could try learning from failure and review failed projects. Or do checks on behalf of your customer. 
<br>
 In general you always have to check some implementation or documentation for compliance against standards or best practices. In general it is best to always follow these steps in your review process. It's called DRIVE and it is an approach to problem solving and analysis. I also like to use it for rewiews.
<br>
<br><b>Define:</b> the scope of the problem(s), the criteria to check.
<br><b>Review:</b> the current situation, understand the background, identify and collect information.
<br><b>Identify:</b> analyze the review results.
<br><b>Verify:</b> rate the review results. Together with a peer.
<br><b>Execute:</b> improvements or solutions from the results. Draw conclusions. 
<br>
<br><b>Compliance review</b>
<br>
 Checking for compliance is "easy". This is done against any kind of software development process or any other standard. If you are using for example OpenUP or TOGAF, you have checklists at hand (see ressources). They should guide you through all relevant parts. In other cases, you even have predefined gateway checklists you could work on. Anyway: Before starting, remember that it should always be an option to adjust them to you needs. You have a defined project order and you should focus on that. The bigger the checklist(s) grow, the more you have to evaluate.
<br>
<br><b>Metrics review</b>
<br>
 In some cases you could also think about using tools for your reviews. We are not talking about source code reviews here, so this is not obvious at the first sight. But there are tools out there, helping you to judge on the implementation of a special architectural approach. Think of <a href="http://clarkware.com/software/JDepend.html" target="_blank">JDepend</a> or the <a href="http://source.valtech.com/display/dpm/Dependometer" target="_blank">Dependometer</a>. Both perform static analysis of physical dependencies within a software system. Any kind of metric analysis should be already setup with the project. So you probably only have to check the generated metrics.
<br>
<br><b>Interviews</b>
<br>
 If you are doing interviews with the team, you should try to keep the sessions as short as possible. Whatever system/project you are looking at, try to find one interview partner per "tier" or "layer". Which view/methodology or concept you use here is unimportant. This approach helps you to schedule the interviews and assists you in a basic coverage of all relevant parts. 
<br>
 You should try to keep discussion to a minimum. Go with the checklists and work through your questions. Even if you are willing to share your knowledge. Try not to do this during the interview. There is room for it later on in your report.
<br>
 Don't forget to do a transcipt of the conversation.
<br>
<br><b>Result communication</b>
<br>
 This highly depends on the requirements. Could be any kind of presentation and/or document. Most important: stick to the truth. Refer to the place of discovery of the problems. Don't communicate a problem without a solution. If you discover one, also briefly think about the solution.
<br>
<br><i>Online resources:</i>
<br><a href="http://www.opengroup.org/architecture/togaf8-doc/arch/toc.html" target="_blank">TOGAF -- The Open Group Architecture Framework</a>
<br><a href="" target="_blank">OpenUP</a>
<br><a href="http://www.google.com/search?ie=UTF-8&amp;oe=UTF-8&amp;sourceid=navclient&amp;gfns=1&amp;q=Architecture+Review+Checklist" target="_blank">Google for Architecture+Review+Checklist</a>
<br><a href="http://www.businessballs.com/dtiresources/TQM_process_improvement_tools.pdf" target="_blank">Short abstract about DRIVE (PDF)</a>
---
title: "friendly reminder. do architecture documentation the right way."
date: 2010-05-18 06:40:00 +0000
layout: post
tags: ["documentation", "architecture"]
slug: "friendly-reminder-do-architecture"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2010/05/friendly-reminder-do-architecture.html
---

I had to work through a lot of documentation lately. If you have bigger systems this is anything but fun. Especially, if nothing more than some document templates exist for the team to put their knowledge in. Also very unhandy is documentation that is not up to date. If you want to make it better, you could take the following suggestions serious and put them in place for your project. I don't want to call them documentation principles, but they should guide you in writing clear and worth reading architectur documentation for your software projects. The points are in no particular order. I value them equal but had to start and end with anyone.
<br>
<br><b>Don’t repeat yourself</b>
<br>
 Even if it is alluring because there are still some customers out there, believing that only the total amount of documentation counts. It's not. Try to be DRY. If this does not work: Try harder! Documentation is there to have a single place for putting all information available.
<br>
<br><b>Avoid surprises</b>
<br>
 According to the principle of least astonishment (POLA), you should avoid solutions that leave the reader wondering. Try to make everything as clear as possible. Try to use different perspectives and views for different stakeholders.
<br>
<br><b>Use standardized structures</b>
<br>
 If you are not in the position using a standard software development process, invest the time to develop at last a standard set of documents you want your team to take care of. Take the time to brief your team according to the structure and what your expectations are.
<br>
<br><b>Work iterative</b>
<br>
 Compared to a great novel, software documentation is hardly ever finished. You will find the need for documenting open issues and first thoughts. Don't just put empty headlines in it for them. Create a seperate chapter and fill in as many information you may have according to your open issues. Don't create empty chapters at all. The simplest thing to have in a chapter is a note why this is (nearly) empty now and when you expect it to be filled.
<br>
<br><b>Five2Nine</b>
<br>
 According to the rules for cognitive psychology the short term memory of a person can hold five up to nine elements. If you draw charts (Especially for mngmt audience ;)) try to reduce yourself to not more elements per chart. Any higher number will not work. And don't forget about the legend. A chart without one is worth nothing.
<br>
<br><b>Draw and explain</b>
<br>
 If you make drawings and charts don't forget to explain them. If you don't explain your symbols or notation you will end up with a big misinterpretation. Even for a big project it could be sufficiant to have the basic perspectives (functional, contextual, distribution, development and runtime) as charts and explain them in more detail. A good start for this can be found on the CC-licensed <a href="" target="_blank">http://www.arc42.com/</a>. 
<br>
<br><b>Write for your readers</b>
<br>
 Before you begin writing take a moment, relax and thing about the audience for your document. If you find out, that you have more than one group, it could be worth thinking about a reading guideline in which you state which chapter is worth reading for whom. If you follow this, you will find it easier to nail down the points and don't find yourself explaining the basics over and over again.
<br>
<br><b>Be complete but don't write an epic</b>
<br>
 The hardest part. The bigger the project, the more documentation needs. Anyway. Try to find a way to strip and skip all unneeded parts. Think about splitting your whole documentation into several parts. But keep a root document where you put the basics. It does not make sense to have a single document with more than 200 pages.
<br>
<br><b>Document the "why"</b>
<br>
 Don't just simply put the result in you documents. Explain the reasons why you have chosen the solution. This will give your readers a chance to follow your thoughts and don't put you under pressure explaining even the weird constructs. 
<br>
<br><b>Document the "what"</b>
<br>
 Invest in a short chapter to explain, what kind of software you are going to support with your design. What are the basic functional requirements behind it? What is the business case? Why does the project exist. This is also a good place to link to from the "why"s and could explain why you prefer open source over closed source solutions for example.
<br>
<br>
 As always, this is ment as a general guideline. There are plenty examples out there where one or more of the mentioned points is/are violated. The most basic requirement I have concerning the documentation is that you make your decisions transparent and readable.
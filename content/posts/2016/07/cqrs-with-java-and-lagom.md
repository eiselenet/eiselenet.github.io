---
title: "CQRS with Java and Lagom"
date: 2016-07-15 12:36:00 +0000
layout: post
tags: ["CQRS", "java", "Lagom", "CJUG"]
slug: "cqrs-with-java-and-lagom"
link: "2016/07/cqrs-with-java-and-lagom.html"
url: /2016/07/cqrs-with-java-and-lagom.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="cjug.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="179" src="cjug.png" width="320"></a>
</div> I've had the pleasure to talk at the Chicago Java User Group and talk about how Lagom implements CQRS, the Command Query Responsibility Segregation pattern. Thankfully, there is a recording and I also <a href="http://www.slideshare.net/myfear/cqrs-and-event-sourcing-for-java-developers" target="_blank">published the slides on slideshare</a>.
<br>
<br><b>Abstract:</b>
<br>
 As soon as an application becomes even moderately complex, CQRS and an Event Sourced architecture start making a lot of sense. The talk is focused on: - the challenges and tactics of separating the write model from the query model in a complex domain - how commands naturally lead to events and to an event based system, and - how events get projected into useful, eventually consistent views. Event Sourcing is one of those things that you really need to push through at the beginning (much like TDD) and that - once understood and internalized, will change the way you architect a system. This talk introduces you to the basic concepts and problem spaces to solve.
<br>
<br>
 Thanks again for hosting me, CJUG! It was a real pleasure!
<br>
<br>
<div class="video-container">
 <iframe src="https://player.vimeo.com/video/172735253" width="640" height="360" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
 <p><a href="https://vimeo.com/172735253">CJUG - 2016-06-28 - Markus Eisele on CQRS and Event Sourcing for Java Developers</a> from <a href="https://vimeo.com/spantree">Spantree Technology Group, LLC</a> on <a href="https://vimeo.com">Vimeo</a>.</p>
</div>
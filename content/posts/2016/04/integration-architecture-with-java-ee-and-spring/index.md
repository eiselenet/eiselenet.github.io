---
title: "Integration Architecture with Java EE and Spring"
date: 2016-04-12 12:27:00 +0000
layout: post
tags: ["Java EE", "integration", "SACon", "Spring"]
slug: "integration-architecture-with-java-ee-and-spring"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2016/04/integration-architecture-with-java-ee-and-spring.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG_0589.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="240" src="IMG_0589.jpg" width="320"></a>
</div> The <a href="http://conferences.oreilly.com/software-architecture/engineering-business-us" target="_blank">O'Reilly Software Architecture Conference</a> in New York happens this week. And I had the pleasure to give a tutorial together with Josh Long about how to integrate Java EE and Spring. We've been joking about this one since a while. The super stupid biased view onto both technologies which some people have in mind was something that bugged both of us since a while. Another important reason for this talk was, that we both are caring about modernisation of old applications. There is so much legacy software out there that is easy 10+ years old. And you find those legacy applications in both technologies. This is, why we wanted to help people to understand how to modernise them and survive the transition phase.
<br>
<br><b>A little history about Spring and Java EE</b>
<br>
 The first part of the talk caught up on a little historical background of both technologies. Where they came from and how they evolved and lead into the state they are in today. Both evolved significantly since their inception and asking the question about what to chose today can easily be answered with a single sentence: "Chose the right tool for the right job". But you can even mix and match for many reasons.
<br>
<br><b>Spring on Java EE</b>
<br>
 There is a broad space of problems where you might think about using Spring on top of Java EE. While EE has been around and evolved a lot, we had to learn that you can't really innovate in a standard body. This lead to more than just a handful of features that are to be desired if you build a reasonable modern application. Some of those gaps include the security space (social logins), NoSQL integration, enterprise integration in general. And while you are free to pick from Java EE open or closed source offerings to close them, Spring most often has an answer in the family which makes it easy to use the same programming model and have an integrated offering. Plus, the Spring framework has a very long tail: Spring framework 4 runs on Servlet 2.5+ (2006!!), Java EE 6 (2009) and Java 6+. Which makes it very easy to use modern features even on the most outdated base platform. Find the <a href="https://github.com/myfear/javaee-spring-tutorial/tree/master/code/spring-on-ee" target="_blank">demo code in my github</a> repository and enjoy how easy it is to deploy a spring war to a Java EE server and just use the APIs.
<br>
<br><b>Java EE on Spring</b>
<br>
 But you can also turn this around and use Java EE APIs with Spring. The reasons you might want to do this are plenty: It can be a first migration step towards Spring while simply re-using some of your old code. Plus you want to use standards where standards make sense and where everything else would be to invasive. Examples include JTA, JPA, JSR303, JSR 330, JCA, JDBC, JMS, Servlets, etc.
<br>
 And there is also an <a href="https://github.com/myfear/javaee-spring-tutorial/tree/master/code/ee-on-spring" target="_blank">example app which you can run</a> as a Spring Boot based fat-jar while using (mostly) Java EE APIs in it.
<br>
<br><b>Technical Integration and Microservices</b>
<br>
 The last part of the presentation touched on technical integration between two systems and the technologies supported in both worlds. We also talked about microservices designs and answered a bunch of questions over the turn of the three hours.
<br>
 I really enjoyed it and have to admit that Josh is an amazing presenter and I learned a hell lot over the last couple of days working with him! It's a pleasure to know you, Josh! Make sure to follow him on Twitter&nbsp;<a href="">@starbuxman</a>.
<br>
<br>
<div class="video-container">
 <iframe allowfullscreen frameborder="0" height="485" marginheight="0" marginwidth="0" scrolling="no" src="//www.slideshare.net/slideshow/embed_code/key/J8eNigSHsMZofe" style="border-width: 1px; border: 1px solid #ccc; margin-bottom: 5px; max-width: 100%;" width="595"> </iframe>
</div>
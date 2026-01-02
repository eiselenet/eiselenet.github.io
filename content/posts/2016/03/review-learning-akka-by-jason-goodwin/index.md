---
title: "Review: \"Learning Akka\" by Jason Goodwin"
date: 2016-03-11 07:43:00 +0000
layout: post
tags: ["packtpub", "akka", "review", "book"]
slug: "review-learning-akka-by-jason-goodwin"

url: /2016/03/review-learning-akka-by-jason-goodwin.html
---

Haven't done a review in a while. It's time to dive a little deeper into the technical portfolio of Lightbend. Today it is Akka. A book with this title is the ideal start with a new technology in general. And for all my Java readers: Rest assured, that all examples in this book are in Java 8 (and in Scala).
<br>
 A big "Thank you!" to Packt Publishing who provided the book to me for review.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="http://bit.ly/1TH7Rk9" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;" target="_blank"><img border="0" height="320" src="learning_akka.jpg" width="259"></a>
</div><b>Abstract</b>
<br>
 Software today has to work with more data, more users, more cores, and more servers than ever. Akka is a distributed computing toolkit that enables developers to build correct concurrent and distributed applications using Java and Scala with ease, applications that scale across servers and respond to failure by self-healing. As well as simplifying development, Akka enables multiple concurrency development patterns with particular support and architecture derived from Erlang’s concept of actors (lightweight concurrent entities). Akka is written in Scala, which has become the programming language of choice for development on the Akka platform.
<br>
<br>
 Learning Akka aims to be a comprehensive walkthrough of Akka. This book will take you on a journey through all the concepts of Akka that you need in order to get started with concurrent and distributed applications and even build your own.
<br>
<br>
 Beginning with the concept of Actors, the book will take you through concurrency in Akka. Moving on to networked applications, this book will explain the common pitfalls in these difficult problem areas while teaching you how to use Akka to overcome these problems with ease.
<br>
<br>
 The book is an easy to follow example-based guide that will strengthen your basic knowledge of Akka and aid you in applying the same to real-world scenarios.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="http://bit.ly/1TH7Rk9" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;" target="_blank"><img border="0" height="100" src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEin_yK8mvEvRu7mGn81OUUm9a_EUK7zb2nGvcMhC6Qim-NvlDL7QCgxiG12ckkGtDeH08E-qrPL4nItF5KR2_mi9D4gO1uvvj9mOfvTjKRDjSpd96eVrTasMc7cPQpsiApnhyphenhyphenbCDuljwjhi/s200/Banner+%2528Akka%2529.jpg" width="100"></a>
</div> Book: "<a href="http://bit.ly/1TH7Rk9" target="_blank">Learning Akka</a>" 
<br>
 Language : English
<br>
 Paperback: 274 pages 
<br>
 Release Date : 30. Dezember 2015
<br>
 ISBN-10: 1784393002
<br>
 ISBN-13: 978-1784393007
<br>
<br><b>The Author</b>
<br>
 Jason Goodwin (GitHub: <a href="https://github.com/jasongoodwin" target="_blank">jasongoodwin</a>) is a developer who is primarily self-taught. His entrepreneurial spirit led him to study business at school, but he started programming when he was 15 and always had a high level of interest in technology. This interest led his career to take a few major changes away from the business side and back into software development. His journey has led him to working on high-scale distributed systems. He likes to create electronic music in his free time.
<br>
<br>
 He was first introduced to an Akka project at a Scala/Akka shop—mDialog—that built video ad insertion software for major publishers. The company was acquired by Google eventually. He has also been an influential technologist in introducing Akka to a major Canadian telco to help them serve their customers with more resilient and responsive software. He has experience of teaching Akka and functional and concurrent programming concepts to small teams there. He is currently working via Adecco at Google. 
<br>
<br><b>The Content</b>
<br>
 Take all the preface, index and praises away you end up with 216 pages of plain content. Divided into nine chapters.
<br><i>Chapter 1:</i> Starting Life as an Actor gives an introduction to the Akka Toolkit and Actor Model in general. It covers everything you need to know to get started including the setup of the development environment.
<br><i>Chapter 2:</i> Actors and Concurrency introduces you to the reactive design approach. The anatomy of, creation of, and communication with an actor together with the tools and knowledge necessary to deal with asynchronous responses and how to work with Futures—place-holders of results.
<br><i>Chapter 3:</i> Getting the Message Across helps you to understand the details of message delivery mechanisms in Akka. That includes different messaging patterns.
<br><i>Chapter 4:</i> Actor Lifecycle – Handling State and Failure introduces you to the actor's life cycle and explains what happens when an actor encounters an exceptional state and how you can change its state to modify its behaviour.
<br><i>Chapter 5:</i> Scaling Up guides you through how Akka can help us scale up more easily to make better use of our hardware, with very little effort.
<br><i>Chapter 6:</i> Successfully Scaling Out – Clustering comes in handy, when you reach the physical limits of a single machine. Learn what happens when you reach the limit of a physical host and need to process the work across multiple machines.
<br><i>Chapter 7:</i> Handling Mailbox Problems digs deeper into what happens when you start to hit the limits of your actor system and how to describe how your system should behave in those situations.
<br><i>Chapter 8:</i> Testing and Design examines some general approaches to design and testing in greater detail.
<br><i>Chapter 9:</i> A Journey's End highlights a few outstanding features and modules that you may want to be aware of, and some next steps.
<br>
<br><b>Writing and Style</b>
<br>
 The author thoughtfully explored all the content in every chapter and created a great resource for everybody who wants to start with the Akka toolkit. Sentences are a little longer from time to time and it is a technical book but absolutely readable also for non native speakers. 
<br>
 Every chapter includes links to further resources and a little homework for you to do. Testing and test-design is covered in a separate chapter but also present in code samples throughout the complete book. 
<br>
<br><b>Conclusion and recommendation</b>
<br>
 This book attempts to give both the introductory reader and the intermediate or advanced reader an understanding of basic distributed computing concepts as well as demonstrates how to use Akka to build fault-tolerant horizontally-scalable distributed applications that communicate over a network. With all the examples being present in both languages (Java 8 and Scala) it is the ideal entry for a Java developer to dive into Akka and get a first idea about the concepts. It does not simply copy the documentation and covers many of the important topics and approaches you should understand to successfully build applications with Akka. But be aware that this book only gets you up to speed quickly. To fully understand the toolkit you should follow the further reading advices at the end of each chapter. Don't forget to use the above codes to get 50% off the eBook or 25% off the printed edition. Because the recommendation is to buy it!
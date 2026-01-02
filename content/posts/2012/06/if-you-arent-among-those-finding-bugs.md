---
title: "If you aren't among those finding bugs you might be among those complaining about them later."
date: 2012-06-11 13:43:00 +0000
layout: post
tags: ["glassfish", "Arquillian", "javaee"]
slug: "if-you-arent-among-those-finding-bugs"
link: "2012/06/if-you-arent-among-those-finding-bugs.html"
url: /2012/06/if-you-arent-among-those-finding-bugs.html
---

Back from vacation and looking forward to the more productive summer times in general I stumbled over a post titled "<a href="http://blog.ringerc.id.au/2012/06/most-important-lesson-in-java-ee.html" target="_blank">The most important lesson in Java EE</a>" by Craig Ringer. I follow Craig on twitter (<a href="https://twitter.com/#!/craigdevel">@craigdevel</a>) and&nbsp;reading his tweetsI believe he is a creative and solution oriented person . But I have to jump in with this special post because it made me angry. And this is not because of his points. They are right. But it's because of his timing, the title and the overall recommendation he gives. But lets start at the beginning.
<br>
<br><b>You aren't talking about Java EE but generally about versions!</b>
<br>
 Mixing up things is a bad habit. And what he did in this post is exactly that. Yes, the technologies he mentioned have a Java EE focus or are reference implementations. But Arquillian and ShrinkWrap are good examples for independent products with no exclusive EE relation. So the main complaint he is having is, that you shouldn't use a x.0 version.
<br>
<br><b>This ain't news, everybody knows you don't buy version x.0 of anything</b>
<br>
 Many corporations do not purchase x.0 releases of software - preferring to wait until a .1 or .2 maintenance release has come along. And so should you if you are working on a production ready and stable release of your software. If you started with the first release of the Java EE RI you knew it would be a hurdle race. It ever has been like this. Not only with GlassFish but with any commercial container out there. So, complaining about a Glassfish 3.0 release is like hitting yourself in the face. And this is true for any other software release he mentioned in his post. So, Craig: If you are reading this ... by writing this post you confirmed you are making beginners mistakes ;)
<br>
<br><b>When to use x.0 versions?</b>
<br>
 I told you, that Craig is basically right. And I second his recommendation in general. But with a slightly adjusted focus. Don't skip x.0 versions if you are going to make your experiences or trying to improve your skills. Every single product on his post Glassfish, JBoss, Arquillian, Mojarra, RichFaces and many others simply can't improve without your feedback. Don't push your highly&nbsp;sophisticated app with millions of active users to the limits with a x.0 and aim for a production date shortly, but why not&nbsp;test drive&nbsp;it?&nbsp;&nbsp;I did this for GlassFish and even some others (closed source) and it helped the vendors sorting out stuff. They learn how you are using their products and maybe they add a&nbsp;test case&nbsp;for you to improve the upcoming releases.
<br>
 And even if you have a product which is developed in free and open and does provide you with a&nbsp;road map&nbsp;you could start your development with a x.0. If you plan you upgrade and keep track of needed changes and workarounds. I did this. It works. I believe many others experience the same. It might be more time-consuming than simply using a final/"bugfree" version of anything but .. yeah .. that's life... isn't it. But there also is a downside to this medal. If you are only using final/bugfree/approved versions you might end up staying with stuff that is &gt;10 years old. I don't know if I like to trade that in. I like to manage that problem like I do with any of the risks I have to face with software development.
<br>
<br><b>If you aren't among those finding bugs you might be among those complaining about them later.</b>
<br>
 It should be clear to anybody that improving OSS has different shades and you can work actively with many of the communities to make the product experience better. I have written up a few about&nbsp;<a href="http://blog.eisele.net/2012/04/5-areas-to-contribute-to-glassfish.html">5 Ways to Contribute to GlassFish without being a Programming Genius</a>&nbsp;and I believe it should be clear to anybody working with OSS that he didn't pay for anything and there always might be a good chance for a bug. Especially with early versions. 
<br>
 As I mentioned I am following Craig on twitter and had the chance to see at least a few&nbsp;of his ShrinkWrap and Arquillian problems which were discussed publicly being addressed by the product owners or&nbsp;volunteers from the different product communities. And they value what he does. Stopping other from doing the same or trying early versions will harm an entire community and not only Java EE.
<br>
 I don't know if he ever had the chance to work close with other vendors products. Especially the commercial ones. There are good reasons for them to be "Early Adopters" and not "Innovators" Especially with such a broad standard with many many reference implementations.
<br>
<br><b>To make a long post short</b>
<br>
 Don't listen to Craig here! Use whatever version number your favorite software, framework or more generally product has at the moment. Apply the precautions you always apply being a responsible developer, architect or project manager and if there isn't any other way: Contribute your findings. Help developing patches and manage this as part of your risks. If you are looking for a 100% error-less system you might consider buying one of those commercial products out there. But ... hey, let's tell you a secret: The only difference here is that their support-systems might look a little bit nicer. You would also have to provide a test case and work with them on the solution. .... decide on your own! I would go with GlassFish, Arquillian and ShrinkWrap and any of the others. Even with a x.0 release.
---
title: "Mobile Apps - Native? Flash? HTML 5? - a decision helper."
date: 2010-11-09 06:39:00 +0000
layout: post
tags: ["mobile", "html5", "flash"]
slug: "mobile-apps-native-flash-html-5"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2010/11/mobile-apps-native-flash-html-5.html
---

Playing around with mobile app development recently one question comes up over and over again: Should I write a native app, rely on the browser capabilities or even take the flash/air approach? As you might have guessed, this is not an easy answer. But there are things, you could keep in mind and make your own decision.
<br>
<br><b>What's out there?</b>
<br>
 To understand the problem behind this, we do need to have a look at the industry and standards out there.
<br>
 According to different sources (<a href="http://www.gartner.com/it/page.jsp?id=1452614">Gartner</a>, et all) there are roughly 270 million smartphone devices and 19 million tablet computers in the hands of users at the moment. With tablets being the fastest growing cosumer electronic category of all times. Especially the iPad became the most quickly adopted electronic device ever. According to <a href="">different sources</a>, the number of mobile devices shipped every year will grow at rates close to 20 percent. There are roughly six different vendors with offerings for smartphones or tablet devices which both count as mobiles in my definition. The most prominent ones are of course Apple, RIM and Nokia.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="vendors.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="218" src="vendors.png" width="320"></a>
</div>
<br><b>Why should I care?</b>
<br>
 Mobile computing is a trend in the enterprise. Forrester predicts, that 2/3 of the employees will require mobility support until 2012. For IDC it is clear that more than 35 percent of the workforce will be done using mobile technologies in 2013. All this are&nbsp;enormous numbers that do point into one direction. Mobile computing will hit your desk or even development environment sometimes in the near future. You better should be prepared for that.
<br>
<br><b>Which technologies are there?</b>
<br>
 Six major vendors are a valid size in the industry. But if we look at the used&nbsp;technologies&nbsp;you quickly notice, that they are all cooking their own soup. Android and BlackBerry rely on Java (Eclipse) with additional device APIs. iOS has it's own Objective-C based programming and tooling environment. Windows Phone 7&nbsp;relays&nbsp;on &nbsp;.NET and C#, Symbian uses Qt and C++. All (except one) OS are equipped with a modern browser based on an HTML 5 capable rendering engine. Windows Phone 7 however will probably still contain an older version of Microsoft's Internet Explorer and therefore will not be able to take advantage from the next generation&nbsp;web standard. All but one OS actually can or will be able to run Flash / AIR applications.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="technology.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="199" src="technology.png" width="320"></a>
</div><b><br></b>
<br><b>HTML 5 and Flash/AIR are the solution?</b>
<br>
 If you are looking at the table above and are searching for a "write once, run everywhere solution", you probably are looking for an HTML 5 or Flash/AIR app. BUT, both have their limitations. Using one of them does not give you access to the device specific APIs. So you will not be able to access contacts, phone, camera and many many more of the device specific features.&nbsp;If you need them and you need to support all the major OS in the mobile space, you probably end up writing different applications for different devices. If you are&nbsp;comfortable&nbsp;with the feature set you get from HTML 5 and Flash/AIR you probably have found your&nbsp;programming language of choice.
<br>
<br><b>Native apps are the solution?</b>
<br>
 You are going to develop&nbsp;native&nbsp;applications if you are trying to be most efficient in terms of resource&nbsp;usage. This is true for battery, RAM and CPU. If you are trying to get the best out of your resources there is no other way than writing native apps. I already mentioned the integration with built in device features. If you are willing to write an email, contacts or whatever integration there is no other way than to provide a native app making use of all the device/OS specific features.
<br>
<br><b>What else?</b>
<br>
 But to be honest, this are not the only decisions you have to make. There are many more things to look after if your are evaluating your approach to mobile development. Everything starts with technology but you also have to look at the distribution (e.g. Do you really want your app in iTunes?) at the development skills in your company, at the needed&nbsp;resources (Do all developers really need a mac ?) ....
<br>
<br>
 As always, there is no single answer to this simple question. But I still hope, the little basics give a brief idea about where to look to make your decision.
<br><b><br></b>
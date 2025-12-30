---
title: "Mobile GlassFish Admin GUI with HTML 5 and the iPad"
date: 2010-09-01 06:27:00 +0000
layout: post
tags: ["glassfish", "iPad", "html"]
slug: "2010-09-01-mobile-glassfish-admin-gui-with-html-5"
url: /2010/09/mobile-glassfish-admin-gui-with-html-5.html
---

You know, that I am "playing" around with the iPad recently. One part of the game is to look for suitable UI frameworks that assist you building modern "native looking" webapps for the iPad. There are <a href="">some promissing approaches</a> out there and the one that is most complete and best looking is <a href="">Sencha Touch</a>. Sencha Touch allows you to develop web apps that look and feel native on WebKit based browsers and touchscreen devices.
<br>
 I will probably blog about the more technical findings I had working with some of the mentioned frameworks but this post is dedicated to a concrete usecase for mobile UIs. If you have read my iPad for Business? posts (<a href="http://blog.eisele.net/2010/07/ipad-for-business-about-why-and-how.html">part 1</a> | <a href="http://blog.eisele.net/2010/08/ipad-for-business-about-how.html">part 2</a>) you already know, that the device is hot and nearly every company is designing their personal mobile usecase. But what about product UIs? 
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG00024-20100901-0738-10.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="320" src="IMG00024-20100901-0738-10.jpg" width="239"></a>
</div>Have you seen any of the mature Java application server admin UIs thinking about or doing prototyping on this? Neither did I. But why not? Is this not needed? 
<br>
 Here is how a mobile UI for the latest GlassFish could look like. For now this is only a simple UI prototype without any further functionality, designed to make you want it :) The native gesture driven approach for touch devices would optimize the usability a lot. But beside this basic benefit you could also think about other szenarios. The following represent what I might find usefull. 
<br>
<br><b>Admins in datacenters</b>
<br>
 I could image admins walking around in the datacenter and connecting their mobiles to the server instance they are standing in front of. This could save time for walking to the next terminal or even be good for the health because they do no longer need to carry their heavy notebooks around.
<br>
<br><b>Load test specialists</b>
<br>
 Load and stress tests typically run for longer times. Very often they have to run outside of the normal office hours. The mobile UI gives a very lightweight access to all relevant domain data that could be of interest to those people. 
<br>
<br><b>Developers</b>
<br>
 And last but not least for the developers. They could grap one of those fancy desktop stands and have their admin UI always on beside their screen. This could speed up development if you do not have to switch windows. 
<br>
<br>
<br>
 If you have comments, ideas, other cases, let me know. I would love to see a mobile UI version in one of the next GF or even the WLS ;)
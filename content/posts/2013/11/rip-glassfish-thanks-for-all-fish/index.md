---
title: "R.I.P. GlassFish - Thanks for all the fish."
date: 2013-11-05 05:59:00 +0000
layout: post
tags: ["glassfish", "oracle"]
slug: "rip-glassfish-thanks-for-all-fish"

url: /2013/11/rip-glassfish-thanks-for-all-fish.html
---

We've all heard it coming probably. Yesterday the official roadmap update for JavaEE and GlassFish was <a href="https://blogs.oracle.com/theaquarium/entry/java_ee_and_glassfish_server" target="_blank">updated and published</a>. And beginning with the title the whole post was basically about one thing: GlassFish Server as we know it today is deprecated from a full blown product to a toy product.
<br>
<br><b>The Long Road from Sun to Oracle</b>
<br>
 GlassFish was something to worry about right from the start. After the merger it took some time to silence the voices which insisted on "Oracle killing GlassFish". Oracle did a decent job in nurturing the community and keeping their stuff together. I've written a <a href="http://blog.eisele.net/2010/03/glassfish-product-roadmap-updates.html" target="_blank">couple of blogs</a> myself helping to get the word out. The 100-day releases 2.1.2 and 3.0.1 have somehow become the milestone to prove the will to improve. And we all have been fine with it after a while. Even back in January 2013 I <a href="http://blog.eisele.net/2013/01/selecting-your-java-ee-6-application.html" target="_blank">compiled a list of open source application servers</a> and which one to pick. The final criteria was vendor support. That kicked WAS CE out of the game. As of yesterday it would also removed GlassFish. The two remaining alternatives burned down to one: Which is JBoss AS7 / WildFly.
<br>
<br><b>Customers Need Support for their Servers</b>
<br>
 But come on, what is the issue here? Who wants support anyway? And Oracle obviously did not make enough money out of the commercial licenses otherwise they wouldn't have killed the offering at all. It might not be a very obvious reason but I can offer some kind of explanation. First of all if a vendor is not only developing an open source alternative but also has a commercial offering this leads to different things that will be taken care of implicitly:
<br>
 - Changes/Bugs discovered by customers go into the oss release
<br>
 - Changes need to have a decent quality. Developers knowing about the need to support their solutions will be (at least a bit) more careful implementing stuff.
<br>
 - Developers knowing that their stuff is run under decent load think differently implementing it. The complete list of non-functional criteria changes with that move.
<br>
 - Customers demand more frequent releases and security patches which also end up in the oss version.
<br>
 - Customers have different requirements than people using free and open source servers. One prominent example is clustering. Which is rarely used among oss projects.
<br>
<br>
 Another factor is driven by experience. I would never try to develop project on a completely different setting than the production setting is at. Even that both WLS and GF understand at least a bit of each others deployment descriptors there is a high risk buried in here that such a setting is the road to trouble.
<br>
<br>
 The bottom line of my argumentation basically is, that the need for providing a commercial distribution improves the overall quality and reliability by changing some relevant non functional requirements for the product. If those aren't there and nobody is consequently taking care of them ... they will not be in there.
<br>
<br><b>Why will Java EE suffer from a dead GlassFish?</b>
<br>
 The quality of the Java EE TCK has been questioned a lot. And in the past many people used GF as a showcase for not working code. On top of that some production scenarios and errors lead to different implementations and last but not least specifications. All the practical field knowledge has been in the heads of the team. I don't know how Oracle is running WLS development internally but I expect it do be different from what the team did for GF, probably a bit heavier. Extracting specification edge-cases and removing product specific parts from WLS based customer cases will for sure be trickier and not happen very frequently. So I expect the spec to be a little less Oracle driven and a little less mature generally. Not the worst part in the story. But given the fact that some very bright minds are working in that area I expect that their passion and knowledge will be missed a lot. And there is nobody there to catch them falling.
<br>
<br><b>Which Parts of GlassFish will die?</b>
<br>
 So GlassFish will stay the reference implementation for upcoming Java EE standards. Oracle needs it to be around just for that one reason. With the emerging JCP which is becoming more and more open it isn't a big surprise that they are not simply going to define WLS as the RI. But that will be the cut between things that will die and things that will be around. I DON'T have any insights here and I'm just speculating and I could make an educated guess about the first comment on this blog but .. bottom line for me is, that everything that isn't covered by the Java EE spec is going to age very fast. This could include clustering and for sure some of the admin features and security also is a good candidate (PAM realm and others). And frankly I can't confirm any of them. It is pure speculation!
<br>
<br><b>Is there a good part in that at all?</b>
<br>
 Well, yes: The move leaves a field wide open for strengthened competition. And this is not only WildFly but for sure also TomEE with tomitribe. Congratulations to them. Further on many customers will save a lot of license fees. GF and WLS are differently licensed and using WLS standard gives customers more options on picking the right license. And at least the WLS team will be strengthened with those people don't having to switch heads anymore working frequently on different products.
<br>
<br><b>Can Oracle do something to make the killing worth it?</b>
<br>
 As of today it is a&nbsp;senseless death. Users can simply sit back and wait for the next minor release which probably will happen once a year. If you've been complaining about infrequent releases until today .. prepare for even less in the future. There are indeed a couple of things Oracle could do to make this a strategic move for everybody and not only themselves:
<br>
 1) Develop and support a clear upgrade path. Find a way to at least support a development setting based on a very lightweight server and only deploy to a full blown WLS in production. With the given features and differences between the two this is hardly a working story as of today.
<br>
 2) Make an attractive licensing offering for GF users. Not only to the customers as of today but for all. Or even better: Come up with a bunch of licensing terms in the OTN license which allows NPOs to use WLS free of charge.
<br>
 3) Consequently open-source GF (under a decent license) and make community contributions attractive. The used infrastructure and OCA makes this impossible as of today. Move the server code (including modules) to GitHub and appoint a change manager who reviews and pulls in proposed fixes and changes. Let the community decide on releases.
<br>
<br><b>The Echoes Are Gone In The Hall</b>
<br>
 Basically the news isn't a big surprise. We all understand the move. Having two servers instead of one is a double burden. With the BEA merger Oracle killed their own application server. Now it was GlassFish's turn. Oracle already tried to reduce the needed efforts to maintain it by merging the teams and also discussed different options along merging WLS to HK2 or extending the use of the same components for both servers. Some things happened and pushed the time for yesterdays announcement out by a couple of months but finally did not prevent it. So. R.I.P. GlassFish. It was nice. Thanks for all the fish.
<br>
<br>
<br>
<br>
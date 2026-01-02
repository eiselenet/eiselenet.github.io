---
title: "WildFly 8 vs. GlassFish 4 - Which Application Server to Choose."
date: 2014-01-28 09:53:00 +0000
layout: post
tags: ["glassfish", "wildfly", "javaee7"]
slug: "wildfly-8-vs-glassfish-4-which"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2014/01/wildfly-8-vs-glassfish-4-which.html
---

It's been a while since my last blog. I've obviously been busy with different things including my main job. After some more questions regarding the right choice for application servers cross my way it is the right time to pick this topic up again and share my thoughts.
<br>
<br>
 One of the most read post on this blog is the post about which <a href="http://blog.eisele.net/2013/01/selecting-your-java-ee-6-application.html">Java EE 6 application server</a> to choose. I've been looking at a bunch of criteria and knocked down the different certified servers according to a very basic but common pattern. Given the main guideline that each server should be available as OSS and commercially supported variant the post concludes with recommending both GlassFish 3 and JBoss AS7 as valid choices. After the <a href="http://blog.eisele.net/2013/11/rip-glassfish-thanks-for-all-fish.html">GlassFish roadmap updates</a> from last November the situation seems to have changed and many people tend to accept that AS7/WildFly now remain the only alternative. Today I'd like to shift the focus on this a bit and try to put the discussion back into a more strategical context and elaborate further on the impact on the GlassFish vs. WildFly decision as of today.
<br>
<br><b>Basic Strategy Principle for Java EE Applications</b>
<br>
 Beginning with having made the decision to develop a new application based on Java EE you already assumed a couple of things. Java EE is called an industry standard for a reason. It means it is widely adopted and still not officially captured by one of the official standards or standardization organisations like DIN/ISO or IEEE. The JCP provides rules and regulations for it and governs it along a broad contribution from different individuals and organizations. Calling it an open industry standard is common and valid for me. You may weight the difference between both on your own. In principle the <a href="http://www.oracle.com/technetwork/java/javaee/overview/compatibility-jsp-136984.html" target="_blank">Java EE certification</a> list provides you with a range of different products which at least comply to the so called <a href="https://jcp.org/aboutJava/communityprocess/final/jsr342/index.html" target="_blank">Java EE TCK</a>. The TCK is heavily discussed and it is safe to assume that it does not cover every single line of all contained specifications completely. But every certified Java EE server should basically be ready to execute a Java EE application. The write once - run everywhere principle can be achieved (at least to a certain extend).
<br>
 Bottom line of your decision is: Avoid (vendor) specific features and build your new application on an open industry standard which provides flexibility and choice between different products and vendors.
<br>
 Beside this you gain additional value by having the flexibility to choose from a broad range of companies and developers offering skills and services in Java EE technologies.
<br>
<br><b>WildFly 8 and GlassFish 4 are equal from a Java EE 7 Perspective</b>
<br>
 With the announcement of <a href="" target="_blank">WildFly 8 CR1</a> it passes the Java EE 7 TCK. Even if the official paperwork obviously haven't been completely processed it looks like the 8 final will officially be certified. At least with regards to the Java EE 7 technologies both servers offer the same. There is and always has been different additional features surrounding the core technology stack but I haven't done a complete feature comparison of them and I honestly have no intend of doing it.
<br>
 If you plan on doing a greenfield development come up with your own decision making process and weight those additional metrics in. You're already and Oracle or Red Hat customer? Or using additional infrastructural components which work best with the one or the other? In my experience you also need to weight in a couple of others (from my own experience we are talking about &gt;=30) and rank them accordingly.
<br>
<br><b>Migrate from GlassFish 2.x, 3.x to 4.0?</b>
<br>
 The most commonly asked question these days. What should I do with the applications which are already running on GlassFish 2.x or 3.x? It probably is the hardest one. I would need to know a bit more from you to answer it.
<br>
<br><i>Oracle/GlassFish Customer/Shop and not changing anything?</i>
<br>
 Are you already using the Oracle GlassFish Server (commercially supported version) or are you using the Open Source version? Do you plan on extending the application or use newly introduced Java EE 7 features? If you are hooked up with Oracle or the commercial version already and you are NOT planning on making any changes you basically don't have to worry about migrating at all.&nbsp;Existing Oracle GlassFish Server 2.1.x and 3.1.x commercial customers will continue to be supported according to the <a href="http://www.oracle.com/us/support/library/lifetime-support-middleware-069163.pdf" target="_blank">Oracle Lifetime Support Policy</a>&nbsp;(PDF). I basically don't recommend to migrate at all if you're in that kind of setting. The extended support for both servers ends in January 2017 (GFv2) respectively March 2019 (GFv3). 
<br>
<br><i>Oracle/GlassFish Customer/Shop and willing to use new Java EE 7 features early?</i>
<br>
 So you are an Oracle Customer and you are keen to use latest technology early? Or you need to heavily modify your applications?
<br>
 You basically have three options: Stick with the GlassFish 4 OSS Version (without support contract) or move to the WebLogic 12c (12.1.4) which will <a href="" target="_blank">most likely have full EE 7</a> support or do this step by step by first moving to GF 4 and then to WebLogic 12.1.4 later.
<br>
 Directly switching to GlassFish and planning to go on with WebLogic in production later carries the risk of using different application servers in development and production. You need to value this in and handle it accordingly.
<br>
 To completely reduce the risk I would recommend to wait for at least the WebLogic 12.1.3 which is expected to have a first set of new Java EE 7 specifications and will hopefully available sometime during the first half of CY2014.
<br>
 If you don't run a mission critical application and you don't need a support contract I recommend to migrate to GlassFish 4.0 in order to facilitate the already available infrastructure and skills and contracts. To me there is no point in hastily switching vendors. Be prepared for ending support contracts and plan on evaluating your decisions about the right Open Source Application Server then.
<br>
<br><i>Not really and Oracle Customer/Shop, not changing anything no interest in new EE 7 features?</i>
<br>
 Don't migrate at all until your requirements change. You might start evaluating your next Java EE 7 server product soon. But as of today there aren't many certified alternatives available.
<br>
<br><i>Not really and Oracle Customer/Shop, changing requirements, will to use new EE 7 features?</i>
<br>
 Might be time to revisit your IT landscape this year. It seems as if you've decided to go with GlassFish at some point. You might have to revisit your former decision and evaluate what to do. To make a well grounded decision about your next Java EE server you are too early. The certification matrix for EE 7 servers is mostly empty. Wait for more alternatives to come up. I expect this to take most of CY2014.
<br>
 If you have the need for new EE 7 features as of today and you need to be able to buy commercial support in the future but don't need it right here and now the only alternative you have is WildFly 8.
<br>
<br><b>What does the future hold for GlassFish 4?</b>
<br>
 I wish I could tell you. I guess I made my points in the <a href="http://blog.eisele.net/2013/11/rip-glassfish-thanks-for-all-fish.html" target="_blank">earlier posting</a>. Oracle needs GlassFish to stick around as Java EE Reference Implementation and given the number of commonly used components in both WebLogic and GlassFish it will always be there. And it is safe to assume that the Java EE specifications will always be the latest and greatest in GlassFish. But the Java EE ecosystem lead to a bunch of vendor specific extensions and features which are not really covered by any specification. Those are commodity &nbsp;to all of us (mostly clustering, admin Features, embedded server) and we don't want to miss them in many cases. Further on the patch frequency and grade of community participation will be crucial factors for the successful spread of GlassFish among projects and developers.
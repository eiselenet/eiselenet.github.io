---
title: "GlassFish Product Roadmap Updates - Versions 2.1.2, 3.0.1, 3.1, 3.2, 4.0"
date: 2010-03-25 19:31:00 +0000
layout: post
tags: ["glassfish", "roadmap", "oracle", "updates"]
slug: "2010-03-25-glassfish-product-roadmap-updates"
url: /2010/03/glassfish-product-roadmap-updates.html
---

As announced a few days ago, EclipseCon delivers details about the future directions of GlassFish. After the first details came up yesterday, there was a more detailed session today. As always, this is only second hand information, as I am not affiliated to Oracle. If you are looking for authoritative answers there are other places to look at (see links and readings below). You will find the following information there too in the next few days ;)
<br>
<br><b>Changes to GlassFish in general</b>
<br>
 First: It's easy to talk about the changes, because most things will not change. GlassFish will stay Open Source (mostly GPL/CDDL). It will have non-Oracle commiters and a (more) transparent development process in general. And you will be able to download the GlassFish Open Source Binaries from the places you are used to.
<br>
 The available AddOns will remain closed source. And this is the future way of delivering any integration works for other Oracle products. In short: You will not have to care of any Oracle specific middleware or features if your are staying with the Open Source distribution.
<br>
 The glassfish.org environment will stay the same. You will have all the mailinglists, forums and wikis in place, as you are used to. Same for the source and binary distributions.
<br>
 There will be an additional oracle.com site about Oracle GlassFish where your will find additional information about the commercial offerings. This includes the formal documentation, support and the licensed versions you can use for production.
<br><b>UPDATE (3/28/10)</b>
<br>
 The Oracle GlassFish (commercial distribution) licenses WILL change. It will be available under the <a href="http://www.oracle.com/technology/software/htdocs/devlic.html" target="_blank">Oracle Technology Network Developer License</a> and under a productive deployment license. Pricing will change, too. As usual for Oracle this will be a per processor pricing. Details will become available during the next weeks. If you are unshure what to do, contact your sales rep. 
<br>
<br><b>Naming changes</b>
<br>
 You have to adjust the names you were used to. The two basic offerings will be called: 
<br>
 - Oracle GlassFish Server (this is the commercial distribution)
<br>
 - GlassFish Server Open Source Edition (the OSS licensed version)
<br>
 No v2 or v3 anymore. Just the numbers. 
<br>
<br><b>Upcomming Releases</b>
<br>
 We are going to see two kind of releases in the next time. First are the so called <b>100day Releases</b>. They will basically cover the products as they are and put a new branding in place. I also expect the supported environments to extend to at last the popular ones for Oracle. There will be a 100day release for GlassFish 2.x and 3.x. The 2.1.2 will contain patches and the new branding, and will be released this summer. The 3.0.1 also contains the new branding, patches but will also deliver a multi-language release and provides basic interoperability features with WebLogic Server. It will be available this year summer, too.
<br>
<br>
 Those two will be followed by the normal feature releases; at last for GlassFish 3.x. All 2.x will go to maintainance mode after the 100day release.
<br>
 Actually the following represents the basic timeline that is know up to now:
<br>
<br><b>GlassFish 3.1 (2010 H2)</b>
<br>
 - Centralized Administration / Clustering
<br>
 - High Availability / State Replication
<br>
 - More value added features (e.g. Coherence support)
<br>
<br><b>GlassFish 3.2 (2011 H1)</b>
<br>
 - Improved Cluster / High Availability administration
<br>
 - Integration with Oracle Fusion Middleware
<br>
 - Virtualization Support
<br>
 - Some Java EE 6 spec updates
<br>
 - Some Java EE 7 EA
<br>
<br><b>GlassFish 4 (2012 H2)</b>
<br>
 - Common Server Plattform - sharing best of breed components with WebLogic Server
<br>
 - Java EE 7 RI
<br>
<br><b>Comments</b>
<br>
 After the first rumors around this is not a big surprise at all. GlassFish will stay the reference implementation for Java EE and will survive. But much more beyond this we will see both Oracle Java EE Servers growing together. There are no further details around about the plans for GlassFish 4.x and up to now this is only speculation again. But it seems to me that "sharing components" between both could mean more than simply running the same codebase for selected features. I believe this could lead to a general server platform which allows for only a few components/services to be different from each other. I am very excited to see both systems moving into a common direction.
<br>
 The provided roadmap only applies to the GlassFish server. There are a couple of things around it. One to mention is the GlassFish Tools Bundle For Eclipse. Nothing special has been announced for it so far. But Oracle also has the Oracle Enterprise Pack for Eclipse (OEPE) in place. The situation seems quite similar to the two servers. But I expect a near-term solution here. 
<br>
<br><b><br>
  Further Links and Readings</b>
<br><a href="">https://glassfish.dev.java.net/roadmap/</a>
<br><a href="">http://www.oracle.com/technology/products/glassfish/</a>
<br>
<br><b>UPDATE 3/25/10</b>
<br>
 The first link refers to the PDF slides of the presentation done by the GlassFish gang yesterday evening at EclipseCon.
---
title: "OEPE - tech preview - 11gR1 (11.1.1.5) for Eclipse Helios (3.6). And the future with GlassFIsh."
date: 2010-07-12 09:31:00 +0000
layout: post
tags: ["glassfish 3.1", "helios", "OEPE"]
slug: "oepe-tech-preview-11gr1-11115-for"

url: /2010/07/oepe-tech-preview-11gr1-11115-for.html
---

Looking around the web is good for surprise findings from time to time. My latest discovery is the <a href="http://marketplace.eclipse.org/content/oracle-enterprise-pack-eclipse-tech-preview" target="_blank">OEPE 11gR1 (11.1.1.5) tech preview</a> update for Eclipse Helios (3.6).
<br>
 The <a href="http://marketplace.eclipse.org/content/oracle-enterprise-pack-eclipse" target="_blank">latest OEPE Release</a> (11.1.1.5.0201003170852) still runs on Eclipse Galileo (3.5.2). 
<br>
<br>
 If you want to testdrive the latest tech preview, you obviously need Eclipse Helios first. Before going any further: Be aware that Eclipse 3.6 Helios is final but the tech preview is based on the M6 milestone release of Eclipse 3.6.
<br>
 If you are planning to play with it anyway: <a href="" target="_blank">Go, get it!</a>. 
<br>
 Unpack it to a suitable location and start it. Next is to add the tech preview update site: http://download.oracle.com/otn_software/oepe/helios. A detailed description is provided on the <a href="http://marketplace.eclipse.org/updatesite/help?url=http://download.oracle.com/otn_software/oepe/helios">Eclipse marketplace</a>.
<br>
 If all this does not work, you can get a preconfigured version from <a href="http://www.oracle.com/technology/software/products/oepe/oepe_11115.html" target="_blank">otn</a>. It is a 222MB download and takes some time.
<br>
<br>
 What do you get with this preview? Quite simple answer. Everything you have with the GA. Plus one thing: It runs on new Helios (M6) release. No new features and nothing else surprising. 
<br>
 I was not able to get this running with the latest GA release. Therefore, save yourself some time: Don't try this. It's not worth it up to now. But if you are planning to be up to date with your Eclipse versions, you can take a first sneak at OEPE running in it. 
<br>
<br><b>Features of upcomming releases?</b>
<br>
 You know: I don't know anything. And you know Oracle. They will not tell anybody anything until it is ready. Therefore please keep in mind, that the following is just speculation. As always, I am happy to hear your ideas about this.
<br>
<br>
 If you look around a bit, you find some more information about some (planned) features and the (first impressions of a) timeline:
<br>
 Inside the GlassFish project, you find the famous "OnePager"s. One among others is the <a href="http://wikis.sun.com/display/GlassFish/3.1DevToolingWTP1Pager" target="_blank">GlassFish Server Open Source Edition 3.1 - Eclipse Integration One Pager</a>. It outlines the general approach for OEPE towards it's GlassFish integration. The OEPE GlassFish integration is based on the <a href="" target="_blank">GlassFish plugins</a> for the Eclipse Web Tools Platform (WTP). Therefore this is the right place to look. The basic outline is:
<br>
<blockquote>
 All the exposed Java EE 6 features of the Eclipse IDE should work with minimal configuration with GlassFish 3 and 3.1 targets.
 <br>
  (Source: <a href="http://wikis.sun.com/display/GlassFish/3.1DevToolingWTP1Pager" target="_blank">Eclipse Integration 1P</a>.) 
 <br>
</blockquote>
<br>
 To speculate a bit, this means that OEPE will get full support for GlassFish 3.0 and 3.1. Beside the already existing support for WebLogic versions 8.1 through 10.3.3 (11gR1 PS2). This could also mean, that AppXRay &amp; AppXaminer will run on GlassFish projects, too. And of course, that OEPE will be able to handle all GlassFish deployment descriptors. GlassFish will be the target of all the JAX-WS wizards that are currently very WebLogic centric. In general it will not make a difference if you are using GlassFish or WebLogic as the active target server. 
<br>
<br><b>My personal feature wishlist</b>
<br>
 I compiled the following as my personal wish list for next versions of OEPE. I would love to read, if you have some additional thoughts and ideas!
<br>
<br>
<ul>
 <li>GlassFish and WebLogic should play together within OEPE to enable lightweight development with GF and productive staging to WLS instances. This should also be supported with a set of related maven plugins.</li>
 <li>Seeing both servers having some kind of scripting, I would love to have it aligned, so that I can write one script and run it on both servers. This should be supported by the IDE in any possible way.</li>
 <li>The already provided JRockit Mission Control Suite should be part of standard OEPE and be available with plugins for both servers.</li>
 <li>A lightweight administration console for development proposes should be available for both servers. I don't want to fire up the console to change settings if I am working in my IDE.</li>
 <li>I need Virtual EAR and FastSwap technology for GlassFish server instances, too.</li>
</ul>
<br><b>Planned (?) Timeline</b>
<br>
 Again, this is what I tried making sense of the available information. Nothing commited up to now.
<br>
<ul>
 <li><b>Eclipse 3.6: June 2010:</b> Support for GlassFish 3.0.1 and nightly builds of GlassFish 3.1</li>
 <li>OEPE release : July 2010.</li>
 <li>OEPE Update : September 2010: mainly bug fixes on Eclipse 3.6.</li>
 <li><b>Eclipse 3.6 Update: December 2010:</b> possible date where more Java EE 6 wizards can be included in Eclipse itself. This is is update release that is targeted for support of the official GlassFish 3.1 release</li>
 <li><b>Eclipse 3.7: June 2011:</b> Goal is to cover 100% of the Java EE 6 wizards and GlassFish 3.1 update release.</li>
</ul>If you now take a look at the <a href="http://wikis.sun.com/display/GlassFish/GlassFishV3Schedule" target="_blank">planned schedule for GlassFish</a>, you can draw some conclusions.
<br>
<br>
 GF 3.1 M3 will be around 7/19/2010. Expect the new OEPE GA to be there, too. The September release will be there during or before JavaOne and it could be aligned with the GF M5 release on 09/13/2010. The complete Java EE 6 compatible OEPE will probably be available together with the GF 3.1 final around 12/13/2010. The complete Java EE 6 wizzard support for (JAX-WS, CDI, Bean Validation, etc) will be available at the end of Q2/2011.
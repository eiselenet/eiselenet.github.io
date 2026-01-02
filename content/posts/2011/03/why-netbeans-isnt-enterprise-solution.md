---
title: "Why NetBeans isn't an Enterprise Solution - for me."
date: 2011-03-31 08:07:00 +0000
layout: post
tags: ["development", "netbeans", "enterprise"]
slug: "why-netbeans-isnt-enterprise-solution"
link: "2011/03/why-netbeans-isnt-enterprise-solution.html"
url: /2011/03/why-netbeans-isnt-enterprise-solution.html
---

We are all looking forward to the new NetBeans 7.0 release which should come out in a few weeks. And the more excitement grows the more people start asking questions about if it's suitable for enterprise development or not. 
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="NB_box.jpg" imageanchor="1" style="clear:right; float:right; margin-left:1em; margin-bottom:1em"><img border="0" height="200" width="169" src="NB_box.jpg"></a>
</div>According to the <a href="">Java EE Productivity Report (2011)</a> NetBeans has an overall usage that is about 12%. By far the most users (65%) rely on Eclipse. But why is this so? NetBeans is fancy as hell and even in my personal past I was a happy NetBeans user for a long time. In this post I try to summarize my own experiences and feelings about using NetBeans heavily. Please note, that this is not an objective comparison. I am happy to read about your thoughts! 
<br>
<br><b>Enterprise Requirements</b>
<br>
 Development of enterprise class software has some special needs. They all arise from the fact that we are mostly talking about bigger development projects with a whole team of developers which have to complete their job within a more or less short time frame. This leads to a couple of things a truly enterprise IDE can assist you.
<br>
<br><b>Rollout into large teams</b>
<br>
 First requirement category is derived from the needs to rollout the IDE to a larger team quickly. This includes the initial installation and configuration and a following distribution to the team. The individual member should not feel the need for anything else than to unzip the project distribution to a suitable location and start coding. This is very easily achieved with Eclipse. You simply download and unzip the recent build and configure the needed plugins before zipping everything up again and distribute it to your team. There are even some very decent central packaging and distribution solutions available which make it very easy to standardize on enterprise level (compare <a href="">Yoxos </a>from Innopract). If you are more interested in any pre-packaged solutions you could even thing about using <a href="http://www.myeclipseide.com/module-htmlpages-display-pid-6.html" target="-blank">MyEclipse</a> or comparable offerings. 
<br>
 NetBeans doesn't have this kind of valuable ecosystem. The most complete way of installing NetBeans is offered by the OS dependent installers. The OS-independent set is feature limited (but only 23MB smaller than the installer for windows). I don't know about any management solutions comparable to Yoxos or even other bundles. So you simply have to take what is offered in terms of technology packages.
<br>
 In addition it's most important to have a separation of configuration. You need to be able to differentiate IDE and project specific settings. The "workspace" approach used by Eclipse is a very convenient way for that. You simply have to put the complete workspace under version control and every developer has a consistent development environment to start over with.
<br>
 Project and IDE settings with NetBeans are very distributed between the project and the installation folder. If you are willing to keep a consistent project setting for a whole team you are probably forced to send updates around via email.
<br>
 Don't get me wrong. <a href="http://blog.eisele.net/2010/12/netbeans-in-large-teams-rollout-and.html">You can actually rollout NetBeans to larger teams</a>. But if you try to do this with a complete environment (Server, Build-Tools, etc) you quickly run into trouble if anybody is not using the same installation folder or want's to change some locations afterwards. This is not necessarily a problem with NetBeans itself but possibly a trade off in favor of the tight integration with the supported servers and build-tools. 
<br>
<br><b>Fast development</b>
<br>
 Beside this, key to every IDE is it's ability to let developers code fast. There are a couple of places you experience this. The most obvious ones are the available templates and shortcuts. Both IDEs are strong here. You could even partly configure the NetBeans shortcuts to work like Eclipse shortcuts. But what's very different is the code completion. You don't realize this if your are only doing small examples. If you ever tried to use NetBeans with a large and probably not too well designed project with too big classes and many files you quickly find yourself having to pause up to 2 sec. after typing a single character. <a href="http://netbeans.org/bugzilla/show_bug.cgi?id=179727">Code-completion is slow</a>. Another issue I have with NetBeans are the project scans happening. They take too long and appear too often. The larger your projects grow, the more problems they make. 
<br>
 Next big point is the server integration. That is nicely done with NetBeans. Especially the GlassFish and Tomcat integration. Smoothly. And hot-deployment works .... as long as you stick to the generated ANT scripts. Switch over to maven and your out. Same is true for Eclipse for sure. But then non of the IDEs really makes a difference here. But there are solutions upcoming. The Oracle Enterprise Pack for Eclipse already can take full advantage of the Fast Swap feature for WebLogic deployments. I bet, this is going to swap over to GlassFish soon. 
<br>
 Let's look at the maven integration. It's natively build into NetBeans. And it simply executes maven on behalf of the IDE. You have a nice repository view and some ways to create and modify mavenized projects. You can add dependencies and have a couple of ways to run the goals with the profiles you need. What about a simple and helpful editor for pom.xml files? what about visualizing dependency hierarchies? There are a couple of other things that m2eclipse does far better for me personally than the NetBeans integration. 
<br>
<br><b>Quality insurance</b>
<br>
 One of the most important things in enterprise projects are the available tools for quality checks and insurance. FindBugs, Checkstyle, you name them. For any of the tools you can find an .... Eclipse plugin :). Some have NetBeans support but most not done by the tool developers itself but by third parties. This leads to outdated integrations or force you to use complete packages (like PMD) even if you only need parts out of it. Subjective it feels like the PMD integration with NetBeans is many times slower than any of the Eclipse plugins I know. 
<br>
 If you look at Code-Coverage: A <a href="http://wiki.netbeans.org/TutorialCodeCoveragePlugin">cobertura plugin</a> was available in prior NetBeans versions. It does not seem to be there with 7.0. The only way to have any information about code-coverage is to use the cobertura plugin. What about Emma? Nothing. And there are some more examples. TestNG integration for example. Because of tight integration of JUnit testing framework in NetBeans most IDE-wide tests related actions do not work for TestNG tests. Even if there is a plugin you can install manually (don't expect this to be an official plugin). 
<br>
<br><b>Extensibility</b>
<br>
 Let's look at team collaboration solutions. Codebeamer? Rational Team Concert? Any other commercial solution? NetBeans supports Bugzilla and JIRA. You probably will find Eclipse integrations. In general the plugin experience with NetBeans simply is worse. If you use the pre-configured three Update-Centers you get a total of 30 Plugin proposals. If you look at the <a href="http://netbeans.org/downloads/index.html">plugin central</a> you can find additional 600. Many of them seem to be outdated (e.g. JAD integration). The presentation is worse. Hardly any documentation. Some broken links. No images. A website I am not willing to use. In general NetBeans only has 1/3 of the number of Plugins which are available for Eclipse via the <a href="">Eclipse Marketplace</a> (which has 1,064 "solutions"). 
<br>
<br><b>Stability</b>
<br>
 This is quite a bit unfair because this mostly relates to latest NB 7.0 Betas and RC. But I have the subjective feeling, that NetBeans is unstable. I've had lot's of exceptions and unresponsivenesses during development that I was not able to track down. If you take a Java EE 6 development project you quickly start over NetBeans a couple of times during the day because of that. 
<br>
<br><b>Bottom line</b>
<br>
 Thanks NetBeans. It was a great time playing with you. And I guess, we could get used to each other again for doing teaching, showcases, wizard driven developments. But if I have to do an enterprise level project with many developers and a tight timeline I will chose Eclipse.
<br>
 What are your experiences? And please don't talk about your experiences with your 20 classes projects.
---
title: "J2EE to JEE5 or JEE6 Migration"
date: 2009-08-18 07:20:00 +0000
layout: post
tags: ["migration", "j2ee", "Java EE 5", "Java EE 6"]
slug: "j2ee-to-jee5-or-jee6-migration"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2009/08/j2ee-to-jee5-or-jee6-migration.html
---

More and more mature projects think about migrating these days. After a couple
<br>
 of years of successfull development it is time to decide weather to 
<br>
 switch to updated enterprise java versions or to keep the old ones.
<br>
 Looking out for the JEE6 release, which is getting closer each day, I will have
<br>
 a look at some things to keep in mind if you are thinking about updating your J2EE
<br>
 projects.
<br>
<br><span style="font-weight:bold;">Migrate to JEE5 or JEE6?</span>
<br>
 Even if the final version of the JEE6 specificatione is getting closer, there are 
<br>
 only few pre-release versions of JEE6 appservers available already. And with the 
<br>
 late integration of JSR-299 and JSR-330 there are still some major pending changes.
<br>
 In a business sense, this puts unneeded risk to your projects. If you have some 
<br>
 more time for migrating (target time mid of 2010) it would be worth waiting. Otherwise it will be best to update to JEE5.
<br>
<br><span style="font-weight:bold;">Migrate to JEE5 or keep J2EE?</span>
<br>
 Basically these are your choices. Keep it or migrate. The main decision driver are
<br>
 the business requirements. Do you have to update your application server version but
<br>
 keep all the code and no need for making changes? Go ahead and rely on the JEE5 backward
<br>
 compatibility. You have bigger changes to do? Possible performance problems? Expensive
<br>
 refactorings planned? Give JEE5 a chance and migrate. Third option is to mix both strategies.
<br>
 Only change parts of the implementation that needs to be touched and leave the rest J2EE style.
<br>
<br><span style="font-weight:bold;">Effort estimation</span>
<br>
 The migration effort highly depends on the implementation quality and complexity of your application.
<br>
 Starting with plain webapplications up to full blown CMP1.x/2.x projects your technical complexity 
<br>
 grows and the migration efforts will do so, too. The worser your code quality and arcitectures are,
<br>
 the bigger are your migration efforts. Following this, the worst kind of migration project would be
<br>
 a CMP 1.x enterprise applications with hardly any architectural concepts, bad documentation and 
<br>
 really poor code quality.
<br>
<br>
<br><span style="font-weight:bold;">Risks and Problems</span>
<br>
 Most risky is the migration not only from one specification version to another but from one
<br>
 appserver to another verdor's version. In this case you probably would have to cope with 
<br>
 vendor specific solutions (proprietary classes and/or features, deployment descriptors, specific behaviour).
<br>
 Another problem could arise from special frameworks (e.g. Weblogic Diagnostics). If you depend heavily on
<br>
 such features, you should probably try to no change the application server vendor in a first step.
<br>
<br><span style="font-weight:bold;">Requirements for migration</span>
<br>
 Your project should meet some requirements before you think about migration. 
<br>
 You should have:
<br>
 - ... some testcases at hand. Especially if you are going to migrate the persistence layer!
<br>
 - ... a testdatasource available.
<br>
 - ... two test environments (old and new)
<br>
 - ... code that compiles with JDK 1.5 (check for incompatibilities/deprecations)
<br>
 - ... thought about moving your development project settings to a JEE5 project.
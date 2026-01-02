---
title: "Java EE Development Environment - Rollout for large teams"
date: 2010-02-10 08:04:00 +0000
layout: post
tags: ["Java EE", "glassfish", "development", "rollout", "large team", "workplace"]
slug: "java-ee-development-environment-rollout"

url: /2010/02/java-ee-development-environment-rollout.html
---

Everybody comes across this issue from time to time. You have a fresh and exciting project with a couple of developers. The skills of the team members are different and you have to deliver a complete setup of the development environment for all of them. This is probably no big deal if you have up to five members. You just decide, what components to use and write a small howto which everybody can follow. Running around solves the rest of the open issues. But this is getting much harder if the team grows beyond this. And this is a setting you can still find. Even if more and more projects get smaller and use agile methods ;)
<br>
 If you are not willing to spend weeks in team internal support for setup and configuration you have to find the right approach. I am going to summarize some thoughts about this in the following parts.
<br>
<br><b>Basic Requirements</b>
<br>
 Let's look at what is needed for a minimal developer setup. 
<br>
<br>
 - Applicationserver (Binaries and Configuration)
<br>
 - Database (Binaries and Configuration)
<br>
 - Build Tool (Binaries and Configuration)
<br>
 - CVS/SVN/Whatever Client (Binaries)
<br>
 - Integrated Development Environment incl. Plugins (Binaries)&nbsp;- Basic Project Setup (Configuration)
<br>
<br>
 For almost any single part in this hopefully not too incomplete list, you have some kind of binary that needs to be installed on a developers desktop and some kind of configuration. It is highly recommended to keep any project specific configurations within your source code repository. The following thoughts only apply to the binary installs.
<br>
<br><b>Preliminary work</b>
<br>
 The bigger the teams get, the more you are in need of a detailed plan for what you are going to do and use. This does include everything. Beginning from the basic decision about the Java EE Appserver up to the single plugins for the IDE used by the developers. Basic rules are:
<br>
 - Be as near as possible to the future production environment. If this is not possible, think about staging and possibly arising problems and how to avoid them.
<br>
 - Find the right balance between the number of plugins for your IDE and make shure, they work together smoothly.
<br>
 - Find the right build tool. Even if already commodity, I still like Maven. But this adds more infrastructure to your projects. (e.g., proxy, company repository)
<br>
 - Think about the software design and architecture up front. You have to have an idea about which modules you will need and which parts of the team should work on them. (There is much more in/behind the team issues in a project. But I am not going to cover them here and now;))
<br>
<br>
 If all&nbsp; this is done, you can think about the rollout. It highly depends on the basic setting. Are you using Windows based systems for development or Unix/Linux? Do your project have special infrastructural dependencies (e.g, SSO, Host) that cannot be mocked? Make a complete list of all things that could influence the development and choose one or even combine the following approaches.
<br>
 And by the way, it is always good to catch up with the most experienced members in your team to discuss your prefered solution:
<br>
<br><b>The </b>"all-in-one-solution"<b> Rookie's Workplace</b>
<br>
 I love to call it this way. The name stands for a single image of the complete environment. Could be achieved using any kind of VM solution out there. We experiment with VMWare but there are a lot of other products available. The only task here is to setup the virual machine and install everything the way, you would like it as a developer.
<br>
 After this, you have to rollout the VM runtime to the dev PCs and ship the image.
<br>
<br><i>Implementation cost:</i> probably some days
<br><i>Rollout cost: </i>should be around half a day per team member
<br><i>Advantages: </i>Very easy rollout. Highly predetermined setup.
<br><i>Disadvantages: </i>VM performance (?), no easy incremental update, cost of the VM solution
<br>
<br><b>The</b> "bit-by-bit-solution" <b>Hacker's Workplace</b>
<br>
 The complete opposite of the previous. You define all used parts and rollout a document containing the install and setup instructions. Place the binaries in any kind of network share or provide download links and version information. 
<br>
<br><i>Implementation cost:</i> probably one day
<br><i>Rollout cost: </i>easily more than a day per team member
<br><i>Advantages: </i>hardly any rollout, Highly configurable, Easy to update
<br><i>Disadvantages: </i>Cost of setup within the team, error-prone, learning curve
<br>
<br><b>The</b> "best-of-bread-solution" <b>Developer's Workplace</b>
<br>
 If you don't like one of the above, you are in need of a combination. Such combinations are most often called the "best-of-bread" solutions, derived from a couple of projects. This is, where you start to cut the problem into pieces. Which parts of your setup is project related? Which parts change often? Which parts are common in your company? Depending on this, you have a much broader range of options to choose from. Some examples:
<br>
<br><i>Software distribution for common software</i>
<br>
 IDE, build tools and source control clients are good examples of common software you could probably put into the (already in place) software distribution system of your company. This reduces the complexity in your rollout to basically two components. The appserver and the db.
<br>
<br><i>Implementation cost:</i> not your budget :-)
<br><i>Rollout cost: </i>not your budget :-)
<br><i>Advantages: </i>stable and standardized
<br><i>Disadvantages: </i>probably not the software version you like to have.
<br>
<br><i>Option central server</i>
<br>
 This is a unusual but valid solution. You set up a central server instance and enable it for multiple developers. This could be done in different ways and is highly dependend on the application/db server you are going to use. You could for example 
<br>
 a) have separate domains for each developer (something like a multi project server)
<br>
 b) use individual application deployments (beware of naming problems)
<br>
 c) have separate databases or schemas
<br>
 d) have separate tablenames
<br>
<br>
 To get an idea what this is all about, I recommend you read <a href="http://blogs.sun.com/cphcampus/entry/gone_fishing_for_glassfish" target="_blank">"Gone fishing for Glassfish"</a> by Sidsel Jensen
<br>
<br><i>Implementation cost:</i> Depending on your org. probably not your budget :-) 
<br><i>Rollout cost: </i>none
<br><i>Advantages: </i>stable and standardized. Garanteed operation, SLA, central infrastructure, capable of big deployments with lot of data
<br><i>Disadvantages: </i>Depending on your org (hardware cost, monthly cost), hardly any flexibility
<br>
<br><i>Option de-central server</i>
<br>
 The most common setting. Every developer gets his own db and appserver on his local hdd. How easy this is, depends on the used products. If you are going to use websphere and db2 you probaply have to have bigger hardware at hand and it takes slightly more time than using mysql and tomcat :-D But this should be no problem at all. The biggest issu is to rollout the project specific configuration. In most cases it is promissing to think about a scripting approach. Nearly all appserver have any kind of command line interface you can use. Or you can even use a scripting environment. Arun Gupta posted an example for the GlassFish v2 <a href="http://blogs.sun.com/arungupta/entry/glassfish_asadmin_cli_driven_cluster" target="_blank">in his blog</a>.
<br>
 The database should not need any special setup or configuration for the development environment at all. You can thing about moving it to the central software distribution or try to find the silent install option. 
<br>
<br><i>Implementation cost:</i> Probably some days 
<br><i>Rollout cost:</i> highly dependent on the products you use. One to five days per developer.
<br><i>Advantages:</i> stable and standardized but flexible
<br><i>Disadvantages:</i> Bigger hardware required, cost of setup within the team, error-prone, learning curve
<br>
<br><i>Option embedded server</i>
<br>
 To be honest, I don't like the central approach. And having some three blue character company's software stack on my notebook is also something I realy don't even want to dream of ;) If you have the time to prepare it, you could thing about using the embedded server approach. There are some containers out there, that could be run in embedded mode. If you think about H2 DB, OpenJPA or GlassFish it is definitely an option to have a local startup class for all needed containers. In most of the cases you are forced to develop on components far way from the productive environment. Therefore you have to strictly stick to the Java EE standards your container provides. If you even have different DB this gets even more complex. 
<br>
<br><i>Implementation cost:</i> Probably some days 
<br><i>Rollout cost:</i> should be around half a day per team member
<br><i>Advantages:</i> stable and standardized but very flexible
<br><i>Disadvantages:</i> learning curve, staging problems
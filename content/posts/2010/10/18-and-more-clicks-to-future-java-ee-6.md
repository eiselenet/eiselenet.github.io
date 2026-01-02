---
title: "18 and more clicks to the future. Java EE 6 with IBM's WAS 8.0"
date: 2010-10-20 09:18:00 +0000
layout: post
tags: ["websphere", "javaee6", "ibm"]
slug: "18-and-more-clicks-to-future-java-ee-6"
link: "2010/10/18-and-more-clicks-to-future-java-ee-6.html"
url: /2010/10/18-and-more-clicks-to-future-java-ee-6.html
---

Its out. Finally. The first beta version of IBM's WebSphere Application Server has arrived. The beta offers an initial glimpse into the future of IBM's flagship.
<br>
 As of now, it isn't touting complete and total Java EE 6 support for the beta product. As they carefully state with the feature list which tells us about "support for portions of key Java Enterprise Edition 6.0 specifications." Those key portions include:
<br>
<ul>
 <li>Enterprise JavaBeans 3.1</li>
 <li>Java Persistence API (JPA) 2.0</li>
 <li>JavaServer Faces (JSF) 2.0</li>
 <li>JavaServer Pages (JSP) 2.2</li>
 <li>Servlet 3.0</li>
 <li>Java EE Connector Architecture 1.6</li>
 <li>Contexts and Dependency Injection for Java (CDI)</li>
</ul>
<div class="separator" style="clear: both; text-align: center;">
 <a href="install_packages2.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="251" src="install_packages2.png" width="320"></a>
</div>
<br><!--more-->
<br>
 Some discussions came up yesterday about how to obtain and install this release. Here is the small howto.
<br>
<ol>
 <li>Get to the <a href="https://www14.software.ibm.com/iwm/web/cc/earlyprograms/websphere/wsasoa/index.shtml" target="_blank">offical download site</a>.</li>
 <li>Click on the tab "Download"</li>
 <li>Scroll down to the requirements-section for your desired operating system. Up to now there is support for: AIX, HP-UX, Linux, Solaris, Windows and z/OS. If you are running Windows XP with SP2, you can directly jump to requirement details 4.</li>
 <li>Select the download which is the right one for your favorite isntallation mode. You can choose between a web-installation and a local-installation. For the last one, you need access to the internet. I took this pill ;)</li>
 <li>Now you are required to login with your IBM-ID.</li>
 <li>Accept the license (after carefully reading it of course)</li>
 <li>Click the tab "Download using HTTP"</li>
 <li>Select the entry "IBM Installation Manager for Windows on Intel"</li>
 <li>Click "Download" iim.win32.x86_1.4.2000.20100901_0107.zip (97 MB) and save the file</li>
 <li>Unzip it to a folder of your choice.</li>
 <li>Start install.exe</li>
 <li>Install IBM Installation manager (wowowowo...)</li>
 <li>Accept the license a second time (after carefully reading it of course)</li>
 <li>Choose your install location</li>
 <li>Start the Installation Manager</li>
 <li>If needed, don't forget to update the proxy settings</li>
 <li>Click "Install" and enter your IBM-ID for the second time.</li>
 <li>Choose the packets, you are willing to install. In this case I decided to install the Application Server Version 8.0.0.0 and click next.</li>
 <li>Accept the license a third time (after carefully reading it of course)</li>
 <li>Select the folder to place the shared resources into.</li>
 <li>Select the folder to place the appserver into.</li>
 <li>Select the language to use for the server.</li>
 <li>Select the additional components to install. I took all three (EJBDeploytool, Stand-alone clients, sample applications).</li>
 <li>Check the summary. Complete Installation 1,73 GB !!! Complete Download 885,48 MB !!! Click install and wait.</li>
 <li>The next wizard comes up. Select "Start Profile management for application server environment creation".</li>
 <li>I decided to not get into trouble with security and I unchecked the "Security" settings. This means, you can login without having to enter a password. Don't do this in production kids ...</li>
 <li>Check the settings and click next. Check the "Display First Steps Console".</li>
 <li>Click: "Start server"</li>
 <li>Point your browser to http://localhost:9060/admin and enter admin as a user</li>
 <li>Deploy your favorite Java EE 6 app making use of one of the above preview implementations</li>
</ol>
<br>
 That was a long way to go. I did my first tries with Adam Biens <a href="http://kenai.com/projects/javaee-patterns/sources/hg/show/ServerSmokeTest" target="_blank">ServerSmokeTest</a>. Adam is also on it and I guess, he will publish results soon on his <a href="" target="_blank">blog.adam-bien.com</a> .
<br>
 Next on my list is Arun Guptas <a href="http://blogs.sun.com/arungupta/entry/java_ee_6_twitter_demo" target="_blank">JavaOne Twitter</a> demo. I'll keep you updated on this.
<br>
<br>
 ************ Start Display Current Environment ************
<br>
 WebSphere Platform 8.0.0.0 [BASE 8.0.0.0 gg1038.06] running with process name XXXXNode01Cell\XXXXNode01\server1 and process id 6056
<br>
 Host Operating System is Windows XP, version 5.1 build 2600 Service Pack 3
<br>
 Java version = 1.6.0, Java Compiler = j9jit24, Java VM name = IBM J9 VM
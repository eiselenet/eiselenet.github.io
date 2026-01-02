---
title: "Updating GlassFish Server Adapter with Eclipse 3.5 (Galileo) and 3.6 (Helios)"
date: 2010-07-27 10:53:00 +0000
layout: post
tags: ["glassfish", "OEPE"]
slug: "updating-glassfish-server-adapter-with"

url: /2010/07/updating-glassfish-server-adapter-with.html
---

A coworker asked for this recently. If he tries to download the additional server adapter for GlassFish Java EE 5, Java EE 6 1.0.54 with his 3.5 Eclipse he gets the following error:
<br>
<blockquote>
 [... truncated to the relevant parts ;)]
 <br>
  Cannot complete the install because of a conflicting dependency.
 <br>
  Only one of the following can be installed at once: 
 <br>
  Sun Application Servers and GlassFish Servers support 1.0.52 
 <br>
  GlassFish Java EE 5, Java EE 6 1.0.54 
 <br>
</blockquote>
<br>
 He tried "check for updates" on the complete distribution but this does not work. Even downloading the packages and replacing them directly within Eclipse does not work. After some research, I believed I found a solution:
<br>
<br>
 If you try to simply "install the 1.0.54 as a "new extension", you will get the above error because of the already installed .52. 
<br>
 What you really need is an update. Lets give it a try:
<br>
<br>
 1) Start Eclipse 3.5.x. or 3.6.Mx (Make sure that the WTP (Web Tools Platform) plugins are installed.)
<br>
 2) Start the installation procedure : select the Help&gt;Software Updates&gt;Find and Install... menu item.
<br>
 3) Select "Search for new features to install" option and click Next.
<br>
 4) Click New Remote Site...
<br>
 5) Give a name (ie GlassFish Eclipse Site), enter the URL: <a href="https://ajax.dev.java.net/eclipse" target="_blank">https://ajax.dev.java.net/eclipse</a>
<br>
 6) Select this new site in the Sites to include in search list and click Next.
<br>
 7) Select "GlassFish v2.x Java EE 5 and v3 Java EE 6 support" in the "Select the features to install" list and click Next.
<br>
 8) Review the "Your original request has been modified." window and click Next.
<br>
 9) Accept the terms of the license agreements and click Finish.
<br>
 10) Wait for the installation to complete 
<br>
<br>
 This should be everything to do. But unfortunatualy this update fails due to a missing Manifest.mf within com.sun.enterprise.jst.server.sunappsrv_1.0.54.jar.
<br>
<blockquote>
 [...]
 <br>
  An error occurred while loading the manifest D:\Program Files\eclipse-jee-galileo-win32\plugins\com.sun.enterprise.jst.server.sunappsrv_1.0.54.jar.
 <br>
  java.util.zip.ZipException: error in opening zip file
 <br>
  at java.util.zip.ZipFile.open(Native Method)
 <br>
  at java.util.zip.ZipFile.<init>
  (ZipFile.java:114)
  <br>
   [...]
  <br>
 </init>
</blockquote>
<br>
 And: btw. This even does not work with 3.6. :(
<br>
<br>
 If you are running Helios (3.6) you can make a new try. All the above steps with a different URL: <a href="http://download.java.net/glassfish/eclipse/helios">http://download.java.net/glassfish/eclipse/helios</a>. 
<br>
 The 11th step is to confirm that you are willing to install unsigned content. if you choose yes, you are asked to restart Eclipse. This works and you are done.
<br>
<br>
 You are now able to create new Server Runtimes:
<br>
<ul>
 <li>GlassFish 2.1 Java EE 5</li>
 <li>GlassFish Server Open Source Edition 3 (Java EE 6)</li>
 <li>Sailfin v2</li>
</ul>
<br>
 I did not manage to get all this working with 3.5 :( Seems as if the whole server adaptor plugins situation is a mess at the moment. I hope, that this will be resolved shortly. Mainly because the GlassFish Plugins will move to the Oracle Enterprise Pack for Eclipse (OEPE) (compare this <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=312912" target="_blank">bug entry</a>) and will be available at this url <a href="http://download.oracle.com/otn_software/oepe/helios/wtp">http://download.oracle.com/otn_software/oepe/helios/wtp</a> in the future. This will happen with the next OEPE release (which is expected to arrive shortly). At the moment this page is not available.
<br>
<br>
 Oracle: if you are reading this: Please provide updates for 3.5 also. Don't expect anybody to 
<br>
 a) use OEPE or
<br>
 b) always update to the latest environments
<br>
<br>
 If anybody knows a solution to this .. let me know ..
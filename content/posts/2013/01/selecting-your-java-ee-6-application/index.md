---
title: "Selecting Your Java EE 6 Application Server"
date: 2013-01-11 10:10:00 +0000
layout: post
tags: ["evaluation", "appserver", "javaee6"]
slug: "selecting-your-java-ee-6-application"

url: /2013/01/selecting-your-java-ee-6-application.html
---

The number one question I get asked is: "Which Java EE Application server should we use?". With the growing adoption of Java EE 6 new compatible application server get certified. The current official&nbsp;<a href="http://www.oracle.com/technetwork/java/javaee/overview/compatibility-jsp-136984.html" target="_blank">compatibility and certification matrix</a> lists 12 different products certified for the Full Profile, the Web Profile or both. If you are going to start a new project on a green field which decision would you make? Here is what I do trying to narrow the solution space down.
<br>
<br><b>The Players</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="javaee_certified_servers.PNG" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="197" src="javaee_certified_servers.PNG" width="320"></a>
</div> What does the bouquet to pick from look like? Very colorful. Beside the well know names like IBM, SAP, RedHat, Apache and Oracle we also have lesser know names in the list. Caucho's Resin, Apache's TomEE, OW2's JOnAS and SAP's NetWeaver Cloud are Web Profile only certified. All the others reached a Full Profile certification.
<br>
 A full list of our participants with some furhter details is contained in the following table:
<br>
<br>
<table border="1">
 <tbody>
  <tr>
   <td><b>Appserver</b></td>
   <td><b>Vendor</b></td>
   <td><b>License</b></td>
   <td><b>Profile</b></td>
   <td><b>Vendor Support Available</b></td>
   <td><b>Java Version</b></td>
  </tr>
  <tr>
   <td>GlassFish Server 3.01</td>
   <td>Oracle</td>
   <td>OTN / Commercial</td>
   <td>FP</td>
   <td>Yes</td>
   <td>HotSpot 6/7</td>
  </tr>
  <tr>
   <td>GlassFish Server Open Source Edition 3.x</td>
   <td>Oracle</td>
   <td>GPL + CDDL</td>
   <td>FP</td>
   <td>No</td>
   <td>HotSpot 6/7</td>
  </tr>
  <tr>
   <td>WebSphere Application Server 8.x</td>
   <td>IBM</td>
   <td>Commercial (prod+dev)</td>
   <td>FP</td>
   <td>Yes</td>
   <td width:60pt="width:60pt">IBM JVM 7</td>
  </tr>
  <tr>
   <td>WebSphere Application Server Community Edition 3.0</td>
   <td>IBM</td>
   <td>IBM International License Agreement for Non-Warranted Programs</td>
   <td>FP</td>
   <td>Yes</td>
   <td>IBM JVM 7</td>
  </tr>
  <tr>
   <td>JEUS 7</td>
   <td>TMAX</td>
   <td>Commercial</td>
   <td>FP</td>
   <td width:60pt="width:60pt">Yes</td>
   <td width:60pt="width:60pt">HotSpot 6</td>
  </tr>
  <tr>
   <td>Interstage Application Server powered by Windows Azure</td>
   <td>Fujitsu</td>
   <td>Commercial</td>
   <td>FP</td>
   <td>Yes</td>
   <td>HotSpot 6</td>
  </tr>
  <tr>
   <td>Interstage Application Server v10.1</td>
   <td>Fujitsu</td>
   <td>Commercial</td>
   <td>FP</td>
   <td>Yes</td>
   <td>HotSpot 6</td>
  </tr>
  <tr>
   <td>Geronimo 3.0-beta-1</td>
   <td>Apache</td>
   <td>Apache 2.0</td>
   <td>FP</td>
   <td>No</td>
   <td>HotSpot 6</td>
  </tr>
  <tr>
   <td>WebLogic Server 12.1.1</td>
   <td>Oracle</td>
   <td>Commercial / Free for Dev</td>
   <td>FP</td>
   <td>Yes</td>
   <td>HotSpot 6/7</td>
  </tr>
  <tr>
   <td>uCosminexus Application Server v9.0</td>
   <td>Hitachi</td>
   <td>Commercial</td>
   <td>FP</td>
   <td>Yes</td>
   <td>?</td>
  </tr>
  <tr>
   <td>JBoss Application Server 7.x</td>
   <td>RedHat</td>
   <td>LGPL</td>
   <td>FP</td>
   <td>No</td>
   <td>HotSpot 6</td>
  </tr>
  <tr>
   <td>JBoss Enterprise Application Platform 6</td>
   <td>RedHat</td>
   <td>LGPL / Commercial</td>
   <td>FP</td>
   <td>Yes</td>
   <td>HotSpot 6</td>
  </tr>
  <tr>
   <td>Resin 4.0.17</td>
   <td>Caucho</td>
   <td>GPL "Resin Open Source" version "Resin Professional" Commercial</td>
   <td>WP</td>
   <td>Yes</td>
   <td>HotSpot 6</td>
  </tr>
  <tr>
   <td>TomEE 1.0</td>
   <td>Apache</td>
   <td>Apache 2.0</td>
   <td>WP</td>
   <td>No</td>
   <td>HotSpot 6/7</td>
  </tr>
  <tr>
   <td>JOnAS 5.3.0-M8-SNAPSHOT</td>
   <td>OW2</td>
   <td>LGPL</td>
   <td>WP</td>
   <td>No</td>
   <td>HotSpot 6/7</td>
  </tr>
  <tr>
   <td>NetWeaver Cloud</td>
   <td>SAP</td>
   <td>Commercial</td>
   <td>WP</td>
   <td>Yes</td>
   <td>SAP Java Server VM 1.6</td>
  </tr>
 </tbody>
</table>
<br><b>Looking at your Requirements</b>
<br>
 The good message first. All the application servers listed on the compatibility matrix passed the Java EE 6 TCK (Technology Compatibility Kit) which basically means, that they deliver the same kind of functionality related to Java EE 6. Even if this contains a decent area of fuzziness due to many reasons. One is, that no TCK covers 100% of the specified features. But I'm sure you can come up with other reasons. What basically is a good message leaves you wondering which could be the right set of requirements to compare instead? It is obviously not a complete technical set of metrics but a combination of different aspects.
<br>
 A simple example set of metrics&nbsp;could be the following:
<br>
 - Source Code License (OSS or Commercial)
<br>
 - License Costs (free for development and production)
<br>
 - Support (development and/or production support available)
<br>
 - Certified Java Version (6.0, 7.0 / HotSpot / Proprietary JVM)
<br>
 - Java EE 6 profile (Full or Web Profile)
<br>
 This is by far too less if you are doing a full blown product selection . You most likely are going to look at metrics from different categories (e.g. functional, non-functional, corporate, financial aspects) but for now this should be sufficient. 
<br>
<br><b>The Selection Process</b>
<br>
 Lets do it: On to the selection process in this simple example. 
<br><b>First a little disclaimer: This is a very very simplified selection process which really don't dig into all the dirty little details. It is only thought as a rough methodology idea for your own selection process! So, if anybody is unhappy about what I did, I am happy to read about it in the comments!</b>
<br>
 We are looking for an OSS server which supports the Java EE 6 Full Profile. That means we are going to kill all the commercial servers and the Web Profile only ones:
<br>
<br>
<table border="1">
 <tbody>
  <tr>
   <td><b>Appserver</b></td>
   <td><b>Vendor</b></td>
   <td><b>License</b></td>
   <td><b>Profile</b></td>
   <td><b>Vendor Support Available</b></td>
   <td><b>Java Version</b></td>
  </tr>
  <tr>
   <td>GlassFish Server 3.01</td>
   <td>Oracle</td>
   <td>OTN / Commercial</td>
   <td>FP</td>
   <td>Yes</td>
   <td>HotSpot 6/7</td>
  </tr>
  <tr>
   <td>GlassFish Server Open Source Edition 3.x</td>
   <td>Oracle</td>
   <td>GPL + CDDL</td>
   <td>FP</td>
   <td>No</td>
   <td>HotSpot 6/7</td>
  </tr>
  <tr>
   <td>WebSphere Application Server Community Edition 3.0</td>
   <td>IBM</td>
   <td>IBM International License Agreement for Non-Warranted Programs</td>
   <td>FP</td>
   <td>Yes</td>
   <td>IBM JVM 7</td>
  </tr>
  <tr>
   <td>Geronimo 3.0-beta-1</td>
   <td>Apache</td>
   <td>Apache 2.0</td>
   <td>FP</td>
   <td>No</td>
   <td>HotSpot 6</td>
  </tr>
  <tr>
   <td>JBoss Application Server 7.x</td>
   <td>RedHat</td>
   <td>LGPL</td>
   <td>FP</td>
   <td>No</td>
   <td>HotSpot 6</td>
  </tr>
  <tr>
   <td>JBoss Enterprise Application Platform 6</td>
   <td>RedHat</td>
   <td>LGPL / Commercial</td>
   <td>FP</td>
   <td>Yes</td>
   <td>HotSpot 6</td>
  </tr>
 </tbody>
</table>
<br>
 Five left. Two which are questionable from a license point of view. Both JBoss EAP 6 and Oracle GlassFish Server do have a commercial license (as to my understanding) but they still rely on the OSS server products and the licensed version is only relevant if you want to have support. With the next step I am going to kick all the servers which don't offer vendor support:
<br>
<br>
<table border="1">
 <tbody>
  <tr>
   <td><b>Appserver</b></td>
   <td><b>Vendor</b></td>
   <td><b>License</b></td>
   <td><b>Profile</b></td>
   <td><b>Vendor Support Available</b></td>
   <td><b>Java Version</b></td>
  </tr>
  <tr>
   <td>GlassFish Server 3.01</td>
   <td>Oracle</td>
   <td>OTN / Commercial</td>
   <td>FP</td>
   <td>Yes</td>
   <td>HotSpot 6/7</td>
  </tr>
  <tr>
   <td>WebSphere Application Server Community Edition 3.0</td>
   <td>IBM</td>
   <td>IBM International License Agreement for Non-Warranted Programs</td>
   <td>FP</td>
   <td>Yes</td>
   <td>IBM JVM 7</td>
  </tr>
  <tr>
   <td>JBoss Enterprise Application Platform 6</td>
   <td>RedHat</td>
   <td>LGPL / Commercial</td>
   <td>FP</td>
   <td>Yes</td>
   <td>HotSpot 6</td>
  </tr>
 </tbody>
</table>
<br>
 Which brings us down to three. Still not really a single result. Now lets intentionally remove IBM because they are not certified on HotSpot but use their own IBM JVM J9.
<br>
<br>
<table border="1">
 <tbody>
  <tr>
   <td><b>Appserver</b></td>
   <td><b>Vendor</b></td>
   <td><b>License</b></td>
   <td><b>Profile</b></td>
   <td><b>Vendor Support Available</b></td>
   <td><b>Java Version</b></td>
  </tr>
  <tr>
   <td>GlassFish Server 3.01</td>
   <td>Oracle</td>
   <td>OTN / Commercial</td>
   <td>FP</td>
   <td>Yes</td>
   <td>HotSpot 6/7</td>
  </tr>
  <tr>
   <td>JBoss Enterprise Application Platform 6</td>
   <td>RedHat</td>
   <td>LGPL / Commercial</td>
   <td>FP</td>
   <td>Yes</td>
   <td>HotSpot 6</td>
  </tr>
 </tbody>
</table>
<br>
 That leaves us with JBoss AS 7 and GlassFish as the only real options today according to my little evaluation.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="the_winners.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" src="the_winners.PNG"></a>
</div>
<br><b>Recommendation</b>
<br>
<br>
 One could call me afraid of selection a single winner here. If you know me, you know that this simply isn't true. Given the initial metrics this is exactly the outcome and obviously the selected metrics are not&nbsp;sufficient enough to appoint a clear winner.
<br>
 If you need a final recommendation you have to tweak the metrics to your needs. I would recommend looking a little bit further into:
<br>
 - Market reach (e.g. downloads/customers/etc.)
<br>
 - Maturity (e.g. availability&nbsp;in years since EE 6 final version)
<br>
 - Development Performance (e.g. Startup-Time/IDE integration)
<br>
<br>
 Remark:
<br>
 I'm sorry for the fact, that I mixed the commercial offerings from Oracle and RedHat with the related OSS servers a bit. Given the fact, that I wanted to see a OSS server would have lead to a knock-out criteria with the vendor support requirement. Given the fact, that I really don't want to propose to use AS7.1.1.Final to anybody (see a very&nbsp;<a href="" target="_blank">nice summary of the reasons</a>&nbsp;done by henk) I personally think, that it is fair to recommend the commercial side for both servers.
<br>
<div>
 <br>
</div>
---
title: "GlassFish vs. WebLogic - 10 visions for the future"
date: 2010-02-04 14:23:00 +0000
layout: post
tags: ["glassfish", "oracle", "weblogic server"]
slug: "glassfish-vs-weblogic-10-visions-for"
link: "2010/02/glassfish-vs-weblogic-10-visions-for.html"
url: /2010/02/glassfish-vs-weblogic-10-visions-for.html
---

The Sun/Oracle merger raised some questions about the future of different components. One of interest to me is the <a href="" target="_blank">GlassFish Application Server</a>. Beside the <a href="" target="_blank">Oracle WebLogic</a> it is the next Java EE Application Server in Oracle's portfolio. 
<br>
 Not really much concrete has been said about the future coexistence of both. But some postings, slides and webcasts are around. Time to summarize them and draw some conclustions. To be honest: Non of the thoughts here are confirmed by anybody. Especially <b>not </b>by Oracle! I don't know if the described things will happen and I don't have any detailed insights in both products timelines or roadmaps. Happy to discuss everything and read about your thoughts.
<br>
<br><b>1) "GlassFish continues as the Java EE reference implementation and as an open source project"</b>
<br>
 This is a statement, that is totally clear. Nothing will change. It will continue as an Open Source project and you will have a new RI for any of the comming Java EE versions.
<br>
<br><b>2) GlassFish software licensing</b>
<br>
 Most of the components of the GlassFish plattform are available under a Dual License consisting of the <a href="https://glassfish.dev.java.net/public/CDDL+GPL.html" target="_blank">Common Development and Distribution License (CDDL) v1.0 and GNU Public License (GPL) v2</a>. Details for GFv2 kann be found on the <a href="http://wiki.java.net/bin/view/Projects/GlassFishCodeDependencies" target="_blank">GF Wiki</a>. This will stay the same for most of the modules. Except for those, making the way into WebLogic Server. I expect this to be at least the following three: <a href="" target="_blank">Metro</a>, <a href="" target="_blank">Jersey</a>, <a href="" target="_blank">Grizzly</a>
<br>
<br><b>3) Equinox will NOT be the OSGI platform for the Weblogic DM Server</b>
<br>
 As presented on last years OOW (WebLogic Server Architecture, OSGi and Java Enterprise Edition, Adam Leftik and Jeff Trent), the Equinix Platform has some drawbacks (Lacks a Generic Application Framework, Application Isolation, RFC-133 Context Class Loader). Therefore I expect the Weblogic DM server to use something else. I don't know if this will have any effect on GF. It is possible that the OSGI platform of GF will change, too.
<br>
<br><b>4) There will be NO GlassFish v3 with clustering capabilities</b>
<br>
 The slide #15 of the Oracle + Sun: Application Server webcast states, that GF will be for productive and agile development. WLS is the availabillity and scalability solution. Therefore the v2 was the last GF with clustering facilities.
<br>
<br><b>5) Metro, Jersey and Grizzly will make it to the WebLogic 11g</b>
<br>
 As mentioned by Thomas Kurian in the strategy webcast. These are great assets from the GF family and I believe that those three projects will make it to WLS.
<br>
<br><b>6) There will be tool support for migrating GF Apps to WLS</b>
<br>
 The complete development to production staging process will be adressed by upcomming Oracle solutions. JDeveloper and/or OEPE will have plugins/support for automatic migration of GF apps to WLS. The WLS split deployment directory structure will also be enhanced with staging features. There will probably also be new maven plugins supporting dev and productive builds with GF and WLS.
<br>
<br><b>7) Embedded GlassFish will be bundled with the OEPE</b>
<br>
 Beeing the development platform of the future, it is obvious that OEPE will bundle an embedded GF in the future. 
<br>
<br><b>8) JDeveloper will get support for GF</b>
<br>
 Beeing the development platform of Oracle could lead to having build in support for GF development in JDeveloper. 
<br>
<br><b>9) NetBeans will become the Java ME IDE</b>
<br>
 Having more and more GF support in JDeveloper and OEPE leads to a further specialization of NetBeans. It will become the Java ME IDE of the Future. 
<br>
<br><b>10) There will be a complete ADF implementation for GF</b>
<br>
 ADF will become available on GF, too.
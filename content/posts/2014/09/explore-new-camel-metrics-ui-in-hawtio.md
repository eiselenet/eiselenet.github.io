---
title: "Explore the new Camel Metrics UI in hawtio"
date: 2014-09-12 09:09:00 +0000
layout: post
tags: [""]
slug: "explore-new-camel-metrics-ui-in-hawtio"
link: "2014/09/explore-new-camel-metrics-ui-in-hawtio.html"
url: /2014/09/explore-new-camel-metrics-ui-in-hawtio.html
---

The new Camel 2.14 release is in voting right now and should arrive very soon. Time to already play around with it a bit and see, what amazing new features will be part of the release. One thing I've been looking into in more detail lately is the general purpose console hawtio. 
<br>
<br><b>Latest Camel as Snapshot in your application</b>
<br>
 First thing to do is to grep latest 2.14-SNAPSHOT of Camel and put it to work. Most simple thing to do is to take my <a href="https://github.com/myfear/CamelEE7" target="_blank">CamelEE7 bootstrap</a> example and modify it a bit. Change the camel version property in the pom.xml to:
<br>
 &nbsp;&lt;camel.version&gt;2.14-SNAPSHOT&lt;/camel.version&gt;
<br>
 And <a href="http://camel.apache.org/maven-2-snapshot-repository-in-pom.html" target="_blank">add the Maven 2 snapshot</a> repository also. Now you can build it and deploy it to your WildFly 8.1 domain.
<br>
<br>
 One More Thing
<br>
 If you want to use hawtio, you need to have access to the JMX information of the server. There's this little agent required to run on the server-side to make that happen. It is called jolokia and you can simply get the latest <a href="http://jolokia.org/download.html" target="_blank">WAR Agent from their download</a> site and install it as a WAR in your WildFly.
<br>
<br>
<br><b>Get Latest Hawtio</b>
<br>
 The getting started guide is a perfect place to find all the details you need to run today's release of Hawtio 1.4.19 which is required to play around with the metrics console.
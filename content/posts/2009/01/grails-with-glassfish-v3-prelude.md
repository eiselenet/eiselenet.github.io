---
title: "Grails with Glassfish v3 Prelude"
date: 2009-01-19 08:00:00 +0000
layout: post
tags: ["glassfish", "prelude", "grails", "v3", "howto"]
slug: "2009-01-19-grails-with-glassfish-v3-prelude"
url: /2009/01/grails-with-glassfish-v3-prelude.html
---

I got the change to take a deeper look at the new GlassFish v3 Prelude. <a href="" target="_blank">Nazrul Islam</a> published a <a href="http://blogs.sun.com/nazrul/entry/top_10_glassfish_v3_prelude1" target="_blank">personal top 10 of the biggest features</a> in v3. One among others is the "Support for dynamic languages such as Ruby and Groovy". Have a look at the official <a href="http://wiki.glassfish.java.net/attach/V3FunctionalSpecs/Scripting-one-pager-v1.1.html?version=11" target="_blank">OnePager</a> or a <a href="http://wikis.sun.com/display/TheAquarium/VivekPandeyScripting" target="_blank">presentation</a>.
<br>
<br>
In this short howto I want to provide a brief step by step guide, on how to setup a sample Grails application and start it with the embedded GlassFish server.
<br>
<br>
#1 Download GlassFish v3 prelude from the official website (choose the distribution ,you like)
<br>
#2 Install or extract the archive to a suitable location
<br>
#3 Add your JDK/bin folder to the path variable (export with unix/linux)
<br>
#4 Start the server with &lt;as-install&gt;/bin/asadmin start-domain
<br>
#5 Access the admin console http://localhost:4848/ 
<br>
#6 Click on Update Tool, choose "Available AddOns", choose "Grails Scripting" (version 1.0.4-1.0 at the time of writing)
<br>
#7 Stop the server with &lt;as-install&gt;/bin/asadmin stop-domain
<br>
#8 Set/export GRAILS_HOME=&lt;as-install&gt;/glassfish/grails
<br>
#9 Execute GRAILS_HOME/bin/grails create-app &lt;your_app_name&gt;
<br>
#10 Change to the newly created directory &lt;your_app_name&gt;
<br>
#11 Execute GRAILS_HOME/bin/grails run-app
<br>
#12 Open your favorite browser and point it to: http://localhost:8080/&lt;your_app_name&gt;/
<br>
<br>
Congratulations! You are done!
<br>
<br><a onblur="try \{parent.deselectBloggerImageGracefully();\} catch(e) \{\}" href="http://www.eisele.net/blog/uploaded_images/grails-712832.jpg"><img style="display:block; margin:0px auto 10px; text-align:center;cursor:pointer; cursor:hand;width: 200px; height: 116px;" src="http://www.eisele.net/blog/uploaded_images/grails-712829.jpg" border="0" alt=""></a>
---
title: "Basics: Debugging with Oracle Weblogic Server"
date: 2009-07-10 05:33:00 +0000
layout: post
tags: ["debugging", "basics", "oracle", "weblogic server"]
slug: "2009-07-10-basics-debugging-with-oracle-weblogic"
url: /2009/07/basics-debugging-with-oracle-weblogic.html
---

Remote Debugging a JVM is quite simple at all. This is the same, if you are trying to debug an Oracle Webglogic Server instance.
<br>
Grep your favourite IDE and give it a try.
<br>
<br>
First thing to do, is to enable Debugging with Oracle Weblogic Server. Look out for the startWeblogic.cmd/sh in your Weblogic Server Domain and add the needed properties to the java options:
<br>
<br>
<blockquote>
 <br>
 (Windows)
 <br>
 set JAVA_OPTIONS=-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n
 <br>
 <br>
 (Unix/Linux)
 <br>
 JAVA_OPTIONS=-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n
 <br>
 export OPTIONS
 <br>
</blockquote>
<br>
<br>
The -XDebug parameter enables debugging. The -Xnoagent parameter disables the default sun.tools.debug debug agent. The -Xrunjdwp parameter loads the JPDA reference implementation of JDWP. Debugging is enabled on port 4000. The JDWP protocol is the protocol used to debug with a remote debugger.
<br>
<br>
After this, start the WebLogic Server by invoking the startWebLogic.cmd/sh script.
<br>
<br>
Next step is to deploy your application to the server. You can do this, however you like to. Use the Oracle Enterprise Pack for Eclipse or the autodeploy feature or even the weblogic administration console. 
<br>
<br>
Now start your favourite IDE and look out for the debugging view. If you are using Eclipse, open the Debug configurations and choose "Remote Java Application" and press the "new Launch Configuration" button and enter the needed values.
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/debug_config-725968.png"><img style="display:block; margin:0px auto 10px; text-align:center;cursor:pointer; cursor:hand;width: 200px; height: 189px;" src="http://www.eisele.net/blog/uploaded_images/debug_config-725965.png" border="0" alt=""></a>
<br>
<br>
Now we must start the remote debugger. Right click on the class you want to debug and choose "Debug As" and your server configuration. The only thing left to do is to set the needed breakpoints in your code.
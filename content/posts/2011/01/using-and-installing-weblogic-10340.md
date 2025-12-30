---
title: "Installing and Using the WebLogic 10.3.4.0 Maven Plug-In for Deployment"
date: 2011-01-15 19:40:00 +0000
layout: post
tags: ["weblogic", "maven"]
slug: "2011-01-15-using-and-installing-weblogic-10340"
url: /2011/01/using-and-installing-weblogic-10340.html
---

It happened very silent. The Oracle Middleware 11g Patchset 3 is available since yesterday. To me all the fancy new Oracle stuff (ADF, SOA and more) is not too important at the moment. But one thing is: The backbone of it. My all time favorite <a href="http://oracle.com/weblogic" target="_blank">WebLogic Server</a>. It's now at version number 10.3.4.0 and comes with <a href="http://download.oracle.com/docs/cd/E17904_01/web.1111/e13852/toc.htm" target="_blank">some minor tweaks</a> in general this time. One of the things I was waiting for since some time is the new <a href="http://download.oracle.com/docs/cd/E17904_01/web.1111/e13702/maven_deployer.htm#DEPGD383" target="_blank">WebLogic Maven plugin</a>. Now you can use an Apache Maven plug-in for WebLogic Server (weblogic-maven-plugin) to perform deployment operations similar to those supported by the command-line utility, weblogic.Deployer. The plug-in lets you deploy, redeploy, update, and such, applications built using Maven to WebLogic Server from within the Maven environment. 
<br>
<br><b>Getting started</b>
<br>
 Get your copy of the latest <a href="http://www.oracle.com/technetwork/middleware/weblogic/downloads/wls-main-097127.html" target="_blank">WebLogic (together with Coherence and OEPE)</a> and install it. Download and configure <a href="http://maven.apache.org/download.html" target="_blank">Apache Maven 2.x</a>. 
<br>
 Next is to configure the new plugins. Everything gets a lot simpler if you start with a blank domain. Start the configuration wizard and create one. Execute the user_projects\domains\base_domain\bin\setDomainEnv.cmd/.sh to prepare your environment. Now we can go on and configure the plugin jar file. This could be done with the WebLogic JarBuilder Tool (wljarbuilder) which ist located at <code>MW_HOME/wlserver_10.3/server/lib/</code> change to that folder and execute: <code>java -jar wljarbuilder.jar -profile weblogic-maven-plugin</code>. This will generate your barely 59 MB big <code>weblogic-maven-plugin.jar</code>. Don't think about the weird output ;)
<br>
 Open the jar file with your favorite ZIP tool and extract the META-INF\maven\com.oracle.weblogic\weblogic-maven-plugin\pom.xml to MW_HOME/wlserver_10.3/server/lib. If you look at the file you get the maven dependency information:
<br><code> <br>
  &lt;groupId&gt;com.oracle.weblogic&lt;/groupId&gt;<br>
  &lt;artifactId&gt;weblogic-maven-plugin&lt;/artifactId&gt;<br>
  &lt;packaging&gt;maven-plugin&lt;/packaging&gt;<br>
  &lt;version&gt;10.3.4&lt;/version&gt;<br></code>
<br>
 These fields identify the plug-in and mark a specific place in a Maven repository, acting like a coordinate system for Maven projects.
<br>
 Now you have to install the plugin to your local maven repository. Execute: 
<br><code><br>
  mvn install:install-file -Dfile=weblogic-maven-plugin.jar -DpomFile=pom.xml<br></code>
<br>
 If you have never ever used Maven before, you should probably go and read other pages before but if you came here and you are wondering about what's happening after you executed this command for the first time ever: Maven is downloading the internet to your local repository and finally installs the generated jar file there, too. 
<br>
<br><b>Using the WebLogic Maven Plugin</b>
<br>
 You can use the plugin in two modes. First is from the cmd line. You can use the following goals: deploy, list-apps, redeploy,start-app,stop-app,undeploy,update-app.
<br>
 If you followed my explanation you can simply type the following:
<br><code>mvn com.oracle.weblogic:weblogic-maven-plugin:help</code>
<br>
 Or you can include the plugin in your own applications's pom.xml file:
<br><code><br>
  ...<br>
  &lt;build&gt; <br>
  &lt;plugins&gt; <br>
  &lt;plugin&gt; <br>
  &lt;groupid&gt;com.oracle.weblogic&lt;/groupId&gt; <br>
  &lt;artifactid&gt;weblogic-maven-plugin&lt;/artifactId&gt; <br>
  &lt;version&gt;10.3.4&lt;/version&gt; <br>
  &lt;configuration&gt; <br>
  &lt;adminurl&gt;t3://localhost:7001&lt;/adminurl&gt;<br>
  &lt;user&gt;system&lt;/user&gt; <br>
  &lt;password&gt;weblogic1&lt;/password&gt; <br>
  &lt;upload&gt;true&lt;/upload&gt; <br>
  &lt;action&gt;deploy&lt;/action&gt; <br>
  &lt;remote&gt;false&lt;/remote&gt; <br>
  &lt;verbose&gt;true&lt;/verbose&gt; <br>
  &lt;source&gt;$\{project.build.directory\}/$\{project.build.finalName\}.$\{project.packaging\}&lt;/source&gt; <br>
  &lt;name&gt;$\{project.build.finalName\}&lt;/name&gt; <br>
  &lt;/configuration&gt; <br>
  &lt;executions&gt; <br>
  &lt;execution&gt; <br>
  &lt;phase&gt;install&lt;/phase&gt; <br>
  &lt;goals&gt; <br>
  &lt;goal&gt;deploy&lt;/goal&gt; <br>
  &lt;/goals&gt; <br>
  &lt;/execution&gt; <br>
  &lt;/executions&gt; <br>
  &lt;/plugin&gt; <br>
  ... <br>
  &lt;/plugins&gt; <br>
  &lt;/build&gt; <br>
  ...<br></code>
<br>
<br>
 You can add the plug-in into any life cycle phase of the project. Optionally, you can add a goal to any of the default Maven life cycle phases by adding an executions tag in the pom.xml file. The goal is then bound to that particular phase of the Maven life cycle. As shown above the goal is bound to the "install" phase of the life cycle. By doing this, every time you run the mvn install command, the deployment plug-in is also called.
<br>
<br><b>Wondering about the long command?</b>
<br>
 The cmd line command is quite long. You can and should shorten the invocation name of the plugin. A couple of steps have to be taken to achieve this. 
<br>
 You have to change the pom.xml file and add:
<br><code><br>
  &lt;build&gt;<br>
  &lt;plugins&gt;<br>
  &lt;plugin&gt;<br>
  &lt;artifactid&gt;maven-plugin-plugin&lt;/artifactId&gt;<br>
  &lt;version&gt;2.3&lt;/version&gt;<br>
  &lt;configuration&gt;<br>
  &lt;goalprefix&gt;weblogic&lt;/goalPrefix&gt;<br>
  &lt;/configuration&gt;<br>
  &lt;/plugin&gt;<br>
  &lt;/plugins&gt;<br>
  &lt;/build&gt;<br></code>
<br>
 After that modify the settings.xml file located in your $HOME/.m2 directory with the following lines:
<br><code><br>
  &lt;plugingroups&gt;<br>
  &lt;plugingroup&gt;com.oracle.weblogic&lt;/pluginGroup&gt;<br>
  &lt;/pluginGroups&gt;<br></code>
<br>
 before you provision the plug-in in your Maven repository.
<br>
<br>
 After all this is done, you can call all commands with a shortened version:
<br><code>mvn weblogic:deploy</code>
<br>
<br><b>Conclusion</b>
<br>
 Even if the documentation states, that the plugin should use Maven 2.x I tried everything with Maven 3 and did not discover any problems in general.
<br>
 I will give it a test drive production near and will report further findings. At the moment everything looks very promising and simple. Thanks guys for providing this one!
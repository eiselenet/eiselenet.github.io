---
title: "Oracle WebLogic Development-Only Installer"
date: 2010-04-30 06:21:00 +0000
layout: post
tags: ["oracle", "installation", "weblogic server"]
slug: "oracle-weblogic-development-only"
link: "2010/04/oracle-weblogic-development-only.html"
url: /2010/04/oracle-weblogic-development-only.html
---

One of the biggest issues I always had with WebLogic was the installation. Even if the installer iteself was very straight forward and easy, the installation took longer with every new release containing new features. This was already adressed in the past with the possibility to select from some of the most common components. But it still took some time to setup the basic installation.
<br>
 Starting with WebLogic Server 10.3.3.0 Oracle now provides a Development-Only Installer as a ZIP file. It supports Windows, Linux, and Mac OS X systems. It contains all necessary artifacts for development of applications on WebLogic Server. The following parts are NOT included in the distribution:
<br>
<ul>
 <li>Samples</li>
 <li>Derby database</li>
 <li>WebServer plug-ins</li>
 <li>Native JNI libraries for unsupported platforms</li>
 <li>Sun SDK and Oracle JRockit SDK</li>
 <li>Coherence</li>
 <li>Oracle Enterprise pack for Eclipse</li>
</ul>
<br><b>Download and extract</b>
<br>
 Get the 423 MB ZIP File from the <a href="http://www.oracle.com/technology/software/products/ias/htdocs/wls_main.html" target="_blank">offical OTN download page</a>. 
<br>
 And extract it's contents to a folder of your choice. This will become your "Middleware home directory". Please do not use any existing one. Make shure you place it seperate from any existing fusion middleware (FMW) installations.
<br>
 You can see, another ZIP file and a simple readme.txt with nothing but a link to the FMW documentation was extracted. Extract this one, too. 
<br>
<br><b>Install and configure the JDK</b>
<br>
 As the installer comes without any JDK, you should have either the Sun jdk 160_18 or the Oracle JRockit 160_17_R28 in place. If not: Install one of them. The following examples I assume that you have a Windows system running. 
<br>
 Next is to setup JAVA_HOME and MW_HOME variables in your command shell. Open one and type: 
<br><code>set JAVA_HOME=C:\jrockit_160_17_R28.0.0-679<br>
  set MW_HOME=C:\myfmwhome</code>
<br>
<br><b>Configure your installation</b>
<br>
 Now you have to run the installation configuration script in the %MW_HOME% directory. You need to do this only only once. Again only if you move the installation to another location/machine.
<br><code>configure.cmd</code>
<br>
 Now you have setup your WLS environment in the current shell by typing:
<br><code>%MW_HOME%\wlserver\server\bin\setWLSEnv.cmd</code>
<br>
<br><b>Create a domain and start it</b>
<br>
 A domain is automatically created if you create a domain directory with any name you like and simply start your WLS. 
<br><code>mkdir C:\myfmwhome\mydomain<br>
  %JAVA_HOME%\bin\java.exe -Xmx1024m -XX:MaxPermSize=128m weblogic.Server</code>
<br>
 Once the domain is created, you can shutdown WLS and restart it with the scripts provided in the newly created domain. 
<br>
 If you want some additional control, you can also use the GUI based configuration wizzard:
<br><code>%MW_HOME%/wlserver/common/bin/config.cmd</code>
<br>
 But you can also do this using WLST or the unpack command. WebLogic Server domain and extension templates are available in the %MW_HOME%/wlserver/common/templates/domains and %MW_HOME%/wlserver/common/templates/applications directories to help you get started with domain creation. For further details refer to the WLS product documentation.
<br>
<br>
 Note: It is recommended that you create the domains outside the %MW_HOME%. This example does not follow this recommendation for simplicity ;)
<br>
<br><b>Applying patches</b>
<br>
 As the smart update tool is not supported, patching has to be done manually. You have to get the needed patch jars from Oracle Support and apply it manually to the classpath.
<br>
<br><b>Upgrading</b>
<br>
 In-place upgrade of installation is not supported in the zip distribution. If needed, you have to re-download the zip distribution and extract it again to a new location. Of course you could keep the already generated domains.
<br>
<br><b>Deleting</b>
<br>
 simply delete the %MW_HOME% directory. Ensure that you don't delete any of your domains. Keeping them in a separate directory makes this easier.
<br>
<br>
<br><b>Further readings:</b>
<br><a href="http://download.oracle.com/docs/cd/E14571_01/wls.htm">Complete WebLogic Server Documentation</a>
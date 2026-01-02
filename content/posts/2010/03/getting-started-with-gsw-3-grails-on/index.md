---
title: "Getting started with (GSW #3): Grails on Weblogic 11g (WLS 10.3.2.0)"
date: 2010-03-23 10:34:00 +0000
layout: post
tags: ["GSW", "groovy", "grails", "\"Getting started with\"", "weblogic server"]
slug: "getting-started-with-gsw-3-grails-on"

url: /2010/03/getting-started-with-gsw-3-grails-on.html
---

Next in my <a href="http://blog.eisele.net/search/label/GSW">GSW</a> series is a topic I want to write about since some time. One of my co-workers left the company (never will get it, why ;)) and moved on to a <a href="" target="_blank">Groovy</a> company. After having heard him talking about the "fancy" part in Groovy I was looking for a way to get this into my favorite Java EE appserver. Found it in <a href="" target="_blank">Grails</a>.
<br>
<br><i>Grails is a dynamic web application framework built on Java and Groovy, leveraging best of breed APIs from the Java EE sphere including Spring, Hibernate and SiteMesh. Grails brings to Java and Groovy developers the joys of convention-based rapid development while allowing them to leverage their existing knowledge and capitalize on the proven and performant APIs Java developers have been using for years.<br>
  (Quote: http://www.grails.org)</i>
<br>
<br><b>Prerequisites</b>
<br>
 - You should have an Oracle Weblogic Server 11g R1P1 in place. If you don't,
<br>
 get it from the <a href="http://www.oracle.com/technology/software/products/middleware/index.html" target="_blank">Oracle Technology Network's download page</a>.
<br>
 - Grep a copy of the lates <a href="http://www.grails.org/1.3-M1+Release+Notes">Grails 1.3-M1</a> (download takes some time..)
<br>
<br><b>Installation</b>
<br>
 Extract the 48MB archive into any folder to your haddrive. You should stay away from spaces or any other weird characters. X:\grails on Windows or ~/grails on Unix is a nice place.
<br>
 Now you need a suitable environment variable that points to your installation folder. On Windows you do this with
<br><code>set GRAILS_HOME="X:\grails</code> try <code>export GRAILS_HOME=~/grails</code>
<br>
 Now check, if you have a JAVA_HOME environment variable defined. If not, do this now. It should point to your JDK installation. You find one inside the Weblogic product installation directory. (e.g. %WLS_HOME%\jdk160_14_R27.6.5-32).
<br>
 Next is to append a reference to the "bin" directory within the Grails directory to your PATH variable (eg %GRAILS_HOME%\bin on Windows or $GRAILS_HOME/bin on Unix). 
<br>
 Open a command window on windows or simply type "grails" in your shell. You should see a help message.
<br>
<br><b>Create an empty Grails project</b>
<br>
 Once you have installed Grails and veryfied that everything is running you can start using it. First step is to 
<br>
 create a new project. Use the built-in target for creating one. Change to the command line and enter:
<br><code>grails create-app</code>
<br>
 Some magic happens to the std_out. Watch out for the script to prompt you for the name of your application. I used "wlssample" here. After the script executed completely you find a new directory in the filesystem which is named after the application name you entered. Explore the directory structure. If you are used to Java EE webapplications you probably will not find anything surprising here.
<br>
<br><b>Create a Domain Class</b>
<br>
 Having an empty app does not make sense. Let's add a persistent object, a Domain Class to it.
<br>
 Make shure you cd'ed to the root directory of your new project.
<br><code>grails create-domain-class</code>
<br>
 The script asks for a Domain Class name. Enter one. Responding to the command with "book" will create a file
<br>
 called Book.groovy in %appname%/grails-app/domain. You can edit it with your favorite text editor or IDE.
<br>
 Modify it to look like this:
<br><code>class Book \{<br>
  String title<br>
  String author<br>
  \}</code>
<br>
<br><b>Create a Controller</b>
<br>
 Ok. An app, a Domain Object. We are still missing the UI. All this starts with a Controller. Grails Controllers are central to Grails applications. They handle web requests and URLs of the request map to a controller class and a closure within the class.
<br>
 Another target assists you with this. Run the
<br><code>grails create-controller</code>
<br>
 target and type in the name of the controller. In this example I use the name "Book" again, which generates a controller called BookController.groovy in the directory %appname%/grails-app/controllers.
<br>
 To make this example as simple as possible we use a feature called dynamic Scaffolding. It allows you to auto-generate a whole application for a given domain class. In your case for the Domain Class Book. Open the controller and change it to look like this:
<br><code>class BookController \{<br>
  def scaffold = Book<br>
  \}</code>
<br>
<br>
<br><b>Fire it up with Tomcat</b><a href="grails1.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="320" src="grails1.png" width="240"></a>
<br>
 To see, if you did everything the right way you can fire up the included tomcat and take a first look at your application. Type:
<br><code>grails run-app</code>
<br>
 and point your browser to 
<br><code>http://localhost:8080/wlssample/</code>
<br>
 You should see something like in the following screenshot. Congratulations! You have your fist Grails App up and running.
<br>
<br><b>Package a WAR File</b>
<br>
 But we want to run this on our Weblogic. To get this up an running we need to create a web application archive (WAR) file from the project. There is a simple script out there to package it. Type:
<br><code>grails prod war</code>
<br>
 This will place a %appname%-0.1.war file in the %appname%/target directory. Now browse to your weblogic console application (typically found here http://localhost:7001/console/), login and click left on "Deployments". Click the "install" button and browse to the target folder. Select the %appname%-0.1.war file and click "Next". Choose "Install this deployment as an application" and click "Next". Don't change anything on the next screen. Simply klick "Finish".
<br>
 If everything went perfect, you will see two simple confirmation messages stating that the deployment has been successfully installed. Now point your browser to:
<br><code>http://localhost:7001/wlssample-0.1/</code>
<br>
 There you are! Congratulations. You have your sample Grails app up and running in Weblogic Server! 
<br>
<br><b>Further Links and Readings</b>
<br><a href="" target="_blank">Grails Documentation</a>
<br><a href="http://www.grails.org/IDE+Integration" target="_blank">IDE Integration</a>
<br><a href="http://www.grails.org/plugin/home" target="_blank">More Plugins</a>
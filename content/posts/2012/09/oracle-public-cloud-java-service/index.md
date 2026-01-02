---
title: "Oracle Public Cloud Java Service Development Tools"
date: 2012-09-07 07:10:00 +0000
layout: post
tags: ["OracleCloud", "CLI", "java", "maven", "ant"]
slug: "oracle-public-cloud-java-service"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2012/09/oracle-public-cloud-java-service.html
---

Even if the Java EE 7 EG decided to postpone the PaaS features to EE 8, Oracle itself is actively working on a Java EE driven public PaaS offering. This is known since some time now and you also might have read about <a href="http://blog.eisele.net/2012/08/oracle-public-cloud-java-netbeans.html" target="_blank">my first test-drives with NetBeans</a> in the context of the Early Access program. Signs are there, that we will see the final GA version during Oracle's OpenWorld and I thought I give you some more stuff to increase your anticipation about the things to come.
<br>
<br><b>Command Line Interface (CLI)</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG-20120907-00369.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="240" src="IMG-20120907-00369.jpg" width="320"></a>
</div> The Oracle Cloud tooling is quite impressive. You (will) have NetBeans 7.3, Eclipse OEPE and JDeveloper support and beside this you also have a couple of CLI driven tools to integrate with your build&nbsp;life-cycle. The base for that is the CLI interface. Two areas are supported here: First of all the remote control for your cloud service instance. So, everything you need to manage your deployment is there. You can install, update, delete, start and stop deployments. Beside that you have a hook to a couple of configuration information about your deployments, the executed jobs, your datasources and instances. The second area is the whitelist-tool. This allows you to check against the white-list before spending time on deployment circles.
<br>
 Everything you need to get started with it will be in the sdk-download. At the time of writing this, it is still a 1.0.0 meant for early access. I expect that version to increase prior to GA, especially as I wasn't able to get this up and running behind a proxy. So, for now let's note that everything I show you should be doable with a proxy by additionally defining the "httpproxy" attribute with every command.
<br>
<br>
 Given the following:
<br>
<pre> java -jar javacloud.jar install -user user@domain.com -serviceinstance java -identitydomain myiddomain -path oracle-javacloud-sdk-1.0.0/samples/apps/visitors.war -application visitor </pre> you can install the example application located in the cloud SDK examples folder under the name visitor. If you don't specify the -password attribute you will be prompted to enter it. The&nbsp;white list&nbsp;checker should have been executed before:
<br>
<pre>java -jar whitelist.jar oracle-javacloud-sdk-1.0.0/samples/apps/visitors.war </pre> The expected output here is: 
<br>
<pre>INFO - Whitelist validation has succeeded </pre> If you try something caught by the white list it lists the validations accordingly: 
<br>
<pre>ERROR - There are 1 error(s) found for /SimpleSample/dist/SimpleSample.war ERROR - Path:/SimpleSample/dist/SimpleSample.war (1 Error) ERROR - Class:net.eisele.cloud.ForbiddenServlet (1 Error) ERROR - 1:Method exit not allowed from java.lang.System.(Line No:34 Method Name:java.lang.System-&gt;exit(int)) ERROR - /SimpleSample/dist/SimpleSample.war Failed with 1 error(s). ERROR - Whitelist validation has failed with 1 error(s). </pre>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="on-premise.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="183" src="on-premise.png" width="320"></a>
</div> Nice so far. But what makes this very interesting is, that you can also use the same CLI to deploy to your local (on premise) instance by simply adding a little classpath magic. Most of the commands do support an optional argument 'local'. When this argument takes the value of true, the command is performed against the local Weblogic domain.
<br>
<pre>java -jar javacloud.jar install -user system -password weblogic1 -serviceinstance java -identitydomain myiddomain -local true -adminurl http://localhost:7001 -classpath oracle-javacloud-sdk-1.0.0\lib\localextension.jar:wls1035\wlserver_10.3\server\lib\weblogic.jar -path /SimpleSample/dist/SimpleSample.war -application simplesample </pre> That was it. The output tells you what is wrong or right: 
<br>
<pre>WARNING - Application Manager is on CLI mode. No asynchronous operations will be available against local Weblogic Server when invoked from CLI. &lt;06.09.2012 18:16 Uhr MESZ&gt; &lt;Info&gt; &lt;J2EE Deployment SPI&gt; &lt;BEA-260121&gt; &lt;Initiatin g deploy operation for application, simplesample [archive: /temp/cloud/simplesample.war], to AdminServer .&gt; INFO - The application installation operation is successfully completed. &lt;06.09.2012 18:16 Uhr MESZ&gt; &lt;Warning&gt; &lt;JNDI&gt; &lt;BEA-050001&gt; &lt;WLContext.close() was called in a different thread than the one in which it was created.&gt;</pre> That was easy! But the CLI isn't the only tool you have. 
<br>
<br><b>Maven Tooling</b>
<br>
 Based on the CLI you also have a Maven plugin which could be integrated with your build.&nbsp;Each command is exposed as a maven goal. The plugin jar is maven-javacloud.jar that is available in the SDK. Each goal supports all of its corresponding CLI command arguments as properties. First step is to install the plugin which is located in the lib folder of the SDK.
<br>
<pre>mvn install:install-file -Dfile=$SDK_HOME/lib/maven-javacloud.jar -DgroupId=com.oracle.cloud -DartifactId=javacloud -Dversion=1.0 -Dpackaging=jar </pre> If this is done, you can either add it to your pom.xml or even execute it directly from the command line. To get a list of jobs from your java service all you have to do is to issue the following command: 
<br>
<pre>mvn com.oracle.cloud:javacloud:listjobs -D$SDK_HOME=/oracle-javacloud-sdk-1.0.0 -Didentitydomain=myiddomain -Duser=user@domain.com -Dpassword=weblogic1 -Dserviceinstance=java </pre> That brings up a complete list of the jobs you issued against the instance. An example could look like that: 
<br>
<pre>1:Job Id - 27199 ----------- - ----------------------- -&gt; - Properties ------------ - ----------------------- Status - COMPLETE Application - MyCloud Start Time - Thu Aug 02 18:22:15 CEST 2012 End Time - Thu Aug 02 18:22:22 CEST 2012 Duration - 7 seconds Last Updated Time - Thu Aug 02 18:22:22 CEST 2012 Last Updated Description - 35 days, 22 hours, 23 minutes and 1 second ago Operation - Redeploy Application Number of Logs - 3 ------------- - ----------------------- </pre> This basically is an overview of all the status of all jobs for the given instance. You could also see the individual status for a jobid using the mvn com.oracle.cloud:javacloud:jobstatus goal. There are three logfiles available for every job: virus-scan, whitelist and the action log (deploy, redeploy). You can get any of the logfiles by using the 
<br>
<pre>mvn com.oracle.cloud:javacloud:joblogfile -Djobid=27199 -Dlog=whitelist [...] </pre> goal. But using it this way basically doesn't differ to what the CLI interface has to offer. So, let's move on to another example by integrating it into a pom.xml. There is a very good example in the SDK which contains (nearly) the complete life-cycle binding for all goals. Integrated into the &lt;build&gt; section it could look like this:
<br>
<pre class="brush:xml">&lt;build&gt; &lt;plugins&gt; &lt;plugin&gt; &lt;groupId&gt;com.oracle.cloud&lt;/groupId&gt; &lt;artifactId&gt;javacloud&lt;/artifactId&gt; &lt;version&gt;1.0&lt;/version&gt; &lt;executions&gt; &lt;execution&gt; &lt;id&gt;install&lt;/id&gt; &lt;phase&gt;integration-test&lt;/phase&gt; &lt;configuration&gt; &lt;failonerror&gt;true&lt;/failonerror&gt; &lt;jobidproperty&gt;install_job_id&lt;/jobidproperty&gt; &lt;downloadlastlogonfailure&gt;true&lt;/downloadlastlogonfailure&gt; &lt;/configuration&gt; &lt;goals&gt; &lt;goal&gt;install&lt;/goal&gt; &lt;/goals&gt; &lt;/execution&gt; &lt;execution&gt; &lt;id&gt;installjobstatus&lt;/id&gt; &lt;phase&gt;integration-test&lt;/phase&gt; &lt;configuration&gt; &lt;jobid&gt;$\{install_job_id\}&lt;/jobid&gt; &lt;waitfor&gt;true&lt;/waitfor&gt; &lt;failonerror&gt;true&lt;/failonerror&gt; &lt;downloadlastlogonfailure&gt;true&lt;/downloadlastlogonfailure&gt; &lt;/configuration&gt; &lt;goals&gt; &lt;goal&gt;jobstatus&lt;/goal&gt; &lt;goal&gt;listapplications&lt;/goal&gt; &lt;/goals&gt; &lt;/execution&gt; </pre> You see the plugin dependency followed by three goal bindings. The install goal binds to the integration-test phase and installs the app to the cloud. Remembering the jobid stuff? The&nbsp;&lt;jobidproperty&gt; tag binds the assigned jobid to the corresponding maven property and you can access it later on via&nbsp; &lt;jobid&gt;$\{install_job_id\}&lt;/jobid&gt;. Nicely done! You can reinstall, start and stop your deployments and do whatever has to be done. Working with different profiles for on-premise and cloud is also possible.
<br>
<br><b>ANT Tooling</b>
<br>
 Last but not least you still have the ANT version of everything I described above available.&nbsp;The tasks are packaged in ant-javacloud.jar that is available in the SDK. The task declaration can be found in the SDK at ant-javacloud.jar\oracle\cloud\antlib.xml and can be declared with the namespace prefix of javacloud like this: 
<br>
<pre class="brush:xml">&lt;project name="sample-how-to-use-ant" default="all" basedir="." javacloud="antlib:oracle.javacloud.antlib"&gt; &lt;!-- more of your stuff here --&gt; &lt;path id="javacloud.classpath"&gt; &lt;pathelement location="$\{SDK_HOME\}/lib/ant-javacloud.jar"/&gt; &lt;/path&gt; &lt;taskdef uri="antlib:oracle.javacloud.antlib" resource="oracle/cloud/antlib.xml" classpathref="javacloud.classpath"&gt; &lt;!-- required only when working against local Weblogic domain --&gt; &lt;path id="local.classpath"&gt; &lt;pathelement path="$\{SDK_HOME\}/lib/localextension.jar"/&gt; &lt;!-- optional for running whitelist validation automatically while installing --&gt; &lt;pathelement path="$\{SDK_HOME\}/lib/whitelist.jar"/&gt; &lt;pathelement path="$\{WEBLOGIC.JAR\}"/&gt; &lt;/path&gt; &lt;/project&gt; </pre> That is a quite complete set of tools to integrate your new Oracle Java Cloud service into your build. The Maven plugin is the one which obviously makes most sense to most of us but all the others aren't forgotten. So, be curious about when this stuff becomes publicly abailable! 
<br>
<br>
 As a reminder, the Oracle Public Cloud runs on Exalogic :)
<br><iframe allowfullscreen frameborder="0" height="315" src="http://www.youtube.com/embed/UGzjDloUw_s?rel=0" width="560"></iframe>
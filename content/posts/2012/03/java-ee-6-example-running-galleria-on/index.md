---
title: "The Java EE 6 Example - Running Galleria on WebLogic 12 - Part 3"
date: 2012-03-14 11:52:00 +0000
layout: post
tags: ["weblogic", "javaee6", "netbeans", "example"]
slug: "java-ee-6-example-running-galleria-on"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2012/03/java-ee-6-example-running-galleria-on.html
---

You probably followed me with the last <a href="http://blog.eisele.net/2012/03/java-ee-6-running-galleria-on-glassfish.html" target="">Java EE 6 Galleria example posts</a>. The <a href="http://blog.eisele.net/2012/03/java-ee-6-galleria-example-part-1.html">first one</a> was the basic introduction. The <a href="http://blog.eisele.net/2012/03/java-ee-6-running-galleria-on-glassfish.html">second one</a> was about running it on latest GlassFish. Someone of the RedHat guys mentioned, that we should look into bringing this example off from GlassFish. Great ;) Thanks for the nice idea. That is exactly what we are going to do today. I am going to bring the Galleria example to latest WebLogic 12c.
<br>
<br><b>Preparation</b>
<br>
 Get yourself in the mood for some configuration. You already have the latest <a href="http://netbeans.org/downloads/index.html" target="_blank">NetBeans 7.1</a> installed and you are going to download the <a href="http://www.oracle.com/technetwork/middleware/fusion-middleware/downloads/index.html" target="_blank">WebLogic 12c ZIP</a> distribution in a second. After you have downloaded the wls1211_dev.zip put it to a location of choice and unzip it. From now on we are going to call this folder the %MW_HOME% folder. Open a command line and&nbsp;setup %JAVA_HOME%, %JAVA_VENDOR% and %MW_HOME% variables in it:
<br>
<pre>set JAVA_HOME=D:\jdk1.7.0_04 set MW_HOME=D:\temp\wls12zip set JAVA_VENDOR=Sun </pre> After you have done this one final step is to&nbsp;run the installation configuration script configure.cmd in the MW_HOME directory. This is a one time thing to do.
<br>
<br><b>Setup your WebLogic Domain</b>
<br>
 Next thing we need is a WebLogic domain.&nbsp;Open a new command line prompt. Setup your environment in the current shell by running the&nbsp;%MW_HOME%\wlserver\server\bin\setWLSEnv.cmd script. Execute the&nbsp;%MW_HOME%\wlserver\common\bin\config.cmd and follow the wizard to create a basic WebLogic Server Domain called test-domain in a folder of your choice (e.g. D:\temp\test-domain). Give a username and password of your choice (e.g. system/system1) and click through the wizard until you have a "finish" button down there. WebLogic needs the Derby client jar file in order to configure and use the database. Copy the&nbsp;derbyclient-10.8.2.2.jar from your m2 repository to the&nbsp;test-domain\lib folder.&nbsp;Now lets start the newly created domain manually by running the startWebLogic.cmd in your newly created domain directory. Verify that everything is up and running by navigating to&nbsp;<a href="http://localhost:7001/console">http://localhost:7001/console</a>&nbsp;and logging in with the credentials from above. Navigate to "Services &gt; Data Sources" and select the "New" button from above the table. Select a "Generic Datasource" and enter a name of your choice (e.g.&nbsp;GalleriaPool) and enter&nbsp;jdbc/galleriaDS as the JNDI-Name. Select Derby as the Database Type and click "next". Select Derby's Driver (Type 4) and click "Next" and "Next" and enter the connection properties (Database: GALLERIATEST, Host: localhost. User and Password: APP" and click "Next". If you like to, you can hit the "Test Configuration" button on top and make sure everything is setup in the right way.
<br>
 Next the most tricky part. We need a JDBC realm like the one we configured for GlassFish. First difference here is, that we don't actually create a new realm but add an authentication mechanism to the available one. There is a nasty limitation with WebLogic. You can configure as many security realms as you like, but only one can be active at a given time. This stopped myself for a while until I got the tip from <a href="http://blog.eisele.net/2011/11/review-oracle-weblogic-server-11gr1-ps2.html">Michel Schildmeijer</a> (thanks, btw!).&nbsp;Navigate to "Security Realms" and select "myrealm" from the table. Switch to the Providers tab. Select "New" above the table of the Authentication Providers. Enter "GalleriaAuthenticator" as the name and select "SQLAuthenticator" from the dropdow-box as a type. Click ok. Select the GalleriaAuthenticator and set the Control Flag: SUFFICIENT and save. After that&nbsp;switch to the "Provider Specific" tab. Enter the following:
<br>
<pre>Data Source Name: GalleriaPool Password Style Retained: unchecked Password Algorithm: SHA-512 Password Style: SALTEDHASHED SQL Get Users Password: SELECT PASSWORD FROM USERS WHERE USERID = ? SQL Set User Password: UPDATE USERS SET PASSWORD = ? WHERE USERID = ? SQL User Exists: SELECT USERID FROM USERS WHERE USERID = ? SQL List Users: SELECT USERID FROM USERS WHERE USERID LIKE ? SQL Create User: INSERT INTO USERS VALUES ( ? , ? ) SQL Remove User: DELETE FROM USERS WHERE USERID = ? SQL List Groups: SELECT GROUPID FROM GROUPS WHERE GROUPID LIKE ? SQL Group Exists: SELECT GROUPID FROM GROUPS WHERE GROUPID = ? SQL Create Group: INSERT INTO GROUPS VALUES ( ? ) SQL Remove Group: DELETE FROM GROUPS WHERE GROUPID = ? SQL Is Member: SELECT USERID FROM USERS_GROUPS WHERE GROUPID = ? AND USERID = ? SQL List Member Groups: SELECT GROUPID FROM USERS_GROUPS WHERE USERID = ? SQL List Group Members: SELECT USERID FROM USERS_GROUPS WHERE GROUPID = ? AND USERID LIKE ? SQL Remove Group Memberships: DELETE FROM USERS_GROUPS WHERE GROUPID = ? OR GROUPID = ? SQL Add Member To Group: INSERT INTO USERS_GROUPS VALUES( ?, ?) SQL Remove Member From Group: DELETE FROM USERS_GROUPS WHERE GROUPID = ? AND USERID = ? SQL Remove Group Member: DELETE FROM USERS_GROUPS WHERE GROUPID = ? Descriptions Supported: unchecked </pre> Save your changes. and go back to the "Providers" tab. Click on the "Reorder" button and push the GalleriaAuthenticator to the top of the list. Click "ok", when done and stop your WebLogic instance. You are free to restart it at any time.
<br>
<br><b>Configure your Projects</b>
<br>
 Java EE is portable. Right. And you should be able to run the same deployment without any changes on the WebLogic 12c. That is theory. In practice you will have to touch the deployment. Because WebLogic has some issues with Hibernate. And it is a lot more crotchety when it comes to deployments than GlassFish is. First of all you have to create a "galleria-ear\src\main\application\META-INF" folder. Put a blank weblogic-application.xml there and put the following code in it:
<br>
<br>
<pre class="brush:xml">&lt;?xml version='1.0' encoding='UTF-8'?&gt; &lt;weblogic-application xmlns="http://xmlns.oracle.com/weblogic/weblogic-application" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.oracle.com/weblogic/weblogic-application http://xmlns.oracle.com/weblogic/weblogic-application/1.4/weblogic-application.xsd"&gt; &lt;prefer-application-packages&gt; &lt;package-name&gt;antlr.*&lt;/package-name&gt; &lt;/prefer-application-packages&gt; &lt;/weblogic-application&gt; </pre>
<div>
 <br>
</div> That tells WebLogic to prefer the application packaged libraries over those already present in the server. Let's go ahead. We need to add the Hibernate dependencies to the ear. With GlassFish we skipped that step, because we installed the Hibernate package with the server. Here we go. Open the galleria-ear pom.xml and add the following to the dependencies section: 
<br>
<pre class="brush:xml">&lt;dependency&gt; &lt;groupId&gt;org.hibernate&lt;/groupId&gt; &lt;artifactId&gt;hibernate-entitymanager&lt;/artifactId&gt; &lt;version&gt;4.0.1.Final&lt;/version&gt; &lt;/dependency&gt; &lt;dependency&gt; &lt;groupId&gt;org.hibernate&lt;/groupId&gt; &lt;artifactId&gt;hibernate-core&lt;/artifactId&gt; &lt;version&gt;4.0.1.Final&lt;/version&gt; &lt;/dependency&gt; &lt;dependency&gt; &lt;groupId&gt;org.hibernate&lt;/groupId&gt; &lt;artifactId&gt;hibernate-validator&lt;/artifactId&gt; &lt;version&gt;4.2.0.Final&lt;/version&gt; &lt;/dependency&gt; &lt;dependency&gt; &lt;groupId&gt;org.jboss.logging&lt;/groupId&gt; &lt;artifactId&gt;jboss-logging&lt;/artifactId&gt; &lt;version&gt;3.1.0.CR2&lt;/version&gt; &lt;/dependency&gt; </pre> You also need to look at the maven-ear-plugin and add the following to the &lt;configuration&gt;:
<br>
<pre class="brush:xml">&lt;defaultLibBundleDir&gt;lib&lt;/defaultLibBundleDir&gt; </pre> And if you are there already, remove the commons-codec jarModule. It doesn't hurt, but it get's packaged into the ear/lib folder, so you can skip it.
<br>
 Next navigate to the galleria-jsf project and open the web.xml. The &lt;login-config&gt; is incomplete and should look like this:
<br>
<pre class="brush:xml"> &lt;login-config&gt; &lt;auth-method&gt;FORM&lt;/auth-method&gt; &lt;form-login-config&gt; &lt;form-login-page&gt;/Login.xhtml&lt;/form-login-page&gt; &lt;form-error-page&gt;/Login.xhtml&lt;/form-error-page&gt; &lt;/form-login-config&gt; &lt;/login-config&gt;</pre>
<pre class="brush:xml">&lt;security-role&gt; &lt;description&gt;All registered Users belong to this Group&lt;/description&gt; &lt;role-name&gt;RegisteredUsers&lt;/role-name&gt; &lt;/security-role&gt; </pre>
<br>
 You need to define the possible roles, too otherwise the WebLogic security stuff will start to complain.
<br>
 Add a blank weblogic.xml to the&nbsp;galleria-jsf\src\main\webapp\WEB-INF folder and add the following lines to it:
<br>
<pre class="brush:xml">&lt;?xml version="1.0" encoding="UTF-8"?&gt; &lt;weblogic-web-app xmlns="http://xmlns.oracle.com/weblogic/weblogic-web-app" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.oracle.com/weblogic/weblogic-web-app http://xmlns.oracle.com/weblogic/weblogic-web-app/1.4/weblogic-web-app.xsd"&gt; &lt;security-role-assignment&gt; &lt;role-name&gt;RegisteredUsers&lt;/role-name&gt; &lt;principal-name&gt;RegisteredUsers&lt;/principal-name&gt; &lt;/security-role-assignment&gt; &lt;session-descriptor&gt; &lt;timeout-secs&gt;3600&lt;/timeout-secs&gt; &lt;invalidation-interval-secs&gt;60&lt;/invalidation-interval-secs&gt; &lt;cookie-name&gt;GalleriaCookie&lt;/cookie-name&gt; &lt;cookie-max-age-secs&gt;-1&lt;/cookie-max-age-secs&gt; &lt;url-rewriting-enabled&gt;false&lt;/url-rewriting-enabled&gt; &lt;/session-descriptor&gt; &lt;/weblogic-web-app&gt; </pre>
<br>
 We are mapping the web.xml role to a WebLogic role here. You could have skipped this, but I like it this way so you don't get confused. The session-descriptor element is taking care of the JSESSION cookie name. If you wouldn't change it, you would get into trouble with signed in users to the admin console. 
<br>
 Move on the the galleria-ejb project. Create a blank weblogic-ejb-jar.xml in the "galleria-ejb\src\main\resources\META-INF" folder. Put the following code in it:
<br>
<br>
<pre class="brush:xml">&lt;?xml version="1.0" encoding="UTF-8"?&gt; &lt;weblogic-ejb-jar xmlns="http://xmlns.oracle.com/weblogic/weblogic-ejb-jar" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.oracle.com/weblogic/weblogic-ejb-jar http://xmlns.oracle.com/weblogic/weblogic-ejb-jar/1.0/weblogic-ejb-jar.xsd"&gt; &lt;security-role-assignment&gt; &lt;role-name&gt;RegisteredUsers&lt;/role-name&gt; &lt;principal-name&gt;RegisteredUsers&lt;/principal-name&gt; &lt;/security-role-assignment&gt; &lt;/weblogic-ejb-jar&gt; </pre>
<div>
 <br>
</div> Comparable to the web.xml/weblogic.xml this also tells&nbsp;WebLogic how to map the ejb-jar.xml security roles to WebLogic roles. Fine,&nbsp;open the persistence.xml and add the following lines:
<br>
<pre class="brush:xml">&nbsp;&lt;property name="hibernate.dialect" value="org.hibernate.dialect.DerbyDialect" /&gt; &lt;property name="hibernate.transaction.jta.platform" value="org.hibernate.service.jta.platform.internal.WeblogicJtaPlatform" /&gt; </pre> The first one explicitly selects the Derby dialect for Hibernate. The second one tells Hibernate where and how to look for the transactions. All done. Now you should be able to build the project again and deploy it. Use the admin console or <a href="http://blog.eisele.net/2011/12/quickstart-weblogic-12c-with-netbeans.html">NetBeans</a> to deploy it. Thanks for taking the time to follow this lengthy post. I hope it was helpful!
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="galleria_on_weblogic.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="228" src="galleria_on_weblogic.png" width="400"></a>
</div>
<br>
 Want to know what it takes to get the <a href="http://blog.eisele.net/2012/03/java-ee-6-example-testing-galleria-part.html">unit and integration tests</a> up and running? <a href="http://blog.eisele.net/2012/03/java-ee-6-example-testing-galleria-part.html">Read on!</a>
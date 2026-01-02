---
title: "JDBC Security Realm and form based Authentication on GlassFish with Primefaces"
date: 2011-01-25 07:32:00 +0000
layout: post
tags: ["glassfish", "primefaces", "Security Realm"]
slug: "jdbc-security-realm-and-form-based"

url: /2011/01/jdbc-security-realm-and-form-based.html
---

A very common scenario is to have a protected application. You have users which belong to a group and have different rights in your application. The most basic part is to configure your application server and enable the login for your application. This post guides you through the process of setting up a simple JDBC based realm with GlassFish 3.0.1 and creates a basic login form based on Primefaces. 
<br>
<br>
 UPDATE: 28.01.2013
<br>
 There is a <a href="http://blog.eisele.net/2013/01/jdbc-realm-glassfish312-primefaces342.html">new version of the tutorial</a> out since a few days.
<br>
<br><b>Preparation</b>
<br>
 I am assuming, that you have <a href="" target="_blank">NetBeans (7 Beta2, Java)</a>, GlassFish (<a href="http://glassfish.java.net/public/downloadsindex.html#top">3.0.1</a> or 3.1 bundled with NetBeans) and <a href="">MySQL (5.x)</a> installed and you verified that everything single bit is working. 
<br>
<br><b>Basic Project Setup</b>
<br>
 Fire up NetBeans and start a new project. Choose Java Web &gt; Web Application and hit next. Now enter a name (e.g. jdbcrealm) and hit next. Choose a Server or add a new one. Select Java EE 6 as your EE version and hit next. Check the box that states JavaServer Faces and switch to the tab components to select Primefaces as the component suite. Click finish. Now you are set. With your NetBeans project. 
<br>
<br><b>Database</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="jdbcrealmdb.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" src="jdbcrealmdb.png"></a>
</div> Create a new database. There are a couple of ways to do this. I like the MySQL GUI Tooling. Get your copy from the <a href="http://dev.mysql.com/downloads/gui-tools/5.0.html" target="_blank">mysql.com website</a>. But you can also use the mysql cmd line. How ever. Execute the following SQL against your installation:
<br><code><br>
  CREATE DATABASE jdbcrealmdb;<br>
  USE jdbcrealmdb;<br>
  CREATE TABLE `jdbcrealmdb`.`users` (<br>
  `username` varchar(255) NOT NULL,<br>
  `password` varchar(255) DEFAULT NULL,<br>
  PRIMARY KEY (`username`)<br>
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;<br><br>
  CREATE TABLE `jdbcrealmdb`.`groups` (<br>
  `username` varchar(255) DEFAULT NULL,<br>
  `groupname` varchar(255) DEFAULT NULL)<br>
  ENGINE=InnoDB DEFAULT CHARSET=utf8; <br>
  CREATE INDEX groups_users_FK1 ON groups(username ASC);<br></code>
<br>
 If we are going to secure our application we need some users with roles. Let's create them, if we are already here:
<br><code><br>
  INSERT INTO users VALUES('admin','adminadmin');<br>
  INSERT INTO users VALUES('markus','blogeisele');<br>
  INSERT INTO groups VALUES('admin','admin');<br>
  INSERT INTO groups VALUES('markus','user');<br></code>
<br>
<br><b>Domain Configuration</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
</div>
<div class="separator" style="clear: both; text-align: center;">
 <a href="nb_project.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="320" src="nb_project.png" width="151"></a>
</div> Now switch to the Services tab in NetBeans and right click the Databases node in the tree. Choose new connection and select MySQL (Connector/J driver) in the drop-down. The driver files should show an entry which points to %NB_HOME%/ide/modules/ext/mysql-connector-java-5.1.13-bin.jar. Hit next and enter the connection parameters (Don't forget to select the newly created database: jdbcrealmdb). Remember to test the connection. After that click finish. Back to the projects tab and your application. Right click and select "New &gt; Other &gt; GlassFish &gt; JDBC Connection Pool". Enter a name for the new connection pool (e.g. SecurityConnectionPool) and underneath the checkbox "Extract from Existing Connection:" select your newly created MySQL connection. Click next. review the connection pool properties and click finish. The newly created Server Resources folder now shows your sun-resources.xml file. Follow the steps and create a "New &gt; Other &gt; GlassFish &gt; JDBC Resource" pointing the the created SecurityConnectionPool (e.g. jdbc/securityDatasource).
<br>
<br>
 Next is to copy the %NB_HOME%/ide/modules/ext/mysql-connector-java-5.1.13-bin.jar to your GlassFish domain (%GF_HOME%/glassfish/domains/domain1/lib). Fire up your domain by switch to the services tab in NetBeans and expand the servers node. Right click your GlassFish server node and select start. Open a browser and visit <code>http://localhost:4848/ </code>. Select "Configuration &gt; Security &gt; Realms" and click new. Enter a name (e.g. JDBCRealm) and select the com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm from the drop down. Fill in the following values into the textfields:
<br>
<table>
 <tbody>
  <tr>
   <td>JAAS</td>
   <td><b>jdbcRealm</b></td>
  </tr>
  <tr>
   <td>JNDI</td>
   <td><b>jdbc/securityDatasource</b></td>
  </tr>
  <tr>
   <td>User Table</td>
   <td><b>users</b></td>
  </tr>
  <tr>
   <td>User Name Column</td>
   <td><b>username</b></td>
  </tr>
  <tr>
   <td>Password Column</td>
   <td><b>password</b></td>
  </tr>
  <tr>
   <td>Group Table</td>
   <td><b>groups</b></td>
  </tr>
  <tr>
   <td>Group Name Column</td>
   <td><b>groupname</b></td>
  </tr>
  <tr>
   <td>Assign Groups</td>
   <td><b>default</b></td>
  </tr>
  <tr>
   <td>Digest Algorithm</td>
   <td><b>none</b></td>
  </tr>
 </tbody>
</table> Most of the values are self explanatory. The most important one is the digest algorithm. I have set it to "none" in this case. This means, the passwords are stored in plain text in your DB. You have to use a digest algorithm that fits your required security assurance. You can use whatever algorithm is supported by Java (e.g. MD5, SH-1, SHA-256).
<br>
 After you are finished, click save. 
<br>
<br><b>Secure your application</b>
<br>
 Done with configuring your environment. Now we have to actually secure the application. First part is to think about the resources to protect. Jump to your Web Pages folder and create two more folders. One named "admin" and another called "users". The idea behind this is, to have two separate folders which could be accessed by users belonging to the appropriate groups. Now we have to create some pages. Let's start with the loginForm.xhtml. Create the file with the following content in the root of your Web Pages folder. 
<br><code><br>
  &lt;?xml version='1.0' encoding='UTF-8' ?&gt;<br>
  &lt;!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"&gt;<br>
  &lt;html xmlns="http://www.w3.org/1999/xhtml"<br>
  xmlns:p="http://primefaces.prime.com.tr/ui"<br>
  xmlns:h="http://java.sun.com/jsf/html"&gt;<br>
  &lt;h:head&gt;<br>
  &lt;title&gt;Login Form&lt;/title&gt;<br>
  &lt;/h:head&gt;<br>
  &lt;h:body&gt;<br>
  &lt;p:panel header="Login From"&gt;<br>
  &lt;form method="POST" action="j_security_check"&gt;<br>
  Username: &lt;input type="text" name="j_username" /&gt;<br>
  Password: &lt;input type="password" name="j_password" /&gt;<br>
  &lt;br /&gt;<br>
  &lt;input type="submit" value="Login" /&gt;<br>
  &lt;input type="reset" value="Reset" /&gt;<br>
  &lt;/form&gt; <br><br>
  &lt;/p:panel&gt;<br>
  &lt;/h:body&gt;<br>
  &lt;/html&gt;<br></code>
<br>
 As you can see, whe have the basic Primefaces p:panel component which has a simple html form which points to the predefined action j_security_check. This is, where all the magic is happening. You also have to include two input fields for username and password with the predefined names j_username and j_password. Now we are going to create the loginError.xhtml which is shown, if the user did not enter the right credentials. (use the same DOCTYPE and header as seen in the above example).
<br><code><br>
  &lt;h:body&gt;<br>
  &lt;p:panel header="Login Error"&gt;<br>
  Sorry, you made an Error. Please try again: &lt;a href="#\{facesContext.externalContext.requestContextPath\}/faces/loginForm.xhtml" &gt;Login&lt;/a&gt;<br>
  &lt;/p:panel&gt;<br>
  &lt;/h:body&gt;<br></code>
<br>
 The only magic here is the href link of the Login anchor. We need to get the correct request context and this could be done by accessing the faces context. If a user without the appropriate rights tries to access a folder he is presented a 403 access denied error page. If you like to customize it, you need to add it and add the following lines to your web.xml:
<br><code><br>
  &lt;error-page&gt;<br>
  &lt;error-code&gt;403&lt;/error-code&gt;<br>
  &lt;location&gt;/faces/403.xhtml&lt;/location&gt;<br>
  &lt;/error-page&gt;<br></code>
<br>
 That snipped defines, that all requests that are not authorized should go to the 403 page.
<br>
<br>
 If you have the web.xml open already, let's start securing your application. We need to add a security constraint for any protected resource:
<br><code><br>
  &lt;security-constraint&gt;<br>
  &lt;display-name&gt;Admin Pages&lt;/display-name&gt;<br>
  &lt;web-resource-collection&gt;<br>
  &lt;web-resource-name&gt;Protected Admin Area&lt;/web-resource-name&gt;<br>
  &lt;description/&gt;<br>
  &lt;url-pattern&gt;/faces/admin/*&lt;/url-pattern&gt;<br>
  &lt;http-method&gt;GET&lt;/http-method&gt;<br>
  &lt;http-method&gt;POST&lt;/http-method&gt;<br>
  &lt;http-method&gt;HEAD&lt;/http-method&gt;<br>
  &lt;http-method&gt;PUT&lt;/http-method&gt;<br>
  &lt;http-method&gt;OPTIONS&lt;/http-method&gt;<br>
  &lt;http-method&gt;TRACE&lt;/http-method&gt;<br>
  &lt;http-method&gt;DELETE&lt;/http-method&gt;<br>
  &lt;/web-resource-collection&gt;<br>
  &lt;auth-constraint&gt;<br>
  &lt;description/&gt;<br>
  &lt;role-name&gt;admin&lt;/role-name&gt;<br>
  &lt;/auth-constraint&gt;<br>
  &lt;/security-constraint&gt;<br>
  &lt;security-constraint&gt;<br>
  &lt;display-name&gt;User Pages&lt;/display-name&gt;<br>
  &lt;web-resource-collection&gt;<br>
  &lt;web-resource-name&gt;Protected Users Area&lt;/web-resource-name&gt;<br>
  &lt;description/&gt;<br>
  &lt;url-pattern&gt;/faces/users/*&lt;/url-pattern&gt;<br>
  &lt;http-method&gt;GET&lt;/http-method&gt;<br>
  &lt;http-method&gt;POST&lt;/http-method&gt;<br>
  &lt;http-method&gt;HEAD&lt;/http-method&gt;<br>
  &lt;http-method&gt;PUT&lt;/http-method&gt;<br>
  &lt;http-method&gt;OPTIONS&lt;/http-method&gt;<br>
  &lt;http-method&gt;TRACE&lt;/http-method&gt;<br>
  &lt;http-method&gt;DELETE&lt;/http-method&gt;<br>
  &lt;/web-resource-collection&gt;<br>
  &lt;auth-constraint&gt;<br>
  &lt;description/&gt;<br>
  &lt;role-name&gt;user&lt;/role-name&gt;<br>
  &lt;/auth-constraint&gt;<br>
  &lt;/security-constraint&gt;<br></code>
<br>
 If the constraints are in place you have to define, how the container should challenge the user. 
<br><code><br>
  &lt;realm-name&gt;JDBCRealm&lt;/realm-name&gt;<br>
  &lt;form-login-config&gt;<br>
  &lt;form-login-page&gt;/faces/loginForm.xhtml&lt;/form-login-page&gt;<br>
  &lt;form-error-page&gt;/faces/loginError.xhtml&lt;/form-error-page&gt;<br>
  &lt;/form-login-config&gt;<br>
  &lt;/login-config&gt;<br></code>
<br>
 The realm name has to be the name that you assigned the security realm before. Close the web.xml and open the sun-web.xml to do a mapping from the application role-names to the actual groups that are in the database. This abstraction feels weird, but it has some reasons. It was introduced to have the option of mapping application roles to different group names in enterprises. I have never seen this used extensively but the feature is there and you have to configure it. Other appservers do make the assumption that if no mapping is present, role names and group names do match. GlassFish doesn't think so. Therefore you have to put the following into the sun-web.xml:
<br><code><br>
  &lt;security-role-mapping&gt;<br>
  &lt;role-name&gt;admin&lt;/role-name&gt;<br>
  &lt;group-name&gt;admin&lt;/group-name&gt;<br>
  &lt;/security-role-mapping&gt;<br>
  &lt;security-role-mapping&gt;<br>
  &lt;role-name&gt;user&lt;/role-name&gt;<br>
  &lt;group-name&gt;user&lt;/group-name&gt;<br>
  &lt;/security-role-mapping&gt;<br></code>
<br>
<br>
 If you now point your browser to <code>localhost:8080/jdbcrealm/</code> you will see the login screen. Enter your credentials and see what happens.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="login.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="73" src="login.png" width="400"></a>
</div>
<div class="separator" style="clear: both; text-align: center;">
 <a href="adminwelcome.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="63" src="adminwelcome.png" width="400"></a>
</div>
<br>
 That's it. By far the longest tutorial I did so far. Hope, you like it. Let me know, if you need more details or have questions. Thanks for reading!
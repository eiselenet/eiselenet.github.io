---
title: "JPA 2.0 and Oracle Weblogic 10.3.2.0 (11g)"
date: 2010-01-13 06:27:00 +0000
layout: post
tags: ["oracle", "jpa 2.0", "weblogic server"]
slug: "jpa-20-and-oracle-weblogic-10320-11g"
link: "2010/01/jpa-20-and-oracle-weblogic-10320-11g.html"
url: /2010/01/jpa-20-and-oracle-weblogic-10320-11g.html
---

Everything is about JEE 6 these days. And believe me, I really love the new spec. What I am really missing is the JEE 6 version of a Weblogic server. Anyway, I am still giving parts of the spec some tries on the latest version. Up to now, this was not too successfull. <a href="http://www.eisele.net/blog/2009/07/jsf-20-beta-1-on-oracle-weblogic-10gr3.html">JSF 2.0 is not working.</a> Next was to give JPA 2.0 a try. The guys from the EclipseLink project already did this for me. There is a complete analysis about the possible solutions online in the <a href="http://wiki.eclipse.org/EclipseLink/Development/JPA_2.0/weblogic" target="_blank">EclipseLink wiki space</a>. You basically have two differnt options available at the moment to use even parts of the new JPA 2.0.
<br>
<br>
 Change your server/domain install to use new versions of javax.persistence.* and org.eclipse.persistence.* or bundle the related libraries with your application. Both cases have some drawbacks at the moment. 
<br>
<br>
 I decided to give it a try anyway and just wanted to give you a more detailed explanation, of what to do to make the second approach work.
<br>
<br>
 First is to get the latest EclipseLink 2.x and the JPA 2.0 API libraries. I was using javax.persistence_2.0.0.v200911041116.jar and eclipselink.jar both taken from the eclipselink-2.0.0.v20091127-r5931.zip download.
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/project_structure-799706.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="200" src="http://www.eisele.net/blog/uploaded_images/project_structure-799704.png" width="200"></a> Now you have to setup an EAR and a WAR project within your favorite IDE. I am using the Oracle Enterprise Pack for Eclipse (OEPE) for this. Add both jars to the APP-INF/lib folder of your EAR project and change the weblogic-application.xml descriptor by adding the following lines to it:
<br><code><br>
  &lt;wls:prefer-application-packages&gt;<br>
  &lt;wls:package-name&gt;org.eclipse.persistence.*&lt;/wls:package-name&gt;<br>
  &lt;wls:package-name&gt;javax.persistence.*&lt;/wls:package-name&gt;<br>
  /wls:prefer-application-packages&gt;<br></code>
<br>
 Now you have to add a src/META-INF folder to your WAR project. Create a MANIFEST.MF file with the following two lines:
<br><code><br>
  Manifest-Version: 1.0<br>
  Class-Path: javax.persistence_2.0.0.v200911041116.jar eclipselink.jar<br></code>
<br>
 Place your persistence.xml in the same place. Mine looks like this:
<br><code><br>
  &lt;persistence version="1.0" xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_1_0.xsd"&gt;<br>
  &lt;persistence-unit name="example" transaction-type="JTA"&gt;<br>
  &lt;provider&gt;org.eclipse.persistence.jpa.PersistenceProvider&lt;/provider&gt;<br>
  &lt;jta-data-source&gt;ds/localJTA&lt;/jta-data-source&gt;<br>
  &lt;class&gt;...&lt;/class&gt;<br>
  &lt;properties&gt;<br>
  &lt;property name="eclipselink.target-server" value="WebLogic_10"/&gt;<br>
  &lt;property name="eclipselink.logging.level" value="FINEST"/&gt;<br>
  &lt;property name="eclipselink.ddl-generation" value="drop-and-create-tables" /&gt;<br>
  &lt;/properties&gt; <br>
  &lt;/persistence-unit&gt;<br>
  &lt;/persistence&gt;<br></code>
<br>
 This is basically everything. Now you can start to put your Entities and business in your webapplication. But remeber to:
<br>
 - add every Entity as a &lt;class&gt;...&lt;/class&gt; entry to your persistence.xml as dynamic class weaving will not work with this approach. 
<br>
 - Reorder the Java Build Path of your IDE to have the EAR libraries in front of any server libraries. Only this way, you will be able to use the new API features.
<br>
 - As the schema of the persistence.xml states, you are only able to use JPA 1.0 declarations in it. 
<br>
 - Injecting the EntityManager will not work. You have to get it manually from javax.persistence.Persistence like this:
<br><code><br>
  EntityManagerFactory emf = Persistence.createEntityManagerFactory("example");<br>
  EntityManager em = emf.createEntityManager();<br></code>
<br>
<br>
 After all, this is not a full blown approach to JPA 2.0. But better than nothing. Let's hope for a early preview of EclipseLink 2.0 on Weblogic Server.
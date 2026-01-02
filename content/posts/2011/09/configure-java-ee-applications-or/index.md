---
title: "Configure Java EE applications or \"Putting Bien into practice\" ;)"
date: 2011-09-08 07:23:00 +0000
layout: post
tags: ["CDI", "adam", "javaee6", "configuration"]
slug: "configure-java-ee-applications-or"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2011/09/configure-java-ee-applications-or.html
---

A lot has been talked about application configuration in the past. I don't know who kicked off the debate but the most fundamental reading (with a look at future Java EE 7 and beyond) is Antonio Goncalves' posting <a href="" target="_blank">[Debate] – And what about configuration in Java EE 7</a>. Fact is, with vanilla Java EE we all do application configuration day by day. Without having any special mechanism in place. Having seen <a href="http://www.adam-bien.com/roller/abien/entry/how_to_configure_java_ee" target="_blank">Adam's latest post from yesterday</a> I would like to share a slight add-on to it, which I feel could fit to most of the projects out there. 
<br>
<br><b>Why this post?</b>
<br>
 The basics shown by Adam are really smart. You simply 
<br>
<br>
<pre class="brush: java"> @Inject int yourConfigVariable; </pre>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG-20110908-00024.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="320" src="IMG-20110908-00024.jpg" width="240"></a>
</div> and you are done. You don't have to care about properties or other configuration classes. But looking into it, you see, that you somehow need to fill your configuration from somewhere. And looking back at Antonio's post, you see that you have a lot of options doing this. The one we are most comfortable with is probably Java's <a href="http://download.oracle.com/javase/7/docs/api/java/util/Properties.html" target="_blank">Properties</a> mechanism. Using this in combination with the code presented by Adam you end up having a Configuration.properties with an endless list of single word keys. That's not what I would call maintainable. So basically this is why the post has the title: "Putting Bien into&nbsp;practice" ..oO(sorry for that, Adam!) :-) Here are my approaches to the problem. 
<br>
<br><b>Fill your configuration from a properties file</b>
<br>
 The most basic part is to add a Configuration.properties file to your application (default package). Now we are going to modify the configuration holder a bit to make it a Properties type. Now modify Adam's fetchConfiguration() method to load it. 
<br>
<br>
<pre class="brush: java"> private Properties configData; @PostConstruct public void fetchConfiguration() \{ String fileName = "Configuration.properties"; configData = loadPropertiesFromClasspath(fileName); \} /** * Load properties file from classpath with Java 7 :-) * @param fileName * @return properties */ public static Properties loadPropertiesFromClasspath(String fileName) \{ try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream( fileName)) \{ if (in != null) \{ props = new Properties(); props.load(in); \} \} catch (IOException ioe) \{ log.debug("Can't load properties.", ioe); \} </pre>
<br>
 Now you have to modify the @Producer methods accordingly. I'm only showing the getString() method here to show you the concept:
<br>
<br>
<pre class="brush: java"> /** * Get a String property * @param point * @return String */ @Produces public String getString(InjectionPoint point) \{ String propertyPath = point.getMember().getDeclaringClass().getName()+ "."; String propertyName = point.getMember().getName(); String propertyValue = configData.getProperty(propertyPath+propertyName); return (propertyValue == null) ? "" : propertyValue; \} </pre> For convenience reasons I added the name of the declaring class as propertyPath in order to have a bit more order within your property file. You use the producer methods as Adam has shown: 
<br>
<pre class="brush: java"> package net.eisele.configuration; public class HitsFlushTimer \{ @Inject private String hitsFlushRate; \} </pre>
<br>
 In this case you end up accessing a property with the key net.eisele.configuration.HitsFlushTimer.hitsFlushRate in your Configuration.properties file. One quick warning. If it happens to you, that you have to package separate ejb and war modules within an ear you probably need the javax.annotation.security.PermitAll annotation at your Configuration singleton.
<br>
<br><b>Then you end up with lots of duplicates </b>
<br>
 That's probably true. If you have the same configuration over an over again (e.g. httpProxy) this would force you to have the same value for different keys in your property file. The solution seems straight forward. We need our own&nbsp;Qualifier&nbsp;for that. Let's go:
<br>
<pre class="brush: java"> @Retention(RUNTIME) @Target(\{FIELD, METHOD\}) @Qualifier public @interface AppProperty \{ @Nonbinding public String value(); \} </pre>
<br>
 Now we have our own Qualifier for that. Next is to change the @Producer accordingly:
<br>
<pre class="brush: java"> @Produces @AppProperty("") public String getString(InjectionPoint point) \{ String property = point.getAnnotated().getAnnotation(AppProperty.class).value(); String valueForFieldName = configData.getProperty(property); return (valueForFieldName == null) ? "" : valueForFieldName; \} </pre>
<br>
 That's it. Now you can use something like this wherever you like: 
<br>
<pre class="brush: java"> @Inject @AppProperty("net.eisele.configuration.test2") String test2; </pre>
<br>
 I know, this isn't half that elegant like Adam's one @Inject annotation. But:You don't have to guess a lot to see what's happening and where your value is coming from. I consider this a big pro in projects with more than one developer.
<br>
<br><b>Yeah. Still not very maintainable.</b>
<br>
 Ok. I know. You are still talking about refactoring property names. Right? What is left to do? You could think about using a CKey Enum which encapsulates all your property keys and use this instead of simply using the keys itself. But, I would prefer to simply use the plain String keys within my code. Happy configuring now. How are you configuring your applications? Let me know! Happy to receive comments :)
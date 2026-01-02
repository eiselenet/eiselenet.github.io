---
title: "Java Temporary Caching API - Test-driving the Early Draft Review RI"
date: 2012-10-25 07:15:00 +0000
layout: post
tags: ["JCache", "JSR107"]
slug: "java-temporary-caching-api-test-driving"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2012/10/java-temporary-caching-api-test-driving.html
---

It was known as "<a href="http://en.wikipedia.org/wiki/The_Neverending_Story" target="_blank">The Neverending Story</a>". The JSR kicked of 11 and a half year ago and passed the JSR <a href="http://jcp.org/en/jsr/detail?id=107" target="_blank">Review Ballot on 06 Mar, 2001</a>. If you ever wondered what it takes to get a fancy low JSR number in the hundreds: That is the secret. Unlike in the German fantasy novel by Michael Ende this was not about people's lack of imagination but about resources, political discussions and finally about licensing. But let's forget about the past and move to what is there since yesterday. Note that this material was uploaded to the JCP in February but was delayed while the legal complications of having two companies as shared spec leads got sorted out. That is done and will not be an issue going forward in the process.
<br>
<br><b>What is it all about?</b>
<br>
 Caching is known for dramatically speeding up applications. Those typically use temporary data which is expensive to create but has a long lifetime during which it can be re-used. This specification standardizes caching of Java objects in a way that allows an efficient implementation, and removes from the programmer the burden of implementing cache expiration, mutual exclusion, spooling, and cache consistency. 
<br>
 It is designed to work with both Java SE and Java EE. For the later it still is not ensured, that it will be included in upcoming EE 7 release but the EG is working hard on it and needs your feedback.
<br>
<br><b>How do I get my hands on it?</b>
<br>
 That is easy. All the needed artifacts are in maven central already. Let's build a very simple sample for you to get you started. Fire up NetBeans and create a new Maven Java Application. Name it whatever you like (e.g. cachingdemo, open the pom.xml and add the following two dependencies to it: 
<br>
<pre class="brush:xml">&lt;dependency&gt; &lt;groupId&gt;javax.cache&lt;/groupId&gt; &lt;artifactId&gt;cache-api&lt;/artifactId&gt; &lt;version&gt;0.5&lt;/version&gt; &lt;/dependency&gt; &lt;dependency&gt; &lt;groupId&gt;javax.cache.implementation&lt;/groupId&gt; &lt;artifactId&gt;cache-ri-impl&lt;/artifactId&gt; &lt;version&gt;0.5&lt;/version&gt; &lt;/dependency&gt; </pre> And if you are there, change the junit version to 4.8.2. 
<br>
 Refactor the AppTest to utilize the new junit: 
<br>
<pre class="brush:java">package net.eisele.samples.cachingdemo; import org.junit.Test; /** * Simple Cache Test */ public class AppTest \{ @Test public void testApp() \{ \} \} </pre> All set. To make this simple, I'm going to add some caching features in the test-case.
<br>
<br><b>The Basic Concepts</b>
<br>
 From a design point of view, the basic concepts are a CacheManager that holds and controls a collection of Caches. Caches have entries. The basic API can be thought of map-like. Like a map, data is stored as values by key. You can put values, get values and remove values. But it does not have high network cost map-like methods such as keySet() and values(). And generally it prefers zero or low cost return types. So while Map has V put(K key, V value) javax.cache.Cache has void put(K key, V value).
<br>
<pre class="brush:java"> // Name for the cache String cacheName = "myfearsCache"; // Create a cache using a CacheBuilder Cache&lt;Integer, String&gt; cache = Caching.getCacheManager().&lt;Integer, String&gt;createCacheBuilder(cacheName).build(); // define a value String value1 = "Markus"; // define a key Integer key = 1; //put to the cache cache.put(key, value1); // get from the cache String value2 = cache.get(key); //compare values assertEquals(value1, value2); // remove from the cache cache.remove(key); // ceck if removed assertNull(cache.get(key)); </pre>
<br><b>Things to come</b>
<br>
 This basically is all that is possible at the moment. Going down the road with subsequent releases you should be able to:
<br>
 - Integrate with Spring and CDI via @Annotations
<br>
 - Use CacheEventListener
<br>
 - Work with Transactions
<br>
 The EG is actively searching for feedback on the available stuff. So, if you can get your hands on it, give it a try and let the EG know what you think!
<br>
<br><b>Links and Reading</b>
<br>
 JCP page: <a href="http://jcp.org/en/jsr/detail?id=107" target="_blank">JSR 107: JCACHE - Java Temporary Caching API</a>
<br>
 Group Mailing List <a href="http://groups.google.com/group/jsr107" target="_blank">http://groups.google.com/group/jsr107</a>
<br>
 Log issues in the Issue Tracker <a href="https://github.com/jsr107/jsr107spec/issues" target="_blank">https://github.com/jsr107/jsr107spec/issues</a>
<br>
 A very simple demo <a href="https://github.com/jsr107/demo" target="_blank">https://github.com/jsr107/demo</a>
<br>
 ehcache-jcache - an implementation of the 0.5 specification <a href="https://github.com/jsr107/ehcache-jcache" target="_blank">https://github.com/jsr107/ehcache-jcache</a>
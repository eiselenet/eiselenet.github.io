---
title: "GlassFish 3.1 SecureJDBCRealm - Detecting failed logins."
date: 2011-05-11 11:50:00 +0000
layout: post
tags: ["glassfish", "security"]
slug: "2011-05-11-glassfish-31-securejdbcrealm-detecting"
url: /2011/05/glassfish-31-securejdbcrealm-detecting.html
---

Playing around with security in GlassFish is a lot of fun. If you have a project and some kind of security organization in place and you have to implement stuff, that&nbsp;fulfills&nbsp;requirements regarding high or higher protection needs, you most often are alone with that. This is a short howto about what I did to extend the existing JDBCRealm a bit to keep track of failed logins and deactivate users after they reached their configured limit.
<br>
<br><b>What's there and what did I do?</b>
<br>
 You probably know the <a href="http://blogs.oracle.com/swchan/entry/jdbcrealm_in_glassfish">GlassFish JDBC Realm</a> already. It's a quite&nbsp;comfortable&nbsp;way to use a database as a user&nbsp;back-end&nbsp;for your container security. If you <a href="http://download.oracle.com/javaee/6/tutorial/doc/bncat.html">configure your&nbsp;web application&nbsp;appropriate</a> your can ensure, that only valid users hit your protected resources. The only thing you have to do is to configure your JDBCRealm and off you go. If you are not familiar with the topic so far, I suggest, you dig into the <a href="http://download.oracle.com/javaee/6/tutorial/doc/bnbxj.html">Java EE 6 tutorial</a> a bit to get a basic understanding.
<br>
 So far so good. But what happens in an environment where you have to take care of additional protection needs? Some typical ones? Encrypted passwords in your database. You could&nbsp;achieve&nbsp;this with setting a digest-algorithm (MessageDigest: e.g. MD5) and off you go. What about tracking failed logins? Here we go. That's most obviously nothing the standard JDBCRealm does. So, I was trying to build a more&nbsp;SecureJDBCRealm and add this feature.
<br>
<br><b>Starting point</b>
<br>
 If I already have an example, I am going to add my stuff there. So I started over looking at the code already there. The <a href="https://fisheye4.atlassian.com/browse/glassfish-svn/tags/3.1-b43/security/core/src/main/java/com/sun/enterprise/security/auth/realm/jdbc/JDBCRealm.java">com.sun.enterprise.security.auth.realm.JDBCRealm</a> is an excellent place to look at.
<br>
 So I basically copied it (with remaining copyright-headers of course ;)) and added my own features. Beside the fact, that you need at last two more properties for your realm, you need some additional prepared statements to execute in order to be able to keep track of the tries a user did. And you also do need your own SecureJDBCLoginModule to call your realm.
<br>
 The steps I took in a very high-level view:
<br>
 0) Add two more params to the realm (user-tries-column and user-tries-max)
<br>
 1) change the passwordQuery to include the new "sanity-check" for userTriesColumn + " &lt;=" + userTriesMax
<br>
 2) Add three new prepared statements triesReadQuery, triesUpdateQuery, triesResetQuery
<br>
 3) Change the isUserValid() method to include the test if more tries are available for a given username and add some logic for handline the tries colum (increment and reset)
<br>
 4) I added a new public property to the realm to be able to getTriesLeft() for a given user.
<br>
 5) implement the SecureJDBCLoginModule
<br>
<br>
 Great. That basically was it. Now you have a new column mapping in your login module to track the not successful login attempts a user does. To be honest, this is not high performance in general. And using the programmatic request.login() with it's simple ServletException that is thrown is not very convenient. So even if you use this login module, you still have to find a way to tell the user about it's left tries and what he possibly did wrong. 
<br>
<br><b>Try it out - and give feedback!</b>
<br>
 If you are willing to try it out: Here you are. I made the complete maven based project (GF 3.1) available for you <a href="https://github.com/myfear/SecureJDBCRealm" target="_blank">on github</a>. Use it as it is. Without any warranty. And don't blame me, if something goes wrong. If you have ideas or better approaches: let me know! Happy to discuss this a bit!
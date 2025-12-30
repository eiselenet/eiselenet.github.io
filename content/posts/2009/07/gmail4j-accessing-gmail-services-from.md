---
title: "Gmail4J - accessing Gmail services from Java"
date: 2009-07-14 06:51:00 +0000
layout: post
tags: ["gmail", "google", "Gmail4J", "example"]
slug: "2009-07-14-gmail4j-accessing-gmail-services-from"
url: /2009/07/gmail4j-accessing-gmail-services-from.html
---

Gmail4J is a simple, object oriented library for accessing Gmail services from Java. The actual build version 0.3 is available under a Apache License 2.0 from <a href="" target="_blank">code.google.com</a>.
<br>
<br>
You can use it with maven (Java.net Repository):
<br>
<blockquote>
 <br>
 &lt;dependency&gt;
 <br>
  &lt;groupId&gt;com.googlecode&lt;/groupId&gt;
 <br>
  &lt;artifactId&gt;gmail4j&lt;/artifactId&gt;
 <br>
  &lt;version&gt;0.3&lt;/version&gt;
 <br>
 &lt;/dependency&gt;
 <br>
</blockquote>
<br>
<br>
Some example code? Get unread Messages from your account (through proxy):
<br>
<blockquote>
 <br>
 GmailClient client = new RssGmailClient();
 <br>
 GmailConnection connection = new HttpGmailConnection(LoginDialog.getInstance().show("Enter Gmail Login"));
 <br>
 connection.setProxy("proxy.example.com", 8080);
 <br>
 client.setConnection(connection);
 <br>
 final List<gmailmessage>
  messages = client.getUnreadMessages();
  <br>
  for (GmailMessage message : messages) \{
  <br>
   System.out.println(message);
  <br>
  \}
  <br>
 </gmailmessage>
</blockquote>
<br>
<br>
Looks like this:
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/gmail-715074.png"><img style="display:block; margin:0px auto 10px; text-align:center;cursor:pointer; cursor:hand;width: 200px; height: 92px;" src="http://www.eisele.net/blog/uploaded_images/gmail-715072.png" border="0" alt=""></a>
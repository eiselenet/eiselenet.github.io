---
title: "Log4J Twitter Appender - twitter your logmessages :)"
date: 2009-10-30 08:22:00 +0000
layout: post
tags: ["twitter", "appender", "java", "log4j"]
slug: "log4j-twitter-appender-twitter-your"

url: /2009/10/log4j-twitter-appender-twitter-your.html
---

Don't ask me why, but I was thinking about a "twittering" WebLogic for some days now. And I finaly managed to spend some minutes on this today. This is the first approach. Have some more in mind and would like to contribute them, too.
<br>
<br>
 Let's start over with the <b>Log4JTwitterAppender</b>:
<br>
 You can <a href="http://www.eisele.net/jar/l4jtwitterappender.jar">download the jar</a> directly. Anything else you need is a recent version of Log4J and JTwitter. I used <a href="" target="_blank">Log4J 1.2.15</a> and <a href="http://www.winterwell.com/software/jtwitter.php" target="_blank">JTwitter 1.2</a>. Now drop all three jars log4j-1.2.15.jar, jtwitter.jar and l4jtwitterappender.jar to your project and add a log4j.properties file to it.
<br>
<br>
 A sample log4j.properties looks like this:
<br><code><br>
  # Add the twitter appender<br>
  log4j.rootLogger=DEBUG, TWITTER<br>
  log4j.appender.TWITTER=<br>
  net.eisele.log4j.twitterappender.TwitterAppender<br>
  log4j.appender.TWITTER.Username=<br>
  &gt;your_twitter_username&lt;<br>
  log4j.appender.TWITTER.Password=<br>
  &gt;your_twitter_password&lt;<br>
  log4j.appender.TWITTER.ProxyPort=<br>
  &gt;proxy_port&lt;<br>
  log4j.appender.TWITTER.ProxyHost=<br>
  &gt;proxy_host&lt;<br>
  log4j.appender.TWITTER.layout=<br>
  org.apache.log4j.PatternLayout<br>
  log4j.appender.TWITTER.layout.ConversionPattern=<br>
  [%d\{MMM dd HH:mm:ss\}] %-5p (%.20F) - %.49m%n<br></code>
<br>
<br>
 If everything is configured, you can start logging:
<br>
<br><code><br>
  private static final Logger log = <br>
  Logger.getLogger("your.Clazzname");<br><br>
  log.debug("your log message");<br></code>
<br>
<br>
 If everything works fine, you now have a logging twitter account :)
<br>
<br><a href="http://www.eisele.net/blog/uploaded_images/twittermessage-784915.png" imageanchor="1" style="clear: left; float: left; margin-bottom: 1em; margin-right: 1em;"><img border="0" height="38" src="http://www.eisele.net/blog/uploaded_images/twittermessage-784914.png" width="320"></a>
<br>
<br>
 You can configure the layout if you like with the normal <a href="http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/PatternLayout.html" target="_blank">PatternLayout</a> machanism. But to be honest ... 140 characters are hardly enough for log messages :) 
<br>
 Anyway, it was fun doing it ...
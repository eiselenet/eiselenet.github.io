---
title: "Bleeding edge - First steps with OpenJDK 7 Build b115"
date: 2010-10-27 06:10:00 +0000
layout: post
tags: ["java SE 7", "OpenJDK 7"]
slug: "bleeding-edge-first-steps-with-openjdk"

url: /2010/10/bleeding-edge-first-steps-with-openjdk.html
---

Here it is. The guide, to making your first steps into the next version of Java we all are waiting for. Let all the others talk about politics. We are going to test drive what's there today. Prepare for some exciting times.
<br>
<br><b>Preface</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="openjdk.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="54" src="openjdk.png" width="200"></a>
</div>This is not a true beginners topic. I'll guide you through installing the binary builds and give some very brief hints at building your own OpenJDK7 version first. After that, we are going to take a look at some of the new features which are already working. But there are a couple of things, you should be aware of:
<br>
 1) We are working with a development build. Expect this to be anything but mature, production ready or even stable. If you go on reading, you agree on&nbsp;being&nbsp;a geek willing to work with bleeding edge stuff. There is no other help from now on than yourself. Don't bother anyone of the OpenJDK developers with beginners questions. They are hard at work providing the complete set. 
<br>
 2) You should know how to write and compile Java classes without any IDE support. Non of the Eclipse or NetBeans versions were too happy about having a JDK 7 on board and they know nothing about it today. So you need to get yourself an editor and a cmd-line. 
<br>
<br><b>Download and install</b>
<br>
 If you are not willing to build&nbsp;everything&nbsp;yourself, you also could download a <a href="http://dlc.sun.com.edgesuite.net/jdk7/binaries/index.html" target="_blank">binary snapshot release</a>. It's far easier to install this one. The list offers files for different platforms - please be sure to select the proper file(s) for your platform. Carefully review the files listed there to select the ones you want, then click the link(s) to download. The DEBUG builds have not been run through ANY quality procedures, and are only provided as a potential way to track down a bug in the more official product bits. There is no guarantee that the debug builds even will work. For a Windows Offline Installation, Multi-language JDK installation, you need the 86.22 MB <a href="http://www.java.net/download/jdk7/binaries/jdk-7-ea-bin-b115-windows-i586-21_oct_2010.exe">jdk-7-ea-bin-b115-windows-i586-21_oct_2010.exe</a>. It's installation is exactly what you know from previous versions. You have a couple of wizard screens. Follow them and you are done. It's better not to install the Public JRE! You are not willing to have OpenJDK7 in your browsers as the default JRE at the moment.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="jdk7_install.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="254" src="jdk7_install.png" width="320"></a>
</div>
<br><b>Download and build</b>
<br>
 If you are willing to build the OpenJDK 7 yourself. There are a couple of things you need:
<br>
 - The&nbsp;<a href="" target="_blank">source downloads</a> for the <a href="" target="_blank"> OpenJDK Project.</a>&nbsp;You could also grep the sources from the <a href="http://openjdk.org/guide/repositories.html" target="_blank">repositories</a>.
<br>
 - Some binary plugins. Also available from the source downloads page. 
<br>
 - Things listed at <a href="http://hg.openjdk.java.net/jdk7/build/raw-file/tip/README-builds.html#MBE" target="_blank">Minimum Build Environments</a>
<br>
 - GNU make
<br>
 There is a very complete <a href="http://hg.openjdk.java.net/jdk7/build/raw-file/tip/README-builds.html" target="_blank">OpenJDK Build README</a> out there, where to find more detailed explanations of what to do. If you are unhappy with it or you don't understand something. Don't try it. There are binary builds available. 
<br>
<br><b>First steps</b>
<br>
 Check, if your installation is valid. Switch to your %JDK7%\bin folder and type java -version.
<br>
<pre>java version "1.7.0-ea" Java(TM) SE Runtime Environment (build 1.7.0-ea-b115) Java HotSpot(TM) Client VM (build 20.0-b02, mixed mode, sharing) </pre>If you are willing to give it a try, you should put the %JDK7%\bin folder to your path variable.
<br>
 Seems as if you are set. Next is to fire up some first tests. Make yourself familiar with the <a href="http://blog.eisele.net/2010/10/java-se-7-new-features-in-detail.html">new features</a> and give them a try. A good place to start is to look at Arul's post <a href="" target="_blank">Rest of Project Coin explored, advantage Java 7</a>. I tried the Strings in switch feature. I have waited for this too long :) Taken from Joe Darcy's Project Coin JavaOne presentation and adapted from Arul's blog. It takes a month name and a year as input and returns the days of the month in that year. Take your favorite editor and put it in (Classname and Package, as you like it). 
<br>
<pre>int days = monthNameToDays("February", 2008); public int monthNameToDays(String s, int year) \{ GregorianCalendar c = new GregorianCalendar(); switch (s) \{ case "April": case "June": case "September": case "November": return 30; case "January": case "March": case "May": case "July": case "August": case "December": return 31; case "February": int days = 28; days += c.isLeapYear(year) ? 1 : 0; return days; default: return -1; \} \} </pre>
<br>
 And, as usual, compile the class and run it. For February, 2008 it returns 29 days. As expected. Great stuff! If you are looking for more advanced examples, you have to dig deeper. The new I/O API is probably a great place to look at. There is a quite <a href="http://today.java.net/pub/a/today/2008/07/03/jsr-203-new-file-apis.html" target="_blank">old article (2008)</a> about the new features on java.net. 
<br>
 Very short background: What used to be a java.io.File will now become a java.nio.file.Path. This is a more accurate name, since there was never any guarantee that a File object mapped to a real file. Also, paths can refer to both files and directories. The Path class is abstract and has no constructors. Instead, you'll ask a FileSystem object to create a path for you. This way, it can create a path that's specific to the type of file system.
<br>
<pre>import java.nio.file.*; public class NioTest \{ public static void main(String... args) \{ FileSystem local = FileSystems.getDefault(); Path p = local.getPath("bar.txt"); String url = p.toAbsolutePath().toUri().toString(); System.out.println("Path: " + p.toString()); System.out.println("Url: " + url); \} \} </pre>
<br>
 If I run it on my system I get what I expected:
<br>
<pre>E:\OpenJDK7Tests\src&gt;java NioTest Path: bar.txt Url: file:///E:/OpenJDK7Tests/src/bar.txt </pre>
<br><b>Further readings</b>
<br>
 This really was not everything waiting for you. You should make yourself familiar with all the new things to come. To be honest, it's not too easy to keep up with what's becoming Java SE 7 at the moment. As you might have heard, there are a couple of political things around which lead to many not technical search results, if you are googling for JDK7. The best place to start looking for further information is the <a href="" target="_blank">openjdk.org homepage</a>. If you are looking for a blog to subscribe to, look at the <a href="" target="_blank">openJDK News blog</a> or follow <a href="http://twitter.com/#%21/openjdk" target="_blank">@OpenJDK</a> on twitter. Next place to look are the <a href="http://mail.openjdk.java.net/mailman/listinfo" target="_blank">mailing lists</a>. Sad to say, that every project has it's own. If you are looking for some more advanced stuff, you can also have a look at <a href="" target="_blank">Arul Dhesiaseelan's blog</a>. He is looking into what's coming up for Java SE 8 (Lambdas, Rest of Coin ...). The offical Java Tutorials also start pushing content for JDK7 out. The Concurrency lesson now includes a chapter about the new <a href="http://download.oracle.com/javase/tutorial/essential/concurrency/forkjoin.html" target="_blank">Fork/Join</a> framework. Also the <a href="http://java.sun.com/docs/books/tutorial/essential/io/file.html" target="_blank">NIO 2.0 parts</a> are already featured. A short overview about the <a href="" target="_blank">SDP (Sockets Direct Protocol)</a> is also available. But be aware, that parts are still moving around. We are all waiting for the Java SE 7 JSR's to be filed and we will see the Expert Group (EG) moving around things, too. So, for the last time a friendly reminder not to take anything too serious at the very moment. 
<br>
 If I could make a Christmas wish, I would love to see some more Java SE 7/OpenJDK7 content coming up. <a href="http://twitter.com/#%21/java/status/28424639372" target="_blank">@Java told me, that Santa heard my wish</a> :) Let's look forward!
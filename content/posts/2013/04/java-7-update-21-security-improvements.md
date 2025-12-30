---
title: "Java 7 Update 21 Security Improvements in Detail"
date: 2013-04-17 08:01:00 +0000
layout: post
tags: ["cpu", "security", "java"]
slug: "2013-04-17-java-7-update-21-security-improvements"
url: /2013/04/java-7-update-21-security-improvements.html
---

Oracle released <a href="https://blogs.oracle.com/java/entry/java_se_7_update_21" target="_blank">three updates to Java</a> yesterday. It is important to note that they contain several security related changes. The majority of those changes have been announced since a while and first thing to notice is, that Oracle ships as planned.
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="http://parleys.com/play/5167cff7e4b07b9cc6de3259/chapter28/about" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;" target="_blank"><img border="0" height="188" src="Java_cpu.PNG" width="320"></a>
</div> Oracle's Java Platform Security Manager <a href="https://twitter.com/spoofzu" target="_blank">Milton Smith</a> recently gave a talk at DevoxxUK titled "<a href="http://parleys.com/p/5167cff7e4b07b9cc6de3259">Securing the Future with Java</a>" where he explains the overall process of handling security within Oracle's products and also gave an overview about the Java CPUs. Those 4-monthly updates cover all Java families and according to this we also see a&nbsp;Java SE 6 Update 45 and a new Java SE Embedded 7 Update 21 since yesterday which addresses the same issues on that&nbsp;families.
<br>
<br><b>What is in it for me? (I'm an end-user)</b>
<br>
 The <a href="http://www.oracle.com/technetwork/java/javase/7u21-relnotes-1932873.html" target="_blank">release notes list</a> a couple of new things. Most of them are targeted at a more secure Java runtime for end-users. Starting with the most important change, the low and custom settings on the&nbsp;Java Control Panel(JCP)'s Security Slider&nbsp;are now removed. That basically mean that unsigned applets don't run without warning anymore. Further on, depending on the security level set in the Java Control Panel and the user's version of the JRE, self-signed or unsigned applications might not be allowed to run at all. The default setting of High permits all but local applets to run on a secure JRE. If the user is running an insecure JRE (&lt;7), only applications that are signed with a certificate issued by a recognized certificate authority are allowed to run. 
<br>
<table cellpadding="0" cellspacing="0" class="tr-caption-container" style="float: right; margin-left: 1em; text-align: right;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="http://java.com/en/download/help/appsecuritydialogs.xml" imageanchor="1" style="clear: right; margin-bottom: 1em; margin-left: auto; margin-right: auto;" target="_blank"><img border="0" height="194" src="trusted_signed.jpg" width="320"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;">Trusted and signed (Source: <a href="http://java.com/en/download/help/appsecuritydialogs.xml" target="_blank">Oracle</a>)</td>
  </tr>
 </tbody>
</table> As a brief guideline you, the end-user now see two different kind of Java warning messages in your browser after (!) you already agreed to starting the active content for which your browser typically asks you, too. All blue and Java is good and valid content (compare picture to the right "trusted and signed").&nbsp;Applications of this type are typically low risk, but Oracle advises you to check that the app name, publisher name and location make sense given the site you are visiting (i.e. Java Detection, Oracle America, http://www.java.com while on java.com). It is recommend you hit Cancel if any of this information does not match or doesn't make sense to you at all.
<br>
<table cellpadding="0" cellspacing="0" class="tr-caption-container" style="float: right; margin-left: 1em; text-align: right;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="http://java.com/en/download/help/appsecuritydialogs.xml" imageanchor="1" style="clear: right; margin-bottom: 1em; margin-left: auto; margin-right: auto;" target="_blank"><img border="0" height="186" src="unsigned_cert.jpg" width="320"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;">Unsigned (Source: <a href="http://java.com/en/download/help/appsecuritydialogs.xml" target="_blank">Oracle</a>)</td>
  </tr>
 </tbody>
</table> If you come across an unsigned or not validly signed application things start to get yellow and red (compare picture "Unsigned"). To make it short, you most likely are willing to revise your decision about executing this kind of applications. Even if there are different levels of potential security risks compared to self signed applications (Sandbox vs. Full Access) <b>you should cancel and don't run any application that generate a yellow/red warning for you!</b> If you're interested in the complete story you can dive into more details about "<a href="http://java.com/en/download/help/appsecuritydialogs.xml" target="_blank">Java Security Prompts</a>".
<br>
<br>
 Beside this very prominent change Oracle also introduced a central&nbsp;certificate and jar blacklist repository.&nbsp;This data is updated on client computers daily on the first execution of a Java applet or web start application. With this change your JRE is now able to call home and get the most up to date information about potentially bad certificates and 4th-party jars out there. Oracle didn't disclose anything about the process behind it but I guess, that this mechanism will be used to block a majority (all) of the know exploit kits completely.
<br>
<br>
 You get plenty of security fixes! There is a complete list available and it is called the "<a href="http://www.oracle.com/technetwork/topics/security/javacpuapr2013-1928497.html" target="_blank">Oracle Java SE Risk Matrix</a>".&nbsp;This Critical Patch Update contains 42 new security fixes for Oracle Java SE. 39 of these vulnerabilities may be remotely exploitable without authentication, i.e., may be exploited over a network without the need for a username and password. 
<br>
<br>
 If you haven't been&nbsp;prompted&nbsp;to update you should do this as soon as possible. Download the JRE for your system from <a href="" target="_blank">java.com</a> and be up-to-date!
<br>
<br><b>What is in it for me? (I'm a developer)</b>
<br>
 If you're working with Applets, JavaFX Applications in the browser or with Java WebStart applications you might want to change your development process to support signed applications. With the introduced changes it is most likely that no end-user is able to run your application when they are either self-signed or unsigned. For a detailed overview about what to do refer to the <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/plugin/developer_guide/rsa_signing.html" target="_blank">signing applets guide in the Java tutorials</a>.
<br>
 We now have a <a href="http://docs.oracle.com/javase/7/docs/webnotes/install/index.html" target="_blank">server JRE</a>.&nbsp;&nbsp;If you need the JRE on a server and do not want the ability to run RIAs, <a href="http://www.oracle.com/technetwork/java/javase/downloads/server-jre7-downloads-1931105.html" target="_blank">download the Java SE Server JRE</a>. This version of the Java SE Server JRE does not include the Java plug-in or Java Web Start support, additional tools might be removed from future versions.
<br>
 Some&nbsp;behavior&nbsp;changes have been introduced also. The RMI property java.rmi.server.useCodebaseOnly is set to true by default. This might lead to broken RMI-based applications. Watch out for a stack trace that contains a java.rmi.UnmarshalException containing a nested java.lang.ClassNotFoundException.
<br>
 On Windows platform, the decoding of command strings specified to Runtime.exec(String), Runtime.exec(String,String[]) and Runtime.exec(String,String[],File) methods, has been improved to follow the specification more closely. This may cause problems for applications that are using one or more of these methods with commands that contain spaces in the program name, or are invoking these methods with commands that are not quoted correctly.
<br>
<br><b>More Information</b>
<br>
 There is plenty of coverage in the <a href="http://www.oracle.com/technetwork/java/javase/downloads/index.html" target="_blank">official documentation</a> for developers. The end-user documentation is also improving very quickly and a good place to start is <a href="" target="_blank">java.com</a>. For my German reads you can also find a more detailed coverage on <a href="http://heise.de/-1840305" target="_blank">heise.de/developer</a>.
---
title: "Using JavaMail with Weblogic Server"
date: 2009-08-19 06:06:00 +0000
layout: post
tags: ["Java EE", "java", "jee", "mail", "weblogic server"]
slug: "using-javamail-with-weblogic-server"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2009/08/using-javamail-with-weblogic-server.html
---

WebLogic Server includes the JavaMail API version 1.3. Using the JavaMail API, you can add email capabilities to your enterprise java applications. It provides access to Internet Message Access Protocol (IMAP)- and Simple Mail Transfer Protocol (SMTP).
<br>
<br>
JavaMail depends on configuration files that define the mail transport capabilities of the system. The weblogic.jar file contains the standard configuration files from Sun, which enable IMAP and SMTP mail servers for JavaMail and define the default message types JavaMail can process. If you do want to extend JavaMail, <a href="" target="_blank">download JavaMail</a> from Sun and <a href="http://java.sun.com/products/javamail/FAQ.html" target="_blank">follow Sun’s instructions</a> for adding your extensions. Then add your extended JavaMail package in the WebLogic Server classpath in front of weblogic.jar.
<br>
<br>
First thing to do is to configure a JavaMail Session in WebLogic Server. This allows server-side modules and applications to access JavaMail services with JNDI, using Session properties you preconfigure for them (e.g. mail hosts, transport and store protocols, and the default mail user). Applications that are heavy email users benefit because the mail session creates a single javax.mail.Session object and makes it available via JNDI to any module that needs it. 
<br>
<br>
You can override any properties set in the mail session in your code by creating a java.util.Properties object containing the properties you want to override. Then, after you look up the mail session object in JNDI, call the Session.getInstance() method with your Properties object to get a customized Session. 
<br>
<br>
To create a mail session in weblogic admin console:
<br>
<ol>
 <br>
 <li>In the Administration Console, expand <span style="font-weight:bold;">Services </span>and select <span style="font-weight:bold;">Mail Sessions</span>.</li>
 <br>
 <li>Click the <span style="font-weight:bold;">New </span>button.</li>
 <br>
 <li>Enter a name for your mail session and click <span style="font-weight:bold;">OK</span>.</li>
 <br>
 <li>On the Mail Sessions page, click the new mail session name.</li>
 <br>
 <li>On the Mail Sessions: Configuration page, in <span style="font-weight:bold;">JNDI Name</span>, enter a unique JNDI name.</li>
 <br>
 <li>In <span style="font-weight:bold;">Properties</span>, specify information for connecting to an existing mail server.</li>
 <br>
 <li>save your changes</li>
 <br>
</ol>
<br>
<br>
A simple example of sending a message with java mail:
<br><code><br>
 // Look up the mail session<br>
 InitialContext ic = new InitialContext();<br>
 Session session = (Session) <br>
  ic.lookup("sampleMailSession");<br><br>
 // Construct a MimeMessage. <br><br>
 String to = "some@adress.net";<br>
 String subject = "some example subject";<br>
 String messageTxt = <br>
  "Some example message body text";<br><br>
 Message msg = new MimeMessage(session);<br>
 msg.setFrom();<br>
 msg.setRecipients(<br>
  Message.RecipientType.TO, <br>
  InternetAddress.parse(to, false));<br>
 msg.setSubject(subject);<br>
 msg.setSentDate(new Date());<br>
 // Content is stored in a MIME <br>
 // multi-part message<br>
 // with one body part<br>
 MimeBodyPart mbp = new MimeBodyPart();<br>
 mbp.setText(messageTxt);<br><br>
 Multipart mp = new MimeMultipart();<br>
 mp.addBodyPart(mbp);<br>
 msg.setContent(mp);<br><br>
 //Send the message<br>
 Transport.send(msg);<br><br></code>
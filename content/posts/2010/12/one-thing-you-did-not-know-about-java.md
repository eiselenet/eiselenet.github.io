---
title: "One thing you did not know about Java EE class loading in GlassFish 2.x"
date: 2010-12-15 10:10:00 +0000
layout: post
tags: ["migration", "class-loading", "glassfish"]
slug: "one-thing-you-did-not-know-about-java"
link: "2010/12/one-thing-you-did-not-know-about-java.html"
url: /2010/12/one-thing-you-did-not-know-about-java.html
---

As you might have read, I am working on a couple of WebLogic to GlassFish migration projects occasionally. Most of them are WLS 10.0 to GlassFish 2.1.1 and therefore stick to the Java EE 5 specification without removing or adding anything. A couple of projects failed to pass the regression tests on GlassFish because of a class loading issue I was not aware of. So, if you are not willing to make the same mistake, read on ...
<br>
<br><b>Class Loader Hierarchy: What I knew.</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="http://docs.sun.com/app/docs/doc/821-0181/beade?l=en&amp;a=view" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;" target="_blank"><img border="0" height="320" src="http://docs.sun.com/source/821-0181/images/dgdeploy2.gif" width="242"></a>
</div>Class loaders in the Enterprise Server runtime follow a delegation hierarchy. Note that the class loader hierarchy is not a Java inheritance hierarchy, but a delegation hierarchy. In the delegation design, a class loader delegates classloading to its parent before attempting to load a class itself. A class loader parent can be either the System class loader or another custom class loader. If the parent class loader cannot load a class, the class loader attempts to load the class itself. In effect, a class loader is responsible for loading only the classes not available to the parent. This is true for most application servers out there. Transfered into practical experiences this should tell you, that you have a great example if you look at two web application archives packaged in an ear. Non of the two should have access to the classes loaded by the other class loader. This is, what I believed to be the single truth out there. And the image to the right (taken from <a href="http://docs.sun.com/app/docs/doc/821-0181/beade?l=en&amp;a=view" target="_blank">Sun GlassFish Enterprise Server v2.1.1 Developer's Guide</a>) seems to support this. You can also compare a little bit smaller documentation without the image in the latest <a href="http://docs.sun.com/app/docs/doc/821-1752/beadf?l=en&amp;a=view" target="_blank">Oracle GlassFish Server 3.0.1 Application Development Guide</a>. On the first read, both seem to tell me the same.... I have a strong class loader hierarchy and one war can not access the classes of the other.
<br>
<br><b>The test case </b>
<br>
 Ok. Here we go. Build a simple maven project. Two wars in one ear.
<br>
<pre>[INFO] [dependency:tree \{execution: default-cli\}] [INFO] net.eisele:ear:ear:1.0-SNAPSHOT [INFO] +- net.eisele:web1:war:1.0-SNAPSHOT:compile [INFO] +- net.eisele:web2:war:1.0-SNAPSHOT:compile [INFO] +- javaee:javaee-api:jar:5:provided </pre>Put a simple Servlet into war1 and a simple bean with a simple getName() which returns a String into War2. Now access the bean from your Servlet (you need to trick around a bit to get this to work. If anything else fails, put the bean into War1 also and remove it from the war afterwards). Now deploy it to GlassFish 2.x and start being amazed. It works. Even if it should not according to the documented class loading mechanisms. 
<br>
 If you switch over to GlassFish 3.x you get the expected behavior. The class loading from here on is save and you have separate class loaders (Archive class loader) for every module in your application and the test case fails :) Thank god!
<br>
<br><b>The Spec</b>
<br>
 Now you are wondering, what the specification tells you about this. Look at the latest javaee_platform-6_0-fr-spec.pdf and read:
<br>
<blockquote>
 Components in the web container may have access to the following classes and resources. [...]
 <br>
  The classes and resources accessible to any other web modules included in the same ear file, as described above.
</blockquote>
<br><b>Bottom line</b>
<br>
 Wow. That was unexpected. In fact, I did expect something else. Now we all are a bit wiser. Be careful migrating apps from one app server to the other. And don't expect to have a strong hierarchical class loader in place. That is especially true for GF 2.x class loading.
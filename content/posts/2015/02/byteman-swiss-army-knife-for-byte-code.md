---
title: "Byteman - a swiss army knife for byte code manipulation"
date: 2015-02-16 11:00:00 +0000
layout: post
tags: ["Byteman", "Jboss"]
slug: "2015-02-16-byteman-swiss-army-knife-for-byte-code"
url: /2015/02/byteman-swiss-army-knife-for-byte-code.html
---

I am working with a bunch of communities in JBoss and there is so much interesting stuff to talk about, that I can't wrap my head around every little bit myself. This is the main reason why I am very thankful to have the opportunity to welcome guest bloggers here from time to time. Today it is Jochen Mader, who&nbsp;&nbsp;is part of the nerd herd at codecentric. He currently spends his professional time coding Vert.x-based middleware solutions, writing for different publications and talking at conferences. His free time belongs to his family, mtb and tabletop gaming. You can follow him on Twitter&nbsp;<a href="http://www.twitter.com/codepitbull" target="_blank">@codepitbull</a>.
<br>
<br>
 There are tools you normally don't want to use but are happy enough to know about them when the need arises. At least to me Byteman falls into this category. It's my personal swiss army knife to deal with a <a href="" target="_blank">Big Ball of Mud</a> or one of those dreaded Heisenbugs. So grab a current <a href="http://byteman.jboss.org/downloads" target="_blank">Byteman-distribution</a>, unzip it to somewhere on your machine and we are off to some dirty work.
<br>
<br><b>What is it</b>
<br>
 Byteman is a byte code manipulation and injection tool kit. It allows us to intercept and replace arbitrary parts of Java code to make it behave differently or break it (on purpose):
<br>
<ul>
 <li>&nbsp;get all threads stuck in a certain place and let them continue at the same time (hello race condition)</li>
 <li>&nbsp;throw Exceptions at unexpected locations</li>
 <li>&nbsp;tracing through your code during execution</li>
 <li>&nbsp;change return values</li>
</ul> and a lot more things.
<br>
<br><b>An example</b>
<br>
 Let's get right into some code to illustrate what Byteman can do for you.
<br>
 Here we have a wonderful Singleton and a (sadly) good example of code you might find in many places.
<br>
<br>
<pre class="code"><code>public class BrokenSingleton \{ &nbsp; &nbsp; private static volatile BrokenSingleton instance; &nbsp; &nbsp; private BrokenSingleton() \{ &nbsp; &nbsp; \} &nbsp; &nbsp; public static BrokenSingleton get() \{ &nbsp; &nbsp; &nbsp; &nbsp; if (instance == null) \{ &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; instance = new BrokenSingleton(); &nbsp; &nbsp; &nbsp; &nbsp; \} &nbsp; &nbsp; &nbsp; &nbsp; return instance; &nbsp; &nbsp; \} \} </code></pre> Let's pretend we are the poor souls tasked with debugging some legacy code showing weird behaviour in production. After a while we discover this gem and our gut indicates something is wrong here.
<br>
 At first we might try something like this:
<br>
<pre class="code"><code> public class BrokenSingletonMain \{ &nbsp; &nbsp; public static void main(String[] args) throws Exception \{ &nbsp; &nbsp; &nbsp; &nbsp; Thread thread1 = new Thread(new SingletonAccessRunnable()); &nbsp; &nbsp; &nbsp; &nbsp; Thread thread2 = new Thread(new SingletonAccessRunnable()); &nbsp; &nbsp; &nbsp; &nbsp; thread1.start(); &nbsp; &nbsp; &nbsp; &nbsp; thread2.start(); &nbsp; &nbsp; &nbsp; &nbsp; thread1.join(); &nbsp; &nbsp; &nbsp; &nbsp; thread2.join(); &nbsp; &nbsp; \} &nbsp; &nbsp; public static class SingletonAccessRunnable implements Runnable \{ &nbsp; &nbsp; &nbsp; &nbsp; @Override &nbsp; &nbsp; &nbsp; &nbsp; public void run() \{ &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; System.out.println(BrokenSingleton.get()); &nbsp; &nbsp; &nbsp; &nbsp; \} &nbsp; &nbsp; \} \} </code></pre> Running this there is a very small chance to see the actual problem happen. But most likely we won't see anything unusual. The Singleton is initialized once and the application performs as expected. A lot of times people start brute forcing by increasing the number of threads, hoping to make the problem show itself. But I prefer a more structured approach.
<br>
<br>
 Enter Byteman.
<br>
<br><b>The DSL</b>
<br>
 Byteman provides a convenient DSL to modify and trace application behaviour. We'll start with tracing calls in my little example. Take a look at this piece of code.
<br>
<pre class="code"><code> RULE trace entering CLASS de.codepitbull.byteman.BrokenSingleton METHOD get AT ENTRY IF true DO traceln("entered get-Method") ENDRULE RULE trace read stacks CLASS de.codepitbull.byteman.BrokenSingleton METHOD get AFTER READ BrokenSingleton.instance IF true DO traceln("READ:\n" + formatStack()) ENDRULE </code></pre> The core building block of Byteman-scripts is the RULE.
<br>
<br>
 It consists of several components (example shamelessly ripped from the <a href="http://downloads.jboss.org/byteman/2.2.1/ProgrammersGuide.html" target="_blank">Byteman-Docs</a>:
<br>
<br>
<pre class="code"><code>&nbsp;# rule skeleton &nbsp;RULE &lt;rule name&gt; &nbsp;CLASS &lt;class name&gt; &nbsp;METHOD &lt;method name&gt; &nbsp;BIND &lt;bindings&gt; &nbsp;IF &lt;condition&gt; &nbsp;DO &lt;actions&gt; &nbsp;ENDRULE </code></pre>
<br>
 Each RULE needs to have unique __rule name__. The combination of CLASS and METHOD define where we want our modifications to apply. BIND allows us to bind variables to names we can use inside IF and DO. Using IF we can add conditions under which the rule fires. In DO the actual magic happens.
<br>
<br>
 ENDRULE, it ends the rule.
<br>
<br>
 Knwoing this my first rule is easily translated to:
<br>
<br>
 When somebody calls _de.codepitbull.byteman.BrokenSingleton.get()_ I want to print the String "entered get-Method" right before the method body is called (that's what __AT ENTRY__ translates to).
<br>
<br>
 My second rule can be translated to:
<br>
<br>
 After reading (__AFTER READ__) the instance-Member of BrokenSingleton I want to see the current call-Stack.
<br>
<br>
 Grab the code and put it into a file called _check.btm_. Byteman provides a nice tool to verify your scripts. Use __&lt;bytemanhome&gt;/bin/bmcheck.sh -cp folder/containing/compiled/classes/to/test check.btm__ to see if your script compiles. Do this EVERY time you change it, it's very easy to get a detail wrong and spend a long time figuring it out.
<br>
<br>
 Now that the script is saved and tested it's time to use it with our application.
<br>
<br><b>The Agent</b>
<br>
 Scripts are applied to running code through an agent. Open the run-Configuration for the __BrokenSingletonMain-class__ and add
<br>
<pre class="code"><code> __-javaagent:&lt;BYTEMAN_HOME&gt;/lib/byteman.jar=script:check.btm__ </code></pre> to your JVM-parameters. This will register the agent and tell it to run _check.btm_.
<br>
<br>
 And while we are at it here are a few more options:
<br>
 If you ever need to manipulate some core java stuff use
<br>
<pre class="code"><code> __-javaagent:&lt;BYTEMAN_HOME&gt;/lib/byteman.jar=script:appmain.btm,boot:&lt;BYTEMAN_HOME&gt;/lib/byteman.jar__ </code></pre> This will add Byteman to the boot classpath and allow us to manipulate classes like _Thread_, _String_ ... I mean, if you ever wanted to such nasty things ...
<br>
<br>
 It's also possible to attach the agent to a running process. Us __jps__ to find the process id you want to attach to and run
<br>
<pre class="code"><code> __&lt;bytemanhome&gt;/bin/bminstall.sh &amp;ltpid&amp;gt__ </code></pre> to install the agent. Afterwards run
<br>
<pre class="code"><code> __&lt;bytemanhome&gt;/bin/bmsubmit.sh check.btm__ </code></pre> Back to our problem at hand.
<br>
<br>
 Running our application with the modified run-Configuration should result in output like this
<br>
<pre class="code"><code> entered get-Method entered get-Method READ: Stack trace for thread Thread-0 de.codepitbull.byteman.BrokenSingleton.get(BrokenSingleton.java:14) de.codepitbull.byteman.BrokenSingletonMain$SingletonAccessRunnable.run(BrokenSingletonMain.java:20) java.lang.Thread.run(Thread.java:745) READ: Stack trace for thread Thread-1 de.codepitbull.byteman.BrokenSingleton.get(BrokenSingleton.java:14) de.codepitbull.byteman.BrokenSingletonMain$SingletonAccessRunnable.run(BrokenSingletonMain.java:20) java.lang.Thread.run(Thread.java:745) </code></pre>
<br>
 Congratulations you just manipulated byte code. The output isn't very helpful yet but that's something we are going to change.
<br>
<br><b>Messing with threads</b>
<br>
 With our infrastructure now set up we can start digging deeper. We are quite sure about our problem being related to some multithreading issue. To test our hypothesis we have to get multiple threads into our critical section at the same time. This is close to impossible using pure Java, at least without applying extensive modifications to the code we want to debug.
<br>
<br>
 Using Byteman this is easily achieved.
<br>
<br>
<pre class="code"><code>RULE define rendezvous CLASS de.codepitbull.byteman.BrokenSingleton METHOD get AT ENTRY IF NOT isRendezvous("rendezvous", 2) DO createRendezvous("rendezvous", 2, true); traceln("rendezvous created"); ENDRULE </code></pre>
<br>
 This rule defines a so called rendezvous. It allows us to specify a place where multiple threads have to arrive until they are allowed to procede (also known as a a barrier).
<br>
<br>
 And here the translation for the rule:
<br>
<br>
 When calling _BrokenSingleton.get()_ create a new rendezvous that will allow progress when 2 threads arrive. Make the rendezvous reusable and create it only if it doesn't exist (the IF NOT part is critical as otherwise we would create a barrier on each call to _BrokenSingleton.get()_).
<br>
<br>
 After defining this barrier we still need to explicitly use it.
<br>
<br>
<pre class="code"><code>RULE catch threads CLASS de.codepitbull.byteman.BrokenSingleton METHOD get AFTER READ BrokenSingleton.instance IF isRendezvous("rendezvous", 2) DO rendezvous("rendezvous"); ENDRULE </code></pre>
<br>
 Translation: After reading the _instance_-member inside _BrokenSingleton.get()_ wait at the rendezvous until a second thread arrives and continue together.
<br>
<br>
 We now stop both threads from _BrokenSingletonMain_ in the same lace, after the instance-null-check. That's how to make a race condition reproducible. Both threads will continue thinking _instance_ is null, causing the constructor to fire twice.
<br>
<br>
 I leave the solution to this problem to you ...
<br>
<br><b>Unit tests</b>
<br>
 Something I discovered while writing this blog post is the possibility to run Byteman-scripts as part of my unit tests. Their <a href="https://github.com/bytemanproject/byteman/tree/master/contrib/bmunit" target="_blank">JUNit- and TestNG-integration</a> is easily integrated.
<br>
<br>
 Add the following dependency to your _pom.xml_
<br>
<pre class="code"><code> &lt;dependency&gt; &nbsp; &nbsp; &lt;groupId&gt;org.jboss.byteman&lt;/groupId&gt; &nbsp; &nbsp; &nbsp; &lt;artifactId&gt;byteman-submit&lt;/artifactId&gt; &nbsp; &nbsp; &lt;scope&gt;test&lt;/scope&gt; &nbsp; &nbsp; &lt;version&gt;$\{byteman.version\}&lt;/version&gt; &lt;/dependency&gt; </code></pre> Now Byteman-scripts can be executed inside your Unit-Tests like this:
<br>
<pre class="code"><code> @RunWith(BMUnitRunner.class) public class BrokenSingletonTest \{ &nbsp; @Test &nbsp; @BMScript("check.btm") &nbsp; public void testForRaceCondition() \{ &nbsp; &nbsp; ... &nbsp; \} \} </code></pre> Adding such tests to your suits increases the usefulness of Byteman quite a bit. There's no better way preventing others from repeating your mistakes as making these scripts part of the build process.
<br>
<br><b>Closing words</b>
<br>
 There is only so much room in a blog post and I also don't want to start rewriting their documentation. It was a funny thing writing writing this post as I hadn't used Byteman for quite a while. I don't know how I managed to overlook the unit test integration. That will make me use it a lot more in the future.
<br>
 And now I suggest to browse their <a href="http://downloads.jboss.org/byteman/2.2.1/ProgrammersGuide.html" target="_blank">documentation</a> and start injecting, there's a lot to play around with.
<br>
<br>
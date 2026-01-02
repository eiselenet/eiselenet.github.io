---
title: "Arquillian - Test in the Container with GlassFish 3.1-b04 Embedded"
date: 2010-11-12 06:41:00 +0000
layout: post
tags: ["glassfish", "javaee6", "Arquillian"]
slug: "arquillian-test-in-container-with"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2010/11/arquillian-test-in-container-with.html
---

I am playing around with JBoss <a href="http://community.jboss.org/wiki/Arquillian">Arquillian </a>these days. Arquillian provides a easy mechanism to test your application code inside a remote or embedded container or by interacting as a client of the container. Having all those integration testing issues since years really makes me wanting all those stuff. And this week there was time to get my hands on that.
<br>
<br><b>Download and first information</b>
<br>
 It's simple. Grep your copy of the <a href="https://github.com/arquillian/arquillian-examples">Arquillian Examples</a> from Github and start using it. You can either download or even checkout the repository. It's also a very good idea to make yourself familiar with the <a href="">reference guide</a>.
<br>
 The examples are based on the latest Arquillian 1.0.0.Alpha4 and this probably is the first hint for what to expect. It's an alpha. Even if the reference guide gives very helpfull information there is still a lack of public solutions to many things you come across. So you are up to the help of the guys who created it. Thank god, they are available and very responsive. (Thanks to <a href="">@aslakknutsen</a> and <a href="">@mojavelinux</a> for the latest help and ideas!) If you are having problems, the first place to look is the <a href="http://community.jboss.org/en/arquillian?view=discussions">user forum</a>. It's there since September and already contains 127 threads to look for solutions.
<br>
<br><b>Getting Started</b>
<br>
 Extract the arquillian-arquillian-examples-XXXXX.zip archive to a suitable location. All you need is a recent JDK (1.6.0_21) and Maven (3.0). The best place to start is the ejb31-gfembedded example. In most cases this will not work out of the box. I had&nbsp; to adjust a couple of things to get this running.
<br>
<br>
 1) Repositories
<br>
 You need access to some JBoss maven repositories in order to build your example. Add the JBoss public repository as a repository and additional as pluginRepository in your projects pom.xml.
<br>
 2) Offline/Proxy issues with GF
<br>
 If you are working behind a corporate proxy, you need to add a build section with the maven-surefire-plugin to your pom.xml. Add both systemProperties relevant: http.proxyHost and http.proxyPort. Offline testing is not possible at the moment. Because Arquillian only runs with GF 3.1-b04 which has an <a href="https://glassfish.dev.java.net/issues/show_bug.cgi?id=11516">issue </a>trying to download schema ressources from sun.com.
<br>
 3) Arquillian configuration files
<br>
 If you are not adding an arquillian.xml to your project you will get into trouble with temp gf folders. It's best to exactly configure where you have your instance_root. This is done via the arquillian.xml file. Add a src\test\resources folder and put it in there. Configure the instanceRoot to be at src/test/glassfish and create the folder. Next is to add a testResource pointing to src\test\resources in your pom.xml
<br>
<br><b>Executing your tests</b>
<br>
 Done. Now you are set. Get a cmd line and enter mvn test -Pglassfish-embedded-3. You see a couple of log messages streamed to your console. The most important one:
<br>
<br><code><br>
  INFO: GlassFish3.1-b04 (java_re-private) startup time : Embedded(406ms) startup services(312ms) tota<br>
  l(718ms)<br></code>
<br>
 followed by 
<br><code><br>
  INFO: Portable JNDI names for EJB HelloEJB : [java:global/test/HelloEJB, java:global/test/HelloEJB!o<br>
  rg.jboss.arquillian.examples.gfembedded.HelloEJB]<br></code>
<br>
 which both tells you, that the embedded server started and your HelloEJB was bound to the JNDI tree. And finally you have the test-results.
<br><code><br>
  -------------------------------------------------------------------------------<br>
  Test set: org.jboss.arquillian.examples.gfembedded.HelloEJBTest<br>
  -------------------------------------------------------------------------------<br>
  Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 9.563 sec<br></code>
<br>
 You can find an output per testcase in the target/surefire-reports folder. This also contains an XML with all the testsuite properties used and the testcase itself.
<br>
<br><b>Some more hints</b>
<br>
 It took quite some time to get this up and running. There are some examples in the initial distribution also (arquillian-1.0.0.Alpha4\examples\junit) but I was not able to get them to work. Still not giving up, but it's hard because missing qualified error output. You only get logfiles and they collect errors no matter from where they are. If you are looking for the actuall deployment that Arquillian tries to deploy, you can add an engine entry to your arquillian.xml
<br><code><br>
  &lt;engine&gt;<br>
  &lt;deploymentExportPath&gt;target/temp&lt;/deploymentExportPath&gt;<br>
  &lt;/engine&gt;<br></code>
<br>
 This puts the generated jar to the target directory. Helpfull, if you are tracking down things in the server. 
<br>
 Also don't try to use a later GlassFish build than b04. This leads to a couple of problems and exceptions, that needed to be tracked down further. Seems as if some older TLDScanner things came up again. 
<br>
<br><b>Where next</b>
<br>
 This was a very short howto about running a simple example with Arquillian. Now you have a working environment to make yourself compfortable with this awesome tool and you should actually start looking at the executed testcase and try to write some yourself. I have never ever seen a tool before which could run my integration tests with that ease. I am really looking forward to the next versions and I still hope, that the guys at JBoss start having a more frequent look at 
<br>
 a) their examples and 
<br>
 b) the integration with the Java EE 6 Reference Implementation.
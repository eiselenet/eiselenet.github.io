---
title: "Clustering Stateful Session Beans with GlassFish 3.1"
date: 2011-02-21 06:50:00 +0000
layout: post
tags: ["ejb 3.1", "cluster", "glassfish 3.1"]
slug: "2011-02-21-clustering-stateful-session-beans-with"
url: /2011/02/clustering-stateful-session-beans-with.html
---

Clustering will be the next big topic for the GlassFish 3.1 version. It's right ahead and you can already <a href="http://blogs.sun.com/theaquarium/entry/two_glassfish_3_1_rc" target="_blank">pick your RC4</a> build to experiment a bit. There are some screencasts for clustering the HTTPSession available. Arun Gupta did a great intro for <a href="http://www.youtube.com/watch?v=xSiZHKJLOh4" target="_blank">GlassFish 3.1 Clustering, High Availability and Centralized Administration</a>. So you can get a basic introduction into clustering, nodes, instances and domains. Up to today the documentation for all those features is still very limited. But this will change in the next few weeks. Here is, what I did to get a Stateful Session Bean failover scenario working with two instances on one node.
<br>
<br><b>Preparation</b>
<br>
 As always: Get you GlassFish 3.1 RC4 up an running. Create a cluster (e.g. demoCluster) and add two instances (instance1 and instance2). Now start your domain and cluster. Write a simple EJB (e.g. MySessionBean) and make it implement a RemoteInterface (e.g. MySessionBeanRemote). My example has a simple String showInstance() business method which prints out the actual running instance:
<br><code><br>
  Logger.getLogger(MySessionBean.class.getName())<br>
  .log(Level.INFO, "Running on: \{0\}",<br>
  System.getProperty("com.sun.aas.instanceName"));<br></code>
<br>
 Wrap your ejb-jar into an ear and deploy it. Make sure to check the "Availability" during deployment. This enables in-memory replication from one instance to the other.
<br>
 Go and check if the EJB is registered with your instances:
<br><code><br>
  %AS_INSTALL%/bin/asadmin list-jndi-entries demoCluster<br></code>
<br>
 You should see something like this:
<br><code><br>
  node2:<br>
  java:global: com.sun.enterprise.naming.impl.TransientContext<br>
  [...]net.eisele.cluster.MySessionBeanRemote#<br>
  net.eisele.cluster.MySessionBeanRemote: javax.naming.Reference<br>
  [...]<br><br>
  node1:<br>
  java:global: com.sun.enterprise.naming.impl.TransientContext<br>
  [...]<br>
  net.eisele.cluster.MySessionBeanRemote#<br>
  net.eisele.cluster.MySessionBeanRemote: javax.naming.Reference<br>
  [...]<br></code>
<br>
 Up to now, this hardly was any problem. It's a simple EJB without anything else to tweak. Now let's go and write some clients.
<br>
<br><b>Standalone Client</b>
<br>
 Coming from the old-school I am starting with a standalone client without any "magic". Create a Java project (e.g. StandaloneClient), add a Main class (StandaloneClient) and add the %AS_INSTALL%/glassfish/lib/gf-client.jar as a dependency. Make sure to reference the RemoteInterface (MySessionBeanRemote) somehow (Classpath or copying it to your projects source).
<br><code><br>
  // get a simple no argument constructor InitialContext<br>
  InitialContext ic = new InitialContext();<br>
  // use the <a href="http://glassfish.java.net/javaee5/ejb/EJB_FAQ.html#What_is_the_syntax_for_portable_global_" target="_blank">global jndi</a> name as lookup string<br>
  String lookup = "java:global/ClusterTestEar/ClusterTestEar-ejb/MySessionBean!net.eisele.cluster.MySessionBeanRemote";<br>
  //lookup the jndi name <br>
  MySessionBeanRemote myEjb = (MySessionBeanRemote) ic.lookup(lookup);<br>
  //call showInstance<br>
  myEjb .showInstance();<br></code>
<br>
 Everything straight forward. No magic here. Compared with most of the other application servers it's unusual that you don't either have to specify any InitialContext properties or even add any cluster magic to the lookup string. But how does the client know about my instances and ports? That's simple. you have to specify them as VM Options:
<br><code><br>
  -Dcom.sun.appserv.iiop.endpoints=<br>
  127.0.0.1:23701,127.0.0.1:23700<br></code>
<br>
<br><b>Application Client Container</b>
<br>
 The youth is probably unhappy about the standalone client. They are used to some kind of injection and those fancy annotation stuff. Ok. Here we go. You are calling for the ACC. The easiest way of creating one is to let your IDE do this. NetBeans generates a great acc project for you. It also has a main class and you can simply:
<br><code><br>
  // inject you ejb ...<br>
  @EJB<br>
  private static MySessionBeanRemote myEjb;<br>
  [...] <br>
  // and use it <br>
  myEjb .showInstance();<br></code>
<br>
 Great. But here it goes. NetBeans itself does not have any kind of cluster deployment support, but the ACC project was designed to be deployed. So, even if you are not in need doing so, the "Run File" NetBeans command fails. So you have to do this manually. First part is to Tell the ACC where to find the remote EJB. 
<br>
 If you are not going to write a webstart based scenario (which is exactly what we are NOT going to do :)), you can simply pick a copy of the %AS_INSTALL%\domains\clusterdomain\config\sun-acc.xml and put it to your projects folder. You need to edit it and make it reflect your instances:
<br><code><br>
  &lt;target-server name="MYNODE" address="localhost" port="23701"/&gt;<br>
  &lt;target-server name="MYNODE" address="localhost" port="23700"/&gt;<br></code>
<br>
 The %AS_INSTALL%\bin\appclient.bat/sh script is there to help you executing the ACC. Build a jar from your appclient project and run it:
<br><code><br>
  appclient -client %YOUR_DIST_FOLDER%\ClusterAppClient.jar -xml %YOUR_DIST_FOLDER%\sun-acc.xml<br></code>
<br>
 Done. Now you have everything working. The new-school and the old-school way of accessing remote EJBs. 
<br>
<br><b>Clustering / Failover</b>
<br>
 Now let's test the failover capabilities. Add a second business method call to your clients and do anything to make the client pause between them. Either to automatically continue after a few seconds or on key-press. 
<br>
 Now let's start the client. It prints out to which instance this client is connected. Assume it tells you something like this:
<br><code><br>
  Running on: instance1<br></code>
<br>
 Now switch over to your asadmin console and enter the needed stop-instance command to shutdown the instance the client is connected to. In this case:
<br><code><br>
  asadmin&gt; stop-instance instance1<br></code>
<br>
 If it is stopped, press a key or wait for the client script to continue. and see, that the second call is redirected to the second node:
<br><code>Running on: instance2<br></code>
<br>
 Failover works. Perfect :) Thanks to Oracle's Cheng and Tim for answering my questions during research!
<br>
<br><b>Further Reading</b>
<br><a href="http://download.oracle.com/docs/cd/E19798-01/821-1752/beakt/index.html" target="_blank">Oracle GlassFish Server 3.0.1 Application Development Guide Chapter 11 Developing Java Clients</a>
<br><a href="http://glassfish.java.net/javaee5/ejb/EJB_FAQ.html#What_is_the_syntax_for_portable_global_" target="_blank">GlassFish Community EJB FAQ</a>
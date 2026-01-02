---
title: "Weblogic 11g calling GlassFish v3, Foreign JNDI Provider"
date: 2009-12-17 09:14:00 +0000
layout: post
tags: ["ejb 3.x", "ejb 2.x", "rmi-iiop", "weblogic server", "foreign jndi", "glassfishv3"]
slug: "weblogic-11g-calling-glassfish-v3"
link: "2009/12/weblogic-11g-calling-glassfish-v3.html"
url: /2009/12/weblogic-11g-calling-glassfish-v3.html
---

And here is the second post about WLS/GF interoperability.
<br>
 After answering the question <a href="http://www.eisele.net/blog/2009/12/glassfish-v3-calling-weblogic-11g.html">How to call an EJB deployed on Weblogic 11g (WLS 10.3.2.0) from GlassFish v3?</a> I now wanted to know, how do it the other way around. 
<br>
<br>
 Obvisously it would work the same way, I did on the GF side. Anyway, there is another feature build into Weblogic that makes this more easy for the developer. It is called <a href="http://download.oracle.com/docs/cd/E12839_01/web.1111/e13730/toc.htm#i479016" target="_blank">Foreign JNDI</a>. Foreign JNDI is an API that allows you to access objects on a remote JNDI tree without having to connect directly to the remote tree. It enables you to make links to a JNDI tree on another server or provider including, but not limited to, WebLogic Server, or a JNDI tree in a java program. Once you have configured Foreign JNDI, you can use an object that is somewhere else with the same ease that you would use an object bound in your WebLogic server instance. This is again using RMI-IIOP communication. Ok. Let's start.
<br>
<br>
 You need an EJB to deploy to GF. Of course, this has to be a EJB 3.x. I wrote a quite simple one called GFTest. It has one business method public void sayHelloOnServer() which puts a text to stdout.
<br>
 And again, you need EJB 2.x style remote interfaces for this. 
<br>
 That should look similar to this:
<br><code><br>
  @Stateless(mappedName="GFTest")<br>
  @Remote(GFTestRemote.class)<br>
  @RemoteHome(GFTestRemoteHome.class)<br>
  public class GFTest implements GFTestRemote<br></code>
<br>
 GFTest is the EJB 3.x business interface. GFTestRemoteHome is the EJB 2.x EJBHome which creates the GFTestRemoteObject EJBObject. it is best to put all interfaces into a separate client project.
<br>
 If everything is ready, deploy the ejb. In my previous post I recommended to run the appc compiler to generate stubs and skeletons. As proven today, this is not realy needed. You only need the interface classes on your client project. Thats totally suffient.
<br>
<br>
 Now let's move over to the Weblogic administration console (http://localhost:7001/console) and configure the foreign JNDI Provider. The steps to take are described in the <a href="http://download.oracle.com/docs/cd/E15523_01/apirefs.1111/e13952/taskhelp/jndi/ConfigureForeignJNDIProvider.html" target="_blank">admin console online help</a>. 
<br>
 First you have to create a foreign JNDI provider. (Summary of Services / Summary of Foreign JNDI Providers / New) and give it a name you like. Now click on the newly created provider and configure it. Settings are as follows:
<br><b>Initial Context Factory:</b> weblogic.jndi.WLInitialContextFactory
<br><b>Provider URL:</b> iiop://your_host:your_port (standard case is iiop://localhost:3700)
<br>
<br>
 Next is to create a foreign JNDI object link. Switch from the general to the links tab and click new. Give it a name, you like, it is for administrational proposes only. Then configure the following settings:
<br><b>Local JNDI Name:</b> The local binding name for the object (in my case GFTest2)
<br><b>Remote JNDI Name:</b> corbaname:iiop:your_host:your_port#remote_binding_name (in my case corbaname:iiop:localhost:3700#GFTest).
<br>
<br>
 After that, you have to restart the server instance.
<br>
<br>
 Start your favorite IDE for your Weblogic projects and setup a client project. Again, I am one of those webguys and therefore I setup a simple Dynamic Webproject with OEPE. The remote interfaces should be available to the project (client.jar, or classess folder).
<br>
<br>
 The only thing you have to do now, is to get an InitialContext and lookup your EJBHome.
<br><code><br>
  InitialContext context = new InitialContext();<br>
  GFTestRemoteHome home = <br>
  (GFTestRemoteHome) context<br>
  .lookup("GFTest2");<br></code>
<br>
 The only thing left to do is to create your GFTestRemoteObject on which to call your business method.
<br><code><br>
  GFTestRemoteObject object = home.create();<br>
  object.sayHelloOnServer();<br></code>
<br>
 Now you are done!Compared to the manual lookup this is extremely simple and does not force you to take care of the connection handling, urls and jndi names in the code at all.
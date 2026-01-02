---
title: "GlassFish v3 calling Weblogic 11g"
date: 2009-12-17 06:48:00 +0000
layout: post
tags: ["ejb 3.0", "ejb 2.x", "glassfishv3", "rmi-iiop", "weblogic server"]
slug: "glassfish-v3-calling-weblogic-11g"

url: /2009/12/glassfish-v3-calling-weblogic-11g.html
---

I was playing around with interoperability problems lately. The question was: How to call an EJB deployed on Weblogic 11g (WLS 10.3.2.0) from GlassFish v3? What looks simple on the first hand, is not too easy.
<br>
 Here are the steps to take.
<br>
<br>
 First of all, it is not handy to put any wls libraries into GF's classloader. Therefore you should stick to the most basic communication method available. This is RMI-IIOP. Ok. Let's start.
<br>
<br>
 You need an EJB to deploy to WLS. Of course, this has to be a EJB 3.0. I wrote a quite simple one called Logger. It has one business method void logString(String message) which gets a string and puts it to stdout.
<br>
 If you are going to use RMI-IIOP, you have to have EJB 2.x style remote interfaces. 
<br>
 That should look similar to this:
<br>
<br><code><br>
  @Stateless(mappedName = "Logger")<br>
  @Remote(LoggerRemote.class)<br>
  @RemoteHome(LoggerRemoteHome.class)<br>
  public class Logger implements LoggerRemote <br></code>
<br>
<br>
 LoggerRemote is the EJB 3.x business interface. LoggerRemoteHome is the EJB 2.x EJBHome which creates the LoggerRemoteObject EJBObject. Put all interfaces into a separate client project (ejb-client-jar). 
<br>
 If everything is ready, deploy the ejb. Now it is time to compile the Stubs and Skeletons for your ejb-client-jar.
<br>
 Take your jar or ear and put it in weblogic.appc compiler. 
<br>
<br><code>java weblogic.appc ejbinteropEAR.ear</code>
<br>
<br>
 This will generate the ejbinteropClient.jar with all needed additional classes. Start your favorite IDE for your GF projects and setup a client project. I badly needed to try some Servlet 3.0 features and wrote a small 
<br><code>@WebServlet(name = "InterobTest", urlPatterns = \{"/InterobTest"\})</code> servlet for that.
<br>
 The ejbinteropClient.jar needs to be in your projects classpath.
<br>
 First thing to do is, to build an InitialContext.
<br>
<br>
 // Build Properties Object
<br>
 Properties h = new Properties();
<br>
 h.put(Context.INITIAL_CONTEXT_FACTORY,
<br>
 "com.sun.jndi.cosnaming.CNCtxFactory");
<br>
 h.put(Context.PROVIDER_URL, 
<br>
 "iiop://localhost:7001");
<br>
 h.setProperty("org.omg.CORBA.ORBInitialHost", 
<br>
 "localhost");
<br>
 h.setProperty("org.omg.CORBA.ORBInitialPort", 
<br>
 "7001");
<br>
 // Get InitialContext with Properties
<br>
 InitialContext ic = new InitialContext(h);
<br>
<br>
 Now you are half way through. Now lookup your remote object
<br>
<br><code>Object home = ctx.lookup(JNDI_NAME);</code>
<br>
<br>
 This took some time. Ok, not having the java.net forums online since a few days did realy not speed this up.
<br>
 Anyway, the magic in this is the JNDI_NAME.
<br>
 It is constructed out of several parts:
<br>
<br><code><br>
  String JNDI_NAME = <br>
  "corbaname:iiop:localhost:7001#"+<br>
  "Logger#ejbinterop.LoggerRemoteHome";<br></code>
<br>
<br>
 First part is the static part containing your host and port of the target WLS instance. Second is the binding name for your RemoteHome interface in Weblogic Server's JNDI tree.
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/jndibrowser-774490.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="320" src="http://www.eisele.net/blog/uploaded_images/jndibrowser-774488.png" width="320"></a>
<br>
<br>
 The only thing left to do is to narrow your RemoteHome from the stubclass
<br>
<br><code><br>
  LoggerRemoteHome home =(LoggerRemoteHome) <br>
  PortableRemoteObject<br>
  .narrow(home, <br>
  LoggerRemoteHome.class);<br></code>
<br>
<br>
 and create your LoggerRemoteObject on which to call your business method on.
<br>
<br><code><br>
  LoggerRemoteObject obj= home.create();<br>
  obj.logString("Testlog");<br></code>
<br>
<br>
 Now you are done! A few simple lines of code, which could cause you to get grey hair if you are missing any part.
<br>
 For me it was quite helpfull, to turn on all related debug settings in WebLogic server. Go to Environment / Servers / Your Server / Debug and enable all needed scopes and attributes. If the call is successfull, you see some detailed debug information on the stdout (don't forget to change the log level!).
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/wls_debug_settings-793886.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="320" src="http://www.eisele.net/blog/uploaded_images/wls_debug_settings-793882.png" width="320"></a>
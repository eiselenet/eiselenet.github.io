---
title: "Seven ways to get things started. Java EE Startup Classes with GlassFish and WebLogic"
date: 2010-12-07 08:04:00 +0000
layout: post
tags: ["Java EE", "glassfish", "weblogic. startup"]
slug: "2010-12-07-seven-ways-to-get-things-started-java"
url: /2010/12/seven-ways-to-get-things-started-java.html
---

This is a blog about a topic that I realy don't like. But it comes across my ways over and over again and it's no doubt that you need it from time to time. Enough reasons for me to collect some information about it and publish it for your reference. I am talking about Startup-/Shutdown classes with Java EE applications or servers. 
<br>
<br><b>What are those and why?</b>
<br>
 Most Java EE applications require you to invoke some functionality at the server startup or application deployment time. For example, you may want to preload some data or invoke some business logic or invoke some logic at server shutdown to gracefully disconnect from a service or release some resources. The right place to do most of the stuff is a so called startup- and/or shutdown class. If you look into the most recent Java EE 6 specification you only find one application construct, the <span style="font-family: monospace;">@Startup</span> annotation to do the job. But you have a couple of other options. Here you are:
<br>
<br><b>EJB 3.1 @Startup</b>
<br>
 The introduction of singletons also provides a convenient way for EJB applications to receive callbacks during application initialization or shutdown. By default, the container decides when to instantiate the singleton instance. However, you can force the container to instantiate the singleton instance during application initialization by using the @Startup annotation. This allows the bean to define a <span style="font-family: monospace;">@PostConstruct</span> method that is guaranteed to be called at startup time. In addition, any @PreDestroy method for a singleton is guaranteed to be called when the application is shutting down, regardless of whether the singleton was instantiated using lazy instantiation or eager instantiation. In lazy instantiation, the singleton isn't instantiated until it's method's are first needed. In eager instantiation, the singleton is instantiated at startup time whether or not it gets used. 
<br><span style="font-family: monospace;"><br>
  @Singleton<br>
  @Startup<br>
  public class StartupBean \{<br><br>
  @PostConstruct<br>
  private void startup() \{ ... \}<br><br>
  @PreDestroy<br>
  private void shutdown() \{ ... \}<br>
  ...<br>
  \}<br></span>
<br>
 If you are in a clustered environment this probably will not work for you out of the box. This only works within a single VM. You will have your StartupBean called on every server instance starting up or shutting down. If you have special cluster requirements (e.g. only initialize once for the complete cluster) you have to think about synchronizing your StartupBeans by using a database. Usable on any EJB 3.1 compliant container and highly portable. This even should work with the lightweight Java EE 6 Webprofile.
<br>
<br><b>Startup Servlet</b>
<br>
 The old fashioned way. You can do whatever logic is needed within your startup servlet. This is a simple servlet in general which gets a &gt;load-on-startup&lt; parameter in your web.xml or the&nbsp;<span style="font-family: monospace;">@WebServlet(name="startup", loadOnStartup="2")&nbsp;</span>annotation.
<br>
 This servlet has a load-at-startup priority of 2, meaning any servlet with a load priority lower than 2 will be loaded before this one, and those greater than 2 after.
<br>
 If you have an init() method which is called by the servlet container to indicate to a servlet that the servlet is being placed into service and a destroy() method which is called by the servlet container to indicate to a servlet that the servlet is being taken out of service.
<br><span style="font-family: monospace;"><br>
  public class StartupServlet extends javax.servlet.http.HttpServlet \{<br><br>
  public void init(ServletConfig config) throws ServletException \{<br>
  ....<br>
  \} <br><br>
  public void destroy() \{<br>
  ....<br>
  \} <br>
  \} <br></span>
<br>
<br>
 There are a couple of disadvantages to this approach. First of all, this obviously only works with web applications. And second, there is nothing that prevents the servlet from being called a couple of times. To ensure this, you probably would have the need to implement the singleton pattern. Biggest plus here is, you can port it to any Java application server you like. 
<br>
<br><b>ServletContextListener</b>
<br>
 A better way to implement an application startup class is by using the servlet context listener. It listens to application startup and shutdown events by implementing the methods <span style="font-family: monospace;">contextInitialized()</span> and <span style="font-family: monospace;">contextDestroyed()</span>. You can configure the listener in your web.xml (<span style="font-family: monospace;">&lt;listener-class&gt;</span>) or even use the <span style="font-family: monospace;">@WebServletContextListener</span> annotation.
<br><span style="font-family: monospace;"><br>
  public class StartupListener implements javax.servlet.ServletContextListener \{<br>
  ....<br>
  public void contextInitialized(ServletContextEvent sce) \{<br>
  ....<br>
  \}<br><br>
  public void contextDestroyed(ServletContextEvent sce) \{<br>
  ....<br>
  \}<br>
  \} <br></span>
<br>
 Drawback again; there is one context per "web application" per Java Virtual Machine. So you would have to handle cluster problems yourself. But positive about this is, that you don't have to care about anybody calling it externally, like this could happen with startup servlets. But it's portable and can be used on any Java EE server.
<br>
<br><b>Resource Adapter to Perform as a Startup Class</b>
<br>
 Another way would be to implement a resource adapter with a minimal resource adapter class that implements javax.resource.ResourceAdapter, which defines a <span style="font-family: monospace;">start()</span> and <span style="font-family: monospace;">stop()</span> method. When the resource adapter is deployed, the <span style="font-family: monospace;">start()</span> method is invoked. When it is undeployed, the <span style="font-family: monospace;">stop()</span> method is called. 
<br><span style="font-family: monospace;"><br>
  public class StartupRA implements ResourceAdapter<br>
  \{<br>
  public void start( BootstrapContext bsCtx ) \{ <br>
  ...<br>
  \}<br>
  public void stop() \{<br>
  ...<br>
  \}<br>
  // Because of the definition of the ResourceAdapter interface, <br>
  // you must also define the endpointActivation(), Deactivation() and getXAResource() methods.<br>
  ...<br>
  \}<br></span>
<br>
 You can use this approach if you have the Java EE full profile at hand. This is a very mature way of initializing your application in general. Nevertheless you have to add an Resource Adapter to your project and you have to package and deploy it. Compared with the other approaches this is additional complexity. But it will work for any of the containers available providing an RA implementation. 
<br>
<br><b>Using JMX notification objects with WebLogic Server</b>
<br>
 JMX provides two ways to monitor MBeans: MBeans can emit notifications when specific events occur (such as a change in an attribute value), or a special type of MBean called a monitor MBean can poll another MBean and periodically emit notifications to describe an attribute value. You create Java classes called listeners that listen for these notifications and respond appropriately. To receive a notification when a server starts or stops, register a listener with each server's ServerLifeCycleRuntimeMBean in the Domain Runtime MBean Server and configure an AttributeChangeNotificationFilter. Find a detailed description in the Oracle WebLogic Server documentation <a href="http://download.oracle.com/docs/cd/E14571_01/web.1111/e13728/notifications.htm#i1145555" target="_blank">Listening for Notifications from WebLogic Server MBeans: Main Steps</a>.
<br>
<br>
 Biggest drawback here is the complexity. You have to handle the many notifications and filter them according to your needs. I still do not feel JMX is the easiest API to work with. At the end of the day, this will still be no portable solution. You have to use the WLS specific ObjectNames (e.g. DomainRuntimeServiceMBean).
<br>
<br><b>WebLogic Startup- and Shutdown classes</b>
<br>
 Prior to release 10.x of WLS, you could have used <a href="http://download.oracle.com/docs/cd/E11035_01/wls100/programming/lifecycle.html" target="_blank">application-scoped startup and shutdown</a> classes. Application lifecycle listener events provide handles on which developers can control behavior during deployment, undeployment, and redeployment. You create a listener by extending the abstract class weblogic.application.ApplicationLifecycleListener. The container then searches for your listener. 
<br><span style="font-family: monospace;"><br>
  public class MyListener extends ApplicationLifecycleListener \{<br>
  public void preStart(ApplicationLifecycleEvent evt) \{<br>
  ...<br>
  \}<br><br>
  public void postStart(ApplicationLifecycleEvent evt) \{<br>
  ... <br>
  \} <br><br>
  public void preStop(ApplicationLifecycleEvent evt) \{<br>
  ...<br>
  \} <br><br>
  public void postStop(ApplicationLifecycleEvent evt) \{<br><br>
  \} <br>
  ...<br>
  \}<br></span>
<br>
 If you are not willing to use any WebLogic Server-specific interfaces, you can also declared it in the weblogic-application.xml deployment descriptor. 
<br><span style="font-family: monospace;"><br>
  &lt;listener&gt;<br>
  &lt;listener-class&gt;MyListener&lt;/listener-class&gt;<br>
  &lt;/listener&gt;<br></span>
<br>
 This is a very easy way of implementing startup- and shutdown functionality. Too sad, that it is deprecated and should not be used anymore. And it's not portable and and and ... forget about it :)
<br>
<br><b>GlassFish Server Life Cycle Events</b>
<br>
 A similar construct is available within GlassFish. The so called Server Life Cycle Events. A lifecycle module (implementing LifecycleListener) listens for and performs its tasks in response to events. Upon start up, before initializing its subsystems application server posts lifcycle modules the INIT_EVENT. This is followed by server posting the STARTUP_EVENT to the lifecycle modules upon which server starts loading and initializaing the applications. Once this phase is completed, the READY_EVENT is posted to the lifecycle modules. When the server is shutdown, server posts the SHUTDOWN_EVENT to the lifecycle modules and then shuts down the applications and subsystems. Once this phase is completed the TERMINATION_EVENT is posted. 
<br>
 You only have to implement the <a href="http://javadoc.glassfish.org/v3/apidoc/com/sun/appserv/server/LifecycleListener.html" target="_blank">com.sun.appserv.server.LifecycleListener</a> to get this going. It has one method handleEvent(LifecycleEvent event) which gets notified. 
<br><span style="font-family: monospace;"><br>
  public class StartupListener implements LifecycleListener \{<br><br>
  public void handleEvent(LifecycleEvent event) throws ServerLifecycleException \{<br>
  ...<br>
  if (LifecycleEvent.INIT_EVENT == event.getEventType()) \{<br>
  ... <br>
  return;<br>
  \}<br><br>
  if (LifecycleEvent.STARTUP_EVENT == event.getEventType()) \{<br>
  ...<br>
  return;<br>
  \}<br><br>
  if (LifecycleEvent.SHUTDOWN_EVENT == event.getEventType()) \{<br>
  ...<br>
  return;<br>
  \}<br><br>
  if (LifecycleEvent.TERMINATION_EVENT == event.getEventType()) \{<br>
  ... <br>
  return;<br>
  \}<br>
  \}<br>
  \}<br></span>
<br>
 After you have implemented it, you can put the jar to the domain/lib directory and add the needed configuration to the domain.xml. 
<br><span style="font-family: monospace;"><br>
  &lt;application name="StartupTest" object-type="user"&gt;<br>
  &lt;property name="isLifecycle" value="true" /&gt;<br>
  &lt;property name="class-name" value="net.eisele.StartupListener" /&gt;<br>
  &lt;/application&gt;<br></span>
<br>
 This could also be done via the admin console or the asadmin command. 
<br>
 Things to keep in mind are that the configured properties for a lifecycle module are passed as properties after the INIT_EVENT. The JNDI naming context is not available before the STARTUP_EVENT. If a lifecycle module requires the naming context, it can get this after the STARTUP_EVENT, READY_EVENT, or SHUTDOWN_EVENT.
<br>
<br>
 In general it's the same as with the WebLogic startup mechanisms. They are not portable and you should consider using them only in very special cases.
<br>
<br><b>Bottom Line</b>
<br>
 Now you know about seven ways to get things started. Only four of them are portable and also usable with other Java EE servers. You should try to stick to the first four presented. But there could be reasons for using server depended ways of initializing. If you have the need for threads in general you are almost save starting them the proprietary way, as this is most likely the only way that is supported. Or you have a (customer) blueprint to stick to. Or you need guarantees for your startup classes, that could not be meet by the standardized ways (e.g. classloading, order) ... At the end of the day, it's up to you to make the right decision. Or ask your favorite architect about it :)
<br>
 If I have missed an approach .. let me know, happy to read your comments!
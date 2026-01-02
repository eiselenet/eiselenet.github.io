---
title: "Dynamically registering WebFilter with Java EE 6"
date: 2011-06-15 05:52:00 +0000
layout: post
tags: ["webfilter", "Java EE 6", "servlet"]
slug: "dynamically-registering-webfilter-with"

url: /2011/06/dynamically-registering-webfilter-with.html
---

Yeah. Security. I start loving this stuff. I have a nice little application running with Java EE 6. And if you are following my posts lately, you know, that it has little more security requirements than usual and therefore we definitely have some custom filter logic in place. The <a href="http://download.oracle.com/javaee/6/api/javax/servlet/Filter.html" target="_blank">javax.servlet.Filter</a> is a great place to start, if you are looking for a way to implement cross cutting concerns. 
<br>
<table cellpadding="0" cellspacing="0" class="tr-caption-container" style="float: right; margin-left: 1em; text-align: right;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="canon_filter.jpg" imageanchor="1" style="clear: right; margin-bottom: 1em; margin-left: auto; margin-right: auto;"><img border="0" src="canon_filter.jpg"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;"><a href="" target="_blank">CC BY-NC 2.0</a> by <a href="" target="_blank">aftab</a></td>
  </tr>
 </tbody>
</table>Filters perform filtering in the doFilter method. Every Filter has access to a FilterConfig object from which it can obtain its initialization parameters, and a reference to the ServletContext which it can use, for example, to load resources needed for filtering tasks.
<br>
 Beside Logging and Auditing, compression and conversion this is also suitable for placing security related stuff. 
<br>
 Beginning with Java EE 6 the implementation is straight forward and easy.
<br>
 Add a @WebFilter annotation to your implementation class and you are done:
<br><code><br>
  @WebFilter(dispatcherTypes = \{DispatcherType.REQUEST, DispatcherType.FORWARD\}, urlPatterns = \{"/something/*"\})<br>
  public class SecurityFilter implements Filter \{<br>
  ...<br>
  \}</code>
<br>
<br><b>Problem</b>
<br>
 But: What to do, if you are running different environments and you have some very heavy filter logic in place, which in fact depends on other infrastructure components placing header variables or other stuff into the request before processing? You have to disable them in development. Enable them in production or integration testing. 
<br>
 Building for different environments basically is not a big issue, but you end up commenting in and out the @WebFilter annotation. That ...cks. 
<br>
<br><b>Solution: Dynamically register your WebFilter</b>
<br>
 But hey, the Servlet 3.0 API is here. And you are able to register your components dynamically. A good place to register filters is a <a href="http://download.oracle.com/javaee/6/api/javax/servlet/ServletContextListener.html" target="_blank">ServletContextListener</a>. And you don't even have to forgo your beloved annotations. Let's start with the basics. 
<br><code><br>
  @WebListener<br>
  public class FilterStartupListener implements ServletContextListener \{<br><br>
  @Override<br>
  public void contextInitialized(ServletContextEvent sce) \{<br>
  ServletContext ctx =<br>
  sce.getServletContext();<br>
  ...<br>
  \}<br>
  \}<br></code>
<br>
 Next is to find any way to figure out, if you are running in production mode or not. You could think about using a system property or even reading the <a href="http://blogs.oracle.com/rlubke/entry/jsf_2_0_new_feature2" target="_blank">projectStage</a> property from your JSF implementation. Whatever you chose, the magic happens here:
<br><code><br>
  if (Util.isProduction()) \{<br>
  // if we are running in production mode<br>
  // register with servletContext<br>
  FilterRegistration fr = ctx.addFilter("SecurityFilter", SecurityFilter.class);<br>
  fr.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD),<br>
  true, "/something/*");<br>
  \}<br>
  \}<br></code>
<br>
 That's it. It does all the magic for you and you no longer have to care about them. If the property of your choice changes, your filters get registered dynamically or not.
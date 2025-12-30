---
title: "JSR-299 CDI Interceptors"
date: 2010-01-18 05:51:00 +0000
layout: post
tags: ["Interceptor", "JSR-299", "Java EE 6"]
slug: "2010-01-18-jsr-299-cdi-interceptors"
url: /2010/01/jsr-299-cdi-interceptors.html
---

Playing around with Java EE 6 these days, I came across some new features, I will blog about. Today I just want to give a short introduction to the enhancements made to <a href="http://java.sun.com/javaee/6/docs/api/index.html?javax/interceptor/package-tree.html" target="_blank">javax.interceptor </a> by the <a href="http://docs.jboss.org/weld/reference/1.0.0/en-US/html/interceptors.html" target="_blank">CDI</a>.
<br>
<br>
 Interceptor functionality is allready defined by the Java Interceptors specification. CDI enhances this with a more sophisticated, annotation-based approach for binding interceptors to beans. Only four stepts to get to your new CDI interceptor.
<br>
<br><b>1) Write the interceptor binding:</b>
<br><code><br>
  @InterceptorBinding<br>
  @Target(\{METHOD, TYPE\})<br>
  @Retention(RUNTIME)<br>
  public @interface Log \{\} <br></code>
<br>
<br><b>2) Write the interceptor:</b>
<br><code><br>
  @LogTime<br>
  @Interceptor<br>
  public class LoggingInterceptor \{<br><br>
  @AroundInvoke<br>
  public Object logExecutionTime(InvocationContext ic) throws Exception \{<br>
  long start = System.currentTimeMillis();<br>
  try \{<br>
  return ic.proceed();<br>
  \} catch (Exception e) \{<br>
  throw e;<br>
  \} finally \{<br>
  long time = System.currentTimeMillis() - start;<br>
  String method = ic.getClass().getName();<br>
  Logger.getLogger(<br>
  LoggingInterceptor.class.getName())<br>
  .log(Level.INFO, "*** Invocation of " <br>
  + method + " took " + time + "ms");<br>
  \}<br>
  \}<br>
  \}</code>
<br>
<br><b>3) Declare the interceptor in beans.xml:</b>
<br>
 &lt;interceptors&gt;
<br>
 &lt;class&gt;cdi.LoggingInterceptor&lt;/class&gt;
<br>
 &lt;/interceptors&gt;
<br>
<br><b>4) Use the interceptor in your code:</b>
<br><code><br>
  //...<br>
  @LogTime<br>
  public String getText() \{<br>
  //..<br>
  \}<br></code>
<br>
<br>
 That is all. Have fun.
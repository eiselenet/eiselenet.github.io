---
title: "Perf4j - Release 0.9.9 available"
date: 2009-02-02 09:34:00 +0000
layout: post
tags: ["performance", "Perf4J", "log4j"]
slug: "perf4j-release-099-available"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2009/02/perf4j-release-099-available.html
---

I was playing around with perf4j recently. Quite impressive, how simple you can generate performance statistics for your application.
<br>
Even without using any of the delivered aspects you can simply write your own interceptor and use an appropriate stoppwatch, wherever needed.
<br>
In my sample case, I wrote a simple interceptor. This is the slightes way ever. I skipped essential things like handling exceptions and so on. But this should be enough to demonstrate the basics ;)
<br>
<br>
<blockquote>
 <br>
 public class PerformanceLoggingInterceptor implements MethodInterceptor \{
 <br>
 [...]
 <br>
 <br>
  public Object invoke(MethodInvocation invocation) throws Throwable \{
 <br>
  //new Log4JstopWatch with your own tag
 <br>
  StopWatch stopWatch = new Log4JStopWatch("myperftag", logger);
 <br>
 <br>
  Object retVal = invocation.proceed();
 <br>
  stopWatch.stop(cls + "." + methodName);
 <br>
  return retVal;
 <br>
  \}
 <br>
 \}
 <br>
 <br>
</blockquote>
<br>
<br>
Now you need to configure the intercepter and setup perf4j within your application. 
<br>
1) Use the right dependency with maven
<br>
<br>
&lt;dependency&gt;
<br>
&lt;groupId&gt;org.perf4j&lt;/groupId&gt;
<br>
&lt;artifactId&gt;perf4j&lt;/artifactId&gt;
<br>
&lt;version&gt;0.9.9&lt;/version&gt;
<br>
&lt;scope&gt;compile&lt;/scope&gt;
<br>
&lt;/dependency&gt;
<br>
<br>
2) Change your log4j.properties to an xml style version and add the needed appenders. The documentation on the <a href="http://perf4j.codehaus.org/devguide.html#Using_the_log4j_Appenders_to_Generate_Real-Time_Performance_Information" target="_blank">perf4j website</a> is a good place to start with the basic configuration. 
<br>
Remember to change the tags, that should be logged appropriate, e.g.:
<br>
<blockquote>
 <br>
 &lt;param name="TagNamesToGraph"
 <br>
 value="doStart,myTag,test2" /&gt;
 <br>
</blockquote>
<br>
<br>
3) If you like to expose the statistics within your webapp you have to configure the delivered servlet within your web.xml. <a href="http://perf4j.codehaus.org/devguide.html#Exposing_Performance_Graphs_in_a_Web_Application" target="_blank">An example is provided </a>on the perf4j website.
<br>
<br>
If this is finished, you can take a look at the working system and get a chart generated from google, which looks like this:
<br>
<br><img src="http://chart.apis.google.com/chart?cht=lxy&amp;chtt=TPS&amp;chs=400x300&amp;chxt=x,x,y&amp;chd=t:0.0,5.3,10.5,15.8,21.1,26.3,31.6,36.8,42.1,47.4,52.6,57.9,63.2,68.4,73.7,78.9,84.2,89.5,94.7,100.0|50.0,50.0,50.0,50.0,50.0,50.0,42.9,50.0,50.0,50.0,50.0,42.9,50.0,42.9,50.0,42.9,50.0,42.9,50.0,42.9|0.0,5.3,10.5,15.8,21.1,26.3,31.6,36.8,42.1,47.4,52.6,57.9,63.2,68.4,73.7,78.9,84.2,89.5,94.7,100.0|100.0,100.0,100.0,100.0,92.9,100.0,92.9,100.0,92.9,100.0,92.9,100.0,92.9,92.9,85.7,100.0,92.9,92.9,92.9,92.9&amp;chco=ff0000,00ff00&amp;chm=d,ff0000,0,-1,5.0|d,00ff00,1,-1,5.0&amp;chdl=myTag|mySecondTag&amp;chxr=2,0,1.4&amp;chxl=0:|06:37:50|06:38:20|06:38:50|06:39:20|06:39:50|06:40:20|06:40:50|1:|Time&amp;chxp=0,0.0,15.8,31.6,47.4,63.2,78.9,94.7|1,50&amp;chg=5.3,10" with="400" height="300">
<br>
<br>
Since the 0.9.8 release a bug with non english locales was fixed. In addition you can now use a csv enabled logfile parser, that generates csv output.
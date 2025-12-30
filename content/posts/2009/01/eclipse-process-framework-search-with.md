---
title: "Eclipse Process Framework - Search with published war file"
date: 2009-01-27 08:54:00 +0000
layout: post
tags: ["oracle", "bea oracle", "weblogic server", "lucene", "EPF", "eclipse"]
slug: "2009-01-27-eclipse-process-framework-search-with"
url: /2009/01/eclipse-process-framework-search-with.html
---

The new <a href="http://www.eclipse.org/projects/project_summary.php?projectid=technology.epf" target="_blank">EPF Composer 1.5</a> is out since some time. The publishing process now got an option to publish sites for processes as .war files.
<br>
This is quite a big step forward. Before this, you had to publish html pages and copy them to your target webserver.
<br>
EPF build in is a lucene search. If you stick to tomcat you never will run into any problems with this construct. If you try to use the Oracle Weblogic to deploy your process.war file, you get into trouble.
<br>
<br>
The root of all evil is the spec. The org.eclipse.epf.web.servlet.SearchServlet uses 
<br>
ServletConfig.getServletContext().getRealPath(index) to determine the lucene index location. If you look at the Servlet Specification you read this:
<br>
<br>
<blockquote>
 <br>
 The real path returned will be in a form appropriate to the computer and
 <br>
 operating system on which the servlet container is running, including the
 <br>
 proper path separators. This method returns null if the servlet container
 <br>
 cannot translate the virtual path to a real path for any reason (such as
 <br>
 when the content is being made available from a .war archive).
 <br>
</blockquote>
<br>
<br>
Tomcat for example returns the path of the actual exploded deployment. Weblogic simply returns null here.
<br>
<br>
<blockquote>
 <br>
 LD&gt; _indexPath: null
 <br>
 LD&gt; _currentSearchString: test
 <br>
 LD&gt; unicodes: test
 <br>
 LD&gt; analyzerName: null
 <br>
 LD&gt; _hits: null
 <br>
 java.lang.NullPointerException
 <br>
  at org.eclipse.epf.web.servlet.SearchServlet.runQuery(SearchServlet.java
 <br>
 :206)
 <br>
  at org.eclipse.epf.web.servlet.SearchServlet.runSearch(SearchServlet.jav
 <br>
 a:174)
 <br>
  at org.eclipse.epf.web.servlet.SearchServlet.doGet(SearchServlet.java:12
 <br>
 9)
 <br>
  at javax.servlet.http.HttpServlet.service(HttpServlet.java:707)
 <br>
  at javax.servlet.http.HttpServlet.service(HttpServlet.java:821)
 <br>
  at weblogic.servlet.internal.StubSecurityHelper$ServletServiceAction.run
 <br>
 (StubSecurityHelper.java:227)
 <br>
  at weblogic.servlet.internal.StubSecurityHelper.invokeServlet(StubSecuri
 <br>
 tyHelper.java:125)
 <br>
  at weblogic.servlet.internal.ServletStubImpl.execute(ServletStubImpl.jav
 <br>
 a:292)
 <br>
  at weblogic.servlet.internal.ServletStubImpl.execute(ServletStubImpl.jav
 <br>
 a:176)
 <br>
  at weblogic.servlet.internal.WebAppServletContext$ServletInvocationActio
 <br>
 n.run(WebAppServletContext.java:3498)
 <br>
  at weblogic.security.acl.internal.AuthenticatedSubject.doAs(Authenticate
 <br>
 dSubject.java:321)
 <br>
  at weblogic.security.service.SecurityManager.runAs(Unknown Source)
 <br>
  at weblogic.servlet.internal.WebAppServletContext.securedExecute(WebAppS
 <br>
 ervletContext.java:2180)
 <br>
  at weblogic.servlet.internal.WebAppServletContext.execute(WebAppServletC
 <br>
 ontext.java:2086)
 <br>
  at weblogic.servlet.internal.ServletRequestImpl.run(ServletRequestImpl.j
 <br>
 ava:1406)
 <br>
  at weblogic.work.ExecuteThread.execute(ExecuteThread.java:201)
 <br>
  at weblogic.work.ExecuteThread.run(ExecuteThread.java:173)
 <br>
</blockquote>
<br>
<br>
Ok. What to do to get this working on Oracle Weblogic Server? You have different options.
<br>
1) you can simply deploy the process.war exploded. No NullPointerException, everything works fine. If this is no option for you, you need to digg deeper
<br>
2) you can change the <a href="http://dev.eclipse.org/viewcvs/index.cgi/org.eclipse.epf/projects/org.eclipse.epf.web/src/org/eclipse/epf/web/servlet/SearchServlet.java?root=Technology_Project&amp;view=markup" target="_blank">org.eclipse.epf.web.servlet.SearchServlet</a> a bit to refer to absolute paths. Look at lines 64/65 and change them to:
<br>
<br>
<blockquote>
 <br>
 _indexPath = config.getInitParameter("searchIndex");
 <br>
 _xslURL = config.getInitParameter("xslFile"); 
 <br>
</blockquote>
<br>
<br>
<br>
 This means, you have to move the index directory out of the war to a separate location on your disc. After this you should also change the web.xml:
<br>
&lt;init-param&gt;
<br>
 &lt;param-name&gt;searchIndex&lt;/param-name&gt;
<br>
 &lt;param-value&gt;Drive:/your/path/to/the/index&lt;/param-value&gt;
<br>
&lt;/init-param&gt;
<br>
<br>
This mean, you always have to distribute the search index as a separate directory. I Should mention here, that I realy did not try this out at all. You may come across some more issues with the org.eclipse.epf.web.search.IndexSearch after this.
<br>
<br>
3) you can do some heavier modifications on the codebase. Its an option to change the lucene directory driver. A good beginning for further research on this could be <a href="http://mail-archives.apache.org/mod_mbox/lucene-java-user/200305.mbox/%3CB519E56A7A231C4286F099EF124CCB7F442E4D@cleut-xmb01%3E" target="_blank">this thread</a> in the <a href="" target="_blank">lucene java-users mailinglist</a>. I will possibly get the change to a more deeper look at the 3rd way in the future ...
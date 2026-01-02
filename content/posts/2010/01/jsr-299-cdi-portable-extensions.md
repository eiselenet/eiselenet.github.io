---
title: "JSR-299 CDI portable extensions"
date: 2010-01-19 07:59:00 +0000
layout: post
tags: ["JSR-299", "Java EE 6"]
slug: "jsr-299-cdi-portable-extensions"
link: "2010/01/jsr-299-cdi-portable-extensions.html"
url: /2010/01/jsr-299-cdi-portable-extensions.html
---

One of my fav features of the new CDI spec is the ability to integrate custom extensions. The <a href="http://docs.jboss.org/cdi/api/1.0/javax/enterprise/inject/spi/package-summary.html" target="_blank">portable extensions spi</a> is the place to start over with it.
<br>
 You can find some examples of portable extensions in <a href="http://blog.hibernate.org/Bloggers/GavinsBlog/Tag/Portable+Extensions" target="_blank">gavins blog</a>. The latest <a href="http://seamframework.org/service/File/105766" target="_blank">weld reference documentation (weld-reference.pdf, 867 KiB, application/pdf)</a> contains a chapter (16) with more details about the extension spi.
<br>
<blockquote>
 CDI is intended to be a foundation for frameworks, extensions and integration with other technologies. Therefore,
 <br>
  CDI exposes a set of SPIs for the use of developers of portable extensions to CDI.
 <br>
</blockquote>
<br>
 Getting started is straight forward. (I was using NetBeans 6.8 with this example)
<br>
 Add a webproject and a separate java library project. Push whatever is needed to your webproject and don't forget to put an empty beans.xml in your WEB-INF/ folder.
<br>
 Now, start writing your extension. 
<br>
<br><b>1) Create a java class</b>
<br>
 that implements the marker interface javax.enterprise.inject.spi.Extension
<br>
<br><code><br>
  public class MyExtension implements Extension \{ <br>
  //...<br>
  \}<br></code>
<br><b>2) Register your extension</b>
<br>
 as a provider by creating a folder named META-INF/services/ in your java library project and putt a file named javax.enterprise.inject.spi.Extension in it. This file has to contain the full qualified name of your extension class (in my case net.eisele.cdi.extensions.MyExtension))
<br>
<br><b>3) Implement the extension logic.</b>
<br>
 Basically extensions listen to events fired by the CDI container and are able to modify the containers metamodel. The events fired are one or all of the following:
<br>
 javax.enterprise.inject.spi.BeforeBeanDiscovery
<br>
 javax.enterprise.inject.spi.ProcessAnnotatedType
<br>
 javax.enterprise.inject.spi.ProcessInjectionTarget and ProcessProducer
<br>
 javax.enterprise.inject.spi.ProcessBean and ProcessObserverMethod
<br>
 javax.enterprise.inject.spi.AfterBeanDiscovery
<br>
 javax.enterprise.inject.spi.AfterDeploymentValidation
<br>
<br>
 You have to add your business methods and declare an @Observes for every event you are willing to catch. Example:
<br><code><br>
  void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) \{<br>
  log.info("Begin the scanning process");<br>
  \}<br></code>
<br>
<br><b>4) You can have a BeanManager</b>
<br>
 injected to your methods, too.
<br><code><br>
  public void getBeanManager(@Observes BeanManager bm)<br></code>
<br>
 The nerve center for extending CDI is the BeanManager object. The BeanManager interface let you obtain beans, interceptors, decorators, observers and contexts programmatically.
<br>
<br><b>5) You can inject your new extension</b>
<br>
 even it is not really a bean.
<br><code><br>
  @Inject<br>
  MyExtension ext;<br></code>
<br>
 or 
<br><code><br>
  @Inject<br>
  MyBean(MyExtension myExtension) \{<br>
  myExtension.doSomething();<br>
  \}<br></code>
<br>
<br>
 If you deploy your app to GlassFish v3 you see something like this in your logfile:
<br>
<blockquote>
 INFO: Begin the scanning process
</blockquote>
<br>
 That was it. You got your first CDI extension up and running.
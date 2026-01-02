---
title: "You should not have any other @ManagedBean beside @Named."
date: 2011-04-30 12:01:00 +0000
layout: post
tags: ["named", "jsf", "managedBean"]
slug: "you-should-not-have-any-other"

url: /2011/04/you-should-not-have-any-other.html
---

Provocative title, I know. But this is what my mind came up with, reading about <a href="https://twitter.com/#!/edburns" target="_blank">@EdBurns</a> latest survey where the JSF EG asks the community: "<a href="" target="_blank">Should we deprecate Managed Beans in #JSF 2.2?</a>". Seeing the first intermediate direction from today, it's the majority of the voters (roughly 80%), which vote for removing/deprecating them.
<br>
 Great indication not only for the JSF EG but also for the whole EE 7 EG. Why?
<br>
<br><b>What's all this about?</b>
<br>
 It's about the <a href="http://download.oracle.com/docs/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/managed-bean-api/index.html" target="_blank">Faces Managed Beans</a>. It defines a couple of Annotations for automatically registering classes with the runtime as a managed bean class within specific scopes. Classes must be scanned for the presence of this annotation at application startup, before any requests have been serviced. As you can see from the comments, it has been planned to extract the specification for managed beans from JSF and place it into its own specification since the 2.0 (Java EE 6). If you compare the features with the ones provided by Contexts and Dependency Injection (CDI). CDI defines a set of contextual services, provided by Java EE containers, that make it easy for developers to use enterprise beans along with JavaServer Faces technology in web applications. Designed for use with stateful objects, CDI also has many broader uses, allowing developers a great deal of flexibility to integrate different kinds of components in a loosely coupled but type-safe way. If you compare the scopes in javax.enterprise.context.* and javax.faces.bean.* (<a href="" target="_blank">Weld API Docs</a>, <a href="http://download.oracle.com/javaee/6/api/index.html?overview-tree.html" target="_blank">DI API</a>) you see some common names. And that basically is the problem here. You can achieve mostly the same using the javax.faces.bean.ManagedBean or any javax.inject.Named with a CDI Context. This multiple solutions space not only covers JSF but also the javax.annotation.ManagedBean (<a href="http://download.oracle.com/javaee/6/api/index.html?javax/annotation/ManagedBean.html" target="_blank">API</a>) and with it the complete platform. The ManagedBean annotation marks a POJO (Plain Old Java Object) as a ManagedBean. A javax.annotation.ManagedBean supports a small set of basic services such as resource injection, lifecycle callbacks and interceptors. It seems as if this was intended to be a first draft of the new container objects which don't have to implement interfaces or conform to any container requirements except the fact, that they have a special annotation with them. 
<br>
<br><b>What does it mean to remove the Faces Managed Beans?</b>
<br>
 Depending on you view onto the platform it could either be a first step into consolidation of the different ManagedBean approaches with Java EE or even a step to make JSF completely dependent on the Java EE Web Profile. If the Faces Managed Beans feature is dropped, you need any kind of Managed Bean service to the core JSF functionality. A standalone appserver which does not contain one of the mechanisms JSF will depend on (CDI/DI or EE ManagedBeans) will not be able to run it anymore. So, you decide weather or not JSF could be used without a EE profile or not.
<br>
<br><b>What do the experts say?</b>
<br>
 I can only speak for myself here, rate for yourself if you still need the answer from an expert :) I personally voted for removing the Faces Managed Beans. They cause confusion with developers. Removing them force the EE 7 EG to think about a general approach for other specs being in a comparable situation (present and future). The drawback is, you will probably lose the ability to run on very lightweight Java appserver which do not conform to one of the defined profiles and can't provide DI/CDI features. 
<br>
 Further on, there are still complaints about the quality and performance of WELD being the RI for CDI. So, in the end, I believe a couple of actions have to be taken to make the removal of the Faces Managed Beans a success story. But it will be worth it: Looking at the complete Java EE 7 platform!
<br>
<br><a href="" target="_blank">Cast your vote here! Help shape Java EE 7 and beyond!</a>
<br>
<br>
 I would love to read about your thoughts! Please take a minute to express your ideas and rating on that in the comments!
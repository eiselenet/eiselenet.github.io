---
title: "JEE6 - Public Review Draft"
date: 2009-01-26 11:43:00 +0000
layout: post
tags: ["Java EE", "enterprise", "Java EE 6", "ejb 3.1", "jee", "JSR-316", "jee6"]
slug: "2009-01-26-jee6-public-review-draft"
url: /2009/01/jee6-public-review-draft.html
---

The public review period of the JEE6 specification (<a href="http://jcp.org/en/jsr/detail?id=316" target="_blank">JSR-316</a>) started on january 23. It will be open for comments up to the 23 of february.
<br>
 Java EE 6 is the Enterprise Edition of version 6 of the Java platform, and thus will be built on Java SE 6. 
<br>
<br>
 The main focus of this new release will be to include existing JSRs and frameworks to the JEE landscape and to further continue in simplyfing the whole platform. The reach of the Java EE platform has become so broad that it has lost some of its original focus. To refocus the Java EE platform on particular classes of developers and applications, the JSR-316 introduces the concept of Java EE platform Profiles. The public review draft already contains the initial Java™ Platform, Enterprise Edition 6 (Java EE 6) Web Profile Specification.
<br>
<br>
 At the moment the required components of the Web Profile are defined as follows:
<br>
 The following technologies are required components of the Web Profile:
<br>
<ul>
 <li>Servlet 3.0</li>
 <li>JavaServer Pages (JSP) 2.2</li>
 <li>Expression Language (EL) 2.2</li>
 <li>Debugging Support for Other Languages (JSR-45) 1.0</li>
 <li>Standard Tag Library for JavaServer Pages (JSTL) 1.2</li>
 <li>JavaServer Faces (JSF) 2.0</li>
 <li>Common Annotations for Java Platform (JSR-250) 1.1</li>
 <li>Enterprise JavaBeans (EJB) 3.1 Lite</li>
 <li>Java Transaction API (JTA) 1.1</li>
 <li>Java Persistence API (JPA) 2.0</li>
</ul>
<br>
<br>
 The specification team is looking for comments onto including Web Beans (<a href="http://jcp.org/en/jsr/detail?id=299" target="_blank">JSR-299</a>) into the profile or not. The profile definition itself is quite simple. Beside the Web Beans I am missing the possibility to deploy libraries to the container.
<br>
<br>
 Let's see, what other Profiles will be available with the final version and what happens to the application server out there. I personaly expect non of them to provide separate profiles. They all will deliver complete JEE6 functionality.
<br>
 Changes will come to separate webcontainers, like tomcat.
<br>
<br>
 Btw: did you wonder about (EJB) 3.1 Lite? I did!
<br>
 Chapter 2.11 from the EJB 3.1 (<a href="http://jcp.org/en/jsr/detail?id=318" target="_blank">JSR-318</a>) Specification states:
<br>
<blockquote>
 <br>
  The EJB API is comprised of a large feature set with support for implementing business logic in a wide variety of enterprise applications. However, the full range of API contracts is not always crucial for all runtime environments. In addition, the breadth of the full API can present challenges for developers just getting started with Enterprise JavaBeans technology.
 <br>
  For these reasons this specification defines a minimal subset of the EJB API known as EJB Lite. EJB Lite is a proper subset of the full EJB 3.1 API. It contains a small, powerful selection of EJB features suitable for writing portable transactional business logic. The vastly reduced size of the feature set makes it suitable for inclusion in a wider range of Enterprise Java products, many of which have much smaller installation and runtime footprints than a typical full Java EE implementation.
 <br>
  An EJB Lite application is merely an EJB application whose EJB API usage falls within the EJB Lite subset. There are no special APIs defined only for EJB Lite. Therefore, any EJB Lite application can be deployed on any Java EE product that implements Enterprise JavaBeans technology, whether that product
 <br>
  supports EJB Lite or the full EJB API.
 <br>
  The EJB Lite API is composed of the following subset of the EJB API :
 <br>
 <ul>
  <li>Stateless, Stateful, and Singleton Session Bean components</li>
  <li>Local and no-interface view only</li>
  <li>Synchronous method invocations only</li>
  <li>Container-managed transactions / Bean-managed transactions</li>
  <li>Declarative and programmatic Security</li>
  <li>Interceptors</li>
  <li>Deployment Descriptor support (ejb-jar.xml)</li>
 </ul>
 <br>
  An EJB Lite implementation is also required to support Java Persistence 2.0.
 <br>
</blockquote>
<br>
<br>
 There you are. It's a quite minimalistic approach. But after all it's nothing. No full blown EJB and no minimalistic POJOs. What do you think?
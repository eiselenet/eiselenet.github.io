---
title: "Five ways to know how your data looked in the past. Entity Auditing."
date: 2011-01-03 12:21:00 +0000
layout: post
tags: ["entity auditing", "jpa. java ee"]
slug: "2011-01-03-five-ways-to-know-how-your-data-looked"
url: /2011/01/five-ways-to-know-how-your-data-looked.html
---

A transactional application often requires to audit the persistent entities. The reasons for this are different. Sometimes you simply have to be able to track down changes and build up a history of changes. Other frequent requirements are related to long term rollback features or even archiving in accordance with legal audit requirements. There are different approaches to auditing in general. Here is an excerpt of what I know, you could come up with more I am sure.
<br>
<ul>
 <li>Change log<br>
   You only need to know, who changed which entity type.</li>
 <li>Simple change tracking<br>
   You are only interested to know if something changed. Can audit a complete entity or any subset of information from it.</li>
 <li>Complete history<br>
   You need a complete auditing about who changed what and when. Need to keep all old entity versions.</li>
 <li>Complex change tracking<br>
   Need to know who changed which value to which new value and when. Even combined with additional requirements.</li>
</ul>
<br>
 All of the above could be addressed with the below approaches. They generally differ by implementation complexity and performance. The more complex you auditing needs are the more impact you can expect on performance in general. You should also keep in mind, that depending on audit level and change frequency your data volume will grow very fast. 
<br>
 To implement it, there are a couple of technical options. Here are the five ways I would come up with, if you would ask me.
<br>
<br><b>EJB Interceptors</b>
<br>
 You could use EJB Interceptors. All you need is a facade around your entity operations. Create an AditInterceptor and add it to your EJB methods. Adam Bien has a nice and <a href="http://www.adam-bien.com/roller/abien/entry/simplest_possible_ejb_3_11">simple example on his blog</a>.
<br>
<br>
 Pro: Simple programatical approach. 
<br>
 Con: This is not a very entity centric approach. You have to find the right repository facades and methods for auditing. If this is a usable approach depends on the amount of auditing needed.
<br>
<br><b>CDI Interceptors</b>
<br>
 If you don't have EJBs in your application you could also use plain CDI interceptors. Refer to <a href="http://blog.eisele.net/2010/01/jsr-299-cdi-interceptors.html">this older post of mine</a> to see a simple example.
<br>
<br>
 Pros/Cons: Same as EJB Interceptors.
<br>
<br><b>JPA Event listeners</b>
<br>
 If you have plain JPA you could make your application audit changes via the life cycle callback methods. The JPA specification describes how an entity or a separate, stateless entity listener instance can receive callbacks during various life cycle state transition of an entity. Seven transitions are defined: @PrePersist, @PostPersist, @PreRemove, @PostRemove, @PreUpdate, @PostUpdate and @PostLoad. To receive these callbacks either annotate certain methods of the entity class itself or of a separate entity listener class. In addition to this, most of the JPA implementations also have some kind of listeners available, which could be used for this.
<br>
<br>
 Pro: Simple programatical approach. Extended features available from the JPA implementations. 
<br>
 Con: You have to get hand on "old" entities. This is not too simple in general. And you have to understand the lifecycle methods needed here. 
<br>
<br><b>Hibernate Envers</b>
<br><a href="http://www.jboss.org/envers">Envers </a>is library for Hibernate that help to easily achieve audit functionality. It has been created by Adam Warski and is part of Hibernate core since 3.5. Basically you annotate an Entity with @Audited to tell Envers that this class needs to be audited. All fields except those annotated with @NotAudited will be audited. You configure the org.hibernate.envers.event.AuditEventListener with any of the following types: post-insert, post-update, post-delete, pre-collection-update, pre-collection-remove, post-collection-recreate. The org.hibernate.envers.auditTablePrefix and org.hibernate.envers.auditTableSuffix properties are helping to add a prefix or suffix your audit tables. The audit tables contain a REV column, which specifies the object access type. 0 means creation, 1 means modification and 2 means deletion. You could even get an object with a previous version using the AuditReader.find() method. 
<br>
<br>
 Pro: Very easy to implement. Rich API to use. 
<br>
 Con: You more or less archive the complete entity. There is now way ootb to audit which attributes really changed. This is an very generic all or nothing approach.
<br>
<br><b>Database triggers</b>
<br>
 You can for example use some generic "before update" triggers to compare new and old values and log the changes. (See <a href="http://asktom.oracle.com/pls/asktom/f?p=100:11:0::::P11_QUESTION_ID:59412348055">this Ask Tom answer</a> on how to do this.)
<br>
<br>
 Pro: If you know how, it's very easy and right at the heart. 
<br>
 Con: You have a very high database dependency, so it's in general no good idea if you have to have a portable solution at hand.
<br>
<br><b>Bottom line</b>
<br>
 Whatever requirements you have. I can promise you, that it will never be a simple solution. In general it's best to evaluate your purpose for auditing in detail. In order to have an appropriate auditing strategy and to avoid unnecessary auditing, you must have a clear understanding of the reasons for auditing. In order to prevent unnecessary audit information from cluttering the meaningful information, it is important to audit the minimum number of statements, users, or objects required to get the targeted information.
---
title: "The Future of NoSQL with Java EE"
date: 2012-05-07 08:28:00 +0000
layout: post
tags: ["NoSQL", "javaee", "EclipseLink", "HibernateOGM"]
slug: "2012-05-07-future-of-nosql-with-java-ee"
url: /2012/05/future-of-nosql-with-java-ee.html
---

I've been following the recent NoSQL momentum since some time now and it seems as if this buzzword also is drawing some kind of attention in the enterprise java world. Namely EclipseLink 2.4 started <a href="https://blogs.oracle.com/arungupta/entry/java_ee_6_and_nosql" target="_blank">supporting MongoDB and Oracle NoSQL</a>. Having EclipseLink as the JPA reference implementation you might wonder what this means for Java EE 7. A short side-note here: Even if I am part of the JSR-342 EG this isn't meant to be an official statement. In the following I simply try to summarize my own personal experiences and feelings towards NoSQL support with future Java EE versions. A big thank you goes out to Emmanuel Bernard for providing early feedback! Happy to discuss what follows:
<br>
<br><b>What is NoSQL?</b>
<br>
 NoSQL is a classification of database systems that do not conform to the relational database or SQL standard. Most often they are categorized according to the way they store the data and fall under categories such as key-value stores, BigTable implementations, document store databases, and graph databases. In general the term isn't well enough defined to reduce it to a single supporting JSR or technology. So the only way to find suitable integration technologies is to dig through every single category. 
<br>
<br><b>Key/Value Stores</b>
<br>
 Key/Value stores allow data storage in a schema-less way. It could be stored in a datatype of a programming language or an object. Because of this, there is no need for a fixed data model. This is obviously comparable to&nbsp;parts of <a href="http://jcp.org/en/jsr/detail?id=338" target="_blank">JSR 338</a> (Java Persistence 2.1) and&nbsp;<a href="http://jcp.org/en/jsr/summary?id=347" target="_blank">JSR 347</a> ( Data Grids for the Java Platform) and also&nbsp;to what is done with&nbsp;<a href="http://jcp.org/en/jsr/summary?id=107" target="_blank">JSR 107</a>&nbsp;(<a href="" target="_blank">JCACHE - Java Temporary Caching API</a>).
<br>
<br>
<br><i>with native JPA2</i>
<br>
 Also primary aimed at caching is the JPA L2 Cache. The JPA Cache API is good for basic cache operations, while L2 cache shares the state of an entity -- which is accessed with the help of the entity manager factory -- across various persistence contexts. Level 2 cache underlies the persistence context, which is highly transparent to the application. When Level 2 cache is enabled, the persistence provider will look for the entities in the persistence context first. If it does not find them there, the persistence provider will look in the Level 2 cache next instead of sending a query to the database. The drawback here obviously is, that as of today this only works with NoSQL as some kind of "Cache". And not as a replacement for the RDBMS data store. Given the scope of this spec it would be a good fit: But I strongly believe that JPA is designed to be an abstraction on RDBS and nothing else. If there has to be some kind of support for non relational databases we might end up having a more high level abstraction layer in place which tons of different persistence modes and features (maybe something like&nbsp;<a href="http://www.springsource.org/spring-data" target="_blank">Spring Data</a>). Generally&nbsp;mapping at the object level has many advantages including the ability to think object&nbsp;and let the underlying engine drive the de-normalization if needed. So reducing JPA to the caching features probably is the wrong decision.
<br>
<br>
<br><i>with JCache</i>
<br>
 JCache having a CacheManager that holds and controls a collection of Caches and every single Caches have it's entries. The basic API can be thought of map-like with additional features (compare <a href="" target="_blank">Greg's blog</a>). With JCache being designed as a "Cache" using it as a standardized interface against NoSQL data stores this isn't a good fit on the first look. But given the nature of the use-cases for unstructured Key/Value based data with enterprise java this might be the right kind of integration. And the NoSQL concept also allows for the "Key-value cache in RAM" category which is an exact fit for both JCache and DataGrids.
<br>
<br><i>with DataGrids</i>
<br>
 This JSR proposes an API for interacting with in-memory and disk-based distributed data grids. The API aims to allow users to perform operations on the data grid (PUT, GET, REMOVE) in an asynchronous and non-blocking manner returning a java.util.concurrent.Futures rather than the actual return values. The process here is not really visible at the moment (at least to me). So there aren't any examples or concepts for integration of a NoSQL Key/Value store available until today. Beside this the same reservations as for the JCache API are in place.
<br>
<br><i>with EclipseLink</i>
<br><a href="http://wiki.eclipse.org/EclipseLink/Examples/JPA/NoSQL" target="_blank">EclipseLink's NoSQL</a> support is based on previous EIS support offered since EclipseLink 1.0. <a href="http://wiki.eclipse.org/Introduction_to_EIS_Projects_(ELUG)" target="_blank">EclipseLink's EIS</a> support allowed persisting objects to legacy and non-relational databases. EclipseLink's EIS and NoSQL support uses the Java Connector Architecture (JCA) to access the data-source similar to how EclipseLink's relational support uses JDBC. EclipseLink's NoSQL support is extendable to other NoSQL databases, through the creation of an EclipseLink EISPlatform class and a JCA adapter. At the moment it supports MongoDB (Document Oriented) and Oracle NoSQL (BigData). It's interesting to see, that Oracle doesn't address the Key/Value DBs first. Might be because of the possible confusion with the Cache features (e.g. Coherence).
<br>
<br><b>Column based DBs</b>
<br>
 Read and write is done using columns rather than rows. The best known examples are Google's BigTable and the likes of HBase and Cassandra that were inspired by BigTable. The BigTable paper says that BigTable is a sparse, distributed, persistent, multidimensional sorted Map. GAE for example works only with BigTable. It offers variety of APIs: from "native" low-level API to "native" high-level ones (<a href="http://www.oracle.com/technetwork/java/index-jsp-135919.html" target="_blank">JDO</a> and <a href="https://developers.google.com/appengine/docs/java/datastore/jpa/overview?hl=en" target="_blank">JPA</a>). With the older Datanucleus version used by Google there seem to be a lot of limitations in place which could be removed (<a href="http://www.datanucleus.org/products/accessplatform/appengine/support.html" target="_blank">see comments</a>) but still are in place.
<br>
<br><b>Document-oriented DBs</b>
<br>
 The Document-oriented DBs are most obviously best addressed by <a href="http://jcp.org/en/jsr/summary?id=170">JSR 170</a> (Content Repository for Java) and&nbsp;<a href="http://jcp.org/en/jsr/summary?id=283">JSR 283</a> (Content Repository for Java Technology API Version 2.0). With <a href="http://en.wikipedia.org/wiki/Apache_Jackrabbit" target="_blank">JackRabbit</a> as a reference implementation it's a strong sign for that :) The support for other NoSQL document stores is non existent as of today. Even Apache's CouchDB doesn't provide a JSR 170/283 compliant way of accessing the documents. &nbsp;The only drawback is that both JSR's aren't sexy or bleeding edge. But for me this would be the right bucket to put support for document-oriented DBs.&nbsp;Flip side&nbsp;of the medal?&nbsp;The content &nbsp;repository API isn't exactly a natural model for an application. Does an app really want to deal with Nodes and attributes in Java?&nbsp;The notion of a domain model works nicely for many apps and if there is no chance to use it, you probably would be better off going native and use the MondoDB driver directly.
<br>
<br><b>Graph oriented DBs</b>
<br>
 This kind of databases are thought for data whose relations are well represented with a graph-style (elements interconnected with an undetermined number of relations between them). Aiming primarily at any kind of network topology the recently rejected JSR 357 (Social Media API) would have been a good place to put support. At least from a use-case point of view. If those graph-oriented DBs are considered as a data-store there are a couple of options. If the Java EE persistence is steering into the direction of a more general data abstraction layer the 338 or it's successors would be the right place to put support. If you know a little bit about how Coherence works internally and what had to be done to put JPA on top of it you also could consider 347 a good fit for it. With all the drawbacks already mentioned. Another alternative would be to have a separate JSR for it. The most prominent representative of this category is Neo4J which itself has an easy API available to simply include everything you need directly into your project. There is additional stuff to consider if you need to control the Neo4J instance via the application server.
<br>
<br><b>Conclusion</b>
<br>
 To sum it up: We already have a lot in place for the so-called "NoSQL" DBs. And the groundwork for integrating this into new Java EE standards is promising. Control of embedded NoSQL instances should be done via JSR 322 (Java EE Connector Architecture) with this being the only allowed place spawn threads and open files directly from a filesystem. I'm not a big supporter of having a more general data abstraction JSR for the platform comparable to what Spring is doing with Spring Data. To me the concepts of the different NoSQL categories are too different than to have a one-size-fits-all approach.&nbsp;The main pain point of NoSQL besides the lack of standard API is that users are forced to denormalize and maintain de-normalization by hand.
<br>
<br>
 What I would like to see are some smaller changes to both the products to be more Java EE ready and also to the way the integration into the specs is done. Might be a good idea to simply define the different persistence types and generally define the JSRs which could be influenced by this and noSQLing those accordingly.
<br>
<br>
<br>
 For users willing to&nbsp;facilitate&nbsp;a domain model (ie a higher level of abstraction compared to the raw NoSQL API), JPA might be the best vehicle for that at the moment. The feedback from both EclipseLink and <a href="http://www.hibernate.org/subprojects/ogm.html" target="_blank">Hibernate OGM</a> users is needed to value what is working and what not. From a political point of view it might also make sense to&nbsp;pursue&nbsp;347.&nbsp;Especially since main big players are present here&nbsp;already.
<br>
<div>
 <br>
</div> The really hard part is querying.&nbsp;&nbsp;Should there be standardized query APIs for each family? With Java EE? Or would that better be placed within the NoSQL space? Would love to read your feedback on this!
<br>
<br>
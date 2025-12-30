---
title: "Tune your Weblogic Server Application"
date: 2009-08-20 05:20:00 +0000
layout: post
tags: ["performance", "tuning", "weblogic server"]
slug: "2009-08-20-tune-your-weblogic-server-application"
url: /2009/08/tune-your-weblogic-server-application.html
---

During the days of the worldwide financial crisis the wish for effective and efficient applications comes up more often, too. If you have a performing application you could possible use some smaller hardware or serve more users. This article compiles some performance tuning aspects, you could use together with your JEE Application and Weblogic server. Performance tuning is one of the most challenging parts of application development. It’s highly dependent on the platform you are running and you need quite a bit experience to get the right feeling for the right screws to twiddle. This article is meant as an overview to give a brief understanding of the places, you can put your hands on. Anyway, as in most cases the overall solution will not be to change all described settings but find the ones, relevant for your application needs.
<br>
<br>
<br><span style="font-weight:bold;">Pool Sizes</span>
<br>
Pool Size is the magic deep inside of your Weblogic. For nearly all kinds of ressources you can configure them. This includes pools for JDBC connections, Stateless Session EJBs, and MDBs.
<br>
If you increase the Pool Size it will maximize concurrency for the expected thread utilization. In almost any case, when your application is slow and you CPU is still down, you will get a performance boost. 
<br>
Introduced with WebLogic Server releases 9.0 every server instance uses a self-tuned thread-pool. Even if it is self-tuned, you can still optimize such pools by configuring the Workmanagers.
<br>
Before 9.x series you were able to configure separate thread pools for separate objects. Now all Weblogic Server instances use one single thread pool, in which all types of work are executed. 
<br>
The server prioritizes work based on defined rules and run-time metrics, including the actual time it takes to execute a request and the rate at which requests are entering and leaving the pool.
<br>
On behalf of this the thread pool changes its size automatically to maximize throughput. The queue monitors throughput over time and based on history, determines whether to adjust the thread count or not. 
<br>
For example, if historical throughput statistics indicate that a higher thread count increased throughput, the server increases it. Similarly, if statistics indicate that fewer threads did not reduce throughput, the count will be reduced. 
<br>
To modify this behaviour you can configure a set of scheduling guidelines and associate them with one or more applications, or with particular application components (e.g. Servlets, EJB). 
<br>
<br>
To manage work in your applications, you define one or more of the following Work Manager components:
<br>
<br>
• Fair Share Request Class
<br>
• Response Time Request Class
<br>
• Min Threads Constraint
<br>
• Max Threads Constraint
<br>
• Capacity Constraint
<br>
• Context Request Class
<br>
<br>
You can configure Work Managers at the domain level, application level, and module level in one of the following configuration files, or using the administration console:
<br>
<br>
• config.xml 
<br>
Work Managers defined here can be assigned to any application, or application component, in the domain.
<br>
• weblogic-application.xml 
<br>
Work Managers defined here can be assigned to that application, or any component of that application.
<br>
• weblogic-ejb-jar.xml or weblogic.xml 
<br>
Work Managers specified at the component-level can only be assigned to that specific component.
<br>
• weblogic.xml 
<br>
Here you can configure Work Managers for a Web application.
<br>
<br>
Here is a sample configured to use Request Classes:
<br>
<br><code><br>
 &lt;work-manager&gt;<br>
 &lt;name&gt;highpriority_workmanager&lt;/name&gt;<br>
  &lt;fair-share-request-class&gt;<br>
  &lt;name&gt;high_priority&lt;/name&gt; <br>
  &lt;fair-share&gt;100&lt;/fair-share&gt; <br>
  &lt;/fair-share-request-class&gt;<br>
  &lt;min-threads-constraint&gt;<br>
  &lt;name&gt;MinThreadsCountFive&lt;/name&gt;<br>
  &lt;count&gt;5&lt;/count&gt;<br>
  &lt;/min-threads-constraint&gt;<br>
 &lt;/work-manager&gt;<br></code>
<br>
<br>
You can configure Request Classes, Context Request Class, Constraints and Stuck Thread Handling components within a workmanager. For more details on all of them, have a look at the <a href="http://edocs.bea.com/wls/docs103/config_wls/self_tuned.html#wp1064787" target="_blank">official documentation</a>. 
<br>
<br>
To reference the workmanager, you just put a reference comparable to the following into your component configuration file (e.g. web.xml) for example:
<br>
<br><code><br>
 &lt;init-param&gt;<br>
  &lt;param-name&gt;wl-dispatch-policy&lt;/param-name&gt;<br>
  &lt;param-value&gt;highpriority_workmanager&lt;/param-value&gt;<br>
 &lt;/init-param&gt;<br></code>
<br>
<br>
The best way to determine the appropriate pool size is to monitor the pool's current size, shrink counts, grow counts, and wait counts during an application run. To do this, just have a look at the diagnostics console within the Weblogic admin console. During normal operations the preconfigured self-tuned thread-pool does all the work for you. Tuning Work Managers can have positive but also negative effects. Therefore you need quite a bit experience to do this the right way.
<br>
<br>
<br><span style="font-weight:bold;">Prepared Statement Cache </span>
<br>
When you use a prepared statement or callable statement in an application or EJB, there is considerable processing overhead for the communication between the application server and the database server and on the database server itself. To minimize the processing costs, WLS can cache prepared and callable statements used in your applications. When an application or EJB calls any of the cached statements they will be reused out of the cache. This reduces CPU usage on the database server which improves performance for the current statement and leaves enough CPU cycles for other tasks. 
<br>
<br>
Each connection in a data source has its own individual cache of prepared and callable statements used on the connection. However, you configure statement cache options per data source. That is, the statement cache for each connection in a data source uses the statement cache options specified for the data source, but each connection caches its own statements. Statement cache configuration options include: 
<br>
• Statement Cache Type
<br>
This defines the algorithm which determines the statements to store in the statement cache. 
<br>
o When you select LRU (Least Recently Used, the default) as the Statement Cache Type, WebLogic Server caches prepared and callable statements used on the connection until the statement cache size is reached.
<br>
o When you select FIXED as the Statement Cache Type, WebLogic Server caches prepared and callable statements used on the connection until the statement cache size is reached. When additional statements are used, they are not cached. 
<br>
<br>
• Statement Cache Size
<br>
The number of statements to store in the cache for each connection. The default value is 10.
<br>
<br>
The Statement Cache Size attribute determines the total number of prepared and callable statements to cache for each connection in each instance of the data source. By caching statements, you can increase your system performance. However, you must consider how your DBMS handles open prepared and callable statements. In many cases, the DBMS will maintain a cursor for each open statement. This applies to prepared and callable statements in the statement cache. If you cache too many statements, you may exceed the limit of open cursors on your database server.
<br>
<br>
For example, if you have a data source with 10 connections deployed on 2 servers, if you set the Statement Cache Size to 10 (the default), you may open 200 (10 x 2 x 10) cursors on your database server for the cached statements. 
<br>
<br>
You configure the Statement Cache Options within the Weblogic Admin Console at a single Datasource.
<br>
<br><span style="font-weight:bold;">Logging Last Resource Optimization</span>
<br>
When using transactional database applications, consider using the JDBC data source Logging Last Resource (LLR) transaction policy instead of XA. The LLR optimization can significantly improve transaction performance by safely eliminating some of the 2PC XA overhead for database processing, especially for two-phase commit database insert, update, and delete operations.
<br>
<br>
<br>
WebLogic Server supports the Logging Last Resource (LLR) transaction optimization through JDBC data sources. LLR is a performance enhancement option that enables one non-XA resource to participate in a global transaction with the same ACID guarantee as XA. LLR is a refinement of the “Last Agent Optimization.” It differs from Last Agent Optimization in that it is transactionally safe. The LLR resource uses a local transaction for its transaction work. The WebLogic Server transaction manager prepares all other resources in the transaction and then determines the commit decision for the global transaction based on the outcome of the LLR resource’s local transaction.
<br>
<br>
The LLR optimization improves performance by:
<br>
<br>
• Removing the need for an XA JDBC driver to connect to the database. XA JDBC drivers are typically inefficient compared to non-XA JDBC drivers.
<br>
• Reducing the number of processing steps to complete the transaction, which also reduces network traffic and the number of disk I/Os.
<br>
• Removing the need for XA processing at the database level
<br>
<br>
• When a connection from a data source configured for LLR participates in a two-phase commit (2PC) global transaction, the WebLogic Server transaction manager completes the transaction by:
<br>
<br>
• Calling prepare on all other (XA-compliant) transaction participants.
<br>
• Inserting a commit record to a table on the LLR participant (rather than to the file-based transaction log).
<br>
• Committing the LLR participant's local transaction (which includes both the transaction commit record insert and the application's SQL work).
<br>
• Calling commit on all other transaction participants.
<br>
<br>
For a one-phase commit (1PC) global transaction, LLR eliminates the XA overhead by using a local transaction to complete the database operations, but no 2PC transaction record is written to the database.
<br>
<br>
The Logging Last Resource optimization maintains data integrity by writing the commit record on the LLR participant. If the transaction fails during the local transaction commit, the WebLogic Server transaction manager rolls back the transaction on all other transaction participants. For failure recovery, the WebLogic Server transaction manager reads the transaction log on the LLR resource along with other transaction log files in the default store and completes any transaction processing as necessary. Work associated with XA participants is committed if a commit record exists, otherwise their work is rolled back. 
<br>
<br>
Configure the LLR at a single Data Source (Transactions-Options Page) via the adminstration console. For more optimization information have a look at the <a href="http://e-docs.bea.com/wls/docs103/jta/llr.html" target="_blank">Programming WebLogic JTA section</a>
<br>
<br>
<br><span style="font-weight:bold;">Connection Backlog Buffering </span>
<br>
You can tune the number of connection requests that a WebLogic Server instance will accept before refusing additional requests. The Accept Backlog parameter specifies how many Transmission Control Protocol (TCP) connections can be buffered in a wait queue. This fixed-size queue is populated with requests for connections that the TCP stack has received, but the application has not accepted yet. 
<br>
You can configure the Backlog Buffering at a single server instance via the administration console.
<br>
To not get in trouble with this, you should have a look at the basic <a href="http://edocs.bea.com/wls/docs103/perform/OSTuning.html#wp1125571" target="_blank">OS Tuning information on TCP for Webglogic Server</a>
<br>
<br><span style="font-weight:bold;">Chunk Size</span>
<br>
A chunk is a unit of memory that the WebLogic Server network layer, both on the client and server side, uses to read data from and write data to sockets. To reduce memory allocation costs, a server instance maintains a pool of these chunks. For applications that handle large amounts of data per request, increasing the value on both the client and server sides can boost performance. The default chunk size is about 4K. Use the following properties to tune the chunk size and the chunk pool size:
<br>
<br>
• weblogic.Chunksize
<br>
Sets the size of a chunk (in bytes). The primary situation in which this may need to be increased is if request sizes are large. It should be set to values that are multiples of the network’s maximum transfer unit (MTU), after subtracting from the value any Ethernet or TCP header sizes. Set this parameter to the same value on the client and server.
<br>
• weblogic.utils.io.chunkpoolsize
<br>
Sets the maximum size of the chunk pool. The default value is 2048. The value may need to be increased if the server starts to allocate and discard chunks in steady state. To determine if the value needs to be increased, monitor the CPU profile or use a memory/ heap profiler for call stacks invoking the constructor weblogic.utils.io.Chunk.
<br>
• weblogic.PartitionSize
<br>
Sets the number of pool partitions used (default is 4). The chunk pool can be a source of significant lock contention as each request to access to the pool must be synchronized. Partitioning the thread pool spreads the potential for contention over more than one partition.
<br>
<br><span style="font-weight:bold;">JPA instead of CMP </span>
<br>
Ok. It's not a big secret anyhow. CMP is dead and the following section should be outdated at all. Anyway, there are a lot of older applications out there and not all of them will be migrated to JPA. If you are planning this, you are safer with performance tunings of your JPA implementation at all. From a Weblogic point of view this is/was Kodo. I personaly never really was a friend of Kodo. And after Bea went over to Oracle we all should stick to what is far better: ToplinkEssentials and EclipseLink. Nobody minds if you use Hibernate. If you are using JPA, the first thing to keep an eye open for is the fetching strategy. Basacaly you can choose between eager and lazy loading. Depending on the implementation you use, there may be additional fetch strategies. If you have them, use them and make them fit your applications needs. Beside the basics, all frameworks offer some more advanced performance management options. Starting from caching (which is no part of the specification so far) up to special monitoring and optimizing tools.
<br>
<br><span style="font-weight:bold;">Optimistic or Read-only Concurrency </span>
<br>
Use optimistic concurrency with cache-between-transactions or read-only concurrency with query-caching for CMP EJBs wherever possible. Both of these two options leverage the Entity Bean cache provided by the EJB container.
<br>
<br>
• Optimistic-concurrency with cache-between-transactions work best with read-mostly beans. Using verify-reads in combination with these provides high data consistency guarantees with the performance gain of caching. 
<br>
<br>
The EJB Container caches stateful session beans in memory up to a count specified by the max-beans-in-cache parameter specified in weblogic-ejb-jar.xml. This parameter should be set equal to the number of concurrent users. This ensures minimum passivation of stateful session beans to disk and subsequent activation from disk which yields better performance. 
<br>
<br>
Entity beans are cached at two levels by the EJB container. Transaction-Level Caching, once an entity bean has been loaded from the database, it is always retrieved from the cache whenever it is requested when using the findByPrimaryKey or invoked from a cached reference in that transaction. Note that getting an entity bean using a non-primary key finder always retrieves the persistent state of the bean from the data base. Caching between transactions, entity bean instances are also cached between transactions. However, by default, the persistent state of the entity beans are not cached between transactions. To enable caching between transactions, set the value of the cache-between-transactions parameter to true. 
<br>
<br>
Is it safe to cache the state? This depends on the concurrency-strategy for that bean. The entity-bean cache is really only useful when cache-between-transactions can be safely set to true. In cases where ejbActivate() and ejbPassivate() callbacks are expensive, it is still a good idea to ensure the entity-cache size is large enough. Even though the persistent state may be reloaded at least once per transaction, the beans in the cache are already activated. The value of the cache-size is set by the deployment descriptor parameter max-beans-in-cache and should be set to maximize cache-hits. In most situations, the value need not be larger than the product of the number of rows in the table associated with the entity bean and the number of threads expected to access the bean concurrently. 
<br>
<br>
• Query-caching is a WebLogic Server 9.0 feature that allows the EJB container to cache results for arbitrary non-primary-key finders defined on read-only EJBs. All of these parameters can be set in the application/module deployment descriptors.
<br>
<br>
The concurrency-strategy deployment descriptor tells the EJB container how to handle concurrent access of the same entity bean by multiple threads in the same server instance. Set this parameter to one of four values: Exclusive, Database, Optimistic and ReadOnly.
<br>
<br>
The ReadOnly value is the most performant. When selected, the container assumes the EJB is non-transactional and automatically turns on cache-between-transactions. Bean states are updated from the data base at periodic, configurable intervals or when the bean has been programmatically invalidated. The interval between updates can cause the persistent state of the bean to become stale. The ReadOnly is the only concurrency-strategy for which query-caching can be used. 
<br>
Entity bean instances are also cached between transactions. However, by default, the persistent state of the entity beans are not cached between transactions. To enable caching between transactions, set the value of the cache-between-transactions parameter to true. 
<br>
<br>
<br><span style="font-weight:bold;">Local Interfaces </span>
<br>
Use local-interfaces or use call-by-reference semantics to avoid the overhead of serialization when one EJB calls another or an EJB is called by a servlet/JSP in the same application. Prior to Weblogic Server versions 8.1 call-by-reference is turned on by default.For releases of WebLogic Server 8.1 and higher, call-by-reference is turned off by default.
<br>
<br>
You can do this in the weblogic-ejb-jar.xml for example like this:
<br>
<br><code><br>
 &lt;weblogic-enterprise-bean&gt;<br>
  &lt;entity-descriptor&gt;<br>
  &lt;ejb-name&gt;AccountBean&lt;/ejb-name&gt;<br>
  ...<br>
  &lt;enable-call-by-reference&gt;False&lt;/enable-call-by-reference&gt;<br>
  &lt;/entity-descriptor&gt;<br>
 &lt;/weblogic-enterprise-bean&gt;<br></code>
<br>
<br><span style="font-weight:bold;">Use Relationship caching (if using CMP)</span>
<br>
Using eager relationship caching allows the EJB container to load related entity beans using a single SQL join. Use only when the same transaction accesses related beans.
<br>
Relationship caching—also known as “pre-fetching” or “eager relationship caching’—improves the performance of entity beans by loading related beans into the cache and preventing multiple queries by issuing a join query for the related bean. If a set of beans is accessed as part of the same unit of work, then your application should load them into cache at the same time. 
<br>
<br>
• Relationship caching is supported for one-to-one, one-to-many, and many-to-one relationships. It is not supported for many-to-many relationships.
<br>
• When using weblogic-ql, this feature only works with finder methods that return references to either EJBObject or EJBLocalObject beans.
<br>
<br>
Specify the relationship-caching element in weblogic-cmp-jar.xml, as shown in this example:
<br>
<br><code><br>
 &lt;relationship-caching&gt;<br>
  &lt;caching-name&gt;cacheMoreBeans&lt;/caching-name&gt;<br>
  &lt;caching-element&gt;<br>
  &lt;cmr-field&gt;accounts&lt;/cmr-field&gt;<br>
  &lt;group-name&gt;acct_group&lt;/group-name&gt;<br>
  &lt;caching-element&gt;<br>
  &lt;cmr-field&gt;address&lt;/cmr-field&gt;<br>
  &lt;group-name&gt;addr_group&lt;/group-name&gt;<br>
  &lt;/caching-element&gt;<br>
  &lt;/caching-element&gt;<br><br>
  &lt;caching-element&gt;<br>
  &lt;cmr-field&gt;phone&lt;/cmr-field&gt;<br>
  &lt;group-name&gt;phone_group&lt;/group-name&gt;<br>
  &lt;/caching-element&gt;<br>
 &lt;/relationship-caching&gt;<br></code>
<br>
<br><span style="font-weight:bold;">HTTP Sessions </span>
<br>
As a general rule, you should optimize your application so that it does as little work as needed with sessions. If possible, you should always try to get away without using any session persistance at all. But you required, wls offers five session persistence mechanisms you can use. They are configurable at the Web application layer. Which mechanism you choose for your application depends on various factors, like session size, session life cycle, reliability, and session failover requirements. For example, a Web application with no failover requirements could be maintained as a single memory-based session; whereas, a Web application with session fail-over capability could be maintained as replicated sessions or JDBC-based sessions, based on their life cycle and object size.
<br>
<br>
In terms of pure performance, in-memory session persistence is a better overall choice when compared to JDBC-based persistence for session state.While all session persistence mechanisms have to deal with the overhead of data serialization and deserialization, the additional overhead of the database interaction impacts the performance of the JDBC-based session persistence and causes it to under-perform compared with the in-memory replication. However, in-memory-based session persistence requires the use of WebLogic clustering, so it isn’t an option in a single-server environment. 
<br>
<br>
On the other hand, an environment using JDBC-based persistence does not require the use of WebLogic clusters and can maintain the session state for longer periods of time in the database. One way to improve JDBC-based session persistence is to optimize your code so that it has as high a granularity for session state persistence as possible. Other factors that can improve the overall performance of JDBC-based session persistence are: the choice of database, proper database server configuration, JDBC driver, and the JDBC connection pool configuration. 
<br>
<br>
WebLogic Server tracks and replicates changes in the session by attribute so you should:
<br>
<br>
• Aggregate session data that changes in tandem into a single session attribute.
<br>
• Aggregate session data that changes frequently and read-only session data into separate session attributes
<br>
<br>
For example: If you use a a single large attribute that contains all the session data and only 10% of that data changes, the entire attribute has to be replicated. This causes unnecessary serialization/deserialization and network overhead. You should move the 10% of the session data that changes into a separate attribute. 
<br>
<br>
<br><span style="font-weight:bold;">Performance test and profile your application</span>
<br>
Last but not least you should keep an eye on the overall performance of your application. There are many different ways to do this. Some of them are more difficult than others. Which type you choose, depends on the results you want to achieve. If you want to have a quickstart into performance tests and methodologies, have a look at the articel <a href="" http: www.oracle.com technology pub articles dev2arch 2005 09 performance_testing.html>"Approaches to Performance Testing"</a> on otn from Matt Maccaux.
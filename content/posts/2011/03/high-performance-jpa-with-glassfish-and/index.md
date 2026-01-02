---
title: "High Performance JPA with GlassFish and Coherence - Part 3"
date: 2011-03-04 08:29:00 +0000
layout: post
tags: ["glassfish", "jpa", "Coherence"]
slug: "high-performance-jpa-with-glassfish-and"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2011/03/high-performance-jpa-with-glassfish-and.html
---

In this third part of my <a href="http://blog.eisele.net/2011/02/high-performance-jpa-with-glassfish-and.html">four part series</a> I'll explain strategy number two of using Coherence with EclipseLink and GlassFish. This is all about using Coherence as Second Level Cache (L2) with EclipseLink.
<br>
<br><b>General approach</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="gf_coherence_2.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="320" src="gf_coherence_2.png" width="219"></a>
</div>This approach applies Coherence data grid to JPA applications that rely on database hosted data that cannot be entirely pre-loaded into a Coherence cache. Some reasons why it might not be able to pre-loaded include extremely complex queries that exceed the feature set of Coherence Filters, third party database updates that create stale caches, reliance on native SQL queries, stored procedures or triggers, and so on. This is not only an option for local L2 Caches but with additional configured Coherence instances on different nodes, you also get a cluster-wide JPA L2 Cache. 
<br>
<br><b>Details</b>
<br>
 As with many Caches, this is a read-mostly optimization. Primary key queries attempt to get entities first from Coherence and, if unsuccessful, will query the database, updating Coherence with query results. Non-primary key queries are executed against the database and the results checked against Coherence to avoid object construction costs for cached entities. Newly queried entities are put into Coherence. Write operations update the database and, if successfully committed, updated entities are put into Coherence. This approach is called "Grid Cache" in the Coherence documentation.
<br>
<br><b>Move it into practice</b>
<br>
 Start with the previous blog-post and <a href="http://blog.eisele.net/2011/02/high-performance-jpa-with-glassfish-and_14.html#preparation">prepare your environment</a>, if you have not already done so. There is a single thing, you need to change. Go back to GlassFish 3.0.1 / EclipseLink 2.0.1 for this scenario as there is a problem with the CacheKey.getKey() method. The 2.0.1 returns <a href="http://www.eclipse.org/eclipselink/api/2.0/org/eclipse/persistence/sessions/interceptors/CacheKeyInterceptor.html" target="_blank">a Vector</a>, the 2.2.0 simply returns <a href="http://www.eclipse.org/eclipselink/api/2.1/org/eclipse/persistence/sessions/interceptors/CacheKeyInterceptor.html" target="_blank">an Object</a>. Seeing the new Oracle GlassFish Server 3.1 having support for ActiveCache, I expect this to be fixed with the 3.7 Coherence release. But until than, you have to stick to the old GF or EclipseLink.
<br>
 Anyway, lets create a new web project with your favorite IDE (e.g. GridCacheExample). Add the required libraries (coherence.jar, toplink-grid.jar and the eclipselink.jar). Now let's create our entity class and add the extra @CacheInterceptor annotation to it:
<br><code><br>
  ...<br>
  import oracle.eclipselink.coherence.integrated.cache.CoherenceInterceptor;<br>
  import org.eclipse.persistence.annotations.CacheInterceptor;<br>
  ...<br><br>
  @Entity<br>
  @CacheInterceptor(value = CoherenceInterceptor.class)<br>
  public class Employee implements Serializable \{<br>
  ...<br>
  \}<br></code>
<br>
 Don't forget to add the @GeneratedValue(strategy= GenerationType.SEQUENCE) as this is needed in opposite to the last example. After this is done, you have to add the coherence configuration to your WEB-INF/classes folder. You can start from the tutorial (<a href="http://download.oracle.com/docs/cd/E17904_01/doc.1111/e16596/configjpa.htm#BGBJIHCD" target="_blank">Example 2</a>). (be careful, there is a typo in it ... a dublicate &lt;/backing-map-scheme&gt; tag). Configure your persistence.xml as you would do with a normal JPA based application.
<br><code><br>
  &lt;persistence-unit name="GridCacheExamplePU" transaction-type="JTA"&gt;<br>
  &lt;provider&gt;org.eclipse.persistence.jpa.PersistenceProvider&lt;/provider&gt;<br>
  &lt;jta-data-source&gt;jdbc/coherence&lt;/jta-data-source&gt;<br>
  &lt;properties&gt;<br>
  &lt;property name="eclipselink.ddl-generation" value="drop-and-create-tables"/&gt;<br>
  &lt;property name="eclipselink.logging.level" value="FINE" /&gt;<br>
  &lt;/properties&gt;<br>
  &lt;/persistence-unit&gt;<br></code>
<br>
 That's it basically. Now you can test your new L2 cache. A simple servlet should do the trick:
<br><code><br>
  public class InsertServletPart3 extends HttpServlet \{<br>
  @PersistenceUnit(unitName = "GridCacheExamplePU")<br>
  EntityManagerFactory emf;<br>
  @Resource<br>
  UserTransaction tx;<br>
  ...<br><br>
  EntityManager em = emf.createEntityManager();<br>
  tx.begin();<br>
  // some loop magic<br>
  Employee employee = new Employee();<br>
  employee.setFirstName("Markus");<br>
  employee.setLastName("Eisele");<br>
  em.persist(employee);<br>
  // some loop magic end<br>
  tx.commit();<br>
  em.close();<br></code>
<br>
 If you watch the log, you can see something like this:
<br>
<pre>FEIN: INSERT INTO EMPLOYEE (LASTNAME, FIRSTNAME) VALUES (?, ?) bind =&gt; [Eisele, Markus] ... FEIN: Coherence(Employee)::Put: 1 value: net.eisele.coherence.entities.Employee[ id=1 ] ... </pre>Which basically tells you, that the actual database insert is carried out by EclipseLink as you are used to. After that, you can see, that the Employee object is put to the Coherence Cache named Employee with the PK as it's key.
<br>
 If you now issue a query against the database
<br><code><br>
  em.createQuery("select e from Employee e where e.lastName = :lastName").setParameter("lastName", "Eisele").getResultList();<br></code>
<br>
 you see the following:
<br>
<pre>FEIN: SELECT ID, LASTNAME, FIRSTNAME FROM EMPLOYEE WHERE (LASTNAME = ?) bind =&gt; [Eisele] FEIN: Coherence(Employee)::Get: 1 result: net.eisele.coherence.entities.Employee[ id=1 ] FEIN: Coherence(Employee)::Put: 1 value: net.eisele.coherence.entities.Employee[ id=1 ] ... </pre>Which tells you, that the query itself is issued against the database but the results checked against Coherence to avoid object construction already for cached entities. Newly queried entities are put into Coherence. If you issue a simple PK query:
<br><code><br>
  em.find(Employee.class, 1);<br></code>
<br>
 the output changes to:
<br>
<pre>FEIN: Coherence(Employee)::Get: 1 result: net.eisele.coherence.entities.Employee[ id=1 ] </pre>and you don't see any db query at all. That's it :) Your cache works! Thanks for reading. Stay tuned for the next part!
<br>
<br><b>Further Reading</b>
<br><a href="http://www.oracle.com/technetwork/middleware/ias/index-096228.html" target="_blank">OTN How-To: Using Coherence as a Shared L2 Cache</a>
<br><a href="http://download.oracle.com/docs/cd/E17904_01/doc.1111/e16596/toc.htm" target="_blank">Integration Guide for Oracle TopLink with Coherence Gird 11g Release 1 (11.1.1)</a>
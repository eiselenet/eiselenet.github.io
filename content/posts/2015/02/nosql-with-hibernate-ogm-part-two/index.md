---
title: "NoSQL with Hibernate OGM - Part two: Querying for your data"
date: 2015-02-10 11:00:00 +0000
layout: post
tags: ["tutorial", "NoSQL", "Jboss", "HibernateOGM"]
slug: "nosql-with-hibernate-ogm-part-two"

url: /2015/02/nosql-with-hibernate-ogm-part-two.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;" target="_blank"><img border="0" src="hibernate_ogm_600px.png" height="53" width="320"></a>
</div> After the <a href="http://in.relation.to/Blogge/FirstHibernateOGMReleaseAka41Final" target="_blank">first final version of Hibernate OGM</a> came out end of January the team has been busy crafting a series of tutorial-style blogs which give you the chance to start over easily with Hibernate OGM. The fist part was all about <a href="http://blog.eisele.net/2015/01/nosql-with-hibernate-ogm-part-one.html" target="_blank">getting setup and persisting your first entity</a>. In this second part you're going to learn how to query your data.&nbsp;Hibernate OGM will let you get your data in several different ways:
<br>
<ul>
 <li>using the Java Persistence Query Langage (JP-QL)</li>
 <li>using the NoSQL native query language of the datastore of your choice (if it has one)</li>
 <li>using Hibernate Search queries - primarly full-text queries</li>
</ul> All of these alternatives will allow you to run a query on the datastore and get the result as a list of managed entities.
<br>
<br><b>Preparing the test class</b>
<br>
 We are going to add a new class HikeQueryTest. It will populate the datastore with some information about hikes:
<br>
<pre class="code"><code> public class HikeQueryTest \{ private static EntityManagerFactory entityManagerFactory; @BeforeClass public static void setUpEntityManagerFactoryAndPopulateTheDatastore() \{ entityManagerFactory = Persistence.createEntityManagerFactory( "hikePu" ); EntityManager entityManager = entityManagerFactory.createEntityManager(); entityManager.getTransaction().begin(); // create a Person Person bob = new Person( "Bob", "McRobb" ); // and two hikes Hike cornwall = new Hike( "Visiting Land's End", new Date(), new BigDecimal( "5.5" ), new HikeSection( "Penzance", "Mousehole" ), new HikeSection( "Mousehole", "St. Levan" ), new HikeSection( "St. Levan", "Land's End" ) ); Hike isleOfWight = new Hike( "Exploring Carisbrooke Castle", new Date(), new BigDecimal( "7.5" ), new HikeSection( "Freshwater", "Calbourne" ), new HikeSection( "Calbourne", "Carisbrooke Castle" ) ); // let Bob organize the two hikes cornwall.setOrganizer( bob ); bob.getOrganizedHikes().add( cornwall ); isleOfWight.setOrganizer( bob ); bob.getOrganizedHikes().add( isleOfWight ); // persist organizer (will be cascaded to hikes) entityManager.persist( bob ); entityManager.getTransaction().commit(); entityManager.close(); \} @AfterClass public static void closeEntityManagerFactory() \{ entityManagerFactory.close(); \} \} </code></pre> This methods will make sure that the entity manager factory is created before running the tests and that the datastore contains some data. The data are the same we stored in <a href="http://blog.eisele.net/2015/01/nosql-with-hibernate-ogm-part-one.html" target="_blank">part 1</a>.
<br>
 Now that we have some data in place, we can start to write some tests to search for them.
<br>
<br><b>Using the Java Persistence Query Langage (JP-QL)</b>
<br>
 The JP-QL is a query language defined as part of the <a href="" target="_blank">Java Persistence API (JPA) specification</a>. It's designed to work with entities and to be database independent.
<br>
 Taking the entity <i>Hike</i> as an example:
<br>
<pre class="code"><code>@Entity public class Hike \{ @Id @GeneratedValue(generator = "uuid") @GenericGenerator(name = "uuid", strategy = "uuid2") private String id; private String description; private Date date; private BigDecimal difficulty; @ManyToOne private Person organizer; @ElementCollection @OrderColumn(name = "sectionNo") private List&lt;HikeSection&gt; sections; // constructors, getters, setters, ... \} </code></pre> A JP-QL query to get the list of available hikes ordered by difficulty looks like this:
<br>
<pre class="code"><code>SELECT h FROM Hike h ORDER BY h.difficulty ASC </code></pre> Hibernate OGM will parse this query and transform it into the equivalent one in the native query language of the datastore of your choice. In Neo4j, for example, it creates and execute a Cypher query like the following:
<br>
<pre class="code"><code>MATCH (h:Hike) RETURN h ORDER BY h.difficulty </code></pre> In MongoDB, using the MongoDB JavaScript API as a query notation, it looks like this:
<br>
<pre class="code"><code>db.Hike.find(\{\}, \{ "difficulty": 1\}) </code></pre> If you use JP-QL in your application you will be able to switch between datastore without the need to update the queries.
<br>
 Now that you have an understanding of what's going on, we can start querying for the data we persisted. We can, for example, get the list of available hikes:
<br>
<br>
<pre class="code"><code> @Test public void canSearchUsingJPQLQuery() \{ // Get a new entityManager EntityManager entityManager = entityManagerFactory.createEntityManager(); // Start transaction entityManager.getTransaction().begin(); // Find all the available hikes ordered by difficulty List&lt;Hike&gt; hikes = entityManager .createQuery( "SELECT h FROM Hike h ORDER BY h.difficulty ASC" , Hike.class ) .getResultList(); assertThat( hikes.size() ).isEqualTo( 2 ); assertThat( hikes ).onProperty( "description" ).containsExactly( "Visiting Land's End", "Exploring Carisbrooke Castle" ); entityManager.getTransaction().commit(); entityManager.close(); \} </code></pre> If you have used the JPA specification before you will find this code very familiar: it's the same code you would write when working on a relational database using JPA.
<br>
 You can test this by switching the configuration and dependency between Neo4j and MongoDB: the test will still pass without any change in the code.
<br>
 The cool thing is that you can use JP-QL queries with datastores which don't have their own query engine. Hibernate OGM's query parser will create full-text queries in this case which are executed via Hibernate Search and Lucene. We will see later how you can do this in more details.
<br>
 The result of the query is a list of managed entities. This means that changes to the objects will be applied to the data in the database automatically. You also can navigate the resulting object graph, causing lazy associations to be loaded as required.
<br>
 The support for the JP-QL language is not complete and it might change depending on the backend. We will leave the details to the official Hibernate OGM documentation. At the moment what's supported is:
<br>
<ul>
 <li>simple comparisons</li>
 <li><i>IS NULL</i> and <i>IS NOT NULL</i></li>
 <li>the boolean operators <i>AND</i>, <i>OR</i>, <i>NOT</i></li>
 <li><i>LIKE</i>, <i>IN</i> and <i>BETWEEN</i></li>
 <li><i>ORDER BY</i></li>
</ul> In case JP-QL is not a good fit for your use case, we will see how you can execute a query using the native language of the backend of your choice.
<br>
<br><b>Using the native backend query language</b>
<br>
 Sometimes you might decide to sacrifice portablility in favor of the power of the underlying native query language. For example, you might want to benefit from the abilities of Neo4j's Cypher language for running hierarchical/recursive queries. Using MongoDB, let's get the hikes passing through "Penzance":
<br>
<pre class="code"><code>// Search for the hikes with a section that start from "Penzace" in MongoDB List&lt;Hike&gt; hikes = entityManager.createNativeQuery("\{ $query : \{ sections : \{ $elemMatch : \{ start: 'Penzance' \} \} \} \}", Hike.class ).getResultList(); </code></pre> The same code with Neo4j would look like this:
<br>
<pre class="code"><code>// Search for the hikes with a section that start from "Penzace" in Neo4j List&lt;Hike&gt; hikes = entityManager.createNativeQuery( "MATCH (h:Hike) -- (:Hike_sections \{start: 'Penzance'\} ) RETURN h", Hike.class ).getResultList(); </code></pre> The important thing to notice is that, like JPA queries, the objects returned by the query are managed entities.
<br>
 You can also define queries using the annotation javax.persistence.NamedNativeQuery:
<br>
<pre class="code"><code>@Entity @NamedNativeQuery( name = "PenzanceHikes", query = "\{ $query : \{ sections : \{ $elemMatch : \{ start: 'Penzance' \} \} \} \}", resultClass = Hike.class ) public class Hike \{ ... \} </code></pre> and then execute it like this:
<br>
<pre class="code"><code>List&lt;Hike&gt; hikes = entityManager.createNamedQuery( "PenzanceHikes" ).getResultList(); </code></pre>
<br><b>Using Hibernate Search queries</b>
<br>
 Hibernate Search offers a way to index Java objects into Lucene indexes and to execute full-text queries on them. The indexes do live outside your datastore. This means you can have query capabilities even if they are not supported natively. It also offers a few interesting properties in terms of feature set and scalability. In particular, using Hibernate Search, you can off-load query execution to separate nodes and scale it independently from the actual datastore nodes.
<br>
 For this example we are going to use MongoDB. You first need to add Hibernate Search to your application. In a Maven project, you need to add the following dependency in the pom.xml:
<br>
<pre class="code"><code>&lt;dependencies&gt; ... &lt;dependency&gt; &lt;groupId&gt;org.hibernate&lt;/groupId&gt; &lt;artifactId&gt;hibernate-search-orm&lt;/artifactId&gt; &lt;/dependency&gt; ... &lt;/dependencies&gt; </code></pre> Now, you can select what you want to index:
<br>
<pre class="code"><code>@Entity @Indexed public class Hike \{ @Id @GeneratedValue(generator = "uuid") @GenericGenerator(name = "uuid", strategy = "uuid2") private String id; @Field private String description; private Date date; private BigDecimal difficulty; @ManyToOne private Person organizer; @ElementCollection @OrderColumn(name = "sectionNo") private List&lt;HikeSection&gt; sections; // constructors, getters, setters, ... \} </code></pre> The @Indexed annotation identifies the classes that we want to index, while the @Field annotation specifies which properties of the class we want to index. Every time a new Hike entity is persisted via the entity manager using Hibernate OGM, Hibernate Search will automatically add it to the index and keep track of changes to managed entities. That way, index and datastore are up to date.
<br>
 You can now look for the hikes to Carisbrooke using Lucene queries. In this example, we will use the query builder provided by Hibernate Search:
<br>
<pre class="code"><code>@Test public void canSearchUsingFullTextQuery() \{ EntityManager entityManager = entityManagerFactory.createEntityManager(); entityManager.getTransaction().begin(); //Add full-text superpowers to any EntityManager: FullTextEntityManager ftem = Search.getFullTextEntityManager(entityManager); // Optionally use the QueryBuilder to simplify Query definition: QueryBuilder b = ftem.getSearchFactory().buildQueryBuilder().forEntity( Hike.class ).get(); // A Lucene query to search for hikes to the Carisbrooke castle: Query lq = b.keyword().onField("description").matching("Carisbrooke castle").createQuery(); //Transform the Lucene Query in a JPA Query: FullTextQuery ftQuery = ftem.createFullTextQuery(lq, Hike.class); //This is a requirement when using Hibernate OGM instead of ORM: ftQuery.initializeObjectsWith( ObjectLookupMethod.SKIP, DatabaseRetrievalMethod.FIND_BY_ID ); // List matching hikes List&lt;Hike&gt; hikes = ftQuery.getResultList(); assertThat( hikes ).onProperty( "description" ).containsOnly( "Exploring Carisbrooke Castle" ); entityManager.getTransaction().commit(); entityManager.close(); \} </code></pre> The result of the code will be a list of hikes mentioning "Carisbrooke castle" in the description.
<br>
 Hibernate Search is a very powerful tool with many different options, it would take too long to describe all of them in this tutorial. You can check the <a href="" target="_blank">reference documentation</a> to learn more about it.
<br>
<br><b>Wrap up</b>
<br>
 That's all for now. As you have seen, Hibernate OGM provides you with a range of options to run queries against your datastore, which should cover most of your typical query needs: JP-QL, native NoSQL queries and full-text queries via Hibernate Search / Apache Lucene. Even if you have never worked with NoSQL datastores before, you will be able to experiment with them easily.
<br>
 You can find the <a href="https://github.com/hibernate/hibernate-demos/tree/master/hibernate-ogm/nosql-with-hibernate-ogm-101/hibernate-ogm-demo-nosql-with-hibernate-ogm-101-part-2" target="_blank">complete example code</a> of this blog post (and the previous one) on GitHub. Just fork it and play with it as you like.
<br>
 Now that you know how to store and find your entities, we will see in the next part of the series how you can put everything inside an application container like <a href="" target="_blank">WildFly</a>.
<br>
 We are eager to know your opinion, feel free to comment or <a href="" target="_blank">contact us</a>, we will answer your questions and hear your feed-back.
<br>
<br>
 Thanks to Gunnar Morling (<a href="http://www.twitter.com/gunnarmorling" target="_blank">@gunnarmorling</a>) and Davide D'Alto (@Github:&nbsp;<a href="https://github.com/DavideD" target="_blank">DavidD</a>) for creating this tutorial.
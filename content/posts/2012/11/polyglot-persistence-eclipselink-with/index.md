---
title: "Polyglot Persistence: EclipseLink with MongoDB and Derby"
date: 2012-11-06 12:43:00 +0000
layout: post
tags: ["persistence", "MongoDB", "jpa", "Derby", "EclipseLink"]
slug: "polyglot-persistence-eclipselink-with"

url: /2012/11/polyglot-persistence-eclipselink-with.html
---

Polyglot persistence has been in the news since some time now. Kicked off by the famous <a href="http://martinfowler.com/bliki/PolyglotPersistence.html" target="_blank">Fowler post</a> from end 2011 I see more an more nice ideas coming up. Latest one was a company internal student project in which we used Scala as a backend persisting data into MongoDB, PostgreSQL and Apache Solr. I'm not a big fan of Scala and remembered <a href="http://blog.eisele.net/2012/05/future-of-nosql-with-java-ee.html" target="_blank">EclipseLink's growing support</a> for NoSQL databases. Given that I simply had to try something like this.
<br>
<br><b>Where to start?&nbsp;</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG-20121106-00036.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="240" src="IMG-20121106-00036.jpg" width="320"></a>
</div> The biggest issue are the missing examples. You find quite a bit stuff about how to change the data-containers (either NoSQL or RDBMS) with EclipseLink but you will not find a single one which exactly uses both technologies seamlessly. Thanks to <a href="https://twitter.com/shaunmsmith" target="_blank">Shaun Smith</a> and <a href="https://twitter.com/guw" target="_blank">Gunnar Wagenkrnecht</a> we have this great <a href="https://oracleus.activeevents.com/connect/sessionDetail.ww?SESSION_ID=4365" target="_blank">JavaOne talk about Polyglot Persistence: EclipseLink JPA for NoSQL, Relational, and Beyond</a>&nbsp;which talks exactly about this.&nbsp;Unfortunately the sources still haven't been pushed anywhere and I had to rebuild this from the talk.So, credits go to Shaun and Gunnar for this.
<br>
 The magic solution is called&nbsp;<a href="http://wiki.eclipse.org/EclipseLink/DesignDocs/328404_new" target="_blank">Persistence Unit Composition</a>. You need one persistence unit for every data container. That looks like the following basic example. You have a couple of entities in each PU and a composite PU is the umbrella.
<br>
<table align="center" cellpadding="0" cellspacing="0" class="tr-caption-container" style="margin-left: auto; margin-right: auto; text-align: center;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="eclipselink_composite-pu_basic.png" imageanchor="1" style="margin-left: auto; margin-right: auto;"><img border="0" height="216" src="eclipselink_composite-pu_basic.png" width="320"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;">Basic Composition Example</td>
  </tr>
 </tbody>
</table>
<br><b>Let's go</b>
<br>
 You should have <a href="" target="_blank">MongoDB</a> in place before you're going to start this little tutorial example. Fire up NetBeans and create two java projects. Lets call them&nbsp;polyglot-persistence-nosql-pu and&nbsp;polyglot-persistence-rational-pu. Put the following entities into the nosql-pu: Customer, Address, Order and OrderLine. (Mostly taken from the <a href="http://wiki.eclipse.org/EclipseLink/Examples/JPA/NoSQL" target="_blank">EclipseLink nosql examples</a>) and put a Product entity into the rational-pu.
<br>
 The single products go into Derby while all the other entities persist into MongoDB. The interesting part is, where OrderLine has a One-to-One relation to a Product: 
<br>
<pre class="brush:java"> @OneToOne(cascade = \{CascadeType.REMOVE, CascadeType.PERSIST\}) private Product product; </pre> This is the point where both worlds come together. More on that later. 
<br>
 Both PUs need to be&nbsp;transaction-type="RESOURCE_LOCAL" and need to contain the following line in the persistence.xml:
<br>
<pre class="brush:xml"> &lt;property name="eclipselink.composite-unit.member" value="true"/&gt; </pre> Don't forget to add the db specific configuration. For MongoDB this is 
<br>
<pre class="brush:xml">&lt;property name="eclipselink.nosql.property.mongo.port" value="27017"/&gt; &lt;property name="eclipselink.nosql.property.mongo.host" value="localhost"/&gt; &lt;property name="eclipselink.nosql.property.mongo.db" value="mydb"/&gt; </pre> For derby this is something like this: 
<br>
<pre class="brush:xml">&lt;property name="javax.persistence.jdbc.url" value="jdbc:derby://localhost:1527/mydb"/&gt; &lt;property name="javax.persistence.jdbc.password" value="sa"/&gt; &lt;property name="javax.persistence.jdbc.driver" value="org.apache.derby.jdbc.ClientDriver"/&gt; &lt;property name="javax.persistence.jdbc.user" value="sa"/&gt; </pre> Now we need something to link those two PUs together. The combined-pu resides in a sample polyglot-persistence-web module and looks like this: 
<br>
<pre class="brush:xml">&lt;persistence-unit name="composite-pu" transaction-type="RESOURCE_LOCAL"&gt; &lt;provider&gt;org.eclipse.persistence.jpa.PersistenceProvider&lt;/provider&gt; &lt;jar-file&gt;\lib\polyglot-persistence-rational-pu-1.0-SNAPSHOT.jar&lt;/jar-file&gt; &lt;jar-file&gt;\lib\polyglot-persistence-nosql-pu-1.0-SNAPSHOT.jar&lt;/jar-file&gt; &lt;properties&gt; &lt;property name="eclipselink.composite-unit" value="true"/&gt; &lt;/properties&gt; &lt;/persistence-unit&gt; &lt;/persistence&gt; </pre> Watch out for the jar-file path. We are going to package this in a war-archive and because of this, the nosql-pu and the rational-pu will go into WEB-INF/lib folder. As you can see, my example is build with maven. Make sure to use the latest EclipseLink dependency. Even GlassFish 3.1.2.2 still ships with a lower version. MongoDB support has been added beginning with 2.4.
<br>
<pre class="brush:xml"> &lt;dependency&gt; &lt;groupId&gt;org.eclipse.persistence&lt;/groupId&gt; &lt;artifactId&gt;eclipselink&lt;/artifactId&gt; &lt;version&gt;2.4.1&lt;/version&gt; &lt;/dependency&gt; </pre> Beside this, you also need to turn GlassFish's classloaders around: 
<br>
<pre class="brush:xml">&lt;class-loader delegate="false"/&gt; </pre> Don't worry about the details. I put up everything to <a href="https://github.com/myfear/polyglot-persistence" target="_blank">github.com/myfear</a> so, you might dig into the complete example later on your own. 
<br>
<br><b>Testing it</b>
<br>
 Let's make some very brief tests with it. Create a nice little Demo servlet and inject the composite-pu to it. Create an EntityManager from it and get a transaction. Now start creating prodcuts, a customer, the order and the separate order-lines. All plain JPA. No further magic here: 
<br>
<pre class="brush:java"> @PersistenceUnit(unitName = "composite-pu") private EntityManagerFactory emf; protected void processRequest() // [...] \{ EntityManager em = emf.createEntityManager(); em.getTransaction().begin(); // Products go into RDBMS Product installation = new Product("installation"); em.persist(installation); Product shipping = new Product("shipping"); em.persist(shipping); Product maschine = new Product("maschine"); em.persist(maschine); // Customer into NoSQL Customer customer = new Customer(); customer.setName("myfear"); em.persist(customer); // Order into NoSQL Order order = new Order(); order.setCustomer(customer); order.setDescription("Pinball maschine"); // Order Lines mapping NoSQL --- RDBMS order.addOrderLine(new OrderLine(maschine, 2999)); order.addOrderLine(new OrderLine(shipping, 59)); order.addOrderLine(new OrderLine(installation, 129)); em.persist(order); em.getTransaction().commit(); String orderId = order.getId(); em.close(); </pre> If you put the right logging properties in place you can see, what is happening:
<br>
 A couple of sequences are assigned to the created Product entities (GeneratedValue). The Customer entity gets persisted into Mongo with a MappedInteraction. Entities map onto collections in MongoDB. 
<br>
<pre>FINE: Executing MappedInteraction() spec =&gt; null properties =&gt; \{mongo.collection=CUSTOMER, mongo.operation=INSERT\} input =&gt; [DatabaseRecord( CUSTOMER._id =&gt; 5098FF0C3D9F5D2CCB3CFECF CUSTOMER.NAME =&gt; myfear)] </pre> After that you see the products being inserted into Derby and again the MappedInteraction, that perssits the Order into MongoDB. The really cool part is down at the OrderLines: 
<br>
<pre>ORDER.ORDERLINES =&gt; [DatabaseRecord( LINENUMBER =&gt; 1 COST =&gt; 2999.0 PRODUCT_ID =&gt; 3), DatabaseRecord( LINENUMBER =&gt; 2 COST =&gt; 59.0 PRODUCT_ID =&gt; 2), DatabaseRecord( LINENUMBER =&gt; 3 COST =&gt; 129.0 PRODUCT_ID =&gt; 1)] </pre> Orderlines has an object which has the product_id which was generated for the related product entities. Further on you can also find the related Order and iterate over the products and get their descriptions: 
<br>
<pre class="brush:java">Order order2 = em.find(Order.class, orderId);</pre>
<pre class="brush:java"> for (OrderLine orderLine : order2.getOrderLines()) \{ String desc = orderLine.getProduct().getDescription(); \} </pre> The nice little demo looks like this: 
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="screenshot.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="200" src="screenshot.png" width="400"></a>
</div> Thanks Shaun, thanks Gunnar for this nice little example. Now go to <a href="https://github.com/myfear/polyglot-persistence" target="_blank">github.com/myfear</a> and get your hands dirty :)
---
title: "JBoss Data Virtualization 6.1 Beta Now Available"
date: 2015-01-09 11:00:00 +0000
layout: post
tags: ["JDV", "Jboss", "DataVirtualization"]
slug: "jboss-data-virtualization-61-beta-now-available"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2015/01/jboss-data-virtualization-61-beta-now-available.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;" target="_blank"><img border="0" src="images_products_datavirt_red-hat-jboss-datavirt.png" height="183" width="200"></a>
</div> JBoss <a href="" target="_blank">Data Virtualization</a>&nbsp;(JDV) is a data integration solution that sits in front of multiple data sources and allows them to be treated as a single source. &nbsp;Do do that, it offers data abstraction, federation, integration, transformation, and delivery capabilities to combine data from one or multiple sources into reusable and unified logical data models accessible through standard SQL (JDBC, ODBC, Hibernate) and/or web services (REST, OData, SOAP) interfaces.
<br>
 Yesterday the latest 6.1 Beta was made available for download. It focuses on three major areas which are Big Data, Cloud and Development and Deployment Improvements.
<br>
<br><b>Big Data Improvements</b>
<br>
 In addition to the Apache Hive support released in 6.0, 6.1 will also offer support for Cloudera Impala for fast SQL query access to data stored in Hadoop. 
<br>
 Also new in 6.1 is the support for Apache Solr as a data source. &nbsp;With Apache Solr you are able to take advantage of enterprise search capabilities for organized retrieval of structured and unstructured data. Another area of improvements is the updated support for MongoDB as a NoSQL data source. This was already introduced as a Technical Preview in 6.0 and will be fully supported in 6.1.
<br>
 The <a href="" target="_blank">JBoss Data Grid</a> support has been further expanded and brings the ability to perform writes in addition to reads. With 6.1 it is also possible to take advantage of JDG Library mode as an embedded cache in addition to the support as a remote cache that was previously available.
<br>
 Newly introduced in this release is the Apache Cassandra support which is included as a not supported, technical preview.
<br>
<br><b>Cloud Improvements</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="https://developer.jboss.org/wiki/IntroToTheDataVirtualizationWebInterfaceOnOpenShift" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;" target="_blank"><img alt="" border="0" src="ManageSources-SelectMySQL.png" height="151" width="320"></a>
</div> The OpenShift cartridge for 6.1 will be updated with a new WebUI that focusses on ease of use for web and mobile developers. &nbsp;This lightweight user interface allows users to quickly access a library of existing data services, or create one of their own in a top-down manner. 
<br>
 Beside that, the support for the Salesforce.com (SFDC) API has been improved. It now supports the Bulk API with a better RESTful interface and better resource handling and is now able to handle very large data-sets. Finally, the 6.1 version brings full support of JBoss <a href="" target="_blank">Data Virtualization</a> on Amazon EC2 and Google Compute Engine.
<br>
<br><b>Productivity and Deployment Improvments</b>
<br>
 The consistent centralized security capabilities across multiple heterogeneous data sources got even better by a security audit log dashboard that can be viewed in the dashboard builder. It works with JDV’s RBAC feature and displays who has been accessing what data and when. Beside the large set of already supported data sources, JDV already allowed to create custom integrations, called translators. Those have been reworked and the developer productivity got better by providing features to improve usability including archetype templates that can be used to generate a starting maven project for custom development. &nbsp;When the project is created, it will contain the essential classes and resources to begin adding custom logic. JDV 6.1 will provide support for Azul Zing JVM. &nbsp;Azul Zing is optimized for Linux server deployments and designed for enterprise applications and workloads that require any combination of large memory, high transaction rates, low latency, consistent response times or high sustained throughput. &nbsp;The support for MariaDB as a data source has been added. The Excel support has been further extended and allows to read Microsoft Excel documents on all platforms by using the Apache POI connector.&nbsp; 
<br>
<br>
 Find out more:
<br>
<ul>
 <li>Discuss on the <a href="https://developer.jboss.org/en/products/datavirt" target="_blank">JBoss Data Virtualization Forums</a><br></li>
 <li>Get started with one of our many <a href="https://draft.blogger.com/developer-materials?quickstarts" target="_blank">quickstarts</a>.<br></li>
 <li>View the <a class="external-link" href="https://access.redhat.com/site/documentation/en-US/Red_Hat_JBoss_Data_Virtualization" target="_blank">JBoss Data Virtualization Documentation</a><br></li>
 <li>Hava a look at the <a href="" target="_blank">6.1 Beta Documentation</a></li>
</ul>
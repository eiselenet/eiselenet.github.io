---
title: "JPA implementations comparison: Hibernate, Toplink Essentials, Openjpa, Eclipselink"
date: 2009-01-20 06:10:00 +0000
layout: post
tags: ["Hibernate", "java", "performance", "Toplink Essentials", "OpenJPA", "EclipseLink"]
slug: "jpa-implementations-comparison"

url: /2009/01/jpa-implementations-comparison.html
---

Found an article about performance comparison for the most well known Java Persistence API (JPA) implementations: Toplink Essentials, EclipseLink, Hibernate and OpenJPA.
<br>
<br>
Summarized conclusions: (taken from the articel)
<br>
<br>
<ol>
 <br>
 <li>There is not an implementation that clearly has the best performance. Some had a very good CPU or memory performance and some did it very well when inserting or querying. But none of them was outstanding as a whole.</li>
 <br>
 <li>The number of records inserted by Hibernate was extremely higher than it was for any other implementation (4 times more compared to Eclipselink and 24 times more compared to OpenJPA). However, Hibernate was also the JPA implementation that executed the lowest number of queries, although the differences in this value (3080 for Hibernate vs 3740 for Toplink Essentials) are not so extreme as for the number of inserts.</li>
 <br>
 <li>Hibernate was also the implementation that consumed more memory. But having into account that it inserted many more records than the others, it sounds reasonable.<br></li>
 <br>
 <li>OpenJPA had the lowest value of inserts+queries.</li>
 <br>
 <li>The number of inserts executed by OpenJPA was extremely low, compared to the others.</li>
 <br>
 <li>The usage of CPU in the case of Toplink Essentials and Eclipselink was extremely low.</li>
 <br>
</ol>
<br>
<br><a href="http://terrazadearavaca.blogspot.com/2008/12/jpa-implementations-comparison.html" target="_blank">read the full article here</a>
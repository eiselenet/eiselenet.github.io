---
title: "Best Practices für EAR Files"
date: 2008-02-19 06:24:00 +0000
layout: post
tags: ["softwareentwicklung", "programmierung", "bea"]
slug: "best-practices-fr-ear-files"
link: "2008/02/best-practices-fr-ear-files.html"
url: /2008/02/best-practices-fr-ear-files.html
---

Ein Posting auf <a href="http://dev2dev.bea.com" target="_blank">beas dev2dev</a> Seiten hat meine Aufmerksamkeit erregt. Ein recht <a href="http://dev2dev.bea.com/pub/a/2008/01/packaging-best-practices.html" target="_blank">umfassender Beitrag zum produktionsgerechten Packaging von EAR</a> (Enterprise Application Archives) Files. In Summe finden sich 15 Hinweise und Vorgehensweisen zum optimalen Packagen für Produktionsumgebungen.
<br>
Mit Ausnahme einiger Weniger, sind diese Hinweise "state-of-the-art" und werden sicherlich von jedem berücksichtigt. 
<br>
Erstaunt war ich über den dritten Hinweis: <span style="font-weight:bold;">PS003: JSPs, unprocessed/uncompiled annotations and source files must not be included in the EAR</span>. Genauer: über die JSP Seiten. Dass Java Sourcen nicht in eine Produktivumgebung gehören ist klar.
<br>
Wenn die JSP Seiten unter WEB-INF/jsp abgelegt sind, dann ist ein unberechtigter Zugriff auf die Sourcen von extern gar nicht möglich. 
<br>
Und verglichen mit dem Umstand, dass JSPClassServlet einrichten zu müssen ist dieser "Schutz" vergleichsweise einfach zu erreichen.
<br>
JSP Sourcen gehören m.E. nicht in ein Produkt. Aber im Kontext eine klassischen Individualentwicklung, welche auf einer separaten Instanz betrieben wird, ist dieser Aufwand m.E. überflüssig. Trotz allem sollte man allerdings den jspc nicht vergessen. Grad bei großen Webprojekten unter Last ist ein Precompilen der JSP Seiten unabdingbar.
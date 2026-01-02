---
title: "Running A MySQL Database with Persistent Storage on OpenShift"
date: 2015-10-20 11:13:00 +0000
layout: post
tags: [""]
slug: "running-mysql-database-with-persistent"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2015/10/running-mysql-database-with-persistent.html
---

Another episode in the lose series of OpenShift and microservices deployment bits and pieces. Today I am going to show you, how to deploy a MySQL database to OpenShift. The special case here is, that I want the database to survive restarts. Which means, I have to assign a a persistent storage to it.
<br>
<br><b>Prerequisites</b>
<br>
 I assume, that you are using the <a href="http://blog.eisele.net/2015/09/running-openshift-origin-on-windows.html" target="_blank">OpenShift all-in-one-vm</a> or the <a href="http://blog.eisele.net/2015/07/running-wildfly-on-openshift-3-with-kubernetes-fabric8-on-windows.html" target="_blank">Fabric8 Vagrant OpenShift image</a>. It doesn't really matter for the things we're going to do.
<br>
<br>
<br>
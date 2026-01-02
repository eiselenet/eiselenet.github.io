---
title: "Secure JDBC Realm with Custom Principle"
date: 2015-02-18 10:59:00 +0000
layout: post
tags: [""]
slug: "secure-jdbc-realm-with-custom-principle"

url: /2015/02/secure-jdbc-realm-with-custom-principle.html
---

USE jdbcrealmdb;
<br>
 CREATE TABLE `jdbcrealmdb`.`users` (
<br>
 `username` varchar(255) NOT NULL,
<br>
 `description` TEXT,
<br>
 `salt` varchar(255) NOT NULL,
<br>
 `password` TEXT NOT NULL,
<br>
 `lastlogin` TIMESTAMP DEFAULT NULL,
<br>
 `falseattempts` SMALLINT DEFAULT NULL,
<br>
 PRIMARY KEY (`username`)
<br>
 )
<br>
<br>
<br>
<br>
<br>
 TEXT and BLOB is stored off the table with the table just having a pointer to the location of the actual storage.
<br>
<br>
 VARCHAR is stored inline with the table. VARCHAR is faster when the size is reasonable, the tradeoff of which would be faster depends upon your data and your hardware, you'd want to benchmark a realworld scenario with your data.
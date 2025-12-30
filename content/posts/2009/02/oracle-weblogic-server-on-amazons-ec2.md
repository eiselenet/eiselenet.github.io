---
title: "Oracle WebLogic Server on Amazon's EC2"
date: 2009-02-11 04:41:00 +0000
layout: post
tags: ["cloud", "amazon ec2", "oracle", "weblogic server"]
slug: "2009-02-11-oracle-weblogic-server-on-amazons-ec2"
url: /2009/02/oracle-weblogic-server-on-amazons-ec2.html
---

Amazon Elastic Compute Cloud (Amazon EC2) is a web service that provides resizable compute capacity in the cloud. It is designed to make web-scale computing easier for developers.
<br>
Amazon EC2’s simple web service interface allows you to obtain and configure capacity with minimal friction. It provides you with complete control of your computing resources and lets you run on Amazon’s proven computing environment. Amazon EC2 reduces the time required to obtain and boot new server instances to minutes, allowing you to quickly scale capacity, both up and down, as your computing requirements change.
<br>
<br>
You can now find two new Amazon Machine Images (AMIs) for Oracle WebLogic Server listed in <a href="http://developer.amazonwebservices.com/connect/kbcategory.jspa?categoryID=205" target="_blank">Amazon's public AMI repository</a>.
<br>
In a matter of minutes, you can have a fresh fully functioning WebLogic Server environment up and running ready to host your JEE applications.
<br>
On the oracle technology network there is a separate <a href="http://www.oracle.com/technology/tech/cloud/index.html" target="_blank">Cloud Computing Center</a> (you find it under middleware). There you can finde a <a href="http://www.oracle.com/technology/tech/cloud/pdf/wlsami_ref.pdf" target="_blank">guide (PDF) to Oracle and Weblogic in the cloud</a>. After all some excellent instructions in getting started with the provided AMIs.
<br>
For more detailed information have a look at the <a href="http://docs.amazonwebservices.com/AWSEC2/latest/DeveloperGuide/index.html" target="_blank">Amazon Elastic Compute Cloude Developer Guide</a>.
<br>
<br>
There are two AIMs availabe on EC2 with weblogic. Both 32 &amp; 64 Bit versions of Weblogic Server 10.3.0.0 with JRockit JDK 6.0 R27.6 (Java version 1.6.0_05) installed on a Oracle Enterprise Linux 5 Update 2 JeOS-1.0.1.
<br>
Keep in mind, that the WLS does not include the samples, webserver plugins and the workshop components.
<br>
More information about licensing, Oracle in the cloud and more general questions can be found in the <a href="http://www.oracle.com/technology/tech/cloud/faq.html" target="_blank">Oracle Cloud Computing FAQ</a>.
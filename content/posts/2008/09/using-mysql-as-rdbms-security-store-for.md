---
title: "Using MySQL as RDBMS Security Store for Weblogic Server"
date: 2008-09-01 05:08:00 +0000
layout: post
tags: ["mysql", "oracle", "Security Realm", "weblogic server"]
slug: "2008-09-01-using-mysql-as-rdbms-security-store-for"
url: /2008/09/using-mysql-as-rdbms-security-store-for.html
---

One of the truely new features of the Weblogic Server 10gR3 is the ability to get rid of the embedded LDAP server for security realms. You can now configure your own RDBMS based security. This means the RDBMS is not only used for users and groups but for all security related ressources. A must for the use of the new SAML2 features.
<br>
<br>
For more details have a look at the official documentation at <a href="http://edocs.bea.com/wls/docs103/secmanage/rdbms.html" target="_blank">http://edocs.bea.com/wls/docs103/secmanage/rdbms.html</a>
<br>
<br>
Oracle recommends that you configure your RDBMS security store during domain creation using the configuration wizzard. 
<br>
<br>
This is by far the most easiest way to do the job. The only thing you have to do is to start the wizzard. Select "Create new Weblogic Domain", tell it the prefered user name, template and JDK and here comes the magic:
<br>
Tell the wizzard, that you would like to "Customize Environment and Service Settings". The first page, that displays after this is the "Configure RDBMS Security Store Database".
<br>
<br><a onblur="try \{parent.deselectBloggerImageGracefully();\} catch(e) \{\}" href="http://www.eisele.net/blog/uploaded_images/configwizzard-724206.png"><img style="float:left; margin:0 10px 10px 0;cursor:pointer; cursor:hand;" src="http://www.eisele.net/blog/uploaded_images/configwizzard-724203.png" border="0" alt=""></a>
<br>
<br>
Fill in the required parameters and click through the remaining pages. Finish the wizzard but DO NOT start the server. 
<br>
<br>
Now you have to create the DB tables. Look at the <bea_home>
 \wlserver_10.3\server\lib and find the needed .sql scripts. There is no MySQL Version. But this is not too hard. Take the Pointbase-Version and modify all occurences of BLOB(1M) to BLOB. This should be enough. Now create the schema and the tables and start your domain. 
 <br>
 <br>
 Thats all for now. Now, you are ready to use your new RDBMS based security with WLS and MySQL.
</bea_home>
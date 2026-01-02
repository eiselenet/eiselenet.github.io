---
title: "Customizing the Look and Feel of the Weblogic Server Admin Console"
date: 2009-09-16 10:14:00 +0000
layout: post
tags: ["oracle", "portal", "weblogic server"]
slug: "customizing-look-and-feel-of-weblogic"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2009/09/customizing-look-and-feel-of-weblogic.html
---

<a class="lightbox" href="http://www.eisele.net//blog/uploaded_images/portal_cust-766235.PNG" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="191" src="http://www.eisele.net//blog/uploaded_images/portal_cust-766235.PNG" width="200"></a>
<br>
<br>
 Did you ever wonder, if you could customize the look and feel, that comes 
<br>
 with the weblogic server admin console? You can :)
<br>
 The administration console is a weblogic portal application that uses skins and skeletons 
<br>
 like they exist in the weblogic portal.
<br>
<br>
 If you need to make changes to it, you now only need to know where to do them.
<br>
 The consoleapp is located in your &lt;middleware_home&gt;\wlserver_10.3\server\lib\consoleapp.
<br>
<br>
 Before making any changes: Make a backup of the original consoleapp!!! 
<br>
<br>
 Now you have to look through it and search for the basic skin that is used. It is called
<br>
 wlsconsole and you can find it in webapp\framework\skins\wlsconsole.
<br>
 Next stept is to open the css files and change the desired parts. You can also have a look
<br>
 at the provided images and change them.
<br>
<br>
 If you are finished with the basic changes, you can possibly make more detailed changes. 
<br>
 Look at the main webapp directory. There you can find another interesting file. It is called console.portal
<br>
 and contains the basic definition of all console related ui elements. 
<br>
<br>
 More tweaks in next posts.
<br>
<br>
 Links to read:
<br><a href="http://download.oracle.com/docs/cd/E13218_01/wlp/docs102/index.html">BEA WebLogic Portal 10.2 Documentation: </a>
<br>
<br>
<br><a href="http://download.oracle.com/docs/cd/E13218_01/wlp/docs102/extendadmin/index.html">Customizing the Administration Console </a>
<br>
<br>
<br><a href="http://download.oracle.com/docs/cd/E13218_01/wlp/docs102/portals/develop_ui_lookfeel.html">User Interface Development with Look And Feel Features </a>
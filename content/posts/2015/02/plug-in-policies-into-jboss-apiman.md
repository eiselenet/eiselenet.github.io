---
title: "Plug in Policies Into JBoss Apiman"
date: 2015-02-20 13:00:00 +0000
layout: post
tags: ["Management", "Jboss", "Apiman"]
slug: "2015-02-20-plug-in-policies-into-jboss-apiman"
url: /2015/02/plug-in-policies-into-jboss-apiman.html
---

The <a href="" target="_blank">JBoss apiman project</a> did just release <a href="https://github.com/apiman/apiman/tree/apiman-1.0.3.Final" target="_blank">1.0.3.Final this week</a>. &nbsp;It's mostly a bug fix release, with just a couple of relatively minor improvements. One particular feature, that made it's way into the framework since I last blogged about it is the support for plugins. Those plugins&nbsp;can easily be added to the system in order to provide additional functionality.
<br>
<br><b>Add Policies As Plugins</b>
<br>
 Currently the only functionality that can be contributed through the plugin framework is new policies. Fortunately policies are also the most important aspect of apiman, as they are responsible for doing all of the important work at runtime.
<br>
<br><b>Creating a Plugin</b>
<br>
 An apiman plugin is basically a java web archive (WAR) with a little bit of extra sauce. This approach makes it very easy to build using maven, and should be quite familiar to most Java developers. Because a plugin consists of some resources files, compiled java classes, front-end resource such as HTML and javascript, and dependencies in the form of JARs, the WAR format is a natural choice. If you want to give it a try yourself, make sure to dig trough the extensive documentation in the <a href="http://www.apiman.io/latest/developer-guide.html#_plugins" target="_blank">developer guide</a>.
<br>
 The following video walks you through it quickly: 
<br>
<br>
<div class="video-container">
 <iframe allowfullscreen frameborder="0" height="236" mozallowfullscreen="" src="//player.vimeo.com/video/118262057" webkitallowfullscreen="" width="420"></iframe>
</div>
<br><b>How To Run Apiman</b>
<br>
 There is a very handy quickstart available, which allows you to build, deploy and start apiman on WildFly with a single command:
<br>
<pre class="code"><code> $ mvn clean install -Pinstall-all-wildfly8 $ cd tools/server-all/target/wildfly-8.1.0.Final/ $ ./bin/standalone.sh </code></pre> Make sure to also read my previous blog posts about API Management with apiman:
<br>
<ul>
 <li><a href="http://blog.eisele.net/2014/09/rest-api-management-in-wildfly-81-with-overlord.html" target="_blank">API Management in WildFly 8.1 with Overlord</a></li>
 <li><a href="http://blog.eisele.net/2015/01/kickstart-on-api-management-with-jboss-apiman.html" target="_blank">Kickstart on API Management with JBoss Apiman 1.0</a></li>
</ul> You can follow <a href="http://twitter.com/apiman_io" target="_blank">@apiman_io</a>&nbsp;and <a href="http://www.apiman.io/latest/chat.html" target="_blank">chat with the team on IRC</a>.
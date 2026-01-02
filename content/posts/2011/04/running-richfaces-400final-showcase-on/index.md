---
title: "Running RichFaces 4.0.0.Final Showcase on WebLogic Server"
date: 2011-04-07 04:42:00 +0000
layout: post
tags: ["weblogic", "richfaces"]
slug: "running-richfaces-400final-showcase-on"

url: /2011/04/running-richfaces-400final-showcase-on.html
---

A very short notice: RichFaces 4.0.0.Final is running smoothly on WebLogic server 10.3.4.0. Need a brief howto? Here you are:
<br>
 - <a href="http://www.jboss.org/richfaces/download/stable.html" target="_blank">Download</a> RichFaces 4.0.0.Final distribution
<br>
 - unzip to a suitable location
<br>
 - navigate to \richfaces-4.0.0.Final\examples\richfaces-showcase
<br>
 - edit the pom.xml and remove/uncomment the relativePath of the parent (<code>&lt;!-- &lt;relativePath&gt;../parent/pom.xml&lt;/relativePath&gt; --&gt;</code>)
<br>
 - add the jboss nexus repository
<br><code> &lt;repositories&gt;<br>
  &lt;repository&gt;<br>
  &lt;id&gt;jboss&lt;/id&gt;<br>
  &lt;url&gt;http://repository.jboss.org/nexus/content/groups/public-jboss/&lt;/url&gt;<br>
  &lt;/repository&gt;<br>
  &lt;/repositories&gt;</code>
<br>
 - Follow the<a href="http://blog.eisele.net/2010/04/weblogic-server-10330-released-it-now.html"> instructions on my older post</a> to deploy the jsf(2.0,1.0.0.0_2-0-2) library to WebLogic and create a weblogic.xml with the library link in src\main\webapp\WEB-INF.
<br>
 - type <code>mvn clean install -P jee6</code>
<br>
 - deploy the exploded or packaged war from \target to your WebLogic.
<br>
 - Enjoy!
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="richfaces_weblogic.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="240" src="richfaces_weblogic.png" width="400"></a>
</div>
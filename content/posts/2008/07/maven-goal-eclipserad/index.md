---
title: "maven goal eclipse:rad und checkstyle"
date: 2008-07-21 04:36:00 +0000
layout: post
tags: ["softwareentwicklung", "maven"]
slug: "maven-goal-eclipserad"

url: /2008/07/maven-goal-eclipserad.html
---

Ich muss gestehen, dass ich länger danach gesucht habe.
<br>
Nun bin ich fündig geworden.
<br>
Das Maven goal eclipse:rad erzeugt die IBM RAD spezifischen Konfigurationsdateien und macht damit aus einem einfachen eclipse-projekt ein echtes RAD Projekt.
<br>
Was fehlt nun noch zum richtigen Enterprise Software Projekt?
<br>
Natürlich die Softwarequalität.
<br>
Wenn man am maven-eclipse-plugin folgende zusätztliche Einstellungen vornimmt, dann wird direkt beim Build der IDE Konfiguration die entsprechende Checkstyle Einstellung gesetzt:
<br>
<br><code><br>
 &lt;plugin&gt;<br>
 &lt;groupid&gt;org.apache.maven.plugins&lt;/groupid&gt;<br>
 &lt;artifactid&gt;maven-eclipse-plugin&lt;/artifactid&gt;<br>
 &lt;configuration&gt;<br>
 &lt;additionalconfig&gt;<br>
 &lt;file&gt;<br>
 &lt;name&gt;.checkstyle&lt;/name&gt;<br>
 &lt;url&gt;http://some.place.org/path/to/file&lt;/url&gt;<br>
 &lt;/file&gt;<br>
 &lt;/additionalconfig&gt;<br>
 &lt;/configuration&gt;<br>
 &lt;/plugin&gt;<br></code>
<br>
<br>
Nachtrag:
<br>
Auf den ersten Blick könnte man meinen, dass es sich bei dem &lt;file&gt; um die checkstyle.xml handelt. Das ist leider nicht wahr. 
<br>
Tatsächlich handelt es sich um den xml-Schnipsel, den Eclipse in der.checkstyle Datei erwartet. Erst darin befindet sich dann der Pfad zu einer externen oder internen Konfiguration.
<br>
<br><code><br>
 &lt;fileset-config file-format-version="1.2.0" simple-config="true"&gt;<br>
  &lt;local-check-config name="ITP_Checks_for_ACM" location="<b>/path/to/checks.xml</b>" type="external" description=""&gt;<br>
  &lt;additional-data name="protect-config-file" value="false"/&gt;<br>
  &lt;/local-check-config&gt;<br>
  &lt;fileset name="Alle" enabled="true" check-config-name="ITP_Checks_for_ACM" local="true"&gt;<br>
  &lt;file-match-pattern match-pattern="." include-pattern="true"/&gt;<br>
  &lt;/fileset&gt;<br>
 &lt;/fileset-config&gt;<br></code>
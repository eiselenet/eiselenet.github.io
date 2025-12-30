---
title: "Flickr Uploader 3.0.3"
date: 2008-01-18 10:41:00 +0000
layout: post
tags: ["flickr programmierung", "photographie"]
slug: "2008-01-18-flickr-uploader-303"
url: /2008/01/flickr-uploader-303.html
---

Er ist endlich da :) Der neue <a href="">Flickr-uploader</a> in der aktuellen Version 3.0.3. In deutsch kann man ihn genauso <a href="http://downloads.flickr.com/flickr/FlickrUploadr-3.0.3-de.exe">herunterladen</a>, wie in vielen anderen Sprachen.
<br>
<br>
Wer hinter einer Firewall oder einem Proxy Server sitzt, der kann mit folgendem Workaround auch mit dem neuen Uploader Arbeiten:
<br>
<br>
1. Edit Uploader prefs file. Typically, you’ll find it in:
<br>
C:\Programm Files\Flickr Uploadr\defaults\preferences\prefs.js
<br>
<br>
2. Add the following lines to this file:
<br>
<br>
pref('network.proxy.http', '<b>your.proxy.org</b>');
<br>
pref('network.proxy.http_port', <b>1234</b>);
<br>
pref('network.proxy.type', 1);
<br>
<br>
Die alte 2.5.0.15 Version kann man ebenfalls noch auf <a href="http://downloads.flickr.com/flickr/uploadr_2.5.0.15_de.exe">deutsch</a> oder auf <a href="http://downloads.flickr.com/flickr/uploadr_2.5.0.15_en.exe">englisch</a> beziehen.
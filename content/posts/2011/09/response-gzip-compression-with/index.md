---
title: "Response GZIP Compression with GlassFish in Production"
date: 2011-09-13 08:57:00 +0000
layout: post
tags: ["glassfish", "gzip", "compression"]
slug: "response-gzip-compression-with"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2011/09/response-gzip-compression-with.html
---

A lot has been written about this and this basically should be common&nbsp;knowledge, but talking to different people out there and looking at the efforts Google takes to improve page speed it seems to me that the topic is worth a second and current look.
<br>
<br><b>The basics</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG-20110913-00031.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="240" src="IMG-20110913-00031.jpg" width="320"></a>
</div> HTTP compression, otherwise known as content encoding, is a publicly defined way to compress textual content transferred from web servers to browsers. HTTP compression uses public domain compression algorithms, like gzip and compress, to compress XHTML, JavaScript, CSS, and other text files at the server. This standards-based method of delivering compressed content is built into HTTP 1.1, and most modern browsers that support HTTP 1.1 support ZLIB inflation of deflated documents. In other words, they can decompress compressed files automatically, which saves time and bandwidth.
<br>
<br><b>But that's simple. What are the problems?</b>
<br>
 In order to get your stuff compressed, you have to do this somewhere between the responding server and the client. Looking into this a little deeper you find a couple of things to take care of:
<br>
 It should:
<br>
 1) ...be fast
<br>
 2) ...be proven in production
<br>
 3) ...not slow down your appserver
<br>
 4) ...be portable and not bound to an appserver
<br>
 Let's go and have a more detailed look at what you could do in order to speed up your GlassFish a bit.
<br>
<br><b>Testpage</b>
<br>
 I am trying to run this with a simple test-page. This is the "Edit Network Listener" page in GlassFish's Admin Console (http://localhost:4848/web/grizzly/networkListenerEdit.jsf?name=admin-listener&amp;configName=server-config).&nbsp;The basic response times (uncompressed) for this page on my little&nbsp;machine&nbsp;captured with Firebug:
<br>
<table border="1">
 <tbody>
  <tr>
   <td>Type</td>
   <td># Requests</td>
   <td>Size (kb)</td>
   <td>time (ms)</td>
  </tr>
  <tr>
   <td>css</td>
   <td>11</td>
   <td>120</td>
   <td>125</td>
  </tr>
  <tr>
   <td>js</td>
   <td>12</td>
   <td>460.7</td>
   <td>130</td>
  </tr>
  <tr>
   <td>html</td>
   <td>3</td>
   <td>324.3</td>
   <td>727</td>
  </tr>
  <tr>
   <td>all</td>
   <td>52</td>
   <td>1126.4</td>
   <td>1380</td>
  </tr>
 </tbody>
</table>
<br><b>GlassFish built-in compression</b>
<br>
 If you are running a GlassFish 3.x server, the most obvious thing is to look what he has to offer. You could simply "Enable HTTP/1.1 GZIP compression to save server bandwidth" ("Edit Network Listener" =&gt; HTTP =&gt; middle). You simply add the compressible mime types (defaults plus: text/css,text/javascript,application/javascript) you would like and set a compression minimum size (in this case 1024bytes). You do have to restart your instance in order to let the changes take effect.
<br>
<table border="1">
 <tbody>
  <tr>
   <td>Type</td>
   <td># Requests</td>
   <td>Size (kb)</td>
   <td>time (ms)</td>
   <td>change<br>
     % size</td>
   <td>change<br>
     % time</td>
  </tr>
  <tr>
   <td>css</td>
   <td>11</td>
   <td>24.9</td>
   <td>185</td>
   <td>-79,25</td>
   <td>48,00</td>
  </tr>
  <tr>
   <td>js</td>
   <td>12</td>
   <td>122,2</td>
   <td>55</td>
   <td>-73,48</td>
   <td>-57,69</td>
  </tr>
  <tr>
   <td>html</td>
   <td>3</td>
   <td>22.6</td>
   <td>1470</td>
   <td>-93,03</td>
   <td>102,20</td>
  </tr>
  <tr>
   <td>all</td>
   <td>52</td>
   <td>272,4</td>
   <td>2350</td>
   <td>-75,82</td>
   <td>70,29</td>
  </tr>
  <tr>
   <td></td>
   <td></td>
   <td></td>
   <td></td>
   <td>-80,39</td>
   <td>40,70</td>
  </tr>
 </tbody>
</table> Looking at the results you see, that you have an average of 80% to save on&nbsp;bandwidth using compression but you also see that it takes longer to serve compressed content in general. What I also realize is, that you have to play around with the settings for your mime types. It's helpful to check for single files what mime type they actually have.
<br>
<br><b>Apache mod_deflate</b>
<br>
 If you are not willing to have additional load on your application server (which is quite common) you can dispatch this to someone who knows how to handle http. This is true for Apache's httpd. The module you are looking for is called <a href="http://httpd.apache.org/docs/2.0/mod/mod_deflate.html">mod_deflate</a> and you can simply load it along with your configuration. I assume you have something like mod_proxy in place to proxy all the requests against GlassFish through your httpd. Comparing starts getting a bit tricky here. Having mod_proxy in place means your response times drop a lot. So it would not be valid to compare against a direct request onto GlassFish. In fact, what I did is, that I compare the average response time against a not deflated response via Apache, the size is compared against GlassFish compression.
<br>
<table border="1">
 <tbody>
  <tr>
   <td>Type</td>
   <td># Requests</td>
   <td>Size (kb)</td>
   <td>time (ms)</td>
   <td>change<br>
     % size</td>
   <td>change<br>
     % time</td>
  </tr>
  <tr>
   <td>css</td>
   <td>11</td>
   <td>24.9</td>
   <td>551</td>
   <td>-79,25</td>
   <td>-5,97</td>
  </tr>
  <tr>
   <td>js</td>
   <td>12</td>
   <td>122,2</td>
   <td>55</td>
   <td>-73,48</td>
   <td>0,76</td>
  </tr>
  <tr>
   <td>html</td>
   <td>3</td>
   <td>22.6</td>
   <td>1470</td>
   <td>-93,62</td>
   <td>-1,29</td>
  </tr>
  <tr>
   <td>all</td>
   <td>52</td>
   <td>272,4</td>
   <td>2350</td>
   <td>-75,97</td>
   <td>-5,65</td>
  </tr>
  <tr>
   <td></td>
   <td></td>
   <td></td>
   <td></td>
   <td>-80,58</td>
   <td>-3,04</td>
  </tr>
 </tbody>
</table> Not a big surprise, right? They are both using gzip compression and this is a quite common and well known algorithm. So I did not expect any changes in compression effectiveness. But what you see is, that you have an unlike faster compression compared to running it on GlassFish. With an average overhead of roughly 3% you hardly can feel any change. That's a plus! Another plus is, that you can change the compression level with mod_deflate. Setting it from Zlib#s default to highest (9) gives you an extra bit of compression but it's not likely you see this higher than 1% overall which also could be a measuring inaccuracy. 
<br>
<br><b>Google mod_pagespeed</b>
<br>
 Yeah. That would have been a good additional test. But: I only have a Windows box running and the binaries are still only supported on some flavors of Linux. So, I need to skip it today. 
<br>
<br><b>Compression Filter</b>
<br>
 There are a lot of compression servlet filters out there. Back in the days, even BEA shiped one with their WebLogic. I guess as of today I would not use anything like this in production for stability reasons. I strongly believe, that there is not a single reason to let your appserver do any compression at all. Compressing content on-the-fly uses CPU time and being on an application server this is better spend onto other workload. Especially because you usually don't have a bandwidth problem between your appserver and your DMZ httpd.
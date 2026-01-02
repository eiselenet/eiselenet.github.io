---
title: "Software Version Control Visualization - Gource"
date: 2011-09-05 09:37:00 +0000
layout: post
tags: ["gource", "visualization"]
slug: "software-version-control-visualization"

url: /2011/09/software-version-control-visualization.html
---

I've been playing around with&nbsp;visualizations&nbsp;since some time. You might remember my post about <a href="http://blog.eisele.net/2011/02/glassfish-city-another-view-onto-your.html">GlassFish City</a> where I did a&nbsp;visualization&nbsp;with CodeCity. I came across another great visualization tool called <a href="" target="_blank">Gource</a>. It paints an animated tree of your projects repository history with the root directory of the project at its center. 
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="gource.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="240" src="gource.png" width="320"></a>
</div> Directories appear as branches with files as leaves. Developers can be seen working on the tree at the times they contributed to the project. The animation looks nice and you have a lot of <a href="http://code.google.com/p/gource/wiki/Controls" target="_blank">options to configure</a> the look and feel. The most interesting part is to generate short (or long :)) videos from the journey your team has taken through the project. And there is also a way to do this. Specifying the -o&nbsp;(aka --output-ppm-stream) option let's Gource write an&nbsp;uncompressed sequence of screenshots in PPM format which can then be processed by a video encoder to <a href="http://code.google.com/p/gource/wiki/Videos" target="_blank">produce a video</a>.
<br>
 The most basic example could be done with SVN, because SVN support is build in with Gource since 0.29.
<br>
<br>
<pre>svn log -r 1:HEAD --xml --verbose --quiet &gt; my-project-log.xml</pre>
<br>
 Next is to run gource on this: 
<br>
<br>
<pre>gource my-project-log.xml</pre>
<br>
 Now you have a nice UI where you cann simply watch whats happening or even drag around a bit to see what your team members were doing. If you are looking into creating a video you have to simply run gource in streaming mode:
<br>
<br>
<pre class="brush: text">gource my-project-log.xml -f --hide usernames -o neutral.ppm</pre>
<br>
<br>
 If you think about publishing it ... please hide the details (as shown above) and you could also think about tightening the timeline a bit.
<br>
<br>
<pre class="brush: text">--seconds-per-day 0.1 --auto-skip-seconds 0.1 </pre>
<br>
<br>
 After you are done, you have a more or less big file on your hdd which needs to be processed with your video encoder of choice. The most simple approach is to run your PPM stream through <a href="" target="_blank">ffmpeg</a>. 
<br>
<pre class="brush: text">ffmpeg -an -y -f image2pipe -vcodec ppm -i neutral.ppm -vcodec libx264 -s 800x600 -r 30.000 -vb 8000000 final.mp4 </pre>
<br>
<br>
 That's all. Enjoy!
---
title: "JEE complexity, documentation, pruning - some analyses and thoughts"
date: 2009-11-04 11:08:00 +0000
layout: post
tags: ["Java EE", "Java EE 5", "java", "enterprise", "Java EE 6"]
slug: "2009-11-04-jee-complexity-documentation-pruning"
url: /2009/11/jee-complexity-documentation-pruning.html
---

<blockquote>
 J2EE applications can be rapidly deployed and easily enhanced as the enterprise responds to competitive pressures.
 <br>
  (Source: Sun Microsystems Inc.: J2EE Specification)
 <br>
</blockquote>
<br>
 I am working with JEE6 these days. Try to design some new tech-workshops, sessions and lessons to bring others up to speed with this. During my research I cam across an old slide which talks about total complexity (in number of specification pages). Up to today it only covered J2EE 1.0 until J2EE1.4. But I needed to know more about the actual specifications JEE5 and JEE6. Ok. It was time to browser the jcp website and look out for the details.
<br>
 The result was unexpected. After having seen so many people complaining about the complexity/size of the J2EE if was finally heard by the Specification Leads and the JEE5 was launched with a slightly different goal than the J2EE specifications before:
<br>
<br>
<blockquote>
 The focus of Java EE 5 is ease of development. To simplify the development process for programmers just starting with Java EE, or developing small to medium applications, we’ve made extensive use of Java language annotations that were introduced by J2SE 5.0.
 <br>
  (Source: Sun Microsystems Inc.: JEE5 Specification)
 <br>
</blockquote>
<br>
 But as you can see, compared to the other versions it is the most complex one regarding to the total number of specification pages. Seems as if the fundamental work for simplyfing the platform itself comes to fruition with JEE6 finally.
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/complexity-757220.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="320" src="http://www.eisele.net/blog/uploaded_images/complexity-757218.png" width="320"></a>
<br>
<br>
 The graph itself is reduced to the core and well known APIs. This is a little more than half of the complete platform documentation for JEE6. The complete documentation consists of about 6300 pages (some release notes are not considered with this number).
<br>
<br><b>What else is to mention:</b>
<br>
 - You see the JSF specification comming in with JEE5. About 450 pages of specification, that were added. Slightly reduced to 400 with JEE6.
<br>
 - The size of the umbrella specifications was reduced. JEE5 (JSR-244) has about 80 pages of specification more than JEE6 (JSR-316).
<br>
 - Both JSP versions does not include the Expression Language parts. This would add another 122 pages of specification. JSTL (272 pages) is also not part of this number.
<br>
<br>
 One of the major goals of industry standards is the backward compability. This could be seen in the chart, too. As more and more specifications come in, the old ones still are not beeing dropped even if they are considered as not beeing state of the art anymore. A good examples of this is CMP. Removing the dead parts of APIs, outdated features and not well supported parts is something that will have more attention in the future. The JEE6 already introduced a concept for getting rid of this ballast, called prunning. Based on the community votings, some APIs will disappear from JEE7.
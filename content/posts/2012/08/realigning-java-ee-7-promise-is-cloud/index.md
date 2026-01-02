---
title: "Realigning Java EE 7 - A promise is a cloud; fulfillment is rain."
date: 2012-08-31 04:49:00 +0000
layout: post
tags: ["cloud", "javaee7", "realign"]
slug: "realigning-java-ee-7-promise-is-cloud"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2012/08/realigning-java-ee-7-promise-is-cloud.html
---

The news came out yesterday. <a href="https://blogs.oracle.com/theaquarium/entry/java_ee_7_roadmap" target="_blank">Java EE 7 is going</a> to be realigned and the PaaS enablement and multi-tenancy support will be moved to Java EE 8. While the email to the EG and the linked Aquarium post are only proposals the first EG members (including me) already responded positively. I am kind of relieved now and happy to share some of the reasons I see behind it.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="rain.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="200" src="rain.jpg" width="200"></a>
</div><span style="background-color: white; font-size: 16px; font-weight: bold;">re·a·lign</span><span style="background-color: white; font-size: 13px;">&nbsp;</span><span style="background-color: white; font-size: 13px;">&nbsp;</span><span style="background-color: white; border-bottom-color: rgb(128, 158, 131); border-bottom-style: dashed; border-bottom-width: 1px; cursor: pointer; font-size: 13px;">(/ˌrēəˈlīn/)</span>
<br>
<div style="background-color: white; font-size: 13px;">
 <i>tr.v.</i>&nbsp;<b>re·a·ligned</b>,&nbsp;<b>re·a·lign·ing</b>,&nbsp;<b>re·a·ligns</b>
 <br>
 <div class="ds-list" style="margin-left: 1cm;">
  <b>1.&nbsp;</b>To put back into proper order or alignment.
 </div>
 <div class="ds-list" style="margin-left: 1cm;">
  <b>2.&nbsp;</b>To make new groupings of or working arrangements between.
 </div>
</div>
<br>
 What&nbsp;happened? You can roughly split this up into two parts.
<br>
<br><b>Emerging needs in the community</b>
<br>
 The significant enhancements in simplification, usability, and functionality in updated versions of major JSRs which came with Java EE 6 are still far from perfect. We have seen a lot of requests regarding ongoing efforts to complete what has been started with EE 6. To name but a few the integration of JSON-P, the simplified JMS 2.0 APIs, further Managed Bean alignment, including transactional interceptors, a JAX-RS 2.0 client API, support for method-level validation, a much more comprehensive expression language and many many more. We are in need of a version I like to call Java EE 6.5. A comprehensive and complete version of what we have as of today.
<br>
<br><b>Slow progress on the cloud side of our agend</b>
<br>
 The second part is the fact that we didn't see enough progress with the PaaS or cloud parts of EE 7 so far. As Red Hat's Pete Muir said: 
<br>
<blockquote>
 We've long advocated that we, the Java EE community are not ready to standardise cloud yet, and feel this is proven by OpenShift, our Java EE cloud offering, which is working well with Java EE 6. (Source: <a href="http://java.net/projects/javaee-spec/lists/jsr342-experts/archive/2012-08/message/11">EG Mailinglist</a>)
</blockquote> And this is pretty much to the point. Even if I am an advocate of innovation with standardization this might simply be too early and as I personally see this today, the EG is missing some significant members of the Java cloud market (e.g. Jelastic). Even if Oracle is working with them to optimize their GlassFish offerings and I believe that there are first best-practices around this simply isn't enough for starting to standardize it today.
<br>
 The Arabian Proverb from the title does fit this very well. At the moment all we can do is to promise stuff but we have never actually seen enough of the rain to start extracting the essentials that need to make it into the platform.
<br>
<br>
 Given those two parts it is a brave and honest decision of the EG to realign Java EE 7 to the community needs and make a second try on Cloud and PaaS with the Java EE 8 Platform release scheduled for the spring of 2015.
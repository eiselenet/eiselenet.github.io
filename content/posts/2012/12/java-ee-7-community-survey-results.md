---
title: "Java EE 7 Community Survey Results!"
date: 2012-12-14 09:02:00 +0000
layout: post
tags: ["survey", "javaee7", "results"]
slug: "2012-12-14-java-ee-7-community-survey-results"
url: /2012/12/java-ee-7-community-survey-results.html
---

Work on Java EE 7 presses on under JSR 342. Things are shaping up nicely and Java EE 7 is now in the Early Draft Review stage. In beginning of November <a href="https://blogs.oracle.com/theaquarium/entry/wanted_now_your_feedback_on" target="_blank">Oracle posted a little community survey</a> about upcoming Java EE 7 features. Yesterday the results were published.
<br>
 Over 1,100 developers participated in the survey and there was a large number of thoughtful comments to almost every question asked. Compare the <a href="http://java.net/projects/javaee-spec/lists/jsr342-experts/archive/2012-12/message/46" target="_blank">prepared PDF</a> attached to the EG mailing-list discussion.
<br>
<br><b>New APIs for the Java EE 7 Profiles</b>
<br>
 We have a couple of new and upcoming APIs which needs to be incorporated into either the Full or the Web Profile. Namely this are <a href="http://www.jcp.org/en/jsr/detail?id=356" target="_blank">WebSocket 1.0</a>, <a href="http://www.jcp.org/en/jsr/detail?id=353" target="_blank">JSON-P 1.0</a>, <a href="http://www.jcp.org/en/jsr/detail?id=352" target="_blank">Batch 1.0</a> and <a href="http://www.jcp.org/en/jsr/detail?id=107" target="_blank">JCache 1.0</a>. The community was asked in which profile those should end up. The results about which of them should be in the Full Profile:
<br>
<br>
<table align="center" cellpadding="0" cellspacing="0" class="tr-caption-container" style="margin-left: auto; margin-right: auto; text-align: center;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="add_to_full_profil.png" imageanchor="1" style="margin-left: auto; margin-right: auto;"><img border="0" height="217" src="add_to_full_profil.png" width="320"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;">Add to Full Profile?</td>
  </tr>
 </tbody>
</table>
<br>
 As the graph depicts, support is relatively the weakest for Batch 1.0, but still good. A lot of folks saw JSON-P and WebSocket 1.0 as a critical technology. 
<br>
 The same for both with regards to the Web Profile. Support for adding JCache 1.0 and Batch 1.0 is relatively weak. Batch got 51.8% 'No' votes.
<br>
<table align="center" cellpadding="0" cellspacing="0" class="tr-caption-container" style="margin-left: auto; margin-right: auto; text-align: center;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="add_to_web_profil.png" imageanchor="1" style="margin-left: auto; margin-right: auto;"><img border="0" height="220" src="add_to_web_profil.png" width="320"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;">Add to Web Profile?</td>
  </tr>
 </tbody>
</table>
<br><b>Enabling CDI by Default</b>
<br>
 The majority (73.3%) of developers support enabling CDI by default. Also the detailed comments reflect a strong general support for CDI as well as a desire for better Java EE alignment with CDI.
<br>
<br><b>Consistent Usage of @Inject</b>
<br>
 A light majority (53.3%) of developers support using @Inject consistently across all Java EE JSRs. 28.8% still believe using custom injection annotations is ok. The remaining 18.0% were not sure about the right way to go. The vast majority of commenters were strongly supportive of CDI and general Java EE alignment with CDI.
<br>
<br><b>Expanding the Use of @Stereotype</b>
<br>
 62.3% of the attending developers support expanding the use of @Stereotype across Java EE. A majority of the comments express ideas about general CDI/Java EE alignment.
<br>
<br><b>Expanding Interceptor Use</b>
<br>
 96.3% of developers wanted to expand interceptor use to all Java EE components. 35.7% even wanted to expand interceptors to other Java EE managed classes. Most developers (54.9%) were not sure if there is any place that injection is supported that should not support interceptors. 32.8% thought any place that supports injection should also support interceptors. The remaining 12.2% were certain that there are places where injection should be supported but not interceptors. 
<br>
<br>
 Thanks for taking the time answering the survey. This gives a solid decision base for moving on with Java EE 7. Keep the feedback coming and subscribe to the <a href="mailto:users@javaee-spec.java.net" target="_blank">users@javaee-spec.java.net</a> alias (<a href="http://java.net/projects/javaee-spec/lists/users/archive" target="_blank">see archives online</a>)!
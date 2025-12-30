---
title: "Why I think the EC is wrong about JSR 357 and innovation"
date: 2012-03-20 12:55:00 +0000
layout: post
tags: ["innovation", "java", "Java_Social", "EC", "jcp"]
slug: "2012-03-20-why-i-think-ec-is-wrong-about-jsr-357"
url: /2012/03/why-i-think-ec-is-wrong-about-jsr-357.html
---

As of yesterday the newly proposed <a href="http://jcp.org/en/jsr/detail?id=357" target="_blank">JSR 357</a> (Social Media API) was declined by the JCP Executive Committee for SE/EE. With eight clear "NO" votes, two abstain, five "YES" and one not voted member this is a clear result.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="357_voting.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="104" src="357_voting.PNG" width="320"></a>
</div> If you are examining the voting comments, you see two obvious reasons for this to happen. One is the scope of the proposed JSR which "is far too wide" (IBM) and the second is "it's a bit too early to start standardizing on social" (Twitter). While the first has some additional aspects around having to specify relevant domain objects (which is very similar to what the Java Identity API, <a href="http://jcp.org/en/jsr/detail?id=351">JSR 351</a> will have to solve) the second one is surprising. 
<br>
<br><b>When should Standardization happen?</b>
<br>
 Have you ever thought about standardization? When exactly should it happen? And why? First of all you have to look into what standards provide. For a German like me it feels normal to look at what German DIN institute has to say about this: 
<br>
<blockquote>
 "Standardization is a strategic instrument for economic success. By becoming involved in standards work, businesses can gain a competitive lead through timely access to information and knowledge. They can use this to their own advantage, reducing the risks and costs involved in R &amp; D as well as greatly reducing transaction costs."
 <br>
  (Source: <a href="http://www.din.de/cmd?menuid=47563&amp;level=tpl-bereich&amp;cmsareaid=47563&amp;languageid=en" target="_blank">din.de</a>)
</blockquote> Further on the relation to the field of innovation is expressed as follows: 
<br>
<blockquote>
 "Standards serve as a knowledge base and catalyst for innovation. [...] Standards help researchers in various ways: They lay down terminology in new technological areas, set quality and safety norms at an early stage of development, and introduce the necessary standardized, compatible interfaces. [...] Carrying out standardization at an early phase of development helps establish high tech innovations on the market."
 <br>
  (Source: <a href="http://www.din.de/cmd?menuid=47563&amp;cmsareaid=47563&amp;menurubricid=57882&amp;level=tpl-rubrik&amp;cmsrubid=57882&amp;languageid=en" target="_blank">din.de</a>)
</blockquote> This expresses what I have seen in the wild a lot. Take a look at what happened with HTML, WebSockets or NoSQL. Or compare some open source projects. If you start putting a lot effort into something you have a high interest in spreading the word. Even if it only is a small spin off and you don't have too much common sense you start putting your thoughts and code into a sandbox or at least a wiki. To me standardization is the key to innovation because it simply is a short term which means nothing else than "put all players at a table together". 
<br>
<br><b>Documenting the status quo?</b>
<br>
 This is exactly the opposite of what the EC members had to say about the JSR 357. Reading through the lengthy comment section gives you the impression, that there should be standards if some kind of solution is mature enough to be used by a majority already. In other words: We wait for the unofficial reference implementation which drove enough companies into a vendor-lock-in situation (closed- or open-source) and start standardizing afterwards. If you followed the JCP long enough you might have seen this before. Doesn't it sound like Seam 2/Spring for CDI or Guice for JSR 330? I for myself am not sure if this is the right way to go with the JCP in general. If it is common sense to only adopt what is already out there the JCP will fall behind recent developments and trends even faster than it already did in the past. And it always will follow some kind of documenting the status quo approach. Is that, why we need all the "big names" in the JCP? Because having them agreeing to a standards guarantees for their adoption? I don't think it was meant that way. 
<br>
 I would love to call on the EC members to accept their commitment for developing the Java ecosystem and take this more seriously in the future. 
<br>
<br><b>Other Aspects</b>
<br>
 Let's finish this little angry excursion with the still open issues with 357. Yes. It finally is too broadly scoped. The spec leads have accepted this before and addressed it with a direct communication with the EC members. 
<br>
<blockquote>
 "... the scope of the JSR primarily is Connecting to Social Media Services and Providers, so similar to the first JSON JSR one could see it as Java Social Media Client API" 
 <br>
  (Source: Goldman Sachs &amp; Co voting comments, <a href="http://jcp.org/en/jsr/results?id=5317" target="_blank">jcp.org</a>)
</blockquote> This obviously should have been more clear upfront and the spec leads and the designated EG should have taken some action to address those issues earlier. 
<br>
 Further on it might be a bit risky to rely on things that don't exist yet in the standard. Namely REST client API and JSON processing. But this shouldn't have been a major issue and it happened before to other JSRs. 
<br>
<br><b>Bottom Line</b>
<br>
 It was obviously too early for this JSR. At least for the JCP as it is today. And it showed very clearly what it takes to successfully pass a review ballot. You need a hell lot of support among the EC to move stuff forward. As of today the combination of simply technical voting members like the LJC and the political voting members like SAP and IBM leads to a very easy and fast rejection of a JSR. Maybe it would be a good idea to think about different review ballots for technical and business impacts and define different constraints on JSRs if they are rejected by one or the other ballot. A JSR which was rejected by a business ballot could still be formed and have an active EG which could start developing on it's standard. All under the wings of the JCP. This is exactly what it should be to me. A community process. Not a documentation tool.
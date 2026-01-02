---
title: "Software Quality: JSF Component Libraries - Findbugs Results"
date: 2009-12-14 11:51:00 +0000
layout: post
tags: ["findbugs", "components", "software quality", "jsf"]
slug: "software-quality-jsf-component_14"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2009/12/software-quality-jsf-component_14.html
---

This is part IV of the software quality analysis series about common jsf component libraries.
<br>
 Today we are looking at the <a href="" target="_blank">findbugs</a> results for them in more detail.
<br>
 To make this more transparent to you, I am running the <a href="http://findbugs.cs.umd.edu/demo/jnlp/findbugs.jnlp">Java Webstart version of findbugs</a>. Feel free to give it a try on your own version of the codebase of the projects.
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/findbugsgui-769524.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="320" src="http://www.eisele.net/blog/uploaded_images/findbugsgui-769522.png" width="320"></a>
<br>
<br><b>PrimeFaces</b>
<br>
 You find a total of 126 errors within the PrimeFaces codebase. Eight of them are classified as high prio. 118 have a normal priority. If you look at the eight high prio errors you find one possible multithreading bug and seven malicious code vulnerabilities. The malicious code vularabilities araise from fields that are mutable arrays. This are final static field referencess to arrays which can be accessed by malicious code or by accident from another package. You find all occurences in the same utility class org.primefaces.util.HTML.
<br>
 The multithreading bug can be found in the org.primefaces.component.media.player.MediaPlayerFactory. The static Map players is layzily initialized but not synchronized. After the field is set, the object stored into that location is further accessed. The setting of the field is visible to other threads as soon as it is set. If the futher accesses in the method that set the field serve to initialize the object, then you have a very serious multithreading bug, unless something else prevents any other thread from accessing the stored object until it is fully initialized.
<br>
<br>
 The normal prio bugs devide into all categories. Five bad practice warnings, 82 correctness warnings (all null pointer dereference in method on exception path, which could be a false warning), some more (3) malicious code vulnerabilities warnings (stored references, exposing internal representation), one performance bug in org.primefaces.component.messages.MessagesRenderer.encodeEnd(FacesContext, UIComponent) where the value of a Map entry is accessed using a key that was retrieved from a keySet iterator. It is more efficient to use an iterator on the entrySet of the map, to avoid the Map.get(key) lookup. Last but not least there are 19 dodgy stile warnings of which 18 are useless control flow statements. Which look like this for example:
<br><code><br>
  if(resourceHolder != null) \{<br>
  \}<br></code>
<br>
<br>
 Conclusion: 
<br>
 That is not bad at all. Even if we take into account, that the codebase for PrimeFaces is the smallest one of the three, this is not the place to blame anyone about the results! And to remember, this is still a SNAPSHOT version! 
<br>
<br>
<br><b>RichFaces</b>
<br>
 The Richfaces code base provides 291 errors. 15 classified as high prio. 261 normal priority. Seven high prio bugs fall into the bad practice category (possible problems around serialization). This most likely will only be seen in clustered failover scenarios. There are five correctnes warnings. (possible infinite loop, null pointer dereferences, problem with equals and a bad comparison of signed byte. There are also two malicious code vulnerabilities (mutable static fields) and one possible multithreading bug (wait() with two locks held). Things to look at and to remove. 
<br>
<br>
 The normal prio bugs also devide into all categories. Hotspot are the 91 performance warnings. Most of the warnings result in unnecessary object creation and therefore memory consumption. Second on the list are the possible malicious code vulnerabilities with a count of 70. Followed by 47 bad practice warnings and 41 dodgy stlyle warnings. 
<br>
<br>
 Conclusion: 
<br>
 With hardly the same code size of PrimeFaces this is also a good result. Having seen more RichFaces projects lately, I would be happy if someone takes a deeper look at the performance warnings. Memory consumption seems to be an issue. 
<br>
<br>
<br>
<br><b>ICEFaces</b>
<br>
 The biggest code base is provided by the ICEFaces. To have the most FindBugs warnings in it, no surprise at all. 373 in total. 106 High priority warnings followed by 267 normal priority warnings. 
<br>
 Bad practice warnings (49) and malicious code vulnerabilities (47) cover the most bugs. As the only candidate, ICEFaces is ignoring exceptions in three places. That is bad.
<br>
<br><code><br>
  \} catch (Exception e) \{ \}<br></code>
<br>
<br>
 But you can also find some serialization issues. All malicious code vulnerability warnings relate to mutable static fields. The majority of them shoud be final but are not. Three correctness warnings, two possible multithreading issues and five dodgy style warnings make the rest of the high prio bugs. All more or less smaller things to change.
<br>
<br>
 The normal priority bugs are lead by the performance warnings (77) and followed by bad practices (44) and malicious code vulnerability warnings (46). What is impressive are the 80 dodgy style warnings. Last bigger category are the possible multithreaded correctness warnings (17). 
<br>
<br>
 Conclusion:
<br>
 Compared with the other two candidates, this looks much on the first sight. But the codebase is by far the biggest and therefore it is not too bad at all. 
<br>
<br><b>Bottom line</b>
<br>
 Compared with each other, all code bases look identically in terms of findbugs. You find more or less all categories in some lines of any candidate. What is to note is, that the bigger the code base gets, the more performance issues you can find. I expected the bigger frameworks to watch out for them more carefully. The bug density for all is nearly the same. 
<br>
 Only looking at the metrics itsel does not give you a clue about the quality. You need to have a deeper look at every single warning and check the severity.
<br>
 What is interesting to some extend is, that it is hard to setup findbugs for the projects manually. The source folders are quite distributed along the development structure and you have to spend some time to set it up.
<br>
 Only RichFaces has included some quality checks (PMD, Findbugs, Checkstyle) into their nightly builds. PrimeFaces and ICEFaces are still in need of this.
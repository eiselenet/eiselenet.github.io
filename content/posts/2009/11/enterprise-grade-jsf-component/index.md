---
title: "Enterprise grade JSF component libraries and how to find them"
date: 2009-11-17 10:56:00 +0000
layout: post
tags: ["richfaces", "adf", "jsf", "enterprise", "primefaces"]
slug: "enterprise-grade-jsf-component"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2009/11/enterprise-grade-jsf-component.html
---

It is quite obvious, that there is a still growing number of jsf component libraries out there. The most popular ones are compared on my favorite site <a href="">www.jsf-matrix.net</a>.
<br>
 Working with clients in everyday projects I sometimes need to make a proposal for one or the other library to use. But what are the decision making points on this? What does "enterprise grade" really mean?
<br>
 Even if I am not going to present a complete evaluation report about JSF libraries here, I will try to introduce my approach in finding enterprise-grade JSF component libraries for customers. And further on, I will try to have a more detailed look at three component libraries I choose to evaluate in customer situations quite often.
<br>
<br><strong>Definition: enterprise-grade</strong>
<br>
 My first try was to find out about the definition of enterprise-grade. The wikipedia keeps silent about this. No article about this does exist. Ok. Some googling does return more results. Anyway, as silently allready expected,
<br>
 you will not find a definition anywhere. In a <a href="http://www.theserverside.com/tt/knowledgecenter-is/knowledgecenter-is.tss?l=PodcastEdBurns">2008 TSS Discussion</a>, Ed Burns talked about Enterprise Grade Ajax and JSF already. He defined it as follows:
<br>
<br>
<blockquote>
 [...] something that is robust, scalable, well tested, and industry proven.
 <br>
</blockquote>
<br><strong>General evaluation approach</strong>
<br><i>Evaluation Criteria</i>
<br>
 If you compare the customers needs to Ed's keywords, you obviously find some missing parts. Resorted and completed it could lead to a list like the following:
<br>
<br>
 - robustness
<br>
 - scalability
<br>
 - testability
<br>
 - # of available components
<br>
 - browser support
<br>
 - development tools support
<br>
 - professional support 
<br>
 - # of industry references
<br>
 - size of the community
<br>
 - software quality
<br>
 - documentation (size/quality)
<br>
 - # releases per year
<br>
 - avrg time for bugfixing
<br>
 - customizability
<br>
 - licensing
<br>
 - avalable since
<br>
<br>
 This is still a very limited list and could be expanded, to the customers needs. But to me it seems as if this are the most interesting points to evaluate if you are looking for an enterprise-grade product. Having special platforms or environments in place, you could even think about defining categories (non-technical, technical, enterprise, strategic and so on..)
<br>
<br><i>Candidates</i>
<br>
 Collecting the criteria is the first part. Next step is to identify the candidates to evaluate. Sometimes this is also called a shortlist. There are some pre evaluation suggestions around. To me it is most valuable to define some necessary and sufficient conditions and only discuss those libraries meeting the sufficient criteria. In most cases it should be perfect to work with up to five different libraries in the comparisson. For each of them you have to do the criteria evaluation.
<br>
<br><i>Cost-utility analyses</i>
<br>
 If you have your criteria and degrees of fulfilment in place, you should do your analysis. There are some more or less common approaches in place to do this. A more general description could be <a href="http://en.wikipedia.org/wiki/Cost-utility_analysis">found here</a>. The <a href="http://de.wikipedia.org/wiki/Nutzwertanalyse">german articel in the wikipedia</a> is more complete and also defines some functions as guidelines.
<br>
<br><strong>Personal shortlist</strong>
<br>
 Looking at the jsf-matrix again is a perfect starting point for building a shortlist. My personal shortlist is influenced by what I know from my customers and their requirements. Therefore I have chosen the following three:
<br>
<br>
 - <a href="http://www.jboss.org/richfaces">RichFaces</a>
<br>
 RichFaces is a component library for JSF and an advanced framework for easily integrating AJAX capabilities into business applications.
<br>
<br>
 - <a href="http://www.oracle.com/technology/products/adf/adffaces/index.html">Oracle ADF Faces</a>
<br>
 Oracle ADF Faces Components is a set of over a 150 Ajax-enabled JSF components that let you build a richer user interface for your Java EE applications. Oracle ADF Faces also includes many of the framework features most needed by JSF developers today.
<br>
<br>
 - <a href="">PrimeFaces</a>
<br>
 PrimeFaces is an open source component suite for Java Server Faces featuring 70+ Ajax powered rich set of JSF components. Additional TouchFaces module features a UI kit for developing mobile web applications.
<br>
<br><strong>Criteria fulfillment</strong>
<br>
 Here are some examples derived from the product's websites and communities for selected criteria. 
<br>
<br>
<table border="0" cellpadding="0" cellspacing="0">
 <tbody>
  <tr height="20" style="height:15.0pt">
   <td height="20" width="191" style="height:15.0pt;width:143pt"></td>
   <td width="76" style="width:57pt"><strong>RichFaces</strong></td>
   <td width="113" style="width:85pt"><strong>Oracle ADF Faces</strong></td>
   <td width="102" style="width:77pt"><strong>PrimeFaces</strong></td>
  </tr>
  <tr height="20" style="height:15.0pt">
   <td height="20" style="height:15.0pt"># of available components</td>
   <td>100</td>
   <td>150</td>
   <td>70</td>
  </tr>
  <tr height="20" style="height:15.0pt">
   <td height="20" style="height:15.0pt">development tools support</td>
   <td>Jboss Tools</td>
   <td>JDeveloper</td>
   <td>none</td>
  </tr>
  <tr height="20" style="height:15.0pt">
   <td height="20" style="height:15.0pt">professional support</td>
   <td>yes</td>
   <td>yes</td>
   <td>none</td>
  </tr>
  <tr height="20" style="height:15.0pt">
   <td height="20" style="height:15.0pt">size of the community<br>
     (members forum)</td>
   <td>14.498</td>
   <td></td>
   <td>3600</td>
  </tr>
  <tr height="20" style="height:15.0pt">
   <td height="20" style="height:15.0pt">documentation<br>
     (size/quality)</td>
   <td>lots/ok</td>
   <td>lots/ok</td>
   <td>avrg/ok</td>
  </tr>
  <tr height="20" style="height:15.0pt">
   <td height="20" style="height:15.0pt"># releases per year</td>
   <td class="xl642470">3 - 4</td>
   <td>1</td>
   <td>&gt;6</td>
  </tr>
  <tr height="20" style="height:15.0pt">
   <td height="20" style="height:15.0pt">avrg time for<br>
     bugfixing/days</td>
   <td>60</td>
   <td></td>
   <td></td>
  </tr>
  <tr height="20" style="height:15.0pt">
   <td height="20" style="height:15.0pt">customizability</td>
   <td>skins</td>
   <td>skins</td>
   <td>skins</td>
  </tr>
  <tr height="20" style="height:15.0pt">
   <td height="20" style="height:15.0pt">licensing</td>
   <td>LGPL</td>
   <td>Commercial</td>
   <td>Apache License</td>
  </tr>
  <tr height="20" style="height:15.0pt">
   <td height="20" style="height:15.0pt">avalable since</td>
   <td></td>
   <td></td>
   <td>2009</td>
  </tr>
 </tbody>
</table>
<br><strong>Conclusion</strong>
<br>
 As you can imagine, it is not too easy to gather all those informations. In some cases it could be a solution to prepare a request for information and send it out to the vendors in order to have your questions answered.
<br>
 Before you choose a component library it is best to have a very good understanding of what it is capable in general. Most notably you can only combine selected libraries with each other. Therefore, you should wisely take the component suite, best fitting your projects needs.
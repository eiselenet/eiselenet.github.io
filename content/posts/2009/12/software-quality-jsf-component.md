---
title: "Software Quality: JSF Component Libraries"
date: 2009-12-07 11:19:00 +0000
layout: post
tags: ["components", "software quality", "jsf"]
slug: "2009-12-07-software-quality-jsf-component"
url: /2009/12/software-quality-jsf-component.html
---

Working for a larger sofware company has some advantages. One of them is, to have a software quality checker at hand, which can be easily configured to run on any java project. What is called "msgJavaMessplatz" (Java measuring station) depends on quite popular tools. 
<br>
<br>
 - <a href="" target="_blank">JavaNCSS </a>- A Source Measurement Suite for Java
<br>
 JavaNCSS is a simple command line utility which measures two standard source code metrics for the Java programming language. The metrics are collected globally, for each class and/or for each function. 
<br>
<br>
 - <a href="http://checkstyle.sourceforge.net/4.4/index.html" target="_blank">Checkstyle 4.4</a>
<br>
 Checkstyle is a development tool to help programmers write Java code that adheres to a coding standard. 
<br>
<br>
 - <a href="" target="_blank">FindBugs</a>
<br>
 Is a program which uses static analysis to look for bugs in Java code.
<br>
<br>
 - <a href="" target="_blank">Simian v 2.2.17</a>
<br>
 Simian (Similarity Analyser) identifies duplication in Java Code.
<br>
<br>
 - <a href="http://source.valtech.com/display/dpm/Dependometer" target="_blank">Dependometer</a>
<br>
 Dependometer performs a static analysis of physical dependencies within a software system.
<br>
<br>
 - <a href="" target="_blank">Cobertura</a>
<br>
 Cobertura is a tool that calculates the percentage of code accessed by tests.
<br>
<br>
 This was planned for quite some time now. Today, I finaly managed to setup the testing environment and run a few quality checks with the first candidates.
<br>
 To give you a brief overview of the candidates, here are the basic metrics in terms of size and quality. I will compile more detailed results during the week and publish selected results. So stay tuned for more ....
<br>
<br><a href="">PrimeFaces 1.0.0-SNAPSHOT</a>
<br><a href="http://www.jboss.org/richfaces">RichFaces 3.3.X</a>
<br><a href="">ICEfaces 1.7.1</a>
<br>
<br>
<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse; table-layout: fixed; width: 393px;">
 <colgroup>
  <col style="width: 115pt;" width="153">
 </colgroup>
 <colgroup>
  <col span="3" style="width: 60pt;" width="80">
 </colgroup>
 <tbody>
  <tr height="20" style="height: 15pt;">
   <td height="20" style="height: 15pt; width: 115pt;" width="153"></td>
   <td style="width: 60pt;" width="80">ICEfaces<br></td>
   <td style="width: 60pt;" width="80">RichFaces<br></td>
   <td style="width: 60pt;" width="80">PrimeFaces<br></td>
  </tr>
  <tr height="20" style="height: 15pt;">
   <td height="20" style="height: 15pt;">Package Depth<br></td>
   <td align="right">7<br></td>
   <td align="right">5<br></td>
   <td align="right">5<br></td>
  </tr>
  <tr height="20" style="height: 15pt;">
   <td height="20" style="height: 15pt;">Type Inheritance<br></td>
   <td align="right">6<br></td>
   <td align="right">4<br></td>
   <td align="right">3<br></td>
  </tr>
  <tr height="20" style="height: 15pt;">
   <td height="20" style="height: 15pt;">NCSS<br></td>
   <td align="right">38087<br></td>
   <td align="right">9186<br></td>
   <td align="right">8340<br></td>
  </tr>
  <tr height="20" style="height: 15pt;">
   <td height="20" style="height: 15pt;"># Classes<br></td>
   <td align="right">520<br></td>
   <td align="right">225<br></td>
   <td align="right">160<br></td>
  </tr>
  <tr height="20" style="height: 15pt;">
   <td height="20" style="height: 15pt;"># of Functions<br></td>
   <td align="right">5601<br></td>
   <td align="right">1526<br></td>
   <td align="right">796<br></td>
  </tr>
  <tr height="20" style="height: 15pt;">
   <td height="20" style="height: 15pt;"># Packages<br></td>
   <td align="right">66<br></td>
   <td align="right">21<br></td>
   <td align="right">86<br></td>
  </tr>
  <tr height="20" style="height: 15pt;">
   <td height="20" style="height: 15pt;"># design rule violations<br></td>
   <td align="right">243<br></td>
   <td align="right">39<br></td>
   <td align="right">25<br></td>
  </tr>
  <tr height="20" style="height: 15pt;">
   <td height="20" style="height: 15pt;"># import rule violations<br></td>
   <td align="right">127<br></td>
   <td align="right">15<br></td>
   <td align="right">6<br></td>
  </tr>
  <tr height="20" style="height: 15pt;">
   <td height="20" style="height: 15pt;">Findbugs Total Warnings<br></td>
   <td align="right">760<br></td>
   <td align="right">173<br></td>
   <td align="right">217<br></td>
  </tr>
  <tr height="20" style="height: 15pt;">
   <td height="20" style="height: 15pt;">Findbugs Density<br></td>
   <td align="right">13,2<br></td>
   <td align="right">3,69<br></td>
   <td align="right">9,13<br></td>
  </tr>
 </tbody>
</table>
<br>
<br>
 Screenshot of the "msgJavaMessplatz".
<br>
 ..oO(you can not buy the tool ... )
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/msgjmp-710159.png" imageanchor="1" style="clear: left; float: left; margin-bottom: 1em; margin-right: 1em;"><img border="0" height="320" src="http://www.eisele.net/blog/uploaded_images/msgjmp-710157.png" width="320"></a>
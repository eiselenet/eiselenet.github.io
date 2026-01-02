---
title: "Software Quality on JSF Mojarra 2.0.3-SNAPSHOT"
date: 2010-10-14 11:58:00 +0000
layout: post
tags: ["jsf 2.0", "software quality"]
slug: "software-quality-on-jsf-mojarra-203"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2010/10/software-quality-on-jsf-mojarra-203.html
---

Seeing all those political stuff on my blog lately I personaly need a break. And what is best? Get your hands dirty :) And this is what I did. I downloaded the latest mojarra-2.0.3-FCS-source.zip (12/10/10), build it and did a brief setup of my favorite software quality checker with both the API and the RI.
<br>
 And back to the initial topic, here are the dirty findings :).
<br>
<br><b>Preface</b>
<br>
 I did this without knowing that much about the project. I am not a committer or in any other way related with the Mojarra Team. Therefore, my findings may leak a bit of interpretation. I also missed to discuss the findings with the team before publishing. First of all, they are not critical (everything works and runs literally in a thousands of places) and second they are not about security or anything else compromising. A quick "Thank you!" to Jochen, who helped me with the msg.Java measuring station. I am open to questions and would love to hand the complete report to the team (@Edburns, you know, how to reach me :)). 
<br>
<br><b>msg.Java measuring station</b>
<br>
 The JMP is described in more detail in a <a href="http://blog.eisele.net/2009/12/software-quality-jsf-component.html">previous post</a> (dec last year). Up to now only the tool versions changed. We are running at the moment:
<br>
<pre>[echo] == Ncss 32.53 [echo] == Checkstyle 5.0 [echo] == FindBugs 1.3.9 [echo] == Simian 2.2.24 [echo] == Dependometer 1.18 </pre>
<br><!--more-->
<br><b>Basic data</b>
<br>
 The <a href="" target="_blank">Mojarra project</a> is both, the referenz implementation and the <a href="http://jcp.org/en/jsr/detail?id=314" target="_blank">JSR-314</a> API project for the JavaServer(TM) Faces 2.0 technology. It simplifies building user interfaces for JavaServer applications. Developers of various skill levels can quickly build web applications by: assembling reusable UI components in a page; connecting these components to an application data source; and wiring client-generated events to server-side event handlers. If you look at the code you are roughly talking about the following.
<br>
<br>
<table>
 <tbody>
  <tr>
   <th>NCSS LoC</th>
   <th># of Classes</th>
   <th># of Packages</th>
  </tr>
  <tr>
   <td>55189</td>
   <td>1111</td>
   <td>68</td>
  </tr>
 </tbody>
</table>
<br>
 The deployable results are:
<br>
<br>
<table>
 <tbody>
  <tr>
   <th>layer</th>
   <th>jar</th>
   <th>size</th>
  </tr>
  <tr>
   <td>jsf-api</td>
   <td>jsf-api.jar</td>
   <td>593 KB</td>
  </tr>
  <tr>
   <td>jsf-ri</td>
   <td>jsf-impl.jar</td>
   <td>1.827 KB</td>
  </tr>
 </tbody>
</table>
<br>
 The project itself has some software quality checks in place. You can for example access <a href="https://javaserverfaces.dev.java.net/jsf20-findbugs.html" target="_blank">findbugs reports</a> for every release. The plain numbers differ a bit between both analysis. Possible causes include the problem fields of generated Java classes and the jsf-tools project beeing included or not. I did NOT include the generated sources but the jsf-tools are contained in my analysis.
<br>
<br><b>NCSS Audit</b>
<br>
 We are interested in the NCSS (Non Commenting Source Statements) per method which determines the number of lines of code for a given method and their distribution. 
<br>
 The theoretical limits behind this are 
<br>
 - # of classes per package must not exceed 40.
<br>
 - # of functions per class must not exceed 20.
<br>
 - ncss per function must not exceed 25.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="ncss_distribution.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="276" src="ncss_distribution.png" width="320"></a>
</div>
<br>
 This basically gives you an idea about how structured the project is. Most of it seems to be ok according to the plain numbers. Bigger methods should be the exception. Smaller methods with up to eight NCSS should make up more than 60% of the code. Great. If you look at the distribution to packages, you find some hotspots. It seems as if a lot is going on there. These are probably the places, you should mark for a detailed analysis. With an average of 5.94 per function, you are generally save.
<br>
<br>
<table>
 <tbody>
  <tr>
   <th>Classes</th>
   <th>Functions</th>
   <th>NCSS</th>
   <th>Package</th>
   <th></th>
  </tr>
  <tr>
   <td>41</td>
   <td>259</td>
   <td>1487</td>
   <td>com.sun.faces.facelets.compiler</td>
  </tr>
  <tr>
   <td>41</td>
   <td>137</td>
   <td>1221</td>
   <td>com.sun.faces.facelets.tag.jsf</td>
  </tr>
  <tr>
   <td>50</td>
   <td>374</td>
   <td>3104</td>
   <td>com.sun.faces.application</td>
  </tr>
  <tr>
   <td>68</td>
   <td>756</td>
   <td>5102</td>
   <td>javax.faces.component</td>
  </tr>
 </tbody>
</table>
<br><b>CCN Audit</b>
<br>
 CCN is the abbreviation for Cyclomatic Complexity Number. It is also known as the McCabe Metric. There exists a much hyped theory behind it based on graph theory, but it all comes down to simply counting 'if', 'for', 'while' statements etc. in a method. Whenever the control flow of a method splits, the "CCN counter" gets incremented by one. Having a CNN below 10 is quite normal. The maximum count must not exceed 25. Looking at the code, only 27 methods do exceed this. This are mostly the bigger ones, also seen during the NCSS runs. With an average CCN per function of 2.83 you don't have to worry but keep the hotspots on your list.
<br>
<br><b>CheckStyle Audit</b>
<br>
 Checkstyle reviews Java code for it's compliance to coding standards. The standard checks are applicable to general Java coding style. The optional checks are available for Java EE artifacts. I used a set that is specific to our company. This is, how we love to deliver high quality software to our customers. In general it's up to the projects to decide about the set of checks. And to be honest, there is still plenty of room for improvements and discussions. 
<br>
 Looking at both the RI and the API some of the "Errors" comming up, are simply unavoidable. Someone has to do it. At last the container and the RI is nothing else. Therefore, I am fine with most of the errors. In general the results sueggest to have this on the list for future releases. It's better to avoid bad style to have a maintainable codebase. Both RI and API could improve on this a lot.
<br>
<br>
<table>
 <tbody>
  <tr>
   <th>Files</th>
   <th>Error</th>
   <th>Warning</th>
   <th>Info</th>
  </tr>
  <tr>
   <td>821</td>
   <td>425</td>
   <td>22076</td>
   <td>3534</td>
  </tr>
 </tbody>
</table>
<br>
<br><b>FindBugs Audit</b>
<br>
 FindBugs is a program to find bugs in Java programs. It looks for instances of "bug patterns". The metric itself is the density. It refers to the Defects per Thousand lines of non-commenting source statements. But it's not only the single metric that counts in general it's best to analyze any single report for relevance.
<br>
<br>
<table>
 <tbody>
  <tr>
   <th>Lines of code</th>
   <th>Classes</th>
   <th>Packages</th>
  </tr>
  <tr>
   <td>67263</td>
   <td>1310</td>
   <td>69</td>
  </tr>
 </tbody>
</table>
<br>
<table>
 <tbody>
  <tr>
   <th>Metric</th>
   <th>Total</th>
   <th>Density</th>
  </tr>
  <tr>
   <td>High Priority Warnings</td>
   <td>14</td>
   <td>0.21</td>
  </tr>
  <tr>
   <td>Medium Priority Warnings</td>
   <td>107</td>
   <td>1.59</td>
  </tr>
  <tr>
   <td>Low Priority Warnings</td>
   <td>0</td>
   <td>0</td>
  </tr>
  <tr>
   <td>Total Warnings</td>
   <td>121</td>
   <td>1.80</td>
  </tr>
 </tbody>
</table>
<br>
 Compared with our own projects and even other open source projects I took a look at, this is not worth talking about. It's a clear sign, that the project has it's own FindBugs setup and they are monitoring what they are doing. 
<br>
<br><b>Simian Audit</b>
<br>
 Up to now, this was not that bad. But here comes the magic. Let's look at the sources with Simian. It's a similarity analyser and identifies duplication in Java code. This analysis started finding duplicate lines &gt;=9. And now we have some findings. We have 5105 duplicated code lines aoverall. This is roughly 10% of the code. A bigger thing. The biggest codeblock covers 125+53 (occures twice) lines (SelectItemsIterator.java in both the RI and API). But you find more examples (WriteBehindStateWriter.java and ViewHandlerImpl.java). This is typically the point, where I point out, that OO concepts could have been valued more. Generally there are a couple of places where you find the same code blocks within the API and the RI. That's not transparent to me. I would have expected to have them either in the RI or even the API. For a detailled analysis I am too far away from the project. Maybe, someone will look over it and I will keep you updated on this.
<br>
<br><b>Dependometer Audit</b>
<br>
 The biggest thing in this quality check is the architecture. There is a small tool called <a href="http://source.valtech.com/display/dpm/Dependometer" target="_blank">dependometer</a> out there. It performs a static analysis of physical dependencies within a software system. 
<br>
 WARNING: What I did is not easy to 
<br>
 a) understand and 
<br>
 b) interpret
<br>
 Normally we have an application architecture at hand if we do this with one of our own projects. For the Mojarra I only have very basic information at hand from which I tried to "refactor" the architectural slices. This can be wrong and is in general a bit too high level. But it works and at last points you into the direction the complete project is heading. Therefore it is worth taking a detailled look at.
<br>
<br>
 I configured the tool to use three layers (API , API and external). Both API and RI have one corresponding subsystem only. The external layer covers all external dependencys (apache, jetty, goovy, etc) as separate subsystems. The RI layer depends on the API layer. Both are allowed to use the external layer. The API itself only uses java.* and javax.* subsystems. The RI is free to use all subsystems from the external layer.
<br>
 In terms of layers and layer violation everything looks fine at this level. But if you look at the gathered project metrics you have some very alarming findings.
<br>
 The average component dependency has a value of 167.91. This is huge! It means, that on an average a component depends directly and indirectly (transitively) upon 166,91 other components +1 for itself. It should not exceed 20! There are some other mectics outside of the theoretical threshold. (AverageNumberOfAssertionsPerProjectInternalClass, NumberOfProjectExternalPackages, PercentageOfPackagesWithRcNotLessThanOne) but the most prominent topic is the complexity and interdependence between any kind of component, staring with types, compilation-units and packages. To make this concrete, there are software cycles in Mojarra and they are bad. The tool itself generally counts cycles up to 100. This was by far excceded. Even setting this to 1000 still does not list every single cycle it it. 
<br>
<br>
 An example:
<br>
<pre>javax/faces/event/ActionEvent.java =&gt; javax/faces/event/ActionListener.java &lt;type&gt; (uses) ActionEvent =&gt; ActionListener javax/faces/event/ActionEvent.java =&gt; javax/faces/event/FacesEvent.java &lt;type&gt; (extends) ActionEvent =&gt; FacesEvent javax/faces/event/ActionEvent.java =&gt; javax/faces/event/FacesListener.java &lt;type&gt; (uses) ActionEvent =&gt; FacesListener </pre>Both classes depend on and know each other. The ActionListener knows the ActionEvent, which is not the problem at all. But if you now look at the ActionEvent you can see, that it also knows the ActionListener. If you change on of them, you could get into trouble with the other one. The problem occures with the following method:
<br>
<pre>public boolean isAppropriateListener(FacesListener listener) \{ return (listener instanceof ActionListener); \} </pre>It checks if the listener is an instance of an ActionListener and returns true or false. To solve this kind of dependency, you normaly would have a utility class which does that for you. This should of course be in a common package.
<br>
 This is the basic event handling mechanism used within the source. You find this a couple of times. But there are others. At the moment I am not able to talk about the complete number of existing cycles and the first 1000 only appreaded within the API. Therefore I am missing any cycle information about the RI at all.
<br>
<br>
 Without having looked at any single cycle (yes, that is exactly what to do) I am not able to tell you a bottom line. If this would be one of our projects, I would know now where to spend the next few weeks ;) What is clear to me, that this makes life harder than it should be. 
<br>
<br><b>Other findings</b>
<br>
 Beside the automated checks, it's always a good idea to talk about manual reviews. This is what counts. I did not do any explicitly but found some things on my way through the automated findings.
<br>
 - There are some tools and util packages around. I prefer to have a more consistent naming around. It's likely to see different things happening in any of them. 
<br>
 - There is a com/sun/faces/config package in both the RI and the tools project. This makes automated architecture checks with the dependometer a bit tricky. If I ever get the chance again to do this :)
<br>
 - You can find some jpg files in the source tree. I am not shure, if this is the right thing to do. Even if it is used in the package.html. I would prefer to see it elsewhere. (Some of them look, like they are done with TCC? ;) You are still using it? )
<br>
 - There are some generated classes around. I did not look at the concrete mechanism, but looking at the final packaging, it seems as if all the jsf-api\build\generate\javax\faces\component things go to the jsf-api.jar and the compiled files get copied during the build. This is also not very helpfull for automated checks :)
<br>
<br><b>Bottom line</b>
<br>
 I have seen worser projects. The general metrics are ok. And Mojarra is a good example that the metrics you monitor are the ones which are not eye-catching. I am wondering about the average component dependency and the cycles. I don't know if and how this influences the project but I bet, it does. There is lot of space for improvements. 
<br>
 But this also shows, how much impact the software quality has. JSF is running in a couple of places and nobody complained about it. But if you see faster bugfixing and shorter releases in the future they probably cleaned up some things.
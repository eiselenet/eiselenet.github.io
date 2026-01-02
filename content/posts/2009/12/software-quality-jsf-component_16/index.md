---
title: "Software Quality: JSF Component Libraries - Checkstyle Results"
date: 2009-12-16 06:40:00 +0000
layout: post
tags: ["components", "software quality", "checkstyle", "jsf"]
slug: "software-quality-jsf-component_16"

url: /2009/12/software-quality-jsf-component_16.html
---

This is part V of the software quality analysis series about common jsf component libraries. After a more detailed look at <a href="http://www.eisele.net/blog/2009/12/software-quality-jsf-component_14.html">possible bugs (discovered by findbugs)</a> we will take a deeper look at how the projects comply with coding conventions.
<br>
 The analysis was done with <a href="" target="_blank">Checkstyle 5.0</a>. Checkstyle is a development tool to help programmers write Java code that adheres to a coding standard. It automates the process of checking Java code to spare humans of this boring (but important) task.
<br>
<br>
 A note as preface: Checkstyle is highly configurable and can be made to support almost any coding standard. I did NOT look at any project settings. Checkstyle was configured to use the <a href="http://checkstyle.sourceforge.net/checks.html" target="_blank">Standard Checks</a> which do cover the Sun coding conventions.
<br>
<br>
<br><b>PrimeFaces</b>
<br>
 With the small codebase, PrimeFaces has the smallest ammount of findings in general. 18762 places in 334 files. Only 61 of them are considered as an error. This makes 0,18 problems per file. 
<br>
 The 61 errors occure within two categories (EmptyBlockCheck, IllegalCatchCheck). Looking randomly at the details for does not reveal any real issue. 
<br>
<br>
 The classes with the highest number of findings can be found among the generated files (jsf-plugin). The handcrafted ones are:
<br>
 - org.primefaces.component.datatable.DataTableRenderer.java (265)
<br>
 - org.primefaces.component.calendar.CalendarRenderer.java (217)
<br>
 - org.primefaces.component.chart.UIChart.java (181)
<br>
<br>
<br><b>RichFaces</b>
<br>
 The next biggest codebase is provided by the RichFaces. 713 files with 38.587 findings. 552 of them considered as an error. An average of 0,77 problems per file. This is the worst result of the three. The errors separate into three categories (EmptyBlockCheck, IllegalCatchCheck, IllegalThrowsCheck). Some spot tests on the results did not reveal any bigger problems, which is not surprising at all because findbugs would already have shown them. 
<br>
<br>
 The classes with the highest number of findings are located in the ajax4jsf package.
<br>
 - org.ajax4jsf.org.w3c.tidy.TidyUtils.java (632)
<br>
 - org.ajax4jsf.renderkit.RendererUtils.java (593)
<br>
 - org.ajax4jsf.xml.serializer.ToStream.java (574)
<br>
<br>
 The hotspots from the RichFaces package:
<br>
 - java.org.richfaces.model.StackingTreeModel.java (207)
<br>
 - org.richfaces.json.JSONObject.java (189)
<br>
 - org.richfaces.json.JSContentHandler.java (159)
<br>
<br>
<br><b>ICEFaces</b>
<br>
 The biggest codebase is provided by ICEFaces. 877 files with a surprising low number of findings. "only" 22.959 with a total of 287 errors. That makes an average of 0,3 problems per file. 
<br>
 The errors separate into the already known three categories (EmptyBlockCheck, IllegalCatchCheck, IllegalThrowsCheck). 
<br>
<br>
 The hotspots from the ICEFaces package:
<br>
 - com\icesoft\faces\component\inputfile\InputFile.java (387)
<br>
 - com\icesoft\faces\component\selectinputdate\SelectInputDateRenderer.java (378)
<br>
 - com\icesoft\faces\component\paneltabset\PanelTabSet.java (357)
<br>
<br><b>Conclusion:</b>
<br>
 With an average of 0,18 problems per file, this round goes to PrimeFaces. Even if the total number of warnings is quite high. Followed by ICEFaces and RichFaces. Even if the last one is the only project having codechecks in place. Somehow surprising results. The hot spots can be found within the components. This is true for every library except for RichFaces. What is also true is, that the most complex components are more likely the ones with the biggest problems. 
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/jsfcheckstyle-776748.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="320" src="http://www.eisele.net/blog/uploaded_images/jsfcheckstyle-776746.png" width="320"></a>
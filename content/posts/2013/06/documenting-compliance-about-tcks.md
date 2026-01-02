---
title: "Documenting Compliance - About TCKs, Specifications and Testing"
date: 2013-06-17 09:46:00 +0000
layout: post
tags: ["jboss-test-audit", "java", "TCK", "jcp"]
slug: "documenting-compliance-about-tcks"
link: "2013/06/documenting-compliance-about-tcks.html"
url: /2013/06/documenting-compliance-about-tcks.html
---

Working with software specifications is hard. No matter in which exact filed; you end up with the big question: Does everything ever specified is implemented and tested? Back in the days of waterfall driven methodologies this has been an issue and even today at the time of writing, agility and user-stories still don't guarantee you the perfect fit. Many of today's agile approaches combine well with Test Driven Development or even Behavior Driven Development concepts to turn the issue upside down. Instead of asking "Does my code cover every single sentence of written specification?" those simply assume that writing the tests first is a valid way of having the needed coverage. The down-side here is the lack of documentation which easily can happen. Additionally you never find a suitable document workflow to re-factor tests to the one single document. What might work for individual solutions and projects comes to an end if you look at stuff like "Technology Compatibility Kits" (TCK) which by nature are more or less gathered from any kind of document based written specification.
<br>
<br><b>TCKs for the Java platforms</b>
<br>
 Diving into that kind of topics always is a good candidate to polarize the development community. Especially because documentation is still a topic which tends to be forgotten or delayed completely. To me documentation is key on may levels. On a framework level it assures that your users don't struggle and you lay a good ground for quick adoption. To me the Arquillian project and team did an amazing job in their first years. Even on a project level this makes sense to quickly swap new team members in and out without losing knowledge. But there is another area which not simply benefits from it but has a strong relation to documentation: The Java TCKs. All Java Platforms define Java Specification Requests (JSRs) as the point for language improvements.&nbsp;A Technology Compatibility Kit (TCK) is a suite of tests that at least nominally checks a particular alleged implementation of a Java Specification Request (JSR) for compliance. Given the fact, that most specifications exist in some Office like documents and are pushed around as PDFs for review and comments it is nearly impossible to say weather a TCK has a defined coverage of the original specification at all. This at best is scary. Most of the time it is annoying because Reference Implementations (RIs) simply forget to cover parts of the spec and the user has to handle the resulting bugs or behaviors in specific ways. If that is possible at all.
<br>
 Just a short note here regarding the availability of TCKs. Most of them aren't available as of today but subject to terms of license and financial agreements. Hopefully this is going to change with the upcoming changes to the Java Community Process.
<br>
<br><b>Some JBoss Goddess to cure documentation</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="tck-coverage.PNG" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="252" src="tck-coverage.PNG" width="320"></a>
</div> But some bright minds came up with a solution. It probably isn't a big surprise that a great effort came out of a couple of RedHats. A small project which initially was created as part of the hibernate-validator project which is the RI for BeanValidation is here to cure the problems. The unknown and itself mostly undocumented <a href="https://github.com/jboss/jboss-test-audit" target="_blank">jboss-test-audit</a> project calls itself "Utility classes for TCK Test Coverage Report". This perfectly nails it. It is a very lightweight but still powerful little addition to any RI which post-processes sources for special annotations to gather a coverage report for any project which has the goal of implementing a specification. It is licensed under the&nbsp;Apache License, Version 2.0 and you only need some very few steps to get this up an running against your own setup.
<br>
 It all begins with the specification. This is a xml document which defines the different sections and required assertions.
<br>
<pre class="brush:xml">&lt;specification&gt; &lt;section id="1" title="Chapter 1 - Introduction"/&gt; &lt;section id ="2" title="Chapter 2 - What's new"&gt; &lt;assertion id="a"&gt; &lt;text&gt;A simple sample test&lt;/text&gt; &lt;/assertion&gt;</pre>
<pre class="brush:xml"> &lt;/section&gt;</pre>
<pre class="brush:xml">&lt;/specification&gt; </pre> This document is the base line for your tests. You now need to go ahead and equip all your tests with relevant section and assertion information. This might look like the following: 
<br>
<pre class="brush:java">SpecVersion(spec = "spectests", version = "1.0.0") public class AppTest \{ @Test @SpecAssertion(section = "2", id = "a") public void simpleTestForAssertion() \{ App app = new App(); assertEquals(app.sayHello("Markus"), "Hello Markus"); \} </pre> Combined with a bit of&nbsp;maven magic (maven-processor-plugin) all the annotations are parsed and a nice report is generated about the overall coverage.
<br>
 If you want to have a look at the complete bootstrap example find it on <a href="https://github.com/myfear/spec-test" target="_blank">github.com/myfear</a>.
<br>
<br><b>The Hard Parts</b>
<br>
 This obviously is a no-brainer. To add some annotations to your tests will not be the hardest thing you ever did. What is really hard is to convert your documentation into that fancy audit xml format. There are plenty of ways to do this. Given the fact, that most of the companies leading a JSR have some kind of hard-core document management in place should make this a once in a lifetime thing to implement. If you're working with Microsoft Word you could also use the available <a href="https://github.com/jboss/jboss-test-audit/blob/master/api/src/main/resources/tck-audit.xsd" target="_blank">xml schema</a> to write well formed documents with it (which is a pain! Don't do it!). 
<br>
<br><b>Plenty of Ideas</b>
<br>
 The little utility classes work comparably well. But there is still plenty of room for improvements. It might be a valid idea to have some supportive information here like issue-numbers or other references. I also would like to be able to use asciidoc in the documentation :) But I'm not complaining here because I am not going to change it myself. But if anybody is interested, the complete thing is on <a href="https://github.com/jboss/jboss-test-audit" target="_blank">github.com</a> and I believe those guys know how community works and accept contributions.
<br>
<br><b>Future Wishes for the JCP</b>
<br>
 Given that simple and easy approach it would be a good thing to foster adoption along with JSRs. So if you like it approach the EC member you trust and make him/her aware of this and put it as an idea on their list.
---
title: "Enterprise Java - a firmer grasp of the fundamentals"
date: 2009-08-14 05:12:00 +0000
layout: post
tags: ["Java EE", "basics", "java", "Java EE 6"]
slug: "2009-08-14-enterprise-java-firmer-grasp-of"
url: /2009/08/enterprise-java-firmer-grasp-of.html
---

This blog is about Enterprise Java. The word "Enterprise" mainly refers to the fact, that it need some more that just plain
<br>
 Java to run applications that fulfill enterprise requirements. Beside the fact, that most of them were tried to be adressed
<br>
 by the Java standard with a nearly equal name (JSR 316) there is still lot to learn about this topic beside the basics.
<br>
 Here are some hints and best practices for beginners wanting to gain a firmer grasp of the fundamentals.
<br>
 These tips assume, that you already knwo about OOP and Java in general.
<br>
<br><span style="font-weight:bold;">1) Read, read, read, read and understand</span>
<br>
 The Enterprise Java Specification is incredibly thorough and has truly helpful comments following each included standard.
<br>
 Before asking questions or trying to figure out an issue on your own, save some time and just head straight to the 
<br>
 documentation. Another good place to look for answers and get a deeper understanding about the collaboration of the 
<br>
 standards is the Duke's Bank Sample application. Also known as the JEE Tutorial. Besid this, it is always a good idea
<br>
 to have a look at the documentation of the application server of your choice. 
<br>
<br>
 - JSR 313 Documentation
<br>
 - Duke's Bank Tutoral
<br>
 - Certified Java Application Servers
<br>
<br><span style="font-weight:bold;">2) Get used to exceptions and learn to read them</span>
<br>
 You’ll find bugs in your code that you might not have spotted earlier, as not all bugs keep the application from working. 
<br>
 There are different levels of strictness in the reporting that you can use. Depending on the framework, application server
<br>
 or JDK you are using, you have to configure error reporting the right way. 
<br>
<br>
 - JDK Logging
<br>
 - Commons Logging
<br>
 - Log4j
<br>
<br><span style="font-weight:bold;">3) Use an enterprise IDE</span>
<br>
 IDE’s (Integrated Development Environments) are helpful tools for any developer. 
<br>
 While they’re not for everyone, an IDE definitely has its place. IDE’s provide tools like
<br>
<br>
 * syntax highlighting
<br>
 * code completion
<br>
 * error and deprocation warnings
<br>
 * refactoring (reworking)
<br>
 * appserver integration.
<br>
<br>
 And many other features. There are plenty of great IDEs out there that support Enterprise Java. Have a look and take
<br>
 your choice:
<br>
<br>
 - Eclipse IDE
<br>
 - Netbeans
<br>
 - IntelliJ
<br>
 - OEPE
<br>
<br><span style="font-weight:bold;">4) Try some frameworks</span>
<br>
 Starting with JEE 5 the specification got much easier to use. Anyway, there are still some concepts that feel to 
<br>
 overengineered. Some frameworks try to fight this and reach the goal of providing enterprise grade applications on
<br>
 a different track. Beeing a skilled developer, you should have seen some of them. Take your time and experiment with them.
<br>
<br>
 - Springframework
<br>
 - JBoss Seam
<br>
<br><span style="font-weight:bold;">5) Try different O/R Mappers</span>
<br>
 JEE 5 introduced the Java persistence API (JPA). Beside the reference implementation, that ships with the specification
<br>
 there are plenty other, very good and stable O/R mappers available out there. Most of them have a moving history and
<br>
 come with advantages or disadvantages depending on your needs. To use the right O/R mapper, it is worth seeing some.
<br>
<br>
 - Hibernate
<br>
 - EclipseLink
<br>
 - OpenJPA
<br>
<br><span style="font-weight:bold;">6) Don’t Repeat Yourself (DRY)</span>
<br>
 DRY stands for Don’t Repeat Yourself, and it’s a valuable programming concept, no matter what the language. 
<br>
 DRY programming, as the name implies, is ensuring that you don’t write redundant code. 
<br>
<br><span style="font-weight:bold;">7) Think about readable code</span>
<br>
 If you don’t use indentations and white space in your code, the result looks may look like a painting. 
<br>
 Ensure that your code is readable and easy to search because you’ll most definitely be making changes in the future. 
<br>
 IDEs and advanced text editors can add indentation automatically. Take your time to adjust the default settings to 
<br>
 meet your needs. Have a look at available code-templates and best practices out in the web or other projects.
<br>
<br><span style="font-weight:bold;">8) Think in tiers</span>
<br>
 Maintenability is one of the trickiest parts in JEE development. There are plenty of options for architecting your 
<br>
 software. The first paradigma is about tiers. Separete the client from the logic and the database access. spend some
<br>
 time reading about tiers and how to decouple them. 
<br>
<br><span style="font-weight:bold;">9) Be aware of dependencies</span>
<br>
 Even if your follow tip 7, you still have to think about dependencies. You can have a couple of dependencies not only
<br>
 inside your code and tiers but also with your libraries and infrastructure components. Make shure, you are using the
<br>
 right and only the needed dependendencies wherever they may appear. Handle them on different levels (Architecture, 
<br>
 Development, Build and Depolyment)
<br>
<br><span style="font-weight:bold;">10) Stick to naming conventions</span>
<br>
 Naming this isn’t just for your own good. There’s nothing worse than trying to find your way through some other 
<br>
 programmer’s nonsensical naming conventions. Help yourself and others by using names that make sense for your 
<br>
 classes, variables and declarations. It's worth taking a look at the basic Sun naming conventions. There are plenty
<br>
 of different project or customer sets out there, too. Respect them and don't think of them as unneeded. One aspect
<br>
 of JEE programming is about teams. Most likely you will not be the only one working on a project. Sticking to naming
<br>
 conventions makes live much easier for all involved team members.
<br>
<br><span style="font-weight:bold;">11) Comment your code</span>
<br>
 Aside from plain formatting, you’ll also want to use inline comments and JavaDoc to
<br>
 annotate your code. You’ll thank yourself later when you’re needing to go back and find something in the code, 
<br>
 or if you just can’t remember what a certain function did. It’s also useful for anyone else who needs to look 
<br>
 over your code. Remember, that your most likely will not be the only one writing code in an enterprise grade project.
<br>
<br><span style="font-weight:bold;">12) Decide on the right plattform to start learning with</span>
<br>
 There are some application servers out there for free. Well known are Sun's Glassfish, Apache's Geronimo or even Redhat's
<br>
 JBoss. Beside this, you could always decide to start over with a free for development edition of one of the major commercial
<br>
 vendors, like Oracle's Weblogic, IBM's Websphere or SAP's AS Java. No matter, which version you start over. 
<br>
 This will always be your home to come back to. Therefore it could be 
<br>
 worth thinking about your future needs. What plattform is your company using? Which area of interest drives my developments?
<br>
<br><span style="font-weight:bold;">13) Never, Ever Trust Users</span>
<br>
 If your application has places for user input, you should always assume that they’re going to try to input naughty code. 
<br>
 I am not implying that users are bad people. It’s just a good mindset. A great way to keep your application 
<br>
 hacker-free is to always initialize your variables and validate input on any way to safeguard your site from attacks 
<br>
 (e.g. XSS, SQL Injection).
<br>
<br>
<br><span style="font-weight:bold;">14) Ask for Help</span>
<br>
 It’s only human nature to want to hide the fact that we don’t know much about a certain topic. 
<br>
 Nobody likes being a rooky! But how are we going to learn without asking? Feel free to use forums, StackOverflow or
<br>
 event the vendors support base to ask more seasoned JEE developers questions. 
<br>
<br>
<br>
 Have any ideas and tips of your own? I’m sure you do! Comments welcome!
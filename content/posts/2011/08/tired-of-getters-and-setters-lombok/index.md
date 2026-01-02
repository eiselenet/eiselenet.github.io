---
title: "Tired of Getters and Setters? Lombok them!"
date: 2011-08-22 08:46:00 +0000
layout: post
tags: ["java", "getter", "Lombok", "setter", "jsf"]
slug: "tired-of-getters-and-setters-lombok"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2011/08/tired-of-getters-and-setters-lombok.html
---

I love Java. There is a lot of amazing stuff being build with it every minute and I really love being part of that community. But, to be honest, there is one single thing, that I hate. That is the <a href="http://download.oracle.com/javase/tutorial/javabeans/whatis/index.html" target="_blank">JavaBeans™ architecture</a>. 
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG-20110822-00006.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="240" src="IMG-20110822-00006.jpg" width="320"></a>
</div> Not completely but the basic requirements to have a nullary constructor and property access using getter and setter methods. All this is stupid overhead, hard to maintain and change. But there is a little helper out there, that made my life more easier. It's <a href="" target="_blank">project Lombok</a>. Here is a very short overview about using Lombok with ManagedBeans.
<br><b><br>
 Getting Started</b>
<br>
 Grep your favorite IDE (Eclipse/NetBeans) and start your new Maven based Java EE 6 web-project. Add the Lombok dependency to your pom.xml:
<br><code> &lt;dependency&gt;<br>
  &lt;groupId&gt;org.projectlombok&lt;/groupId&gt;<br>
  &lt;artifactId&gt;lombok&lt;/artifactId&gt;<br>
  &lt;version&gt;0.10.0&lt;/version&gt;<br>
  &lt;scope&gt;compile&lt;/scope&gt;<br>
  &lt;/dependency&gt;<br></code> And you are done. If you are not using Maven, get your copy of the lombok.jar from the <a href="http://projectlombok.org/mavenrepo/index.html">project's website</a>. That's all. Your done. 
<br><b><br>
 Coding the ManagedBean with Lombok</b>
<br>
 Create a new class named "Customer" and add some String fields: <code> public class Customer \{<br>
  private String name;<br>
  private String sure_name;<br>
  private String nickname;<br>
  \}<br></code> But what happens if you try to use this one as @ApplicationScoped @ManagedBean? For sure your JSF runtime will complain about missing properties and constructors. But instead of adding all those boilerplate code you simply add @Data as an additional annotation to your class which should look like this now: <code> @ManagedBean<br>
  @ApplicationScoped<br>
  @Data<br>
  public class Customer \{<br>
  private String name;<br>
  private String surename;<br>
  private String nickname;<br>
  \}<br></code> Now get a simple JSF frontend and add some: <code> &lt;h:inputText id="name" value="#\{customer.name\}" /&gt; </code> for every field in your bean to it and you are done. Everything works. And nobody is complaining about missing properties anymore!
<br>
<br><b>What happened?</b>
<br>
 The offered Lombok annotation @Data simply tells your IDE to generate all the boilerplate code for you silently. And this is done during compile time, so you usually don't have to care about lombok being on your runtime path at all. There are some exceptions to this rule with other Lombok features but it's true for this simple example. <a href="http://projectlombok.org/features/Data.html" target="_blank">@Data</a> especially is a convenient shortcut annotation that bundles the features of <a href="http://projectlombok.org/features/ToString.html" target="_blank">@ToString</a>, <a href="http://projectlombok.org/features/EqualsAndHashCode.html" target="_blank">@EqualsAndHashCode</a>, <a href="http://projectlombok.org/features/GetterSetter.html" target="_blank">@Getter / @Setter</a> and @RequiredArgsConstructor together: In other words, @Data generates all the boilerplate that is normally associated with simple POJOs (Plain Old Java Objects) and beans: getters for all fields, setters for all non-final fields, and appropriate toString, equals and hashCode implementations that involve the fields of the class, and a constructor that initializes all final fields, as well as all non-final fields with no initializer that have been marked with @NonNull or @NotNull, in order to ensure the field is never null. If you look at the generated source you see that the compiled file is there and contains all your well known boilerplate:
<br><code>&gt;javap Customer<br>
  Compiled from "Customer.java"<br>
  public class net.eisele.lombokdemo.Customer \{<br>
  public java.lang.String process();<br>
  public net.eisele.lombok_jsf.Customer();<br>
  public java.lang.String getName();<br>
  public java.lang.String getSurename();<br>
  public java.lang.String getNickname();<br>
  public void setName(java.lang.String);<br>
  public void setSurename(java.lang.String);<br>
  public void setNickname(java.lang.String);<br>
  public boolean equals(java.lang.Object);<br>
  public boolean canEqual(java.lang.Object);<br>
  public int hashCode();<br>
  public java.lang.String toString();<br>
  \}<br></code>
<br><b>That's all?</b>
<br>
 Not really :) There are some <a href="http://projectlombok.org/features/index.html" target="_blank">more features Lombok</a> has to offer. e.g. a simple @Log which gives you access to a <code> private static final java.util.logging.Logger log <br></code> field to simply log what's happening. You can also have a @Log4j, @CommonsLog or even a @Slf4j as alternatives. If you are looking for all the goddess, look at the project's <a href="http://projectlombok.org/features/index.html" target="_blank">feature overview page</a>. 
<br>
<br><b>Risks?</b>
<br>
 One could argue that having all those fancy stuff done by a compiler could lead to unexpected results and probably introduces errors nobody likes. Right. But even if you don't like all those automated and transparent generation, you could simply <a href="http://projectlombok.org/features/delombok.html" target="_blank">Delombok</a> your source files. This would simply replace all lombok annotations with the generated code and gives your access to a complete codebase. So it's a calculable risk using Lombok and I must admit: I love it.
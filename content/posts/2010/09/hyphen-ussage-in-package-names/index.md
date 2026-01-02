---
title: "Hyphen usage in package names"
date: 2010-09-06 09:06:00 +0000
layout: post
tags: ["jls", "names", "package", "dash", "hyphen"]
slug: "hyphen-ussage-in-package-names"

url: /2010/09/hyphen-ussage-in-package-names.html
---

As always, the simple things in live make everything complicated. A co-worker came up with this and I would like to answer the question and point you to some additional links.
<br>
<blockquote>
 Do we have a prefered way about our Java package names? Do we use the short:
 <br>
  - de.xxxx.* or the long form
 <br>
  - com.xxxx-xxxx.* ?
 <br>
</blockquote>
<br><b>The technical answer</b>
<br>
 Trivial? Not necessarily. The actuall question is hidden. If you simply look at the semantics you would tend to choose the "short form" (the de.xxx). But let's look at the syntactic first.
<br>
 The true question is: Does the Java Language Specification (jls) allows hyphen (dashes) in package names? A quick look into <a href="http://java.sun.com/docs/books/jls/third_edition/html/packages.html#7.7">7.7 Unique Package Names</a> tells us the following about package names in general
<br>
<blockquote>
 You form a unique package name by first having (or belonging to an organization that has) an Internet domain name, such as sun.com. You then reverse this name, component by component, to obtain, in this example, com.sun, and use this as a prefix for your package names, using a convention developed within your organization to further administer package names.
 <br>
  [...\}
 <br>
  If the domain name contains a hyphen, or any other special character not allowed in an identifier (§3.8), convert it into an underscore. 
 <br>
  (Source: <a href="http://java.sun.com/docs/books/jls/third_edition/html/packages.html#7.7">jls, 3rd edition</a>)
 <br>
</blockquote>Now you have to look at <a href="http://java.sun.com/docs/books/jls/second_edition/html/lexical.doc.html#40625">3.8 Identifiers</a> to find out about "special characters". 
<br>
<blockquote>
 An identifier is an unlimited-length sequence of Java letters and Java digits, the first of which must be a Java letter.
 <br>
  [...]
 <br>
  The Java letters include uppercase and lowercase ASCII Latin letters A-Z (\u0041-\u005a), and a-z (\u0061-\u007a), and, for historical reasons, the ASCII underscore (_, or \u005f) and dollar sign ($, or \u0024). 
 <br>
</blockquote>You are right, the simple answer is: You are NOT allowed to use the hypen in package names. If you have a unique package name build from a domainname containing a hyphen you have to translate it to the underscore.
<br>
<br>
 For the above example this will look like:
<br>
 - com.xxxx<b>_</b>xxxx.*
<br>
<br><b>The developer answer</b>
<br>
 Reading all this stuff, this somehow feels outdated. If you look at the massive amount of registered domain names it is most likely that the hyphen will more and more become a part of our namespace. Even if it technically does not make a big difference if you use a "-" or a "_" there are some more considerations:
<br>
 - You have to hit an additional key "shift" if you want to access the hyphen.
<br>
 - It simply does not correspond with the domain name. 
<br>
 - It looks like a typo.
<br>
 - The _ has to be "translated" to get the real domain name
<br>
<br>
 Related to the concrete question there are some more conciderations:
<br>
 - The de.xxx obviously is shorter
<br>
 - The com.xxx sounds more international
<br>
<br>
 The answer to the question is: Use the de.xxxx! It is shorter and correct. If any of the marketing guys is forcing you to pick the second option: resist! It is longer, makes developing harder and simply does not feel right. At last to me.
<br>
 You have your own thoughts about this. Glad to hear it.
---
title: "Working with Money in Java"
date: 2011-08-18 13:00:00 +0000
layout: post
tags: ["money", "java"]
slug: "working-with-money-in-java"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2011/08/working-with-money-in-java.html
---

I've a new favorite blog. Peter Lawrey is running a blog called "<a href="" target="_blank">Vanilla Java</a>". It's all about "Understanding how Core Java really works". <a href="http://www.dzone.com/user/908375" target="_blank">Peter is a DZone MVB</a> and pushing some posts about speed and size of different objects and technologies in Java. One particular post recently caught my attention and made me think. 
<br>
<table cellpadding="0" cellspacing="0" class="tr-caption-container" style="float: right; margin-left: 1em; text-align: right;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="money.jpg" imageanchor="1" style="clear: right; margin-bottom: 1em; margin-left: auto; margin-right: auto;"><img border="0" height="240" src="money.jpg" width="320"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;">Picture: <a href="http://www.flickr.com/photos/59937401@N07/5930027206" target="_blank">Images_of_Money</a> (<a href="http://creativecommons.org/licenses/by/2.0/deed.en" target="_blank">CC BY 2.0</a>)</td>
  </tr>
 </tbody>
</table> It is "<a href="http://vanillajava.blogspot.com/2011/08/double-your-money-again.html" target="_blank">Double your money again</a>" in which Peter talks about rounding problems while using java.lang.Double and it's primitive type double. This is a field I know a little about and have seen a lot. And I thought I share a bit about my experiences here.
<br>
<br><b>If you are using double you run into trouble</b>
<br>
 Mainly because of the fact, that <a href="http://java.sun.com/docs/books/jls/third_edition/html/typesValues.html#4.2.3" target="_blank">double</a> (Wrapper: <a href="http://download.oracle.com/javase/7/docs/api/java/lang/Double.html">Double</a>) is a double-precision 64-bit IEEE 754 floating point. It's not meant for keeping exact decimal values.
<br>
<br><code> double result = 0.1 + 0.2 - 0.3;<br>
  System.out.println("0.1 + 0.2 - 0.3=" + result);<br>
  =&amp;gt; 0.1 + 0.2 - 0.3=5.551115123125783E-17<br></code>
<br>
 The .toString() representation follows some (not so simple) rules: 
<br>
<blockquote>
 If m is greater than or equal to 10-3 but less than 107, then it is represented as the integer part of m, in decimal form with no leading zeroes, followed by '.' ('\u002E'), followed by one or more decimal digits representing the fractional part of m.
 <br>
  If m is less than 10-3 or greater than or equal to 107, then it is represented in so-called "computerized scientific notation." Let n be the unique integer such that 10n ≤ m &amp;lt; 10n+1; then let a be the mathematically exact quotient of m and 10n so that 1 ≤ a &amp;lt; 10. The magnitude is then represented as the integer part of a, as a single decimal digit, followed by '.' ('\u002E'), followed by decimal digits representing the fractional part of a, followed by the letter 'E' ('\u0045'), followed by a representation of n as a decimal integer, as produced by the method Integer.toString(int).
 <br>
  (Source: <a href="http://download.oracle.com/javase/7/docs/api/index.html?java/lang/Double.html" target="_blank">java.lang.Double</a>)
</blockquote> Following this: you run into trouble with presentation and calculations which you have to handle.
<br>
<br><b>Rounding Tie-breaking</b>
<br>
 Back to Peter's initial post. He is proposing to use a rounding algorithm when working with doubles to prevent undetermined effects. The little methods he shows are nice: But Java already knows about rounding and further on, it already knows about more than one rounding algorithm. Now it's up to you to choose the best one. We have a couple of them at hand (compare <a href="http://en.wikipedia.org/wiki/Rounding#Tie-breaking" target="_blank">wikipedia article</a>). Let's go:
<br>
<br><i>ROUND_CEILING</i>
<br>
 Rounding mode to round towards positive infinity.
<br><i>ROUND_DOWN&nbsp;</i>
<br>
 Rounding mode to round towards zero.
<br><i>ROUND_FLOOR&nbsp;</i>
<br>
 Rounding mode to round towards negative infinity.
<br><i>ROUND_HALF_DOWN&nbsp;</i>
<br>
 Rounding mode to round towards "nearest neighbor" unless both neighbors are equidistant, in which case round down.
<br><i>ROUND_HALF_EVEN&nbsp;</i>
<br>
 Rounding mode to round towards the "nearest neighbor" unless both neighbors are equidistant, in which case, round towards the even neighbor.
<br><i>ROUND_HALF_UP&nbsp;</i>
<br>
 Rounding mode to round towards "nearest neighbor" unless both neighbors are equidistant, in which case round up.
<br><i>ROUND_UP&nbsp;</i>
<br>
 Rounding mode to round away from zero. 
<br>
<br>
 To get the results processed with the so-called Gaussian- , or bankers' rounding all you have to do is to set a scale on your BigDecimal.
<br>
<br><code> double result = 0.1 + 0.2 - 0.3;<br>
 BigDecimal resultRounded = new BigDecimal(result)</code><code>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;.setScale(2, BigDecimal.ROUND_HALF_UP);<br>
  System.out.println("0.1 + 0.2 - 0.3=" +&nbsp;resultRounded);<br>
  &nbsp;=&amp;gt; 0.1 + 0.2 - 0.3=0.00 </code>
<br>
<br>
 There is some conversion involved here. As you can see, I'm converting the double to a BigDecimal. That means, you cannot use it for further calculations. if you need a double you probably can do the following: 
<br><code><br>
 double result = new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); </code>
<br>
<br>
But as Peter states in the comments on his post this is probably not performing very well. 
<br>
<br><b>BigDecimal != Amount</b>
<br>
 Even if you now know how to get rid of the rounding problem you still have a simple number at hand and not an amount. Which means, you have to fix the&nbsp;presentation.&nbsp;What should be simple, isn't. As always you have a couple of approaches to evaluate.
<br>
<br><i>DecimalFormat</i>
<br>
 The preferred way is to use the DecimalFormat class to format decimal numbers into locale-specific strings. This class allows you to control the display of leading and trailing zeros, prefixes and suffixes, grouping (thousands) separators, and the decimal separator. You can grep an instance by using the NumberFormat.getCurrencyInstance(locale):
<br>
<br><code> double result = 0.1 + 0.2 - 0.3;<br>
  BigDecimal result2 = new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP);<br>
  NumberFormat form = NumberFormat.getCurrencyInstance(new Locale("de", "DE"));<br>
  System.out.println("Amount: " + form.format(result2));<br>
  &nbsp;=&amp;gt; Amount: 0,00 €<br></code>
<br><i>Currency</i>
<br>
 If you need more control than you already have with the preferred way, you could think about using the Currency class. Look at <a href="http://download.oracle.com/javase/7/docs/api/java/util/Currency.html" target="_blank">java.util.Currency</a> as it represents a currency identified by it's <a href="http://en.wikipedia.org/wiki/ISO_4217" target="_blank">ISO 4217</a> currency code. Another alternative is to get it with a locale.
<br><code> Currency curr = Currency.getInstance("EUR");<br>
  Currency currLoc = Currency.getInstance(new Locale("de", "DE"));<br>
  System.out.println("currency EUR in en_US: "+curr.getSymbol(new Locale("de", "DE")));<br>
  System.out.println("currency in de_DE for en_US: "+currLoc.getSymbol(new Locale("en", "US")));<br>
  &nbsp;=&amp;gt;currency EUR in en_US: €<br>
  &nbsp;=&amp;gt;currency in de_DE for en_US: EUR<br></code>
<br>
<br><b>Bottom line</b>
<br>
 If you have a chance to, go ahead with the build in functionality. Both the rounding issues as the i18n stuff can be addressed with the set of utilities and classes available. If you tend to have your own Amount class using the custom currency mechanisms be aware of the fact, that you have to place the currency symbol in front or afterwards depending on the locale you are in. So: There is not much magic inside and if you use it the right way, you don't have to fear working with money in Java at all.
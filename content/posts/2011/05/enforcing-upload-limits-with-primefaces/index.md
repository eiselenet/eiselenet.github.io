---
title: "Enforcing Upload Limits with Primefaces 2.2.1"
date: 2011-05-10 11:05:00 +0000
layout: post
tags: ["jsf", "primefaces"]
slug: "enforcing-upload-limits-with-primefaces"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2011/05/enforcing-upload-limits-with-primefaces.html
---

A very short tip I came across lately. If you are looking into enforcing some limits to your upload component:
<br><code>&lt;p:fileUpload fileUploadListener="#\{fileUploadController.handleFileUpload\}" <br>
  description="Allowed Files"/&gt; <br></code>
<br>
 you are most likely able to use allowTypes and sizeLimit attributes. But what about enforcing project wide settings without repeating this in any faces template over and over again?
<br>
 Simple as you might guess: Write your own component. All you have to do is to extend the org.primefaces.component.fileupload.FileUpload component a bit like this:
<br><code><br>
  public class ExcludeListPrimeFileUpload extends FileUpload \{<br>
  public ExcludeListPrimeFileUpload() \{<br>
  super();<br><br>
  // allowed file-types<br>
  setAllowTypes("*.doc;*.docx;*.pdf;*.ppt;*.pptx;*.zip;*.txt");<br>
  //size limit 30 megabyte = 31 457 280 bytes<br>
  setSizeLimit(new Long(31457280));<br>
  \}<br>
  \}<br></code>
<br>
 and register it as a replacement for the org.primefaces.component.FileUpload component-type with your faces-config.xml.
<br><span class="Apple-style-span" style="font-family: monospace;">&lt;component-type&gt;org.primefaces.component.FileUpload&lt;/component-type&gt;</span>
<br><code> &lt;component-class&gt;net.eisele.test.primetest.ExcludeListPrimeFileUpload&lt;/component-class&gt;<br>
  &lt;/component&gt;<br></code>
<br>
 If you use the Browsee Button you now see, that you are only able to select any of the provided file-types:
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="prime_upload.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="224" src="prime_upload.png" width="320"></a>
</div>That was simple and quick. Thanks PrimeFaces ;)
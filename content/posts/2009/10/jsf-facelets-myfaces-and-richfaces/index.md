---
title: "JSF, Facelets, Myfaces and Richfaces Performance Tuning"
date: 2009-10-02 10:18:00 +0000
layout: post
tags: ["richfaces", "performance", "tuning", "jsf", "myfaces"]
slug: "jsf-facelets-myfaces-and-richfaces"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2009/10/jsf-facelets-myfaces-and-richfaces.html
---

I often come across the same issues in projects. One very hot topic is performance.
<br>
 Beside the things you should do to garantee performance during the development process,
<br>
 you are often left alone with tunings for frameworks. Good examples are the widly used JSF
<br>
 frameworks Myfaces from Apache and Richfaces from JBoss.
<br>
<br>
 I tried to sum up some of our performance findings during the last profiling and tuning sessions.
<br>
 Happy to hear your ideas and best practices on that, too.
<br>
<br><b>Myfaces General Performance Tuning</b>
<br>
<br>
 If you use the MyFacesExtensionsFilter you can start optimizing here. 
<br>
 It buffers and parses the response on every request. 
<br>
 You can disable this, and still gain all functionality the ExtensionsFilter is providing, 
<br>
 by doing the following.
<br>
<br>
 If you use myfaces the traditional way, you end up having all resources needed for your components
<br>
 beeing loaded in separate includes. You could optimize this using the following context param:
<br>
<br>
 &lt;context-param&gt;
<br>
 &lt;param-name&gt;org.apache.myfaces.ADD_RESOURCE_CLASS&lt;/param-name&gt;
<br>
 &lt;param-value&gt;org.apache.myfaces.component.html.util.StreamingAddResource&lt;/param-value&gt;
<br>
 &lt;/context-param&gt;
<br>
<br>
<br>
 The only thing to do now is get rid of the &lt;HEAD&gt; tag in your HTML, and instead use Tomahawk's &lt;t:documentHead/&gt; tag. 
<br>
 Of course, your &lt;f:view/&gt; tag has to enclose your &lt;t:documentHead/&gt; tag for this to work. Now 
<br>
 all resources needed by your components get loaded in one single file.
<br>
<br>
<br>
 Think about using an optimized sirialization provider. (Following example is for JBoss)
<br>
<br>
 &lt;context-param&gt;
<br>
 &lt;param-name&gt;org.apache.myfaces.SERIAL_FACTORY&lt;/param-name&gt;
<br>
 &lt;param-value&gt;org.apache.myfaces.JbossSerialFactory&lt;/param-value&gt;
<br>
 &lt;/context-param&gt;
<br>
<br>
<br>
<br><b>Server Side State Performance Tuning</b>
<br>
<br>
 There are a many settings you can enable in your web.xml file to make MyFaces perform well.
<br>
<br>
 JSF components tree state takes big enough memory. In the server-side
<br>
 state saving ( default JSF behavior ) these objects are stored in the
<br>
 session. For a many concurrent user connections every user gets own
<br>
 session object. 
<br>
<br>
 In general serverside state saving is more performant than the client side. You also get lower
<br>
 page sizes and faster loading times with this.
<br>
<br>
 &lt;context-param&gt;
<br>
 &lt;param-name&gt;javax.faces.STATE_SAVING_METHOD&lt;/param-name&gt;
<br>
 &lt;param-value&gt;server&lt;/param-value&gt;
<br>
 &lt;/context-param&gt;
<br>
<br>
 If your performance is ok, but you have memory problems try to switch to the client-side state saving.
<br>
<br>
 &lt;context-param&gt;
<br>
 &lt;param-name&gt;javax.faces.STATE_SAVING_METHOD&lt;/param-name&gt;
<br>
 &lt;param-value&gt;client&lt;/param-value&gt;
<br>
 &lt;/context-param&gt;
<br>
<br>
 If this is not enough, you could disable serverside state compression. Compression always
<br>
 takes time. If you have enought memory, try this one:
<br>
<br>
 &lt;context-param&gt;
<br>
 &lt;param-name&gt;org.apache.myfaces.COMPRESS_STATE_IN_SESSION&lt;/param-name&gt;
<br>
 &lt;param-value&gt;false&lt;/param-value&gt;
<br>
 &lt;/context-param&gt;
<br>
<br>
 Very important, too, is to disable the serialization of state, serialization and deserialization of the component tree is a major performance hit. 
<br>
<br>
 &lt;context-param&gt;
<br>
 &lt;param-name&gt;org.apache.myfaces.SERIALIZE_STATE_IN_SESSION&lt;/param-name&gt;
<br>
 &lt;param-value&gt;false&lt;/param-value&gt;
<br>
 &lt;/context-param&gt;
<br>
<br>
 If you find that memory is a constraining factor, then reducing the number of views stored in the session might help. The setting is controlled by:
<br>
<br>
 &lt;context-param&gt;
<br>
 &lt;param-name&gt;org.apache.myfaces.NUMBER_OF_VIEWS_IN_SESSION&lt;/param-name&gt;
<br>
 &lt;param-value&gt;20&lt;/param-value&gt;
<br>
 &lt;description&gt;
<br>
 Only applicable if state saving method is "server" (= default).
<br>
 Defines the amount (default = 20) of the latest views are stored in session.
<br>
 &lt;/description&gt;
<br>
 &lt;/context-param&gt;
<br>
<br>
<br><b>Sun-RI Performance Tuning</b>
<br>
<br>
 You should increase response buffer (to reduce reallocations at render time)
<br>
<br>
 &lt;context-param&gt;
<br>
 &lt;param-name&gt;com.sun.faces.responseBufferSize&lt;/param-name&gt;
<br>
 &lt;param-value&gt;500000&lt;/param-value&gt;
<br>
 &lt;/context-param&gt;
<br>
<br>
 Think about using an optimized serialization provider. The following example is a JBoss provider (<a href="http://viewvc.jboss.org/cgi-bin/viewvc.cgi/jbossas/trunk/tomcat/src/main/org/jboss/web/jsf/integration/serialization/JBossSerializationProvider.java">code here</a>)
<br>
 )
<br>
<br>
 &lt;context-param&gt;
<br>
 &lt;param-name&gt;com.sun.faces.serializationProvider&lt;/param-name&gt;
<br>
 &lt;param-value&gt;org.jboss.web.jsf.integration.serialization.JBossSerializationProvider&lt;/param-value&gt;
<br>
 &lt;/context-param&gt;
<br>
<br>
<br>
<br><b>Facelets Performance Tuning</b>
<br>
<br>
 If you don't want to store too many state in either the server or the client, another possible 
<br>
 solution is to allow Facelets to build view before request processing instead of state saving.
<br>
 But be warened about some unpredictable side effects. Use web.xml init parameter
<br>
 together with the <f:view transient="true">
 attribute. 
 <br>
 <br>
  &lt;context-param&gt;
 <br>
  &lt;param-name&gt;facelets.BUILD_BEFORE_RESTORE&lt;/param-name&gt;
 <br>
  &lt;param-value&gt;true&lt;/param-value&gt;
 <br>
  &lt;/context-param&gt;
 <br>
 <br>
  The ultimate solution for solving session state memory or performance problems is to create a custom
 <br>
  aceletsViewHandler subclass with special state handling where needed.
 <br>
  The custom handler could , for example, call buildView method instead of real restoreView 
 <br>
  procedure for pages that typically do not need state (e.g. menue, navigation).
 <br>
 <br>
  Facelets library in the "debug" mode stores information about
 <br>
  components and beans up to 5 times for an every user. You should disable this in production mode!
 <br>
 <br>
  &lt;context-param&gt;
 <br>
  &lt;param-name&gt;facelets.DEVELOPMENT&lt;/param-name&gt;
 <br>
  &lt;param-value&gt;false&lt;/param-value&gt;
 <br>
  &lt;/context-param&gt;
 <br>
 <br>
 <br>
  You should increase response buffer (to reduce reallocations at render time)
 <br>
 <br>
  &lt;context-param&gt;
 <br>
  &lt;param-name&gt;com.sun.faces.responseBufferSize&lt;/param-name&gt;
 <br>
  &lt;param-value&gt;500000&lt;/param-value&gt;
 <br>
  &lt;/context-param&gt;
 <br>
 <br>
 <br>
  You should also turn of the facelets refresh trigger in production environments.
 <br>
 <br>
  &lt;context-param&gt;
 <br>
  &lt;param-name&gt;facelets.REFRESH_PERIOD&lt;/param-name&gt;
 <br>
  &lt;param-value&gt;-1&lt;/param-value&gt;
 <br>
  &lt;/context-param&gt;
 <br>
 <br>
 <br>
 <br>
 <b>RichFaces Performance Tuning</b>
 <br>
 <br>
  Most filters use buffering for request processing. According to the
 <br>
  profile information, these buffers took big enough memory in the
 <br>
  application. Therefore you should limit the size for the RichFaces Ajax filter, too.
 <br>
 <br>
  &lt;init-param&gt;
 <br>
  &lt;param-name&gt;maxRequestSize&lt;/param-name&gt;
 <br>
  &lt;param-value&gt;100000&lt;/param-value&gt;
 <br>
  &lt;/init-param&gt;
 <br>
 <br>
  For a production server, it makes sense to reduce the value to a real page
 <br>
  size or even remove that parameter at all.
 <br>
 <br>
  Richfaces comes with some build in parsers to ‘tidy’ all HTML HTTP Responses so that they are valid 
 <br>
  XHTML (thus XML compliant). This is needed as dynamic DOM updates in the browser need correct XML.
 <br>
 <br>
  Of course, parsing HTML incurs a performance overhead.
 <br>
  This can be minimized by setting the forceparser setting to false. In that case only AJAX responses will be ‘tidied’. In the other case all JSF responses are ‘tidied’. 
 <br>
 <br>
  &lt;filter&gt;
 <br>
  &lt;filter-name&gt;richfaces&lt;/filter-name&gt;
 <br>
  &lt;display-name&gt;RichFaces Filter&lt;/display-name&gt;
 <br>
  &lt;filter-class&gt;org.ajax4jsf.Filter&lt;/filter-class&gt;
 <br>
  &lt;init-param&gt;
 <br>
  &lt;param-name&gt;forceparser&lt;/param-name&gt;
 <br>
  &lt;param-value&gt;false&lt;/param-value&gt;
 <br>
  &lt;/init-param&gt;
 <br>
  &lt;/filter&gt;
 <br>
 <br>
 <br>
  TIDY xml filter is DOM-based, thus it requires a lot of memory. It
 <br>
  would be better to use more optimized "NONE", "NEKO" ore "TIDY" should be the second and third best choice. The following example shows that ORDER parameter defines the order in which particular filter types are used for pages code correction. 
 <br>
 <br>
  &lt;context-param&gt;
 <br>
  &lt;param-name&gt;org.ajax4jsf.xmlparser.ORDER&lt;/param-name&gt;
 <br>
  &lt;param-value&gt;NONE,NEKO,TIDY&lt;/param-value&gt;
 <br>
  &lt;/context-param&gt;
 <br>
 <br>
  You can even set sets of pages for which the filters should apply. The following example applies the NEKO filter to all URLs.
 <br>
 <br>
  &lt;context-param&gt;
 <br>
  &lt;param-name&gt;org.ajax4jsf.xmlparser.NEKO&lt;/param-name&gt;
 <br>
  &lt;param-value&gt;.*\..*&lt;/param-value&gt;
 <br>
  &lt;/context-param&gt;
 <br>
 <br>
 <br>
  Before the version 3.1.3, RichFaces loaded styles and script on demand. I.e. files are loaded only if they are required on a particular page. Since RichFaces 3.1.3, it's possible to manage how the RichFaces script and style files are loaded to application. 
 <br>
  Using the web.xml org.richfaces.LoadScriptStrategy setting, you can tell Richfaces to either:
 <br>
 <br>
  * Load ALL script in one file.
 <br>
  * Load NONE scripts (you do it yourself instead - eg. in the manner prescribed by your book).
 <br>
  * Load scripts when needed (the DEFAULT).
 <br>
 <br>
 <br>
  &lt;context-param&gt;
 <br>
  &lt;param-name&gt;org.richfaces.LoadStyleStrategy&lt;/param-name&gt;
 <br>
  &lt;param-value&gt;ALL&lt;/param-value&gt;
 <br>
  &lt;/context-param&gt;
 <br>
 <br>
  &lt;context-param&gt;
 <br>
  &lt;param-name&gt;org.ajax4jsf.COMPRESS_SCRIPT&lt;/param-name&gt;
 <br>
  &lt;param-value&gt;false&lt;/param-value&gt;
 <br>
  &lt;/context-param&gt;
 <br>
 <br>
  If you use LoadScriptStrategy ALL, turn the compression off like it shown in the code snippet above
 <br>
 <br>
 <br>
 <b>Some basic JSF performance principles</b>
 <br>
 <br>
 <ul>
  <li>Never put logic into your getters. They are called multiple times and should only return something already populated by another method. For example if you are chaining drop-downs together use an a4j:support tag on the first one with an action attribute that loads the data which is then retrieved when you reRender the second one.</li>
  <li>Use the ajaxSingle="true" unless you actually want to send the whole form back to the server.</li>
  <li>Don't use a rich component if you only need a normal one. For example don't use rich:dataTable unless you are making use of some of the features that it has over and above h:dataTable.</li>
  <li>Consider using immediate=true attributes on elements where you do not need validation</li>
  <li>Avoid displaying large tables to user. Use pagination</li>
  <li>Do not over complicate EL expressions, code them in Java in backing bean</li>
 </ul>
</f:view>
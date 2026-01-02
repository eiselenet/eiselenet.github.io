---
title: "Binding SSL-Sessions to HttpSessions in GlassFish"
date: 2011-06-06 08:40:00 +0000
layout: post
tags: ["glassfish", "security", "ssl", "session"]
slug: "binding-ssl-sessions-to-httpsessions-in"
link: "2011/06/binding-ssl-sessions-to-httpsessions-in.html"
url: /2011/06/binding-ssl-sessions-to-httpsessions-in.html
---

You might have noticed, that I am working my way through the security principles regarding secure web applications at the moment. The main idea about this is to enable GlassFish to deliver high secure applications. One of the things making my brain hurt a bit is the Session Hijacking attack. It consists of the exploitation of the http session control mechanism, which is normally managed for a session token. 
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG-20110606-00005.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="239" src="IMG-20110606-00005.jpg" width="320"></a>
</div>In our case the JSESSIONID. The Session Hijacking attack compromises the session token by stealing or predicting a valid session token to gain unauthorized access to the applications running on you GlassFish.
<br>
 There are different type of possible attacks. See the <a href="https://www.owasp.org/index.php/Session_hijacking_attack" target="_blank">OWASP page</a> about that for details. If you are going to address this topic you have different options from an implementation perspective. This is what I am going to describe in this blog post. 
<br>
<br><b>SSL is your friend</b>
<br>
 The basic requirement for session hijack prevention is to use https for your applications. This primary assures, that it's not easy to a) sniff the session out of the http stream and prevents simple man-in-the-middle attacks. Running in very high secure environments requires installing ssl certificates to your GlassFish and running all http-listeners in secure mode.
<br>
<br><b>Where to put and how to configure the JSESSIONID?</b>
<br>
 Before you start with further reading, you should be aware that the whole topic is about the Servlet spec and about containers. The spec itself requires the session tracking cookie to be most convenient for any user and defines many ways of storing and transmitting it. This is a behavior that is undesired in high secure environments. So the first thing is to restrict the session tracking cookie to the minimum needed.
<br><code><br>
  &lt;session-config&gt;<br>
  &lt;!-- <br>
  Specifies General Session Timeout in Minutes<br>
  --&gt;<br>
  &lt;session-timeout&gt;15&lt;/session-timeout&gt;<br>
  &lt;cookie-config&gt;<br>
  &lt;!-- <br>
  Specifies whether any session tracking cookies created <br>
  by this web application will be marked as HttpOnly<br>
  --&gt;<br>
  &lt;http-only&gt;true&lt;/http-only&gt;<br>
  &lt;!-- <br>
  Specifies whether any session tracking cookies created <br>
  by this web application will be marked as secure<br>
  --&gt;<br>
  &lt;secure&gt;true&lt;/secure&gt;<br>
  &lt;/cookie-config&gt;<br>
  &lt;!-- <br>
  Specifies whether JSESSIONID is added to the URL<br>
  --&gt;<br>
  &lt;tracking-mode&gt;COOKIE&lt;/tracking-mode&gt; <br>
  &lt;/session-config&gt;<br></code>
<br>
 What is also true is, that the spec would allow for "secure" HttpSession identifiers. In 7.1.2 it states, that 
<br>
<blockquote>
 Secure Sockets Layer, the encryption technology used in the HTTPS protocol, has a
 <br>
  built-in mechanism allowing multiple requests from a client to be unambiguously
 <br>
  identified as being part of a session. A servlet container can easily use this data to
 <br>
  define a session.
 <br>
</blockquote>Do my knowledge GlassFish does not implement this features up to now. So you have to work around this. Let's go:
<br>
<br><b>HttpSession ID and SSL Session ID</b>
<br>
 Even if you are running SSL with the strongest certificates available, don't use URL-Rewriting and have httpOnly and secure enabled, nobody prevents you from man-in-the-browser attacks or client-side attacks. So, there are still some possibilities to gather the Session ID and use it from a different computer. If you are willing to implement some protection here, you are in need of some additional logic in your application which binds the SSL ID to your HttpSession ID.
<br>
<br><i>SessionIdValve</i>
<br>
 The most obvious thing is to simply make both of them equal. The basic idea here is to take the SSL Session ID from the request and implement your own SessionIdValve which instantiates a HttpSession with that ID. Jan Luehe has a<a href="http://blogs.sun.com/jluehe/date/200712"> basic example how to achieve</a> this with a GlassFish v2 on his blog. The only thing to do is to not take the client IP but the coyoReq.getAttribute("javax.servlet.request.ssl_session") and put it as HttpSession back to the request (see <a href="http://forums.java.net/node/676577" target="_blank">this forum discussion</a> for more details). To be honest, I was not able to get this working with GlassFish 3 (<a href="http://www.java.net/forum/topic/glassfish/glassfish/session-tracking-mechanisms-binding-ssl-session-httpsession" target="_blank">see here</a>). Don't worry: I don't like this solution anyway because it's simply not portable enough. You tie your logic very closely to the container you run in and so, you should avoid this approach in general.
<br>
<br><i>Session Attributes</i>
<br>
 What I like a bit more is to use something like a HijackingPreventionFilter. This could be a simple @WebFilter that is mapped to any resource that should be protected 
<br><code><br>
  @WebFilter(dispatcherTypes = \{DispatcherType.REQUEST, DispatcherType.FORWARD,<br>
  DispatcherType.INCLUDE, DispatcherType.ERROR\}, urlPatterns = \{"/*"\})<br></code>
<br>
 On the first request it checks for an existing session and either does 
<br><code><br>
  chain.doFilter(request, response);<br></code>
<br>
 or checks some session attributes against the information in the actual request. The only prerequisite here is, that you have something in place to add the initial information to your newly created session. There are some places you could come up with. The best would probably be your login. Due to security reasons you should always _renew_ the HttpSession after a successful login. Afterwards you could assume that the request is from the client authenticating against your system. Just get the SSL Session ID and set it as HttpSession attribute there:
<br><code><br>
  String cidSize = (String)request.getAttribute("javax.servlet.request.key_size");<br>
  String cid = (String)request.getAttribute("javax.servlet.request.ssl_session");<br>
  ...<br>
  session.setAttribute("CLIENT_SSL_ID", cid);<br></code>
<br>
 You noticed the cidSize attribute? The javax.servlet.request.ssl_session is not an official servlet supported attribute. Grizzly set's it, when the webcontainer asks to set ALL ssl attributes. So when you just ask for "javax.servlet.request.ssl_session", the webcontainer doesn't recognize it as known SSL attribute and nothing happens (Null), but when you first ask for the key size, it's getting recognized by the webcontainer and it asks Grizzly to set all known SSL attributes including the ssl_session.
<br>
 Another good place could be an HttpSession listener. The big problem here still is, that you are programming against container features which prevent your application from being portable.
<br>
<br><i>Custom HTTP Header variables</i>
<br>
 What really resolves the mess is, if you have any networking device or proxy in front of your GlassFish that simply puts the ssl-session-id as a custom header variable to your request. In this case you don't even have to care for it yourself, you simply change the code in your webfilter to check for your request headers.
<br><code><br>
  String cid = httpRequest.getHeader("HEADER_CLIENT_SSL_ID");<br></code>
<br>
 The only drawback here is, that you basically lose the chance to locally run it without the proxy. So you need to put a startup class in place which adds your filter to the configuration if you are in production mode. 
<br>
<br><b>Conclusion: Security is painful</b>
<br>
 The higher your security requirements are, the more painful your development gets. That's the basic message. You don't have a single switch to turn on to secure your application but you have a lot of screws to tighten to get everything right. This post only shows a little bit from the complete story. What I would like to see is that the Servlet EG is taking some action defining more basic security into the spec. 
<br>
 What's also true is, that nobody should runs a high secure GlassFish without any kind of Enterprise Access Management (EAM) solution in place. Those typically address the described issues with their own plugins and tokens. Anyway, there are still some smaller installations out there suffering from the very little capabilities of todays Java EE servers.
<br>
 Comments and suggestions? I would love to read them!
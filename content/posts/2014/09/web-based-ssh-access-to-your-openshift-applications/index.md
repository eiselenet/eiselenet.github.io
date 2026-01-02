---
title: "Web Based SSH Access your OpenShift Applications"
date: 2014-09-02 07:41:00 +0000
layout: post
tags: ["SSH", "Openshift", "Jboss", "KeyBox"]
slug: "web-based-ssh-access-to-your-openshift-applications"

url: /2014/09/web-based-ssh-access-to-your-openshift-applications.html
---

I recently came across <a href="https://github.com/skavanagh/KeyBox-OpenShift" target="_blank">KeyBox</a>. This is a Apache licensed SSH console for applications in an OpenShift Domain. The cool thing is, that it is completely web-based. And by far cooler: The client is completely written in JavaScript (using <a href="https://github.com/chjj/term.js" target="_blank">term.js</a>) connecting to <a href="" target="_blank">JSch</a> (Java implementation of SSH2) running as a web-application on the JBoss Enterprise Web Server (<a href="https://www.openshift.com/developers/tomcat" target="_blank">EWS 2.0</a>).
<br>
 This is a quick and easy way to get hand on your machine, if you can't use a native ssh client. And it is a great tool in your <a href="http://blog.eisele.net/2014/08/everything-developers-need-to-know-about-redhat-xpaas.html" target="_blank">xPaaS</a> developer toolbox.
<br>
<br><b>Prerequisites</b>
<br>
 There's not a hell lot to get started: But you obviously need a <a href="http://blog.eisele.net/2014/08/start-your-xpaas-journey-with-openshift.html" target="_blank">free OpenShift account</a> first. After that,&nbsp;<a href="https://www.openshift.com/developers/rhc-client-tools-install" target="_blank">install the OpenShift client tools</a> (aka rhc). They require Ruby 1.8.7 or higher. If you want to get the most out of it, make sure to install Git for your system, too.
<br>
<br><b>Installing</b>
<br>
 Installing is just a one-liner in the terminal:
<br>
<pre>rhc app create keybox jbossews-2.0 --from-code git://github.com/skavanagh/KeyBox-OpenShift.git </pre> It might take a while, but after the command finished, you can access KeyBox via: 
<br>
<pre>https://keybox-&lt;namespace&gt;.rhcloud.com</pre> All members of the domain can login with their OpenShift account. 
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="keybox-login.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" src="keybox-login.PNG"></a>
</div> Now you can open a SSH session for every application in your domain.&nbsp;KeyBox generates an SSH key pair and associate the public key with the user account for every login.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="keybox-ssh-established.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" src="keybox-ssh-established.PNG" height="136" width="320"></a>
</div>
<br>
<br>
 Make sure to follow Sean Kavanagh on Twitter (<a href="https://twitter.com/spkavanagh6" target="_blank">@spkavanagh6</a>) and star the <a href="https://github.com/skavanagh/KeyBox-OpenShift" target="_blank">KeyBox-OpenShift repository</a> if you like it!
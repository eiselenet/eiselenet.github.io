---
title: "OpenShift Quick Tip: Port Forwarding with v3 and the All-In-One-VM"
date: 2015-10-20 11:31:00 +0000
layout: post
tags: ["QuickTip", "Openshift"]
slug: "openshift-quick-tip-port-forwarding"
link: "2015/10/openshift-quick-tip-port-forwarding.html"
url: /2015/10/openshift-quick-tip-port-forwarding.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;" target="_blank"><img border="0" height="158" src="origin.PNG" width="320"></a>
</div> Just a short tip today, but I was playing around with the <a href="" target="_blank">all-in-one vm from the OpenShift</a> team and wanted to use the <a href="https://docs.openshift.org/latest/dev_guide/port_forwarding.html" target="_blank">port-forwarding feature</a> for a quick check of a running database.&nbsp;You can use the CLI to forward one or more local ports to a pod. This allows you to listen on a given or random port locally, and have data forwarded to and from given ports in the pod.
<br>
<br>
 But whenever I tried to execute:
<br>
<pre class="code"><code>oc port-forward&nbsp;mysql-2-zjx6u&nbsp;3306:3306</code></pre> It looked like it worked until the very first time I tried to use the tunnel:
<br>
<pre>I1020 11:38:54.754799 8356 portforward.go:227] Forwarding from 127.0.0.1:3306 -&gt; 3306 I1020 11:38:54.757299 8356 portforward.go:227] Forwarding from [::1]:3306 -&gt; 3306 I1020 11:39:10.824839 8356 portforward.go:253] Handling connection for 3306 <b>E1020 11:39:10.833340 8356 portforward.go:312] An error occurred forwarding 3306 -&gt; 3306: Error forwarding port 3306 to pod mysql-2-zjx6u_myfear, uid : Unable to do port forwarding: socat not found.</b> </pre> Turns out, that the needed socat package isn't installed on the all-in-one-vm. In order to fix that, you have to ssh into the instance:
<br>
<pre class="code"><code>vagrant ssh</code></pre> And install socat:
<br>
<pre class="code"><code>sudo /bin/yum install socat</code></pre> After that you're able to use the tunnel and forward ports to your OpenShift pod.
<br>
<br><b>Further Information:</b>
<br>
<ul>
 <li>Get started with the&nbsp;<a href="https://github.com/openshift/origin/blob/master/CONTRIBUTING.adoc" target="_blank">Contributors Guide</a></li>
 <li>Fork&nbsp;<a href="https://github.com/openshift/origin" target="_blank">the repository</a>.</li>
 <li>Read the&nbsp;<a href="http://docs.openshift.org/latest/welcome/index.html" target="_blank">Origin 3 documentation</a>.</li>
 <li>Follow <a href="https://twitter.com/openshift" target="_blank">@OpenShift</a> on Twitter</li>
</ul>
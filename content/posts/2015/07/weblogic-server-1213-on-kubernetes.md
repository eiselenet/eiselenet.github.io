---
title: "WebLogic Server 12.1.3 on Kubernetes"
date: 2015-07-29 08:31:00 +0000
layout: post
tags: ["weblogic", "Openshift", "Docker", "fabric8"]
slug: "2015-07-29-weblogic-server-1213-on-kubernetes"
url: /2015/07/weblogic-server-1213-on-kubernetes.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="wls-hello-world-kubernetes.PNG" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="193" src="wls-hello-world-kubernetes.PNG" width="320"></a>
</div> Most of you recall, that I have some little history in Oracle WebLogic server. Coming all the way back from BEA times. I don't want to comment on recent developments or features or standard support, but one thing is for sure: It is out there and having the chance to run it containerized is something many customers will appreciate. Maybe this is the one thing, that will make a real difference in our industry with the ongoing progress in the containerization field. We actually can manage heterogeneous infrastructure components on a common base. If we have the right tools at hand. And this is true for the operations side with OpenShift and of course for all the developers out there, who will appreciate, what Fabric8 can do for them.
<br>
<br><b>License Preface</b>
<br>
 Whatever happens in this blog-post only happens on my local developer laptop. And I strongly believe, that with regards to Oracle technologies this is absolutely covered by the so called <a href="http://www.oracle.com/technetwork/licenses/wls-dev-license-1703567.html" target="_blank">OTN Free Developer License Agreement</a> and the <a href="http://www.oracle.com/technetwork/java/javase/terms/license/index.html" target="_blank">Oracle Binary Code License Agreement for Java SE</a>.
<br>
 I'm dead sure, that a production environment needs a bunch of licenses. But I'm not a specialist. So, don't ask me. If you want to use RHEL 7, please learn about the <a href="https://access.redhat.com/subscription-value" target="_blank">Red Hat Subscriptions</a>.
<br>
<br><b>Ok, WebLogic - Where's Your Image?</b>
<br>
 Not there. I assume for licensing reasons. But, Bruno did a great job in pushing relevant Dockerfiles and scripts to the <a href="" target="_blank">official Oracle GitHub</a> account. So. the first step in running WebLogic on Kubernetes is to actually build a docker image with it. Go,
<br>
<pre class="code"><code> git clone https://github.com/oracle/docker </code></pre> and navigate to the OracleWebLogic folder. In fact, you can delete everything else beside this one. First step is to download the WebLogic ZIP installer and the correct JDK to be used.
<br>
 Go to the <a href="http://www.oracle.com/technetwork/middleware/fusion-middleware/downloads/index.html" target="_blank">Oracle Website</a>, accept the OTN License (if you feel like it) and download the platform independent ZIP installer&nbsp;(wls1213_dev_update2.zip).
<br>
 Now browse to the <a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html" target="_blank">JDK download website</a>, do the same license thing and download the 8u51 JDK as rpm (jdk-8u51-linux-x64.rpm). Place both into the OracleWebLogic\dockerfiles\1.2.1.3 folder. If you're running on a unix like OS yourself, feel free to check back with the <a href="http://docs.oracle.com/middleware/1213/wls/DOCKR/toc.htm" target="_blank">official documentation</a> and use the provided scripts. This didn't work for me on Windows, so you get a step-by-step walk-through. Go and rename the Dockerfile.developer to Dockerfile and delete all the other ones.
<br>
<pre class="code"><code> mv Dockerfile.developer Dockerfile rm Dockerfile.* </code></pre> Now you open the Dockerfile and change a couple of things. Base it on RHEL 7:
<br>
<pre class="code"><code> FROM rhel7 </code></pre> And comment out the other base, that's in there ... And because, we want to run a decently patched and available Java version, we're going to change the environment variable accordingly
<br>
<pre class="code"><code> ENV JAVA_RPM jdk-8u51-linux-x64.rpm </code></pre> Time to build our image. And before you start, let's reuse the fabric8 vagrant installer, that I've been using for the <a href="http://blog.eisele.net/2015/07/running-wildfly-on-openshift-3-with-kubernetes-fabric8-on-windows.html" target="_blank">last two blog-posts already</a>. So, bring your vagrant instance with OpenShift up first. Now it's time to build the WebLogic image. Sit back and relax, because this is going to take a couple of more minutes. Do have some housekeeping to do? This might be the right time!
<br>
<pre class="code"><code> docker build --force-rm=true --tag="vagrant.f8:5000/oracle/weblogic:12.1.3-dev" . </code></pre> Done? Check if everything is where we expect it to be: (docker images)
<br>
<pre class="code"><code> vagrant.f8:5000/oracle/weblogic &nbsp; &nbsp; &nbsp; 12.1.3-dev &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;68f1ea788bba &nbsp; &nbsp; &nbsp; &nbsp;About a minute ago &nbsp; 2.05 GB </code></pre> Because this image only contains the server binaries, we now need to build an image which has a configured WLS domain in it. Thankfully, there are some more scripts in&nbsp;samples\12c-domain folder. So, go check, if the Dockerfile and all scripts in container-scripts&nbsp;have the correct UNIX line-ending. Sometimes, Git can mess them up, if you're on Windows. And if you're already there, make sure to change some ports according to your needs. I had to change the admin port to 8011 (do this in the Dockerfile and add-machine.py script. Another thing, we want to do is, to run the instance in development mode. This allows us to just copy our Java EE app into the ./autodeployment folder and have it deployed, when started. You can just changing the attribute in the following line from prod to dev:
<br>
<pre class="code"><code> setOption('ServerStartMode','dev') </code></pre> Now, you're ready to go ahead with building the development domain image:
<br>
<pre class="code"><code> docker build --force-rm=true --tag="vagrant.f8:5000/myfear/weblogic:12.1.3-dev" </code></pre> And, after another couple of cups of coffee, we're ready to check if this image made it into our repository (docker images)
<br>
<pre class="code"><code> vagrant.f8:5000/myfear/weblogic &nbsp; &nbsp; &nbsp;12.1.3-dev &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;77a3ec07d176 &nbsp; &nbsp; &nbsp; &nbsp;9 minutes ago &nbsp; &nbsp; &nbsp; 2.052 GB </code></pre> Before going any further, make sure to give it a shot and see, if the Weblogic instance comes up.
<br>
<pre class="code"><code> docker run -it myfear/weblogic:12.1.3-dev </code></pre> If that worked, you're ready to build your third image today. Which will contain your application.
<br>
<br><b>NetBeans And Fabric8 - Take WebLogic Into Greek Heaven</b>
<br>
 Start NetBeans and create a nice, simple and lean Java EE 6 project from a maven archetype of your choice. Add all the fabric8 and docker-maven plugin dependencies to it, like I've shown you before in the <a href="http://blog.eisele.net/2015/07/running-wildfly-on-openshift-3-with-kubernetes-fabric8-on-windows.html" target="_blank">first blog post of the series</a>. Let's tweak the properties to our needs and just name the image:&nbsp;myfear/weblogic-test:latest. Most importantly, you have to map the container port to the Kubernetes service correctly:
<br>
<pre class="code"><code> &lt;!-- Kubernetes Service Port // Mapped via the HARouter--&gt; &lt;fabric8.service.port&gt;9050&lt;/fabric8.service.port&gt; &lt;!-- The exposed container port --&gt; &lt;fabric8.service.containerPort&gt;8011&lt;/fabric8.service.containerPort&gt; &lt;!-- because, I'm working with the remote registry here, base it on the remote image --&gt; &lt;docker.from&gt;vagrant.f8:5000/myfear/weblogic:12.1.3-dev&lt;/docker.from&gt; &lt;!-- Just cosmetics, changing the container label --&gt; &lt;fabric8.label.container&gt;weblogic&lt;/fabric8.label.container&gt; </code></pre> Don't forget to use Java EE 6 as dependency, and change both user and deployment base in the docker-maven plugin to:
<br>
<pre class="code"><code> &lt;user&gt;oracle:oracle:oracle&lt;/user&gt; &lt;basedir&gt;/u01/oracle/weblogic/user_projects/domains/base_domain/autodeploy/&lt;/basedir&gt; </code></pre> Time to build the third and last docker image:
<br>
<pre class="code"><code> mvn clean install docker:build </code></pre> And if that finished correctly, we're going to deploy everything to OpenShift with the Fabric8 tooling:
<br>
<pre class="code"><code> mvn fabric8:json fabric8:apply </code></pre> And don't forget to add the host-name mapping to your hosts file.
<br>
<pre class="code"><code> 172.28.128.4 myfear-weblogic-test.vagrant.f8 </code></pre> A request to http://myfear-weblogic-test.vagrant.f8/sample shows the application after you waited a couple of minutes (at least, I had to; Looks like my laptop wasn't quick enough.).
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="wls-on-k8.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="98" src="wls-on-k8.png" width="400"></a>
</div>
<br><b>Some Further Remarks</b>
<br>
 This isn't exactly production ready. WLS knows managed servers and node managers and there are a bunch of ports for external communication, that need to be opened. This basically did nothing more than to deploy a teensy application onto the AdminServer. There are a couple of whitepapers and further ideas about how to tweak the domain scripts to fit your needs. I didn't want to do that for obvious reasons. So, consider this a proof of concept.
<br>
<br>
<br>
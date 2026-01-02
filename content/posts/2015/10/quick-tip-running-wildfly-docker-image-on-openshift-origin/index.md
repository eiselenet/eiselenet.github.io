---
title: "Quick Tip: Running WildFly Docker Image on OpenShift Origin"
date: 2015-10-05 09:31:00 +0000
layout: post
tags: ["Openshift", "WildFly9", "Docker"]
slug: "quick-tip-running-wildfly-docker-image-on-openshift-origin"

url: /2015/10/quick-tip-running-wildfly-docker-image-on-openshift-origin.html
---

On to a new week. There's been plenty of travel for me recently, and it don't stop soon. But I have some time to try out OpenShift Origin and run it on my Windows environment. There is an entry level blog-post how to setup everything from a couple of days ago. Now it was about time to just run a vanilla Docker image on it.
<br>
<br><b>Prerequisites</b>
<br>
 Get your <a href="http://blog.eisele.net/2015/09/running-openshift-origin-on-windows.html" target="_blank">Origin installation up and running</a>. And make sure to also install the OpenShift binaries locally. The OpenShift team released the all in one vm on a separate, developer friendly and good looking website a couple of days after my post. So, all you need to remember is this address:&nbsp;<a href="" target="_blank">http://www.openshift.org/vm/</a>
<br>
<br><b>Get your OpenShift Environment Up</b>
<br>
 This is a single vagrant up command. If that succeeded, you should be able to access the local admin-console via your browser at http://localhost:8443/ and also login with the client tools from the command line:
<br>
<pre class="code"><code>oc login http://localhost:8443 </code></pre> Use admin/admin as username/password.
<br>
<br><b>Create A Project And Run WildFly</b>
<br>
 First thing to do is to create a new OpenShift project. We want to separate this a bit from the default. At the end, think of it as a namespace in which we can just play around a bit:
<br>
<pre class="code"><code>oc new-project wildfly-tests --description="WildFly and Docker on OpenShift v3" --display-name="WildFly Test Project" </code></pre> OpenShift doesn't directly expose a Docker daemon. So, you need to use the oc command line tool to run an image. There are some (unsupported) JBoss community images available and listed on&nbsp;<a href="" target="_blank">http://www.jboss.org/docker/</a>. I am interested in running latest WildFly 9 for this test.
<br>
<pre class="code"><code>oc new-app --docker-image=jboss/wildfly </code></pre> If you watch the web-console, you will see, that a deployment is running and the Docker image get's pulled.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="origin-pulling-wf-image.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="180" src="origin-pulling-wf-image.PNG" width="400"></a>
</div>
<br>
 Depending on your connection, this might take some time. But when it's finished, you will see a green bar that states "Running" and also shows an IP-address. Let's see, if everything went well and the WildFly instance is up and running. We do need to see the logs for our pod. Let's list them:
<br>
<pre class="code"><code>oc get pods NAME &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;READY &nbsp; &nbsp; STATUS &nbsp; &nbsp;RESTARTS &nbsp; AGE wildfly-1-jzvsj &nbsp; 1/1 &nbsp; &nbsp; &nbsp; Running &nbsp; 0 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;11m </code></pre> and see the logs:
<br>
<pre class="code"><code>oc logs wildfly-1-jzvsj </code></pre> Note, that the pod name will most likely be different in your environment. The command should output the WildFly logs as you are used to them. For now, we have a pod running. Now we need to expose this pod's port via a service to the external world. But first of all we need to decide, via which domain-name we want to expose it. Add/change your hosts file with the following entry:
<br>
<pre class="code"><code>127.0.0.1 wildfly.openshiftdev.local </code></pre> And execute the following command to add an external route to the service:
<br>
<pre class="code"><code>oc expose service wildfly --hostname=wildfly.openshiftdev.local </code></pre> Browse to the services tab in the console and see, that the route was created for the service.
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="origin-wf-service.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="124" src="origin-wf-service.PNG" width="320"></a>
</div>
<br>
 The only thing left to do now is to change the port-forwarding rules in the VirtualBox console. Add the port 80 from the host to the guest.
<br>
 Now you can access the WildFly instance via&nbsp;h<a href="">ttp://wildfly.openshiftdev.local/</a>. Congratulations!
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="wf-running-on-origin.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="244" src="wf-running-on-origin.PNG" width="320"></a>
</div>
<br><b>Trouble Shooting</b>
<br>
 If you're running anything else than the all-in-on-vm, for example the <a href="http://blog.eisele.net/2015/07/running-wildfly-on-openshift-3-with-kubernetes-fabric8-on-windows.html" target="_blank">fabric8 vagrant image</a>, you will need to change the <a href="https://docs.openshift.org/latest/admin_guide/manage_scc.html#enable-images-to-run-with-user-in-the-dockerfile" target="_blank">security settings in OpenShift</a>. Ssh into the instance, login via the oc command line and edit the security settings:
<br>
<pre class="code"><code>oc edit scc restricted </code></pre> Change the runAsUser.Type strategy to RunAsAny.&nbsp;This allows images to run as the root UID if no USER is specified in the Dockerfile.
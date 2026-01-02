---
title: "Scaling Java EE Microservices on OpenShift"
date: 2015-10-12 12:44:00 +0000
layout: post
tags: ["Openshift", "Scaling", "wildfly", "microservice"]
slug: "scaling-java-ee-microservices-openshift"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2015/10/scaling-java-ee-microservices-openshift.html
---

The first two parts of this little series introduced you build a tiny <a href="http://blog.eisele.net/2015/10/wildfly-swarm-jax-rs-microservice-on-docker.html" target="_blank">JAX-RS service with WildFly Swarm and package it into a Docker image</a>. You learned how to <a href="http://blog.eisele.net/2015/10/scaling-java-ee-microservices-on-openshift.html" target="_blank">deploy this example to OpenShift</a> and now it is time to scale it up a bit.
<br>
<br><b>Why Scaling Is Important</b>
<br>
 One of the key aspects of microservices based architectures is decomposition into highly performant individual services which scale on demand and technically easy. Applications are now being built to scale and infrastructure is transparently assisting where necessary. While Java EE developers have done this a lot in the past with the standard horizontal scaling by putting more physical boxes next to each other or limit vertical scaling by spinning up more instances on the same host. Microservices allow for different scaling approaches. A much more complete definition of the different variations for scaling can be found in the book <a href="" target="_blank">The Art Of Scalability</a>. I'm going to dig into different approaches with future blog-posts. To make the entry into scaling a little bit easier, we're going to scale our tiny little app vertically today by spinning up more pods for it.
<br>
<br><b>What Is A Pod</b>
<br>
 A pod (as in a pod of whales or pea pod) is a Kubernetes object which corresponds to a colocated group of applications running with a shared context.&nbsp;In terms of Docker constructs, a pod consists of a colocated group of Docker containers with shared volumes.&nbsp;In a pre-container world, they would have executed on the same physical or virtual host. So, that's what we want to scale in this example. The pod, that is already running.
<br>
<br><b>What Did We Do So Far?</b>
<br>
 When you first deployed the JAX-RS example, OpenShift created a bunch of resources. Namely:
<br>
<ul>
 <li><i>Imagestream&nbsp;</i><br>
  An image stream is similar to a Docker image repository in that it contains one or more Docker images identified by tags. OpenShift stores complete metadata about each image (e.g., command, entrypoint, environment variables, etc.). Images in OpenShift are immutable. OpenShift components such as builds and deployments can watch an image stream and receive notifications when new images are added, reacting by performing a build or a deployment, for example.</li>
 <li><i>Service</i><br>
   A Kubernetes service serves as an internal load balancer. It identifies a set of replicated pods in order to proxy the connections it receives to them.</li>
 <li><i>DeploymentConfig</i><br>
  Building on replication controllers, OpenShift adds expanded support for the software development and deployment lifecycle with the concept of deployments. OpenShift deployments also provide the ability to transition from an existing deployment of an image to a new one and also define hooks to be run before or after creating the replication controller.</li>
</ul> So, a service proxies our request to the pods, and a deploymentconfig is build on top of the Kubernetes replication controller, which controls the number of pods. We're getting closer!
<br>
<br><b>Scale My Microservice now, please!</b>
<br>
 Just a second longer, so: while services provide routing and load balancing for pods which may blink in and out of existence, ReplicationControllers (RC) are used to specify and enforce the number of pods (replicas) that should be in existence. RCs can be thought of to live at the same level as Services but they provide different functionality above pods. &nbsp;RCs are a Kubernetes object.OpenShift provides a “wrapper” object on top of the RC called a Deployment Configuration (DC). DCs not only include the RC but they also allow you to define how transitions between images occur as well as postdeploy hooks and other deployment actions.
<br>
 We finally know where to look at. Let's seem what the DeploymentConfig looks like, that we created when we started our swarm-sample image.
<br>
<pre class="code"><code>oc get dc swarm-sample</code></pre>
<pre>NAME &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; TRIGGERS &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;LATEST VERSION swarm-sample &nbsp; ConfigChange, ImageChange &nbsp; 1 </pre> Even though RCs control the scaling of the pods, they are wrapped in a higher construct, DeploymentConfig, which also manages when, where, and how these Pods/RCs will be deployed. We can still see the underlying RC: (note: output truncated)
<br>
<pre class="code"><code>oc get rc swarm-sample-1</code></pre>
<pre>CONTROLLER &nbsp; &nbsp; &nbsp; CONTAINER(S) &nbsp; IMAGE(S) &nbsp; REPLICAS swarm-sample-1 &nbsp; swarm-sample &nbsp; 172.30.101.151:5000/myfear/swarm-sample@sha256:[...] &nbsp; 1 </pre> And now we need to know if whatever scaling we're going to do is actually working. I did push a little <a href="https://github.com/myfear/WildFlySwarmDockerSample/blob/master/check_scaling.bat" target="_blank">curl script</a>, which outputs the result from the JAX-RS endpoint and sleeps for 2 seconds before it is requesting the output again. Start it up and watch the result returning the same hostname environment variable all over until you execute the following command:
<br>
<pre class="code"><code>oc scale dc swarm-sample --replicas=3</code></pre> Now everything changes and after a while you see three different hostnames being returned. It might take a while (depending on your machine and how quickly OpenShift can spin up the new pods.You can also see the change in the admin console, where three pods are now displayed.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="wildfly-swarm-scaled.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="186" src="wildfly-swarm-scaled.PNG" width="400"></a>
</div>
<br>
 We can revert the behavior by setting the replicas count back to 1.
<br>
<pre class="code"><code>oc scale dc swarm-sample --replicas=1</code></pre> That was easy. And not exactly considered best-practice. Because all of the pods share the same context, they should never run on the same physical machine. Instead, it would be better to run a complete microservice (frontend, backend, database) on three pods within the same RC. But this is a topic for more blog-posts to come. Now you learned, how to scale pods on OpenShift and we can continue to evolve our example application further and do more scaling examples later.
---
title: "Deploying Java EE Microservices on OpenShift"
date: 2015-10-09 09:38:00 +0000
layout: post
tags: ["Openshift", "javaee", "microservice"]
slug: "deploying-java-ee-microservices-on-openshift"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2015/10/deploying-java-ee-microservices-on-openshift.html
---

I blogged about the <a href="http://blog.eisele.net/2015/10/wildfly-swarm-jax-rs-microservice-on-docker.html" target="_blank">simple JAX-RS microservice with WildFly Swarm</a> yesterday. You learned how to build a so called "fat-jar" with Maven and also used the Maven Docker plugin to dockerize our microservice and run it locally on Docker Machine. This was a nice way to test things locally. What was missing so far is to put this into production. Let's look what steps are necessary to run yesterdays example on OpenShift Origin.
<br>
<br><b>Why Should An Enterprise Java Developer Care?</b>
<br>
 But first of all, let's briefly look into why an Enterprise Java developer should even care about all of this. There is something about the recent hypes and buzzes, that let me wonder a bit. For sure, they make an interesting playing-field and you can spend hours of downloading container images and running them on your laptop. But bringing them into production was a challenge so far. Nigel has a really <a href="" target="_blank">nice blog-post up about a deprecated feature in Docker</a>. And it has another gem in it: A paragraph called: "Enterprise Impact". The main quote is:
<br>
<br>
<blockquote class="tr_bq">
 "I’m sure doing this kind of stuff is done all the time in cool hipster companies [...] But it’s absolutely not done in rusty old enterprises [...]".
 <br>
  (Nigel Poulton)&nbsp;
</blockquote>
<br>
 And I can absolutely second that. Enterprise Developers, Architects and Project Managers are taking a much slower and conservative approach to adopting all those technologies. And they are looking for ways to successfully manage infrastructures and projects. All those technologies will find their way into our daily work life, but they will come in a more manageable way. So, we're just doing our homework with educating ourselves about all of this and evaluating solutions that will help us with that. But enough of general thoughts; Let's start to deploy and scale a Java EE application.
<br>
<br><b>Prerequisites</b>
<br><a href="http://blog.eisele.net/2015/09/running-openshift-origin-on-windows.html" target="_blank">Install and run OpenShift Origin</a> and follow the steps to build a <a href="http://blog.eisele.net/2015/10/wildfly-swarm-jax-rs-microservice-on-docker.html" target="_blank">WildFly Swarm JAX-RS Microservice in a Docker Container</a>. Because this is the example, I'm going to deploy and scale further on.
<br>
 (NOTE: I am using both, the&nbsp;<a href="" target="_blank">all-in-on-vm from the OpenShift project</a>&nbsp;and the&nbsp;<a href="http://fabric8.io/guide/getStarted/vagrant.html" target="_blank">Vagrant image delivered by the Fabric8 project</a>&nbsp;interchangeable. They work pretty much the same and both rely on OpenShift Origin. If you see URLs ending on .f8, e.g. https://vagrant.f8:8443 in one of the codes or examples, you can use localhost or other host-mappings interchangeable. )
<br>
<br><b>What Is OpenShift Origin?</b>
<br><a href="http://www.openshift.org/#v3" target="_blank">OpenShift Origin</a> is the upstream open source version of Red Hat's distributed application system, OpenShift. We launched this project to provide a platform in which development teams could build and manage cloud native applications on top of Docker and Kubernetes. You can find the <a href="https://github.com/openshift/origin" target="_blank">source code on Github</a> and&nbsp;we know you've got great ideas for improving OpenShift Origin. So roll up your sleeves and come <a href="http://www.openshift.org/#contribute" target="_blank">join us in the community</a>.
<br>
 There is a lot to know to master all the integrated technologies. But the community is working hard to make this as understandable and manageable as possible for us, the enterprise Java developers. To give you a brief overview of OpenShift, this is a simple diagram of how everything works:
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="origin-overview.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="237" src="origin-overview.PNG" width="400"></a>
</div>
<br>
 You see a lot of common parts here, if you've been following the latest buzz around Docker and Kubernetes. A request comes in via a client and ends in the routing layer. It get's dispatched to a service and hit's a pod which is running one of our Docker images in a container. The pods are controlled by replication controllers. There is a lot more to it, of course, but this should be all you need to understand for now to get a first idea about the whole thing.
<br>
 Another, more detailed overview gives you a more precise idea about the parts, that we are going to work with today.
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="origin-overview.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="225" src="origin-overview.PNG" width="400"></a>
</div>
<br>
 Especially the integrated Docker registry, the image stream, deployment configuration and routing to our services are of interest for now.
<br>
<br><b>The Basics - Administration UI and Client Tools</b>
<br>
 After you setup your vagrant box and have fired it up, you can access the web-based administration by browsing to: https://localhost:8443. The all-in-one-vm comes without configured security. This means, that the "Allow All" identity provider is used. You can login with any non-empty user name and password. The "admin" user is the administration user with all rights. Login in with "admin/admin" gives you full power on Origin. The web-based administration is good for looking at logfiles and the overall picture. It is (not yet) fully featured and doesn't allow you to tweak or change things. First and foremost, you need to use the command line tool: "oc". And similar to the web-adminstration, you also have to login to OpenShift:
<br>
<pre class="code"><code>oc login https://localhost:8443 </code></pre> You are also prompted for a username and password (admin/admin) and presented with a list of projects:
<br>
<pre>Authentication required for https://vagrant.f8:8443 (openshift) Username: admin Password: Login successful. Using project "default". You have access to the following projects and can switch between them with 'oc project &lt;projectname&gt;': &nbsp; * default (current) &nbsp; * openshift &nbsp; * openshift-infra </pre> Now you're ready for some administration in OpenShift.
<br>
<br><b>Exposing the Internal Docker Registry</b>
<br>
 If we want to run a dockerized application in OpenShift, which isn't available in the docker-hub, we need to push it to the OpenShift Docker Registry. By default it isn't externally exposed, so first thing to do is to expose the build in OpenShift Docker Registry via a Route.
<br>
<pre class="code"><code>oc create -f registry-route.json </code></pre> The <a href="https://github.com/myfear/WildFlySwarmDockerSample/blob/master/registry-route.json" target="_blank">json file</a> contains the definition for the route and is checked into my Github repository. Make sure to adjust the <a href="https://github.com/myfear/WildFlySwarmDockerSample/blob/master/registry-route.json#L8" target="_blank">host name in Line 8</a> to your needs. For this example to work, I added the following mapping to my hosts file on Windows:
<br>
<pre class="code"><code>172.28.128.4<span class="Apple-tab-span" style="white-space: pre;"> </span>registry.vagrant.f8 </code></pre> When the route is successfully created, all you have to do is to set your environment accordingly (you will have done this already, when you followed my intro blog-posts. So this is just a reminder):
<br>
<pre class="code"><code>set DOCKER_HOST=tcp://vagrant.f8:2375 </code></pre><b>Creating A Project And A User</b>
<br>
 Let's create a new project for our example. Because of namespace reasons, we will name the project exactly after the user and image name: In this example, "myfear".
<br>
<pre class="code"><code>oc new-project myfear --description="WildFly Swarm Docker Image on OpenShift v3" --display-name="WildFly Swarm Project" </code></pre> The description and display name are optional, but make it better looking in the web-ui.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="origin-projects.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="120" src="origin-projects.PNG" width="320"></a>
</div>
<br>
 Let's create a user "myfear" by simply logging in as:
<br>
<pre class="code"><code>c login https://vagrant.f8:8443 -u myfear </code></pre>
<br><b>Tweaking The Example</b>
<br>
 We need to change some parts of the pom.xml from yesterdays example. First of all, we need to tell the Docker Maven Plugin, that it should use a private registry running at registry.vagrant.f8:80. Wondering, why this isn't port 5000? Because, we exposed the service via OpenShift and the HAProxy did it via port 80. Uncomment the <a href="https://github.com/myfear/WildFlySwarmDockerSample/blob/master/pom.xml#L20" target="_blank">two lines in the pom.xml</a>:
<br>
<pre class="code"><code>&lt;docker.host&gt;tcp://vagrant.f8:2375&lt;/docker.host&gt; &lt;docker.registry&gt;registry.vagrant.f8:80&lt;/docker.registry&gt; </code></pre> And get the login token for the user myfear via the oc client tools:
<br>
<pre class="code"><code>$oc whoami -t </code></pre> which will output something like this:
<br>
<pre>ykHRzUGGu-FAo_ZS5RJFcndjYw0ry3bskMgFjqK1SZk </pre> Now update the token in the <a href="https://github.com/myfear/WildFlySwarmDockerSample/blob/master/pom.xml#L128" target="_blank">&lt;authConfig&gt; element</a> of the pom. That's basically it.
<br>
<br><b>Build And Push The Image</b>
<br>
 The image has been build in <a href="http://blog.eisele.net/2015/10/wildfly-swarm-jax-rs-microservice-on-docker.html" target="_blank">my earlier blog-post</a> already, but let's just do it again here:
<br>
<pre class="code"><code>mvn clean install docker:build </code></pre> Now push the image to our OpenShift Docker Registry: 
<br>
<pre class="code"><code>mvn docker:push </code></pre> Which will output the process of pushing the image to&nbsp;registry.vagrant.f8:80/myfear/swarm-sample.
<br>
<br><b>Run A Docker Image On OpenShift</b>
<br>
 Now we just use the regular way to spin up a new Docker image on OpenShift:
<br>
<pre class="code"><code>oc new-app --docker-image=myfear/swarm-sample:latest </code></pre> And watch what is happening: OpenShift actually created several resources behind the scenes in order to handle deploying and running this Docker image. First, it made a Service, which identifies a set of pods that it will proxy and load balance. Services assign an IP address and port pair that, when accessed, redirect to the appropriate back end The reason you care about services is they basically act as a proxy/load balancer between your pods and anything that needs to use the pods that is running inside the OpenShift environment. Get a complete description of what OpenShift created from our image by using the describe command:
<br>
<pre class="code"><code>oc describe service swarm-sample </code></pre> Which outputs:
<br>
<pre>Name: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; swarm-sample Namespace: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;myfear Labels: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; app=swarm-sample Selector: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; app=swarm-sample,deploymentconfig=swarm-sample Type: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ClusterIP IP: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 172.30.25.44 Port: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 8080-tcp &nbsp; &nbsp; &nbsp; &nbsp;8080/TCP Endpoints: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;172.17.0.5:8080 Session Affinity: &nbsp; &nbsp; &nbsp; None No events. </pre> The one thing, we're missing so far is the external mapping via a route. You recall what we did for the Docker Registry? This is the next and last step so far.
<br>
<pre class="code"><code> oc expose service swarm-sample --hostname=wildfly-swarm.vagrant.f8 </code></pre> And as you might have guessed, we also need to map the hostname in the hosts file:
<br>
<pre>172.28.128.4 &nbsp; &nbsp;wildfly-swarm.vagrant.f8 </pre> And we're done. Why I didn't use a json file to create the route? Because I wanted to show you, that it can be easier, as long the image uses the correct EXPOSE definitions for the ports, the oc expose command does this job without having to mess around with json. It is the same result.
<br>
<br>
 Browse to:&nbsp;http://wildfly-swarm.vagrant.f8/rs/customer and see the output:
<br>
<pre>\{"text":"WildFly Swarm Docker Application on OpenShift at http://wildfly-swarm.vagrant.f8/rs/ - Hostname: swarm-sample-1-7mmd7"\} </pre> The hostname is the pod, on which our container is running on.
<br>
<br>
 Next up in this series is scaling and load-balancing our little example. Stay tuned for more! Have questions, or ideas about more Java EE and Docker and OpenShift? Please let me know and follow me on Twitter <a href="http://twitter.com/myfear" target="_blank">@myfear</a>
<br>
<br>
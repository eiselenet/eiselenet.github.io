---
title: "Java EE Deployment Scenarios for Docker Containers"
date: 2015-05-29 11:20:00 +0000
layout: post
tags: ["wildfly", "Docker", "javaee"]
slug: "java-ee-deployment-secenarios-for-docker"

url: /2015/05/java-ee-deployment-secenarios-for-docker.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="container.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="200" src="container.jpg" width="200"></a>
</div> I've been posting some content around Docker since a while and I like to play around with containers in general. You can find some more information about how to run <a href="http://blog.eisele.net/2015/05/docker-maschine-on-windows-host-setup-howto.html" target="_blank">Docker-Machine on Windows</a> and also showed you <a href="http://blog.eisele.net/2015/04/how-to-use-docker-16-windows-client.html" target="_blank">how to use the Docker 1.6 client</a>. One of the first blog posts was a compilation of all kinds of resources around <a href="http://blog.eisele.net/2014/12/docker-for-java-ee-developers-on-windows-with-maven.html" target="_blank">Java EE, Docker and Maven for Java EE developers</a>. Working more detailed and often with containers brings up the question about how Java EE applications should be distributed and how developers should use containers. This tries to clarify a little and give you a good overview about the different options.
<br>
<br><b>Base Image Image vs. Custom Image and Some Basics</b>
<br>
 Most likely, your application server of choice is available on the public registry, known as <a href="" target="_blank">docker hub</a>. This is true for WildFly. The first decision you have to make is, if you want to use one of the base images or if you are going to bake your own image. Running with the base is pretty much:
<br>
<pre class="code"><code> docker run -p 8080 -it jboss/wildfly </code></pre> and your instance is up and running in a second, after the base image was downloaded. But what does that mean? And how does it work? At the heart of every container is Linux. A teensy one. In a normal Linux, the kernel first mounts the root File System as read-only, checks its integrity, and then switches the whole rootfs volume to read-write mode. The teensy Linux in a Docker container does that differently. Instead of changing the file system to read-write mode, it takes advantage of a union mount to add a read-write file system over the read-only file system. In fact there may be multiple read-only file systems stacked on top of each other. If you look at the jboss/wildfly image, this is what you get on first sight:
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="plain-wildfly0.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="153" src="plain-wildfly0.png" width="320"></a>
</div> You see four different levels in this picture. Let's not call them layer, because they aren't yet. This is the hierarchy of images which are the base for our jboss/wildfly image. Each of those images is composed out of a <a href="" target="_blank">Dockerfile</a>. This is an empty text file with a bunch of instructions in it. You can think of it as a sort of pom-file which needs do be processed through a tool called "<a href="" target="_blank">Builder</a>". It can contain a variety of commands and options to add users, volumes, add software, downloads and many many more. &nbsp;If you look at the <a href="" target="_blank">jboss/wildfly Dockerfile</a> you see the commands that compose the image:
<br>
<pre class="code"><code> # Use latest jboss/base-jdk:7 image as the base FROM jboss/base-jdk:7 # Set the WILDFLY_VERSION env variable ENV WILDFLY_VERSION 8.2.0.Final # Add the WildFly distribution to /opt, and make wildfly the owner of the extracted tar content # Make sure the distribution is available from a well-known place RUN cd $HOME &amp;&amp; curl http://download.jboss.org/wildfly/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz | tar zx &amp;&amp; mv $HOME/wildfly-$WILDFLY_VERSION $HOME/wildfly # Set the JBOSS_HOME env variable ENV JBOSS_HOME /opt/jboss/wildfly # Expose the ports we're interested in EXPOSE 8080 # Set the default command to run on boot # This will boot WildFly in the standalone mode and bind to all interface CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"] </code></pre> The first line defines the base from which the image is derived. Looking at the <a href="" target="_blank">jboss/base-jdk:7 Dockerfile</a> reveals the root which is <a href="" target="_blank">jboss/base</a>.
<br>
 Now imagine, that every single one of those lines does something to the filesystem. Most obvious example is a download. It adds something to it. But instead of writing it to an already mounted partition, it get's stacked up as a new layer. Looking at all the layers of jboss/wildfly this sums up to <a href="https://imagelayers.io/?images=jboss%2Fwildfly:latest" target="_blank">19 unique layers with a total size of 951mb</a>.
<br>
<br>
 Using the base image, you can expect to have a default configuration at hand. And this is a great place to start. We at JBoss try to make our projects (and products too!) usable out-of-box for as many use cases as we can, but there is no way that one configuration could satisfy everyone’s needs. For example, we ship 4 flavors of the standalone.xml configuration file with WildFly since there are so many different deployment scenarios. But this is still not enough. We need to be able to tweak it at any point. The jboss/wildfly image is not an exception here.
<br>
 Creating a custom image with
<br>
<pre class="code"><code> # Use latest jboss/wildfly as a base FROM jboss/wildfly </code></pre>
<br>
 is your first step into the word of a customized image. If you want to know, how to do that, there is <a href="" target="_blank">an amazing blog post which covers almost all the details</a>.
<br>
<br><b>Java EE Applications - On Docker</b>
<br>
 One of the main principles behind Docker is "Recreate — Do Not Modify ". With a container being a read-only, immutable piece of infrastructure with very limited capabilities to be changed at runtime, you might be interested in the different options you have to deploy your application.
<br>
<br><i>Dockerize It</i>
<br>
 This is mostly referred to as "custom image" beside the needed configuration changes, you also add the binary of your application as a new layer your image.
<br>
<pre class="code"><code> RUN curl -L https://github.com/user/project/myapp.war?raw=true -o /opt/jboss/wildfly/standalone/deployments/myapp.war </code></pre> Done. Build, Push and run your custom image. If one instance dies, don't worry and fire up a new one.
<br>
<br><u>Drawbacks:</u>
<br>
 - No re-deployments. Every
<br>
 - No changes
<br>
 - New version, new image version
<br>
 - Not the typical operations model for now.
<br>
 - Might need additional tooling (plugins for Maven/Jenkins)
<br>
<br><u>Advantages:</u>
<br>
 - The Docker Way
<br>
 - Easy to integrate into <a href="" target="_blank">your project build</a>.
<br>
 - Easy to roll-out and configure
<br>
<br><i>Containers as Infrastructure</i>
<br>
 There's no real term for it. It basically means that you don't care how your infrastructure is run. This might be the called old-fashioned operations model. You will need to have some kind of access to the infrastructure. Either a shared filesystem with the host to deploy applications via the deployment scanner or the management ports need to be open in order to use the CLI or other tooling to deploy your applications into the container.
<br>
<br><u>Drawbacks:</u>
<br>
 - More complex layering to keep state in containers
<br>
 - Not the Docker Way
<br>
 - Not fancy.
<br>
 - Centralized ops and administration
<br>
<br><u>Advantages:</u>
<br>
 - Hardly any change to what you're used to as a developer in enterprises today
<br>
 - Doesn't need to be integrated into your build at all. It's just an instance running somewhere
<br>
<div>
 - No additional tooling.
</div>
<div>
 <br>
</div>
<div>
 <b>Recommendation</b>
</div>
<div>
 This is hard. I'd suggest, that you look into what fits best for your situation. Most of the enterprise companies might tend to stick with the Containers as Infrastructure solution. This has a lot of drawbacks for now looking at it from a developers perspective. A decent intermediate solution might be <a href="http://www.openshift.org/#v3" target="_blank">OpenShift v3</a> which will optimize operations for containers and bring a lot of usability and comfort to the Java EE PaaS world.
</div>
<div>
 If you are free to make a choice, you can totally go with the "Dockerize" way. Keep in mind, that this is a vendor-lock-in as of today and some more <a href="" target="_blank">promising and open solutions</a> on the horizon already.&nbsp;
</div>
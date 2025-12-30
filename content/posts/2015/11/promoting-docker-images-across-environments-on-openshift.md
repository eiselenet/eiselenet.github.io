---
title: "Promoting Docker Images Across Environments"
date: 2015-11-04 15:00:00 +0000
layout: post
tags: ["Openshift", "wildfly", "Docker", "microservice"]
slug: "2015-11-04-promoting-docker-images-across-environments-on-openshift"
url: /2015/11/promoting-docker-images-across-environments-on-openshift.html
---

I got off the stage at WJAX in Munch just a couple of minutes ago. And while my co-worker Jan was talking about this amazing <a href="https://www.redhat.com/en/about/press-releases/amadeus-deploys-openshift-red-hat-foundation-cloud-based-application-infrastructure" target="_blank">customer success story AMADEUS</a>, I had the pleasure to base his thoughts and ideas a bit and talk about how DevOps can be done with OpenShift and Docker Images from a developers perspective.
<br>
<br><b>Microservices and DevOps</b>
<br>
 If you have hundreds or even more container running in production everything isn't any longer just an operations problem. Not only because everybody is part of the DevOps movement today, but mainly, because we have to build our applications differently if we want to be able to profit from the synergies provided by immutable and stateless containers. The example I was using in the keynote was pretty much the same, that has <a href="http://blog.eisele.net/2015/10/wildfly-swarm-jax-rs-microservice-on-docker.html" rel="nofollow" target="_blank">been used on my blog before</a>. A single WidlFly Swarm JAX-RS service, which does little more than printing out some JSON which contains an environment variable and a timestamp. The thing, that the series was missing is the staging across environments. And while there are some great blogs by Veer Muchandi over at OpenShift, they all assume that you use the OpenShift way of building images and applications.
<br>
<br><b>Let's Stick To Plain Docker</b>
<br>
 I wanted to show, how easy it is while using plain Docker Images. So, assuming that you have a very straight forward environment setup:
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="environments.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img alt="Environment Setup (Dev, QA, Production)" border="0" height="251" src="environments.png" title="Promoting across Environments" width="320"></a>
</div> We have a dev environment which the developers use to just push their updated images. And another QA environment where the testers go crazy and probably many others. The example I showed just used the two mentioned. It will be pretty much the same for every other env you add. Let's see how this works with Maven, Docker and OpenShift
<br>
<br><b>Preparations</b>
<br>
 I used the fabric8 vagrant image in this case behind the scenes. Only because they always pull in the latest OpenShift builds and I wanted to show the new visualizations that are already in HEAD. So, install vagrant and the OpenShift binaries and fire up the openshift vagrant machine. I tweaked the memory and cpu settings a bit to 8 GB and 4 cores. Make sure to expose the internal Docker Registry as shown <a href="http://blog.eisele.net/2015/10/scaling-java-ee-microservices-on-openshift.html" rel="nofollow" target="_blank">in the earlier blogpost</a>. If you set your DOCKER_HOST variable accordingly you can start the walk-through. The recording of the demo gives you the complete show in about 10 minutes. And yes, there's no audio in this one.
<br>
<br>
<div class="video-container">
 <iframe allowfullscreen frameborder="0" height="315" src="https://www.youtube.com/embed/f1jpoZZeW2w" width="560"></iframe>
</div>
<br>
<br><b>The Individual Steps </b>
<br>
 Sometimes it is easier to just follow the individual steps yourself. Here they are:
<br>
 Login to OpenShift as admin with password admin
<br>
<pre class="code"><code>oc login https://vagrant.f8:8443 --insecure-skip-tls-verify=true</code></pre>
<br>
 Build your Maven based project (maybe just <a href="https://github.com/myfear/WildFlySwarmDockerSample" target="_blank">git clone my example</a> and use that)
<br>
<pre class="code"><code>mvn clean install</code></pre>
<br>
 Run the Local WildFly Swarm fat-jar
<br>
<pre class="code"><code>java -jar target/swarm-sample-0.0.1-SNAPSHOT-swarm.jar</code></pre>
<br>
 Point your browser to&nbsp;http://localhost:8080/api/v1/service and verify everything is working as expected
<br>
<br>
 Before you build the Docker Image from your application, make sure to pull the jboss/base-jdk:8 image. I don't know exactly why, but the plugin seems to have an issue with resolving it.
<br>
<pre class="code"><code>docker pull jboss/base-jdk:8&nbsp;</code></pre>
<br>
 Now build your Docker image
<br>
<pre class="code"><code>mvn clean install docker:build&nbsp;</code></pre>
<br>
 Look at the resulting Dockerfile
<br>
<pre class="code"><code>cat&nbsp;target/docker/development/swarm-sample/latest/build/Dockerfile</code></pre>
<br>
 Create a new development project in OpenShift. At this point it doesn't matter, if it is a project in the same instance or another environment. As I was going to demo this locally on my laptop, the decision was easy. Compare Veers post from above, if you want to learn how to stage across physical environments.
<br>
<pre class="code"><code>oc new-project development --display-name="WildFly Swarm Development Project"</code></pre>
<br>
 Add rights to view and edit for both users dev1 and test1
<br>
<pre class="code"><code>oc policy add-role-to-user edit dev1 oc policy add-role-to-user view test1</code></pre>
<br>
 Create a QA project in OpenShift
<br>
<pre class="code"><code>oc new-project test --display-name="WildFly Swarm QA Project"</code></pre>
<br>
 Give the test1 user the edit rights:
<br>
<pre class="code"><code>oc policy add-role-to-user edit test1</code></pre>
<br>
 Add roles to system accounts to be able to pull images from QA out of the development environment
<br>
<pre class="code"><code>&nbsp;oc policy add-role-to-group system:image-puller system:serviceaccounts:test -n development</code></pre>
<br>
 Login as dev1 with password dev1 and get your access token
<br>
<pre class="code"><code>oc login https://vagrant.f8:8443 -u dev1 oc whoami -t</code></pre>
<br>
 Change the token in the <a href="https://github.com/myfear/WildFlySwarmDockerSample/blob/master/pom.xml#L130" target="_blank">authConfig section of your pom.xml</a>. Now, you're ready to push your WildFly Swarm Docker Image to the OpenShift Docker registry
<br>
<pre class="code"><code>mvn docker:push</code></pre>
<br>
 Create an app from the image
<br>
<pre class="code"><code>oc new-app --docker-image=development/swarm-sample:latest</code></pre>
<br>
 Expose a route for the newly created route. And make sure to have your hosts file updated with the correct mappings before accessing it via your browser.
<br>
<pre class="code"><code>oc expose service swarm-sample --hostname=wildfly-swarm.vagrant.f8</code></pre>
<br>
 Check&nbsp;http://wildfly-swarm.vagrant.f8/api/v1/service and see, if everything works.
<br>
<br>
 Get the OpenShift image streams from the image
<br>
<pre class="code"><code>oc get is oc describe is&nbsp;</code></pre>
<br>
 Tag a new image from latest to promote
<br>
<pre class="code"><code>oc tag &nbsp;172.30.238.11:5000/development/swarm-sample@sha256:6a53f0d875d75459eca6192e83cf4f8d879bb7cf9260e2a8addf412745b434fc development/swarm-sample:promote</code></pre>
<br>
 Login as user test1 with password test1 and switch to the test project
<br>
<pre class="code"><code>oc login -u test1 oc project test</code></pre>
<br>
 Create an application from the promoted image
<br>
<pre class="code"><code>oc new-app development/swarm-sample:promote</code></pre>
<br>
 Expose the service as a route
<br>
<pre class="code"><code>oc expose service swarm-sample --hostname=wildfly-swarm-test.vagrant.f8</code></pre>
<br>
 Check, if you can access:&nbsp;http://wildfly-swarm-test.vagrant.f8/api/v1/service
<br>
<br>
 If you now login as dev1 again, go full development cycle and change the app, build a new image and push it again, you can see, that only the development environment gets updated, while the QA environment still stays on the promoted tag. When developers feel like updating QA again, you can just promote the "new" latest version of the image and the test environment will get updated, too.
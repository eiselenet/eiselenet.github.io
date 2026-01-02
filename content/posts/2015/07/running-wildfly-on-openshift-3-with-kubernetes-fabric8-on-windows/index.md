---
title: "Running WildFly on Kubernetes. On Windows. Fabric8!"
date: 2015-07-25 17:32:00 +0000
layout: post
tags: ["Openshift", "wildfly", "Windows", "fabric8", "Kubernetes"]
slug: "running-wildfly-on-openshift-3-with-kubernetes-fabric8-on-windows"

url: /2015/07/running-wildfly-on-openshift-3-with-kubernetes-fabric8-on-windows.html
---

<div class="separator" style="clear: both; text-align: center;">
 <a href="redhat-container-stack.png" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="245" src="redhat-container-stack.png" width="320"></a>
</div> Have you ever dreamed about running WildFly on OpenShift and leverage the latest Kubernetes features: On Windows? Sounds like blasphemy: Everything about those technologies is screaming GO and Linux. Windows doesn't seem to be the right fit. But I know, that there are many developers out there, being stuck on Windows. Corporate laptops, easy management and whatever reasons the different employers come up with. The good news is, there is a small and brave group of people, who won't let those &nbsp;Windows users down. And I have to admit, that running a Windows operating system while working for Red Hat is a challenge.
<br>
 We're a Linux company and an open source company and everything Windows simply feels wrong.
<br>
 As my fellow colleague Grant stated&nbsp;<a href="" target="_blank">in a blog-post</a> a couple of weeks ago:
<br>
 "That being said, I have decided to use Windows as my primary operating system in order to ensure that OpenShift has a great developer experience for Windows users. "
<br>
 So, I tried to get Kubernetes and OpenShift running on Windows for a while, natively not possible right now. On the other hand, I really want to get my hand on latest developments and look into fancy stuff. &nbsp;But there is a solution: Vagrant and Fabric8.
<br>
 And Fabric8 only because, I am a Java developer. In fact if you are a Java developer wanting to work with Kubernetes Fabric8 really is the easiest and quickest way to get going. So, let's setup OpenShift and Fabric8 on a Windows machine.
<br>
<br><b>Prerequisites</b>
<br><a href="http://www.vagrantup.com/downloads.html" target="_blank">Download and install Vagrant</a> (don't worry, it's MIT licensesed). Done with that? Restart your machine (You know, why it's Windows.) You will need to install an additional Vagrant plugin. Switch to a cmd line and type:
<br>
<pre class="code"><code> $vagrant plugin install vagrant-hostmanager-fabric8 </code></pre> Vagrant-hostmanager is a Fabric8 Vagrant 1.1+ plugin that manages the /etc/hosts file on guest machines (and optionally the host). Its goal is to enable resolution of multi-machine environments deployed with a cloud provider where IP addresses are not known in advance.
<br>
 The only other thing you need to have installed and ready is <a href="" target="_blank">VirtualBox</a> (GPL licensed!)
<br>
 Go and clone the <a href="https://github.com/fabric8io/fabric8-installer.git" target="_blank">Fabric8 installer git repository</a>&nbsp;and cd into the openshift/latest folder:
<br>
<pre class="code"><code> $ git clone https://github.com/fabric8io/fabric8-installer.git $ cd fabric8-installer/vagrant/openshift/latest </code></pre> The next steps are needed for proper routing from the host to OpenShift services which are exposed via routes. Unfortunately for Windows no automatic routing for new services is possible.
<br>
 You have to add new routes manually to %WINDIR%\System32\drivers\etc\hosts.
<br>
 For your convenience, a set of routes for default Fabric8 applications will be pre-added when you start up vagrant
<br>
 For new services look for the following line and add your new routes (&lt;service-name&gt;.vagrant.f8) to this file on a new line like this:
<br>
<pre class="code"><code> ## vagrant-hostmanager-start id: 9a4ba3f3-f5e4-4ad4-9e80-b4045c6cf2fc 172.28.128.4 &nbsp;vagrant.f8 fabric8.vagrant.f8 jenkins.vagrant.f8 ..... 172.28.128.4<span class="Apple-tab-span" style="white-space: pre;"> </span>myfear-wildfly-test.vagrant.f8 ## vagrant-hostmanager-end </code></pre> Now startup the Vagrant VM:
<br>
<pre class="code"><code> vagrant up </code></pre> If you want to tweak the settings for the vm you have to edit the Vagrantfile. The startup including downloads takes a couple of minutes (Good time for #coffee++). While you're waiting, jump ahead and install the OpenShift client for windows. Download the one for your os from the <a href="" target="_blank">origin project on github</a>. The windows build has 55 MB. Next is to unpack it into a folder of your choice. Make sure
<br>
 to add this folder to your PATH environment variable.
<br>
<pre class="code"><code> set PATH=%PATH%;"D:\Program Files (x86)\openshift-origin-v1.0.3" </code></pre> While you're at it, add some more environment variables:
<br>
<pre class="code"><code> set KUBERNETES_DOMAIN=vagrant.f8 set DOCKER_HOST=tcp://vagrant.f8:2375 </code></pre> Assuming, you haven't changed the default routes added to your hosts file by the vagrant start.
<br>
 The first one allows your OpenShift cli to use the right Kubernetes domain and the second one allows you to re-use the same Docker daemon, which is already running inside your Fabric8 vagrant image. Please make sure to NOT define any of the other docker env vars like DOCKER_CERT_PATH or DOCKER_TLS_VERIFY!
<br>
 It is probably a good idea to add this into your system environment variables or put it into a batch-script.
<br>
 Note: Make sure to use the Docker 1.6 client Windows (<a href="https://get.docker.com/builds/Windows/x86_64/docker-1.6.0.exe%20on" target="_blank">exe download</a>). The latest 1.7 version doesn't work yet.
<br>
 After the vagrant box is created and docker images are downloaded, the fabric8 console should appear at <a href="" target="_blank">http://fabric8.vagrant.f8/</a>.
<br>
 Your browser will complain about an insecure connection, because the certificate is self signed. You know how to accept this, <a href="http://fabric8.io/guide/getStarted/browserCertificates.html" target="_blank">don't you</a>?
<br>
 Enter admin and admin as username and password. &nbsp;Now you see all the already installed fabric8 apps. Learn more about <a href="http://fabric8.io/guide/fabric8Apps.html" target="_blank">Apps and how to build them</a> in the documentation.
<br>
<br>
 Now, let's see if we can use the docker daemon in the vagrant image :
<br>
<pre class="code"><code> docker ps </code></pre> and see the full list of images running (just an excerpt here):
<br>
<pre class="code"><code> CONTAINER ID &nbsp; &nbsp; &nbsp; &nbsp;IMAGE &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;COMMAND &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;CREATED &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;STATUS &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;PORTS &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;NAMES d97e438222d1 &nbsp; &nbsp; &nbsp; &nbsp;docker.io/fabric8/kibana4:4.1.0 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"/run.sh" &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;7 seconds ago &nbsp; &nbsp; &nbsp; &nbsp;Up Less than a second &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;k8s_kibana.7abf1ad4_kibana-4gvv6_default_500af2d1-32b8-11e5-8481-080027bdffff_4de5764e &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; eaf419a177d6 &nbsp; &nbsp; &nbsp; &nbsp;fabric8/fluentd-kubernetes:1.0 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "fluentd" &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;About a minute ago &nbsp; Up About a minute &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;k8s_fluentd-elasticsearch.920b947c_fluentd-elasticsearch-172.28.128.4_default_9957562ee416ea2e083f45adb9b6edd0_676633bf &nbsp; c4111cea4474 &nbsp; &nbsp; &nbsp; &nbsp;openshift/origin-docker-registry:v1.0.3 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"/bin/sh -c 'REGISTR &nbsp; 3 minutes ago &nbsp; &nbsp; &nbsp; &nbsp;Up 3 minutes &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </code></pre> One last thing to check, login to OpenShift via the command line tool:
<br>
<pre class="code"><code> oc login https://172.28.128.4:8443 </code></pre> use admin and admin again as username and password. Now check, which services are already running:
<br>
<pre class="code"><code> oc get services </code></pre> Now you're ready for the next steps. Let's spin up a WildFly instance on OpenShift with Fabric8.
<br>
<br><b>Dockerizing Your Java EE Application&nbsp;</b>
<br>
 Ok, how does that work? OpenShift is build on top of Docker and Kubernetes. And Fabric8 gives the normal developer a reasonable abstraction on top of all those infrastructure issues. Where do we start? Let's start with a simple Java EE 7 project. It's a really simple one in this case. <a href="https://github.com/myfear/sample-wildfly-fabric8" target="_blank">An html page and a HelloWorld servlet</a>. First step is to dockerize it. There is a wonderful plugin out there, which is part of the Fabric8 ecosystem of tools named <a href="" target="_blank">docker-maven-plugin</a>. Simply add this <a href="https://github.com/myfear/sample-wildfly-fabric8/blob/master/pom.xml#L48" target="_blank">to your pom.xml</a> and define how the image should look like. The magic is in the plugin configuration:
<br>
<pre class="code"><code> &nbsp;&lt;configuration&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;images&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;image&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;name&gt;myfear/wildfly-test:latest&lt;/name&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;build&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;from&gt;jboss/wildfly:9.0.1.Final&lt;/from&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&lt;maintainer&gt;markus at jboss.org&lt;/maintainer&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;assembly&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;inline&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;dependencySets&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;dependencySet&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;includes&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;include&gt;net.eisele:sample-web&lt;/include&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/includes&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;outputFileNameMapping&gt;sample.war&lt;/outputFileNameMapping&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/dependencySet&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/dependencySets&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/inline&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&lt;user&gt;jboss:jboss:jboss&lt;/user&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;basedir&gt;/opt/jboss/wildfly/standalone/deployments&lt;/basedir&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/assembly&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/build&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/image&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/images&gt; &nbsp;&lt;/configuration&gt; </code></pre> Running a
<br>
<pre class="code"><code> mvn clean install docker:build </code></pre> Builds your application and creates your docker image. Plus, this image is going to be uploaded to the docker registry running on your OpenShift instance. This is configured with two additional maven properties
<br>
<pre class="code"><code> &nbsp;&lt;docker.host&gt;tcp://vagrant.f8:2375&lt;/docker.host&gt; &nbsp;&lt;docker.registry&gt;vagrant.f8:5000&lt;/docker.registry&gt; </code></pre> There's one more properties to look after:
<br>
<pre class="code"><code> &lt;docker.assemblyDescriptorRef&gt;artifact&lt;/docker.assemblyDescriptorRef&gt; </code></pre> It defines which parts of the build will be copied over to the Docker image.
<br>
 The resulting Dockerfile looks like this:
<br>
<pre class="code"><code> FROM jboss/wildfly:9.0.1.Final MAINTAINER markus at jboss.org COPY maven /opt/jboss/wildfly/standalone/deployments/ USER root RUN ["chown", "-R", "jboss:jboss","/opt/jboss/wildfly/standalone/deployments/"] USER jboss </code></pre> and a maven folder contains your application as a war file. From this point on, you could also use the docker image and push it to the official docker hub or another private repository. There's not special magic in it. Find all the configuration options in the <a href="https://github.com/rhuss/docker-maven-plugin/blob/master/doc/manual.md" target="_blank">extensive docker-maven plugin manual</a>.
<br>
<br><b>Fabric8 - Docker and Kubernetes Are Usable Now</b>
<br>
 Fabric8’s aim is to help any developer, team and organisation that wants to work with containers. Nobody really wants to use a command line to push and start containers. Plus, there's a lot more to it: Keeping them running, moving them around on hosts, monitoring, and and and. Don't even think about microservices right now, but those need even more. More fine grained control, more teams, more CI/CD and auto-discovery features. And all this is Fabric8. It can create a complete CI/CD pipeline with approvals and code quality insurance. If you want to see a complete example, have a look at what <a href="https://medium.com/fabric8-io/continuous-delivery-with-fabric8-d3c7cad76954" target="_blank">James Rawlings wrote up a couple of days ago</a>. So, what does that mean for my Java EE project and how to deploy it to OpenShift now? Read up a little about how to <a href="" target="_blank">run an application on OpenShift</a> with the nice overview post by Arun Gupta. It also includes a pointer to the <a href="https://github.com/openshift/origin/blob/master/examples/sample-app/README.md" target="_blank">OpenShift life-cycle</a>. You basically need to create an OpenShift project and include a json file, which describes your application including all the links to the docker images. Doable. For sure. But Fabric8 can do better. There is another Maven plugin available, which takes all this burden off you and just let's you deploy your application. Exactly, like I as a Java EE developer expected it to be. Let's <a href="https://github.com/myfear/sample-wildfly-fabric8/blob/master/pom.xml#L80" target="_blank">add the plugin to your project</a> and configure it a bit: 
<br>
<pre class="code"><code> &nbsp; &nbsp; &lt;plugin&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;groupId&gt;io.fabric8&lt;/groupId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;artifactId&gt;fabric8-maven-plugin&lt;/artifactId&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;version&gt;$\{fabric8.version\}&lt;/version&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;executions&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;execution&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;id&gt;json&lt;/id&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;phase&gt;generate-resources&lt;/phase&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;goals&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;goal&gt;json&lt;/goal&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/goals&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/execution&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;execution&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;id&gt;attach&lt;/id&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;phase&gt;package&lt;/phase&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;goals&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;goal&gt;attach&lt;/goal&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/goals&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/execution&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/executions&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/plugin&gt; </code></pre> This does little more, than just bind it to the different execution phases. You can skip this for this example, because we're going to execute it manually anyway. The additional configurations do happen in Maven properties again: 
<br>
<pre class="code"><code> &lt;!-- Defining the Service Name for Fabric8 --&gt; &lt;fabric8.service.name&gt;myfear-wildfly-test&lt;/fabric8.service.name&gt; &lt;!-- Defining the internal service port --&gt; &lt;fabric8.service.port&gt;9101&lt;/fabric8.service.port&gt; &lt;!-- the expsed container port --&gt; &lt;fabric8.service.containerPort&gt;8080&lt;/fabric8.service.containerPort&gt; &lt;!-- the component label, as shown in the console --&gt; &lt;fabric8.label.component&gt;$\{project.artifactId\}&lt;/fabric8.label.component&gt; &lt;!-- the container label --&gt; &lt;fabric8.label.container&gt;wildfly&lt;/fabric8.label.container&gt; &lt;!-- the application group label --&gt; &lt;fabric8.label.group&gt;myfears&lt;/fabric8.label.group&gt; &lt;!-- the domain were working in --&gt; &lt;fabric8.domain&gt;vagrant.f8&lt;/fabric8.domain&gt; &lt;!-- We don't want to upload images, but want OpenShift to pull them automatically --&gt; &nbsp;&lt;fabric8.imagePullPolicy&gt;IfNotPresent&lt;/fabric8.imagePullPolicy&gt; </code></pre> Ok, that's about it. Most of it are naming, labels and configurations which are a one-time thing to figure out. All we really need from here on, is the Kubernetes JSON file. So, type: 
<br>
<pre class="code"><code> mvn fabric8:json fabric8:apply </code></pre> What didn't work locally with my installation is, that my hosts file got updated with the new routing. So, you might need to add the domain-name mapping manually: 
<br>
<pre class="code"><code> 172.28.128.4<span class="Apple-tab-span" style="white-space: pre;"> </span>myfear-wildfly-test.vagrant.f8 </code></pre> After a couple of seconds, the new pod is created and you can access your application via <a href="" target="_blank">http://myfear-wildfly-test.vagrant.f8/</a>. This runs your application on OpenShift.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="wildfly-app.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="199" src="wildfly-app.PNG" width="320"></a>
</div>
<br>
 Try docker ps again and see, if you can spot your container. In my case:
<br>
<pre class="code"><code> c329f2e0f63b &nbsp; &nbsp; &nbsp; &nbsp;myfear/wildfly-test:latest </code></pre> If you struggle with something and your app doesn't come up as expected, there are some ways to get closer to the problem. First is, to run the image locally against your Docker daemon. There's a handy command, mvn fabric8:create-env to figure out the env vars for you so that you can run docker images outside of kubernetes as if they are inside (in terms of service discovery and environment variables defined in the kubernetes json). If that's not an option, you can also get a bash from your running container: 
<br>
<pre class="code"><code> docker exec -i -t&nbsp;c329f2e0f63b bash </code></pre> Just replace the container id, with the real one from the ps command. That's about it. Now you can totally start over. I'm going to walk you through the consoles a bit.
<br>
<br><b>Access The OpenShift Console</b>
<br>
 First things first. You can spot your application on the OpenShift console. http://vagrant.f8:8443 brings you to the OpenShift console. Select the "default" space and see the Docker Registry, some elasticsearch inststances, some other and finally your instance:
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="openshift-wildfly.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="96" src="openshift-wildfly.PNG" width="320"></a>
</div>
<br>
 You can also browse the individual pods and services. More about this maybe in a later blogpost
<br>
<br><b>The Fabric8 Console</b>
<br>
 The one magical thing, we're really interested in is the Fabric8 Console. http://fabric8.vagrant.f8/ brings you there and the "Kubernetes" tab displays all the running apps for you. This also includes you own application:
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="wildfly-on-fabric.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="50" src="wildfly-on-fabric.PNG" width="320"></a>
</div> As you can see in this screenshot, I already scaled the app from one (default) to two pods. Clicking on the little pod icon on the far right (not in this screenshot) let's you adjust the number of pods running. If you click on the "diagram" view, you see a complete overview of your infrastructure:
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="fabric8-console-scaled.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="172" src="fabric8-console-scaled.png" width="320"></a>
</div> There's a lot more to explore and I am going to show you more in subsequent blog-posts. Now, that we got everything up and running, this will be even more entertaining. Let me know, what you want to read about in particular.
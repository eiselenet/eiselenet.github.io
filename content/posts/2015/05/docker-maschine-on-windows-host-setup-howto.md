---
title: "Docker Machine on Windows - How To Setup You Hosts"
date: 2015-05-06 10:00:00 +0000
layout: post
tags: ["Windows", "Docker", "Machine"]
slug: "2015-05-06-docker-maschine-on-windows-host-setup-howto"
url: /2015/05/docker-maschine-on-windows-host-setup-howto.html
---

I've been playing around with Docker a lot lately. Many reasons for that, one for sure is, that I love to play around with latest technology and even help out to build a demo or two or a lab. The main difference, between what everybody else of my coworkers is doing is, that I run my setup on Windows. Like most of the middleware developers out there. So, If you followed Arun's blog about "<a href="" target="_blank">Docker Machine to Setup Docker Host</a>" you might have tried to make this work on windows already. Here is the ultimate short how-to guide on using Docker Machine to administrate and spin up your Docker hosts.
<br>
<br><b>Docker&nbsp;Machine</b>
<br>
 Machine lets you create Docker hosts on your computer, on cloud providers, and inside your own data center. It creates servers, installs Docker on them, then configures the Docker client to talk to them. You basically don't have to have anything installed on your machine prior to this. Which is a hell lot easier, than having to manually&nbsp;<a href="http://blog.eisele.net/2014/12/docker-for-java-ee-developers-on-windows-with-maven.html" target="_blank">install boot2docker</a> before. So, let's try this out.
<br>
<br>
 You want to have at least one thing in place before starting with anything Docker or Machine. Go and get&nbsp;<a href="" target="_blank">Git for Windows</a>&nbsp;(aka msysgit). It has all kinds of helpful unix tools in his belly, which you need anyway.
<br>
<br><b>Prerequisites - The One For All Solution</b>
<br>
 The first is to install the windows boot2docker distribution&nbsp;<a href="http://blog.eisele.net/2014/12/docker-for-java-ee-developers-on-windows-with-maven.html" target="_blank">which I showed in an earlier blog</a>. It contains the following bits configured and ready for you to use:
<br>
 - VirtualBox
<br>
 - Docker Windows Client
<br>
<br><b>Prerequisites- The Bits And Pieces</b>
<br>
 I dislike the boot2docker installer for a variety of reasons. Mostly, because I want to know what exactly is going on on my machine. So I played around a bit and here is the bits and pieces installer if you decide against the one-for-all solution. Start with the virtualization solution. We need something like that on Windows, because it just can't run Linux and this is what Docker is based on. At least for now. So, get <a href="https://virtualbox.org/wiki/Downloads" target="_blank">VirtualBox</a> and ensure that version 4.3.18 is correctly installed on your system (<a href="http://download.virtualbox.org/virtualbox/4.3.18/VirtualBox-4.3.18-96516-Win.exe" target="_blank">VirtualBox-4.3.18-96516-Win.exe</a>, 105 MB). WARNING: There is a strange issue, when you run Windows itself in Virtualbox. You might&nbsp;<a href="https://github.com/docker/machine/issues/742" target="_blank">run into an issue with starting</a> the host.
<br>
 And while you're at it, go and get the Docker Windows Client. &nbsp;The other is to grab the final from the test servers as a direct download (<a href="http://test.docker.com.s3.amazonaws.com/builds/Windows/x86_64/docker-1.6.0.exe" target="_blank">docker-1.6.0.exe</a>, x86_64, 7.5MB). Rename to "docker" and put it into a folder of your choice (I assume it will be c:\docker\. Now you also need to download Docker Machine, which is another single executable (<a href="https://github.com/docker/machine/releases/download/v0.2.0/docker-machine_windows-amd64.exe" target="_blank">docker-machine_windows-amd64.exe</a>, 11.5MB). Rename to "docker-machine" and put it into the same folder. Now add this folder to your PATH:
<br>
<pre class="code"><code>set PATH=%PATH%;C:\docker </code></pre> If you change your standard PATH environment variable, this might safe your from a lot of typing. That's it. Now you're ready to create your first Machine managed Docker Host.
<br>
<br><b>Create Your Docker Host With Machine</b>
<br>
 All you need is a simple command:
<br>
<pre class="code"><code>docker-machine create --driver virtualbox dev </code></pre> And the output should state:
<br>
<pre>←[34mINFO←[0m[0000] Creating SSH key... ←[34mINFO←[0m[0001] Creating VirtualBox VM... ←[34mINFO←[0m[0016] Starting VirtualBox VM... ←[34mINFO←[0m[0022] Waiting for VM to start... ←[34mINFO←[0m[0076] "dev" has been created and is now the active machine. ←[34mINFO←[0m[0076] To point your Docker client at it, run this in your shell: eval "$(docker-machine.exe env dev)" </pre> This means, you just created&nbsp;a Docker Host using the VirtualBox provider and the name “dev”. Now you need to find out on which IP address the host is running.
<br>
<pre class="code"><code>docker-machine ip 192.168.99.102 </code></pre> If you want to configure your environment variables, needed by the client more easy, just use the following command:
<br>
<pre class="code"><code>docker-machine env dev export DOCKER_TLS_VERIFY=1 export DOCKER_CERT_PATH="C:\\Users\\markus\\.docker\\machine\\machines\\dev" export DOCKER_HOST=tcp://192.168.99.102:2376 </code></pre> Which outputs the Linux version of environment variable definition. All you have to do is to change the "export" keyword to "set", remove the " and the double back-slashes and you are ready to go. 
<br>
<pre class="code"><code>C:\Users\markus\Downloads&gt;set DOCKER_TLS_VERIFY=1 C:\Users\markus\Downloads&gt;set DOCKER_CERT_PATH=C:\Users\markus\.docker\machine\machines\dev C:\Users\markus\Downloads&gt;set DOCKER_HOST=tcp://192.168.99.102:2376</code></pre>
<br><b>Time to test our Docker Client</b>
<br>
 And here we go now run WildFly on your freshly created host:
<br>
<pre class="code"><code> docker run -it -p 8080:8080 jboss/wildfly </code></pre> Watch the container being downloaded and check, that it is running by redirecting your browser to&nbsp;<a href="" target="_blank">http://192.168.99.102:8080/</a>.
<br>
<br>
 Congratulations on having setup your very first docker host with Maschine on Windows.
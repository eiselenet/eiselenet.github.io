---
title: "How To Use The Docker 1.6 Windows Client"
date: 2015-04-17 13:00:00 +0000
layout: post
tags: ["Windows", "Docker", "Boot2Docker"]
slug: "how-to-use-docker-16-windows-client"
link: "2015/04/how-to-use-docker-16-windows-client.html"
url: /2015/04/how-to-use-docker-16-windows-client.html
---

Newest <a href="" target="_blank">Docker 1.6 release was pushed</a> yesterday. Normally, no reason to go crazy, but this time I think, it's worth a blog. Because, it finally contains the first Docker Client for Windows. If you hated having to ssh into your instance and having to do everything via boot2docker: This is over now.
<br>
 You can download official distribution of Docker Client for Windows by either installing it from the <a href="http://chocolatey.org/packages/docker" target="_blank">Chocolatey</a> package manager (which I never tried before) or installing <a href="" target="_blank">Boot2Docker</a>, which provides a Docker-ready development environment inside a local virtual machine (VirtualBox). You can also simply <a href="http://docs.docker.com/installation/windows/#upgrading" target="_blank">upgrade your Docker VM</a> with boot2docker (stop || download || start).
<br>
<br><b>Installing Boot2Docker And The Windows Client</b>
<br>
 I went down the <a href="http://blog.eisele.net/2014/12/docker-for-java-ee-developers-on-windows-with-maven.html" target="_blank">road I did before and re-installed boot2docker</a> completely. A very simple and clear experience. Please refer to the <a href="" target="_blank">complete Windows installation instructions</a>, if you run into any errors.
<br>
 The Boot2Docker start shortcut initializes and starts your docker VM. Although you will be using Windows Docker client, the docker engine hosting the containers will still be running on Linux. Until the Docker engine for Windows is developed, you can launch only Linux containers from your Windows machine. If the VM is up and running, just open another command prompt and try the new Docker Windows client. Make sure you have initialized some environment variables first:
<br>
<pre class="code"><code> set DOCKER_HOST=tcp://&lt;IP_ADDRESS&gt;:2376 set DOCKER_CERT_PATH='C:\Users\%USERPROFILE%\.boot2docker\certs\boot2docker-vm' set DOCKER_TLS_VERIFY=1 </code></pre> Now just enter "docker" and you have successfully launched the client.
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="docker_windows_client.PNG" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" src="docker_windows_client.PNG" height="295" width="320"></a>
</div>
<br>
 This is it. If you want to make sure, everything is working, just try to run the hello-world example:
<br>
<pre class="code"><code> docker run hello-world </code></pre> And it's done. So, now you have the full user experience of the windows command prompt without having to go through boot2docker ever again. Just start your docker VM via VirtualBox and use the new windows client tooling. All this was mainly done with help from Microsoft.&nbsp;Ahmet Alp Balkan (<a href="https://twitter.com/ahmetalpbalkan" target="_blank">@ahmetalpbalkan</a>) was the main committer and he also <a href="http://azure.microsoft.com/blog/2015/04/16/docker-client-for-windows-is-now-available" target="_blank">blogged about the client </a>yesterday.
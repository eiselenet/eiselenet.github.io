---
title: "Throwing Light on GlassFish Webserver Plugins and Proxying"
date: 2012-01-10 11:26:00 +0000
layout: post
tags: ["glassfish", "apache", "webserver", "loadbalancing"]
slug: "throwing-light-on-glassfish-webserver"

url: /2012/01/throwing-light-on-glassfish-webserver.html
---

Inspired by an article done by Lincoln Baxter III about <a href="" target="_blank">running JBoss on port 80</a>, I decided to have a more detailed look at what's possible and necessary for GlassFish to utilize proxying and load-balancing.&nbsp; 
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG-20120101-00026.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="320" src="IMG-20120101-00026.jpg" width="239"></a>
</div> Let's look at the basics&nbsp;front up. First of all we have to separate two kinds of problems here. The one addressed by Lincoln which talks about general Unix/Linux security restriction which prevents non-root users from binding to&nbsp;privileged-ports (in this case port 80). The second one is about&nbsp;load balancing&nbsp;and failover which is needed in clustered environments. Both can be solved with some kind of proxying approaches and depending on your needs you will have a couple of options to solve it.
<br>
<br><b>Binding GlassFish to port 80 (HTTP)</b>
<br>
 There are occasions when it is convenient to allow non-root users to run services, binding to "privileged ports". There are several approaches to this problem.
<br>
<br><i>Using a firewall(iptables) to redirect packets</i>
<br>
 This technique configures the firewall to send all port 80 traffic to port 8080 (Normal GF Application Port). You can do the same thing for any other ports you need that are less than 1024.
<br>
<br><code>iptables -t nat -A PREROUTING -p tcp -m tcp --dport 80 -j REDIRECT --to-ports 8080&nbsp;</code>
<br><code>iptables -t nat -A PREROUTING -p udp -m udp --dport 80 -j REDIRECT --to-ports 8080&nbsp;</code>
<br>
<br><i>Using authbind</i>
<br>
 Authbind is an Open source system utility. The authbind software allows a program that would normally require superuser privileges to access privileged network services to run as a non-privileged user. authbind allows the system administrator to permit specific users and groups access to bind to TCP and UDP ports below 1024. Authbind is distributed with the Debian and Ubuntu Linux distributions. For all other distributions you would have to install it manually. By executing the following with an installed authbind
<br>
<br><code>touch /etc/authbind/byport/80&nbsp;</code>
<br><code>chown glassfish:glassfish /etc/authbind/byport/80&nbsp;</code>
<br><code>chmod 755 /etc/authbind/byport/80&nbsp;</code>
<br>
<br>
 you will enable the "glassfish" user to bind to port 80 on your maschine.
<br>
<br><i>Using Jsvc</i>
<br><a href="http://commons.apache.org/daemon/jsvc.html" target="_blank">Apache Commons Jsvc</a> allows the application (e.g. GlassFish) to perform some privileged operations as root (e.g. bind to a port &lt; 1024), and then switch identity to a non-privileged user. There two ways to use jsvc: via a Class that implements the Daemon interface or via calling a Class that has the required methods. Using this for GlassFish would mean to implement a Jsvc GlassFishDaemon Class which implements the needed Methods (init, start, stop, destroy) and run it via the
<br>
<br>
 &nbsp; <code>./jsvc -cp mygflauncher.jar GlassFishDaemon </code>
<br>
<br><i>Apache mod_proxy with ProxyPass</i>
<br>
 First step here is to have a running Apache which itself is already bound to port 80. Normally this is done via chroot environments or the sudo command. For a more detailed step-by-step guide look at <a href="http://ocpsoft.com/opensource/jboss-application-server-7-on-port-80-with-apache-httpd-proxypass" target="_blank">Lincoln's post</a>. This configuration allows remote servers to be mapped into the space of the local (Apache) server; the local server does not act as a proxy in the conventional sense, but appears to be a mirror of the remote server. The local server is often called a reverse proxy or gateway. All you need is the following directive, assuming that your Apache is running on port 80: 
<br>
<br><code> ProxyPass / http://localhost:8080/<br></code>
<br>
 For more details refer to the <a href="http://httpd.apache.org/docs/2.1/mod/mod_proxy.html#proxypass" target="_blank">Apache documentation</a>. 
<br>
<br><i>Apache mod_proxy with RewriteRules</i>
<br>
 If you are looking into more complex scenarios your are probably willing to redirect single contexts only. This is possible with the <a href="http://httpd.apache.org/docs/2.1/mod/mod_rewrite.html#rewriterule" target="_blank">RewriteRule </a>directive. It is the real rewriting workhorse. The directive can occur more than once, with each instance defining a single rewrite rule. The order in which these rules are defined is important - this is the order in which they will be applied at run-time. 
<br>
<br><code> ProxyPreserveHost on<br>
  RewriteEngine on<br><br>
  RewriteRule ^/exampleapp$ /exampleapp/ [R,L]<br>
  RewriteRule ^/exampleapp/(.*) http://localhost:8080/exampleapp/$1 [P,L]<br></code>
<br>
 Rewrite rule number one adds a eventually missing trailing slash and rule number two proxies your requests over to the Glassfish server. The ProxyPreserveHost directive makes sure that redirects work properly.
<br>
<br><b>Methods for Load-balancing and Failover</b>
<br>
 In order to scale for large amounts of load, it is typical to use multiple server instances in enterprise environments. Typically a load balancer is used in front of these cluster of instances. There are several different approaches to load-balancing and failover. Here are a couple of software based ways which I have seen in the wild.
<br>
<br><i>Oracle GlassFish LoadBalancer</i>
<br>
 Even if this is the first option you stumble across if you are looking around for load-balancing solutions, you have to keep in mind, that this is part of the commercial Oracle GlassFish Server and as such not free of charge. Even if it would be possible to use it with the OSS bits!
<br>
 The Loadbalancer Plug-In is shipped as a ZIP bundle that you install after installing and configuring GlassFish Server and your desired web server. The plug-in is installed by means of a graphical GlassFish Loadbalancer Configurator that helps you configure your web server and GlassFish Server to work together. In most cases, the GlassFish Loadbalancer Configurator automatically configures GlassFish Server and your web server, and no manual intervention is required. However, depending on your web server configuration, there may be some manual configuration steps that you need to perform after installing the Loadbalancer Plug-In. This is also the case for Apache 2.2.x. You simple have to ensure some pre- and post-installation steps which can be found in details in the <a href="http://docs.oracle.com/cd/E18930_01/html/821-2426/abdhg.html#scrolltoc" target="_blank">Oracle GlassFish server documentation</a>. If you are interested in the inner workings of the plugin, you can have a look at the <a href="" target="_blank">GlassFish LoadBalancer page</a> which summarizes all the details. I assume they haven't changed that much for the latest version. 
<br>
<br><i>Apache mod_jk</i>
<br>
 Beside the official GlassFish LoadBalancer plugin which is part of the Oracle GlassFish server the recommended solution to load-balancing for the open source GlassFish is by using the Apache mod_jk. The Apache Tomcat Connector mod_jk can be used to connect the web container with the Apache HTTP Server. The configuration is a little more complex and you find tons of information about the configuration in general. First important step is to install the mod_jk into your Apache server. For a detailed step by step guide look at the <a href="http://tomcat.apache.org/connectors-doc/webserver_howto/apache.html" target="_blank">official documentation</a>. A minimal configuration looks like the following. Add the following lines to your httpd.conf: 
<br>
<br><code> LoadModule jk_module /home/oracle/glassfish3/apache/modules/mod_jk.so <br>
  JkWorkersFile /home/oracle/glassfish3/apache/conf/glassfish-workers.properties<br><br>
  # where to put the log files for the jk module<br>
  JkLogFile /home/oracle/glassfish3/apache/logs/mod_jk.log<br><br>
  # the log level [debug|info|error]<br>
  JkLogLevel info<br><br>
  # log format<br>
  JkLogStampFormat "[%a %b %d %H:%M:%S %Y]"<br><br>
  # options to indicate to send SSL KEY SIZE<br>
  JkOptions +ForwardKeySize +ForwardURICompat -ForwardDirectories<br><br>
  # set the request log format<br>
  JkRequestLogFormat "%w %V %T"<br><br>
  # send requests that contain the following to glassfish<br>
  JkMount /one-instance/* glassfish-worker1 <br><br>
  # send requests to the cluster<br>
  JkMount /* loadbalancer<br></code>
<br>
<br>
 The JkWorkersFile contains all the needed information about your instances and the load-balancing.
<br>
<br><code> # define the workers<br>
  worker.list=glassfish-worker1,glassfish-worker2,loadbalancer<br><br>
  # set properties for the workers<br>
  worker.glassfish-worker1.type=ajp13<br>
  worker.glassfish-worker1.host=localhost # your GlassFish instance1 host<br>
  worker.glassfish-worker1.port=24849 # your GlassFish instance1 port<br>
  worker.glassfish-worker2.type=ajp13<br>
  worker.glassfish-worker2.host=localhost&nbsp; # your GlassFish instance2 host<br>
  worker.glassfish-worker2.port=24848&nbsp; # your GlassFish instance2 port<br>
  worker.loadbalancer.type=lb<br>
  worker.loadbalancer.balance_workers=glassfish-worker1,glassfish-worker2<br></code>
<br>
<br>
 For a detailed guide for the configuration of Apache mod_jk with GlassFish 3.1 look at the <a href="http://docs.oracle.com/cd/E18930_01/html/821-2416/gfaad.html" target="_blank">official documentation</a>&nbsp;or the <a href="http://middlewaremagic.com/weblogic/?p=7677" target="_blank">excellent article</a> from the middleware magic guys.
<br>
<br>
 Last but not least you have to enable the JK Listener with each of your http-listeners via the GlassFish admin console and you have to add a jvmRoute system property by either using the asadmin command or adding it via the GlassFish admin console:
<br>
<br><code> asadmin create-jvm-options "-DjvmRoute=worker1"<br></code>
<br><i>Apache mod_proxy_balancer</i>
<br>
 The Apache mod_proxy has a balancer extension called <a href="http://httpd.apache.org/docs/2.1/mod/mod_proxy_balancer.html" target="_blank">proxy_balancer_module</a>. This module is highly configuratble and could be adopted to many szenarios. You can adjust the load balancing algorithms and stickyness and many other things. At a minimum your configuration looks like this:
<br>
<br><code> ProxyPass / balancer://mycluster <br>
  &lt;Proxy balancer://mycluster&gt;<br>
  BalancerMember http://localhost:8080 route=route1<br>
  BalancerMember http://localhost:8080 route=route2<br>
  ProxySet stickysession=JSESSIONID<br>
  &lt;/Proxy&gt;<br></code>
<br>
 To make sure the session sticks you will have to set a JVM option, add the following JVM option to your Glassfish instances so they know they are running in an Apache cluster setting:
<br>
<br><code> asadmin create-jvm-options "-DjvmRoute=route1"<br></code>
<br>
<br>
 Note the jvmRoute needs to be the same as the route parameter mentioned in the BalancerMember entries (route1/route2).
<br>
<br><b>Which way to go? </b>
<br>
 If you have different roads to travel your in charge of picking the right one. This isn't easy in general as every solution has it's strengths and weaknesses. Generally speaking you can rely on simply forwarding or proxying in most of the cases. Starting with development or testing stages. For most of the production sites I know you wouldn't stand the traffic without a cluster. With clustering comes loadbalancing into play. If you can go with the most basic round robin schemes available with the easier solutions you still have a quite simple setup. If you are forced to control your load and need to adjust it to different hardware sizes you better go with the load-balancing offer contained in the Oracle GlassFish server. Even if I know a lot of sites running the simpler mod_proxy_balancer or mod_jk approaches without any problems. 
<br>
 I am happy to read about your experiences!
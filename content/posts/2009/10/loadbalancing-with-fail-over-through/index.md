---
title: "Loadbalancing with fail-over through Apache 2.2 and GlassFish v2.1.1"
date: 2009-10-29 11:33:00 +0000
layout: post
tags: ["v2.1.1", "proxy", "glassfish", "apache", "failover", "2.2", "loadbalancing"]
slug: "loadbalancing-with-fail-over-through"

url: /2009/10/loadbalancing-with-fail-over-through.html
---

One of the top posts on my blog is about <a href="http://www.eisele.net/blog/2009/01/installing-and-configuring-wls-apache.html">installing and configuring the BEA/Oracle Apache Plugins</a> for WebLogic Server. This inspired me to write a similar howto about how to achieve the same effect with Apache and GlassFish Server. To be honest, I recently came across some projects working with a clustered GlassFish domain behind an Apache Webserver. And there was definitely a need to understand, what is going on ;)
<br>
<br>
 Let's start.
<br>
<br><b>Prerequisites:</b>
<br>
 - GlassFish v2.1.1 
<br>
 - Apache Webserver 2.2.14
<br>
<br><b>Setup GlassFish Domain,Nodeagent,Cluster and Nodes:</b>
<br>
 1) Download and install GlassFish (java -Xmx256m -jar glassfish-installer-v2.1.1-b31g-windows.jar)
<br>
 2) Build a Cluster domain ( $GLASSFISH_ROOT/lib/ant/bin/ant -f setup-cluster.xml)
<br>
 3) Start GlassFish ($GLASSFISH_ROOT/bin/asadmin start-domain)
<br>
 4) Create a Nodeagent ($GLASSFISH_ROOT/bin/asadmin create-node-agent --host localhost --port 4848 nodeagent1
<br>
 5) Start the Nodeagent ($GLASSFISH_ROOT/bin/asadmin start-node-agent nodeagent1)
<br>
 6) Login to your admin server (http://localhost:4848/)
<br>
 7) Create a cluster (name it, whatever you like, I like <i>cluster1</i>)
<br>
 8) Create two nodes (same here, I like <i>instance1, instance2</i>)
<br>
 9) Start the cluster
<br>
 10) Add a property to the clusterwide jvm configuration (-DjvmRoute=$\{INSTANCE_ROUTE\} )
<br>
 11) Add an instance specific property to instance1 (INSTANCE_ROUTE = w1)
<br>
 12) Add an instance specific property to instance2 (INSTANCE_ROUTE = w2)
<br>
 13) deploy clusterjsp example app ($GLASSFISH_ROOT/glassfish/samples/quickstart/clusterjsp)
<br>
<br><b>Setup Apache, mod_proxy, mod_status and mod_proxy_balancer:</b>
<br>
 1) Download and install Apache HTTPD Server (e.g apache_2.2.14-win32-x86-no_ssl.msi for windows )
<br>
 2) Edit the httpd.conf:
<br>
 Change Listen Port to whatever you like. If you run on a single maschine, you should change it to anything but the already blocked GF ports. I use <i>Listen 6666</i> in this example
<br>
 3) Uncomment the modules:
<br>
 LoadModule proxy_module modules/mod_proxy.so
<br>
 LoadModule proxy_balancer_module modules/mod_proxy_balancer.so
<br>
 LoadModule proxy_http_module modules/mod_proxy_http.so
<br>
 LoadModule status_module modules/mod_status.so
<br>
 4) Add the rewriting configuration to the end of the file:
<br>
<br>
 # Enable the rewriting engine
<br>
 RewriteEngine On
<br>
<br>
 # Enable Reverse Proxy functions
<br>
 ProxyRequests Off
<br>
<br>
 # Preserve the proxy host-name
<br>
 ProxyPreserveHost On
<br>
<br>
 # Set access permissions to anyone
<br>
 &lt;Proxy *&gt;
<br>
 Order deny,allow
<br>
 Allow from all
<br>
 &lt;/Proxy&gt;
<br>
<br>
 # Enable balance-manager (http://localhost:port/balancer-manager)
<br>
 &lt;Location /balancer-manager&gt;
<br>
 SetHandler balancer-manager
<br>
 &lt;/Location&gt;
<br>
<br>
 # balancer configuration with two nodes
<br>
 &lt;Proxy balancer://mycluster&gt;
<br>
 BalancerMember http://127.0.0.1:38080 route=w1
<br>
 BalancerMember http://127.0.0.1:38081 route=w2
<br>
 ProxySet lbmethod=byrequests stickysession=JSESSIONID|jsessionid nofailover=Off
<br>
 &lt;/Proxy&gt;
<br>
<br>
 # define the proxy rules for your application
<br>
<br>
 ProxyPassMatch ^(.*\.do)$ balancer://mycluster$1
<br>
 ProxyPassMatch ^(.*\.jsp)$ balancer://mycluster$1
<br>
 ProxyPassMatch ^(.*\/clusterjsp\/.*)$ balancer://mycluster$1
<br>
<br>
 # define the reverse proxy rule
<br>
 ProxyPassReverse / balancer://mycluster/
<br>
<br>
 # Enable extended server status
<br>
 ExtendedStatus On
<br>
<br>
 # enable server-status location (http://localhost:port/server-status)
<br>
 &lt;Location /server-status&gt;
<br>
 SetHandler server-status
<br>
<br>
 # Set access permissions to anyone (not suitable for production !!!)
<br>
 Allow from all
<br>
 &lt;/Location&gt; 
<br>
<br>
 5) Test the configuration and start the apache instance
<br>
 6) Access your application (http://localhost:6666/clusterjsp/)
<br>
<br>
 Thats all. Finaly, this installation behaves like the oracle apache plugins. You can define dispatching rules für dynamic content or whole directories. 
<br>
<br>
 Not needed for the cluster funcionality is the server-status. But this is quite helpful if you like to know, what your apache is doing. Give it a shot and have a look at http://localhost:6666/server-status. Don't forget to disable this in production environment.
<br>
 Another great thing is the balancer-manager. You can define weight based routings and see the status of the server nodes and the LoadBalancer status in general.
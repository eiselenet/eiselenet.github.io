---
title: "JMS with JBoss A-MQ on OpenShift. Lessons learned about remote Clients and Encryption."
date: 2014-11-25 12:00:00 +0000
layout: post
tags: ["JMS", "Openshift", "wildfly", "ActiveMQ", "Jboss"]
slug: "jms-with-activemq-on-openshift-remote-clients-and-encryption"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2014/11/jms-with-activemq-on-openshift-remote-clients-and-encryption.html
---

OpenShift is the "open hybrid cloud application platform by Red Hat". It comes in different flavors and the most interesting part for most of the things you want to do is the public&nbsp;cloud application development and hosting platform "<a href="https://www.openshift.com/products/online" target="_blank">OpenShift Online</a>". You can easily try it out because&nbsp;using OpenShift Online in the cloud is free and it's easy. All it takes is an <a href="https://www.openshift.com/app/account/new" target="_blank">email address</a>. The free offering allows for up to three basic small gears and host up to three applications from a variety of different languages and frameworks. If you need more, you can upgrade your plan to a paid version. For more details look at the <a href="https://www.openshift.com/products/pricing" target="_blank">online feature comparison website</a>.
<br>
<br><b>JBoss A-MQ on OpenShift</b>
<br>
 The Java Message Service is an effective method for cross-system communication, even among non-Java applications. By basing itself on open source technologies and strong standards, RedHat OpenShift allows developers to easily move their JMS applications to the cloud or write new systems that leverage JMS messages with encrypted internet connectivity.
<br>
 This post will cover the means for using two major applications: <a href="https://developers.openshift.com/en/wildfly-overview.html" target="_blank">WildFly 8 </a>for hosting web applications, and <a href="https://github.com/jboss-fuse/amq-openshift-cartridge" target="_blank">JBoss A-MQ</a> for asynchronous messaging. Both applications can run on gears within the free tier of OpenShift.
<br>
<br><b>Creating an A-MQ Gear</b>
<br>
 By deploying A-MQ to the OpenShift cloud, your gear will receive several publicly accessible ports. Client systems can then use these remote ports to connect to your&nbsp;A-MQ&nbsp;service. The endpoints require&nbsp;encryption, so no JMS message will ever be sent in plain-text across the internet.
<br>
 The first step in creating your&nbsp;A-MQ&nbsp;gear is to clone the existing JBoss Fuse A-MQ&nbsp;cartridge. For those interested&nbsp;in&nbsp;cartridge management, you can view<a href="https://github.com/jboss-fuse/amq-openshift-cartridge" target="_blank"> full details on this cartridge</a>. (Note: If you are looking for an upstream cartridge with ActiveMQ, <a href="" target="_blank">take a look at this blog.</a>)
<br>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; color: #333333; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 13px; line-height: 1.42857143; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><code style="background-color: transparent; border-radius: 0px; box-sizing: border-box; color: inherit; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: inherit; padding: 0px; white-space: pre-wrap;">rhc create-app amq http://is.gd/Q5ihum</code></pre> Upon creating, the gear provides three important pieces of information:
<br>
 1. The administrative password that you will use to log in to JBoss Fuse, for managing&nbsp;A-MQ.
<br>
 2. A new public key that clients must have in order to communicate with&nbsp;A-MQ. This
<br>
 information looks like
<br>
 -----BEGIN CERTIFICATE-----
<br>
 …
<br>
 -----END CERTIFICATE-----
<br>
 3. A list of public ports&nbsp;A-MQ&nbsp;is using for remote connections.
<br>
<br><b>Managing the encryption on OpenShift</b>
<br>
 The difference between clients and your OpenShift gear is that OpenShift needs the private key. If you need to change the keys, the keystore file is FILENAME. If you change keys, clients must have the public key before they will trust it. If you change the keys, you must restart the gear. If you forgot to copy your certificate during gear creation of you changed the keystore and need to extract is, use the following commands:
<br>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; color: #333333; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 13px; line-height: 1.42857143; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><code style="background-color: transparent; border-radius: 0px; box-sizing: border-box; color: inherit; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: inherit; padding: 0px; white-space: pre-wrap;">keytool -list -keystore ~/jboss-amq/jboss-a-mq-6.1.0.redhat-378/etc/keystore.jks</code></pre>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; color: #333333; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 13px; line-height: 1.42857143; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><code style="background-color: transparent; border-radius: 0px; box-sizing: border-box; color: inherit; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: inherit; padding: 0px; white-space: pre-wrap;">keytool -exportcert -alias (whatever it says) -keystore -file openshiftamq.cer</code></pre> Download the openshiftamq.cer file using an SFTP client and configure clients.
<br>
<br><b>Managing the encryption on clients&nbsp;</b>
<br>
 1. Copy the text of your key into a file called amqpublic.cer. Copy each line, inclusive of the BEGIN and END lines.
<br>
 2. Import the public certificate into a trust store that your clients will use.
<br>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; color: #333333; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 13px; line-height: 1.42857143; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><code style="background-color: transparent; border-radius: 0px; box-sizing: border-box; color: inherit; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: inherit; padding: 0px; white-space: pre-wrap;">keytool -importcert -alias openshiftamq -file openshiftamq.cer openshiftamq.jks</code></pre> 3. Put the openshiftamq.jks file as a classpath resource of your application or somewhere memorable. You won’t need the .cer file anymore but can still keep it around.
<br>
 4. Within client code, configure this trust store to be used with&nbsp;A-MQ&nbsp;connections. If you do not do this step,&nbsp;clients will not trust the server.
<br>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; color: #333333; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 13px; line-height: 1.42857143; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><code style="background-color: transparent; border-radius: 0px; box-sizing: border-box; color: inherit; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: inherit; padding: 0px; white-space: pre-wrap;"> private ConnectionFactory connection(String url) \{ &nbsp; &nbsp; ActiveMQSslConnectionFactory connectionFactory = new ActiveMQSslConnectionFactory(url); &nbsp; &nbsp; try \{ &nbsp; &nbsp; &nbsp; &nbsp; connectionFactory.setTrustStore("openshiftamq.jks"); //or file if not in classpath root &nbsp; &nbsp; \} catch (Exception ex) \{ &nbsp; &nbsp; &nbsp; &nbsp; Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Unable to load trust store.", ex); &nbsp; &nbsp; \} &nbsp; &nbsp; connectionFactory.setTrustStorePassword("put your password here"); &nbsp; &nbsp; return connectionFactory; \}</code></pre>
<br><b>Remote communication from clients </b>
<br>
 One benefit of using the OpenShift Fuse&nbsp;A-MQ&nbsp;gear is that is exposes several external ports. As a result,&nbsp;your&nbsp;A-MQ&nbsp;service is available without requiring the rhc port-forward command. The URL for your&nbsp;A-MQ&nbsp;clients will look like this: 
<br>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; color: #333333; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 13px; line-height: 1.42857143; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><code style="background-color: transparent; border-radius: 0px; box-sizing: border-box; color: inherit; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: inherit; padding: 0px; white-space: pre-wrap;">ssl://gearname-YourDomain.rhcloud.com:PORT</code></pre>
<ul>
 <li>Gearname – the name of your gear within the administrative console.</li>
 <li>YourDomain – Your standard OpenShift domain.</li>
 <li>PORT – the numeric port number provided when you created the cartridge.</li>
</ul> Configure clients using the ConnectionFactory code from above. 
<br>
<br><b>Additional ActiveMQ Configurations in your OpenShift Gear</b>
<br>
 Many configuration options from a standard&nbsp;A-MQ&nbsp;instance are available within your OpenShift instance.&nbsp;The configuration file for this is
<br>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; color: #333333; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 13px; line-height: 1.42857143; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><code style="background-color: transparent; border-radius: 0px; box-sizing: border-box; color: inherit; font-family: Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: inherit; padding: 0px; white-space: pre-wrap;"> ~/jboss-amq/jboss-a-mq-6.1.0.redhat-78/etc/activemq.xml </code></pre>
<br>
 with a few caveats. Namely, you can change the protocol of a &lt;transportConnector /&gt; but must not change the IP or port. The ports are controlled by your OpenShift Gear and are the only ones actually allowed from external areas.
<br>
<br><b>Prevent accidental Gear idling</b>
<br>
 OpenShift is designed as a resource sharing system, and idle resources will essentially be put to sleep until accessed. JMS poses a particular problem on OpenShift in that if it is idle, connections will not function and new clients cannot connect.
<br>
 To prevent this behavior, automate a script that periodically interacts with the JBoss Fuse web console or always keep at least one client connected to your A-MQ.
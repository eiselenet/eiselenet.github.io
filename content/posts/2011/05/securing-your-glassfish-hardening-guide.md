---
title: "Securing your GlassFish. Hardening Guide."
date: 2011-05-24 06:37:00 +0000
layout: post
tags: ["glassfish", "hardening", "security"]
slug: "2011-05-24-securing-your-glassfish-hardening-guide"
url: /2011/05/securing-your-glassfish-hardening-guide.html
---

If you are seriously thinking about running a GlassFish in a production environment your are looking for some kind of information about securing it. &nbsp;Most basically you would do, what sounds right for your and start with a secure installation, think about firewalls and secure applications. And this basically is right. But have you ever thought about the why? I did. And further on I checked back with GlassFish and here is the ultimate hardening guide for your GlassFish installation. I don't have any special version in mind, so most of it should work beginning with v3.
<br>
<br><b>IT-Security Guidelines</b>
<br>
<table cellpadding="0" cellspacing="0" class="tr-caption-container" style="float: right; margin-left: 1em; text-align: right;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="gf-java-net.png" imageanchor="1" style="clear: right; margin-bottom: 1em; margin-left: auto; margin-right: auto;"><img border="0" height="213" src="gf-java-net.png" width="320"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;">Picture <a href="http://creativecommons.org/licenses/by-nc/2.0/deed.en">CC BY-NC 2.0</a>, <a href="">annamagal</a></td>
  </tr>
 </tbody>
</table>Work and business processes are increasingly based on IT solutions. For this reason, the security and reliability of information and communications technology gains more and more importance. You simply have to look at what happened to the playstation network lately and you get a feeling about what IT-Security could mean to your business. I always thought of it as a comprehensive checklist of things to do to ensure a secure environment. Simple and boring stuff. And this is, why I was on the hunt for the most comprehensive list I can get to make my own GlassFish installations as secure as possible. Call me innocent and you are right. But: Hey, I'm a simple developer. Let's start with the basics. IT-Security is a lot more than simple checklists. It's a complete bunch of methods, processes, procedures, approaches and measures relating to information security. The most comprehensive standard work is the German Federal Office for Information Security (BSI) <a href="https://www.bsi.bund.de/EN/Topics/ITGrundschutz/itgrundschutz_node.html">IT-Grundschutz</a>. The aim of IT-Grundschutz is to achieve an appropriate security level for all types of information of an organisation. It uses a holistic approach to this process. Through proper application of well-proven technical, organisational, personnel, and infrastructural safeguards. I highly recommend reading a bit about this. What I quickly want to dive into are the so called "IT-Grundschutz Catalogues" as they contain the essential security safeguards which support a systematic approach to IT-Security. Don't get me wrong. This is the "simple" part of it. Dealing with standard threads and catalogues are basics. For a complete BSI solution overview get a coffee and talk to your security officer.
<br>
<br><b>The net and the fish</b>
<br>
 First important part to notice is that you have to take some time to consider your security needs. There are many screws to tighten and you should make sure to use the right ones. A picture came to my mind if I thought about this: The fishing net could be a symbol for your infrastructure. It keeps your GlassFishes in place and prevents them from shark attacks. And your GlassFish takes care of your Java application, running within it. So first and obvious thing to check is the infrastructure. A typical GlassFish does not swim in the wild. He's hidden behind a reverse proxy which itself sits inside a <a href="http://en.wikipedia.org/wiki/DMZ_(computing)">DMZ</a>. If I am talking about "system hardening" here, it's the most basic security process you should apply to your GlassFishes living in similar situations. Depending on your security needs, you should extend the list to your needs.
<br>
<br><b>Prerequisites</b>
<br>
 Hardening a single GlassFish instance is useless, if you are running it somewhere. The weakest point of your infrastructure defines your overall security level. So the first thing to check is, if your Hardware is up to date (yes, I'm talking about BIOS and stuff here) and if the operating system you are using is hardened at all. Don't forget about the network (Firewalls, Switches, and so on). If not. Stop reading and check back with the guys that are responsible for that.
<br>
<br><b>Thoughts about password strength</b>
<br>
 Password strength is a measure of the effectiveness of a password in resisting guessing and brute-force attacks. In its usual form, it estimates how many trials an attacker who does not have direct access to the password would need, on average, to guess it correctly. The strength of a password is a function of length, complexity, and unpredictability. (Source: <a href="http://en.wikipedia.org/wiki/Password_strength" target="_blank">wikipedia.org</a>) Whenever I talk about changing a password, think about unsing a strong password!
<br>
<br><b>Hardening basics with GlassFish</b>
<br>
 Before you start doing anything you should think about a security concept. Yes. The documentation stuff. You need to write down, what you are going to do and why. "Which resources am I protecting?" and "From whom am I protecting the resources?". Done? Fine. Let's start.
<br>
<br><i>Install an up-to-date and completely patched version of Java</i>
<br>
 There are many ways to do this. Get the latest bits, compare the checksums and apply all patches. 
<br>
<br><i>Setting up the environment</i>
<br>
 Very important from security point of view is not to run your Glassfish server as root. This means you need to create a user with restricted rights which you can use for running Glassfish. A good idea is to have a "gfish" user belonging to a "gfishadm" group. This group is the only one allowed to administrate the complete GlassFish installation including files. Note, that you are not going to run GlassFish on port 80 as a non-root user. But this is not too bad at all. As a principle for system hardening you could assume, that all "defaults" are bad. So you don't want to run it there anyway :) 
<br>
<br><i>Install an up-to-date and completely patched version of GlassFish</i>
<br>
 Don't start over with one of the old archives downloaded weeks ago. Visit <a href="glassfish.org" target="_blank">glassfish.org</a> or <a href="http://oracle.com/goto/glassfish" target="_blank">oracle.com/goto/glassfish</a> to grep the lates bits. Check the md5 hashes and make sure you really get the right ones. Check back with the <a href="http://www.oracle.com/technetwork/topics/security/alerts-086861.html#CriticalPatchUpdates" target="_blank">critical patch updates</a> website and make sure you have the latest security patches in place.
<br>
<br><i>Configure your ports</i>
<br>
 As I said before: Try to avoid default settings. Whatever ports are assigned with your basic installation; change them. Even if you find a lot of tools around to query system ports it's still considered good practice to shuffle the ports around. 
<br>
<br><i>Restrict access to the http/https ports</i>
<br>
 Check back with your network guys, to restrict access to your GlassFish server to the http/https port only. All other ports (admin-listener) should be blocked and accessible from the localhost or the cluster nodes only. You can rely on the external firewall product or configure your systems firewall (e.g. iptables) accordingly.
<br>
<br><i>Securing the admin console</i>
<br>
 If you decide not to protect the admin-listener on network level you need to enable the secure administration feature. The <a href="http://download.oracle.com/docs/cd/E18930_01/html/821-2435/gksbw.html#gkscn" target="_blank">secure administration feature</a> allows an administrator to secure all administrative communication between the domain administration server (DAS), any remote instances, and administration clients such as the asadmin utility, the administration console, and REST clients. In addition, secure administration helps to prevent DAS-to-DAS and instance-to-instance traffic, and carefully restricts administration-client-to-instance traffic.
<br>
<br><i>Change the master password</i>
<br>
 Glassfish uses the master password to protect the domain-encrypted files from unauthorized access, i.e. the certificate store which contains the certificates for https communication. Every asadmin action needs it to execute successfully. You have to decide if you put your installation in interactive or non-interactive way for the master password challenge. Running it as an autostart demon probably needs a saved master password. 
<br>
<br><i>Change the administration password</i>
<br>
 Same with the administration password. You also have the chance to put this into a password file for an "automatic login". Depending on your network configuration, your thread analysis (from whom do I protect the system) this could be ok. But I advise you to not use any automatic login features available.
<br>
<br><i>Aliasing Passwords</i>
<br>
 You should change your resource passwords to aliased ones. Use the <a href="http://download.oracle.com/docs/cd/E19798-01/821-1758/create-password-alias-1/index.html" target="_blank">asadmin create-password-alias</a> cmd to change clear-text passwords in domain.xml to $\{ALIAS=xxxx\} entries.
<br>
<br><i>A word about certificates</i>
<br>
 Normally you do not configure SSL certificates with your GlassFish instance. This is done by a reverse proxy and has several advantages. You have some lower load on your instance, you don't have to deal with configuring ssl and certificates. If you are directly terminating your ssl connections with the GlassFish, you have to change the keystore entries accordingly. And certainly you should change the keystore password.
<br>
<br><i>Hiding your identity</i>
<br>
 As many servers, GlassFish is a bit chatty. The response headers contain some information which should not be disclosed to the public to prevent targeted attacks.
<br>
<pre>X-Powered-By: Servlet/3.0, JSF/2.0 Server: GlassFish Server Open Source Edition 3.0.1 </pre>You can disable this by turning off the "XPowered By:" header with your http-listener and by adding a JVM-Option -Dproduct.name="". 
<br>
<br><i>Preventing System.gc()</i>
<br>
 Set an additional JVM Option -XX:-DisableExplicitGC . This will disable calls to System.gc() even if the JVM still performs garbage collection when necessary.
<br>
<br><b>Extended Hardening</b>
<br>
 If you have accomplished the most basic parts of the hardening, you could also start over and take care for the following points.
<br>
<br><i>Remove unused components/services</i>
<br>
 Minimize the GlassFish Server installation by <a href="http://download.oracle.com/docs/cd/E18930_01/html/821-2435/gkscq.html" target="_blank">removing components</a> that you are not using and do not intend to use. Every component you uninstall reduces the risk for somebody to break into. This needs a whole lot of knowledge about the stuff you are running with your GlassFish.
<br>
<br><i>Define working with update and pkg tools</i>
<br>
 Think about a small process for working with the update and pkg tools. You should disable the update checks for the admin console ( -Dcom.sun.enterprise.tools.admingui.NO_NETWORK=true) or probably completely remove it from your distribution. 
<br>
<br><i>Admin server and instances</i>
<br>
 Beginning with 3.1 you can have instances beside your admin server. It's considered best practice not to run any application on your admin server at all. So you should have a concept about running your applications on instances and clusters. You could also think about completely shutting down the admin server except for the duration you need it.
<br>
<br><i>Enable authentication and authorization auditing.</i>
<br>
 Auditing is the process of recording key security events in your GlassFish Server environment. You use audit modules to develop an audit trail of all authentication and authorization decisions. You should track all relevant events via the<a href="http://download.oracle.com/docs/cd/E18930_01/html/821-2435/ghgol.html" target="_blank"> Audit Logging features</a>.
<br>
<br><i>Check file integrity</i>
<br>
 There are some tools out there to check the integrity of your installation. Starting with simple rootkit hunters you also find some commercial solutions out there (e.g. tripwire). Think about using such a tool to protect the integrity of your installation.
<br>
<br><b>Bottom Line</b>
<br>
 This is a very unintuitive topic. You have to have very detailed knowledge about the product you are trying to secure and the complete infrastructure. If you are called to harden GlassFish make sure to understand the security needs and make an assessment about the risks you have to take care of. And it's a team play. A single hardened GlassFish is by far not enough.
<br>
<br><b>Links and&nbsp;Literature</b>
<br><a href="https://www.bsi.bund.de/ContentBSI/EN/Publications/BSI_standards/standards.html">BSI-Standards</a>
<br><a href="https://www.bsi.bund.de/SharedDocs/Downloads/EN/BSI/Grundschutz/guidelines/guidelines_pdf.pdf?__blob=publicationFile">IT Security Guidelines (PDF)</a>
<br><a href="http://download.oracle.com/docs/cd/E18930_01/html/821-2435/toc.html">Oracle GlassFish Server 3.1 Security Guide</a>
<br><a href="http://www.nabisoft.com/tutorials/glassfish/installing-glassfish-301-on-ubuntu">Installing Glassfish 3.0.1 on Ubuntu</a>
<br><a href="http://www.nabisoft.com/tutorials/glassfish/installing-glassfish-31-on-ubuntu">Installing Glassfish 3.1 on Ubuntu 10.04 LTS</a>
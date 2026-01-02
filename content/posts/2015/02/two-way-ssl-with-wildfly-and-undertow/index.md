---
title: "Two Way SSL With WildFly and Undertow - The Ultimate Howto"
date: 2015-02-24 15:35:00 +0000
layout: post
tags: [""]
slug: "two-way-ssl-with-wildfly-and-undertow"

url: /2015/02/two-way-ssl-with-wildfly-and-undertow.html
---

Security is and probably always has been one of my favorite topics to blog and talk about. And whenever I get a chance to make developer life easier, I'll give it a try. After my first post about how to configure SSL/TLS (aka One-Way SSL) with WildFly and Undertow I thought it might be a good idea to also guide you through how to configure the same with x.509 Client-side authentication (aka Two-Way SSL). I hope, you brought some time, because this is going to be a longer tutorial but it will cover:
<br>
<br>
<ul>
 <li>Custom Root CA with OpenSSL</li>
 <li>Key And Certificate Generation</li>
 <li>One-Way SSL setup for WildFly</li>
 <li>Client Side Key and Certificate generation with OpenSSL</li>
 <li>Two Way SSL setup for WildFly</li>
</ul>
<div>
 <br>
</div>
<div>
 <br>
</div>
<br>
<div>
 <br>
</div>
<div>
 <div>
  Preparing the Keys
 </div>
 <div>
  <br>
 </div>
 <div>
  0) directory structure
 </div>
 <div>
  pass phrase everywhere "secret"
 </div>
 <div>
  1) Generate the CA private key
 </div>
 <div>
  openssl genrsa –des3 –out keys/markusCA.pem 1024
 </div>
 <div>
  <br>
 </div>
 <div>
  2) Generate the CA root certificate
 </div>
 <div>
  req -new -key keys/markusCA.pem -x509 -days 1024 -subj /C=DE/OU=myfear/O=eisele.net/ST=Bavaria/CN=MarkusCA -out certs/MarkusCA.crt
 </div>
 <div>
  <br>
 </div>
 <div>
  3) Generate the server-identity keystore
 </div>
 <div>
  <br>
 </div>
 <div>
  keytool -genkey -alias WildFlyCert -keyalg RSA -keystore server.jks -keypass secret -storepass secret -dname "CN=localhost,OU=myfear,O=eisele.net,S=Bavaria,C=DE"
 </div>
 <div>
  <br>
 </div>
 <div>
  4) Generate the server-identity request file,
 </div>
 <div>
  <br>
 </div>
 <div>
  keytool -certreq -alias WildFlyCert -keystore server.jks -keypass secret -storepass secret -file requests/WildFlyCert.csr
 </div>
 <div>
  <br>
 </div>
 <div>
  5) Generate the server-identity certificate
 </div>
 <div>
  <br>
 </div>
 <div>
  openssl x509 -req -days 365 -in requests/WildFlyCert.csr -CA certs/MarkusCA.crt -CAkey keys/myCAK.pem -CAcreateserial -out certs/WildFlyCert.crt
 </div>
 <div>
  <br>
 </div>
 <div>
  6) Import the CA root certificate into the server-identity keystore
 </div>
 <div>
  keytool -import -alias MarkusRootCert -file certs/MarkusCA.crt -keystore server.jks
 </div>
 <div>
  <br>
 </div>
 <div>
  7) Import the server-identity certificate into the server-identity keystore:
 </div>
 <div>
  keytool -import -alias WildFlyCert -file certs/WildFlyCert.crt -keystore server.jks
 </div>
 <div>
  <br>
 </div>
 <div>
  8) Generate the Trusted CA Certificates keystore and import the CA root certificate into the trust store.
 </div>
 <div>
  <br>
 </div>
 <div>
  keytool -import -alias MarkusRootCert -file certs/MarkusCA.crt -keystore trusted.jks
 </div>
 <div>
  <br>
 </div>
 <div>
  _________________________________________________
 </div>
 <div>
  <br>
 </div>
 <div>
  Preparing the Browser / OS
 </div>
 <div>
  <br>
 </div>
 <div>
  9 Import Root CA into Trusted Root Certification Authorities
 </div>
 <div>
  <br>
 </div>
 <div>
  <br>
 </div>
 <div>
  Configuring WildFly for One-Way SSL
 </div>
 <div>
  <br>
 </div>
 <div>
  10) Copy server.jks file in the folder it is executed. Copy this to your WildFly config directory (%JBOSS_HOME%/standalone/configuration).
 </div>
 <div>
  <br>
 </div>
 <div>
  Configure:
 </div>
 <div>
  <br>
 </div>
 <div>
  &lt;management&gt;
 </div>
 <div>
  &nbsp; &nbsp; &nbsp; &nbsp; &lt;security-realms&gt;
 </div>
 <div>
  &lt;!-- ... --&gt;
 </div>
 <div>
  &nbsp;&lt;security-realm name="UndertowRealm"&gt;
 </div>
 <div>
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;server-identities&gt;
 </div>
 <div>
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;ssl&gt;
 </div>
 <div>
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;keystore path="server.jks" relative-to="jboss.server.config.dir" keystore-password="secret" alias="WildFlyCert" key-password="secret"/&gt;
 </div>
 <div>
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/ssl&gt;
 </div>
 <div>
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/server-identities&gt;
 </div>
 <div>
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/security-realm&gt;
 </div>
 <div>
  &lt;!-- ... --&gt;
 </div>
 <div>
  <br>
 </div>
 <div>
  &nbsp; &lt;subsystem xmlns="urn:jboss:domain:undertow:1.2"&gt;
 </div>
 <div>
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&lt;!-- ... --&gt;
 </div>
 <div>
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;server name="default-server"&gt;
 </div>
 <div>
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;!-- ... --&gt;
 </div>
 <div>
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;https-listener name="https" socket-binding="https" security-realm="UndertowRealm"/&gt;
 </div>
 <div>
  &lt;! -- ... --&gt;
 </div>
 <div>
  <br>
 </div>
 <div>
  <br>
 </div>
 <div>
  <br>
 </div>
 <div>
  <br>
 </div>
 <div>
  12:47:00,012 INFO &nbsp;[org.wildfly.extension.undertow] (MSC service thread 1-3) JBAS017519: Undertow HTTPS listener https listening on /12
 </div>
 <div>
  7.0.0.1:8443
 </div>
 <div>
  <br>
 </div>
 <div>
  <br>
 </div>
 <div>
  <br>
 </div>
 <div>
  Configuring WildFly for Two-Way SSL
 </div>
 <div>
  <br>
 </div>
 <div>
  Deploying web applications to use client-certificate Web server authentication requires configuration of the Web
 </div>
 <div>
  server to support two-way SSL. With one-way SSL only the identity of the server is verified; while in two-way SSL, the
 </div>
 <div>
  identities of both the server and the client are verified.
 </div>
 <div>
  <br>
 </div>
 <div>
  11.0)
 </div>
 <div>
  Explain Custom CA
 </div>
 <div>
  http://manmoahn-openssl-net.blogspot.de/2011/06/complete-guide-to-set-up-ca-using.html
 </div>
 <div>
  http://manmoahn-openssl-net.blogspot.de/2011/07/creating-serverclient-certificate-pair.html
 </div>
 <div>
  <br>
 </div>
 <div>
  echo "" &gt; /etc/pki/CA/index.txt
 </div>
 <div>
  echo "01" &gt; /etc/pki/CA/serial
 </div>
 <div>
  <br>
 </div>
 <div>
  11) Create Client Private Key
 </div>
 <div>
  <br>
 </div>
 <div>
  openssl genrsa -des3 -out keys/democlient.pem 1024
 </div>
 <div>
  <br>
 </div>
 <div>
  12) Generate the personal private-key x.509 request file (.csr) with a DN.
 </div>
 <div>
  <br>
 </div>
 <div>
  // openssl req -new -key keys/democlient.pem -days 365 -out requests/democlient-req.csr -multivalue-rdn -subj "/E=markus@eisele.net/CN=MARKUS/OU=Developer Advocate/O=JBoss/L=Grasbrunn/S=Bavaria/C=DE"
 </div>
 <div>
  <br>
 </div>
 <div>
  openssl req -new -key keys/democlient.pem -days 365 -out requests/democlient-req.csr
 </div>
 <div>
  <br>
 </div>
 <div>
  The organizationName field needed to be the same in the
 </div>
 <div>
  CA certificate (eisele.net) and the request (JBoss)
 </div>
 <div>
  <br>
 </div>
 <div>
  <br>
 </div>
 <div>
  CN=USERNAME
 </div>
 <div>
  <br>
 </div>
 <div>
  Common Name (eg, your name or your server's hostname) []:Markus Eisele
 </div>
 <div>
  <br>
 </div>
 <div>
  13) Sign the certificate with the MarkusCA certificate
 </div>
 <div>
  <br>
 </div>
 <div>
  openssl ca -in requests/democlient-req.csr -out certs/democlient.crt -notext -cert certs/MarkusCA.crt -keyfile keys/markusCA.pem
 </div>
 <div>
  <br>
 </div>
 <div>
  <br>
 </div>
 <div>
  //openssl x509 -req -in requests/democlient-req.csr &nbsp;-CA certs/MarkusCA.crt -CAkey keys/markusCA.pem -out certs/democlient.crt
 </div>
 <div>
  <br>
 </div>
 <div>
  14) Combine the public and private keys into PKCS12 format.
 </div>
 <div>
  <br>
 </div>
 <div>
  openssl pkcs12 -export -in certs/democlient.crt -inkey keys/democlient.pem -out certs/democlient.p12
 </div>
 <div>
  <br>
 </div>
 <div>
  15) Importing the Personal Certificate into Your Browser
 </div>
 <div>
  <br>
 </div>
 <div>
  16)&nbsp;
 </div>
 <div>
  <br>
 </div>
 <div>
  # sample app-roles.properties file
 </div>
 <div>
  markuseisele=ADMIN
 </div>
 <div>
  <br>
 </div>
 <div>
  <br>
 </div>
 <div>
  17) Add&nbsp;
 </div>
 <div>
  <span class="Apple-tab-span" style="white-space: pre;"> </span> &lt;authentication&gt;
 </div>
 <div>
  &nbsp; &nbsp;
 </div>
 <div>
  &nbsp;&lt;truststore path="trusted.jks" relative-to="jboss.server.config.dir" keystore-password="secret" /&gt;
 </div>
 <div>
  &nbsp; &nbsp;
 </div>
 <div>
  &nbsp;&lt;!-- &lt;local default-user="$local"/&gt; --&gt;
 </div>
 <div>
  &nbsp;&nbsp;
 </div>
 <div>
  &nbsp; &lt;properties path="cert-role-mapping.properties" relative-to="jboss.server.config.dir"/&gt;
 </div>
 <div>
  &nbsp;
 </div>
 <div>
  &nbsp;&lt;/authentication&gt;
 </div>
 <div>
  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
 </div>
 <div>
  &nbsp; &nbsp;&lt;/security-realm&gt;
 </div>
 <div>
  <br>
 </div>
 <div>
  18) Add new Security Domain
 </div>
 <div>
  &nbsp;&lt;security-domain name="clientCertSecurityDomain" cache-type="default"&gt;
 </div>
 <div>
  <span class="Apple-tab-span" style="white-space: pre;"> </span>&lt;authentication&gt;
 </div>
 <div>
  <span class="Apple-tab-span" style="white-space: pre;"> </span>&lt;login-module code="Certificate" flag="required" /&gt;
 </div>
 <div>
  <span class="Apple-tab-span" style="white-space: pre;"> </span>&lt;login-module code="CertificateRoles" flag="required"&gt;
 </div>
 <div>
  <span class="Apple-tab-span" style="white-space: pre;"> </span>&lt;!-- &lt;module-option name="verifier" value="org.jboss.security.auth.certs.AnyCertVerifier"/&gt;
 </div>
 <div>
  <span class="Apple-tab-span" style="white-space: pre;"> </span>&lt;module-option name="securityDomain" value="clientCertSecurityDomain"/&gt; --&gt;
 </div>
 <div>
  <span class="Apple-tab-span" style="white-space: pre;"> </span>&lt;module-option name="rolesProperties" value="file:$\{jboss.server.config.dir\}/cert-role-mapping.properties"/&gt;
 </div>
 <div>
  <span class="Apple-tab-span" style="white-space: pre;"> </span>&lt;/login-module&gt;
 </div>
 <div>
  <span class="Apple-tab-span" style="white-space: pre;"> </span>&lt;/authentication&gt;
 </div>
 <div>
  <span class="Apple-tab-span" style="white-space: pre;"> </span>&lt;jsse keystore-password="secret" keystore-url="file:$\{jboss.server.config.dir\}/server.jks" truststore-password="secret" truststore-url="file:$\{jboss.server.config.dir\}/trusted.jks" client-auth="true"/&gt;
 </div>
 <div>
  <span class="Apple-tab-span" style="white-space: pre;"> </span>&lt;/security-domain&gt;
 </div>
 <div>
  <br>
 </div>
 <div>
  19) Change https listener
 </div>
 <div>
  &nbsp;&lt;https-listener name="https" socket-binding="https" security-realm="UndertowRealm" verify-client="REQUESTED"/&gt;
 </div>
</div>
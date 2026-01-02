---
title: "WebLogic Server SSL (https/t3s) and Java Web Start"
date: 2010-06-15 10:02:00 +0000
layout: post
tags: ["weblogic", "https", "ssl", "t3s"]
slug: "weblogic-server-ssl-httpst3s-and-java"
link: "2010/06/weblogic-server-ssl-httpst3s-and-java.html"
url: /2010/06/weblogic-server-ssl-httpst3s-and-java.html
---

This is a post about a quite old topic. So it is written to be more of a personal reminder than an hype howto. I came across some of those weird security requirements lately and was forced to digg into SSL with WebLogic server again. 
<br>
 First of all: All those security guys are awesome! I love you all! BUT .. please accept people not having the same level of insanity ;)
<br>
<br>
 The concrete problem was a Java Web Start application (mature one) running t3 as the communication protocol. It should now be secured by using ssl and the corresponding t3s protocol. After some seconds I came to the conclusion, that this sould be possible within minutes. It was not. Here is the why and the 30 minutes solution for you, if you come across the same problem.
<br>
<br><b>The basics</b>
<br>
 Secure Socket Layer (SSL) is a protocol for providing a secure channel of communication between two computers. It makes provisions for data integrity, confidentiality and authentication. Authentication of the server - by the client - provides an assurance of the fact that the traffic has not been diverted to an attacking server. Mutual authentication requires the client to provide credentials to the server over the secure channel. If I am talking about SSL in the following parts, I always refer to simple one-way SSL connections. Meaning, that the certificate receives a trusted certificate from the server and encrypts the data with it. In more detail, this is happening the following way:
<br>
<br>
 The client initiates the SSL connection by requesting a channel through the use of a ClientHello handshake message. This message contains the Cipher Suites that are configured to be supported by the client side and are available for the server to choose in creating the most secure channel configuration possible between the two machines. It also contains a random number to be used by the server in the generation of keys - this random number is a result of the configured or default RNG on a given platform.
<br>
 The server side, in turn, responds with a ServerHello that includes the Cipher Suite selected by the server as the most appropriately secure suite for the channel. If a suitable cipher suite could not be selected from the list of supported suites provided by the client - the request for an SSL connection is denied by the server. It also includes a random number and the certificate that is to be used for authenticating the server to the client. This certificate must be validated by the client in order for it to be trusted as representing the identity asserted by it.
<br>
<br>
 As you might have guessed, it all starts with a certificate. Digital certificates are electronic files that are used to identify people and resources over
<br>
 networks. Certificates are issued by a Certification Authority (CA). The role of the
<br>
 CA is to validate the certificate holder’s identity and to "sign" the certificate so that it cannot be tampered with. The CA itself does this using it's own certificate. Public and well know issuer like Thawte, Verisign, Entrust and many more publish their Root CA (public keys) and you will most likely find them in your browser already. 
<br>
<br><b>Setting up SSL</b>
<br>
 Obtain an identity (private key and digital certificates) and trust (certificates of trusted certificate authorities) for WebLogic Server. You can 
<br>
 - use the digital certificates, private keys, and trusted CA certificates provided by the WebLogic Server (DemoKeys)
<br>
 - use the CertGen utility from WebLogic
<br>
 - use Java's keytool utility
<br>
 - or get an identity from any of the reputable vendors mentioned above
<br>
<br>
 For simplicity and cost, we will stick to a self generated identity using Java's keytool. To make this as easy as possible, we start with a blank new keystore and generate a keypare:
<br><code><br>
  keytool -genkeypair -keyalg RSA -keysize 1024 -dname "cn=localhost, ou=markus, o=eisele.net, c=DE" -alias eiselenet -keypass somepass -keystore x:\path\to\keystore\mystore -storepass somepass -validity 365<br></code>
<br>
 According to the documentation, this command generates a new keypair with the alias "eiselenet" and stores it in the x:\path\to\keystore\mystore. We are using a RSA keypair with a size of 1024. The default (DSA) is not supported by WebLogic. If the store does not exist, it will create it. This keypair is valid for 365 days. It's recommended to have a shorter validity for testing proposes.
<br>
<br>
 Next is to export the public key part. We will need this later for establishing the trust in the client jvm.
<br><code><br>
  keytool -export -rfc -alias eiselenet -storepass somepass -keypass somepass -keystore x:\path\to\keystore\mystore -file exported-eiselenet.cer<br></code>
<br>
 If you are going to have this keypair singned by an official vendor you need to issue a Certificate Signing Request (CSR). If not, skipp this step. The following command will generate a Certificate Signing Request (CSR), using the PKCS#10 format. A CSR is intended to be sent to a certificate authority (CA). The CA will authenticate the certificate requestor (usually off-line) and will return a certificate or certificate chain, used to replace the existing certificate chain (which initially consists of a self-signed certificate) in the keystore. 
<br><code><br>
  keytool -certreq -v -alias eiselenet -file csr-for-eiselenet.pem -keypass somepass -storepass somepass -keystore x:\path\to\keystore\mystore<br></code>
<br>
 Next is to configure your WebLogic server to use the newly created certificate and store. You need a WebLogic domain for this :) Create one, if not already there and fire it up. Visit your administration console (http://localhost:7001/) and find your server settings. 
<br>
<br>
 In the "general" tab enable the "SSL Listen Port Enabled" selectbox and enter a corresponding port (SSL Listen Port: e.g. 7002).
<br>
<br>
 Select the tab "keystores" and select "Custom Identity and Java Standard Trust". Now enter the following information:
<br><code><br>
  Custom Identity Keystore: x:\path\to\keystore\mystore<br>
  Custom Identity Keystore Type: jks<br>
  Custom Identity Keystore Passphrase: somepass <br>
  Confirm Custom Identity Keystore Passphrase: somepass <br></code>
<br>
 Move on to the SSL tab and enter the following:
<br><code><br>
  Private Key Alias: eiselenet <br>
  Private Key Passphrase: somepass <br>
  Confirm Private Key Passphrase: somepass <br></code>
<br>
 Now you are done. Restart your WebLogic. If you now access a deployment through the newly configured https port (https://localhost:7002/ssl/test.html) you will get a browser warning, telling you that "The site's security certificate is not trusted!". Depending on your browser you can have a look at the certificate. If you view it, you can see, that it's our newly generated certificate showing all information. Not having this one signed by a trusted root CA causes this error. If you trust yourself you can simply proceed or even import the certificate into your browsers trust keystore to prevent future warnings.
<br>
<br><b>The t3s parts</b>
<br>
 Basic ssl setup is now done. Next is to make a frist try with t3s. I assue you have any kind of "simple sample" running (Eclipse, Main Class, weblogic.jar). 
<br>
 For this example I have written a simple Hello EJB.
<br><code><br>
  @Remote<br>
  public interface HelloBeanRemote \{<br>
  public String sayHello(String name);<br>
  \}<br></code>
<br>
 I am not going to use the application client in this example! Therefore you have to provide EJB 2.x style interfaces, too! (@RemoteHome(HelloBeanRemoteHome.class))
<br>
 Next is a simple client. First I tried a standalone class.
<br><code><br>
  public class Startup \{<br>
  public static void main(String[] args) throws Exception \{<br>
  Properties props = new Properties();<br>
  props.setProperty("java.naming.factory.initial",<br>
  "weblogic.jndi.WLInitialContextFactory");<br>
  props.setProperty("java.naming.provider.url", <br>
  "t3s://localhost:7002");<br>
  Context ctx = new InitialContext(props);<br><br>
  de.msg.test.HelloBeanRemoteHome home = <br>
  (de.msg.test.HelloBeanRemoteHome) ctx<br>
  .lookup("HelloBean#de.msg.test.HelloBeanRemoteHome");<br><br>
  HelloBeanRemoteObject hello = home.create();<br>
  hello.sayHello("markus");<br>
  \}<br></code>
<br>
 If you are running your code without any further steps, you will run into a
<br><code><br>
  javax.net.ssl.SSLKeyException: [Security:090542]Certificate chain received from localhost - 127.0.0.1 was not trusted causing SSL handshake failure. Check the certificate chain to determine if it should be trusted or not. If it should be trusted, then update the client trusted CA configuration to trust the CA certificate that signed the peer certificate chain. If you are connecting to a WLS server that is using demo certificates (the default WLS server behavior), and you want this client to trust demo certificates, then specify -Dweblogic.security.TrustKeyStore=DemoTrust on the command line for this client.; No available router to destination]<br></code>
<br>
 Btw: This exception could mean anything and nothing. You should enable SSL debugging within your weblogic server (Server, Debug, weblogic, ssl) and change your Logging level to DEBUG.
<br>
<br>
 In this case, it's obvious. The client is not trusting the self signed certificate and rejects the handshake. Now you have to make you client trust in your own certificate.
<br><code><br>
  keytool -import -alias eiselenet -file exported-eiselenet.cer -keystore x:\path\to\jre6\lib\security\cacerts" -storepass changeit <br></code>
<br>
 This command shows the certificate basics and asks you if you are willing to import it.
<br><code><br>
  Owner: CN=localhost, OU=markus, O=eisele.net, C=DE<br>
  Issuer: CN=localhost, OU=markus, O=eisele.net, C=DE<br>
  Serial: 4c17121c<br>
  Valid from: Tue Jun 15 07:39:40 CEST 2010 bis: Wed Jun 15 07:39:40 CEST 2011<br>
  Fingerprint:<br>
  MD5: BF:F7:A7:40:7E:35:66:25:3D:48:E9:40:A5:60:17:1A<br>
  SHA1: 08:05:90:63:52:6F:01:34:68:AC:F1:EB:42:49:64:C4:2D:B1:6B:BF<br>
  Signer-Algorithm: SHA1withRSA<br>
  Version: 3<br>
  Trust this certificate? [No]: yes<br>
  Certificate was added to the keystore.<br></code>
<br>
 If this is done, you can give it another try. And it will work. One pitfall: If you are running more than one JDK/JRE, find the time to figure out, which one is running. If you are importing the certificate to the wrong one, you keep searching for some time to track down the error.
<br>
<br><b>The Web Start parts</b>
<br>
 If the standalone version is running, you should give the web start version a try. You do not need a fancy UI if you don't want to. Just create a simple .jnlp file and use your standalone test class as the application-desc main-class. And don't forget to add:
<br><code><br>
  &lt;security&gt; &lt;all-permissions&gt; &lt;/security&gt;<br></code>
<br>
 If you open the java console, you will see output and errors there. Should be enough for this test. 
<br>
 Going to have a t3 client in place, you are in need of the full WebLogic client jar. You can get one following these simple steps:
<br>
 Change directories to the server/lib directory. (cd WL_HOME/server/lib) and use the following command to create wlfullclient.jar in the server/lib directory:
<br><code><br>
  java -jar wljarbuilder.jar<br></code>
<br>
 Second one you are in need of is the WL_HOME/server/lib/wlcipher.jar. Copy both to your Web Start client directory and sign them. To make this easy, I am going to use the same certificate we used already for the ssl connection. But you can use any other one, too. Just make shure, you sign all jars with the same certificate.
<br><code><br>
  jarsigner -keystore x:\path\to\keystore\mystore -storepass somepass -keypass somepass wlfullclient.jar eiselenet<br>
  jarsigner -keystore x:\path\to\keystore\mystore -storepass somepass -keypass somepass wlcipher.jar eiselenet<br></code>
<br>
 Package all togeter (helloClient.jar, wlfullclient.jar, wlciper.jar, ejbclient.jar) and run your Web Start application.
<br>
<br>
 And here we are: you run into the next exception. 
<br><code><br>
  java.lang.AssertionError: Failed to generate class for de.msg.test.HelloBean_gbyfgg_HomeImpl_1033_WLStub<br>
  ...<br>
  Caused by: java.security.AccessControlException: access denied (java.lang.RuntimePermission getClassLoader)<br>
  at java.security.AccessControlContext.checkPermission(Unknown Source)<br></code>
<br>
 This is a more or less known bug. The: &lt;security&gt; &lt;all-permissions&gt; &lt;/security&gt; tag in conjunction with signed jar files only sets the permissions for code loaded by the JNLPClassLoader from the jars specified in the jnlp file. Therefore you have to edit jre\lib\security\java.policy files on client to grant the following:
<br><code><br>
  permission java.lang.RuntimePermission "getClassLoader";<br></code>
<br>
 This was the last hurdle. Now you are done and finaly got the server response expected.
<br>
<br>
<br><b>Links and Readings</b>
<br><a href="http://blogs.oracle.com/WebLogicServer/2009/10/ssl_troubleshooting_and_debugg.html">SSL Troubleshooting and Debugging</a>
<br><a href="http://download.oracle.com/docs/cd/E15051_01/wls/docs103//secmanage/ssl.html">WLS Doc Configuring SSL </a>
<br><a href="http://java.sun.com/javase/6/docs/technotes/tools/windows/keytool.html">keytool - Key and Certificate Management Tool</a>
<br><a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4809366">Bug ID: 4809366 all-permissions does not grant permission for getClassLoader()</a>
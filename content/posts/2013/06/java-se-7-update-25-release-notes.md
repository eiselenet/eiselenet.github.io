---
title: "Java SE 7 Update 25 - Release-Notes explained."
date: 2013-06-19 06:41:00 +0000
layout: post
tags: ["security", "java", "7u25"]
slug: "2013-06-19-java-se-7-update-25-release-notes"
url: /2013/06/java-se-7-update-25-release-notes.html
---

Yesterday was CPU day. Oracle released the <a href="http://www.oracle.com/technetwork/java/javase/downloads/index.html" target="_blank">Java SE update 25</a> with the <a href="http://www.oracle.com/technetwork/topics/security/javacpujun2013-1899847.html" target="_blank">June Java Critical Patch Update</a>. After the last <a href="http://blog.eisele.net/2013/04/java-7-update-21-security-improvements.html" target="_blank">major update in April</a> this is the last one which does not fit into the Oracle Critical Patch Update schedule along with all other Oracle products. <a href="https://blogs.oracle.com/security/entry/maintaining_the_security_worthiness_of" target="_blank">Starting in October 2013</a>, Java security fixes will follow the four annual security release cycle. But don't panic:&nbsp;Oracle will retain the ability to issue emergency “out of band” security fixes through the <a href="http://www.oracle.com/us/support/assurance/remediation/index.html" target="_blank">Security Alert program</a>. Further on this is the first CPU which will not publicly update the Java SE 6 family. If you need an update on that JRE Family you need to have a <a href="http://www.oracle.com/us/technologies/java/standard-edition/support/overview/index.html" target="_blank">Oracle's Java SE Support</a>. Going down this road brings you Java SE 6u51.
<br>
<br><b>The Management Summary</b>
<br>
 This release has been announced some time back already and addresses 40 vulnerabilities with fixes across Java SE products. 37 of these vulnerabilities may be remotely exploitable without authentication, i.e., may be exploited over a network without the need for a username and password. &nbsp;Four of them are applicable to server deployments (CVE-2013-2451,CVE-2013-2457, CVE-2013-2407, CVE-2013-2461). A complete list is shown in the <a href="http://www.oracle.com/technetwork/topics/security/javacpujun2013-1899847.html#AppendixJAVA" target="_blank">Oracle Java SE Risk Matrix</a>. The expiration date for JRE 7u25 is November 15, 2013. After that date the clients start showing warnings about a too old JRE.
<br>
<br><b>I'm an End-User. Whats new?</b>
<br>
<table cellpadding="0" cellspacing="0" class="tr-caption-container" style="float: right; margin-left: 1em; text-align: right;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="http://docs.oracle.com/javase/7/docs/technotes/guides/jweb/jcp.html#advanced" imageanchor="1" style="clear: right; margin-bottom: 1em; margin-left: auto; margin-right: auto;" target="_blank"><img border="0" height="320" src="Advanced_3.jpg" width="308"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;">(Source: <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/jweb/jcp.html#advanced" target="_blank">Oracle Docs</a>)</td>
  </tr>
 </tbody>
</table> Not very much this time. Two little improvements which should not impact you too much.
<br>
 Before signed Java applets and Java Web Start applications are run, the signing certificate is checked to ensure that it has not been revoked. Advanced options in the Java Control Panel (JCP) can be set to manage the checking process. These online checks might not work at all in enterprise environments or have an impact on startup performance. To avoid both it is now possible to disable it. You should carefully make this decision and only do it in managed environments because it decreases the overall security protection mechanism.
<br>
<br>
<table cellpadding="0" cellspacing="0" class="tr-caption-container" style="float: right; margin-left: 1em; text-align: right;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="http://java.com/en/download/help/appsecuritydialogs.xml" imageanchor="1" style="clear: right; margin-bottom: 1em; margin-left: auto; margin-right: auto;" target="_blank"><img border="0" height="171" src="unsigned_cert.jpg" width="320"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;">(Source: <a href="http://java.com/en/download/help/appsecuritydialogs.xml" target="_blank">Oracle Docs</a>)</td>
  </tr>
 </tbody>
</table> Further on the security dialogues have been enhanced with a "more information" link. Whenever you hit an insecure constellation you are now presented with the warning dialogues introduced with 7u21 with an additional link in them.
<br>
<br><b>If you haven't been prompted to update you should do this as soon as possible.</b> Download the JRE for your system from <a href="">java.com</a> and be up-to-date!
<br>
<br><b>I'm a Developer! Tell me the dirty news!</b>
<br>
 No dirty and not announced news this time. But again, you still have a couple of things to take care of. First of all this release brings the new Olson Data 2013b. Which is a good thing even if we have the <a href="https://blogs.oracle.com/henrik/entry/tzupdater_for_jdk_7_available" target="_blank">TZUpdater back</a>.
<br>
<br>
 An important bug was fixed regarding signed jars.&nbsp;With 7u21 signed jars were allowed to be loaded without any unsigned warning if they contain unsigned index.list entry but this is not true anymore with 7u25.&nbsp;To properly sign a jar, index entries must be created before the jar is signed. For more information see bug&nbsp;<a href="http://bugs.sun.com/view_bug.do?bug_id=8016771" target="_blank">8016771</a>.
<br>
<br>
 JDK 7u25 release introduces the permissions and codebase attributes in the JAR Manifest File. The Permissions attribute is used to verify that the permissions level requested by the RIA when it runs matches the permissions level that was set when the JAR file was created. The values sandbox and all-permissions are valid. It must match the permission level requested in the JNLP file or the applet tag. 
<br>
 The Codebase attribute is used to restrict the code base of the JAR to specific domains.&nbsp;Set this attribute to either the domain name or IP address where the application is located. A port number can also be included. For multiple locations, separate the values with a space. An asterisk (*) can be used as a wildcard only at the beginning of the domain name. The value of the Codebase attribute must match the Code base specified in the JNLP file or the applet tag or the actual location from which the app is accessed.
<br>
 If one of both or both requirements don't match, an error is shown and the application is not run. If the attributes permissions or codebase &nbsp;are not present, a warning is written to the Java Console and the permissions/codebase specified for the applet tag or JNLP file is used. This behavior is most likely going to change and be handled more restrictively in the future. If you want more examples have a look at the <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/jweb/no_redeploy.html#permissions" target="_blank">SE 7 technote</a>.
<br>
<br>
 If you're hosting Javadoc somewhere make sure to regenerate it with latest <a href="http://www.oracle.com/technetwork/java/javase/documentation/index-jsp-135444.html" target="_blank">Javadoc Tool</a>. As stated in&nbsp;&nbsp;<a href="http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2013-1571" target="_blank">CVE-2013-1571</a>&nbsp;&nbsp;API documentation in HTML format generated by the Javadoc tool that contains a right frame may be vulnerable to frame injection when hosted on a web server. If you can't regenerate them, use the new <a href="http://www.oracle.com/technetwork/java/javase/downloads/java-doc-updater-tool-1955731.html" target="_blank">Updater Tool</a>&nbsp;which is NOT contained in the SDK/JRE bundles.
<br>
<br>
 Since 7u21 the decoding of command strings specified to java.lang.ProcessBuilder and the exec methods defined by java.lang.Runtime, has been made stricter on Windows platforms. 7u25 brings a new system property jdk.lang.Process.allowAmbigousCommands which can be used to relax the checking process and may be used as a workaround for some applications that are impacted by the stricter validation.&nbsp;&nbsp;To use this workaround, either the command line should be updated to include -Djdk.lang.Process.allowAmbigousCommands=true or the java application should set the system property jdk.lang.Process.allowAmbigousCommands to true.
<br>
<br>
 Further on there have been a lot of bug fixes which directly address CVEs. A complete <a href="http://www.oracle.com/technetwork/topics/security/javacpujun2013verbose-1899853.html" target="_blank">explained list is available in text form</a>.
<br>
<br><b>Further Readings</b>
<br>
 The official announcement on the Java Blog:
<br><a href="https://blogs.oracle.com/java/entry/java_se_7_update_25">https://blogs.oracle.com/java/entry/java_se_7_update_25</a>
<br>
 The 7u25 Release-Notes:
<br><a href="http://www.oracle.com/technetwork/java/javase/7u25-relnotes-1955741.html">http://www.oracle.com/technetwork/java/javase/7u25-relnotes-1955741.html</a>
<br>
 Overview April Java CPU:
<br><a href="http://www.oracle.com/technetwork/topics/security/javacpujun2013-1899847.html">http://www.oracle.com/technetwork/topics/security/javacpujun2013-1899847.html</a>
<br>
 Patch Availability Document for Oracle Java SE June CPU
<br><a href="https://support.oracle.com/CSP/main/article?cmd=show&amp;type=NOT&amp;id=1560542.1" target="_blank">https://support.oracle.com/CSP/main/article?cmd=show&amp;type=NOT&amp;id=1560542.1</a>
<br>
 Java SE 6 Downloads:
<br><a href="http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase6-419409.html">http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase6-419409.html</a>
<br>
<div>
 <br>
</div>
<div>
 <br>
</div>
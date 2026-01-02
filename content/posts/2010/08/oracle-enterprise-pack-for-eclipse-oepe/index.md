---
title: "Oracle Enterprise Pack for Eclipse (OEPE) 11gR1 (11.1.1.6) - New Features"
date: 2010-08-02 05:48:00 +0000
layout: post
tags: ["glassfish", "wtp", "oracle", "OEPE"]
slug: "oracle-enterprise-pack-for-eclipse-oepe"

url: /2010/08/oracle-enterprise-pack-for-eclipse-oepe.html
---

The new <a href="http://www.oracle.com/technetwork/developer-tools/eclipse/downloads/oepe-1116-161753.html">Oracle Enterprise Pack for Eclipse Release</a> (11.1.1.6.0) is out. I already blogged about the new <a href="http://blog.eisele.net/2010/07/getting-started-with-wlst-and-oracle.html">WLST integration</a>. Today I will report about the other new features as far as I know them. The OTN website still does not show the latest release notes for the 1.6 release.
<br>
<br><b>Oracle WebLogic Server Support</b>
<br>
 OEPE 1.6 adds the next 11g R1P3 Patch Set to its supported versions of Oracle Weblogic Server. 
<br>
<br><b>Support for Web Services</b>
<br>
 You use the WebLogic Web Services Annotation view to add new and edit existing annotations of a Java Web service within projects configured with WebLogic Web Services or WebLogic Web Service Clients facet. This view allows you to add annotations and their attributes without knowing the detailed information about which annotations and attributes are supported. 
<br>
<br><b>Support for Coherence</b>
<br>
 Oracle Coherence provides replicated and distributed data management and caching services on top of a peer-to-peer clustering protocol. OEPE provides a project facet that allows you to configure your dynamic Web projects to use Coherence. 
<br>
<br><b>Updated WTP 3.2 for Helios 3.6</b>
<br>
 The <a href="" target="_blank">GlassFish Plugins</a> project is moving to the Oracle Enterprise Pack for Eclipse (OEPE) (compare this <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=312912" target="_blank">bug entry</a>) and the latest release is available at this url <a href="http://download.oracle.com/otn_software/oepe/helios/wtp">http://download.oracle.com/otn_software/oepe/helios/wtp</a>. But it is also bundeled with the new OEPE release. You can get OEPE 1.6 for Both Eclipse 3.5.2 (Galileo) and 3.6 (Helios) releases.
<br>
<br>
 The OEPE GlassFish integration is based on the GlassFish plugins for the Eclipse Web Tools Platform (WTP) <a href="" target="_blank">version 3.2.0</a>. WTP 3.2 adds support for creating, running, and debugging applications using Java EE 6, including Servlet 3.0, JPA 2.0, JSF 2.0, EJB 3.1, and JAX-RS 1.1 technologies, plus deployment to updated server runtimes, and support for XPath 2.0. 
<br>
<br>
 In detail the enhancements are:
<br>
<br><b>Complete list of WTP 3.2 enhancements</b>
<br>
<ul>
 <li><a href="http://eclipse.org/webtools/releases/3.2.0/NewAndNoteworthy/jpa.php">Dali JPA Tools</a></li>
 <li style="list-style-type: none;">
  <ul>
   <li>Initial JPA 2.0 Support - 2.0 Facet and Generic 2.0 Platform</li>
   <li>Derived ID support</li>
   <li>JPA 1.0 and 2.0 Configurations</li>
   <li>Document version upgrade</li>
   <li>JPA 2.0 Support and EclipseLink 2.0 Platform</li>
   <li>JPA 2.0 Cache support</li>
   <li>JPA 2.0 Canonical Metamodel generation</li>
   <li>EclipseLink 1.2 Platform</li>
   <li>EclipseLink 2.1 support</li>
   <li>EclipseLink Logging Categories</li>
   <li>Validation Preferences</li>
   <li>Detail View options for: OrderColumn, ElementCollection, Derived Identity support</li>
   <li>JAXB XML Schema Generation</li>
   <li>JAXB Class Generation</li>
   <li>JoinTable support for "Many to One" and "One to One" mappings</li>
   <li>Cascade Detach</li>
   <li>Collection-valued associations map key attribute overrides; MapKeyClass, and MapKeyColumn Model Support</li>
  </ul></li>
 <li><a href="http://eclipse.org/webtools/releases/3.2.0/NewAndNoteworthy/javaee.php">Java EE and EJB Tools</a></li>
 <li style="list-style-type: none;">
  <ul>
   <li>Revamped Deployment Assembly</li>
  </ul></li>
 <li><a href="http://eclipse.org/webtools/releases/3.2.0/NewAndNoteworthy/jsf.php">JavaServer Faces Tools</a></li>
 <li style="list-style-type: none;">
  <ul>
   <li>JSF 2.0 - Project Configuration</li>
   <li>JSF 2.0 - XHTML File Creation</li>
   <li>JSF 2.0 - XHTML Source Editing</li>
   <li>JSF 2.0 - Composite Component support</li>
   <li>Web Page Editor (WPE) Design Pane enhancements</li>
  </ul></li>
 <li><a href="http://eclipse.org/webtools/releases/3.2.0/NewAndNoteworthy/server.php">Server Tools</a></li>
 <li style="list-style-type: none;">
  <ul>
   <li>Support for Apache Tomcat 7</li>
  </ul></li>
 <li><a href="http://eclipse.org/webtools/releases/3.2.0/NewAndNoteworthy/sourceediting.php">Source Editing and JSDT</a></li>
 <li style="list-style-type: none;">
  <ul>
   <li>Code Folding enabled by default</li>
   <li>Character Pairing</li>
   <li>Some XML changes</li>
   <li>Some HTML changes</li>
   <li>Some XSL changes</li>
   <li>JSDT - Perspective changes (Script Explorer)</li>
   <li>Improved namespace support</li>
   <li>JSDI API</li>
   <li>Rhino Debug Support</li>
   <li>Quick Access to Breakpoint Properties</li>
   <li>Breakpoint Condition Content Assist</li>
   <li>Rhino Console</li>
   <li>Some JSP changes</li>
   <li>Some CSS changes</li>
  </ul></li>
 <li><a href="http://eclipse.org/webtools/releases/3.2.0/NewAndNoteworthy/webservices.php">Web Services and WSDL Tools</a></li>
 <li style="list-style-type: none;">
  <ul>
   <li>JAX-RS Facet and Enhancements</li>
   <li>JAX-WS CNF Content Extensions</li>
   <li>JAXB Single Member and Marker Annotation Support</li>
   <li>Apache CXF Web Service creation using ANT tasks</li>
   <li>JAX-WS Handler Support</li>
   <li>Support for multiple CXF installations</li>
   <li>JAX-WS Handler Chain Configuration</li>
   <li>Abstract / concrete WSDL definitions</li>
  </ul></li>
</ul>
---
title: "Preventing CSRF with JSF 2.0"
date: 2011-02-28 08:02:00 +0000
layout: post
tags: ["security", "CSRF", "jsf 2.0"]
slug: "2011-02-28-preventing-csrf-with-jsf-20"
url: /2011/02/preventing-csrf-with-jsf-20.html
---

Have you ever had the need for higher security in one of your applications? Than you are probably familiar with the following topic. If not, I am going to tell you a little bit about attacks and web application security before we move over to implement a CSRF prevention approach with JSF 2.0.
<br>
<br><b>What is Application Security? And why should I care?</b>
<br>
 Have you ever heard about attacks? Attacks are the techniques that attackers use to exploit the vulnerabilities in applications. That is not necessarily done by a real hacker but from nearly anybody with some kind of knowledge in the field of security and programming. There are a couple of basic principles your application should comply to. See <a href="http://www.owasp.org/index.php/Category:Principle" target="_blank">OWASP Principles</a> for more details. For each of the principles you could and should employ some prevention methods in your application.
<br>
<br><b>What is a Cross-Site Request Forgery Attack?</b>
<br>
 Cross-Site Request Forgery (CSRF) is a type of attack that occurs when a malicious web site, email, blog, instant message, or program causes a user’s Web browser to perform an unwanted action on a trusted site for which the user is currently authenticated. The impact of a successful cross-site request forgery attack is limited to the capabilities exposed by the vulnerable application. For example, this attack could result in a transfer of funds, changing a password, or purchasing an item in the user's context. In affect, CSRF attacks are used by an attacker to make a target system perform a function (funds Transfer, form submission etc.) via the target's browser without knowledge of the target user, at least until the unauthorized function has been committed.
<br>
 This attack can happen even if the user is logged into a Web site using strong encryption (HTTPS). Utilizing social engineering, an attacker will embed malicious HTML or JavaScript code into an email or Website to request a specific 'task url'. The task then executes with or without the user's knowledge. For more details please have a look at the <a href="http://www.owasp.org/index.php/Cross-Site_Request_Forgery_(CSRF)" target="_blank">owasp page</a>.
<br>
<br><b>How to prevent CSRF Attacks?</b>
<br>
 The only successful way of protection against CSRF attacks is to decide weather an issued user action is valid and allowed in the actual context. There basically are two ways to achieve this: You have to issue a secure random token and assign it to the requests issued by a client. This could be done by either assigning one token per HttpSession or, if you need an even higher level of security by issuing a token per request. If the token is send back from the client you have to check it's validity somehow and allow or reject the request. 
<br>
<br><b>Where to start? - Generating and placing the token</b>
<br>
 If you are looking at a standard JSF 2.0 application you have a couple of places where you can think about integrating the described solution. The following is only one approach focusing on the per HttpSession token, as this will be the sufficient one for most of the requirements. Let's start with the token generation and placement. We are in need of generating a unique token per HttpSession and placing it there. For developer convenience this should be done transparently and centralized. So you first need to implement your own CSRFSessionListener implementing the HttpSessionListener interface. Overwrite the sessionCreated method and place the token in the created session.
<br><code><br>
  @Override<br>
  public void sessionCreated(HttpSessionEvent event) \{<br>
  HttpSession session = event.getSession();<br>
  String randomId = generateRandomId();<br>
  session.setAttribute(CSRFTOKEN_NAME, randomId);<br>
  \}<br></code>
<br>
 As you can see, there is no real magic here. The magic happens in the generateRandomId method. Again there are many ways to generate truly unique identifiers. Since Java SE 5 you have the handy <a href="http://java.sun.com/javase/6/docs/api/java/util/UUID.html" target="_blank">UUID Class</a>. This is a class that represents an immutable universally unique identifier (UUID) which represents a 128-bit value. The second option is to use <a href="http://download.oracle.com/javase/6/docs/api/java/security/SecureRandom.html" target="_blank">SecureRandom</a> and <a href="http://download.oracle.com/javase/6/docs/api/java/security/MessageDigest.html" target="_blank">MessageDigest</a> classes. Their use is far more expensive but you have a couple of options available which make your token a little bit more secure. You basically have to take the following steps:
<br><code><br>
  // Generate a random string<br>
  SecureRandom random = SecureRandom.getInstance("SHA1PRNG");<br>
  // inizialize a MessageDigest<br>
  MessageDigest sha = MessageDigest.getInstance("SHA-1");<br>
  // create a MessageDigest of the random number<br>
  byte[] randomDigest = sha.digest(new Integer(random.nextInt()).toString().getBytes());<br>
  // encode the byte[] into some textual representation <br>
  hexEncode(randomDigest)<br></code>
<br>
 For a more complete example please see this article about <a href="http://www.javapractices.com/topic/TopicAction.do?Id=56" target="_blank">Generating unique IDs</a>
<br>
<br><b>And the JSF parts? - placing the token to a form</b>
<br>
 Up to know we didn't even use JSF. But we will. The idea is to have the token printed as a hidden textfield to every form. To prevent developers from additional coding, we should introduce a new CSRFForm component which does this for us. So the next step is to implement your CSRFForm which should extend the standard HtmlForm. 
<br><code><br>
  @Override<br>
  public void encodeBegin(FacesContext context) throws IOException \{<br><br>
  // initialize the new TokenInput <br>
  CSRFTokenInput cSRFToken = new CSRFTokenInput();<br><br>
  // set the clientId<br>
  cSRFToken.setId(this.getClientId() + "_CSRFToken");<br><br>
  // add the component to the form<br>
  getChildren().add(cSRFToken);<br>
  super.encodeBegin(context); <br></code>
<br>
 As you can see, we also need the CSRFTokenInput class which extends UIComponentBase and represents our own hidden field.
<br><code><br>
  @Override<br>
  public void encodeEnd(FacesContext context) throws IOException \{<br><br>
  // get the session (don't create a new one!)<br>
  HttpSession session = (HttpSession) context.getExternalContext().getSession(false);<br><br>
  // get the token from the session<br>
  String token = (String) session.getAttribute(CSRFTOKEN_NAME);<br><br>
  // write the component html to the response<br>
  ResponseWriter responseWriter = context.getResponseWriter();<br>
  responseWriter.startElement("input", null);<br>
  responseWriter.writeAttribute("type", "hidden", null);<br>
  responseWriter.writeAttribute("name", (getClientId(context)), "clientId");<br>
  responseWriter.writeAttribute("value", token, "CSRFTOKEN_NAME");<br>
  responseWriter.endElement("input");<br><br>
  \}<br></code>
<br>
 Perfect. Now we placed the token which was generated with the HttpSessionListener to the form. If you now register your components with the custom-taglib.xml you can now use the new form within your pages &lt;cu:csrff&gt;. Looking at the page source you will see, that the token has been placed.
<br><code><br>
  &lt;input type="hidden" name="j_idt7:j_idt7_CSRFToken" value="0c776040ff77d3af5acce4d4c59a51411eb960bd" /&gt;<br></code>
<br>
<br><b>... and checking the validity!</b>
<br>
 Fine. But what about checking the validity of the token. Spend a minute and add a decode() method to your CSRFTokenInput component.
<br><code><br>
  public void decode(FacesContext context) \{<br>
  // get the client id of the component<br>
  String clientId = getClientId(context);<br>
  // access the hidden input field value<br>
  ExternalContext external = context.getExternalContext();<br>
  Map<string, string>
  requestMap = external.getRequestParameterMap();
  <br>
   String value = String.valueOf(requestMap.get(clientId));
  <br>
  <br>
   // access the session and get the token
  <br>
   HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
  <br>
   String token = (String) session.getAttribute(CSRFTOKEN_NAME);
  <br>
  <br>
   // check if the token exists
  <br>
   if (value == null || "".equals(value)) \{
  <br>
   throw new RuntimeException("CSRFToken is missing!");
  <br>
   \} 
  <br>
  <br>
   // check the values for equality 
  <br>
   if (!value.equalsIgnoreCase(token)) \{
  <br>
   throw new RuntimeException("CSRFToken does not match!");
  <br>
   \}
  <br>
  <br>
   \}
  <br>
 </string,></code>
<br>
 Done finally. We have the token in place, we can check it and this is done more or less transparently from the page designers. The only thing they have to take care of is the new form.
<br>
<br><b>What's next?</b>
<br>
 It's quite drastic to throw a RuntimeException here. This is done to keep the example short. The JSF way of things would be to register and implement a converter that does the checks. But you probably know how to do this, so I skip this step here. Another part is also missing. You have to audit and alert the attack. But how to do this the right way in the context of your actual implementation is a post of it's own. Thanks for reading!
<br>
<br><b>UPDATE: 01.03.11</b>
<br>
 Some more research and reading input for the fellow readers:
<br>
 JSF does currently have such a token (the javax.faces.ViewState), which is
<br><a href="http://java.net/jira/browse/JAVASERVERFACES-812" target="_blank">currently generated as a sequential token (easy to guess)</a>. A specification request to the EG was <a href="http://java.net/jira/browse/JAVASERVERFACES_SPEC_PUBLIC-869" target="_blank">issued</a>, implemented and <a href="http://java.net/jira/browse/JAVASERVERFACES-1850" target="_blank">backed out</a>. Some more <a href="http://java.net/jira/browse/JAVASERVERFACES_SPEC_PUBLIC-559" target="_blank">example code</a> for the "Synchronizer Token" pattern (avoiding double submits) with JSF which will be targeted to JSF &gt;=2.1
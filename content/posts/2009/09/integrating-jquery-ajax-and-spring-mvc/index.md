---
title: "Integrating jQuery AJAX and Spring MVC with XStream/Jettison and GAE"
date: 2009-09-09 06:16:00 +0000
layout: post
tags: ["xstream", "GAE", "jQuery", "Spring", "Jettison"]
slug: "integrating-jquery-ajax-and-spring-mvc"

url: /2009/09/integrating-jquery-ajax-and-spring-mvc.html
---

I wrote a small <a href="http://www.eisele.net/blog/2009/07/spring-300m3-on-google-appengine-with.html">howto about putting Spring 3.x to work with Google Appengine</a> earlier the year.
<br>
 This is the basic starting point for this extended howto.
<br>
<br>
<br><!-- more -->
<br>
<br>
<br>
 jQuery is a powerful yet unobtrusive JavaScript library with a very readable syntax.
<br>
 It's unobtrusiveness makes it easy to add rich behavior aka AJAX. 
<br>
<br><a class="lightbox" href="http://www.eisele.net/blog/uploaded_images/model-make-764652.png"><img alt="" border="0" src="http://www.eisele.net/blog/uploaded_images/model-make-764651.png" style="cursor: pointer; float: right; height: 102px; margin: 0pt 0pt 10px 10px; width: 184px;"></a>
<br>
 A very simple example
<br>
 could be a search on parent child object relations (e.g Make and Model).
<br>
 The user selects a make and the coresponding models are loaded.
<br>
 You would do this with spring forms like this:
<br>
<br>
 Make: 
<br>
 &lt;form:select path="herstellerId" &gt;
<br>
 &lt;form:options items="$\{herstellerValues\}" itemValue="herstellerId"
<br>
 itemLabel="label" /&gt;
<br>
 &lt;/form:select&gt;
<br>
 &lt;br /&gt;
<br>
 Model: 
<br>
 &lt;form:select path="modellId" &gt;
<br>
 &lt;form:options items="$\{modelValues\}" itemValue="modellId"
<br>
 itemLabel="label" id="herstellerId" /&gt;
<br>
 &lt;/form:select&gt;
<br>
<br>
<br>
 Now it is time to bring in the AJAX magic. All you need is jquery and its forms plugin.
<br>
<br>
 &lt;script type="text/javascript" src="/js/jquery/jquery-1.2.6.min.js"&gt;&lt;/script&gt; 
<br>
 &lt;script type="text/javascript" src="/js/jquery/jquery.form.js"&gt;&lt;/script&gt; 
<br>
<br>
<br>
 Now you need to bind an on change listener to the make select box and define the JSON query function.
<br>
<br>
 &lt;script type="text/javascript"&gt;
<br>
 $(document).ready( function() \{
<br>
 // event listeners 
<br>
 $("select[name^=herstellerId]").bind('change', loadModels);
<br>
<br>
 \});
<br>
<br>
 // When a make is selected, populate the models dropdown
<br>
 function loadModels() \{
<br>
 $.getJSON("/models.html", // url
<br>
 \{
<br>
 makeId :this.value
<br>
 \}, // request params
<br>
 function(json) \{ // callback
<br>
 var options = '';
<br>
 $(json.list.model).each(
<br>
 function() \{
<br>
 options += '&lt;option value="' + this.modellId
<br>
 + '"&gt;' + this.label + '&lt;/option&gt;';
<br>
 \});
<br>
 $("select[name^=modellId]").html(options);
<br>
 \});
<br>
 \}
<br>
 &lt;/script&gt;
<br>
<br>
 That is it. Now you are done with the frontend part. Next you need to have a look at your Spring MVC controller.
<br>
 The /models.html should have a corresponding method in your controller.
<br>
<br>
 @RequestMapping("/models.html")
<br>
 public ModelAndView models(@RequestParam("makeId") Long makeId) \{
<br>
 ModelAndView mav = new ModelAndView(JsonView.instance);
<br>
 mav.addObject(JsonView.JSON_ROOT, getModelList(makeId));
<br>
 return mav;
<br>
 \}
<br>
<br>
 All we do here is to instantiate a JsonView ModelAndView object and add a list of model objects to it.
<br>
 The method signature getModelList could look like this:
<br>
<br>
 private List<carmodel>
 getModelList(Long makeId) 
 <br>
 <br>
  all you have to do is to instantiate your CarModel domain objects and put them to a list. If you try
 <br>
  to use JPA based entities you will probably discover a problem. The XStream/Jettison way of transforming
 <br>
  Objects to JSONObjects is not able to handle the datanucleus enhanced entities. Therefore it will be best
 <br>
  to either write an own XStream converter or simply use value or transfer objects.
 <br>
 <br>
  Now we need to add the JsonView:
 <br>
 <br>
  public class JsonView implements View \{
 <br>
 <br>
  public static final JsonView instance = new JsonView();
 <br>
  public static final String JSON_ROOT = "root";
 <br>
 <br>
  private XStream xstream = new XStream(new JettisonMappedXmlDriver());
 <br>
 <br>
  private JsonView() \{
 <br>
  // your DTO/VO
 <br>
  xstream.processAnnotations(Modell.class);
 <br>
  \}
 <br>
 <br>
  public String getContentType() \{
 <br>
  return "text/html; charset=UTF-8";
 <br>
  \}
 <br>
 <br>
  @SuppressWarnings("unchecked")
 <br>
  public void render(Map model, HttpServletRequest request,
 <br>
  HttpServletResponse response) throws Exception \{
 <br>
  Object root = model.get(JsonView.JSON_ROOT);
 <br>
  if (root == null) \{
 <br>
  throw new RuntimeException("JSON root object with key '"
 <br>
  + JsonView.JSON_ROOT + "' not found in model");
 <br>
  \}
 <br>
  PrintWriter writer = response.getWriter();
 <br>
  String json = xstream.toXML(root);
 <br>
  System.out.println("json: " + json);
 <br>
  writer.write(json);
 <br>
  \}
 <br>
 <br>
 <br>
  That was all in terms of coding. Now you have to add the following jar files to your distribution.
 <br>
 <br>
  - stax-api-1.0.1.jar
 <br>
  - xpp3_min-1.1.4c.jar
 <br>
  - xstream-1.3.jar
 <br>
  - jettison-1.0.1.jar
 <br>
 <br>
 <br>
  And start over with testing. If you are happy, you will see the json response logged with the console:
 <br>
 <br>
  json: \{"list":\{"model":[\{"modellId":19,"label":"GT","herstellerId":7\},\{"modellId":20,"label":"Astra C","herstellerId":7\},\{"modellId":39,"label":"Insignia","herstellerId":7\}]\}\}
 <br>
 <br>
 <br>
  Happy coding.
</carmodel>
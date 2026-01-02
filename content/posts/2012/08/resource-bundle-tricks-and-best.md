---
title: "Resource Bundle Tricks and Best Practices"
date: 2012-08-20 10:20:00 +0000
layout: post
tags: ["java", "resourcebundle", "best practices"]
slug: "resource-bundle-tricks-and-best"
link: "2012/08/resource-bundle-tricks-and-best.html"
url: /2012/08/resource-bundle-tricks-and-best.html
---

Today is resource bundle day. This is the most well known mechanism for internationalization (i18n) in Java in general. Working with it should be a breeze. But there are many little questions that come up while getting your hands dirty with it. If you are feeling the same, this post is for you.
<br>
<br><b>Basics</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="IMG-20120820-00344.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="240" src="IMG-20120820-00344.jpg" width="320"></a>
</div> The java.util.ResourceBundle defines a standardized way for accessing translations in java. They contain locale-specific resources. Resource bundles belong to families whose members share a common base name, but whose names also have additional components that identify
<br>
 &nbsp;their locales. Each resource bundle in a family contains the same items, but the items have been translated for the locale represented by that resource bundle. Those are key/value pairs. The keys uniquely identify a locale-specific object in the bundle.
<br>
<br>
 The most basic example uses the following familiy:
<br>
 Messages.properties
<br>
 Messages_de.properties
<br>
 Messages_en.properties
<br>
<br>
 If you need to query a bundle in your application you simple call the
<br>
<pre class="brush:java">ResourceBundle bundle = ResourceBundle.getBundle("Messages"); </pre> method and query the returned bundle:
<br>
<pre class="brush:java">bundle.getString("welcome.message"); </pre> If you are wondering which Locale is going to be used here, you are right. The String constructor implicitly uses Locale.getDefault() to resolve the language. That might not be what you want. So you should ResourceBundle bundle = 
<br>
<pre class="brush:java">ResourceBundle.getBundle("Messages", locale);</pre> You cannot set the locale after you have retrieved the bundle. Every ResourceBundle has one defined locale.
<br>
<br><b>Naming stuff</b>
<br>
 Some thoughts about naming. Name the bundle properties after their contents. You can go a more general way by simply naming them "Messages" and "Errors" etc. but it also is possible to have a bundle per subsystem or component. Whatever fit's your needs. Maintaining the contents isn't easy with lots of entries. So any kind of contextual split makes developers happy. The bundle properties files are equivalent to classes; Name them accordingly. And further on you should find a common system for naming your keys. Depending on the split you have chosen for the property files you might also introduce some kind of subsystem or component namespace with your keys. Page prefixes are also possible. Think about this wisely and play around with it. You are aiming to have least possible dublicates in your keys.
<br>
<br><b>Encapsulating</b>
<br>
 As you have seen, you use the string representation of the bundles a lot. The fact that those are actually file-names (or better class-names) you would be better of with a simple enum which encapsulates everything for you:
<br>
<pre class="brush:java">public enum ResourceBundles \{ MESSAGES("Messages"), ERRORS("Errors"); private String bundleName; ResourceBundles(String bundleName) \{ this.bundleName = bundleName; \} public String getBundleName() \{ return bundleName; \} @Override public String toString() \{ return bundleName; \} \} </pre>
<br>
 Having this you simply can write
<br>
<pre class="brush:java">ResourceBundle bundle = ResourceBundle.getBundle(MESSAGES.getBundleName()); </pre>
<br><b>Java Server Faces and ResourceBundles</b>
<br>
 To use resource bundles in your jsf based application you simple have to define them in your faces-config.xml and use the shortcuts in your xhtml files.
<br>
<br>
<pre class="brush:xml">&lt;resource-bundle&gt; &lt;base-name&gt;Messages&lt;/base-name&gt; &lt;var&gt;msgs&lt;/var&gt; </pre>
<br>
<pre class="brush:xml">&nbsp;&lt;h:outputLabel value="#\{msgs['welcome.general']\}" /&gt; </pre>
<br>
 JSF takes care of the rest. What about parameter substitution? Think about a key-value pair like the following: 
<br>
<pre>welcome.name=Hi \{0\}! How are you? </pre> You can pass the parameter via the f:param tag: 
<br>
<pre class="brush:xml">&nbsp;&lt;h:outputFormat value="#\{msgs['welcome.name']\}"&gt; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&lt;f:param value="Markus" /&gt; &nbsp;&lt;/h:outputFormat&gt; </pre> To change the language you have to set a specific locale for your current FacesContext instance. It's best to do this via a value change listener:
<br>
<br>
<pre class="brush:java">&nbsp; &nbsp; public void countryLocaleCodeChanged(ValueChangeEvent e) \{ &nbsp; &nbsp; &nbsp; &nbsp; String newLocaleValue = e.getNewValue().toString(); &nbsp; &nbsp; &nbsp; &nbsp; //loop country map to compare the locale code &nbsp; &nbsp; &nbsp; &nbsp; for (Map.Entry&lt;String, Object&gt; entry : countries.entrySet()) \{ &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; if (entry.getValue().toString().equals(newLocaleValue)) \{ &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; FacesContext.getCurrentInstance() &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; .getViewRoot().setLocale((Locale) entry.getValue()); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; \} &nbsp; &nbsp; &nbsp; &nbsp; \} &nbsp; &nbsp; \} </pre><b>Resource Bundles in EJBs</b>
<br>
 JSF obviously is very easily integrated. What about using those bundles in EJBs? It is basically the same. You have the same mechanisms in place to get hand on the bundle and use it. There is one thing that you should keep in mind. You probably don't want to always use the default locale. So you have to find a way to pass the locale down from the UI. If you are thinking about @Injecting the MessageBundle via a @Produces annotation you have to think more than one time. Especially if you are working with @Stateless EJBs. Those instances get pooled and you have to pass the Locale to any business method that needs to know about the current Locale. You typically would do this with a parameter object or some kind of user session profile. Don't add the Locale as method signature all over.
<br>
<br><b>Resource Bundles from the DB</b>
<br>
 In most of the cases I see you need to pull the keys from a DB. Given the inner workings of the ResourceBundle (one "class" per locale) you end up having to implement the logic in your own ResourceBundle implementation. Most of the examples you find on the web do this by overriding the handleGetObject(String key) method. I don't like this approach, especially since we have a far better way using the ResourceBundle.Control mechanism. Now you can override the newBundle() method and return your own ResourceBundle implementation. All you have to do is to set your own Control as a parent with your DatabaseResourceBundle:
<br>
<br>
<pre class="brush:java">public DatabaseResourceBundle() \{ &nbsp; &nbsp; &nbsp; &nbsp; setParent(ResourceBundle.getBundle(BUNDLE_NAME, &nbsp; &nbsp; &nbsp; &nbsp; FacesContext.getCurrentInstance().getViewRoot().getLocale(), new DBControl())); &nbsp; &nbsp; \} </pre> The DBControl returns MyResourceBundle which is a ListResourceBundle:
<br>
<br>
<pre class="brush:java">protected class DBControl extends Control \{ @Override public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException \{ return new MyResources(locale); \} /** * A simple ListResourceBundle */ protected class MyResources extends ListResourceBundle \{ private Locale locale; /** * ResourceBundle constructor with locale * * @param locale */ public MyResources(Locale locale) \{ this.locale = locale; \} @Override protected Object[][] getContents() \{ TypedQuery&lt;ResourceEntity&gt; query = _entityManager.createNamedQuery("ResourceEntity.findForLocale", ResourceEntity.class); query.setParameter("locale", locale); List&lt;ResourceEntity&gt; resources = query.getResultList(); Object[][] all = new Object[resources.size()][2]; int i = 0; for (Iterator&lt;ResourceEntity&gt; it = resources.iterator(); it.hasNext();) \{ ResourceEntity resource = it.next(); all[i] = new Object[]\{resource.getKey(), resource.getValue()\}; values.put(resource.getKey(), resource.getValue()); i++; \} return all; \} \} \} </pre> As you can see, this is backed by an entitymanager and a simple ResourceEntity which has all the fields and NamedQueries necessary for building up the different bundles.
<br>
<pre class="brush:java">&nbsp; &nbsp; @Id &nbsp; &nbsp; @GeneratedValue(strategy = GenerationType.AUTO) &nbsp; &nbsp; private Long id; &nbsp; &nbsp; @Column(name = "i18n_key") &nbsp; &nbsp; private String key; &nbsp; &nbsp; @Column(name = "i18n_value") &nbsp; &nbsp; private String value; &nbsp; &nbsp; @Column(name = "i18n_locale") &nbsp; &nbsp; private Locale locale; </pre> By putting the bundles in a private Map&lt;String, String&gt; values = new HashMap&lt;String, String&gt;(); you also have a good way of caching the results after the bundles have been build up for the first time.
<br>
 This still isn't the best solution as ResourceBundles have a way of caching. But I might dig into this in more detail later. Until now, this bundle is cached forever (or at least until the next redeployment).
<br>
<br><b>Rewrite as Language Switch</b>
<br>
 On last thing to mention is that you also could have some fancy add-ons here. If you already have the JSF language switch magic in place it is simple to add <a href="" target="_blank">ocpsoft's rewrite</a> to your application. This is a simple way to encode the language in the URLs like this&nbsp;<a href="http://localhost:8080/Bundle-Provider-Tricks/en/index.html">http://yourhost.com/Bundle-Provider-Tricks/en/index.html</a>
<br>
 All you have to do is to add rewrite to the game by adding two simple dependencies: 
<br>
<pre class="brush: xml">&lt;dependency&gt; &lt;groupId&gt;org.ocpsoft.rewrite&lt;/groupId&gt; &lt;artifactId&gt;rewrite-servlet&lt;/artifactId&gt; &lt;version&gt;1.1.0.Final&lt;/version&gt; &lt;/dependency&gt; &lt;dependency&gt; &lt;groupId&gt;org.ocpsoft.rewrite&lt;/groupId&gt; &lt;artifactId&gt;rewrite-integration-faces&lt;/artifactId&gt; &lt;version&gt;1.1.0.Final&lt;/version&gt; &lt;/dependency&gt; </pre> Rewrite needs you to add your own ConfigurationProvider which is the central place to hold your rewriting rules. Implement the following: 
<br>
<pre class="brush: java">public class BundleTricksProvider extends HttpConfigurationProvider \{ @Override public Configuration getConfiguration(ServletContext context) \{ return ConfigurationBuilder.begin() // Locale Switch .addRule(Join.path("/\{locale\}/\{page\}.html").to("/\{page\}.xhtml") .where("page").matches(".*") .where("locale").bindsTo(PhaseBinding.to(El.property("#\{languageSwitch.localeCode\}")).after(PhaseId.RESTORE_VIEW))); \} @Override public int priority() \{ return 10; \} \} </pre> Next is to add a file named "org.ocpsoft.rewrite.config.ConfigurationProvider" to your META-INF/services folder and put the fully qualified name of your ConfigurationProvider implementation there. One last thing to tweak is the logic in the LanguageSwitch bean. Rewrite isn't able to trigger a ValueChangeEvent (as far as I know :)) so you have to add some magic to change the Locale while the setter is called. That's it .. very easy! 
<br>
<br>
 This is all for today. Find the code on <a href="">github.com</a>. Happy to read about your thoughts.
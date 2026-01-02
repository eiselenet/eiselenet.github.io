---
title: "GlassFish JDBC Security with Salted Passwords on MySQL"
date: 2012-07-30 11:18:00 +0000
layout: post
tags: ["glassfish", "security", "mysql"]
slug: "glassfish-jdbc-security-with-salted"
link: "2012/07/glassfish-jdbc-security-with-salted.html"
url: /2012/07/glassfish-jdbc-security-with-salted.html
---

One of the most&nbsp;successful&nbsp;posts on this blog is my post about setting up a <a href="http://blog.eisele.net/2011/01/jdbc-security-realm-and-form-based.html">JDBC Security Realm</a> with form based authentication on GlassFish. Some comments on this post made me realize that there is more to do to actually make this secure as it should be.
<br>
<br><b>Security out of the box</b>
<br>
<table cellpadding="0" cellspacing="0" class="tr-caption-container" style="float: right; margin-left: 1em; text-align: right;">
 <tbody>
  <tr>
   <td style="text-align: center;"><a href="lock.jpg" imageanchor="1" style="clear: right; margin-bottom: 1em; margin-left: auto; margin-right: auto;"><img border="0" height="213" src="lock.jpg" width="320"></a></td>
  </tr>
  <tr>
   <td class="tr-caption" style="text-align: center;">Picture: <a href="" rel="nofollow" target="_blank">TheKenChan</a> (<a href="" rel="nofollow" target="_blank">CC BY-NC 2.0</a>)</td>
  </tr>
 </tbody>
</table> GlassFish comes with a <a href="http://blogs.oracle.com/swchan/entry/jdbcrealm_in_glassfish" target="_blank">GlassFish JDBC Realm</a> already. All you have to do is to initialize a database and get the security configuration right and you are done. Among the standard configuration you have the option to define a digest-algorithm (including encoding and charset). The digest-algorithm can be any <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#MessageDigest" target="_blank">JDK supported</a> MessageDigest (MD2, MD5, SHA-1, SHA-256, SHA-384, SHA-512).&nbsp;Compare my <a href="http://blog.eisele.net/2011/01/jdbc-security-realm-and-form-based.html" target="_blank">JDBC Security Realm post</a> for a complete setup.
<br>
<br><b>What is weak or missing?</b>
<br>
 The out of the box solution goes a very trivial way. It simply hashes the password. There are many ways to recover passwords from plain hashes very quickly. The simplest way to crack a hash is to try to guess the password, hashing each guess, and checking if the guess's hash equals the hash being cracked. If the hashes are equal, the guess is the password. The two most common ways of guessing passwords are dictionary attacks and brute-force attacks. Also very widely know are the Lookup tables. They are an effective method for cracking many hashes of the same type very quickly. The general idea is to pre-compute the hashes of the passwords in a password dictionary and store them, and their corresponding password, in a lookup table data structure. But we are not done now. You also find something called Reverse Lookup Tables. This attack allows an attacker to apply a dictionary or brute-force attack to many hashes at the same time, without having to pre-compute a lookup table. And last but not least the Rainbow Tables attack. They are like lookup tables, except that they sacrifice hash cracking speed to make the lookup tables smaller. Very impressive list of approaches. Clearly this doesn't meet my personal need for securing passwords.
<br>
<br><b>Adding some Salt</b>
<br>
 The above approaches work because of the fact that each password is hashed in the exact same way.&nbsp;Every time&nbsp;you run a password through the secure hash function it produces the exact same output. One way to prevent this is to add some salt to it. Appending or prepending a random string to the password before hashing it would solve this. This random string is&nbsp;referred&nbsp;to as "salt". Be aware that reusing the salt for all passwords is not secure. You can still use rainbow tables or dictionary attacks to crack them. So you have to randomize the salt for every password and store it beside the hashed password. And it needs to change every time a user updates his password. A short sentence about length. Salts shouldn't be too short. For the most effective length would be the same size as the password hash. If you use a SHA512 (512/8bit=64 bytes) you should choose a salt with at least 64 random bytes long.
<br>
<br><b>Preparations</b>
<br>
 We are clearly leaving the standard JDBCRealm features now. Which means we have to implement our own security realm. Let's call it UserRealm from now on. Let's start with the same setup we have for the JDBCRealm. A MySQL database with a "jdbcrealmdb" schema. Only difference here, we prepare to save the salt with every password. 
<br>
<pre class="brush:xml">USE jdbcrealmdb; CREATE TABLE `jdbcrealmdb`.`users` ( `username` varchar(255) NOT NULL, `salt` varchar(255) NOT NULL, `password` varchar(255) DEFAULT NULL, PRIMARY KEY (`username`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8; CREATE TABLE `jdbcrealmdb`.`groups` ( `username` varchar(255) DEFAULT NULL, `groupname` varchar(255) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8; CREATE INDEX groups_users_FK1 ON groups(username ASC); </pre>
<br>
 Now we implement the basic realm. The following code simply shows the mandatory members. I am going to make the source available <strike>during the next days. Until today this post is anything that is available for you.</strike>&nbsp; on <a href="https://github.com/myfear/glassfish-jdbc-realm-enhanced/tree/master/glassfish-realm" target="_blank">github.com</a>.
<br>
<br>
<pre class="brush: java"> public class UserRealm extends AppservRealm \{ /** * Init realm from properties */ public void init(Properties props) /** * Get JAASContext */ public String getJAASContext() /** * Get AuthType */ public String getAuthType() /** * Get DB Connection */ private Connection getConnection() /** * Close Connection */ private void closeConnection(Connection cn) /** * Close prepared statement */ private void closeStatement(PreparedStatement st) /** * Make the compiler happy. */ public Enumeration getGroupNames(String string) /** * Authenticate the user */ public String[] authenticate(String userId, String password) </pre> But the most important part is missing here.
<br>
<br><b>Setting up some tests</b>
<br>
 I'm not exactly the kind of test driven guy but in this case it actually makes sense. Because the realm I am going to implement here doesn't support user-management via the GlassFish admin console. So the basic requirement is to have a prepared database with all the users, passwords and salts in place. Let's go. Add the&nbsp;sql-maven-plugin and let it create the tables during test-compile phase.
<br>
<pre class="brush:xml"> &lt;plugin&gt; &lt;groupId&gt;org.codehaus.mojo&lt;/groupId&gt; &lt;artifactId&gt;sql-maven-plugin&lt;/artifactId&gt; &lt;version&gt;1.3&lt;/version&gt; &lt;dependencies&gt; &lt;dependency&gt; &lt;groupId&gt;mysql&lt;/groupId&gt; &lt;artifactId&gt;mysql-connector-java&lt;/artifactId&gt; &lt;version&gt;5.1.18&lt;/version&gt; &lt;/dependency&gt; &lt;/dependencies&gt; &lt;configuration&gt; &lt;driver&gt;$\{driver\}&lt;/driver&gt; &lt;url&gt;$\{url\}&lt;/url&gt; &lt;username&gt;$\{username\}&lt;/username&gt; &lt;password&gt;$\{password\}&lt;/password&gt; &lt;skip&gt;$\{maven.test.skip\}&lt;/skip&gt; &lt;srcFiles&gt; &lt;srcFile&gt;src/test/data/drop-and-create-table.sql&lt;/srcFile&gt; &lt;/srcFiles&gt; &lt;/configuration&gt; &lt;executions&gt; &lt;execution&gt; &lt;id&gt;create-table&lt;/id&gt; &lt;phase&gt;test-compile&lt;/phase&gt; &lt;goals&gt; &lt;goal&gt;execute&lt;/goal&gt; &lt;/goals&gt; &lt;/execution&gt; &lt;/executions&gt; &lt;/plugin&gt; </pre> You can either use some db-unit magic to insert the test-data into your database or do this within your test-cases. I decided to go this way. First let us put all the relevant JDBC stuff to a separate place called SecurityStore. We basically need three methods. Add a user, get the salt for a user and validate the user.
<br>
<br>
<pre class="brush:java"> private final static String ADD_USER = "INSERT INTO users VALUES(?,?,?);"; private final static String SALT_FOR_USER = "SELECT salt FROM users u WHERE username = ?;"; private final static String VERIFY_USER = "SELECT username FROM users u WHERE username = ? AND password = ?;"; //... public void addUser(String name, String salt, String password) \{ try \{ PreparedStatement pstm = con.prepareStatement(ADD_USER); pstm.setString(1, name); pstm.setString(2, salt); pstm.setString(3, password); pstm.executeUpdate(); \} catch (SQLException ex) \{ LOGGER.log(Level.SEVERE, "Create User failed!", ex); \} \} public String getSaltForUser(String name) \{ String salt = null; try \{ PreparedStatement pstm = con.prepareStatement(SALT_FOR_USER); pstm.setString(1, name); ResultSet rs = pstm.executeQuery(); if (rs.next()) \{ salt = rs.getString(1); \} \} catch (SQLException ex) \{ LOGGER.log(Level.SEVERE, "User not found!", ex); \} return salt; \} public boolean validateUser(String name, String password) \{ try \{ PreparedStatement pstm = con.prepareStatement(VERIFY_USER); pstm.setString(1, name); pstm.setString(2, password); ResultSet rs = pstm.executeQuery(); if (rs.next()) \{ return true; \} \} catch (SQLException ex) \{ LOGGER.log(Level.SEVERE, "User validation failed!", ex); \} return false; \} </pre> In order to not implement too much here I decided to have two separate constructors: 
<br>
<pre class="brush:java">public SecurityStore(String dataSource) public SecurityStore(String user, String passwd) </pre> So this will work with both, the app-server and my local tests. Next is the actual password and salt logic. 
<br>
<br><b>Working with Passwords, Hashes and Salts</b>
<br>
 Here is what I came up with:
<br>
<pre class="brush:java">public class Password \{ private SecureRandom random; private static final String CHARSET = "UTF-8"; private static final String ENCRYPTION_ALGORITHM = "SHA-512"; private BASE64Decoder decoder = new BASE64Decoder(); private BASE64Encoder encoder = new BASE64Encoder(); public byte[] getSalt(int length) \{ random = new SecureRandom(); byte bytes[] = new byte[length]; random.nextBytes(bytes); return bytes; \} public byte[] hashWithSalt(String password, byte[] salt) \{ byte[] hash = null; try \{ byte[] bytesOfMessage = password.getBytes(CHARSET); MessageDigest md; md = MessageDigest.getInstance(ENCRYPTION_ALGORITHM); md.reset(); md.update(salt); md.update(bytesOfMessage); hash = md.digest(); \} catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) \{ Logger.getLogger(Password.class.getName()).log(Level.SEVERE, "Encoding Problem", ex); \} return hash; \} public String base64FromBytes(byte[] text) \{ return encoder.encode(text); \} public byte[] bytesFrombase64(String text) \{ byte[] textBytes = null; try \{ textBytes = decoder.decodeBuffer(text); \} catch (IOException ex) \{ Logger.getLogger(Password.class.getName()).log(Level.SEVERE, "Encoding failed!", ex); \} return textBytes; \} \} </pre> Pretty easy, right? To be honest: Working with the byte[] could be hidden better, but I thought you will easier understand what is happening here. The salt() method returns a secure random salt of the configured length. The hashWithSalt() method puts everything into one SHA-512 hashed password.
<br>
<br><b>A word about endcodings</b>
<br>
 I decided to Base64 encode it and I am using the proprietary API (sun.misc.BASE64Decoder, Encoder). You should think about using apache commons here. But it was the easiest way to do it. Another approach is to simply HEX encode (zero-pad) everything. The difference between Base64 and&nbsp; HEX&nbsp;&nbsp;is really just how bytes are represented.&nbsp; HEX&nbsp;&nbsp;is another way of saying "Base16".&nbsp; HEX&nbsp;&nbsp;will take two characters for each byte - Base64 takes 4 characters for every 3 bytes, so it's more efficient than hex. Assuming you're using UTF-8 to encode the XML document, a 100K file will take 200K to encode in hex, or 133K in Base64.
<br>
<br><b>And finally the missing method in the UserRealm</b>
<br>
 The very final part of this lengthy post is the authenticate method in the UserRealm class. 
<br>
<pre class="brush:java"> /** * Authenticates a user against GlassFish * * @param name The user name * @param givenPwd The password to check * @return String[] of the groups a user belongs to. * @throws Exception */ public String[] authenticate(String name, String givenPwd) throws Exception \{ SecurityStore store = new SecurityStore(dataSource); // attempting to read the users-salt String salt = store.getSaltForUser(name); // Defaulting to a failed login by setting null String[] result = null; if (salt != null) \{ Password pwd = new Password(); // get the byte[] from the salt byte[] saltBytes = pwd.bytesFrombase64(salt); // hash password and salt byte[] passwordBytes = pwd.hashWithSalt(givenPwd, saltBytes); // Base64 encode to String String password = pwd.base64FromBytes(passwordBytes); _logger.log(Level.FINE, "PWD Generated \{0\}", password); // validate password with the db if (store.validateUser(name, password)) \{ result[0] = "ValidUser"; \} \} return result; \} </pre> That is all left to do here. If we have a salt for a given user-name we generate a hashed password which we are going to check against the one we have in the database. The getSaltForUser() also is our implicit check for the existence of the user. 
<br>
<br><b>Making password cracks even harder: Slow Hash Functions</b>
<br>
 Security wouldn't be called security if they wouldn't add more to it. So, salted passwords are way better than simply hashed ones but still probably not enough because they still allow for brute-force or dictionary attacks on any individual hash. But you can add more protection. The keyword is <a href="http://en.wikipedia.org/wiki/Key_stretching" target="_blank">key-stretching</a>. Also known as slow hash functions. The idea here is to make computation slow enough to no longer allow for CPU/GPU driven attacks. It is implemented using a special CPU-intensive hash function. <a href="http://en.wikipedia.org/wiki/PBKDF2" target="_blank">PBKDF2</a> (Password-Based Key Derivation Function 2) is one of them. You can use it in different ways but one warning: never try to do this at your own. Use one of the tested and provided implementations like the <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#SecretKeyFactory" target="_blank">PBKDF2WithHmacSHA1</a> from the JDK or the <a href="http://www.cs.berkeley.edu/~jonah/bc/org/bouncycastle/crypto/generators/PKCS5S2ParametersGenerator.html" target="_blank">PKCS5S2ParametersGenerator</a> from the Bouncycastle library. An example could look like this:
<br>
<pre class="brush:java"> public byte[] hashWithSlowsalt(String password, byte[] salt) \{ SecretKeyFactory factory; Key key = null; try \{ factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); KeySpec keyspec = new PBEKeySpec(password.toCharArray(), salt, 1000, 512); key = factory.generateSecret(keyspec); \} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) \{ Logger.getLogger(Password.class.getName()).log(Level.SEVERE, null, ex); \} return key.getEncoded(); \} </pre>
<br><b>Why all that?</b>
<br>
 We hear about password and user database leaks a lot. Every day. Some big sites have been hit and it basically is up to the implementer to provide suitable security for his users. Knowing where and how to tweak can be difficult and honestly using the provided features left you behind with a wrong comfortable feeling. Don't stop learning about security features and keep an eye open for possible problems. I personally wish GlassFish would provide a more comprehensive set of default realms for users to work with. But as long as this isn't the case my blog is the only way to guide you into the right direction. Hope you enjoyed it! 
<br>
<br>
 [UPDATE 31.07.2012]
<br>
 The source is on <a href="https://github.com/myfear/glassfish-jdbc-realm-enhanced/tree/master/glassfish-realm" target="_blank">github.com</a>
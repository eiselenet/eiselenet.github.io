---
title: "Gmail, Notes/Domino and Migration"
date: 2010-05-17 06:40:00 +0000
layout: post
tags: ["migration", "gmail", "lotus notes", "domino"]
slug: "gmail-notesdomino-and-migration"
link: "2010/05/gmail-notesdomino-and-migration.html"
url: /2010/05/gmail-notesdomino-and-migration.html
---

I was playing around with Gmail, the gdata api and Lotus Notes/Domino lately. The idea was to migrate some old stuff (obviously private stuff) from my Lotus Notes archives to a separate Gmail account. My company is running Notes and it's easy to have separate archives but I feel like having a backup from time to time and it is also very handy to have access to those things from everywhere without carrying your computer with you all the time. Therefore I need to transfer the archives over to gmail. If you are a Google Apps Premier or Google Apps Education Edition customer, all you have to do is to look at the <a href="http://www.google.com/support/a/bin/answer.py?hl=en&amp;answer=154630" target="_blank">provided tools</a>, install them and run your migration. If you have a free account you are stuck. But it would not be google, if you would not find any solution to this ;)
<br>
<br>
 Here is my small howto on migrating Lotus Notes databases to Google free Gmail accounts. If you are trying this, you should have some insights in Java, Notes and MIME/Email concepts already. If not, you will find plenty of service companys out there helping you. You can even contact <a href="http://www.google.com/apps/intl/en/business/notes.html" target="_blank">Google directly about this</a>. All others go ahead reading :)
<br>
<br><b>Tools, sources and environment</b>
<br>
<ul>
 <li>Get yourself a <a href="http://www.gmail.com" target="_blank">gmail account</a> for testing :)</li>
 <li>Get yoursel a copy of the Lotus Notes 8.5.1 client (better the designer).</li>
 <li>Have a copy of your favorite Java IDE in place (for me this is still Eclipse) and start a new project.</li>
 <li>If you are not willing to start from scratch, get the <a href="http://code.google.com/p/gdata-java-client/downloads/list" target="_blank">gdata-java-client</a> samples.</li>
 <li>Add all required google libraries (gdata-core, -client,-appsforyourdomain,-media, etc.) to your project's build path</li>
 <li>Copy the notes.jar from %LOTUS%\notes\jvm\lib\ext to your project's build path</li>
 <li>Add the Notes install folder %LOTUS%\notes to your project's run and debug-configuration path environment.</li>
 <li>Copy the sample.appsforyourdomain.migration.AppsForYourDomainMigrationClient.java file from the gdata samples to your project</li>
</ul>
<br><b>Running the first test</b>
<br>
 If everything is in place, you should be able to run the AppsForYourDomainMigrationClient for the first time. Running it out of the box requires you to set some program arguments. --username --password --domain. It places 5 new emails in your inbox. If you look at the example in more detail, you will see, that it makes use of the <a href="http://code.google.com/apis/gdata/docs/batch.html">batch processing api</a>. Basically the code takes a String, parses it, puts it into a Rfc822Msg and this is put into a MailItemFeed which is processed by the MailItemService.batch() method. All the magic happens in the runSample() method. You see, that whatever we are going to push to google, should be a rfc822 compliant message. 
<br>
<br><b>Getting documents from domino/notes</b>
<br>
 The idea is to fetch a message from Notes/Domino, convert it to a rfc822 message and send it via the gdata-api to google. If you are unshure about the notes connection things (they get tricky from time to time .. see links below). Let's go.
<br><code>// initialize notes session<br>
  NotesThread.sinitThread();<br>
  Session session = NotesFactory.createSession();<br>
  session.setConvertMime(false);<br>
  // open your database<br>
  Database d = session.getDatabase(null, "folder/oldstuff.nsf");</code>
<br>
 Now we have to get all the documents one by one and let the magic happen.
<br><code>DocumentCollection inbox = d.getAllDocuments();<br>
  Document doc = inbox.getFirstDocument();<br>
  while (doc != null) \{<br>
  //...<br>
  doc = inbox.getNextDocument(doc);<br>
  \}</code>
<br>
 That is all you have to do. Now you are able to iterate over the complete document collection in one archive. But: All you have now is an instance of a lotus.domino.Document. Some handy methods are there but it's by far not enough to take it, serialise it and put it on the wire.
<br>
<br><b>From Document to MIMEEntity</b>
<br>
 First thing you will have to do with a single Document is to convert it to a so called lotus.domino.MIMEEntity. This is the starting point for all further processing. <code>MIMEEntity mime = doc.getMIMEEntity();</code> if the mail was already received via smtp this is most likely everything you have to do. If you have original domino Documents at hand, this will not work and mime will be null. Beginning with 8.5.1 you have a <code>public void convertToMIME(int conversiontype, long options)</code> method at the Document API. This will do the job for you. You can have different conversion types available. I am using the Document.CVT_RT_TO_PLAINTEXT_AND_HTML for multipart/alternative.
<br>
<br><b>From MIMEEntity to Rfc822Msg</b>
<br>
 Now you are only one step away from a Rfc822Msg. All you have to do now is to build a StringBuffer with all the RFC 822 requirements fulfilled. The tricky part are the headers. To get them you have to work with the lotus.domino.MIMEHeader object.<code>Vector headers = mime.getHeaderObjects();<br>
  for (int j = 0; j &lt; headers.size(); j++) \{ MIMEHeader header = (MIMEHeader) headers.elementAt(j); buffer.append(header.getHeaderName() + ": " + header.getHeaderValAndParams() + "\r\n"); \}</code>
<br>
 If you work through the Document you will see, that it has a child for every multipart part. So you have to make shure, you iterate over each child and add it to the buffer, too.<code>MIMEEntity child1 = mime.getFirstChildEntity();<br>
  while (child1 != null) \{<br>
  //...<br>
  if (child2 == null) \{<br>
  child2 = child1.getNextSibling();<br>
  if (child2 == null) \{<br>
  child2 = child1.getParentEntity();<br>
  if (child2 != null)<br>
  child2 = child2.getNextSibling();<br>
  \}<br>
  \}<br>
  child1 = child2;<br>
  \}</code>
<br>
 If you are working your way through attachments, you will find it usefull to have the <code>public void encodeContent(int encoding)</code> from the MIMEEntity to convert attachements to MIMEEntity.ENC_BASE64. Everything else will not work.
<br>
<br><b>From Rfc822Msg to MailItemEntry</b>
<br>
 Take your buffer and put it to the Rfc822Msg <code>Rfc822Msg rfcMsg = new Rfc822Msg(buffer.toString());</code>. Add it to a MailItemEntry <code>mailItem.setRfc822Msg(rfcMsg);</code>. 
<br>
<br><b>Lables and properties</b>
<br>
 You can apply labels and additional properties to the MailItemEntries. A label will show up in gmail as you are used to. I flaged all migrated emails with a "private" label like this <code>mailItem.addLabel(new Label("private"));</code>. It's also possible to take the original folder names of the Document and put it as label. You also can decide, if you want the message to appear as unread in the inbox or in the sent folder or whatever if you add additional properties like this: <code>mailItem.addMailProperty(MailItemProperty.INBOX);</code>.
<br>
<br><b>Sending mail</b>
<br>
 Use the provided Batch approach but send single mails within a single batch worked for me. Here is the basic approach: <code>BatchUtils.setBatchId(mailItem, "" + uniqueId);<br>
  BatchUtils.setBatchOperationType(mailItem,<br>
  BatchOperationType.INSERT);<br>
  MailItemFeed feeder = new MailItemFeed();<br>
  feeder.getEntries().add(mailItem);<br>
  MailItemFeed feedR = mailItemService.batch(domain,<br>
  destinationUser, feeder);</code>
<br>
 You can get a status of the submission <code>BatchStatus status = BatchUtils.getBatchStatus(returnedEntry);</code>. If something went wrong you will get a status unequal 201.
<br>
<br><b>Further thoughts</b>
<br>
 You should not try to send messages bigger than 5000000 bytes. Google will reject them. It's best to have some migration/error logging in place allowing you to manualy migrate failed documents. For me it was handy to log the documents <code>doc.getUniversalID()</code> for later migration. Another limit at Google are the requests per second. I don't know how high/low this exactly is, but you will get batch status erros if you exceed this limit. You should wait &gt;30sec after continuing with your submissions.
<br>
<br>
 Now you have your Domino/Notes documents in your Gmail account. It was fun to try this and you should give it a try yourself if you need too :)
<br>
<br><b>Links and readings</b>
<br><a href="http://code.google.com/apis/gdata/docs/developers-guide.html">Google Data Protocol Developer's Guide Overview</a>
<br><a href="http://www.ibm.com/developerworks/lotus/library/ls-Java_access_pt1/index.html" target="_blank">Java access to the Domino Objects</a>
<br><a href="http://www.w3.org/Protocols/rfc1341/7_2_Multipart.html" target="_blank">The Multipart Content-Type</a>
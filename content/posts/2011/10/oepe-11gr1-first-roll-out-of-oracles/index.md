---
title: "OEPE 11gR1 first roll out of Oracle's Public Cloud Tools - an early test drive"
date: 2011-10-28 07:37:00 +0000
layout: post
tags: ["cloud", "java", "Nuviaq", "oracle"]
slug: "oepe-11gr1-first-roll-out-of-oracles"
link: ":year/:month/:slug"
aliases:
 - ":year/:month/:slug:.html!"
url: /2011/10/oepe-11gr1-first-roll-out-of-oracles.html
---

The new <a href="http://www.oracle.com/technetwork/developer-tools/eclipse/overview/index.html" target="_blank">Oracle Enterprise Pack for Eclipse 11gR1</a> (11.1.1.8) aka OEPE was released and beside some very fancy and exciting new ADF features (Task Flows and Libraries) and updated Coherence integration you also find a little remark about the "Oracle Public Cloud Tools" (refer to <a href="http://www.oracle.com/technetwork/developer-tools/eclipse/overview/whatsnew-089747.html" target="_blank">OEPE release notes</a>). WOW! That is exactly what I was waiting for! Here we go. I tried to give it a very brief test drive.
<br>
<br><b>Setting up your project</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="create_new_cloud.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="266" src="create_new_cloud.jpg" width="320"></a>
</div> As expected it was not meant to be test driven at this point. It seems as if you "could" but you are missing a bunch of stuff, so I needed to trick around a lot to at least show you a bit of what will be possible. First step is to create a new "Oracle Public Cloud Web Project". Not a single word about Ear projects or other artifacts. This still seems very&nbsp;introductory&nbsp;and opens a lot of questions. But I was not going to wonder about this today and simply start over with what is there. After you have chosen the right project type you have to configure your cloud target. 
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="configure_your_cloud.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="287" src="configure_your_cloud.jpg" width="320"></a>
</div> This is done by specifying the a new or reusing an existing cloud target. Beside the service group and instance you have to specify an administrator and his password. Last and most problematic the wizard is asking for a "Cloud SDK".&nbsp;As you might imagine, this is not part of the OEPE distribution at this point so I had to trick around and create a couple of fancy named files (javacloud.jar,&nbsp;oracle.cloud.paas.api.jar and&nbsp;whitelist.jar) to make the wizard&nbsp;believe&nbsp;I have what he needs. Is it worth mentioning, that the "Test connection" facility will not work with this setup? ;) But it's there. You can also directly jump to open up your (trial?) account on <a href="">http://cloud.oracle.com</a>.
<br>
 Next you need to specify a local target. It's most likely a simple WLS installation you have to refer to. So I took what I have and pointed the wizard to my 11gR1 (10.3.5) install for that. I did somehow expect to be able to select a target domain with this dialog, too. But it was not asking for that. Some other&nbsp;weird&nbsp;stuff &nbsp;happened, and I am not sure if it is&nbsp;meant&nbsp;to work like this for the final version what probably will be out in the next OEPE (11.1.1.9 ?).
<br>
<br><b>Your first Oracle Java Cloud App</b>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="specify_local_target.jpg" imageanchor="1" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" height="288" src="specify_local_target.jpg" width="320"></a>
</div> That's it. Now you have your first Oracle Cloud web application in your latest OEPE. It's very unspectacular. A simple dynamic web project with WebLogic server as it's runtime. Without any special additional jars (at least non of the ones mentioned above; could also be because of my 'dirty' work there). You can create whatever resource you need and either run it on your local WebLogic or on the newly specified Oracle Public Cloud (in this case eiseleNetDemo.eiseleNet) which also directly appears under your Eclipse Servers tab. Some other things to mention:
<br>
<br><b>Access logs and control panel</b>
<br>
 You can access the control panel of your cloud in the same way as you can directly jump to online log viewers for your application and the java service.
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="cloud_control.jpg" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="62" src="cloud_control.jpg" width="400"></a>
</div>
<br>
 This is integrated with the server panel and you can simply "open" the dialog. 
<br>
<br><b>Whitelist scan</b>
<br>
 It will also be possible to test your applications before deployment with integrated whitelist scans. The whitelist tool can be called explicitly on a project (on demand scans to highlight coding violations). If configured with the full Cloud SDK correctly it will also support as-you-type validation, project build validation. You will see that the errors are reported in application source, the Problems view, and a new Whitelist violations view. 
<br>
<br>
<div class="separator" style="clear: both; text-align: center;">
 <a href="whitelist_scan.jpg" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="23" src="whitelist_scan.jpg" width="320"></a>
</div>
<br><b>Niviaq</b>
<br>
 Some classes that OEPE is missing in my dilettantish setup contain the Name Niviaq in it. According to google this is the East Kalaattisit name for "cloud". You hardly find any references to details on the net. Only some job offers in the bay-area telling your, that 
<br>
<blockquote>
 ...pre-packaged PaaS enablement offerings that we provide such as Nuviaq ...
</blockquote> I'm not sure why Oracle is releasing OEPE and it's obviously unusable Public Cloud features at this state. It makes me believe, that it could be a sign of a very tough timeline all related projects have to fulfill. The JDeveloper and NetBeans integration is announced and I personally am waiting for the Cloud SDK. So, let's stay hungry and foolish for clouds ;)
---
title: "Setting Timeout for the JAX-RS 2.0 / Resteasy Client"
date: 2014-12-06 06:40:00 +0000
layout: post
tags: ["javaee7", "Resteasy", "microservices"]
slug: "setting-timeout-for-jax-rs-20-resteasy-client"

url: /2014/12/setting-timeout-for-jax-rs-20-resteasy-client.html
---

Adam asked me about that at NetBeans Day in Munich. One part of the JAX-RS Client API isn't fully standardized but still very important looking at today's microservice architectures. I am talking about timeouts here. Adam showed <a href="" target="_blank">how to set them for Jersey</a> and I just needed to find out how to do the same with <a href="" target="_blank">Resteasy</a>.
<br>
<br>
<pre style="background-color: whitesmoke; border-radius: 4px; border: 1px solid rgb(204, 204, 204); box-sizing: border-box; margin-bottom: 10px; overflow: auto; padding: 9.5px; word-break: break-all; word-wrap: break-word;"><span style="color: #333333; font-family: Menlo, Monaco, Consolas, Courier New, monospace;"><span style="line-height: 18.5714282989502px; white-space: pre-wrap;">import javax.ws.rs.client.Client; import org.jboss.resteasy.client.jaxrs.ResteasyClient; import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder; Client client = new ResteasyClientBuilder() .establishConnectionTimeout(100, TimeUnit.SECONDS) .socketTimeout(2, TimeUnit.SECONDS) .build();</span><span style="line-height: 1.42857143;"> </span></span></pre>
<br><a href="http://docs.jboss.org/resteasy/docs/3.0.9.Final/javadocs/index.html?org/jboss/resteasy/client/jaxrs/ResteasyClientBuilder.html" target="_blank">ResteasyClientBuilder</a> is basically an abstraction for creating Clients which uses Apache Http Client under the covers. That's it.
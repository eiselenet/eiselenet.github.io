---
title: "Beyond the Hype: Building Actually Useful Platforms with the CNCF Maturity Model (and a Healthy Dose of Realism)"
date: 2025-02-17 10:45:00 +0000
layout: post
tags: ["DevEx", "developers", "Platform Engineering", "TVP"]
slug: "cncf-platform-engineering-maturity-model-tvp-guide"
link: "2025/02/cncf-platform-engineering-maturity-model-tvp-guide.html"
url: /2025/02/cncf-platform-engineering-maturity-model-tvp-guide.html
---

<a href="simple_vs_complex.jpeg" style="clear: right; float: right; margin-bottom: 1em; margin-left: 1em;"><img border="0" data-original-height="2048" data-original-width="2048" height="320" src="simple_vs_complex.jpeg" width="320"></a>Platform engineering is hot. Everyone's talking about internal developer platforms (IDPs), self-service infrastructure, and treating the platform "as a product." The CNCF's TAG App Delivery group even released a <a href="" rel="nofollow" target="_blank">Platform Engineering Maturity Model </a>to guide us.&nbsp;
<div>
 &nbsp;That's great – in theory. But as seasoned platform engineers and ops folks, we've seen enough maturity models come and go to know that they're rarely practical. We've all been burned by "check-the-box" exercises that lead to over-engineered, under-utilized platforms. So, while the CNCF model offers valuable insights, it's important to approach it with a critical eye and focus on delivering real value to developers. This post isn't a takedown of the CNCF model. It's a call for a more pragmatic, outcome-focused approach to platform engineering, grounded in the principles of the Thinnest Viable Platform (TVP).&nbsp; 
 <br>
 &nbsp;
 <br>
 <div>
  <h3>The CNCF Model: The Good, The Bad, and The Vague</h3> The CNCF model does a good job of outlining a progressive journey towards platform maturity, from ad-hoc tooling to a sophisticated, product-focused IDP. It highlights the importance of standardization, automation, and developer experience (DevEx). These are all good things. However, the model has some significant weaknesses: 
  <ul>
   <li><b>Fuzzy Boundaries:</b> The lines between maturity levels are often blurry. What exactly distinguishes "Level 2" from "Level 3"? Without clear entry and exit criteria, it's hard to measure progress, assign responsibility, and drive continuous improvement. We need concrete KPIs, not just narratives. Think: "Level 2 requires automated provisioning of environments with a lead time of under 1 hour, measured by x," not just "Level 2 has some automation."</li>
   <li><b>Too Much Tech, Not Enough People:</b> The model is heavily focused on tooling (Kubernetes, service meshes, CI/CD pipelines) and not enough on the organizational and cultural changes required for successful platform engineering. Building a platform is a transformation, not just a technology project. We need to address team structures, responsibilities, communication, and – crucially – how to get buy-in from developers and leadership.</li>
   <li><b>DevOps/SRE Overlap:</b> Where does DevOps and SRE end, and platform engineering begin? The model doesn't clearly differentiate. Platform engineering should build upon DevOps and SRE principles, extending them to create a product-focused approach to internal platforms. It is about abstraction, simplification, self-service, and providing golden paths.</li>
   <li><b>Incomplete Dimensions or Missing Criteria: </b> Many established maturity models measure across multiple dimensions—e.g., people, process, technology, and culture—and define clear goals or KPIs for each dimension as organizations progress. The CNCF model offers more of a narrative progression than a multi-dimensional approach with measurable indicators. That can lead to subjectivity or confusion about what “done” or “successful” looks like at each level.</li>
   <li><b>Granularity vs. Practical Usefulness: </b> I think that the model’s levels are too broad for day-to-day roadmapping. In more established frameworks (like ITIL v4), each progression explicitly details the processes and sub-processes that must be in place. The CNCF model gives higher-level descriptions that leave a fair bit to interpret.</li>
   <li><b>Limited Guidance on How to Advance: </b> While the model outlines what each level might look like, it doesn’t provide much prescriptive guidance about how to move from one level to the next. Many widely used frameworks include recommended practices, organizational structures, or coaching methods at each maturity stage (e.g., in the Scaled Agile Framework (SAFe) or CMMI training materials).</li>
   <li><b>The Kubernetes Assumption:</b> The model implicitly assumes a cloud-native, Kubernetes-centric world. That's fine for many, but what about organizations with hybrid environments or legacy systems? We need a more technology-agnostic perspective. The principles of platform engineering apply regardless of the underlying infrastructure.</li>
   <li><b>Checklist Mentality Risk:</b> Maturity models can easily become checklists. "We have these tools, therefore we're Level 3!" That's missing the point. We need to focus on outcomes – reduced lead times, improved developer satisfaction, increased deployment frequency – not just tool adoption.</li>
  </ul>
  <br>
  <h3>Introducing the Thinnest Viable Platform (TVP)</h3> The Thinnest Viable Platform (TVP) is an approach to counter the common pitfalls of over-engineering and "cargo cult" practices in platform engineering. It represents the absolute minimal collection of tools, processes, and APIs required to help developers to deliver value efficiently and reliably, deliberately avoiding any extraneous complexity. Embracing the TVP means starting small by concentrating on the most urgent developer pain points first, and then incrementally expanding the platform's capabilities based on concrete, demonstrated needs rather than hypothetical scenarios. This approach resist the urge to "gold-plating" your platform and actively avoids the inclusion of features that are not strictly essential. This is&nbsp; fostering a mindset of continuous prioritization, constantly questioning whether a proposed solution is truly the thinnest path to achieve the desired outcome. 
  <br>
  &nbsp;
  <br>
   The TVP isn't a one-time thing; it's an ongoing philosophy. It's about maintaining a laser focus on value delivery and avoiding unnecessary complexity at every stage of platform maturity.&nbsp; 
  <br>
  &nbsp;
  <br>
  <h3 style="text-align: left;">Applying TVP to the CNCF Model&nbsp;</h3>
  <div>
   To effectively apply the TVP concept to the CNCF Maturity Model, consider it as an integral part of each stage of development. 
   <ul>
    <li><b>Level 1 (Ad Hoc):</b> The TVP is a single, impactful automation. Solve one concrete problem.</li>
    <li><b>Level 2 (Scripting &amp; Basic Automation):</b> The TVP is a set of reusable scripts that automate the "paved road" – the most common workflows. Avoid custom solutions for edge cases.</li>
    <li><b>Level 3 (Self-Service &amp; Standardization):</b> The TVP offers self-service for core services, providing a "golden path" for common use cases. Resist the urge to offer every possible option.</li>
    <li><b>Level 4 (Advanced Automation &amp; Optimization):</b> The TVP expands based on data and user feedback, not technology trends. Continuously evaluate complexity and look for opportunities to simplify.</li>
    <li><b>Level 5 (Platform as a Product):</b> The TVP philosophy is ingrained in the platform's product management process. Every feature is evaluated based on its value to users and its impact on platform simplicity.</li>
   </ul>
   <br>
   <h3 style="text-align: left;">Practical Recommendations for Platform Teams&nbsp;</h3>
   <div>
    To put the CNCF Platform Engineering Maturity Model and the TVP concept into practice, platform teams need more than just theoretical frameworks. These practical recommendations offer a roadmap for building and managing a successful platform, aligned with the model's progression but grounded in real-world experience. 
    <ol>
     <li><b>Start with Outcomes:</b> Define clear, measurable goals for your platform. What problems are you trying to solve? How will you measure results?</li>
     <li><b>Use the TVP:</b> Build the smallest platform that meets your developers' current needs. Iterate based on feedback.</li>
     <li><b>Focus on People and Process:</b> Invest as much effort in organizational change management as you do in technology. Build a strong platform team, encourage collaboration, and get support from stakeholders.</li>
     <li><b>Measure:</b> Track key metrics (lead time, deployment frequency, developer satisfaction) and use them to drive continuous improvement.</li>
     <li><b>Say "No":</b> Not every feature request is worth implementing. Prioritize and focus on the highest-impact changes.</li>
     <li><b>Learn from Others:</b> Connect with other platform engineering teams, share best practices, and avoid doing things from scratch that others have already done.</li>
     <li><b>Iterate and Adapt:</b> Problems will arise. The ability to change course and learn is crucial.</li>
    </ol>
    <br>
    <h3>Moving Forward</h3> The CNCF Platform Engineering Maturity Model is a valuable starting point, but it's not a definitive guide. We need to approach it critically, use the TVP philosophy, and adapt it to our specific organizational context. Platform engineering is a journey, not a destination. By focusing on outcomes, being simple, and continuously learning, we can build platforms that truly help developers and ultimately drive business value. Let's build useful platforms, not just impressive ones.
   </div>
  </div>
 </div>
</div>
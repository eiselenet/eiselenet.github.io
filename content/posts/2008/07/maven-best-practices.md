---
title: "Maven Best Practices"
date: 2008-07-19 06:06:00 +0000
layout: post
tags: ["softwareentwicklung", "maven"]
slug: "2008-07-19-maven-best-practices"
url: /2008/07/maven-best-practices.html
---

Den Einsazt von maven als Experiment zu bezeichnen ist warscheinlich schlicht zu gewagt. Dafür existiert es schon zu lange und wird in vielen Projekten erfolgreich verwendet. In Summe ist es ein wirklich netter Ansatz um modulorientiert Software zu entwicklen und zu builden. Alles in Allem finde ich es nach den ersten Wochen des wirklich harten Projekteinsatzes durchaus charmant.
<br>
<br>
Ein paar Punkte fallen allerdings immer wieder unangenehm auf. Aus ihnen kann man schnell und einfach ein paar best practices ableiten:
<br>
<br>
<ul>
 <br>
 <li>Keine Snapshot builds.<br><br>
  Maven gibt nicht umsonst eine Warnung aus. Wenn es sich gar nicht vermeiden läßt, dann sollte man auf alle Fälle einen halbwegs getesteten Snapshot verwenden und den manuell im Repository bereitstellen.<br></li>
 <br>
 <li>Grosse Teams brauchen einen Proxy.<br><br>
  Je größer das Entwicklungsteam ist, desto eher sollte man über einen maven proxy nachdenken. Eine stabile und brauchbare Lösung kommt von Codehaus (<a href="" target="_blank">http://maven-proxy.codehaus.org/</a>). Nett ist, dass man diesen sowohl standalone als auch als WAR auf nahezu jedem beliebigen Appserver betreiben kann. <br></li>
 <br>
 <li>Modulabhängigkeiten sind böse.<br><br>
  Modulabhängigkeiten mit maven machen einfach keinen Spass. Vor allem dann nicht, wenn man aus maven heraus mit bspw. eclipse:eclipse auch die Entwicklungsumgebung und ihre Abhänigkeiten definiert. Bei jeder Änderung eines Moduls sind potentiell andere Module betroffen. Für das Neuanlegen/Updaten der Entwicklungsumgebungen geht ganz schön viel Zeit drauf. Allein das sollte Grund genug sein, die maven Module entlang den Softwarekomponenten zu schneiden. Damit macht man den Entwicklern auch das (ungewollte) Verweben der Komponenten schon beim Build sehr unangenehm. Es ist damit quasi ein pre-build-dependency check :)<br></li>
 <br>
 <li>Maven ist nicht ant.<br><br>
  Wer lange Jahre mit ant seine Projekte gebaut hat, der kommt mit maven erfahrungsgemäß nicht sofort klar. Die Dokumentation von maven ist sehr verstreut und die tatsächlichen Fähigkeiten muss man sich doch in der Praxis erarbeiten. Es empfiehlt sich, dass im Projekt ein Verantwortlicher mit maven Erfahrung regelmäßig überprüft, ob und wie man was besser machen kann. <br>
  Darüber hinaus gibt es Dinge, die man mit maven einfach nicht machen kann. So kann man pro Modul bspw. nur ein Artefakt (*.jar, *.war, etc.) erzeugen. Benötigt man mehr, dann muss man tatsächlich auf ant zurückgreifen<br></li>
 <br>
 <li>Maven und Opensource lieben sich.<br><br>
  Abseits von Opensource Entwicklungsumgebungen wird der Einsatz von maven schnell unbequem. Setzt man beispielsweise auf WebSphere, Bea und Co, dann fehlen einem die gewohnt kompfortablen ant Targets zur Erstellung von Deployment Descriptoren und Ähnliches. Auch wenn es für den RAD schon ein <a href="http://maven.apache.org/plugins/maven-eclipse-plugin/rad-mojo.html" target="_blank">goal im maven</a> gibt. Hier muss man tatsächlich noch ein wenig investieren, damit auch auf weniger quelloffenen Umgebungen eine nahtlose Integration möglich ist.<br></li>
 <br>
</ul>
<br>
<br>
Soviel für Heute. Mehr in den kommenden Woche, wenn Zeit bleibt.
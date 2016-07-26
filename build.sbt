//
// Copyright 2014 Groupon.com
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
import ReleaseTransformations._

sbtPlugin := true

organization := "com.arpnetworking"

name := "sbt-typescript"

scalaVersion := "2.10.5"

libraryDependencies ++= Seq(
  "org.webjars" % "mkdirp" % "0.3.5",
  "org.webjars.npm" % "typescript" % "2.0.0",
  "com.typesafe" % "jstranspiler" % "1.0.0"
)

resolvers ++= Seq(
  Resolver.mavenLocal,
  "Typesafe Releases Repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.url("sbt snapshot plugins", url("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots"))(Resolver.ivyStylePatterns),
  Resolver.sonatypeRepo("snapshots"),
  "Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/snapshots/"
)

addSbtPlugin("com.typesafe.sbt" %% "sbt-js-engine" % "1.1.3")

scalacOptions += "-feature"

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

pomIncludeRepository := { _ => false}

pomExtra := (
  <url>https://github.com/ArpNetworking/sbt-typescript</url>
    <licenses>
      <license>
        <name>Apache 2</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:ArpNetworking/sbt-typescript.git</url>
      <connection>scm:git:git@github.com:ArpNetworking/sbt-typescript.git</connection>
    </scm>
    <developers>
      <developer>
        <id>barp</id>
        <name>Brandon Arp</name>
        <url>http://www.arpnetworking.com</url>
      </developer>
    </developers>)

scriptedSettings

scriptedLaunchOpts <+= version apply { v => s"-Dproject.version=$v" }

scriptedBufferLog := false

credentials += Credentials("Sonatype Nexus Repository Manager",
        "oss.sonatype.org",
        System.getenv("OSSRH_USER"),
        System.getenv("OSSRH_PASS"))

useGpg := true

pgpPassphrase in Global := Option(System.getenv("GPG_PASS")).map(_.toCharArray)

sonatypeProfileName := "com.arpnetworking"

releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    setNextVersion,
    commitNextVersion,
    pushChanges)

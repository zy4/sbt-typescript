libraryDependencies <+= sbtVersion { sv =>
  "org.scala-sbt" % "scripted-plugin" % sv
}

resolvers += Resolver.typesafeRepo("releases")

addSbtPlugin("com.arpnetworking" % "sbt-typescript" % "0.3.5")

addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.7")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.1")

addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.0")


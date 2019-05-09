name := "quickstart"

version := "1.0"

scalaVersion := "2.12.8"

val finagleRelease = "18.2.0"

resolvers += Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns)

libraryDependencies := Seq(
  "com.twitter"           %% "finagle-http" % finagleRelease,
  "com.twitter"           %% "finagle-core" % finagleRelease,
  "com.twitter"           %% "twitter-server" % finagleRelease,
  "com.twitter"           %% "finagle-http" % finagleRelease,
  "com.twitter"           %% "finagle-stats" % finagleRelease,
  "com.twitter"           %% "finagle-zipkin" % finagleRelease,
  "com.twitter"           %% "util-logging" % finagleRelease,
  "com.twitter"           %% "util-stats" % finagleRelease,
  "io.dropwizard.metrics" % "metrics-core" % "4.1.0",
  "com.typesafe"          % "config" % "1.2.1",
  "org.slf4j"             % "slf4j-log4j12" % "1.7.26",
  "com.fasterxml.jackson.core"     % "jackson-annotations" % "2.8.4",
  "org.scalatest"         %% "scalatest" % "3.0.0" % "test",
  "org.mockito"           % "mockito-core" % "1.9.5" % "test"
)

test in assembly := {}

assemblyJarName in assembly := "app.jar"

name := "quickstart"

version := "1.0"

scalaVersion := "2.12.2"

val finagleRelease = "6.45.0"

resolvers += Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns)

libraryDependencies := Seq(
  "com.twitter" %% "finagle-http" % finagleRelease,
  "com.twitter" %% "finagle-core" % finagleRelease,
  "com.twitter" %% "twitter-server" % "1.30.0",
  "com.twitter" %% "finagle-http" % finagleRelease,
  "com.twitter" %% "finagle-stats" % finagleRelease,
  "com.twitter" %% "finagle-zipkin" % finagleRelease,
  "com.twitter" %% "util-logging" % finagleRelease,
  "io.dropwizard.metrics" % "metrics-core" % "3.2.0",
  "com.typesafe"  % "config" % "1.2.1",
  "org.slf4j"     % "slf4j-log4j12" % "1.7.10",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "org.mockito"   % "mockito-core" % "1.9.5" % "test"
)

jacoco.settings

assemblyMergeStrategy in assembly <<= (assemblyMergeStrategy in assembly) {
  (old) => {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
  }
}

test in assembly := {}

assemblyJarName in assembly := "app.jar"

import AssemblyKeys._

assemblySettings

organization := "fast-data"

name := """Fast Data in the IoT"""

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  Resolver.sonatypeRepo("public"),
  Resolver.sbtPluginRepo("releases"),
  Resolver.url("bintray-sbt-plugins", url("http://dl.bintray.com/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
)

val sparkVersion = "2.0.1"
val akkaVersion = "2.3.9"
val sprayVersion = "1.3.3"
val cassandraDriverVersion = "3.1.1"

libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-hive" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "org.apache.spark" %% "spark-streaming" % sparkVersion,
    "org.apache.spark" %% "spark-mllib" % sparkVersion,
    "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "io.spray" %% "spray-can" % sprayVersion,
    "io.spray" %% "spray-routing" % sprayVersion,
    "io.spray" %% "spray-testkit" % sprayVersion % "test",
    "io.spray" %% "spray-json" % "1.3.2",
    "org.scalatest" %% "scalatest" % "2.2.6" % "test",
    "com.datastax.cassandra" % "cassandra-driver-core" % cassandraDriverVersion,
    "com.datastax.cassandra" % "cassandra-driver-mapping" % cassandraDriverVersion,
    "org.scalatest" %% "scalatest" % "3.0.0-M15"
)

assemblySettings

jarName in assembly := "fast-data.jar"

mergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
  case "log4j.properties"                                  => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
  case "reference.conf"                                    => MergeStrategy.concat
  case _                                                   => MergeStrategy.first
}

fork in run := true

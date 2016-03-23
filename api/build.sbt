

lazy val commonSettings = Seq(
  organization := "gni.kraps",
  version := "0.2-SNAPSHOT",
  scalaVersion := "2.11.7"
)

lazy val api = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "gni.kraps.api",
    crossPaths := false,
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF","io.netty.versions.properties") => MergeStrategy.first
      case PathList("org", "slf4j", "impl", xs @ _*) => MergeStrategy.first
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    },
    libraryDependencies ++= {
      val akkaVersion = "2.3.9"
      val sprayVersion = "1.3.3"
      val kafkaVersion = "0.9.0.1"
      Seq(
        "org.apache.kafka" %% "kafka" % kafkaVersion,
        "com.datastax.cassandra" % "cassandra-driver-core" % "3.0.0",
        "com.typesafe.akka" %% "akka-actor" % akkaVersion,
        "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
        "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
        "io.spray" %% "spray-can" % sprayVersion,
        "io.spray" %% "spray-routing" % sprayVersion,
        "io.spray" %% "spray-testkit" % sprayVersion % "test",
        "io.spray" %% "spray-json" % "1.3.2",
        "org.scalatest" %% "scalatest" % "2.2.6" % "test",
        "ch.qos.logback" % "logback-classic" % "1.1.6",
        "ch.qos.logback" % "logback-core" % "1.1.6",
        "org.json4s" %% "json4s-native" % "3.3.0"
      )
    }
  )


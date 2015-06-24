import sbt._

object Dependencies {
  val resolutionRepos = Seq(
    "ScalaTools snapshots at Sonatype" at "https://oss.sonatype.org/content/repositories/snapshots/",
    "Concurrent Maven Repo" at "http://conjars.org/repo"
  )

  object V {
    val scalding = "0.15.0"
    val hadoop = "1.2.1"
    val logback = "1.1.3"
    val specs2 = "2.4.17"
  }

  object Libraries {
    val scaldingCore = "com.twitter" %% "scalding-core" % V.scalding exclude("com.esotericsoftware.minlog", "minlog")
    val hadoopCore = "org.apache.hadoop" % "hadoop-core" % V.hadoop % "provided"
    val logback = "ch.qos.logback" % "logback-classic" % V.logback
    val specs2 = "org.specs2"  %% "specs2-core" % V.specs2 % "test"
  }
}

import sbt.Keys._
import sbt._

object BuildSettings {
  // Basic settings for our app
  lazy val basicSettings = Seq[Setting[_]](
    organization  := "com.example",
    scalaVersion  := "2.11.5",
    scalacOptions := Seq("-deprecation", "-encoding", "utf8"),
    scalacOptions in Test ++= Seq("-Yrangepos"),
    resolvers     ++= Dependencies.resolutionRepos
  )

  lazy val buildSettings = basicSettings
}

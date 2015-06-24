import sbt._
import Keys._

import Dependencies._
import BuildSettings._

lazy val project = Project("scalding-project-template", file("."))
  .settings(buildSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      Libraries.scaldingCore,
      Libraries.hadoopCore,
      Libraries.logback,
      Libraries.specs2
      // Add your additional libraries here (comma-separated)...
    ),
    mainClass in assembly := Some("com.twitter.scalding.Tool"),
    assemblyOutputPath in assembly := file(s"target/${name.value}-${version.value}-assembly.jar")
  )

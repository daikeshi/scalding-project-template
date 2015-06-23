import sbt._
import Keys._

import Dependencies._
import BuildSettings._

lazy val project = Project("scalding-example-project", file("."))
  .settings(buildSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      Libraries.scaldingCore,
      Libraries.hadoopCore,
      Libraries.specs2
      // Add your additional libraries here (comma-separated)...
    ),
    assemblyJarName in assembly := { name.value + "-" + version.value + "-assembly.jar" }
  )

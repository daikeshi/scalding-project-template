import sbt._
import Keys._

import Dependencies._
import Libraries._

lazy val buildSettings = Seq[Setting[_]](
  organization  := "com.example",
  scalaVersion  := "2.11.5",
  scalacOptions := Seq("-encoding", "utf-8", "-deprecation", "-unchecked", "-feature"),
  scalacOptions in Test ++= Seq("-Yrangepos"),
  resolvers     ++= Dependencies.resolutionRepos
)

lazy val assemblySettings = Seq[Setting[_]](
  mainClass in assembly := Some("com.twitter.scalding.Tool"),
  test in assembly := {},
  assemblyOutputPath in assembly := file(s"assembly-jars/${name.value}-${version.value}-assembly.jar")
)


lazy val commonDeps = Seq(
  hadoopCore,
  scaldingCore,
  logback,
  specs2 % Test
)

lazy val parquetDeps = Seq(
  hadoopCore,
  parquetCascading,
  specs2 % Test
)

lazy val avroDeps = Seq(
  hadoopCore,
  scaldingAvro,
  specs2 % Test
)

lazy val all = (project in file("."))
  .aggregate(basic, parquet, avro)
  .settings(buildSettings: _*)
  .settings(
    test := {},
    publish := {},
    publishLocal := {}
  )

lazy val basic = (project in file("scalding-core-template"))
  .settings(buildSettings: _*)
  .settings(assemblySettings: _*)
  .settings(
    libraryDependencies ++= commonDeps
  )

lazy val parquet = (project in file("scalding-parquet-template"))
  .settings(buildSettings: _*)
  .settings(assemblySettings: _*)
  .settings(
    libraryDependencies ++= parquetDeps
  )
  .dependsOn(basic)

lazy val avro = (project in file("scalding-avro-template"))
  .settings(buildSettings: _*)
  .settings(assemblySettings: _*)
  .settings(
    libraryDependencies ++= avroDeps
  )
  .dependsOn(basic)

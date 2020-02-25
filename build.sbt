
name := "accounts"
organization := "com.banking"
version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play"  %% "scalatestplus-play"   % "5.0.0"   % Test,
  "org.scalacheck"          %% "scalacheck"           % "1.14.0"  % "test"
)

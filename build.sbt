name := "catalogue-server"

version := "Hydrogen"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
    "org.specs2" %% "specs2-core" % "3.8.3" % "test",
    "com.typesafe.slick" %% "slick" % "3.1.1",
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    ws
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

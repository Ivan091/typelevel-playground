name := "typelevel-playground"
scalaVersion := "3.3.1"

Global / fork := true
Global / cancelable := true
Global / connectInput := true
Global / onChangedBuildSource := ReloadOnSourceChanges

javaOptions ++= Seq("-Xmx8G")

scalacOptions ++= Seq("-old-syntax", "-no-indent", "-Xfatal-warnings")

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect" % "3.5.3",
  "org.typelevel" %% "cats-effect-testing-scalatest" % "1.4.0" % Test,
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "org.scalatestplus" %% "mockito-4-6" % "3.2.15.0" % Test,
  "io.circe" %% "circe-generic" % "0.14.5",
  "io.circe" %% "circe-parser" % "0.14.5",
  "com.typesafe" % "config" % "1.4.2",
  "org.typelevel" %% "log4cats-slf4j" % "2.5.0",
  "ch.qos.logback" % "logback-classic" % "1.4.12" % Runtime,
  "org.typelevel" %% "shapeless3-deriving" % "3.0.1",
  "org.tpolecat" %% "typename" % "1.0.0",
  "co.fs2" %% "fs2-core" % "3.9.4"
)

import sbt._
import Keys._

object Dependencies {
    lazy val config = "com.typesafe" % "config" % "1.3.1"
    lazy val scalaj = "org.scalaj" %% "scalaj-http" % "2.3.0"
    lazy val joda = "joda-time" % "joda-time" % "2.9.4"
    lazy val elastic4s = "com.sksamuel.elastic4s" %% "elastic4s-core" % "2.3.2"
    lazy val log4j = "log4j" % "log4j" % "1.2.17"
    lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"

    val coreDeps: Seq[ModuleID] = Seq(
        config,
        scalaj,
        joda,
        elastic4s,
        log4j
    )

    val appDeps: Seq[ModuleID] = Seq()

    val updaterDeps: Seq[ModuleID] = Seq()
}

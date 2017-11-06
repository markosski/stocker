import sbt._
import Keys._

object Dependencies {
    lazy val config = "com.typesafe" % "config" % "1.3.1"
    lazy val scalaj = "org.scalaj" %% "scalaj-http" % "2.3.0"
    lazy val joda = "joda-time" % "joda-time" % "2.9.4"
    lazy val jodaConvert = "org.joda" % "joda-convert" % "1.9.2"
    lazy val esCore = "com.sksamuel.elastic4s" %% "elastic4s-core" % "5.5.2"
    lazy val esTcp = "com.sksamuel.elastic4s" %% "elastic4s-tcp" % "5.5.2"
    lazy val log4jCore = "org.apache.logging.log4j" % "log4j-core" % "2.9.1"
    lazy val log4jApi = "org.apache.logging.log4j" % "log4j-api" % "2.9.1"
    lazy val log4jToSlf = "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.9.1"
    lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"

    val coreDeps: Seq[ModuleID] = Seq(
        config,
        scalaj,
        esCore,
        esTcp,
        log4jApi,
        log4jCore,
        log4jToSlf
    )

    val portfolioDeps: Seq[ModuleID] = Seq(
    )

    val appDeps: Seq[ModuleID] = Seq()

    val updaterDeps: Seq[ModuleID] = Seq()

    val utilDeps: Seq[ModuleID] = Seq(
        joda,
        jodaConvert
    )

}

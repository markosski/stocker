import Dependencies._

lazy val commonSettings = Seq(
    version := "0.1",
    scalaVersion := "2.11.8"
)

lazy val root = (project in file(".")).
        settings(commonSettings: _*).
        settings(
            name := "stocker"
        ).
        dependsOn(core, app, updater)

lazy val core = (project in file("core")).
        settings(commonSettings: _*).
        settings(
            name := "stocker-core",
            libraryDependencies ++= coreDeps
        )

lazy val app = (project in file("app")).
        settings(commonSettings: _*).
        settings(
            name := "stocker-app",
            libraryDependencies ++= appDeps
        ).
        dependsOn(core)

lazy val updater = (project in file("updater")).
        settings(commonSettings: _*).
        settings(
            name := "stocker-updater",
            libraryDependencies ++= updaterDeps
        ).
        dependsOn(core)

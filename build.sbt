//Global / logLevel := Level.Debug

lazy val VA = config("version-a").extend(Runtime)
lazy val VB = config("version-b").extend(Runtime)

val commonSettings = Seq(
  organization := "labo",
  name := "labo-sbt",
  scalaVersion := "2.12.20",
  libraryDependencies += "org.playframework" %% "play-json" % "3.0.4",
  libraryDependencies += "org.typelevel" %% "cats-core" % "0.8.1" % VA,
  libraryDependencies += "org.typelevel" %% "cats-core" % "2.12.0" % VB
)

// /!\ Pour faire publishLocal pour un Configuration. /!\
val forRunAndPublishInConf =
  Defaults.configSettings ++
    sbt.plugins.IvyPlugin.projectSettings ++
    addArtifact(packageBin / artifact, packageBin) ++ Seq(
      publishLocal := { publishLocal.dependsOn(sbt.Keys.`package`).value }
    )

lazy val root = (project in file("."))
  .settings(commonSettings)
  .configs(VA, VB)
  .settings(
    // --- VA ---
    inConfig(VA)(
      forRunAndPublishInConf ++
        Seq(
          unmanagedSourceDirectories += (baseDirectory.value / "src" / "main" / "scala-va")
        )
    ),
    // --- VB ---
    inConfig(VB)(
      forRunAndPublishInConf ++
        Seq(
          unmanagedSourceDirectories += (baseDirectory.value / "src" / "main" / "scala-vb")
        )
    )
  )

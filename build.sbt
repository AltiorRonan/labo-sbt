lazy val VA = config("verA").extend(Runtime)
lazy val VB = config("verB").extend(Runtime)

val commonSettings = Seq(
  organization := "com.example",
  scalaVersion := "2.12.20",
  libraryDependencies += "org.playframework" %% "play-json" % "3.0.4",
  libraryDependencies += "org.typelevel" %% "cats-core" % "0.8.1" % VA,
  libraryDependencies += "org.typelevel" %% "cats-core" % "2.12.0" % VB
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .configs(VA, VB)
  .settings(
    // --- VA ---
    inConfig(VA)(
      Defaults.configSettings ++
        Defaults.baseTasks ++
        Defaults.projectTasks ++
        Seq(
          unmanagedSourceDirectories += (baseDirectory.value / "src" / "main" / "scala-va")
        )
    ),
    // --- VB ---
    inConfig(VB)(
      Defaults.configSettings ++
        Defaults.baseTasks ++
        Defaults.projectTasks ++
        Seq(
          unmanagedSourceDirectories += (baseDirectory.value / "src" / "main" / "scala-vb")
        )
    )
  )

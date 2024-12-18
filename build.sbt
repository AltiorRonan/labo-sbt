lazy val VA = config("verA").extend(Runtime)
lazy val VB = config("verB").extend(Runtime)


val commonSettings = Seq(
  organization := "com.example",
  scalaVersion := "2.12.20",
  libraryDependencies += "org.playframework" %% "play-json" % "3.0.4",
  //// --- VA ---
  //VA / libraryDependencies += "org.typelevel" %% "cats-core" % "0.8.1",
  //VA / managedSourceDirectories += (baseDirectory.value / "src" / "main" / "scala-va"),
  //VA / artifactName := { (_, _, _) => "my-project-versionA.jar" },
  //// --- VB ---
  //VB / libraryDependencies += "org.typelevel" %% "cats-core" % "2.12.0",
  //VB / managedSourceDirectories += (baseDirectory.value / "src" / "main" / "scala-vb"),
  //VB / artifactName := { (_, _, _) => "my-project-versionB.jar" }
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .configs(VA, VB)
  //.settings(
  //      libraryDependencies += "org.typelevel" %% "cats-core" % "0.8.1" % VA,
  //      libraryDependencies += "org.typelevel" %% "cats-core" % "2.12.0" % VB,
  //    )
  .settings(
    // --- VA ---
    inConfig(VA)(
      Seq(
        libraryDependencies += "org.typelevel" %% "cats-core" % "0.8.1" ,
        unmanagedSourceDirectories += (baseDirectory.value / "src" / "main" / "scala-va"),
        artifactName := { (_, _, _) => "my-project-versionA.jar" }
      )
    ),
    // --- VB ---
    inConfig(VB)(
      Seq(
        //libraryDependencies += "org.typelevel" %% "cats-core" % "2.12.0" ,
        unmanagedSourceDirectories += (baseDirectory.value / "src" / "main" / "scala-vb"),
        artifactName := { (_, _, _) => "my-project-versionB.jar" }
      )
    )
  )

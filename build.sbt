//Global / logLevel := Level.Debug

lazy val VA = config("version-a").extend(Runtime)
lazy val VB = config("version-b").extend(Runtime)

//Global / publishLocal / skip := false

//val publishLocalAll = taskKey[Unit]("Publish all projects")
//VA / publishLocalAll := publishLocal
//  .all(ScopeFilter(inAnyProject, inAnyConfiguration))
//  .value

val commonSettings = Seq(
  organization := "labo",
  scalaVersion := "2.12.20",
  libraryDependencies += "org.playframework" %% "play-json" % "3.0.4",
  libraryDependencies += "org.typelevel" %% "cats-core" % "0.8.1" % VA,
  libraryDependencies += "org.typelevel" %% "cats-core" % "2.12.0" % VB
  //ivyConfigurations += VA,
  //VA / publishLocal / skip := false,
  //VA / publishArtifact := true,
  //VA / makePomConfiguration := makePomConfiguration.value.withConfigurations(
  //  Configurations.defaultMavenConfigurations :+ VA
  //)
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .configs(VA, VB)
  //.configs(Configurations.default: _*)
  //.configs(Configurations.defaultInternal: _*)
  //.configs(Configurations.defaultMavenConfigurations: _*)
  .settings(
    // --- VA ---
    inConfig(VA)(
      Defaults.configSettings ++
        //Defaults.baseTasks ++
        //Defaults.projectTasks ++
        //Defaults.packageConfig ++
        //Defaults.packageBase ++
        //Defaults.coreDefaultSettings ++
        //Defaults.testSettings ++
        //Defaults.runnerSettings ++
        //Classpaths.ivyPublishSettings ++
        //Classpaths.ivyBaseSettings ++
        //Classpaths.configSettings ++
        //Classpaths.jvmBaseSettings ++
        //Classpaths.jvmPublishSettings ++
        sbt.plugins.IvyPlugin.projectSettings ++
        //sbt.plugins.IvyPlugin.buildSettings ++
        //sbt.plugins.IvyPlugin.globalSettings ++
        addArtifact(VA / packageBin / artifact, packageBin) ++

        Seq(
          name := "labo-sbt-in-config-a",
          unmanagedSourceDirectories += (baseDirectory.value / "src" / "main" / "scala-va"),
          //publishArtifact := true,
          //publishLocal / skip := false,

          //Compile / packageBin / publishArtifact := true,
          //packageBin / publishArtifact := true,
          //packageDoc / publishArtifact := true,

          //packageSrc / publishArtifact := true,
          publishLocal := { publishLocal.dependsOn(sbt.Keys.`package`).value }

          //artifactName := { (_, _, _) => "my-project-versionA.jar" }
        )
    ),
    // --- VB ---
    inConfig(VB)(
      Defaults.configSettings ++
        sbt.plugins.IvyPlugin.projectSettings ++
        addArtifact(VB / packageBin / artifact, packageBin) ++
        Seq(
          name := "labo-sbt-in-config-b",
          unmanagedSourceDirectories += (baseDirectory.value / "src" / "main" / "scala-vb"),
          publishLocal := { publishLocal.dependsOn(sbt.Keys.`package`).value }
        )
    )
  )

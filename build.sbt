// See README.md for license details.

ThisBuild / scalaVersion     := "2.12.14"
ThisBuild / version          := "1.0.0"
ThisBuild / organization     := "com.tsnlab.fpu_playground_01"
ThisBuild / transitiveClassifiers := Seq(Artifact.SourceClassifier)
// ThisBuild / scalafixScalaBinaryVersion := CrossVersion.binaryScalaVersion(scalaVersion.value)


resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases")
)

lazy val commonSettings = Seq(
  libraryDependencies ++= Seq(
    "edu.berkeley.cs" %% "chisel3" % "3.5.+",
    "edu.berkeley.cs" %% "chiseltest" % "0.5.+" % "test",
    "org.easysoc" %% "layered-firrtl" % "1.1.+",
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  ),
  scalacOptions ++= Seq(
    "-Xsource:2.11",
    // Enables autoclonetype2 in 3.4.x (on by default in 3.5)
    "-P:chiselplugin:useBundlePlugin",
    "-language:reflectiveCalls",
    "-deprecation",
    "-feature",
    "-Xcheckinit"
  ),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full),
  addCompilerPlugin("edu.berkeley.cs" % "chisel3-plugin" % "3.5.+" cross CrossVersion.full),
)

//lazy val `api-config-chipsalliance` = (project in file("rocket-chip/api-config-chipsalliance/build-rules/sbt"))
//  .settings(commonSettings)

//lazy val hardfloat = (project in file("berkeley-hardfloat"))
//  .settings(commonSettings)

lazy val hardfloat_fudian = (project in file("fudian"))
  .settings(commonSettings)

//lazy val rocketMacros = (project in file("rocket-chip/macros"))
//  .settings(commonSettings)

//lazy val `rocket-chip` = (Project("rocket-chip", file("rocket-chip/src")))
//  .settings(commonSettings)
//  .settings(
//    Compile / scalaSource := baseDirectory.value / "main" / "scala",
//    Compile / resourceDirectory := baseDirectory.value / "main" / "resources"
//  )
//  .dependsOn(rocketMacros)
//  .dependsOn(apicfg)
//  .dependsOn(hardfloat)
//  //.dependsOn(`api-config-chipsalliance`)

//lazy val rocketChip = RootProject(file("rocket-chip"))

//// Let rocket chip handle their stuff by themselves
//lazy val `rocket-chip` = RootProject(file("rocket-chip"))

//lazy val fpuexample = (Project("FPUExample", base = file(".")))
//  .settings(commonSettings)
//  .dependsOn(`rocket-chip`)

lazy val root = (project in file("."))
  .settings(commonSettings, Seq(name := "chisel-fpu")).dependsOn(hardfloat_fudian)
//  .dependsOn(`api-config-chipsalliance`)
//  .dependsOn(rocketChip)
//  .dependsOn(`rocket-chip`)

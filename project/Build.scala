import sbt._, Keys._

object RootBuild extends Build {
  lazy val root = Project(
    id = "functional-programing-in-scala-1",
    base = file("."))
    .aggregate(example, forcomp, funsets, objsets, patmat, recfun)

  def project(id: String) = Project(
    id = id,
    base = file(id))

  lazy val example = project("example")
  lazy val forcomp = project("forcomp")
  lazy val funsets = project("funsets")
  lazy val objsets = project("objsets")
  lazy val patmat = project("patmat")
  lazy val recfun = project("recfun")

  scalaVersion := "2.11.7"
  name := "functional-programing-in-scala-1"
  version := "0.0.1"
}
package fr.altior.labo.va

import cats.implicits._

import fr.altior.labo.DoSomething

final case class VaDoSomething() extends DoSomething {
  override def run(): Unit =
    println(
      (
        1.some,
        2.some
      ).map2(_ + _)
    )
}

// Package: $ sbt "~VA/package"
// Run:     $ sbt "VA/runMain fr.altior.labo.va.MainVa"
object MainVa {

  def main(args: Array[String]): Unit = {
    VaDoSomething().commonRun("Version A")
  }
}




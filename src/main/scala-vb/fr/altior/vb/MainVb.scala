package fr.altior.vb

import cats.syntax.all._

import fr.altior.labo.DoSomething

final case class VbDoSomething() extends DoSomething {
  override def run(): Unit =
    println(
      (
        1000.some,
        2000.some
      ).mapN(_ + _)
    )
}

object MainVb {

  def main(args: Array[String]): Unit = {
    VbDoSomething().commonRun("Version B")
  }
}

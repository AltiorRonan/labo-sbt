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

object MainVa {

  def main(args: Array[String]): Unit = {
    fdsfsqd
    VaDoSomething().commonRun("Version A")
  }

}




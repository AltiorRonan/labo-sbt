package fr.altior.labo

import play.api.libs.json._

final case class MyCC(s: String, i: Int)

object MyCC {
  implicit lazy val MyCCFormat: OFormat[MyCC] = Json.format[MyCC]
}

trait DoSomething {

  def run(): Unit

  def commonRun(s: String): Unit = {
    println(s"Exec Common code: ${s}")
    println(s"Json: ${Json.toJson(MyCC("tata", 42))}")

    run()
  }
}

// Run: $ sbt "runMain fr.altior.labo.Common"
object Common {
  def main(args: Array[String]): Unit = {
    println(s"Json: ${Json.toJson(MyCC("toto", 10))}")
    println("End of Common Main !")
  }
}

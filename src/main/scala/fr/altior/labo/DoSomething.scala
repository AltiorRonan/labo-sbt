package fr.altior.labo

trait DoSomething {

  def run(): Unit

  def commonRun(s: String): Unit = {
    println(s"Common: ${s}")
    run()
  }
}

object Common {
  def main(args: Array[String]): Unit = {
    println("Common !")
  }
}

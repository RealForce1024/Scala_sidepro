package ch05

/**
  * Created by fqc on 2016/7/13.
  */
class MainConstructor(name: String, age: Int) {
  println("just constructed another obj")

  def description = "name: " + name + " age: " + age

}

object MainConstructor{
  def main(args: Array[String]) {
    val constructor = new MainConstructor("kobe",39)
    println(constructor.description)
  }
}
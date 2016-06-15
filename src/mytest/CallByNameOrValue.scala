/**
  * Created by fqc on 5/27/16.
  */

object CallByNameOrValue {
  def main(args: Array[String]) {
    println(addByName(2, 3 + 3))
    println(addByValue(2, 3 + 3))
  }

  def addByName(a: Int, b: => Int) = {
    println("a:" + a + "b:" + b);
    a + b
  }

  def addByValue(a: Int, b: Int) = {
    println("a:" + a + "-- " + "b:" + b);
    a + b
  }
}

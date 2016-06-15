import scala.collection.mutable.ArrayBuffer

/**
  * Created by fqc on 5/10/16.
  */
object HelloScala {

  def m1(x: Int, y: Int) = x + y

  def main(args: Array[String]) {
//    println(m1(1, 3))
    val ab = ArrayBuffer[Int]()
    ab += 1
    val ab2 = ArrayBuffer[Int]()
    val abb = ab ++= ab2
    println(abb)

    val tup = (2,3)
    ab += (2,3)
//    ab += tup

    println(ab)



  }

  val arr = Array(1, 2, 3, 4, 5)
  val fun1 = (x: Int) => x * 10
  val fun2 = (x: Int, y: Int) => x * 10
  arr.map(fun1)
  println(arr.map(x => x * 10))
//  for (elem <- arr) {
//    print(elem.toList.mkString("-"))
//  }

//  def m2(name: String): Unit = {
//    println(name)
//  }

  def m2(name: String) {
    println(name)
  }

  def m11(x: Int, y: Int) = x * y
  //def m11(x,y) = x * y

  def m22 = 1+2
  m22
  arr.toBuffer















}

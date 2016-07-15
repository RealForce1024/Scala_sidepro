package ch06

import scala.io.Source

/**
  * Created by fqc on 2016/7/15.
  */

class Test {

  //new的时候主构造器里的内容都会被执行一遍，即使lazy?
  val name = "aaa"
  println(name)

  def sayHi(): Unit ={ //朱构造器加载类的内容但不会调用，除非自己调用。这就要注意看构造器中是否有主动调用的方法了，可能隔得很远
    println("sayHi")
  }

  try {
    lazy val lines = Source.fromFile("c:/a.txt").mkString
    println(lines)
  } catch {
    case e: Exception => e.printStackTrace()
  } finally {
    println("finally")
  }

  sayHi()//这就要注意看构造器中是否有主动调用的方法了，可能隔得很远,想象下spark的上千行代码..最后来个start方法...
}

object Test {

  def apply() {
    println("apply invoked")
  }

  def apply(name:String,id:Int): Test= {//注意重载与返回值无关，至于方法列表相关[方法名与参数列表]
     new Test
  }

  def apply(name:String): Unit = {
    println(name)
  }



  def main(args: Array[String]) {
    /*val person: Person = new Person
    //    person.country 由于是private属性，所以获取不到
    println(person.getCountry)*/

    val m = new Test() //打印finally 初始化时就读取文件了，那么改如何使用时才初始化呢

    val apply = Test()
    val apply2 = Test("apply(\"name\")")

    val arr = Array(1,2,3,4) //ctrl+shift+i 查看源代码定义
    /*def apply(x: Int, xs: Int*): Array[Int] = {
      val array = new Array[Int](xs.length + 1)
      array(0) = x
      var i = 1
      for (x <- xs.iterator) { array(i) = x; i += 1 }
      array
    }*/
    println(arr.mkString)
  }
}

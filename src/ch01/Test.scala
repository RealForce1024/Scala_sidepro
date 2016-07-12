package ch01

import scala.BigInt._
import scala.collection.immutable.Range.Inclusive
import scala.util.Random

/**
  * Created by fqc on 2016/7/12.
  */
object Test {

  def main(args: Array[String]) {
    //----声明变量和值--------------------------------------------
    val str = "hello scala"
    val s: String = "hello test" //ctrl + alt +v 快速声明变量:类型
    val i: Int = 3

    //----多变量赋值--------------------------------------------
    val x, y = 3 //x=3,y=3
    val a, b = (3, 4) //a=(3,4),b=(3,4)

    //---字符数组转换---------------------------------------------
    val word = "hello world"
    println(word.toCharArray.toString)
    println(word)

    //string to arr
    val arr = word.toArray
    println(arr)

    //array to string

    println(arr.mkString)

    //---数组------------------------------------------------------
    val ints: Array[Int] = Array(1, 2, 3)

    //----隐式转换 类型增强------------------------------------------------------
    println(1.to(10))
    println(1 to 10)

    println("hello".intersect("world")) //lo 将第一个集合的每个元素与后面集合比较

    //----类型转换都是方法，而非强制转换------------------------------------------
    //println("100.0".toInt) //Exception in thread "main" java.lang.NumberFormatException: For input string: "100.0"
    println("100.0".toFloat) //100.0 要按照字符串中数值的类型来相应转换，否则异常
    println("99.99".toFloat) //99.99
    println(99.99.toInt) //99

    //操作符除普通方式使用外，还可以作为方法调用
    val xy = 1 + 2
    val xz = 1.+(2)
    val mod = 5/3
    val modd = 5%3
    //val moddd = 5./%3 编译不通过，必须使用BigInt类型的才有 /% 该方法
    val z: BigInt= 5
    val moddd =z/%3
    println(mod,modd,moddd)
    println("商:",moddd._1)
    println("余数:",moddd._2)

    println(probablePrime(100, new Random()))//new Class()
    println(probablePrime(100, Random)) //Object 半生对象

    "ehlo".apply(1)
     val inclusives: Array[Inclusive] = Array(1 to 10,1 to 20)
     println(inclusives.length) //2
     println(inclusives(1)) //Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)

    for (elem <- inclusives) {
      println(elem)  //Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
      //Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
    }

  }

}

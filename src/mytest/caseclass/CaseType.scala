package mytest.caseclass

import scala.util.Random

/**
  * Created by fqc on 2016/7/15.
  * 模式匹配字符串类型
  */
object CaseType extends App {

  private val arr = Array(1, "spark2.0", CaseType, 2.0)
  private val elem = arr(Random.nextInt(arr.length))
  println(elem)

  elem match {
    case x: Int => println(s"Int $x")
    case y: String => println(s"Double $y")
    //    case z: _ => println("不认识的类型")
    //    case _ => println("不认识的类型") //注意在测试的时候就是该顺序 ，而最匹配项是Double也会被执行
    //说明了只要找到匹配项即返回，剩余的不会再执行。所以把最模糊的放到最后
    /*2.0
    不认识的类型
  */
    case a: Double => println(s"Double: ", a)
    case _ => println("不认识的类型")
  }


}

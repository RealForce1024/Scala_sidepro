package mytest.caseclass

import scala.util.Random

/**
  * Created by fqc on 2016/7/15.
  * 模式匹配字符串类型
  */
object CaseType extends App {

  //val x = 3
  //val v = if(x >= 5) 1 else if(x < 2) 2.0 else "hello"   如果需求根据判断返回类型 代码比较麻烦且不够清晰，不如模式匹配的方式

  private val arr = Array(1, "spark2.0", CaseType, 2.0)
  private val elem = arr(Random.nextInt(arr.length))
  println(elem)

  //elem.match + tab键组合 一气呵成模板..
  elem.match
  elem match {
    case x: Int => println(s"Int $x")
    case y: String => println(s"Double $y")
    //    case z: _ => println("不认识的类型")
    //    case _ => println("不认识的类型") //注意在测试的时候就是该顺序 ，而最匹配项是Double也会被执行
    //说明了只要找到匹配项即返回，剩余的不会再执行。所以把最模糊的放到最后
    /*2.0
    不认识的类型
  */
    case a: Double => println(s"Double: " + a)
    case _ => throw new Exception("not match exception")
    //    case _ => println("不认识的类型")
  }


}

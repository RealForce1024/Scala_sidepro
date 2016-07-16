package mytest.casematch

/**
  * 偏函数
  */
object PartialFuncDemo {

  def func1: PartialFunction[String, Int] = {
    case "one" => {
      println("one case")
      1
    }
    case "two" => 2
    case _ => -1
  }

  //判断输入参数得到相应结果
  def func2(num: String): Int = num match {
    case "one" => 1
    case "two" => 2
    case _ => -1
  }

  def func3(name: String): String = name match {
    case "kobe" => "科比"
    case "james" => "詹姆斯"
    case "jordan" => "乔丹"
    case _ => "不认识的"
  }

  def main(args: Array[String]) {
    println(func1("two"))
    println(func2("one"))
    println(func3("kobe"))
  }
}

package mytest.casematch

/**
  * option对象
  */
object OptionDemo {
  def main(args: Array[String]) {
    val map = Map("a" -> 1, "b" -> 2)

    val v = map.get("b") match {
      case Some(i) => i //匹配则封装到Some对象当中
      case None => 0 //否则None对象
    }
    println(v)
    //更好的方式
    val v1 = map.getOrElse("c", 0)
    println(v1)
  }
}

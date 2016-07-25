package mytest

/**
  * Created by fqc on 2016/7/24.
  */

class Bar(foo: String)

object Bar {
  def apply(foo: String) = new Bar(foo) //等价于工厂
}


object ObjectTest {

  def main(args: Array[String]) {
    val nowVal: Long = Timer.currentCount()
    println(nowVal)


    val bar1 = Bar("zhangsan")
    println(bar1)
    val boo2 = Bar("lisi")
    println(boo2)

    val bar = Bar //伴生对象 apply()默认
    println(bar)
    val boo = Bar
    println(boo)
  }

}
object Timer {
  var count = 0

  def currentCount(): Long = {//定义返回值类型为Long了..而+=是有副作用的
    count += 1 //有副作用的 返回值为unit，因此需要需要加一行单独返回
    count
  }
}
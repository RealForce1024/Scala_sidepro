package ch05

/**
  * Created by fqc on 2016/7/13.
  * 简单类和无参方法
  */
class MySimpleCounter {
  //0 ctrl+alt+v
  private var value: Int = 0

  //*对象私有*的字段 不会生成getter&&setter
  private[this] var value2: Int = 1

  def increment(): Int = {
    value += 1
    value // 注释了会异常的
  }

  def m_increment(): Unit = {
    value += 1
  }

  def m_increment2() {
    value += 1
  }

  /*def currentValue() = {
    val value1: Int = value
    value1
  }*/
  def currentValue = value

  //使用方法获取属性值，而非直接使用
  def currentVal() = value

}

object MySimpleCounter {
  def main(args: Array[String]) {
    val counter: MySimpleCounter = new MySimpleCounter
    //    counter.increment()
    counter.m_increment() //若无参时，改值()
    println(counter.currentValue) //若无参时，获取值省略()
    println(counter.increment())
    println(counter.currentVal)

  }
}

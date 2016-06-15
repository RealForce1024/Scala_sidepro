/**
  * Created by fqc on 2016/6/15.
  */
class Counter {
  private var value = 0 //字段必须初始化

  def increment() {
    value += 1
  }

  def current() = value
}

object Counter {
  def main(args: Array[String]) {
    val counter: Counter = new Counter //构造类的对象 省略了()
    for (i <- 1 to 10) {
      counter.increment() //改值 ()不省略
    }
    println(counter.current) //取值时 省略()
  }
}
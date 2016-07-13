package ch05

/**
  * Created by fqc on 2016/7/13.
  * getter and setter 的使用
  */
class CannotYoungAgain {
  private var privateAge: Int = 20

  def age = privateAge //getter

  /*def age_(newVale: Int): Unit = { //普通方法，不是getter块的写法
    //setter
    if (newVale > privateAge) privateAge = newVale //岁数只能正在增长
  }*/

  def age_= (newValue: Int){ //getter age_=注意这是一体 参数(newValue: Int)
    if (newValue > privateAge) privateAge = newValue //岁数只能正在增长
  }
}

object CannotYoungAgain {
  def main(args: Array[String]) {
    var again: CannotYoungAgain = new CannotYoungAgain
    println(again.age)
    again.age = 40
    again.age = 10
    println(again.age)
  }
}

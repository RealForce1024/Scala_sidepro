package ch06

/**
  * Created by fqc on 2016/7/13.
  */
class People(val name: String, var age: Int, gender: String, var address: String = "bj") {
  //gender 相当于private[this]当前类中使用  address 默认初始值
  println("just constructed another obj")

  def description = "name: " + name + " age: " + age

}

object People {
  def main(args: Array[String]) {
    val constructor = new People("kobe", 39, "female", "sc")
    println(constructor.description)
    constructor.address = "sc"
    //假设val address 则不能够生成的对象设置了 val不能修改，但是在构造器中还是可以覆盖默认值
    println(constructor.age)
    println(constructor.name)
    println(constructor.address)
    // 访问不到以下内容
    //    println(constructor.gender)

  }
}
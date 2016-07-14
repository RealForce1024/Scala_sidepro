package ch06

/**
  * Created by fqc on 2016/7/13.
  * 辅助构造器
  */
class AuxiziliaryConstructor {
  private var name: String = ""
  private var age: Int = 0

  def this(age: Int) {
    this()
    this.age = age
  }

  def this(name:String,age:Int){
    this(age)
    this.name = name
  }

}

object Constructor{
  def main(args: Array[String]) {
    val constructor = new AuxiziliaryConstructor()
    val constructor1 = new AuxiziliaryConstructor(10)
    val constructor2 = new AuxiziliaryConstructor("kobe",39)

  }
}
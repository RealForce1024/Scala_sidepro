package ch06

/**
  * Created by fqc on 2016/7/13.
  * 辅助构造器
  */
class AuxiziliaryConstructor(val id: Int) {

  var name: String = _

  /* def this(age: Int) {
     this(id)//一定要先调用主构造器
     this.age = age
   }*/

  def this(id: Int = 0, name: String) {
    this(id)
    this.name = name
  }
}

object Constructor {
  def main(args: Array[String]) {
    val constructor = new AuxiziliaryConstructor(1)
    val constructor1 = new AuxiziliaryConstructor(10)
    val constructor2 = new AuxiziliaryConstructor(name = "kobe")

  }
}
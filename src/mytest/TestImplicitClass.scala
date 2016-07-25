package mytest.implicitclass

/**
  * Created by fqc on 2016/7/25.
  */
class TestImplicitClass {

}
//1. 原始类
class OriginClass {
  def say(mes: String): Unit = {
    println(mes)
  }
}
package mytest.context.implicitclass {

  object Context {
    //2.定义隐式转换类 参数为原始类
    implicit class enhancedClass(obj: OriginClass) {
      def enhancedFun1(): Unit = {
        println("fun1 working...........")
      }

      def enhancedFun2(): Unit = {
        println("fun2 working...........")
      }
    }

  }

}

object Driver extends App {
  //导入隐式转换类
  import mytest.context.implicitclass.Context._
  val origin: OriginClass = new OriginClass
  origin.say("hello")
  origin.enhancedFun1()
  origin.enhancedFun2
}
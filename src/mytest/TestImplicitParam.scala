package mytest

/**
  * Created by fqc on 2016/7/25.
  */
class TestImplicitParam {

}


package test_implicit {
  object Person {
    //2.使用隐式参数
    def sayHello(name: String)(implicit message: String): Unit = {
      println(s"hi,$name>> $message")
    }
  }
  object Context {
    //1.定义隐式参数
    implicit val param: String = "this is implcit test"
  }

}

object Driver extends App {
  //3. 导入隐式参数
  import test_implicit.Context._
  val person = test_implicit.Person
  person.sayHello("kobe")
}
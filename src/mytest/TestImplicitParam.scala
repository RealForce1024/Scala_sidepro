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

object DriverMain extends App {
  //3. 导入隐式参数
  import test_implicit.Context._
  val person = test_implicit.Person
  person.sayHello("kobe")

  def sayHello2(implicit mes:String){
    println(mes)
  }
  sayHello2 //这里证明了隐式参数按照类型来发生隐式转换的。
  sayHello2("jordan")//如果有自己的值会覆盖隐式参数的值
}

/*
* 隐式参数 匿名函数作为隐式参数转换
* */

//scala> def min(x:T,y:T)={if(x<y) x else y}
//<console>:7: error: not found: type T
//def min(x:T,y:T)={if(x<y) x else y}
//^
//<console>:7: error: not found: type T
//def min(x:T,y:T)={if(x<y) x else y}
//^
//
//scala> def min[T](x:T,y:T)={if(x<y) x else y}
//<console>:7: error: value < is not a member of type parameter T
//def min[T](x:T,y:T)={if(x<y) x else y}
//^
//
//
//scala> def min[T](x:T,y:T)(implicit s:T => Ordered[T])={if(x<y) x else y}
//min: [T](x: T, y: T)(implicit s: T => Ordered[T])T
//
//scala> min(1,2)
//res0: Int = 1
//
//scala> min("c","d")
//res1: String = c
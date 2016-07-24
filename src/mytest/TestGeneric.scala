package mytest

import scala.runtime.RichInt

/**
  * Created by fqc on 2016/7/24.
  */
class TestGeneric {

}

class Generic[T](val name: T) {
  def test[G](obj: G) = {
    println(obj)
  }
}

//泛型的上届 为了约束传入的泛型的值必须是给定的上届类型的子类
class Pair[T <: Comparable[T]](x: T, y: T) {
  //  def bigger(x: T, y: T) = {
  def bigger = {
    if (x.compareTo(y) > 0) x else y
  }
}


object Generic {
  def main(args: Array[String]) {
    //类的参数泛型与方法参数泛型
    val generic = new Generic[String]("kobe")
    val generic2 = new Generic(101)
    println(generic)
    println(generic2)

    generic.test[String](generic.name)
    generic.test(generic.name)
    generic.test(100)


    //    val pair: Pair[RichInt] = new Pair[RichInt](1,2)
    //    val pair = new Pair(1,2)//fail
    val pair = new Pair("a","b")
    val result = pair.bigger
    println("result = " + result)


  }
}
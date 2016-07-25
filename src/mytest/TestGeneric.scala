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

//泛型的视图界定使用符号 <% 也是用来约束泛型上届，但是里面有个隐式转换在起作用。只要T能够被隐式转换成Comparable[T]的子类即可。
class PairSmaller[T <% Comparable[T]](x: T, y: T) {
  //  def bigger(x: T, y: T) = {
  def smaller = {
    if (x.compareTo(y) < 0) x else y
  }
}

class Person(val name:String)
class Student(name:String) extends Person(name)
class Pair2[T](p1:T,p2:T){
  def replacePerson[R >: T](anotherPerson:R): Unit ={
    new Pair2(anotherPerson,p2)
  }
}


object Generic {
  def main(args: Array[String]) {

    println("======类的参数泛型与方法参数泛型==================================================")
    //类的参数泛型与方法参数泛型
    val generic = new Generic[String]("kobe")
    val generic2 = new Generic(101)
    println(generic)
    println(generic2)

    generic.test[String](generic.name)
    generic.test(generic.name)
    generic.test(100)

    println("======以下是泛型上届测试结果=====================================================")
    //    val pair: Pair[RichInt] = new Pair[RichInt](1,2)
    //    val pair = new Pair(1,2)//fail 编译不通过 因为1 2 Int类型并未实现Comparable接口
    val pair = new Pair("a", "b")
    println("pair.bigger = " + pair.bigger)

    println("======以下是泛型视图界定测试结果==================================================")
    val pair2 = new PairSmaller(1,2)//因为视图界定 只要传入能够实现Comparable接口的参数值即可，包括隐式转换。而Int有对应的隐式转换RichInt
    println("pair2.smaller = " + pair2.smaller)

    println("======以下是下届界定测试结果==================================================")
    val person = new Person("parent")
    val s1 = new Student("s1")
    val s2 = new Student("s2")
    val pair3 = new Pair2(s1,s2)
    //下届限定传入泛型的超类（包含下届自身级别泛型）
    pair3.replacePerson(s1)
    pair3.replacePerson(person)
  }
}
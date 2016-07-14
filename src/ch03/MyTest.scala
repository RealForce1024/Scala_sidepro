package ch03

/**
  * Created by fqc on 2016/7/12.
  */
object MyTest {

  def main(args: Array[String]) {

    //--------------------------
    def fac(n: Int): Int = {
      if (n <= 0) 1
      else {
        println(n + "*(" + n + ")" + "-1")
        n * fac(n - 1)
      }
    }
    println(fac(5))
    /*5*(5)-1
    4*(4)-1
    3*(3)-1
    2*(2)-1
    1*(1)-1
    120*/
  }

  def sum(args: Int*): Int = {
    var sum = 0
    for (elem <- args) {
      sum += elem
    }
    sum
  }

  println(sum(1, 2, 3, 4))
  println(sum(1 to 4: _*))

  //sum((1 to 4).toArray) 类型要对应

  //函数字面量（值函数）

  //  val increase = (x: Int) = x + 1
  val increase = (x: Int) => x + 1

  println(increase(101))

  /* scala> (x:Int) => x+1
   res0: Int => Int = <function1>

   scala> (x:Int) => x.toString
   res1: Int => String = <function1>

   scala> (x:Int) => x.toDouble
   res2: Int => Double = <function1>
   */


  def anotherIncrease(x: Int) = {
    x + 1 //102
    //x //101
  }


  println(anotherIncrease(101))

  //函数字面量直接当做变量传入函数
  val arr = Array(1, 2, 3).map(increase(_)).mkString(",") //map(increase)也可以的
  println(arr)
  //2,3,4

  //匿名函数 (将值函数直接传入函数)
  val arrs = Array(5, 6, 7).map((x: Int) => x + 1).mkString("-")
  println(arrs)
  //6-7-8

  //根据类型推导，也可以省略类型定义
  val array = Array(9, 10, 11).map(x => x + 1).mkString("+")
  //如果参数只在右边一行语句出现一次的话为 _ 的形式，则可以进一步简化
  //  val array = Array(9,10,11).map(_+1).mkString("+")
  println(array)

  //另外写法 还有 1省略 . 以空格替换的形式   2使用{}代替()的形式 不建议使用

  //val fun1 = 1 + _ //不合法，无法进行类型推断
  // val fun1:Double=>Double = 1+_ //将输入输出都指定下
  //进一步简化返回值进行类型推断，指明输入类型
  val fun1 = 1 + (_: Double)
  println(fun1(99))

  //100.0

  //函数参数（高阶函数）
  def convertInt2String(f: Int => String) = f(4)

  //  println((x: Int) => x + " s")
  println(convertInt2String((x: Int) => x + " s"))

  //4 s

  //高阶函数 输入普通的类型，返回值是函数。 高阶函数可以产生新的函数
  def multiply(x: Double) = (y: Double) => x * y

  val fun_multiply = multiply(10)
  println(fun_multiply(5))


  val fun_a = (x: Int) => x + 1

  println(fun_a(4))
  //5

  val fun_b: Int => String = { x => x.toString }
  println(fun_b(4))
  //4
  val fun_c: Int => Int = { x => x + 1 }
  println(fun_c(4))
  //5

  /*
    scala> val fun1: Int=>String = {x=>x.toString}      //val fun1 定义函数变量  Int=>String 输入输出类型定义   {...方法体}
    fun1: Int => String = <function1>
  */

  /* spark 中的神器函数  大致按照以上理解即可
  val startService: Int => (ActorSystem, Int) = { actualPort =>
    doCreateActorSystem(name, host, actualPort, conf, securityManager)
  }
  Utils.startServiceOnPort(port, startService, conf, name)

  val func: Int => String = { x => x.toString }

  val func1 = (x: Int) => x.toString

*/

  //注意区分  val fun1: Int=>String = {x=>x.toString} // Int=>String 输入输出类型定义 =后面方法体
  val fun2 = (x: Int) => x + 1 //input => output 右边是方法体同时也类型推断了类型

  val arr2 = Array(1, 2, 3)
  arr2.filter(_ % 2 == 0) // arr2.filter((x:Int)=>x%2==0) //==>返回boolean值的表达式


  // _ 并不是任意地方都可以使用的.比如在sortBy中
  arr2.sorted
  //arr2.sortBy(_)
  /*Error:(145, 15) missing parameter type for expanded function ((x$4) => arr2.sortBy(x$4))
  arr2.sortBy(_)
              ^
  ^*/

  arr2.sortBy(x => x)

  // sortWith
  arr2.sortWith(_>_)
  arr2.sortWith(_<_)
  arr2.sortWith((x,y)=>x>y)
  arr2.sortWith((x,y)=>x<y)


}

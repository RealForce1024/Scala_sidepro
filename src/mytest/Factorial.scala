/**
  * Created by fqc on 5/27/16.
  */
object Factorial {
  def main(args: Array[String]) {
    for(i<-1 to 10) {
      println(i+"的阶乘:" + factorial(i))
    }
  }

  def factorial(n: Int): Int = { //需要指明返回值类型
    if (n <= 1)
      1
    else
      n * factorial(n - 1)

  }

}

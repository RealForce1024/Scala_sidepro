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

  def sum(args:Int*):Int = {
    var sum = 0
    for (elem <- args) {
      sum += elem
    }
    sum
  }
  println(sum(1,2,3,4))
  println(sum(1 to 4: _*)) //sum((1 to 4).toArray) 类型要对应
}

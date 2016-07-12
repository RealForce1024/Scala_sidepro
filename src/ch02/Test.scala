package ch02

/**
  * Created by fqc on 2016/7/12.
  */
object Test {

  var y = 0

  def main(args: Array[String]) {

    //if语句 if后() 不能省略
    val a = 101;
    //scala中的语句结构都是有值的
    val result = if (a > 100) 1 else -1;
    println(result)

    if (a > 100) y = 1 else y = -1 //y = (-1)
    println(y)


    //--------for------------------------------------
    val arr = 1 to 10 toArray

    for (elem <- arr) {
      println(elem)
    }

    //--------------------------------------------
    arr.map(x => println(x))
    arr.map(println(_))


    //   ((10*1)*2)*3....10
    var x = 10
    for (i <- 1 to 10) {
      x *= i
      println(x * i + "=" + x + "*" + i)
    }

    for (x <- 1 to 3; y <- 1 to 3) {
      print(x * 10 + y + " ") //11 12 13 21 22 23 31 32 33
    }
    println()
    for (x <- 1 to 3; y <- 1 to 3 if y != x) {
      print(x * 10 + y + " ") // 12 13 21 23 31 32
    }
    println()
    for (elem <- 1 to 3) yield print(elem + " " )
  }

}

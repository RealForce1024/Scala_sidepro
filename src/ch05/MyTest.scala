package ch05

/**
  * Created by fqc on 2016/7/14.
  */
class MyTest {



}
object MyTest{


  def main(args: Array[String]) {

    val tuple = (1,"spark",2.0)
    println(tuple._1)
    println(tuple._2)
    println(tuple._3)



    val t_tuple,(x,y,z) = (1,"spark",2.0)


    println(t_tuple)
    println(x)
    println(y)//spark
    println(z)
  }
}

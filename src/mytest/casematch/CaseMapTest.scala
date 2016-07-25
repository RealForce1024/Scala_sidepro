package mytest.casematch

/**
  * Created by fqc on 2016/7/24.
  */
class CaseMapTest {


}

object CaseMapTest extends App {
  val extensions = Map("steve" -> 100, "bob" -> 101, "joe" -> 201)
  //取出座机号小于200的
  val result: Map[String, Int] = extensions.filter(_._2 < 200)
  println("result = " + result)

  /*extensions.filter((m: String, n: Int) => {
    n < 200
    (m, n)
  }*/

  val result2 =extensions.filter((phone: (String, Int)) => {
    //函数应用于每个map元素
    phone._2 < 200
  }
  )
  println("result2 = " + result2)


  /*extensions match {
    case (x,y) => y<200
  }*/

  val result3 = extensions.filter(
    {
      case (name,code) => code<200
    }
  )

  println("result3 = " + result3)

}
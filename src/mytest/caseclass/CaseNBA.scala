package mytest.caseclass

import scala.util.Random

/**
  * Created by fqc on 2016/7/15.
  */
object CaseNBA extends App {

  val arr = Array("kobe", "james", "jordan", "rose")
  private val index: Int = Random.nextInt(arr.length)

  index match {
    case 0 => println("kobe")
    case 1 => println("james")
    case 2 => println("jordan")
    case _ => println("not find") //注意如果使用index的话，需要做处理 _ 处理
  }

  private val name: String = arr(Random.nextInt(arr.length))
  name match {
    case "kobe" => println("kobe")
    case "jordan" => println("jordan")
    case "james" => println("james")
    case _ => println("not find")
  }

}

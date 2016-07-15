package mytest.caseclass

import scala.util.Random

/**
  * Created by fqc on 2016/7/15.
  * 模式匹配字符串的值
  */
object CaseString extends App {

  /////////////////////////////////////////////////////////////////////////////////////////////
  private val arr: Array[String] = Array("kobe", "jordan", "james", "rose")

  private val player: String = arr(Random.nextInt(arr.length))
  println(player)
  player match {
    case "kobe" => println("科比")
    case "james" => println("詹姆斯")
    case "jordan" => println("乔丹")
    case _ => println("公牛那个最年轻的状元？")
  }
  ////该段完全顶上段啊.... 只是为了演示是为了匹配字符串的值！
  player match {
    case x:String => println(x)
  }
/////////////////////////////////////////////////////////////////////////////////////////////

  val arr2 = Array("YoshizawaAkiho", "YuiHatano", "AoiSola")
  val name = arr2(Random.nextInt(arr2.length))
  println(name)
  name match {
    case "YoshizawaAkiho" => println("吉泽老师...")
    case "YuiHatano" => println("波多老师...")
    case _ => println("真不知道你们在说什么...")
  }

}

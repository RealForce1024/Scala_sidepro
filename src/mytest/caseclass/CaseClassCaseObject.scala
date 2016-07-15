package mytest.caseclass

import scala.util.Random

/**
  * Created by fqc on 2016/7/16.
  */
object CaseClassCaseObject {

  case class SubmitTask(id:Int,name:String)
  case class HeartBeat(time:Long) //样例类
  case object CheckTimeOutTask //样例对象

  def main(args: Array[String]) {
    val arr = Array(SubmitTask(1,"mr"),HeartBeat(10000),CheckTimeOutTask)
    val elem = arr(Random.nextInt(arr.length))
    elem match { //样例类 封装了数据,类型
      case SubmitTask(id,name) => println(s"id: $id name: $name")
      case HeartBeat(time) => println(s"time:$time")
      case CheckTimeOutTask => println("check timeout")
    }
  }
}

package actor

import scala.actors.Actor

/**
  * Created by fqc on 2016/7/16.
  */
class SyncMsgAndAyscMsg extends Actor {
  override def act(): Unit = {

    loop(
      react {
        case "start" => println("start")
        case SyncMsg(id, msg) => {
          println(s"id:$id , SyncMsg:$msg")
          sender ! ReplyMsg(1,"finished")
        }
      }
    )
  }
}


case class SyncMsg(id: Int, msg: String) //封装数据

case class AyncMsg(id: Int, msg: String)

case class ReplyMsg(id: Int, msg: String)

object AppRun extends App {

  val msg: SyncMsgAndAyscMsg = new SyncMsgAndAyscMsg
  msg.start() //start
  val replyMsg= msg !? SyncMsg(10000, "syncMsg")
  println(replyMsg)

  val retMsg = msg !! SyncMsg(11111,"syncMsg")
  println(retMsg)
  println(retMsg.isSet)
  val c = retMsg.apply
  println(retMsg.isSet)
  println(c)
}
package actor

/**
  * Created by fqc on 2016/7/27.
  */
object ActorTest {

}

import scala.actors.Actor._ //注意这两个包
import scala.actors.Actor

object MyActor extends Actor {
  override def act() = {
    while(true) {
      receive {
        case msg : Double => println("myactor double, msg = " + msg)
        case _ => println("myactorunknow")
      }
    }
  }
}

object ActorMessages {

  val messageActor = actor {//匿名actor
    while(true){
      //消息传递，如果'actor邮箱'有消息，则立即执行，否则进入阻塞
      receive {
        //isDefinedAtapply
        //首先通过isDefinedAt判断receive是否定义了对应类型消息的处理方法，没有则忽略；存在的话则调用apply，通过偏函数对具体类型的消息进行处理
        case msg : String => {
          println("string, msg = "+ msg)
        }
        case msg : Double => println("double, msg = " + msg)
        case _=> println("unknow")
      }
    }
  }

  def main(args:Array[String]): Unit = {

    //向actor的邮箱中发送消息
    messageActor ! "hadoop" //匿名actor可以直接发送消息
    messageActor! Math.PI
    messageActor! 0

    MyActor.start()//需要启动才能发送消息
    MyActor !"spark"
  }
}
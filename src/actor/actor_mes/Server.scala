package actor.actor_mes

import scala.actors.Actor

/**
  * Created by fqc on 2016/7/27.
  */
object Server extends Actor {
  override def act(): Unit = {
    loop(react {
      case Person(name, age) => {
        println(s"name = $name ,age = $age")
        sender ! "copy that" //通过发送者的句柄发送消息
      }
      case _ => println("unknow") //需要有一个默认的接收，否则actor邮箱中会出现堆满消息的可能，得不到处理
    })
  }
}

package actor

import scala.actors.Actor

/**
  * Created by fqc on 2016/7/16.
  */
object T1 extends Actor {
  //Actor 是trait 其内是实现的act方法 当做实现的接口 ctrl+i 覆盖其方法
  override def act(): Unit = {
    for (elem <- 1 to 20) {
      println(s"线程actor-1: $elem")
      Thread.sleep(1000)
    }
  }
}

object T2 extends Actor{
  override def act(): Unit = {
    for (elem <- 1 to 20) {
      println(s"线程actor-2： $elem")
      Thread.sleep(1000)
    }
  }
}

object App{
  def main(args: Array[String]) {
    T1.start()
    T2.start()
    println("main")
  }
}
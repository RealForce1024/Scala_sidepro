package actor

import scala.actors.Actor

/**
  * Created by fqc on 2016/7/16.
  * 实现的功能与ThreadPrint一样，且极度相似。
  * Actor是Scala中的线程实现
  */
object T1 extends Actor {
  //Actor 是trait 其内是实现的act方法 当做实现的接口 ctrl+i 覆盖其方法
  override def act(): Unit = {
    for (elem <- 1 to 20) {
      println(s"actor-1: $elem")
      Thread.sleep(1000)
    }
  }
}

object T2 extends Actor{
  override def act(): Unit = {
    for (elem <- 1 to 20) {
      println(s"actor-2： $elem")
      Thread.sleep(1000)
    }
  }
}

object AppStart{
  def main(args: Array[String]) {
    T1.start()
    T2.start()
    println("main")
  }
}
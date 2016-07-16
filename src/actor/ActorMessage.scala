package actor

import scala.actors.Actor

/**
  * Created by fqc on 2016/7/16.
  */
class ActorMessage {



}

class ActorTask1 extends Actor{
  override def act(): Unit = {
    while (true) {
      receive{//偏函数PartialFunction
        //message match {
          case "start" => println("start")
        //}
      }

    }
  }
}

class ActorTask2{

}

object  App{
  def main(args: Array[String]) {
    val t1: ActorTask1 = new ActorTask1
    t1.start()
    t1 ! "start"
  }
}
package actor

import scala.actors.Actor

/**
  * Created by fqc on 2016/7/16.
  */
class ActorMessage {


}

class ActorTask1 extends Actor {
  override def act(): Unit = {
    while (true) {
      receive {
        //偏函数PartialFunction
        case "start" => {
          println("starting.......")
          println("当前线程:" + Thread.currentThread().getName)
          //          Thread.sleep(1000)
          println("started")
        }

        case "stop" => {
          println("stopping......")
          println("当前线程:" + Thread.currentThread().getName)
          //Thread.sleep(1000)
          println("stopped")
        }
      }

    }
  }
}

class ActorTask2 extends Actor {
  override def act(): Unit = {
    loop(
      react {
        case "start" => {
          println("starting.......")
          println("当前线程:" + Thread.currentThread().getName)
          //          Thread.sleep(1000)
          println("started")
        }
        case "stop" => {
          println("stopping......")
          println("当前线程:" + Thread.currentThread().getName)
          //Thread.sleep(1000)
          println("stopped")
        }
        case "exit" => {
          exit()
        }
      }
    )
  }
}

object App {
  def main(args: Array[String]) {
//    val t1: ActorTask1 = new ActorTask1
//    t1.start()
//    t1 ! "start"
//    t1 ! "stop"
//    println("发送消息完毕")
//
    val t2: ActorTask2 = new ActorTask2
    t2.start()
    //t2 ! "start" //! actor发送消息是异步的，但是接收到消息执行的过程是同步的
    t2 !? "start" //!? 所以想要start确认start执行完毕之后在发送消息 !?  该方法会阻塞

    t2 ! "stop"
    println("发送消息完毕")
    t2 ! "start" //可以不断发送消息
    t2 ! "exit"
  }
}
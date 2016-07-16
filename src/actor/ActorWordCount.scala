package actor

import actor.Client.{StopTask, SubmitTask}

import scala.actors.{Actor, Future}

/**
  * Created by fqc on 2016/7/16.
  */
class ActorWordCount extends Actor {


  override def act(): Unit = {

    loop {
      react {
        //不能加match
        case SubmitTask(file) => {
          println(file)
          sender ! "file01_received"
        }
        case StopTask => {
          exit()
        }
      }
    }
  }
}

object Client {

  case class SubmitTask(file: String)

  case object StopTask

  def main(args: Array[String]) {
    val counter: ActorWordCount = new ActorWordCount
    counter.start()
    val future: Future[Any] = counter !! SubmitTask("file01") //发送异步消息返回值是future
    println(future)
    Thread.sleep(1000) //该行代码至关重要,否则下面的语句直接串行执行不等待结果有无，这里的时间等待改如何处理呢
    //所以如果在计算时，循环的整体 需要考虑局部结果返回 future对象是否为空的处理。可能没计算完，future对象没有及时填充，计算结果就不准确了。
    println(future.isSet)
    println(future.isSet)
  }
}
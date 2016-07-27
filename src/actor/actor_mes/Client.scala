package actor.actor_mes

import scala.actors.Actor

/**
  * Created by fqc on 2016/7/27.
  */
case class Person(name: String, age: Int)

object Client  {



  def main(args: Array[String]) {

    Server.start()
    Server ! new Person("kobe", 39)
    val replyMsg: Any = Server !? new Person("james", 32)
    println(replyMsg)


  }

}


package mytest.casematch

/**
  * Created by fqc on 2016/7/23.
  */
class CaseUser {

}

object CaseUser extends App {

  case class User(firstName: String, lastName: String, score: Int)

  def advance(xs: List[User]) = xs match {
    case User(_, _, score1) :: User(_, _, score2) :: _ => score1 - score2
    case _ => 0
  }

  private val result: Int = advance(List(User("zhangsan","san",19),User("zhangsan","san",20)))
  println(result)

}
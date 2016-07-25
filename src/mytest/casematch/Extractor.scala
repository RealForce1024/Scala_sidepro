package mytest.casematch

/**
  * Created by fqc on 2016/7/23.
  * 提取器
  */
class Extractor {


}

trait User {
  def name: String
}

class FreeUser(val name: String) extends User

class PremiumUser(val name: String) extends User

object FreeUser {
  def unapply(user: FreeUser): Option[String] = Some(user.name)
}

object PremiumUser {
  def unapply(user: PremiumUser): Option[String] = Some(user.name) //将name字段提取解构出来
}


object MyTest extends App {
  private val name: Option[String] = FreeUser.unapply(new FreeUser("Daniel"))
  println(name)
  println(name.getClass)

  val user = new PremiumUser("kobe")
  user match {
    case FreeUser(name) => println("hello freeuser" + Some(name) + name)
    case PremiumUser(name) => println("hello PremiumUser" + Some(name) + name)
  }
  //hello PremiumUserSome(kobe)

  private val premiumUser: Option[String] = PremiumUser.unapply(new PremiumUser("kobe"))
  println(premiumUser)
  println(premiumUser.getOrElse(0))
}
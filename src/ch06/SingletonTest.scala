package ch06

/**
  * Created by fqc on 2016/7/15.
  * scala中的单例就是如此简单
  */
object SingletonTest {

  def main(args: Array[String]) {
    val st = SingletonTest
    println(st.hashCode())
    val st2 = SingletonTest
    println(st2.hashCode())
    println(st)
    println(st2)
  }

}

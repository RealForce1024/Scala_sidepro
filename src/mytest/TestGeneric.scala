package mytest

/**
  * Created by fqc on 2016/7/24.
  */
class TestGeneric {

}

class Generic[T](val name: T) {
  def test[G](obj: G) = {
    println(obj)
  }
}

object Generic{
  def main(args: Array[String]) {
    val generic = new Generic[String]("kobe")
    generic.test[String](generic.name)
    generic.test(generic.name)
    generic.test(100)
  }
}
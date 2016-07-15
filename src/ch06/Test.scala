package ch06

/**
  * Created by fqc on 2016/7/15.
  */
object Test {

  def main(args: Array[String]) {
    val person: Person = new Person
    //    person.country 由于是private属性，所以获取不到
    println(person.getCountry)
  }
}

package mytest

import java.lang.reflect.Array

/**
  * Created by fqc on 2016/6/15.
  */
class Animal {
  private var privateAge = 27

  //getter
  def age = privateAge

  //setter
  def age_=(age: Int) {
    if (age > privateAge) privateAge = age //不能变年轻
  }
}

object Animal {
 /* def main(args: Array[String]) {
    val animal: Animal = new Animal
    animal.age = 28
    animal.age = 26
    print(animal.age)
  }*/
}

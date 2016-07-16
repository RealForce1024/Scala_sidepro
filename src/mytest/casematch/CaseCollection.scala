package mytest.casematch

/**
  * Created by fqc on 2016/7/15.
  */
object CaseCollection extends App {

  val arr = Array(0, 1, 5, 7)
  arr match {
    case Array(1, x, y) => println(x + " " + y)
    case Array(1, 1, 5) => println("only 0")
    case Array(0, _*) => println("0 ...")
    case _ => println("something else")
  }


  ///////////////////////////////////////////////////////////////////////


  //预热
  /*scala> lst1 ++ lst2
  res9: List[Int] = List(1, 2, 3, 4)

  scala> lst1 ::: lst2 // == ++
  res10: List[Int] = List(1, 2, 3, 4)

  scala> lst1 :: lst2  //奇怪..
  res11: List[Any] = List(List(1, 2), 3, 4)
*/

  //  val lst = List(0, 3, 4, 5)
  //  val lst = List(3, 4, 5)
  //  val lst = List(3, 4)
  val lst = List(0)
  lst match {
    case 0 :: Nil => println("only 0")
    case x :: y :: Nil => println(s"x: $x y: $y")
    case 0 :: a => println(s"0 ... $a")
    case _ => println("something else")
  }

  ////////////////////////////////////////////////////////////////////////
  val tup = (6, 3, 5)
  tup match {
    case (1, x, y) => println(s"hello 123 $x , $y")
    case (_, z, 5) => println(z)
    case _ => println("else")
  }


  // list.head 第一个元素  其余的都是尾部
  //  val lst1 = 9 :: (5 :: (2 :: Nil))
  //  val lst2 = 9 :: 5 :: 2 :: List()
  //  println(lst2)
  /*在Scala中列表要么为空（Nil表示空列表）要么是一个head元素加上一个tail列表。
  9 :: List(5, 2)  :: 操作符是将给定的头和尾创建一个新的列表
  注意：:: 操作符是右结合的，如9 :: 5 :: 2 :: Nil相当于 9 :: (5 :: (2 :: Nil))*/


  val t, (a, b, c) = (1, 2, 3)
  println(t)
  println(a)
  println(b)
  println(c)

}

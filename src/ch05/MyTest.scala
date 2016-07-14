package ch05

import scala.collection.mutable


/**
  * Created by fqc on 2016/7/14.
  */
class MyTest {


}

object MyTest {


  def main(args: Array[String]) {

    //元组的普通取值方式
    val tuple = (1, "spark", 2.0)
    println(tuple._1)
    println(tuple._2)
    println(tuple._3)

    //元组的另类取值方式
    val t_tuple, (x, y, z) = (1, "spark", 2.0)


    println(t_tuple)
    println(x)
    println(y) //spark
    println(z)


    //array toMap
    val arr = Array(("a", 1), ("b", 2), ("c", 3))
    println(arr.mkString)
    println(arr.toMap)

    //zip 将相交数组合并为对偶元组集合
    val a = Array("a", "b", "c")
    val b = Array(1, 2, 3)
    val zip: Array[(String, Int)] = a.zip(b)
    println(zip.mkString)
    println(zip.toMap)



    // set   += 追加一个元素  ++=追加一个集合 +追加一个元素并生成新的集合，原先的内容不变  ++追加并生成新的集合
    val set1 = Set(1, 2, 3)
    val set2 = Set(7, 8, 9)
    val set3 = set1 ++ set2
    val set4 = set1 + 4
    println(set3)
    println(set4)

    // - --
    val set5 = set1 - 4
    println(set5)
    val set6 = Set(8, 9)
    val set7 = set2 -- set6 //很好很强大
    println(set7)
    println(set7.getClass)


    //map中添加元素
    import scala.collection.mutable.Map
    var map1 = Map(("a", 1), ("b", 2), ("c", 3))
    map1("d") = 4
    map1.put("f", 5)
    map1 +=(("x" -> 6), ("y", 7))
    println(map1)
    val arr_x = map1.toArray
    val arr_y = arr_x.sorted
    println(arr_x.mkString) //
    println(arr_y.mkString)
    //map1 = arr_y.toMap  //Error:(74, 18) type mismatch;
    /* found   : scala.collection.immutable.Map[String,Int]
     required: scala.collection.mutable.Map[String,Int]
     map1 = arr_y.toMap
     ^*/
    /*Map(b -> 2, d -> 4, y -> 7, a -> 1, x -> 6, c -> 3, f -> 5)
    (b,2)(d,4)(y,7)(a,1)(x,6)(c,3)(f,5)
    (a,1)(b,2)(c,3)(d,4)(f,5)(x,6)(y,7)
*/

    val map11 = arr_y.toMap
    println(map11) // Map(x -> 6, y -> 7, f -> 5, a -> 1, b -> 2, c -> 3, d -> 4)

    var map21 = mutable.HashMap(("b", 2), ("a", 1))
    map21 += (("f", 5))
    map21.put("c", 3)
    map21 += (("g", 6))
    println(map21) //Map(b -> 2, g -> 6, a -> 1, c -> 3, f -> 5)

    val map31 = mutable.LinkedHashMap(("c" -> 3))
    map31 += (("a", 1))
    map31.put("f", 2)
    map31("b") = 4
    println(map31)//Map(c -> 3, a -> 1, f -> 2, b -> 4)

    val lm  = mutable.ListMap(("c" -> 3))
    lm+= (("a", 1))
    lm.put("f", 2)
    println(lm) //Map(f -> 2, c -> 3, a -> 1)

    val map = mutable.HashMap(("f",56),("b"->2),("c"->3)) //b c f
    println(map)
    //也就是说初始化时 map是按key的hashcode或字典顺序来排序，之后的都是插入顺序。


  }
}

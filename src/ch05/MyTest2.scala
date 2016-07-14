package ch05

/**
  * Created by fqc on 2016/7/14.
  */
class MyTest2 {


}

object MyTest2 {
  def main(args: Array[String]) {
    //创建一个List
    val lst0 = List(1, 7, 9, 8, 0, 3, 5, 4, 6, 2)
    //将lst0中每个元素乘以10后生成一个新的集合
    val lst2 = lst0.map(_ * 10)
    println(lst2)

    //将lst0中的偶数取出来生成一个新的集合
    val lst3: List[Int] = lst0.filter(_ % 2 == 0)
    println(lst3)

    //将lst0排序后生成一个新的集合
    val lst4: List[Int] = lst0.sorted
    println(lst4)
    //反转顺序
    println(lst4.reverse)
    //将lst0中的元素4个一组,类型为Iterator[List[Int]]
    val grouped: Iterator[List[Int]] = lst0.grouped(4)
    println(grouped) //non-empty iterator 可以转换为list操作
    for (elem <- grouped) {
      println(elem)
    }
    /*
    List(1, 7, 9, 8)
    List(0, 3, 5, 4)
    List(6, 2)
    */

    //将Iterator转换成List
    val lst5: List[List[Int]] = lst0.grouped(4).toList //分组的iterator转为list操作很方便
    println(lst5) //List(List(1, 7, 9, 8), List(0, 3, 5, 4), List(6, 2))
    println(lst5.flatten) //List(1, 7, 9, 8, 0, 3, 5, 4, 6, 2)
    //将多个list压扁成一个List
    val lines = List("hello tom hello jerry", "hello jerry", "hello kitty")
    //先按空格切分，在压平
    println(lines)
    println(lines.map(_.split(" ")).flatten)

    //并行计算求和

    //化简：reduce
    //将非特定顺序的二元操作应用到所有元素

    //安装特点的顺序


    //折叠：有初始值（无特定顺序）

    //折叠：有初始值（有特定顺序）


    //聚合
    val arr = List(List(1, 2, 3), List(3, 4, 5), List(2), List(0))


    val l1 = List(5, 6, 4, 7)
    val l2 = List(1, 2, 3, 4)
    //求并集

    //求交集

    //求差集

    //println(r3)

    var words = List("hello kobe", "hello jim hello tom hello fqc fqc")
    println(words.map(_.split(" ")).flatten.toList.map(x => (x, 1)))
    //List((hello,1), (kobe,1), (hello,1), (jim,1), (hello,1), (tom,1), (hello,1), (fqc,1), (fqc,1))

    println(words.map(_.split(" ")).flatten.toList.map((_, 1)).groupBy(_._1))
    //Map(fqc -> List((fqc,1), (fqc,1)), kobe -> List((kobe,1)), tom -> List((tom,1)), jim -> List((jim,1)), hello -> List((hello,1), (hello,1), (hello,1), (hello,1)))

    //    println(words.map(_.split(" ")).flatten.toList.map((_,1)).groupBy(_._1).map(_._2))
    //    println(words.map(_.split(" ")).flatten.toList.map(x=>(x,1)).groupBy(_._1).toList)
    //    println(words.map(_.split(" ")).flatten.toList.map(x => (x, 1)).groupBy(_._1).toList.map((_._1, _._2.size)))
    //    println(words.map(_.split(" ")).flatten.toList.map(x => (x, 1)).groupBy(_._1).toList.map(_ => _._2.size))
    //    println(words.map(_.split(" ")).flatten.toList.map(x => (x, 1)).groupBy(_._1).toList.map((_._2.size)))

    println(words.map(_.split(" ")).flatten.toList.map(x => (x, 1)).groupBy(_._1).toList.map(x => (x._1, x._2.size)))
    //List((fqc,2), (kobe,1), (tom,1), (jim,1), (hello,4))

  }
}
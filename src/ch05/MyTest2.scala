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
    val lst5: List[List[Int]] = lst0.grouped(4).toList //分组的iterator转为list操作很方便 ,iterator与list可以互相转换
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

    println(words.map(_.split(" ")).flatten.toList.map(x => (x, 1)).groupBy(_._1).toList.map(x => (x._1, x._2.size))) //较为啰嗦，toList ，map flatten可以使用flatMap代替
    //List((fqc,2), (kobe,1), (tom,1), (jim,1), (hello,4))


    println(words.map(_.split(" ")).flatten.toList.map((_, 1)))
    println(words.map(_.split(" ")).flatten.map((_, 1)))
    println(words.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).map(x => (x._1, x._2.size)))

    //对结果排序
    println(words.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).map(x => (x._1, x._2.size)).toList.sortBy(_._2))
    println(words.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).map(x => (x._1, x._2.size)).toList.sortBy(_._2).reverse)

    //mapValues更加精炼
    println(words.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(x => x.size))
    println(words.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.size)) //直接对对偶元素的value进行操作，key保持不动

    //但是上面有一个特大的局限就是 mapValues或map时都使用的size，如果分组局部间进行聚合优化了，size将是错误的。 即局部combiner
    //先看下基础的
    val arrsum = Array(1, 2, 3, 4, 5, 6)
    println(arrsum.sum)
    println(arrsum.reduce(_ + _)) // 21 前面结果依次累加后面的一个元素 默认调用的reduceLeft. 注意是单线程的，如果数据量大则需要并行

    println(arrsum.par.reduce(_ + _)) //21 具体看机器cpu核数

    //println(arrsum.fold(_+_))需要有合并的默认值
    //使用fold进行并行计算
    println(arrsum.fold(10)(_ + _)) //31
    println(arrsum.par.fold(10)(_ + _)) //71 81 61 都能够跑出来 这是涉及并发问题，怎样解决？
    println(arrsum.par.fold(0)(_ + _)) //21 将初始值设置为0，就避免了上面的问题

    println(arrsum.foldLeft(0)(_ + _)) //21
    println(arrsum.foldRight(10)(_ + _)) //31


    //再次重构wordcount
    println(words.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.foldLeft(0)(_ + _._2)))
    println(words.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.par.foldLeft(0)(_ + _._2)))//单机版并行wordcount操作
  }

}
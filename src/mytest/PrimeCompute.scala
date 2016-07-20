package mytest

import scala.collection.mutable.ArrayBuffer

/**
  * Created by fqc on 2016/7/20.
  * 计算2 to n的质数
  * 可以转换为spark的并行计算
  * scala> val composite = sc.parallelize(2 to n , 3).map(x=>(x,2 to n /x)).flatMap(kv=>kv._2.map(_*kv._1))
  * composite: org.apache.spark.rdd.RDD[Int] = MapPartitionsRDD[17] at flatMap at <console>:23
  * *
  * scala> val result = sc.parallelize(2 to n,3).subtract(composite).collect
  * result: Array[Int] = Array(3, 7, 2, 5)
  */
object PrimeCompute {
  def main(args: Array[String]) {
    val n = 10
    val map: Array[Int] = (2 to 10).toArray.map(x => (x, 2 to n / x)).flatMap(kv => kv._2.map(_ * kv._1))
    println(map.mkString(","))
    val ab: ArrayBuffer[Int] = ArrayBuffer(2 to 10: _*)
    val result: ArrayBuffer[Int] = ab --= map
    println(result)
  }
}

package mytest

import java.io.File

import scala.io.Source

/**
  * Created by fqc on 2016/7/25.
  */
class TestImplicitMethod {


}

//2. 包装类
class RichFile(file: File) {
  def myRead = Source.fromFile(file).getLines().mkString
}

//1. 定义隐式方法，该方法参数为被包装类
object Context {
  implicit def file2RichFile(file: File) = new RichFile(file)
}

object Test {
  def main(args: Array[String]) {
    //3. 导入隐式转换
    import Context._
    val file: File = new File("c:/1.log")
    println(file.myRead)
  }
}
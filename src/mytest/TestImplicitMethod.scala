package mytest

import java.io.File

import scala.io.Source

/**
  * Created by fqc on 2016/7/25.
  */
class TestImplicitMethod {


}
/*
* 隐式转换 对原有API的拓展增强
* */
//2. 包装类
class RichFile(file: File) {
  def myRead = Source.fromFile(file).getLines().mkString
}

//1. 定义隐式方法，该方法参数为被包装类
object Context {
  implicit def file2RichFile(file: File) = new RichFile(file)//原始类型与加强类型
}

object Test {
  def main(args: Array[String]) {
    //3. 导入隐式转换
    import Context._
    //4. 调用隐式转换后的增强类的增强方法
    val file: File = new File("c:/1.log")
    println(file.myRead)
  }
}
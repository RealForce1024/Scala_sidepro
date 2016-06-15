/**
  * Created by fqc on 5/25/16.
  */

//class Point(xc: Int, yc: Int) {
//
//  var x: Int = xc
//  var y: Int = yc
//
//  def move(xc: Int, yc: Int): Unit = {
//    x = xc
//    y = yc
//    println(x)
//    println(y)
//
//  }
//
//}

class Point(val xc: Int, val yc: Int) {
  var x: Int = xc
  var y: Int = yc
  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
    println ("x 的坐标点 : " + x);
    println ("y 的坐标点 : " + y);
  }
}

object Point{

  def main(args: Array[String]) {
    val point = new Point(1,2)
    point.move(1,2)
  }
}

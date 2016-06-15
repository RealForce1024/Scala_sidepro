/**
  * Created by fqc on 5/25/16.
  */
class Location(override val xc:Int, override val yc :Int, val zc:Int) extends Point(xc, yc){
  var z: Int = zc

  def move(dx: Int, dy: Int, dz: Int) {
    x = x + dx
    y = y + dy
    z = z + dz
    println ("x 的坐标点 : " + x);
    println ("y 的坐标点 : " + y);
    println ("z 的坐标点 : " + z);
  }
}



object Location{

  def main(args: Array[String]) {
    val location: Location = new Location(1,2,3)
    location.move(1,2)

  }

}

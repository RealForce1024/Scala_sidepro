package ch06

/**
  * Created by fqc on 2016/7/14.
  */
class Person(var id:Int= 0,var age:Int = 0) {
  val name: String = null //字段需要进行初始化
  //javap -private Person
  /* public class ch06.Person {
     private final java.lang.String name;
     public java.lang.String name(); //注意这里只有getName 因为name为 val
     public ch06.Person();
   }*/

  private var country: String = "china"
  /*public class ch06.Person {
    private final java.lang.String name;
    private java.lang.String country;
    public java.lang.String name();
    private java.lang.String country();
    private void country_$eq(java.lang.String);
    public ch06.Person();
  }*/

  //getter and setter
  def getCountry = name

  def setCountry_=(newCountry: String) {
    country = newCountry
  }

}

object Person {
  def main(args: Array[String]) {
    val person: Person = new Person()
    person.country_=("weihai") //setter
    println(person.country) //getter
  }
}

package ch06

/**
  * Created by fqc on 2016/7/14.
  */
private[ch06] class Person(var id:Int= 0,var age:Int = 0) {//包访问权限，所有子包可以访问，其他包则无法访问
  val name: String = null //字段需要进行初始化
  //javap -private Person
  /* public class ch06.Person {
     private final java.lang.String name;
     public java.lang.String name(); //注意这里只有getName 因为name为 val
     public ch06.Person();
   }*/

  private[this] var gender :String = _ //只有当前类中才能够使用 伴生对象是无法访问的，可以定义公有方法访问 _只有new的时候初始化相当于null

  private var country: String = "china" //只能在伴生对象中访问，其他对象或类时无法访问到的
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

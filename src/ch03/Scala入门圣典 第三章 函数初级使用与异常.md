# Scala入门圣典 第三章 函数初级使用与异常

作者：冯琪超
微信 : 152 010 98976

[toc]


### 前言
Scala作为一门经典的函数式编程语言（也支持面向对象式的混合编程），函数是Scala语言中的一等公民。
我们前两章中使用了很多对象的方法，很多Java程序员对此很熟悉，
但是谈到今天的主角函数时，`可能会觉得函数它和方法没什么不同，其实差别很大`。
在知道差别之前，让我们先来学习下函数的定义与函数的用法
### 函数的定义
```scala
scala> def abs(x: Double): Double = if(x > 0) x else -x
abs: (x: Double)Double
```
说明：
1. 参数类型一定要指定
2. 返回值类型除非是递归调用，否则可以省略
	**递归中必须声明返回值类型，因为Scala编译器无法校验出n*fac(n-1)的返回类型**

```scala
  def fac(n: Int): Int = {
      if (n <= 0) 1
      else {
        println(n + "*(" + n + ")" + "-1")
        n * fac(n - 1)
      }
    }
    println(fac(5))
    /*5*(5)-1
    4*(4)-1
    3*(3)-1
    2*(2)-1
    1*(1)-1
    120*/
  }
```
### 函数的简单用法
案例1
```scala
scala> def abs(x: Double) = if (x > 0) x else -x
abs: (x: Double)Double

scala> abs(3) //直接调用
res20: Double = 3.0

scala> abs(-9.9)
```
案例2

```scala
scala> def fac(n: Int) = {
     | var r = 1
     | for(i <- 1 to n) r = r*i
     | r
     | }
fac: (n: Int)Int

scala> fac(5)
res27: Int = 120
```
return在Scala中尽量避免使用，如果使用也是break的的含义，而非Java语言中的return返回到调用者处。在今后的Scala编程中，大量的匿名函数的return，并不是返回调用处，而是跳出其外层函数。

### 默认参数和命名参数

#### 默认参数使用
```scala
scala> def decorateStr(str: String, left: String="{", right: String="}") = left + str + right
decorateStr: (str: String, left: String, right: String)String

scala> decorateStr("hello")
res29: String = {hello}
```
#### 覆盖默认参数
不喜欢默认参数，也可以自定义填充覆盖
```scala
scala> decorateStr("hello","[","]")
res30: String = [hello]

scala> decorateStr("hello","<<<",">>>")
res31: String = <<<hello>>>
```

注意：默认情况下，参数的覆盖是从左向右的，也就是说如果填充的默认参数数量不够，则还会使用未被填充到的默认参数。

因此decorateStr("hello","<<<") 执行结果将是如下： 

```scala
scala> decorateStr("hello","<<<")
res32: String = <<<hello}
```

相信聪明的你会想到另一种特殊情况，想要打破顺序，自己按照参数的名称来指定。

```scala
scala> decorateStr(str="world",right="}}",left="{{")
res33: String = {{world}}
```

如果混用的话，命名与默认参数，需要将未命名的参数值放到前面。

```scala
scala> decorateStr(str="world",right=">>>") 
res35: String = {world>>>
```

### 变长参数

```scala
scala> def sum(args: Int*) = {
     | var sum = 0
     | for(i <- args) sum += i
     | sum}
sum: (args: Int*)Int

scala> sum(1,2,3,4,5)
res0: Int = 15

scala> sum(1,2,3,4,5,6)
res1: Int = 21
```
注意：args: Int* 得到的是一个序列 Seq ，我们使用过 1 to 10这样的Range区间类型。当sum(n)只传入一个参数的时候，需要是Int型，而Range类型作为一个参数传入必定会报错。

```
scala> sum(1 to 6)
<console>:9: error: type mismatch;
 found   : scala.collection.immutable.Range.Inclusive
 required: Int
              sum(1 to 6)
                    ^
                    
scala> sum((1 to 6).toArray)
<console>:9: error: polymorphic expression cannot be instantiated to expected type;
 found   : [B >: Int]Array[B]
 required: Int
              sum((1 to 6).toArray)
```

我们可以使用以下方式追加 `: _*`，告诉编译器参数按照序列的方式处理
```scala
scala> sum(1 to 5: _*)
res3: Int = 15
```

`: _*` 在递归方法中会有使用到

```scala
scala> def recursiveSum(args: Int*): Int = {
     | if (args.length == 0) 0
     | else
     | args.head + recursiveSum(args.tail: _*)
     | }
recursiveSum: (args: Int*)Int

scala> recursiveSum(1,2,3,4,5)
res4: Int = 15
```
其中head是序列的首个元素，tail是剩余的其他元素序列，变长参数又是个序列，所以此时还是需要将其使用`: _*`转换一下


### 过程

在函数定义中，花括号没有`=` 且没有返回值的(Unit)，称之为`过程`。之所以有过程的存在，是为了其产生的副作用。就像我们调用一个无返回值的方法一样，这个方法做了些事情，但无返回值而已。

比如将字符串打印在box中

```scala
scala> def box(s: String){  //这里是没有 = 号的
     | val border = "-"*s.length+"--\n"
     | println(border + "|" +s +"|\n" + border)
     | }
box: (s: String)Unit

scala> box("fengqc")
--------
|fengqc|
--------

scala> box("hello world")
-------------
|hello world|
-------------
```

`def box(s: String): Unit = {...}`是上面的全写法，但是我个人推荐上面的写法。**Scala有个哲学是在保证清晰可读的前提下，尽量的简写代码。**




### 值的懒加载
在Scala入门圣典中 我将值和变量清晰的区分出来，和大家约定俗成

| Col1      |     Col2 |   Col3   |
| :-------- | --------:| :------: |
| 值|   valule      |  val|
| 变量|    varibale  |  var|

`lazy` val x = 1  // 值x在被第一次取值使用到的时候才会初始化。
案例
读取文件中的文字拼接为字符串，故意写错文件名称，运行并不会出错，当使用了读取的内容时，程序报错。

```scala
scala> lazy val content = scala.io.Source.fromFile("/home/hadoop/a.txt").mkString  //懒加载，此时并未初始化content的值
content: String = <lazy>

scala> println(content) //此时开始初始化content的值
java.io.FileNotFoundException: /home/hadoop/a.txt (No such file or directory)
        at java.io.FileInputStream.open(Native Method)
        at java.io.FileInputStream.<init>(FileInputStream.java:146)
        at scala.io.Source$.fromFile(Source.scala:90)
        at scala.io.Source$.fromFile(Source.scala:75)
```

读取存在的文件
```scala
scala> lazy val content = scala.io.Source.fromFile("/home/fqc/a.txt").mkString
content: String = <lazy>

scala> println(content)
hello world
hello fengqc
hello fengqc
hello kobe
```

总结：我们看到文件的加载初始化开销是非常大的，lazy懒加载对于这类问题相当有用。对于之后的循环依赖、懒数据结构是相当重要的。


对于Scala的lazy加载还可以被看成 val与def之间的一种状态

``` scala
//content定义时就被初始化赋值
val content = scala.io.Source.fromFile("/home/fqc/a.txt").mkString
//在content首次使用时初始化赋值
lazy val content = scala.io.Source.fromFile("/home/fqc/a.txt").mkString
//在content每次使用时都会初始化赋值
def content = scala.io.Source.fromFile("/home/fqc/a.txt").mkString
```
值的懒加载，是有开销的，这个开销是调用一个线程安全的方法检查该值是否被初始化。


## 异常
Scala中的异常与Java的使用方式**大致是一样的**。抛出的对象都是java.lang.Throwable的子类。
但有个特别明显的区别，**Scala没有"受检"异常**--即方法或函数中不需要声明可能会抛出的某种异常。	

"受检"异常的目标是让程序员必须去想那些异常应该在哪里被处理掉，所以Jvm编译时就会检查这些可能会发生的异常。

以下是Scala处理异常并回收清理资源的模板代码。其中在catch块中使用了模式匹配，之后会再细讲
```scala
try{

}catch{
	case _: ...
	case ex:...
}finally{

}
```



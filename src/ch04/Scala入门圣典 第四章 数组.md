# Scala入门圣典 第四章 数组

作者：冯琪超
微信 : 152 010 98976

@(BigData)[618,scala,数组]

[toc]


## 数组的声明方式

伴生对象方式 apply方法  //在第一章中有简单说明
```scala
val arrs = Array(1,2,3,4,5)
```
new的方式
```scala
scala> val arrs = new Array[Int](5) //初始化指定长度指定类型的数组
arrs: Array[Int] = Array(0, 0, 0, 0, 0)

scala> val arrs = new Array[String](5)
arrs: Array[String] = Array(null, null, null, null, null)
```


## 数组的分类

### 1. 定长数组

```scala
scala> val arrs = Array(1,2,3,4,5)
arrs: Array[Int] = Array(1, 2, 3, 4, 5)

scala> val arrs = Array(1 to 5) //注意这里的1 to 5 产生的Range对象是一个元素。需要变成序列类型才可以。 解决方案: : _*
arrs: Array[scala.collection.immutable.Range.Inclusive] = Array(Range(1, 2, 3, 4, 5))

scala> val arrs = Array(1 to 5: _*)
arrs: Array[Int] = Array(1, 2, 3, 4, 5)

scala> val arr = (1 to 5).toArray //间接生成数组，通过Range对象转换
arr: Array[Int] = Array(1, 2, 3, 4, 5)

```

val型的定长数组，修改其中的元素，对元素操作是没有问题的。但是追加 删除操作都是对val数组的操作，肯定不行。
```scala
scala> val strs = Array("hello","world")
strs: Array[String] = Array(hello, world)

scala> strs(1)
res2: String = world

scala> strs(1) = "scala"

scala> strs
res4: Array[String] = Array(hello, scala)
```

再次注意下 Scala中的数组下标访问是 `arrs(index)`的形式的，非Java `(arrs[index])`形式

### 2. 可变数组：数组缓存
Java中的数组是固定长度的，不可变的。但是有ArrayList形式的可变数组。
而Scala肯定有等效的数据结构，那就是ArrayBuffer。

案例
我们采取声明一个空的ArrayBuffer，然后添加元素，数组，删减，更新，索引等一系列操作


#### 末尾添加或移除操作
**高效**的数组缓冲的末尾添加或移除操作
```scala
scala> import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ArrayBuffer

scala> val ab = new ArrayBuffer[Int]()//声明一个空的数组缓冲
ab: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()

scala> ab += 1   //追加一个元素
res9: ab.type = ArrayBuffer(1)

scala> ab += (2,3)  //追加多个元素
res10: ab.type = ArrayBuffer(1, 2, 3)

scala> ab ++= Array(11,12,13) //++= 追加任意的集合[即一个集合对象，并非多个元素的序列例如(1,2,3)] 而使用+=只能添加一个或多个单一元素（即序列），否则异常
res11: ab.type = ArrayBuffer(1, 2, 3, 11, 12, 13)

scala> ab.trimEnd
                            def trimEnd(n: Int): Unit   

scala> ab.trimEnd(3)  //移除末尾三个元素  另trimStart

scala> ab
res13: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3)
```

#### 任意位置的插入或移除
1. 元素准备工作，可忽略
但有点注意 += 与 ++=的区别
```scala
scala> import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ArrayBuffer

scala> val ab = new ArrayBuffer[Int]()
ab: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()

scala> ab ++= (1,2,3,4,5,6,7)
<console>:10: error: too many arguments for method ++=: (xs: scala.collection.TraversableOnce[Int])ab.type
              ab ++= (1,2,3,4,5,6,7)
                 ^

scala> ab ++= (1,2,3)
<console>:10: error: too many arguments for method ++=: (xs: scala.collection.TraversableOnce[Int])ab.type
              ab ++= (1,2,3)
                 ^

scala> ab ++= Array(1,2,3)
res2: ab.type = ArrayBuffer(1, 2, 3)

scala> ab += (4,5,6)
res3: ab.type = ArrayBuffer(1, 2, 3, 4, 5, 6)
```

2. 添加、删除任意位置的元素
```scala
scala> ab
res5: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4, 5, 6)

scala> ab.insert(2,91,92,93,94)//从下标2开始 即 ab(2)=91

scala> ab
res7: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 91, 92, 93, 94, 3, 4, 5, 6)

scala> ab.remove
   def remove(n: Int): A        
   def remove(n: Int, count: Int): Unit   

scala> ab.remove(2) //index=2 91
res8: Int = 91

scala> ab
res9: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 92, 93, 94, 3, 4, 5, 6)

scala> ab.remove(1,2) //index=1  delete[1,1+2) 2 92  从下标1开始移除两个元素

scala> ab
res11: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 93, 94, 3, 4, 5, 6)
```
**任意位置的插入与删除操作，涉及到元素平移，所以效率较低。**

## 遍历数组和数组缓冲
在第二章中，其实已经体验过Scala中的循环遍历的强大了。这里再细讲下。
循环遍历的语法结构 : for(变量<- 表达式)
### 索引方式
for(索引<- 索引区间)
#### 0 to n
**变量即下标索引，表达式则为下标集合。**
```scala
scala> val arrs = Array(1 to 5: _*) //或者 (1 to 5).toArray
arrs: Array[Int] = Array(1, 2, 3, 4, 5)

scala> for(i <- 0 to arrs.length-1) print(i + " ") //变量为下标索引
0 1 2 3 4 

scala> for(i <- 0 to arrs.length-1) print(arrs(i) + " ") //arrs(i) 元素
1 2 3 4 5 
```
#### 0 until n
until 是RichInt类的方法，Scala通过隐式转换将i变量增强，可以这样看：**0 until n = 0 to n-1**
对于从0开始索引的数组、集合通过索引遍历的时候都更加方便。

```scala
scala> for(i <- 0 until arrs.length) print(arrs(i) + " ")
1 2 3 4 5 
```

### 直接遍历集合
结构： **for(元素 <- 元素集合)**
```scala
scala> for(elem <- arrs) print(elem + " ")
1 2 3 4 5 
```

## 数组转换
对数组的转换一般不直接修改原始数组，而是**产生新的数组**
回想到之前章节讲过 yield 关键字的使用，会产生新的集合，所以这里对数组同样适用。

### 每个元素处理
```scala
scala> arrs
res11: Array[Int] = Array(1, 2, 3, 4, 5)

scala> for(elem  <- arrs) yield elem*2//结果会将每次循环yield后面表达式的值组成新的集合
res12: Array[Int] = Array(2, 4, 6, 8, 10)
```

之后会有更加强大的`map`函数和filter函数

### 筛选元素
还可以通过过滤条件对于元素进行筛选
**在for表达式中添加 if判断**
```scala
scala> arrs
res13: Array[Int] = Array(1, 2, 3, 4, 5)

scala> for(elem <- arrs if elem %2 ==0) yield elem*2
res14: Array[Int] = Array(4, 8)
```

### 换一种思路：filter map
虽然上面的方式让Java程序员感到很经验，对于数组的处理Scala如此的强大。但是Scala的强大还远未显示出来，尤其是函数式编程。
其实在实际编码过程中，上述两种对元素的处理对于Scala的函数式编程来说还是显得不够优雅。这里介绍两个强大的函数 **filter 和 map 函数**，两者配合使用能够使用更简练的表达方式完成复杂的工作

```scala
scala> val arrs = Array(1 to 5: _*)
arrs: Array[Int] = Array(1, 2, 3, 4, 5)

scala> arrs.filter(_ %2 ==0).map(_ *2)
res0: Array[Int] = Array(4, 8)
```

### 常用算法API
Scala中内置大量的常用算法Api，使用起来相当方便。工作中常遇到的求和与排序对于Scala来说都是小菜一碟。

注意：以下讲解的api方法都是**产生新的集合数组，而非原地修改**。

#### sum方法
```scala
scala> arrs
res9: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 3, 4, 5)

scala> arrs.sum
res10: Int = 13
```
#### max、min方法

```scala
scala> arrs
res9: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 3, 4, 5)

scala> arrs.max
res11: Int = 5

scala> arrs.min
res14: Int = 1
```

#### 排序
通过REPL，我们可以看到与排序相关的提示

```scala
scala> arrs.sort
sortBy     sortWith   sorted     
```

##### sorted方法
方法定义
```scala
scala> arrs.sorted
   def sorted[B >: A](implicit ord: scala.math.Ordering[B]): Repr   
```

方法使用
```scala
scala> val ab = ArrayBuffer(9,3,1,2)
ab: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(9, 3, 1, 2)

scala> ab.sorted
                                                                 def sorted[B >: A](implicit ord: scala.math.Ordering[B]): Repr   

scala> ab.sorted //默认升序
res26: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 9)

scala> ab.sorted.reverse //使用reverse函数 反转为降序
res30: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(9, 3, 2, 1)
```

##### sortWith方法
使用sortWith可以提供自定义的比较规则（一个比较函数，高阶函数中会细讲）
结构：`sortWith(比较规则)`

```scala
scala> ab
res27: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(9, 3, 1, 2)

scala> ab.sortWith
    def sortWith(lt: (A, A) => Boolean): Repr   

scala> ab.sortWith(_ < _) //升序 
res28: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 9)

scala> ab.sortWith(_ > _) //降序 
res29: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(9, 3, 2, 1)
```

##### sortBy

```scala
scala> ab
res31: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(9, 3, 1, 2)

scala> ab.sortBy(_)
<console>:10: error: missing parameter type for expanded function ((x$1) => ab.sortBy(x$1))
              ab.sortBy(_)
                        ^
scala> ab.sortBy
 def sortBy[B](f: A => B)(implicit ord: scala.math.Ordering[B]): Repr   

scala> ab.sortBy(x => x)
res33: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 9)
```


### Difference between sorted, sortWith and sortBy in Scala
[参看](http://techie-notebook.blogspot.jp/2014/07/difference-between-sorted-sortwith-and.html)
```
Difference between sorted, sortWith and sortBy in Scala
Scala collections provide you three options for sorting: sorted( ), sortWith( ) and sortBy( ). Here is a simplified explanation:

sorted
Will sort the list using the natural ordering (based on the implicit Ordering passed)

sortBy (an attribute)
Sort by a given attribute using the attribute's type.
e.g. given a list of Person objects, if you want to sort them in ascending order of their age (which is an Int), you could simply say: personList.sortBy(_.age)

sortWith (a function)
Takes a comparator function. Useful when you want to specify a custom sorting logic. 
e.g. if you want to sort by age descending, you could write this as: 

personList.sortWith{(leftE,rightE) => 
     leftE.age > rightE.age
}

Or, more simply: personList.sortWith(_.age > _.age)
```


```
// Sequence of numbers
val xs = Seq(1, 5, 3, 4, 6, 2)

// Sort using Natural ordering as defined for Integers in Scala Library
xs.sorted //1,2,3,4,5,6

// Sort 'with' a comparator function
xs.sortWith(_<_) //1,2,3,4,5,6
xs.sortWith(_>_) //6,5,4,3,2,1
xs.sortWith((left,right) => left > right) //6,5,4,3,2,1

// Create a Person class
case class Person(val name:String, val age:Int)

// Define a list of Persons
val ps = Seq(Person("John", 32), Person("Bruce", 24), Person("Cindy", 33), Person("Sandra", 18))

// Sort People by increasing Age (natural ordering of Int will kick in)
ps.sortBy(_.age) //List(Person(Sandra,18), Person(Bruce,24), Person(John,32), Person(Cindy,33))

// Sort People by decreasing Age, using a comparator function
ps.sortWith(_.age > _.age) //List(Person(Cindy,33), Person(John,32), Person(Bruce,24), Person(Sandra,18))
```

## mkString方法
mkString方法对于查看集合元素，格式化查看相当遍历。
1. 方法定义
```scala
scala> ab.mkString
    def mkString(sep: String): String            
    def mkString(start: String, sep: String, end: String): String   
	def mkString: String             
```
2. 查看数组元素

```scala
scala> ab
res35: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(9, 3, 1, 2)
scala> ab.mkString(">>>","&","<<<")
res36: String = >>>9&3&1&2<<<
```
                               
3. 与toString的区别
```scala
scala> val arrs = Array(1 to 5: _*)
arrs: Array[Int] = Array(1, 2, 3, 4, 5)

scala> arrs
res39: Array[Int] = Array(1, 2, 3, 4, 5)

scala> arrs.toString //调用Java的toString方法，打印内存地址
res41: String = [I@46c87fe5


scala> ab
res43: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(9, 3, 1, 2)

scala> ab.toString  //显示出ArrayBuffer类型
res44: String = ArrayBuffer(9, 3, 1, 2)

```

## 多维数组

```scala
scala> val arrs = Array.ofDim[Int](3,4)
arrs: Array[Array[Int]] = Array(Array(0, 0, 0, 0), Array(0, 0, 0, 0), Array(0, 0, 0, 0))

scala> arrs(0,0) = 99
<console>:10: error: too many arguments for method update: (i: Int, x: Array[Int])Unit
              arrs(0,0) = 99
                        ^

scala> arrs(0)(0) = 99

scala> arrs
res48: Array[Array[Int]] = Array(Array(99, 0, 0, 0), Array(0, 0, 0, 0), Array(0, 0, 0, 0))
```

可以创建不规则的数组，即每行的数组元素数量不同。用到的不多，需要时可查。


## 与Java数组的交互
Scala底层很多api都是基于Java api实现的。Scala的数组是由Java数组实现的，所以两者很容易进行交互。










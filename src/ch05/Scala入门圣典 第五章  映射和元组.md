# Scala入门圣典 第五章  映射和元组

@(BigData)[618,scala,映射,元组,map,tuple]

作者：冯琪超
微信 : 152 010 98976

[toc]

>如果只能用一种数据结构，那就用哈希表吧

哈希表可以更加抽象说成是映射，最灵活多变的数据结构之一。
Scala中的映射不仅简洁灵活，且使用方便。

## 一、映射与对偶元组的关系
映射是**键/值**对偶的集合。 
`键值对`的说法有Java基础的人肯定不会陌生。
`对偶`是相对于元组这个概念来说的。
元组是n个对象的聚集，可以是不同类型的对象。
而对偶则是元组的一种特殊情况，即n=2。
元组的使用场景：元组是在将需要2个或多个元素组织在一起的时候非常有用。

## 二、映射

### 映射中创建对偶的方式
注意：映射中元素是特殊的元组对象，即对偶
#### `->`创建对偶
```scala
scala> "kobe" -> 81
res4: (String, Int) = (kobe,81)
```
#### `( )`创建对偶
```scala
scala> ("jordan",69)
res5: (String, Int) = (jordan,69)
```
### 构造映射
#### 可变与不可变映射
1. Map对象构造不可变映射
2. scala.collection.mutable.Map() 指定可变映射对象
3. 构造空集合最好指明对偶（键值对）元素的类型

```scala
scala> val map = Map("kobe"->1,"james"->2,"cury"->3)
map: scala.collection.immutable.Map[String,Int] = Map(kobe -> 1, james -> 2, cury -> 3)

scala> map("james") = 9999
<console>:9: error: value update is not a member of scala.collection.immutable.Map[String,Int]
              map("james") = 9999
              ^

scala> val map = scala.collection.mutable.Map("kobe"->1,"james"->2,"cury"->3)
map: scala.collection.mutable.Map[String,Int] = Map(james -> 2, kobe -> 1, cury -> 3)

scala> map("james") = 9999

scala> map
res2: scala.collection.mutable.Map[String,Int] = Map(james -> 9999, kobe -> 1, cury -> 3)
```
构造空集合最好指明对偶（键值对）元素的类型
```scala
scala> Map
res3: scala.collection.immutable.Map.type = scala.collection.immutable.Map$@41e31981

scala> val map =  Map()
map: scala.collection.immutable.Map[Nothing,Nothing] = Map()

scala> val map = Map[String,Int]()
map: scala.collection.immutable.Map[String,Int] = Map()
```

看过映射中创建元组的案例
我们自然会相当还有种方式构造出映射。

```scala
scala> val map = Map(("kobe",81),("jordan",69),("james",10))
map: scala.collection.immutable.Map[String,Int] = Map(kobe -> 81, jordan -> 69, james -> 10)
```
- 对映射数据结构的认识
**映射的这种数据结构是一种将`键映射到值的函数`，区别在于通常的函数计算值，而`映射只是作为查询`。**


### 获取映射中的值
在Scala中，映射与函数的相似性非常明显。使用`( )`表示法来查询键对应的值。

#### Map(key)
```scala
scala> map("kobe")
res1: Int = 81
```
#### 键不存在异常

```scala
scala> map("wuzetian")
java.util.NoSuchElementException: key not found: wuzetian
        at scala.collection.MapLike$class.default(MapLike.scala:228)
        ......
```

#### contains(key)预判断

```scala
scala> map.contains("wuzetian")
res3: Boolean = false
```
#### getOrElse预判断的增强
getOrElse
```scala
scala> if(map.contains("kobe")) map("kobe") else 0
res4: Int = 81

scala> map.get
get         getOrElse   

scala> map.get
      def get(key: A): Option[B]   

scala> map.getOrElse
      def getOrElse[B1 >: B](key: A, default: => B1): B1   

scala> map.getOrElse("kobe")
<console>:9: error: not enough arguments for method getOrElse: (key: String, default: => B1)B1.
Unspecified value parameter default.
              map.getOrElse("kobe")
                           ^

scala> map.getOrElse("kobe",0)
res6: Int = 81
```
#### 初始Option对象
注意：get(key)方法返回Option选项
Option对象要么返回Some（键对应的值），要么返回None
```scala
scala> map.get("kobe")
res7: Option[Int] = Some(81)

scala> map.get("kobe1")
res8: Option[Int] = None
```
Option对象之后会再细讲。


### 更新映射中的值
#### 更新元素
```scala
scala> mutable_map("james") = -1

scala> mutable_map
res19: scala.collection.mutable.Map[String,Int] = Map(james -> -1, yaoming -> 41, kobe -> 81, jordan -> 69)
```

#### 新增一个元素
- **要确保是可变map**
```scala
scala> map
res9: scala.collection.immutable.Map[String,Int] = Map(kobe -> 81, jordan -> 69, james -> 10)

scala> map += ("yaoming"->41)
<console>:9: error: value += is not a member of scala.collection.immutable.Map[String,Int]
              map += ("yaoming"->41)
                  ^
```

- **可变映射新增元组**
```scala
scala> val mutable_map = scala.collection.mutable.Map("kobe" -> 81, "jordan" -> 69, "james" -> 0)
mutable_map: scala.collection.mutable.Map[String,Int] = Map(james -> 0, kobe -> 81, jordan -> 69)

scala> mutable_map += ("yaoming" -> 41, "yaoming2" -> 42,"yaoming3" -> 43)
res21: mutable_map.type = Map(yaoming3 -> 43, james -> -1, yaoming -> 41, yaoming2 -> 42, kobe -> 81, jordan -> 69)
```

- **保存新生成的映射**
("james" -> 20, "allen" -> 100) //james被修改为20，allen被新增进来
```scala
scala> mutable_map
res23: scala.collection.mutable.Map[String,Int] = Map(yaoming3 -> 43, james -> -1, yaoming -> 41, yaoming2 -> 42, kobe -> 81, jordan -> 69)

scala> val new_map = mutable_map + ("james" -> 20, "allen" -> 100)
new_map: scala.collection.mutable.Map[String,Int] = Map(yaoming3 -> 43, james -> 20, yaoming -> 41, allen -> 100, kobe -> 81, yaoming2 -> 42, jordan -> 69)
```
- **移除元素**

```scala
scala> new_map
res24: scala.collection.mutable.Map[String,Int] = Map(yaoming3 -> 43, james -> 20, yaoming -> 41, allen -> 100, kobe -> 81, yaoming2 -> 42, jordan -> 69)

scala> new_map -= "yaoming2"
res25: new_map.type = Map(yaoming3 -> 43, james -> 20, yaoming -> 41, allen -> 100, kobe -> 81, jordan -> 69)
```

移除多个元素
```scala
scala> new_mutable_map
res29: scala.collection.mutable.Map[String,Int] = Map(yaoming3 -> 43, james -> 10, yaoming -> 44, kobe -> 81, yaoming2 -> 42, jordan -> 69)

scala> val new2_mutable_map = new_mutable_map -("yaoming3","yaoming2")
new2_mutable_map: scala.collection.mutable.Map[String,Int] = Map(james -> 10, yaoming -> 44, kobe -> 81, jordan -> 69)
```

### 迭代映射
#### `for((k,v) <- map)` 
该结构，使用到了模式匹配，之后会细讲。
```scala
scala> for(i <- map) println(i)
(x,1)
(y,2)
(z,3)

scala> for((x,y) <- map) println((x,y))
(x,1)
(y,2)
(z,3)
```

#### 单独获取keySet Values
```scala
scala> map
res11: scala.collection.immutable.Map[String,Int] = Map(x -> 1, y -> 2, z -> 3)

scala> map.keySet
res12: scala.collection.immutable.Set[String] = Set(x, y, z)

scala> map.values
values           valuesIterator   

scala> map.values
                          def values: Iterable[B]   

scala> map.values
res13: Iterable[Int] = MapLike(1, 2, 3)

scala> for(i <- map.values) print(i + " ")
1 2 3  
```
#### 反转k、v

```scala
scala> for((k,v) <- map) yield (v,k)
res22: scala.collection.immutable.Map[Int,String] = Map(1 -> x, 2 -> y, 3 -> z)
```

#### map高阶函数的使用
之前在讲解循环的时候又用到过，for循环的遍历在Scala中不如map函数使用的频率。
```scala
scala> map.map(x => x)
res21: scala.collection.immutable.Map[String,Int] = Map(x -> 1, y -> 2, z -> 3)
```

## 映射实现
映射中有很多种实现，默认哈希表结构（但注意前面使用的Map，并非hashMap，下面会说明），还有满足其他功能需求的映射结构，如树形映射(已排序)
### 已排序映射SortedMap --不可变

```scala
scala> val sorted_map = scala.collection.immutable.SortedMap("james"->12,"kobe" ->24, "allen" ->1)
sorted_map: scala.collection.immutable.SortedMap[String,Int] = Map(allen -> 1, james -> 12, kobe -> 24)

```
注意排序是区分字母大小写的
```scala
scala> val sorted_map = scala.collection.immutable.SortedMap("James"->12,"kobe" ->24, "allen" ->1)
sorted_map: scala.collection.immutable.SortedMap[String,Int] = Map(James -> 12, allen -> 1, kobe -> 24)
```

Scala中目前没有可变的SortedMap，可以使用Java中的 TreeMap实现

```scala
scala> val mutable_sorted_map = new java.util.TreeMap[String,Int]()
mutable_sorted_map: java.util.TreeMap[String,Int] = {}
```

在Scala中请遵守Scala的规范，即使是使用Java

```scala
scala> val mutable_sorted_map = new java.util.TreeMap<String,Int>()
<console>:1: error: ';' expected but ',' found.
       val mutable_sorted_map = new java.util.TreeMap<String,Int>()
                                                            ^
```

### HashMap实现
```scala
scala> val map = Map("james" -> 0, "kobe" -> 24, "allen"->1)
map: scala.collection.immutable.Map[String,Int] = Map(james -> 0, kobe -> 24, allen -> 1)

scala> val map = scala.collection.immutable.HashMap("james" -> 0, "kobe" -> 24, "allen"->1)
map: scala.collection.immutable.HashMap[String,Int] = Map(allen -> 1, kobe -> 24, james -> 0)
```

我们看到HashMap与Map是有区别的，HashMap重新排过序的。

### 插入顺序 LinkedHashMap

```scala
scala> val linked_hashmap = scala.collection.mutable.LinkedHashMap("kobe" -> 1, "james" ->2, "allen" ->3)
linked_hashmap: scala.collection.mutable.LinkedHashMap[String,Int] = Map(kobe -> 1, james -> 2, allen -> 3)
```
测试结果我们看到与Map一样保持插入顺序。

### 与Java api的交互
#### scala使用java api
分为两步
1. 导入告知编译器要做什么转换,使用什么转换类
```scala
scala> import scala.collection.JavaConversions.mapAsScalaMap
import scala.collection.JavaConversions.mapAsScalaMap
```
2. 指定转换的类型 触发转换

```scala
scala> val java_treemap:scala.collection.mutable.Map[String,Int] = new java.util.TreeMap[String,Int]()
java_treemap: scala.collection.mutable.Map[String,Int] = Map()
```

案例

```scala
scala> import scala.collection.JavaConversions.propertiesAsScalaMap
import scala.collection.JavaConversions.propertiesAsScalaMap

scala> val properties: scala.collection.Map[String,String] = System.getProperties()

properties: scala.collection.Map[String,String] =
Map(java.runtime.name -> Java(TM) SE Runtime Environment, sun.boot.library.path -> /Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home/jre/lib, java.vm.version -> 25.77-b03, user.country.format -> CN, gopherProxySet -> false, java.vm.vendor -> Oracle Corporation, java.vendor.url -> http://java.oracle.com/, path.separator -> :, java.vm.name -> Java HotSpot(TM) 64-Bit Server VM, file.encoding.pkg -> sun.io, user.country -> US, sun.java.launcher -> SUN_STANDARD, sun.os.patch.level -> unknown, java.vm.specification.name -> Java Virtual Machine Specification, user.dir -> /Users/fqc/github, java.runtime.version -> 1.8.0_77-b03, java.awt.graphicsenv -> sun.awt.CGraphicsEnvironment, java.endorsed.dirs -> /Library/Java/JavaVirtualMac...
```



#### Java 使用 scala api
将scala的map集合作为参数传入给Java方法。 
步骤如scala使用Java是一样的
**设置awt窗口字体**
```scala
scala> import java.awt.font.TextAttribute._
import java.awt.font.TextAttribute._

scala> var attrs = Map(FAMILY->"Serif", SIZE->12)
attrs: scala.collection.immutable.Map[java.awt.font.TextAttribute,Any] = Map(java.awt.font.TextAttribute(family) -> Serif, java.awt.font.TextAttribute(size) -> 12)

scala> val font = new java.awt.F
FileDialog             FlowLayout             FocusManager
FocusTraversalPolicy   Font                   FontFormatException
FontMetrics            Frame

scala> val font = new java.awt.Font
Font                  FontFormatException   FontMetrics

scala> val font = new java.awt.Font(attrs)
font: java.awt.Font = java.awt.Font[family=Serif,name=Serif,style=plain,size=12]
```



## 三、元组

### 1. 概念
在研究映射的时候，有提到过元组及对偶。
映射是对偶的集合，对偶是元组的最简单的形式（特例 n=2）。
**元组是[不同类型的]值的聚集。**

### 2.元组的构成
`(element*) `
```scala
scala> val a_tuple = (1,"kobe",Array(1,2,3))
a_tuple: (Int, String, Array[Int]) = (1,kobe,Array(1, 2, 3))
```

### 3. 访问元组
元组与数组的不同在于
- `只读的`
- `下标从1开始`
- `_index访问`

```scala
scala> a_tuple
res0: (Int, String, Array[Int]) = (1,kobe,Array(1, 2, 3))

scala> a_tuple._0  //记住是从1开始的
<console>:11: error: value _0 is not a member of (Int, String, Array[Int])
              a_tuple._0
                      ^
scala> a_tuple._1
res4: Int = 1

scala> a_tuple._2
res5: String = kobe

scala> a_tuple._3
res6: Array[Int] = Array(1, 2, 3)
```
另外 可以使用空格代替 .调用 比如

```scala
scala> a_tuple._1
res7: Int = 1

scala> a_tuple _1
res8: Int = 1
```
### 4. 元组赋值

```scala
scala> a_tuple
res9: (Int, String, Array[Int]) = (1,kobe,Array(1, 2, 3))

scala> val (a,b,c) = a_tuple
a: Int = 1
b: String = kobe
c: Array[Int] = Array(1, 2, 3)

val a,b = 1,2  //会异常

scala> val a,b = (1,2) //元组作为一个值分别赋值给a,b
a: (Int, Int) = (1,2)
b: (Int, Int) = (1,2)

scala> val (a,b) = (1,2) //这里才是一一赋值
a: Int = 1
b: Int = 2
```


需要对应上
```scala
scala> val (a,b) = a_tuple
<console>:12: error: constructor cannot be instantiated to expected type;
 found   : (T1, T2)
 required: (Int, String, Array[Int])
       val (a,b) = a_tuple
           ^
<console>:12: error: recursive value x$1 needs type
       val (a,b) = a_tuple
            ^
```
如果有些组元是不需要的，`_`来代替
```scala
scala> val (a,b,_) = a_tuple
a: Int = 1
b: String = kobe

scala> val (a,_,c) = a_tuple
a: Int = 1
c: Array[Int] = Array(1, 2, 3)
```

特别注意 需要 `( )` ，否则就会变量赋值一样了

```scala
scala> val a,_,c = a_tuple
a: (Int, String, Array[Int]) = (1,kobe,Array(1, 2, 3))
_: (Int, String, Array[Int]) = (1,kobe,Array(1, 2, 3))
c: (Int, String, Array[Int]) = (1,kobe,Array(1, 2, 3))
```




### 5. 元组的地道用法
#### 1. 返回多个值
元组有个特殊的用法.
元组可以为那些需要返回多个值的函数或方法做包装。
例如 ：StringOps类中的partition方法

```scala
scala> val str = "New York"
str: String = New York

scala> str.partition(_.isUpper)
res10: (String, String) = (NY,ew ork)//（满足条件,非满足）
```

#### 2. 拉练操作zip
元组本身是多个值的集合，**即可以绑定多个值在一起**，意味着他们可以同时被处理。Scala中使用zip方法对元组集合处理。
##### 1. 案例1
zip方法聚集集合，集合元素为元组
```scala
scala> val name = Array("kobe","james","jordan")
name: Array[String] = Array(kobe, james, jordan)

scala> val no = Array(24,6,23)
no: Array[Int] = Array(24, 6, 23)

scala> val player = name.zip(no)
player: Array[(String, Int)] = Array((kobe,24), (james,6), (jordan,23))
```
##### 2. 案例2

```
scala> val symbols = Array("<","-",">")
symbols: Array[String] = Array(<, -, >)

scala> val counts = Array(2,10,2)
counts: Array[Int] = Array(2, 10, 2)


scala> val pairs = symbols.zip(counts)
pairs: Array[(String, Int)] = Array((<,2), (-,10), (>,2))

scala> for((x,y) <- pairs) print(x*y)
//结果
<<---------->>
```
##### 案例2 变型
注意元组的元素访问形式 `_index`
```scala
scala> pairs.map(x => x)
res19: Array[(String, Int)] = Array((<,2), (-,10), (>,2))

scala> pairs.map((x,y) => (x,y))
<console>:13: error: wrong number of parameters; expected = 1
              pairs.map((x,y) => (x,y))
                              ^

scala> pairs.map(x => x._1 * x._2)
res22: Array[String] = Array(<<, ----------, >>)
```

#### 3. 将**对偶的集合**转换为映射
如果分别有key和value的集合，可以使用toMap方法转为映射

```scala
scala> val keys = Array("kobe","james","jordan")
keys: Array[String] = Array(kobe, james, jordan)

scala> val values = Array(24,6,23)
values: Array[Int] = Array(24, 6, 23)

scala> keys.zip(values).toMap
res25: scala.collection.immutable.Map[String,Int] = Map(kobe -> 24, james -> 6, jordan -> 23)
```
toMap方法是可以将**对偶的集合**转换为映射
```scala
scala> ("x",1).toMap  //只是对偶，非集合
<console>:10: error: value toMap is not a member of (String, Int)
              ("x",1).toMap
                      ^

scala> (("x",1)).toMap //这也非集合
<console>:10: error: value toMap is not a member of (String, Int)
              (("x",1)).toMap
                        ^

scala> Array(("x",1)).toMap
res28: scala.collection.immutable.Map[String,Int] = Map(x -> 1)
```


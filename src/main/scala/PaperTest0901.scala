import scala.io.{BufferedSource, Source}

object PaperTest0901  {

  def main(args: Array[String]): Unit = {
    val t1 = Array(1, 3)
    val t2 = Array(2, 4)
//     var a = def t3(){} t1,t2,t3
//    select * from t ;t1
//    select * from t ;t2
//    select * from t ;t3
//    select distinct  id from t1,t2,t3 where t1.dt+2=t2.dt+1=t3.dt
    t1.zip(t2).foreach(println(_))
    //如果是子类混入特质的话会从左到右调用构造器
    new Stu
    val p = new Person with A with B //动态混入，调用方法时会从右向左调用，可以super[类]指定调用，不指定的话会依次顺序向左调用
    p.foo

    //第一步：读取本地文件
    var source: BufferedSource = Source.fromFile("D:/个人/ELK0925/hue的安装和使用.txt")
    //第二步：调用getLine方法返回一个迭代器
    var iter:Iterator[String] = source.getLines()
    println(iter)
    //第三步：wordcount统计
    val step1: List[String] = iter.toList.flatMap(x=>{x.split(" ")})
    println("step1+"+step1)
    val step2: Map[String, List[String]] =step1.groupBy(x=>x)
    println("step2+"+step2)
    val step3: Map[String, Int] =step2.mapValues(x=>x.size)
    println("step3+"+step3)

    myCalc("4")
  }

  def myCalc(a:String):Unit={
    //匹配1,2,3,_
    a match {
      case "1" => {print("red")}
      case "2"=> {print("green")}
      case "3"=> {print("yellow")}
      case _ => {print("匹配不成功！")}
    }
  }
}

trait F {
  def foo = {
    println("foo f...")
  }
}

trait A extends F {
  println("a...")

  override def foo = {
    println("foo A...")
    super.foo
  }
}

trait B extends F {
  println("b...")

  override def foo = {
    println("foo B...")
    super[F].foo//调用方法的话，可以指定，不指定按从右到左的顺序调用
  }
}

class Person {
  println("person...")
}

class Stu extends Person with A with B {
  println("stu...")
}


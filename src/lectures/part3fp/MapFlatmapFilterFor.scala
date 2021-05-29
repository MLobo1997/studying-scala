package lectures.part3fp

import lectures.part2oop.{Cons, Empty}
import lectures.part2oop.Generics.MyList

object MapFlatmapFilterFor extends App{
  val list = List(1,2,3)
  println(list.head)
  println(list.tail)

  println(list.map(_+1))
  println(list.map(_+"is a number"))

  println(list.filter(_ % 2 == 0))

  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))


  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("white", "black")

  println(chars.flatMap(c => numbers.flatMap(n => colors.map(c.toString + n + _))))

  list.foreach(println)

  // for-comprehensions to make big maps and flat maps more undestandable
  //The compiler turns this into chains of flatmaps and maps
  val forCombinations = for {
    n <- numbers if n % 2 == 0 //this is called a guard
    c <- chars
    color <- colors
  } yield c.toString + n + color
  println(forCombinations)

  //equivalent to foreach
  for {
    n <- list
  } println(n)

  //another syntax overload
  list.map { x =>
    x * 2
  }

  val myList = Cons(1, Cons(2, Cons(3, Empty)))
  val myColors = Cons("red", Cons("lue", Cons("green", Empty)))
  for {
    n <- myList
  } println(n)

  val forMyList = for {
    n <- myList
    color <- myColors
  } yield color + n
  println(forMyList)
}

package lectures.part3fp

import lectures.part2oop.{Cons, Empty, MyList}

object HOFsAndCurries extends App {
  val superFunction: (Int, (String, (Int => Boolean) => Int) => Int => Int) = null
  //highter order fucntion

  def NTimes: (Int, Int, Int => Int) => Int = (value, N, f) =>
    if (N <= 0) value
    else NTimes(f(value), N - 1, f)

  println(NTimes(6, 7, (x: Int) => x + 1))

  def NTimesBetter(f: Int => Int, N: Int): Int => Int =
    if (N <= 0) (x: Int) => x
    else (x: Int) => NTimesBetter(f, N - 1)(f(x))

  def sumTen = NTimesBetter((x: Int) => x + 1, 10)
  println(sumTen(5))

  //curried functions - sequencially called functions. Just like the . in haskell
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  //curried because. Functions with multiple parameter lists
  superAdder(3)(10)

  def curriedFormatter(c: String)(n: Double): String = c.format(n)

  val standardFormatter: Double => String = curriedFormatter("%4.2f") //if the type is not specified it crashes
  val preciseFormatter: Double => String = curriedFormatter("%10.8f")

  println(standardFormatter(Math.PI))
  println(preciseFormatter(Math.PI))

  val l: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  l.foreach(println)

  def intCmp(a: Int, b: Int): Int = {
    if (a > b) 1
    else if (a < b) -1
    else 0
  }

  val l2: MyList[Int] = Cons(3, Cons(2, Cons(5, Cons(1, Empty))))
  println(l2.reverse)
  println("Sort:")
  println(l2)
  println(l2.sort(intCmp))
  println("----")

  println(l.zipWith(l, Math.addExact))

  println(l.fold(10)(Math.multiplyExact))

  def toCurry[A,B, C](f: (A, B) => C): A => B => C =
    (a: A) => (b: B) => f(a, b)

  val curriedAdd: Int => Int => Int = toCurry(_ + _)
  println(curriedAdd(1)(2))

  def fromCurry[A,B,C](f: A => B => C): (A, B) => C =
    (a: A, b: B) => f(a)(b)

  val normalAdd: (Int, Int) => Int = fromCurry(curriedAdd)
  println(normalAdd(1, 2))

  def compose[A,B,C](f: B => C, g: A => B): A => C = (x: A) => f(g(x))
  def andThen[A,B,C](f: A => B, g: B => C): A => C = (x: A) => g(f(x))
}


package lectures.part2oop

import lectures.part2oop.Generics.MyList

import scala.annotation.tailrec

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B] //New element must be a supertype of current type
  def append[B >: A](element: B): MyList[B]
  def printElements: String
  //higher order functions
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def ++[B >: A](other: MyList[B]): MyList[B]
  def foreach(func: A => Unit): Unit
  def sort(comparisonFunction: (A, A) => Int): MyList[A]
  def zipWith[C, B >: A](other: MyList[B], func: (B, B) => C): MyList[C]
  def reverse: MyList[A]
  //def fold[C, B >: A](start: C)(func: (C, B) => C): C
  def fold[C, B >: A](start: C)(func: (C, B) => C): C
  override def toString: String = s"[$printElements]"
}

case object Empty extends MyList {
  //Nothing is a proper substitute of any type
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)

  def append[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def ++[B >: Nothing](other: MyList[B]): MyList[B] = other

  def foreach(func: Nothing => Unit): Unit = ()

  def sort(comparisonFunction: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  def zipWith[C, B >: Nothing](other: MyList[B], func: (B, B) => C): MyList[C] = Empty

  def fold[C, B >: Nothing](start: C)(func: (C, B) => C): C = start

  def reverse: MyList[Nothing] = Empty
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = Cons(element: B, this)

  def append[B >: A](element: B): MyList[B] =
    Cons(h, t.append(element))

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  //def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    //Cons(transformer.transform(h), t.map(transformer))

  def map[B](transformer: A => B): MyList[B] = {
    Cons(transformer(h), t.map(transformer))
  }

  //def filter(predicate: MyPredicate[A]): MyList[A] = {
    //if (predicate.test(h)) Cons(h, t.filter(predicate))
    //else t.filter(predicate)
  //}

  def filter(test: A => Boolean): MyList[A] =
    if (test(h)) Cons(h, t.filter(test))
    else t.filter(test)

  def ++[B >: A](other: MyList[B]): MyList[B] = Cons(h, t ++ other)

  //def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
  //transformer.transform(h) ++ t.flatMap(transformer)
  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def foreach(func: A => Unit): Unit = {
    func(h)
    t.foreach(func)
  }

  def sort(comparisonFunction: (A, A) => Int): MyList[A] = {
    @tailrec
    def insertInOrder(x: A, sortedList: MyList[A], newList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) newList.add(x).reverse
      else if (comparisonFunction(x, sortedList.head) <= 0) (sortedList ++ newList.add(x)).reverse
      else insertInOrder(x, sortedList.tail, newList.add(sortedList.head))

    //def insertInOrder(x: A, sortedList: MyList[A]): MyList[A] =
      //if (sortedList.isEmpty) Cons(x, Empty)
      //else if (comparisonFunction(x, sortedList.head) <= 0) Cons(x, sortedList)
      //else Cons(sortedList.head, insertInOrder(x, sortedList.tail))

    val sortedTail = t.sort(comparisonFunction)
    insertInOrder(h, sortedTail, Empty)
  }

  def zipWith[C, B >: A](other: MyList[B], func: (B, B) => C): MyList[C] =
    Cons(func(h, other.head), t.zipWith(other.tail, func))

  def fold[C, B >: A](start: C)(func: (C, B) => C): C =  t.fold(func(start, h))(func)

  def reverse: MyList[A] = {
    @tailrec
    def reverseAux(thisList: MyList[A], newList: MyList[A]): MyList[A] =
      if (thisList.isEmpty) newList
      else reverseAux(thisList.tail, newList.add(thisList.head))

    reverseAux(this, Empty)
  }
}

object Test extends App {
  def stringToChars(string: String): MyList[Char] = {
    if (string.isEmpty) Empty
    else Cons(string.head,  stringToChars(string.tail))
  }

  val list = Cons(1, Cons(2, Cons(3, Empty)))
  println(list.tail.head)
  println(list.add(4).head)
  println(list)
  println(list.append(5))

  val listOfString = Cons("Hi", Cons("how are you", Empty))
  println(listOfString)
  println(listOfString.filter((predicate: String) => predicate.length > 3))
  println(listOfString.map((obj: String) => obj.length))
  println(listOfString ++ Cons("3rd", Cons("4th", Empty)))
  println(listOfString ++ Empty)
  println(Empty ++ listOfString)
  println(listOfString.flatMap((obj: String) => stringToChars(obj)))
}
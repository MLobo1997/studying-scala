package exercises.inheritance

import scala.annotation.tailrec

abstract class MyList[T] {
  /*
  method  head
          tail
          isEmpty
          add(int)
          toString
   */
  def head: T

  def tail: MyList[T]

  def isEmpty: Boolean

  def add(value: T): Unit

  def stringRepresentation(string: String = ""): String

  override def toString: String = s"[${stringRepresentation()}]"
}

object LinkedList {
  def apply[T](node: Option[Node[T]]): LinkedList[T] = {
    val l = new LinkedList[T]
    l.current = node
    l
  }
}

private case class Node[T](var value: T, var next: Option[Node[T]] = None){
  def hasNext: Boolean = !next.isEmpty

  def setNext(value: T): Unit = next = Option(Node(value))
}

class LinkedList[T] extends MyList[T]{

  private var current: Option[Node[T]] = None

  def head: T = current.get.value

  def tail: LinkedList[T] = LinkedList(current.get.next)

  def isEmpty: Boolean = current.isEmpty

  def add(value: T): Unit = {
    if (!isEmpty && !current.get.hasNext) current.get.setNext(value)
    else if (isEmpty) current = Option(Node(value))
    else tail.add(value)
  }

  def stringRepresentation(string: String = ""): String = {
    if (isEmpty) string
    else if (!current.get.hasNext) string + head.toString
    else tail.stringRepresentation(s"$string${head.toString} ")
  }

}

object ListsExercise extends App {
  var list: MyList[Int] = new LinkedList

  println(list)
  println(list.isEmpty)
  println(list.add(1))
  println(list.isEmpty)
  println(list)
  println(list.add(2))
  println(list.tail)
  println(list.add(3))
  println(list.head)
  println(list.tail)
  println(list)

}

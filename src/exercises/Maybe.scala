package exercises

trait Maybe[+T]{
  def map[B](func: T => B): Maybe[B]
  def flatMap[B](func: T => Maybe[B]): Maybe[B]
  def filter(pred: T => Boolean): Maybe[T]
}

case object None extends Maybe[Nothing] {
  def map[B](func: Nothing => B): Maybe[B] = None

  def flatMap[B](func: Nothing => Maybe[B]): Maybe[B] = None

  def filter(pred: Nothing => Boolean): Maybe[Nothing] = None
}

case class One[T](value: T) extends Maybe[T] {
  def map[B](func: T => B): Maybe[B] = One(func(value))

  def flatMap[B](func: T => Maybe[B]): Maybe[B] = func(value)

  def filter(pred: T => Boolean): Maybe[T] =
    if (pred(value)) this
    else None
}

object MaybeTest extends App {
  val just3 = One(3)
  println(just3)
  println(just3.map(_*3))
  println(just3.flatMap(c => One(c%2)))
  println(just3.filter(_ % 2 == 0))
}
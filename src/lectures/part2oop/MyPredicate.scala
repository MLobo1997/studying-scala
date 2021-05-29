package lectures.part2oop

trait MyPredicate[-T] { //this is basically saying you can put any class in there
  def test(predicate: T): Boolean
}

class EvenPredicate extends MyPredicate[Int] {
  def test(predicate: Int): Boolean = predicate % 2 == 0
}

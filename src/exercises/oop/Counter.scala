package exercises.oop

class Counter (val count: Int){ //Immutable classes are extremely important in functional programming
  def inc = {
    println(s"Incrementing $count to ${count+1}")
    new Counter(count+1)
  }
  def dec = {
    println(s"Decrementing $count to ${count-1}")
    new Counter(count-1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print = println(count)
}


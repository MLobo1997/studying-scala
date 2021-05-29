package lectures.part3fp

object AnonymousFunctions extends App{

  //anonymous function or lambda. Syntactic sugar for all that Function1 crap
  val doubler: Int => Int = x => x * 2

  // multiple params in lambda
  val adder: (Int, Int) => Int =  (x, y) => x + y

  // no params
  val justDoDomething: () => Int = () => 3

  println(justDoDomething) //this is a pointer to the function
  println(justDoDomething())

  val stringToInt = { (str: String) =>
    str.toInt
  }

  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to a, b => a + b

  def specialFunction: Int => Int => Int = x => (y: Int) => x + y

  println(specialFunction(5)(9))
}

package lectures.part3fp

object WhatsAFunction extends App{
  //use functions as first class elements
  //problem: oop is more world like

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))

  //function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }
  //all scala functions are objects. Some with syntactic sugar
  //cause jvm was not design to be functional
  //by default scala supports these functions up to 24 parameters

  println(stringToIntConverter("3") + 4)


  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  val strConcat: (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  def getFunction: Int => (Int => Int) = new Function1[Int, Int => Int] {
    def apply(x: Int): Int => Int = new Function1[Int, Int] {
      def apply(v1: Int): Int = v1 + x
    }
  }

  //this is a curried function
  val crazyFunction = getFunction(10)
  println(crazyFunction(3))
  println(getFunction(10)(3)) // curried functions can be called with multiple parameter lists

  // In pure oop you need a lot of boilerplate code
  trait MyFunction[A, B] {
    def apply(element: A): B
  }
}


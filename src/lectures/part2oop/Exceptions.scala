package lectures.part2oop

import lectures.part2oop.MyList

object Exceptions extends App{
  val x: String = null
  //println(x.length) //This throws NullPointer exception

  //val aWeirdValue = throw new NullPointerException //expressions can be evaluated
  //but the value will be Nothing

  // throwable classes extend the Throwable class
  //Exceptions and Errors are the major Throwable subtypes

  // 2. how to catch exceptions

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("Not int 4 u")
    else 42

  val potentialFail = try { //Potentialfail is AnyVal. cause getInt returns Int but catch return Unit
    getInt(true)
  } catch {
    case _: RuntimeException => 43
  } finally {
    //does not influence the return type of this expression
    //use only for side effects
    println("finally")
  }

  class MyException extends Exception
  val exception = new MyException

  //throw exception

  def infiniteAdd(list: MyList[String]): MyList[String] = infiniteAdd(list.add("asdfghjkl" * 10000))
  val myList: MyList[String] = Empty
  try {
    //infiniteAdd(myList)
    val array = Array.ofDim(Int.MaxValue)
  } catch {
    case _: OutOfMemoryError => println("Out of memory")
  }

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else n * factorial(n - 1)

  try {
    factorial(100000)
  } catch {
    case _: StackOverflowError => println("Stack overflow")
  }

  class UnderflowException extends Exception
  class OverflowException extends Exception
  class MathCalculationException extends Exception
  object PocketCalculator {
    private def checkValue(r: Long) = {
      if (r > Int.MaxValue) throw new OverflowException
      else if (r < Int.MinValue) throw new UnderflowException
    }

    def add(x: Long, y: Long): Long = {
      val r = x + y
      checkValue(r)
      r
    }

    def subtract(x: Long, y: Long): Long = {
      val r = x - y
      checkValue(r)
      r
    }

    def multiply(x: Long, y: Long): Long = {
      val r = x * y
      checkValue(r)
      r
    }

    def divide(x: Long, y: Long): Long = {
      if (y == 0) throw new MathCalculationException
      val r: Long = x / y
      checkValue(r)
      r
    }
  }

  try {
    PocketCalculator.add(Int.MaxValue, 1)
    PocketCalculator.subtract(Int.MinValue, 1)
    PocketCalculator.multiply(Int.MaxValue, 2)
  } catch {
    case _: OverflowException => println("overflow")
    case _: UnderflowException => println("underflow")
  }
}
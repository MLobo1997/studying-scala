package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App {
  def aFunction(a: String, b: Int): String = a + " " + b

  def aFunction2(a: String, b: Int) = {
    a + " " + b
  }

  println(aFunction("hello", 5)) //calling a function is an expression

  def aParameterLessFunction(): Int = 42
  println(aParameterLessFunction())
  println(aParameterLessFunction) //they both return 42

  def aRepeatedFunction(aString: String, n: Int): String = { //Recursive functions require types cuz compiler cannot infer their val
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 3))
  //SCALA YOU ONLY USE RECURSION

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b
    aSmallerFunction(n, n - 1)
  }


  def greetingFunction(name:String, age:Int) = {
    s"Hi my name is $name and i am $age years old"
  }
  println(greetingFunction("Miguel", 23))

  def factorialFunc(n: Int): Int = {
    if (n == 1) n
    else n * factorialFunc(n - 1)
  }
  println(factorialFunc(4))

  def fibonnaci(n:Int): Int = {
    if (n == 1 || n == 2) 1
    else fibonnaci(n - 1) + fibonnaci(n - 2)
  }

  println(fibonnaci(6))

  def isPrime(n:Int): Boolean = {
    @tailrec
    def isPrimeAux(x:Int): Boolean = {
      if (x == 1) true
      else n % x != 0 && isPrimeAux(x-1)
      // this is only false because the && cancels out the second if negative
    }
    isPrimeAux(math.sqrt(n).toInt)
  }

  println(isPrime(10))
  println(isPrime(4231))
  println(isPrime(4233))
}
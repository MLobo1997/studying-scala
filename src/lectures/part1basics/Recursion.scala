package lectures.part1basics

import scala.annotation.tailrec
import scala.jdk.Accumulator

object Recursion extends App {
  def factorial(n: Int): Int = {
    if (n <= 1) n
    else {
      println(s"Computing factorial of $n - I first need factorial of ${n-1}")
      val result = n * factorial(n - 1)
      println(s"Computed factorial of $n")
      result
    }
  }

  factorial(10)
  //factorial(5000) //this crashes because the recursive depth is too big.

  def anotherFactorial(n: Int): BigInt = {
    @tailrec // this tells the compiler that the function must be tail recursive
    def factHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) //tail recursive -> use recursive call as the last expression
    }
    factHelper(n, 1)
  }
  /*
  anotherFactorial(5) = factHelper(5, 1)
  = factHelper(9, 10 * 1)
  = factHelper(8, 9 * 10 * 1)
  = factHelper(7, 8 * 9 * 10 * 1)
  = factHelper(6, 7 * 8 * 9 * 10 * 1)
  ...
   */
  println(anotherFactorial(5000))


  //when you need loops, use tail recursion

  /*
  1. string concatenation n times
  2. IsPrime
  3. Fibonacci
   */

  def stringRepeat(string: String, n: Int): String = {
    @tailrec
    def aux(n: Int, accumulator: String): String = {
      if (n <= 0) accumulator
      else aux(n - 1, accumulator + string)
    }
    aux(n, "")
  }

  println(stringRepeat("ola", 3))
  println(stringRepeat("ola", -1))
  println(stringRepeat("ola", 1))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def aux(x: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) isStillPrime
      else if (x == 1) isStillPrime
      else aux(x - 1, n % x != 0 && isStillPrime)
    }
    aux(math.sqrt(n).toInt, true)
  }

  println(isPrime(10))
  println(isPrime(71))
  println(isPrime(5381))
  println(isPrime(5391))

  def fibonnaci(n: Int): Int = {
    @tailrec
    def aux(x: Int, accumulator1: Int, accumulator2: Int): Int = {
      if (x == n) accumulator2
      else aux(x + 1, accumulator2, accumulator1 + accumulator2)
    }
    aux(1, 0, 1)
  }
  // 1 + 1 + 2 + 3 + 5 + 8
  println(fibonnaci(1))
  println(fibonnaci(2))
  println(fibonnaci(3))
  println(fibonnaci(4))
  println(fibonnaci(5))
  println(fibonnaci(6))
  println(fibonnaci(7))
  println(fibonnaci(8))
}


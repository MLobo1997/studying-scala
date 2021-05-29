package lectures.part4pm

import scala.annotation.tailrec
import scala.util.{Random, Try}

object PatternMatching extends App {
  //switch on steroids
  val random = new Random

  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the one"
    case 2 => "double!"
    case 3 => "third!"
    case _ => "other..." //wildcard!!
  }


  println(x)
  println(description)

  //1. it decomposes values. Specially with case classes

  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) => s"Hi my name is $n and i am $a years old"
    case _ => "I dont know!!!"
  }

  println(greeting)

  val greeting2 = bob match {
    case Person(n, a) if a < 19 => s"Hi my name is $n and i am $a years old" //this is called a guard
    case _ => "I dont know!!!"
  }

  println(greeting2)

  //cases are matched in order, just like in haskell

  //if we dont match there is an error
  Try {
    val z = x match {
      case 1 => "the one"
      case 2 => "double!"
      case 3 => 212
    } //the type of z become any cuz of inconsitent return
  }

  //case classes are very useful for PM

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Pug")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the ${someBreed} breed")
  }


  //matching everything
  val isEven = x match {
    case n if n % 2 == 0 => true //this is way exagerated.
    case _ => false
  }

  sealed abstract class Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr, brackets: Boolean = false) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def toHumanReadable(expr: Expr): String = {
    def ifSumAddBrackets(expr: Expr): Expr = expr match {
      case Sum(e1, e2, _) => Sum(e1, e2, brackets = true)
      case _ => expr
    }
    def addClosingBracketToNextOps(nextOps: List[String]) = nextOps match {
      case Nil => List(")")
      case ::(head, next) => (')' + head) :: next
    }

    @tailrec
    def aux(e: Expr, string: String, otherExpr: List[Expr], nextOps: List[String]): String = e match {
      case Number(n) => otherExpr match {
        case Nil => string + n + nextOps.headOption.getOrElse("")
        case ::(nextExpr, tail) => aux(nextExpr, string + n + nextOps.headOption.getOrElse(""), tail, nextOps.tail)
      }
      case Sum(e1, e2, needsBrackets) =>
        aux(
          e1,
          if (needsBrackets) string + '(' else string,
          e2 +: otherExpr,
          " + " +: (if(needsBrackets) addClosingBracketToNextOps(nextOps) else nextOps)
        )
      case Prod(e1, e2) => aux(ifSumAddBrackets(e1), string, ifSumAddBrackets(e2) +: otherExpr, " * " +: nextOps)
    }
    aux(expr, "", List(), List())
  }

  println(toHumanReadable(Prod(Sum(Number(1), Number(2)), Number(3))))
  println(toHumanReadable(Prod(Number(3), Sum(Number(1), Number(2)))))
  println(toHumanReadable(Sum(Prod(Number(1), Number(2)), Number(3))))
  println(toHumanReadable(Sum(Prod(Prod(Number(1), Number(2)), Number(3)), Number(5))))
}

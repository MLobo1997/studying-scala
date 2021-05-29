package lectures.part1basics

object Expressions extends App{
  val x = 1 + 2 // Expression
  println(x)

  println(2 + 3 * 4)
  // we have bitwise operators

  println(!(1 == x))

  var aVariable = 2
  aVariable += 3


  //Instructions vs Expressions
  // Instruction is something you tell the computer to do. Print this to the console, etc. Instruction is executed
  // Expression is something that computes a value. Expressions are evaluated
  val aCondition = true
  val aConditionValue = if (aCondition) 5 else 3 // if is an expression
  println(aConditionValue)
  println(if(aCondition) 5 else 3)
  println(1 + 3)

  var i = 0
  val aWhile = while (i < 10){ //while expression returns unit
    println(i)
    i+=1
  } // Never do this!!! This is for imperative programming

  //Scala forces everything to be an expression

  val aWeirdValue = (aVariable = 3) //Unit == void
  println(aWeirdValue)

  //side effects: println(), whiles, reassigning
  //There are for imperative programming! They are expressions returning unit

  //Code blocks

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye" // value of a block is the value of the last expression.  THis if
  }
  //val anotherValue = z + 1 //z is not accessible!!

  //1. difference between "hello world" and println("hello world")?
  //hello world is expression println is instruction with expression

  //
  val someValue = {
    2 < 3 //true!
  }

  val someOtherValue = {
    if (someValue) 239 else 986
    42
  } //42
}
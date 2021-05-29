package lectures.part4pm

import lectures.part2oop.{Cons, Empty, MyList}

object AllThePatterns extends App{
  // 1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "The scala"
    case true => "The truth"
    case AllThePatterns => "A singleton"
  }

  //wildcard
  val matchAnything = x match {
    case _ =>
  }

  // variable
  val matchAVariable = x match { //something matches any value! but it can be used
    case something => s"I've found $something"
  }

  //tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3)) //PMs can be nested
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) => v
  }

  //case classes
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subHead, subTail)) =>
  }

  //5 list patterns
  val aStandardList = List(1,2,3,42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => //this is called an extractor
    case List(1, _*) => //list of arbitrary length
    case 1 :: List(_) => //infix pattern
    case List(1,2,3) :+ 42 => //another infix pattern
  }

  //6
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => //explicit type specifier
    case _ =>
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _ ) => nonEmptyList  //name binding
    case Cons(1, rest @ Cons(2, _)) =>
  }

  //8 multi patterns
  val multipattern: Unit = aList match {
    case Empty | Cons(0, _) => //multi pattern! It's a or
    case _ =>
  }

  // if guards
  val s = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
    case _ =>
  }


  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "A list of strings!"
    case listOfInt: List[Int] => "A list of numbers!"
    case _ => ""
  }

  println(numbersMatch) //this is matched as a list of strings
  //because this is a JVM trick question
  //JVM was designed with backwards compatibility
  //JAVA 1 did not have generics
  //So scala also suffers from this
}

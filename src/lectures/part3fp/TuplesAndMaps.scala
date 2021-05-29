package lectures.part3fp

import java.util.NoSuchElementException

object TuplesAndMaps extends App{

  //tupples are finite ordered lists
  //val aTuple = new Tuple2[Int, String](2, "hello ")
  val aTuple = (2, "hello ")

  println(aTuple._1) // prints 2
  println(aTuple.copy(_2 = "goodbye java"))
  println(aTuple.swap) //revers

  //Maps -> Dicts
  val aMap: Map[String, Int] = Map()

  //val phonebook = Map(("Jim", 555), ("Daniel", 789))
  val phonebook = Map("Jim" -> 555, "Daniel" -> 789).withDefaultValue(-1) //-1 is returned when there is no corresponding key
  println(phonebook)

  val newPairing = "Mary" -> 678 //tuple
  println(newPairing)
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap)
  println(phonebook.view.mapValues(number => "0234-" + number).toMap)

  //conversions
  println(phonebook.toList)
  println(phonebook.toList.toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))


  //Exercises
  val test = Map("Jim" -> 123, "JIM" -> 23445, "Math" -> 1)
  println(test.map(pair => pair._1.toLowerCase -> pair._2)) //One gets erased!! Very dangerous


}

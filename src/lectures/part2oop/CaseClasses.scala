package lectures.part2oop

object CaseClasses extends App {
  // Often for likely data structures you need to implement much boiler code, like companions, equals, hashcode.
  //Case classes are faster

  case class Person(name: String, age: Int)

  //1. Class parameters are promoted to fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  println(jim)

  // 3. equals and hashcode implemented out of the box
  val jim2 = new Person("Jim",34)
  println(jim == jim2)

  // 4. Case classes have copy methods
  val jim3 = jim.copy()
  val jim4 = jim.copy(age = 45)

  // 5. CCs have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  //6. CCs are serializable -- useful for akka

  //7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

}

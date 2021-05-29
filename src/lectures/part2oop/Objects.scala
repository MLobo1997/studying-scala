package lectures.part2oop

//Objects in scala are dedicated, i.e., they are not just instances of classes
object Objects {
  // Scala does not have class level functionality ("static").
  // It has objects instead!

  object Person {
    //static/class level functionality
    val N_EYES = 2 //global variable. This is static

    def canFly: Boolean = false //static method

    //factory method
    def apply(mother: Person, father: Person): Person = new Person( "Bobbie")
  }

  class Person (val name: String){ //you can define class and object simultaneously
    //instance level functionality
  }
  //Defining both is called the Companions design pattern
  //Companions can access each other's private members

  def main(args: Array[String]): Unit = {
    println(Person.N_EYES)
    println(Person canFly)

    //Scala object = singleton instance
    val mary = Person
    val john = Person
    println(mary == john) //this is true! They have the same reference

    val mary1 = new Person("Mary")
    val john1 = new Person("John")
    println(mary1 == john1) //now this is false!

    val bobbie = Person(mary1, john1) //this is the apply method!!
  }

  //Scala application = scala object with
  // def main(args: Array[String]): Unit
}

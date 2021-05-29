package lectures.part2oop

object AnonymousClasses extends App{
  trait Animal {
    def eat: Unit
  }

  /*
  class AnonynousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("aahhahaahh")
  }
  */ //equivalent to:

  //anonymous class is when you define a class on the go
  val funnyAnimal: Animal = new Animal {
    def eat: Unit = println("Ahahahahah")
  }

  println(funnyAnimal.getClass) //anonymous class

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi my name is $name")
  }


  // basically you can override fields/methods on the spot
  val jim = new Person("Jim") {
    override def sayHi: Unit = println("Hi my name is Jim")
  }
}

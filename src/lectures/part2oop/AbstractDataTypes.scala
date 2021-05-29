package lectures.part2oop

object AbstractDataTypes extends App{
  // abstract
  abstract class Animal {
    val creatureType: String //abstact value cuz it is not define. But it can be!
    def eat: Unit //same
  }

  //val animal = new Animal can'' be instantiated

  class Dog extends Animal {
    val creatureType: String = "Canine"
    def eat: Unit = println("Crunch crunch")
  }

  // trait -- ultimate abstacts in scala
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded { //you can add as many traits as you want
    val creatureType: String = "croc"

    override def eat: Unit = println("nom nomnom")
    def eat(animal: Animal): Unit = println(s"I'' a croc eating a ${animal.creatureType}")

  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)
}
// abstact classes can have default values and traits can too
// 1-traits do not have constructors
// 2-multiple traits may be inherited. Abstracts only one
// 3 - traits are meant to describe behaviour while abstract classes are meant to describe things

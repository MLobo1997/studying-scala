package lectures.part2oop

object Inheritance extends App {

  //single class inheritance -- you can only inherit one class at a time
  /*final*/ sealed class Animal { //class cannot be extended with final. Seal allows to extend in same file but not outsied
    /*protected final*/  def eat = println("nomnom") //use final to not allow override
    val creatureType = "wild"
  }

  class Cat extends Animal {
    def crunch = eat
  }

  val cat = new Cat
  //cat.eat //can't!!
  cat.crunch

  //constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age) //Since here you are already defining the constructors you immediately need to define the super
  class Adult1(name: String, age: Int, idCard: String) extends Person(name)

  class Dog(override val creatureType: String) extends Animal {
    override def eat: Unit = {
      super.eat
      println("crunch crunch")
    } //you can override the protction
    //override val creatureType: String = "domestic" //you can do this
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  //type substitution (polymorphism
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overriding vs overloading

  // preventing overrides
  // 1. use `final` on methods
  // 2. use final on class
  // 3. seal the class -- allows extension/overriding only in same file


}

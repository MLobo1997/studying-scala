package lectures.part2oop

object Generics {//extends App{
  //also works for traits
  class MyList[+A] {
    //def add(element: A): MyList[A] = ??? //This don't work cuz The compiler is basically asking: If you have a list of animals with a list of cats, can you add dogs?
    def add[B >: A](element: B): MyList[B] = ???
    /* B is supertype of A // here we are saying that yes, you can add mixed types to list of animals
    A = Cat
    B = Animal
     */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  object MyList { //cannot have generics
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  //variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //QUestion: If a cat extends animal, does a list cat also extend a list of animal??
  //Answers:
  //1-yes List[Cat] extends List[Animal] -> this is Covariance
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // What if I did `animalList.add(new Dog)`?? Should this work? its hard question

  //2 - NO -> Invariance
  class InvariantList[A]
  //val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] //this does not work!!
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  //3. The opposite!! -> Contravariance
  //class ContraVariantList[-A]
  //val contravariantList: ContraVariantList[Cat] = new ContraVariantList[Animal] //THis works.. But its wierd
  //An example of a contravariant generics is:
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types -- Class Cage only accepts subtypes of animal
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)
  class Car
  //val newCage = new Cage(new Car) //this will not work!!
}

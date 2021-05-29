package lectures.part2oop

object MethodNotations extends App{
  class Person(val name: String, favoriteMovie: String, val age: Int) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging with ${person.name}"
    def unary_! : String = s"$name, wtf"
    def isAlive: Boolean = true
    def apply(): String = s"Hi my name is $name and I like $favoriteMovie"

    def +(nameContinuation: String): Person = new Person(name + nameContinuation, favoriteMovie, age)
    def +(age: Int): Person = new Person(name, favoriteMovie, this.age + age)
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def learns(what: String): String = s"$name leans $what"
    def learnsScala: String = this learns "Scala"
    def apply(nr_of_times: Int): String = s"$name watched it $nr_of_times"
  }

  val mary = new Person("Mary", "Inception", 15)
  println(mary.likes("Inception"))
  println(mary likes "Inception") // infix notation -> only works with methods w/ 1 argument

  val tom = new Person("Tom", "Fight Club", 18)
  println(tom.+(mary))
  println(tom + mary)

  println(1.+(2))

  //All operators are methods

  // unary operators - only works with - + ~ !
  val x = -1
  val y = 1.unary_-

  println(!mary)
  println(mary.unary_!)

  //postfix notation -- functions without parameters
  println(mary.isAlive)
  println(mary isAlive)

  //apply
  println(mary.apply())
  println(mary()) //same effect!

  println((+mary).age)
  println((mary+3).age)
  println(mary learnsScala)

  println(mary(3))
}

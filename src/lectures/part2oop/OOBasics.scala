package lectures.part2oop

import exercises.oop.Counter

object OOBasics extends App{
  val person = new Person("João", 23)
  println(person)
  //println(person.name) //does not work
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet()
}

class Person(name:String, val age: Int = 0) { //constructor
  //If use val it is public, if not it is private
  val x = 2 //you are declaring a field

  println("blabla")

  //method
  def greet(name: String): Unit = println(s"${this.name} says: Hi $name")

  //overloading -- supplying same method w/ different signature
  def greet(): Unit = println(s"Hi, I am $name")

  //multiple constructors -- can only call other contstrucotres
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")

  val counter = new Counter(5)
  counter.inc
  counter.inc.inc.inc
  counter.inc(10).print
}

// class parameters are not fields! To become one, you must add `val`


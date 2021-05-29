package lectures.part1basics

//Calling by name vs by value
object CBNvsCBV extends App {
  def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  def calledByName(x: => Long): Unit = { //This means you pass a function that returns Long
    println("by value: " + x)
    println("by value: " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime()) //this one will return different values!
  //System.nanoTime() gets evaluated everytime it is used!!!
  //This is lazy evaluation

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  //printFirst(infinite(), 34) //runs forever
  printFirst(34, infinite()) //infinite is never used
}

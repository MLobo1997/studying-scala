package lectures.part3fp

import scala.util.Random

object Sequences extends App{
  //Properties that have a well defined order and can be index
  val aSequence = Seq(1,3,2,5)
  println(aSequence) //It is a list!
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5,6,7))
  println(aSequence.sorted)

  //Ranges
  //val aRange: Seq[Int] = 1 to 10 //inclusive 10!!
  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)

  (1 to 10).foreach(_ => println("Hello"))

  //Lists
  val aList = List(1,2,3,4)
  val prependend = 42 :: aList
  val both = 42 +: aList :+ 82

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-"))


  //Arrays -are mutable!.  Can have predefined lenght
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements)
  threeElements.foreach(println)

  //mutation
  numbers(2) = 0 //syntatic sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  val numberSeq: Seq[Int] = numbers //Implicit conversions
  println(numberSeq)

  //Vectors - immutable indexed and very efficinet

  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  //vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      _ <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }
    times.sum.toDouble / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector)) //3 orders of magnitude faster.
}

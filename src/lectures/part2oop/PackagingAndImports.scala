package lectures.part2oop

import exercises.oop.Writer
import playground.{PrinceCharming, Cinderella => Princess}

import java.sql
import java.util.Date
object PackagingAndImports extends App {
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  //val princess = new playground.Cinderella
  val princess = new Princess


  // package object
  // package objects can only be one per package.
  //rarely used. Good for global variables and shit
  sayHello
  println(SPEED_OF_LIGHT)

  val prince = new PrinceCharming

  val d = new Date //scala assumes it is the 1st import
  //val sqlDate = new sql.Date()


}

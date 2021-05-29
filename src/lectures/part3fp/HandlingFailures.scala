package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailures extends App {
  //Try, Success and Failure. To avoid using exception cuz with all the catches it might blow in your face
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("buuuh"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod: String = throw new RuntimeException("NO STRING")

  val potentialFailure = Try(unsafeMethod) //try catches the exception!!!!
  println(potentialFailure)

  //syntax sugar

  val anotherPotentialFailure = Try {
    println(1 + 2)
    new RuntimeException
  }

  println(potentialFailure.isSuccess)
  println(anotherPotentialFailure.isSuccess)

  //orElse
  def backupMethod = "A valid result"
  val fallbackTry = Try(unsafeMethod).orElse(Try(backupMethod))
  println(fallbackTry)
  println(fallbackTry.isSuccess)

  // IF you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("Valid!")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  //map, flatmap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Try(x * 2)))
  println(aSuccess.filter(_ > 10)) //Turns success into failure!

  val hostName = "localhost"
  val port = "8080"
  def renderHTML(page:String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "Some html"
      else throw new RuntimeException("No html 4 you")
    }
  }

  object HttpService {
    val random = new Random(System.nanoTime())
    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }
  }

  println("Exercise:")
  Try(HttpService.getConnection(hostName, port))
    .flatMap(conn => Try(conn.get("www.lol.com")))
    .foreach(renderHTML)
  println("-------")


  println("Exercise:")
  for {
    conn <- Try(HttpService.getConnection(hostName, port))
    html <- Try(conn.get("www.lol.com"))
  } yield renderHTML(html)
  println("-------")

  //This avoids an endless amount of try-catches.
}


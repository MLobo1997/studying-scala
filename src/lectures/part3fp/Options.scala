package lectures.part3fp

import scala.util.Random

object Options extends App{
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  //options were invented to deal with unsafe apis
  def unsafeMethod(): String = null
  //val result = Some(unsafeMethod()) //WRONG!!
  val result = Option(unsafeMethod()) //the whole point of options is that we should never use null checks ourselves
  println(result)

  //chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainedResult)

  //designing better apis
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("Ok")
  val betterChainedMethod = betterUnsafeMethod() orElse betterBackupMethod()


  //functions
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) //Unsafe this returns null. Do not use

  //map, flatmap, filter
  println(myFirstOption.map(_*2))
  println(myFirstOption.filter(_ > 10)) //turns into None
  println(myFirstOption.flatMap(x => Option(2*x)))


  val config: Map[String, String] = Map(
    "host" -> "localhost",
    "port" -> "80",
  )

  class Connection {
    def connected = "Connected"
  }

  object Connection {
    val random = new Random()
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }


  for {
    _ <- 1 to 100
  } yield {
    val conn: Option[Connection] = Connection(config("host"), config("port"))
    if (conn.isDefined) {
      println(conn.get.connected)
    } else {
      println("Not connected")
    }
  }

  def getHost(config: Map[String, String]): String =
    config.getOrElse("host", getHost(config))

  def getPort(config: Map[String, String]): String =
    config.getOrElse("port", getPort(config))

  def establishConnection(host: String, port: String): Connection =
    Connection(host, port).getOrElse(establishConnection(host, port))


  for {
    _ <- 1 to 10
  } yield {
    println(establishConnection(getHost(config), getPort(config)).connected)
  }

  println("Connection:")
  for {
    hostVal <- config.get("host")
    portVal <- config.get("port")
    conn <- Connection(hostVal, portVal)
  } yield {
    println(conn.connected)
  }

  //equivalent to
  println("Connection:")
  config.get("host").flatMap(host => config.get("port").flatMap(port => Connection(host, port))).map(_.connected).foreach(println)
}

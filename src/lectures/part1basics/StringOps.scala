package lectures.part1basics


object StringOps extends App{
  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())

  val aNumberString = "2"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z') //this means you are prepending +: a appending :+
  println(str.reverse)
  println(str.take(2))

  //String interpolators

  //S-interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello $name and $age"
  val greeting2 = s"Hello $name and ${age+1}"

  //F-interpolator
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f"
  println(myth)

  //raw interpolator
  println(raw"THis is a \n newline") //.This is printed literally
  val escaped = "THis is a \n newline"
  println(raw"$escaped")
}

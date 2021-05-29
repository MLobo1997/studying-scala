package lectures.part1basics

object ValuesVariablesTypes extends App{
  //val x: Int = 42
  val x = 42 // types are optional, compiler infers. It infers to int
  println(x)

  //x = 2 vals cannot be reassigned they are immutable

  val aString: String = "hello"; val anotherString = "goodbye"//semi colons are allowed but not necessary

  val aBoolean : Boolean = false

  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4212 // 2 bytes instead of 4
  val aLong: Long = 42122144114212421L // 8 bytes instead of 4
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  //variables
  var aVariable: Int = 4
  aVariable = 5 //variables are not immutable
}

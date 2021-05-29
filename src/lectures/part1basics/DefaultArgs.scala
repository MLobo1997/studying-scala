package lectures.part1basics

object DefaultArgs extends App{
  def trFact(n: Int, acc: Int = 1): Int = { //accumulator is polluting the signature
    if (n <= 1) acc
    else trFact(n - 1, n * acc)
  }

  val fact10 = trFact(10) //however I can still pass the acc screw everything

  def savePicture(format: String = "jpg", width: Int, height: Int): Unit = println("Saving picture")
  //savePicture(800, 600) //you cant do this cuz it will thing width is format

  def savePicture1(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("Saving picture")
  //savePicture(800) Same problem!

  savePicture(format = "excel", width = 1500, height = 10)
}

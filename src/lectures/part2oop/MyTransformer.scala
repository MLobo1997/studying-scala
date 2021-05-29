package lectures.part2oop

trait MyTransformer[-A, B] {
  def transform(obj: A): B
}

class StringToIntTransformer extends MyTransformer[String, Int] {
  def transform(obj: String): Int = obj.toInt
}
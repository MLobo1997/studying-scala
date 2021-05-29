package exercises.oop

object OOpExercises extends App {
  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great book", 1861, author)
}

package exercises.oop

import java.time.Year

class Novel(val name: String, val yearOfRelease: Int, val author: Writer){
  def authorAge(): Int = Year.now().getValue - author.year

  def isWrittenBy(author: Writer): Boolean = author.equals(this.author)

  def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}

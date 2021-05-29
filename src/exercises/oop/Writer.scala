package exercises.oop

class Writer(val firstName: String, val surName: String, val year: Int) {
  def fullName: String = s"$firstName $surName"

  def canEqual(other: Any): Boolean = other.isInstanceOf[Writer]

  override def equals(other: Any): Boolean = other match {
    case that: Writer =>
      (that canEqual this) &&
        firstName == that.firstName &&
        surName == that.surName &&
        year == that.year
    case _ => false
  }
}

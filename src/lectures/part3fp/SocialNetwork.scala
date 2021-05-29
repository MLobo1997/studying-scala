package lectures.part3fp

import scala.annotation.tailrec

case class SocialNetwork(private val persons: Map[String, Set[String]] = Map()) {
  def addPerson(person: String): SocialNetwork =
    SocialNetwork(persons + (person -> Set()))

  def removePerson(person: String): SocialNetwork =
    SocialNetwork(persons - person)

  def friend(person1: String, person2: String): SocialNetwork = {
    def addFriend(p1: String, p2: String): (String, Set[String]) = (p1, persons(p1) + p2)
    SocialNetwork(persons + addFriend(person1, person2) + addFriend(person2, person1))
  }

  def unfriend(person1: String, person2: String): SocialNetwork = {
    def removeFriend(p1: String, p2: String): (String, Set[String]) = (p1, persons(p1) - p2)
    SocialNetwork(persons + removeFriend(person1, person2) + removeFriend(person2, person1))
  }

  def remove(person: String): SocialNetwork =
    SocialNetwork(
      persons - person ++ persons.filter(pair => pair._2.contains(person)).map(pair => pair._1 -> (pair._2 - person))
    )


  def nrOfFriends(person: String): Int = persons(person).size

  def personWithMostFriends: String =
    persons.reduce(
      (pair1: (String, Set[String]), pair2: (String, Set[String])) =>
        if (pair1._2.size >= pair2._2.size) pair1
        else pair2
    )._1

  def howManyWithoutFriends: Int =
    persons.count((pair: (String, Set[String])) => pair._2.isEmpty)

  //def existsConnectionBetween(person1: String, person2: String): Boolean = {
    //def aux(p1: String, p2: String): Boolean =
      //if (persons(p1).contains(p2)) true
      //else if (persons(p1).isEmpty) false
      //else persons(p1).foldLeft(false)((flag: Boolean, p: String) => if (flag) flag else aux(p, p2))
    //aux(person1, person2)
  //}
  def existsConnectionBetween(person1: String, person2: String): Boolean = {
      @tailrec
      def aux(checkedPeople: Set[String], peopleToCheck: Seq[String]): Boolean =
        if (peopleToCheck.isEmpty) false
        else if (persons(peopleToCheck.head).contains(person2)) true
        else {
          val newCheckedPeople = checkedPeople + peopleToCheck.head
          val newPeopleToCheck = peopleToCheck.tail :++ persons(peopleToCheck.head).diff(newCheckedPeople)
          aux(newCheckedPeople, newPeopleToCheck)
        }

      aux(Set(), person1 +: persons(person1).toSeq)
    }
}

object TestSocialN extends App{
  val socialNetwork: SocialNetwork = SocialNetwork()
  println(socialNetwork)
  val socialNetwork2 = socialNetwork.addPerson("john")
  println(socialNetwork2)
  val socialNetwork3 = socialNetwork2.removePerson("john")
  println(socialNetwork3)

  val withPeople = SocialNetwork(Map("John" -> Set(), "Joan" -> Set(), "Francis" -> Set()))
  println(withPeople)
  val withFriends = withPeople.friend("John", "Joan").friend("John", "Francis")
  println(withFriends)
  println(withFriends.unfriend("John", "Joan"))
  println(withFriends.nrOfFriends("John"))
  println(withFriends.personWithMostFriends)
  println(withFriends.unfriend("John", "Joan").howManyWithoutFriends)

  println(withFriends.existsConnectionBetween("Joan", "Francis"))
  val complex = withFriends
    .addPerson("Mary")
    .addPerson("Granny")
    .friend("Mary", "Granny")

  println(complex.existsConnectionBetween("John", "Mary"))
  println(complex.remove("John"))
}

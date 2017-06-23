package playground.elasticsearch.data

import java.util.UUID

import io.codearte.jfairy.Fairy

import scala.util.Random

object DataGenerator {

  private val fairy = Fairy.create()

  def randomCompanies(size: Int = 100): List[Company] = (0 until size).map(_ => randomCompany).toList

  private def randomCompany: Company = {
    val randomPerson = fairy.person()
    val randomAddress = randomPerson.getAddress

    Company(fairy.company().getName, fairy.textProducer().paragraph(2), randomPerson.getTelephoneNumber,
      Address(randomAddress.getCity, randomAddress.getStreet),
      Person(if (randomPerson.isMale) "Mr" else "Mrs", randomPerson.getFullName, randomPerson.getAge),
      randomTags.toList)
  }

  def randomUsers(size: Int = 100): List[User] = {
    def randomUserCompany: UserCompany = UserCompany(UUID.randomUUID().toString, fairy.company().getName)

    def randomUser(userCompany: UserCompany): User = {
      val randomPerson = fairy.person()
      User(randomPerson.getFirstName, randomPerson.getLastName, randomPerson.getAge, userCompany)
    }

    val userCompanies = (0 until (size / 4)).map(_ => randomUserCompany)
    (0 until size).map(i => randomUser(userCompanies(i / 4))).toList
  }


  private def randomTags = (0 to Random.nextInt(4)).map(_ => Tags(Random.nextInt(Tags.length))).toSet

  private val Tags = Seq("car", "job", "house", "london", "service", "computer", "it", "school", "uk", "food", "shop",
    "fashion", "clothes", "health", "money", "bank", "finance", "music", "hotel", "hostel", "beauty", "entertainment")
}

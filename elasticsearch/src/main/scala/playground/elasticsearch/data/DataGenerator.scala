package playground.elasticsearch.data

import io.codearte.jfairy.Fairy

import scala.util.Random

object DataGenerator {

  private val fairy = Fairy.create()

  def randomCompanies: List[Company] = (0 to 100).map(_ => randomCompany).toList

  private def randomCompany: Company = {
    val randomPerson = fairy.person()
    val randomAddress = randomPerson.getAddress

    Company(fairy.company().getName, fairy.textProducer().paragraph(2), randomPerson.getTelephoneNumber,
      Address(randomAddress.getCity, randomAddress.getStreet),
      Person(if (randomPerson.isMale) "Mr" else "Mrs", randomPerson.getFullName, randomPerson.getAge),
      randomTags.toList)
  }

  private def randomTags = (0 to Random.nextInt(4)).map(_ => Tags(Random.nextInt(Tags.length))).toSet

  private val Tags = Seq("car", "job", "house", "london", "service", "computer", "it", "school", "uk", "food", "shop",
    "fashion", "clothes", "health", "money", "bank", "finance", "music", "hotel", "hostel", "beauty", "entertainment")
}

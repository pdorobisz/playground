package playground.elasticsearch

import com.sksamuel.elastic4s.ElasticDsl._
import playground.elasticsearch.data.DataGenerator
import com.sksamuel.elastic4s.jackson.ElasticJackson.Implicits._

import scala.concurrent.Future

object Setup extends App with ElasticSearchConfig {

  import scala.concurrent.ExecutionContext.Implicits.global

  initOrganisations()
  insertOrganisationsData()

  initUsers()
  insertUsersData()

  private def initOrganisations() = {
    removeOldIndex(OrganisationsIndex)

    println(s"creating $OrganisationsIndex index...")
    esClient.execute {
      createIndex(OrganisationsIndex).mappings(
        mapping(CompanyType) as(
          keywordField("id"),
          textField("name"),
          textField("phone"),
          objectField("address").fields(
            textField("town"),
            textField("street")
          ),
          textField("description"),
          objectField("ceo").fields(
            textField("name"),
            textField("title"),
            intField("age")
          ),
          keywordField("tags")
        )
      )
    }.await
    println(s"index $OrganisationsIndex created")
  }

  private def initUsers() = {
    removeOldIndex(UsersIndex)

    println(s"creating $UsersIndex index...")
    esClient.execute {
      createIndex(UsersIndex).mappings(
        mapping(PersonType) as(
          keywordField("id"),
          objectField("company").fields(
            keywordField("id")
          )
        )
      )
    }.await
    println(s"index $UsersIndex created")
  }

  private def removeOldIndex(index: String) = {
    esClient.execute(indexExists(index)).flatMap(exists =>
      if (exists.isExists) {
        println(s"delete old $index index")
        esClient.execute(deleteIndex(index))
      } else {
        println(s"index $index not found")
        Future.successful(exists)
      }).await
  }

  private def insertOrganisationsData() = {
    val data = DataGenerator.randomCompanies(10000).map(indexInto(OrganisationsIndex, CompanyType).doc(_))

    esClient.execute {
      bulk(data: _*)
    }.await
  }

  private def insertUsersData() = {
    val data = DataGenerator.randomUsers(10000).map(indexInto(UsersIndex, PersonType).doc(_))

    esClient.execute {
      bulk(data: _*)
    }.await
  }
}

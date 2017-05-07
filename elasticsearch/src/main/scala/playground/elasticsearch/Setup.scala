package playground.elasticsearch

import com.sksamuel.elastic4s.ElasticDsl._
import playground.elasticsearch.data.DataGenerator
import com.sksamuel.elastic4s.jackson.ElasticJackson.Implicits._

import scala.concurrent.Future

object Setup extends App with ElasticSearchConfig {

  import scala.concurrent.ExecutionContext.Implicits.global

  initIndex()
  insertData()

  private def initIndex() = {
    esClient.execute(indexExists(OrganisationsIndex)).flatMap(exists =>
      if (exists.isExists) {
        println(s"delete old $OrganisationsIndex index")
        esClient.execute(deleteIndex(OrganisationsIndex))
      } else {
        println(s"index $OrganisationsIndex not found")
        Future.successful(exists)
      }).await

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

  private def insertData() = {
    val data = DataGenerator.randomCompanies.map(indexInto(OrganisationsIndex, CompanyType).doc(_))

    esClient.execute {
      bulk(data: _*)
    }.await
  }
}

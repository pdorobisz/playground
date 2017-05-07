package playground.elasticsearch

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.jackson.ElasticJackson.Implicits._
import com.sksamuel.elastic4s.{ElasticsearchClientUri, TcpClient}
import playground.elasticsearch.data.{Address, Company, Person}

import scala.concurrent.Future

object Setup extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  private val OrganisationsIndex = "organisations"
  private val CompanyType = "company"
  private val client = TcpClient.transport(ElasticsearchClientUri("localhost", 9300))

  initIndex()
  insertData()

  private def initIndex() = {
    client.execute(indexExists(OrganisationsIndex)).flatMap(exists =>
      if (exists.isExists) {
        println(s"delete old $OrganisationsIndex index")
        client.execute(deleteIndex(OrganisationsIndex))
      } else {
        println(s"index $OrganisationsIndex not found")
        Future.successful(exists)
      }).await

    println(s"creating $OrganisationsIndex index...")
    client.execute {
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
    client.execute {
      indexInto(OrganisationsIndex, CompanyType).doc(
        Company("Abc Inc.", "We create everything you need for your car", "123 123 123",
          Address("London", "Empire Rd"), Person("Mr", "John Smith", 39), List("cars", "london", "repair"))
      )
    }.await
  }
}

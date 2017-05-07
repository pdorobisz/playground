import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.{ElasticsearchClientUri, TcpClient}

import scala.concurrent.Future

object Setup extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  private val OrganisationsIndex = "organisations"
  private val client = TcpClient.transport(ElasticsearchClientUri("localhost", 9300))

  client.execute(indexExists(OrganisationsIndex)).flatMap(exists =>
    if (exists.isExists) {
      println(s"delete old $OrganisationsIndex index")
      client.execute(deleteIndex(OrganisationsIndex))
    } else {
      println(s"index $OrganisationsIndex not found")
      Future.successful(exists)
    }).await

  println(s"creating $OrganisationsIndex index...")
  val createIndexResponse = client.execute {
    createIndex(OrganisationsIndex).mappings(
      mapping("company") as(
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
  println("done")
}

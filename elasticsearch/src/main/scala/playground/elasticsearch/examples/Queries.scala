package playground.elasticsearch.examples

import com.sksamuel.elastic4s.http.ElasticDsl._
import com.sksamuel.elastic4s.jackson.ElasticJackson.Implicits._
import com.sksamuel.elastic4s.searches.queries.QueryDefinition
import playground.elasticsearch.examples.EsConfig.esClient

import scala.concurrent.ExecutionContext.Implicits.global


object Queries extends App {

  def executeQuery(q: QueryDefinition) =
    esClient.execute(search("users1").query(q)).await.map(_.result.hits.hits.toList.map(_.to[User])) match {
      case Left(value) => println(value)
      case Right(value) => println(value.mkString("\n"))
    }

  val s = "John Smith"
  val q = boolQuery()
    .must(matchQuery("firstName", s).minimumShouldMatch("2"))


  executeQuery(q)
  System.exit(0)
}

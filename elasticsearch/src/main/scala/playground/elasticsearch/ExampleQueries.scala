package playground.elasticsearch

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.jackson.ElasticJackson.Implicits._
import playground.elasticsearch.data.Company

object ExampleQueries extends App with ElasticSearchConfig {

  val result = esClient.execute(
    search(OrganisationsIndex / CompanyType).query("london")
  ).await

  val text = result.to[Company].mkString("\n")
  println(text)

}

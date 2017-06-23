package playground.elasticsearch

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.jackson.ElasticJackson.Implicits._
import playground.elasticsearch.data.{Company, User}

object ExampleQueries extends App with ElasticSearchConfig {

  //  example1()
  example2()

  private def example1() = {
    val result = esClient.execute(
      search(OrganisationsIndex / CompanyType).query(termQuery("description", "hotel"))
    ).await

    val text = result.to[Company].mkString("\n")
    println(text)
  }

  private def example2() = {
    val result = esClient.execute(
      search(UsersIndex / PersonType).query(termQuery("company.id", "2536be75-bdb1-4a07-992e-eff8d37ee726"))
    ).await

    val text = result.to[User].mkString("\n")
    println(text)
  }

}

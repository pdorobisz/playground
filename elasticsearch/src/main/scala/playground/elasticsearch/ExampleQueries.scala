package playground.elasticsearch

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.jackson.ElasticJackson.Implicits._
import com.sksamuel.elastic4s.script.ScriptDefinition
import playground.elasticsearch.data.{Category, Company, User}

object ExampleQueries extends App with ElasticSearchConfig {

  //  example1()
  //  example2()
  //  example3()
  example4()

  private def example1() = {
    val result = esClient.execute(
      search(OrganisationsIndex / CompanyType).query(termQuery("description", "hotel"))
    ).await

    val text = result.to[Company].mkString("\n")
    println(text)
  }

  private def example2() = {
    val result = esClient.execute(
      search(UsersIndex / PersonType).query(termQuery("company.id", "4d3c98ed-e994-4ed3-9cae-d243dc087bf6"))
    ).await

    val text = result.to[User].mkString("\n")
    println(text)
  }

  private def example3() = {
    esClient.execute(
      updateIn(UsersIndex / PersonType)
        .query(termQuery("company.id", "4d3c98ed-e994-4ed3-9cae-d243dc087bf6"))
        .script(ScriptDefinition("ctx._source.company.name=\"UPDATED\""))
    ).await
  }

  private def example4() = {
    val result = esClient.execute(
      search(CategoriesIndex / CategoryType).query(termQuery("path", "/for-sale/cars"))
    ).await

    val text = result.to[Category].mkString("\n")
    println(text)
  }

}

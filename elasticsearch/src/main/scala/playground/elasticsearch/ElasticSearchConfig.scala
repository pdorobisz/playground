package playground.elasticsearch

import com.sksamuel.elastic4s.{ElasticsearchClientUri, TcpClient}

trait ElasticSearchConfig {
  val OrganisationsIndex = "organisations"
  val UsersIndex = "users"

  val CompanyType = "company"
  val PersonType = "person"

  val esClient = TcpClient.transport(ElasticsearchClientUri("localhost", 9300))
}

package playground.elasticsearch

import com.sksamuel.elastic4s.{ElasticsearchClientUri, TcpClient}

trait ElasticSearchConfig {
  val OrganisationsIndex = "organisations"
  val CompanyType = "company"
  val esClient = TcpClient.transport(ElasticsearchClientUri("localhost", 9300))
}

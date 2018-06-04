package playground.elasticsearch

import com.sksamuel.elastic4s.{ElasticsearchClientUri, TcpClient}
import org.elasticsearch.common.settings.Settings

trait ElasticSearchConfig {
  val CategoriesIndex = "categories"
  val OrganisationsIndex = "organisations"
  val UsersIndex = "users"

  val CompanyType = "company"
  val PersonType = "person"
  val CategoryType = "category"

  val esClient = TcpClient.transport(Settings.builder()
    .put("cluster.name", "searchservice").build(),
    ElasticsearchClientUri("localhost", 9300))
}

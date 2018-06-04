package playground.elasticsearch.examples

import com.sksamuel.elastic4s.ElasticsearchClientUri
import com.sksamuel.elastic4s.http.HttpClient

object EsConfig {
  val esClient = HttpClient(ElasticsearchClientUri("localhost", 9200))
}

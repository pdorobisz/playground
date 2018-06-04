package playground.elasticsearch.examples

import com.sksamuel.elastic4s.analyzers.{CustomAnalyzerDefinition, EdgeNGramTokenizer, LowercaseTokenFilter}
import com.sksamuel.elastic4s.http.ElasticDsl._

object Mappings {

  val usersMappings = createIndex("users1")
    .analysis(
      CustomAnalyzerDefinition(
        name = "postcode_analyzer",
        filters = Seq(LowercaseTokenFilter),
        tokenizer = EdgeNGramTokenizer("edge_ngram_postcode_tokenizer", minGram = 1, maxGram = 10))
    )
    .mappings(
      mapping("data").as(
        textField("firstName"),
        textField("lastName"),
        intField("age"),
        keywordField("position"),
        textField("postcode").analyzer("postcode_analyzer").searchAnalyzer("standard"),
        textField("description").analyzer("standard").fields(
          textField("english").analyzer("english"))
      )
    )

  val commentsMappings = createIndex("comments")
    .shards(1)
    .mappings(
      mapping("data").as(
        textField("title"),
        textField("content")
      )
    )
}

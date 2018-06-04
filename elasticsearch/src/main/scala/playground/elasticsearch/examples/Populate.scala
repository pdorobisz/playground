package playground.elasticsearch.examples

import com.sksamuel.elastic4s.http.ElasticDsl._
import EsConfig.esClient
import com.sksamuel.elastic4s.indexes.CreateIndexDefinition

import scala.concurrent.ExecutionContext.Implicits.global
import com.sksamuel.elastic4s.jackson.ElasticJackson.Implicits._

object Populate extends App {

  def insert(u: User) = esClient.execute(indexInto("users1", "data").doc(u)).await

  def insert(c: Comment) = esClient.execute(indexInto("comments", "data").doc(c)).await

  def createEsIndex(definition: CreateIndexDefinition) = esClient.execute(definition).await match {
    case Left(value) => println(s"failed to create index: ${definition.name}, $value")
    case Right(_) => println(s"created index: ${definition.name}")
  }

  createEsIndex(Mappings.usersMappings)
  insert(User("John", "Smith", 40, "WC12ES", "JUNIOR", "we are the champions my friends"))
  insert(User("John", "Reynolds", 42, "WC13XY", "JUNIOR", "hard working man with lots of friends"))
  insert(User("Mark", "Jones", 51, "WC24ABC", "SENIOR", "World cup champion"))
  insert(User("Paul", "Marks", 51, "WC25ABC", "SENIOR", "I am the real champion"))
  insert(User("James", "Morgan", 52, "EC102QW", "MANAGER", "I was born in 1966"))
  insert(User("Peter", "Morgan", 57, "EC102QW", "MANAGER", "I was born in 1961"))

  createEsIndex(Mappings.commentsMappings)
  insert(Comment("", "john"))
  insert(Comment("", "john john"))
  insert(Comment("", "john john john"))
  insert(Comment("", "john john john cat"))
  insert(Comment("", "john john john cat cat"))
  insert(Comment("", "john cat cat cat cat cat cat cat cat cat cat"))
  insert(Comment("", "john cat cat cat cat cat cat cat cat cat cat cat cat cat cat"))

  System.exit(0)
}

case class User(firstName: String, lastName: String, age: Int, postcode: String, position: String, description: String)

case class Comment(title: String, content: String)
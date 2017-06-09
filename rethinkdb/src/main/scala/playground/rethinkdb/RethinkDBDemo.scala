package playground.rethinkdb

import com.rethinkdb.RethinkDB

import scala.util.Try
import collection.JavaConverters._

object RethinkDBDemo extends App {

  private val DatabaseName = "mydb"
  private val TableName = "authors"

  val r = RethinkDB.r
  val conn = r.connection().db(DatabaseName).hostname("localhost").port(28015).connect()

  // Drop old data and create new database
  Try(r.dbDrop(DatabaseName).run(conn))
  r.dbCreate(DatabaseName).run(conn)
  r.tableCreate(TableName).run(conn)

  // Insert some data
  val result = r.table(TableName).insert(r.array(
    r.hashMap("name", "William Adama")
      .`with`("tv_show", "Battlestar Galactica")
      .`with`("posts", r.array(
        r.hashMap("title", "Decommissioning speech").`with`("content", "The Cylon War is long over..."),
        r.hashMap("title", "We are at war").`with`("content", "Moments ago, this ship received..."),
        r.hashMap("title", "The new Earth").`with`("content", "The discoveries of the past few days...")
      )),
    r.hashMap("name", "Laura Roslin")
      .`with`("tv_show", "Battlestar Galactica")
      .`with`("posts", r.array(
        r.hashMap("title", "The oath of office").`with`("content", "I, Laura Roslin, ..."),
        r.hashMap("title", "They look like us").`with`("content", "The Cylons have the ability...")
      )),
    r.hashMap("name", "Jean-Luc Picard")
      .`with`("tv_show", "Star Trek TNG")
      .`with`("posts", r.array(
        r.hashMap("title", "Civil rights")
          .`with`("content", "There are some words I've known since...")
      ))
  )).run(conn).asInstanceOf[java.util.Map[String, Object]].asScala
  println(result)

  // Start stream of changes
  private val cursor = r.table("authors").changes().run(conn).asInstanceOf[com.rethinkdb.net.Cursor[Object]]
  cursor.iterator().asScala.foreach( println(_))

  System.exit(0)
}

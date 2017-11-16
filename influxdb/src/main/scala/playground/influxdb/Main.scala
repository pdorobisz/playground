package playground.influxdb

import com.paulgoldbaum.influxdbclient.Parameter.Precision
import com.paulgoldbaum.influxdbclient.{InfluxDB, Point}

import scala.concurrent.Await
import scala.concurrent.duration._

object Main extends App {

  import scala.concurrent.ExecutionContext.Implicits._

  val influxDb = InfluxDB.connect("localhost", 8086)
  val database = influxDb.selectDatabase("testdb")

  val point = Point("testpoint")
    .addTag("tag1", "test1")
    .addField("field1", 10)
    .addField("field2", 20)
  val f = database.write(point, precision = Precision.MILLISECONDS)
  f.onComplete(_ => influxDb.close())

  Await.result(f, 10.second)
  println("done")
}

package playground.influxdb

import java.text.SimpleDateFormat

import com.paulgoldbaum.influxdbclient.Parameter.Precision
import com.paulgoldbaum.influxdbclient.{InfluxDB, Point}

import scala.concurrent.Await
import scala.concurrent.duration._

object Main extends App {

  import scala.concurrent.ExecutionContext.Implicits._

  val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  val timestamp = dateFormat.parse("2017-12-01T10:00:03.000Z").getTime

  val influxDb = InfluxDB.connect("localhost", 8086)
  val database = influxDb.selectDatabase("testdb")

  // timestamp is optional, if not provided current time will be used
  val point = Point("cpu", timestamp)
    .addTag("host", "serverB")
    .addTag("region", "us_west")
    .addField("core1", 0.51)
    .addField("core2", 0.13)
  val f = database.write(point, precision = Precision.MILLISECONDS)
  f.onComplete(_ => influxDb.close())

  Await.result(f, 10.second)
  println("done")
}

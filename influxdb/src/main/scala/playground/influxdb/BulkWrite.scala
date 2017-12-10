package playground.influxdb

import java.text.SimpleDateFormat

import com.paulgoldbaum.influxdbclient.Parameter.Precision
import com.paulgoldbaum.influxdbclient.{InfluxDB, Point}

import scala.concurrent.Await
import scala.concurrent.duration._

object BulkWrite extends App {

  import scala.concurrent.ExecutionContext.Implicits._

  val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

  def timestamp(s: String) = dateFormat.parse(s).getTime

  val influxDb = InfluxDB.connect("localhost", 8086)
  val database = influxDb.selectDatabase("testdb")

  // timestamp is optional, if not provided current time will be used
  val points = Seq(
    Point("temp", timestamp("2017-12-10T10:00:00.000Z")).addTag("sensor", "sensor1").addField("t", 40),
    Point("temp", timestamp("2017-12-10T10:01:00.000Z")).addTag("sensor", "sensor1").addField("t", 42),
    Point("temp", timestamp("2017-12-10T10:06:00.000Z")).addTag("sensor", "sensor1").addField("t", 43),
    Point("temp", timestamp("2017-12-10T10:20:00.000Z")).addTag("sensor", "sensor1").addField("t", 45),
    Point("temp", timestamp("2017-12-10T10:32:00.000Z")).addTag("sensor", "sensor1").addField("t", 46),
    Point("temp", timestamp("2017-12-10T10:50:00.000Z")).addTag("sensor", "sensor1").addField("t", 48),

    Point("temp", timestamp("2017-12-10T11:00:00.000Z")).addTag("sensor", "sensor1").addField("t", 50),
    Point("temp", timestamp("2017-12-10T11:01:00.000Z")).addTag("sensor", "sensor1").addField("t", 52),
    Point("temp", timestamp("2017-12-10T11:06:00.000Z")).addTag("sensor", "sensor1").addField("t", 53),
    Point("temp", timestamp("2017-12-10T11:20:00.000Z")).addTag("sensor", "sensor1").addField("t", 55),
    Point("temp", timestamp("2017-12-10T11:32:00.000Z")).addTag("sensor", "sensor1").addField("t", 56),
    Point("temp", timestamp("2017-12-10T11:50:00.000Z")).addTag("sensor", "sensor1").addField("t", 58)
  )

  val f = database.bulkWrite(points, precision = Precision.MILLISECONDS)
  f.onComplete(_ => influxDb.close())

  Await.result(f, 10.second)
  println("done")
}

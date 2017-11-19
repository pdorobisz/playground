# Influx DB examples

Run InfluxDB:

`docker-compose up`

Access commandline:

`docker exec -it influxdb_influxdb_1 influx -precision rfc3339`

(`precision rfc3339` enables timestamp formatting)

## Influx DB - concepts

Data is organized by time series. Time series has zero to many points.
Point consists of `time` (timestamp), `measurement` (e.g. "temperatures"), at least one `field` (key-value representing 
measured value, e.g. "temperature=22.5"), zero to many `tags` (key-value containing metadata about value, e.g. "host=server1").
Tags are indexed, fields aren't. Measurement is a container for tags, fields and time, it's like SQL table.

Series is the collection of data in InfluxDB’s data structure that share a measurement, tag set, and retention policy (field set
is not part of series identification).

Points are written to InfluxDB using the Line Protocol, which follows the following format:

`<measurement>[,<tag-key>=<tag-value>...] <field-key>=<field-value>[,<field2-key>=<field2-value>...] [unix-nano-timestamp]`

Measurements, tag keys, tag values, and field keys are always strings. Field values can be floats (82), integers (82i), 
strings ("82"), or booleans.

Clustering is not supported by open-source version, only by commercial version.


## Queries

`CREATE DATABASE testdb`

` SHOW DATABASES`

`USE testdb`

`INSERT cpu,host=serverA,region=us_west core1=0.64,core2=0.4` (timestamp not provided so current time will be used)

`SHOW SERIES`, `SHOW MEASUREMENTS`, `SHOW TAG KEYS`, `SHOW FIELD KEYS`

`SELECT * FROM cpu`

`SELECT * FROM cpu ORDER BY time DESC`

`SELECT * FROM cpu LIMIT 2`

`SELECT * FROM cpu WHERE time > now() - 2h AND host='serverA'`

Grouping by tags (`*` to group by all tags):

`SELECT MEAN("core2") FROM cpu GROUP BY host,region`

Grouping by timestamp, 1 minute intervals:

`SELECT MEAN(*) FROM cpu WHERE time > now()-20m GROUP BY time(1m)`

Change data for intervals that have no data:

`SELECT MEAN(*) FROM cpu WHERE time > now()-20m GROUP BY time(1m) fill(0.5)` (other values: `none`, `linear`, `null`, `previous`)

`DROP DATABASE testdb`

`DROP MEASUREMENT cpu`

Drop series (points in given measurement and with given tag values):

`DROP SERIES FROM cpu WHERE host='serverA' and region='us_west'`


## HTTP API

`curl -i -X POST http://localhost:8086/query --data-urlencode "q=CREATE DATABASE testdb"`

`curl 'http://localhost:8086/query?pretty=true' --data-urlencode "db=testdb" --data-urlencode "q=SELECT * FROM \"cpu\""`

`curl -i -X POST http://localhost:8086/query --data-urlencode "q=DROP DATABASE testdb"`

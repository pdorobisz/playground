docker run -p 9200:9200 -p 9300:9300 -e "http.host=0.0.0.0" -e "transport.host=127.0.0.1" docker.elastic.co/elasticsearch/elasticsearch:5.3.2

docker run --rm -p 9100:9100 mobz/elasticsearch-head:5

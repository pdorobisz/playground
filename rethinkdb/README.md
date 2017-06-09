Start RethinkDB:

`docker run --rm -d -p 28015:28015 -p 8080:8080 --name rethink1 rethinkdb`

Run `RethinkdbDemo` app to setup data and start stream returning all changes to db.

Open web interface: http://localhost:8080/#dataexplorer

Run some queries:
```
r.db("mydb").table("authors")
r.db("mydb").table("authors").get("<Some UUID here>").update({name: "Laura Roslin 123"})
```

Stream should return updated data.
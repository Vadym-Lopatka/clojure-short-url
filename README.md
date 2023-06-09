# URL shortner with Clojure and PostgreSQL
based on this youtube tutorials:
https://www.youtube.com/watch?v=0mrguRPgCzI&ab_channel=onthecodeagain
https://www.youtube.com/watch?v=V-dBmuRsW6w&ab_channel=onthecodeagain


## Tech stack backend
- Clojure
- DB: Postgresql, Java.JDBC, HoneySql
- WebServer: Ring(jetty), Reitit, Muuntaja
## Tech stack frontend
- Shadow-cljs(compile cljs to js)
- Helix(React development in ClojureScript)
- Tailwind CSS

## Start the app
- do "DB preparation" first
- start REPL based on deps.edn

## DB preparation:

1. Start the PostgreSQL server
```console
$ cd /clojure-short-url
```
```console
$ docker-compose up -d
```

2. Create DB and the "redirects" table via pgsql or another tool you prefer

```sql 
CREATE DATABASE shorturl;
```

```sql 
CREATE TABLE redirects(
  slug varchar(10) PRIMARY KEY NOT NULL,
  url varchar(1000) NOT NULL                     
);
```

# clojure-short-url

Create a URL shortner with Clojure and PostgreSQL

based on this youtube tutorial:
https://www.youtube.com/watch?v=0mrguRPgCzI&ab_channel=onthecodeagain


DB preparation:

1. Start the PostgreSQL server
```console
$ cd /clojure-short-url
```
```console
$ docker-compose up -d
```

2. Create DB and the "redirects" table via pgsql or another tool you prefer

```pgsql> 
CREATE DATABASE shorturl;
```

```pgsql> 
CREATE TABLE redirects(
  slug varchar(10) PRIMARY KEY NOT NULL,
  url varchar(1000) NOT NULL                     
);
```

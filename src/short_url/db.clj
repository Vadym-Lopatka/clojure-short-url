(ns short-url.db
  (:require [clojure.java.jdbc :as j]))

;; Before running queries - we need to prepare the db
;; I use local docker PostgreSQL instance described in docker-compose.yml
;;
;; start docker
;;
;; docker-compose up -d
;;
;; create database shorturl;
;;
;; CREATE TABLE redirects(
;;   slug varchar(10) PRIMARY KEY NOT NULL,
;;   url varchar(1000) NOT NULL                     
;; );

(def pg-db {:dbtype "postgresql"
            :dbname "shorturl"
            :host "localhost"
            :user "postgres"
            :password "postgres"
            :ssl false
            :sslfactory "org.postgresql.ssl.NonValidatingFactory"})

(j/query pg-db
         ["select * from redirects"])



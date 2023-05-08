(ns short-url.db
  (:require [clojure.java.jdbc :as j]
            [honey.sql :as sql]
            [honey.sql.helpers :refer :all]))

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

(defn query [q]
  (j/query pg-db q))

(defn insert! [q]
  (j/db-do-prepared pg-db q))

(comment
  (query (-> (select :*)
             (from :redirects)
             (sql/format)))
  (insert! (-> (insert-into :redirects)
               (columns :slug :url)
               (values 
                [["abc" "https://www.youtube.com/watch?v=0mrguRPgCzI&ab_channel=onthecodeagain"]])
               (sql/format)))
  )




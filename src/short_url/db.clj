(ns short-url.db
  (:require 
   [clojure.java.jdbc :as j] 
   [honey.sql :as sql]
   [honey.sql.helpers :refer [insert-into
                              columns
                              values
                              select
                              from
                              where]]
   [short-url.env :refer [env]]))

(def pg-db {:dbtype (env :DBTYPE)
            :dbname (env :DBNAME)
            :host (env :HOST)
            :user (env :USER)
            :password (env :PASSWORD)
            :ssl (parse-boolean (env :SSL))
            :sslfactory (env :SSLFACTORY)})

(defn query [q]
  (j/query pg-db q))

(defn insert! [q]
  (j/db-do-prepared pg-db q))

(defn insert-redirect! [slug url] 
  (insert! (-> (insert-into :redirects)
               (columns :slug :url)
               (values
                [[slug url]])
               (sql/format))))

(defn get-url [slug]
  (-> (query (->
              (select :*) 
              (from :redirects) 
              (where [:= :slug slug])
              (sql/format)))
      first
      :url))

(comment
  (query (-> (select :*)
             (from :redirects)
             (sql/format)))
  (insert! (-> (insert-into :redirects)
               (columns :slug :url)
               (values 
                [["clj" "https://clojure.org/guides/deps_and_cli"]
                 ["hon" "https://github.com/seancorfield/honeysql"]])
               (sql/format)))
  (insert-redirect! "aaa" "https://google.com")
  (get-url "clj"))
  

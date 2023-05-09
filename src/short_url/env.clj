(ns short-url.env
  (:require 
   [clojure.edn :refer [read-string]]))

(def envvars (read-string (slurp "env.edn")))

(defn env [k]
  (or (k envvars) (System/getenv (name k))))

(comment
  (parse-boolean (env :SSL))
  )
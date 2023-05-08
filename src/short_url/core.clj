(ns short-url.core
  (:require [ring.adapter.jetty :as ring-jetty]
            [reitit.ring :as ring]
            [ring.util.response :as r]
            [muuntaja.core :as m]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [short-url.db :as db]))


(defn redirect [req]
  (let [slug (get-in req [:path-params :slug])
        url (db/get-url slug)]
    (if url
      (r/redirect url 307)
      (r/not-found "URL is not found"))))

(def app
  (ring/ring-handler
   (ring/router
    ["/"
     [":slug/" redirect]
     ["" {:handler (fn [req] {:body "Hello" :status 200})}]]
    {:data {:muuntaja m/instance
            :middleware [muuntaja/format-middleware]}})))

(defn start []
  (ring-jetty/run-jetty #'app {:port 3000
                             :join? false}))

(def server (start))

(.stop server)

;; (defn -main [&args]
;;   (start))
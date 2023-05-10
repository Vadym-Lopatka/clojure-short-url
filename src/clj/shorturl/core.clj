(ns shorturl.core
  (:require
   [ring.adapter.jetty :as ring-jetty]
   [reitit.ring :as ring]
   [ring.util.response :as r]
   [muuntaja.core :as m]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [shorturl.db :as db]
   [shorturl.slug :refer [generate-slug]]))


(defn redirect [req]
  (let [slug (get-in req [:path-params :slug])
        url (db/get-url slug)]
    (if url
      (r/redirect url 307)
      (r/not-found "URL is not found"))))

(defn create-redirect [req]
  (let [url (get-in req [:body-params :url])
        slug (generate-slug)]
    (db/insert-redirect! slug url)
    (r/response (str "Created slug: " slug))))

(def app
  (ring/ring-handler
   (ring/router
    ["/"
     [":slug/" redirect]
     ["api/" 
      ["redirect/" {:post create-redirect}]]
     ["" {:handler (fn [_] {:body "Create redirect screen" :status 200})}]]
    {:data {:muuntaja m/instance
            :middleware [muuntaja/format-middleware]}})))

(defn start []
  (ring-jetty/run-jetty #'app {:port 3000
                             :join? false}))

(def server (start))

(.stop server)
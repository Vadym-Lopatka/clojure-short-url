(ns app.core 
  (:require [helix.core :refer [defnc $]]
            [helix.hooks :as hooks]
            [helix.dom :as d]
            ["react-dom/client" :as rdom]
            ))

(defnc app []
  (let [[state set-state] (hooks/use-state {:url ""})]
    (d/div
      (d/input {:value (:url state)
                :on-change #(set-state assoc :url (.. % -target -value))})
     (d/button "Shorten URL"))))


  ;; start your app with your favorite React renderer
;; (defonce root (rdom/createRoot (js/document.getElementById "app")))
;; (.render root ($ app))

(defn ^:export init []
  (let [root (rdom/createRoot (js/document.getElementById "app"))]
    (.render root ($ app))))


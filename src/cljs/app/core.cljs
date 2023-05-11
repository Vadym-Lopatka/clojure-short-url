(ns app.core 
  (:require [helix.core :refer [defnc $]]
            [helix.hooks :as hooks]
            [helix.dom :as d]
            ["react-dom/client" :as rdom]
            [promesa.core :as p] 
            ))

(defnc app []
  (let [[state set-state] (hooks/use-state {:url ""})
        fetch-slug (fn [] 
                     (p/let [response (js/fetch "/api/redirect/" (clj->js {:headers {:Content-Type "application/json"}
                                                                          :method "POST"
                                                                          :body (js/JSON.stringify #js {:url (:url state)})}))] 
                       (.json response)))]
    (d/div
      (d/input {:value (:url state)
                :on-change #(set-state assoc :url (.. % -target -value))})
     (d/button {:on-click #(fetch-slug)} "Shorten URL"))))

(defn ^:export init []
  (let [root (rdom/createRoot (js/document.getElementById "app"))]
    (.render root ($ app))))

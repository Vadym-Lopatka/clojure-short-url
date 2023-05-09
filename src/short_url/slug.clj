(ns short-url.slug)

(def charset "ABSDEFGHIJKLMNOPQRSTUXYZ")

(defn generate-slug []
  (->> (repeatedly #(rand-nth charset))
       (take 4)
       (apply str)))

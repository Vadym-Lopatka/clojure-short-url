(ns short-url.slug)

(def charset "ABSDEFGHIJKLMNOPQRSTUXYZ")

(defn generate-slug []
  (->> (repeatedly #(rand-nth charset))
       (take 4)
       (apply str)))


;; (comment
;;   (generate-slug)
;;   (rand-nth charset)
;;   (apply str (take 4 (repeatedly #(rand-nth charset)))))

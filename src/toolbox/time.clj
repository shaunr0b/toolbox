(ns skyscraper.utils.time
  (:require [clj-time.core :as t]))

(defn missing-intervals
  "Given a coll of intervals and a desired interval,
  returns missing gaps as a collection of intervals.
  Thanks to TVH for this gem."
  ([ins desired-in]
    (let [filtered-ins (filter #(t/overlaps? desired-in %) ins)
          sorted-ins (sort-by t/start filtered-ins)
          [d-start d-end] ((juxt t/start t/end) desired-in)
          starts (conj (mapv t/start sorted-ins) d-end)
          ends (remove #(t/after? % d-end)
                       (vec (conj (map t/end sorted-ins) d-start)))
          split-ends (remove (fn [end] (some #(t/within? % end) sorted-ins))
                             ends)]
      (map
        (fn [start]
          [start
           (first (drop-while #(t/before? % start) starts))])
        split-ends))))

(defn same-day?
  "Predicate for checking if an interval or
  vector of two dates is the same day."
  [[d1 d2]]
  (= (t/day d1) (t/day d2)))
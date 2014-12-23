(ns skyscraper.utils.functions)

(defn zipfunc
  "Dedicated to Josh.
  [1 2] [inc dec] => [2 1]"
  [is fs]
  (map (fn [i f] (f i)) is fs))

(defn list-contains? [coll value]
  "Utility to check if a collection contains a value"
  (let [s (seq coll)]
    (if s
      (if (= (first s) value) true (recur (rest s) value))
      false)))

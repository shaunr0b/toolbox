(ns toolbox.io
  (:require [clojure.pprint]))

(defn pretty-spit [path data]
  (let [d (with-out-str
            (clojure.pprint/pprint data))]
    (spit path d)))

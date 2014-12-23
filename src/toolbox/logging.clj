(ns skyscraper.utils.logging)

(defmacro describe [msg & body]
  `(do (println :start ~msg)
       (let [ret# (do ~@body)]
         (println :end ~msg)
         ret#)))

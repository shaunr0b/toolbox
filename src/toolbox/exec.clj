(ns
  ^{:doc "Utilities for multithreaded task execution."}
  skyscraper.utils.exec
  (:require [taoensso.timbre :refer [info]]))

(defn pooled-exec
  "Executes a collection of lambdas using"
  [{:keys [num-workers]} & fs]
  (let [pool-size (quot (count fs) num-workers)
        pools (partition-all pool-size fs)
        running? (atom true)]

    (doseq [pool pools]
      (future
        (doseq [f pool]
          (when @running?
            (try
              (f)
                 (catch Exception e
                   (info e)))))))

    running?))
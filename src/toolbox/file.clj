(ns toolbox.file
  (:require [clojure.java.io :as io]))

(defn files-in-directory
  "List all files in a directory."
  [directory]
  (->>
    (.listFiles (io/file directory))
    (remove #(.isDirectory %))
    (remove #(= \. (first (.getName %))))))

(defn rename-files-in-directory!
  "Rename all files in a directory"
  [directory find-regex replace-str]
  (doseq [file (files-in-directory directory)]
    (let [file-path (.getPath file)
          new-name (clojure.string/replace file-path find-regex replace-str)]
      (.renameTo file (java.io.File. new-name)))))

(defn file-exists? [file]
  (.exists (io/as-file file)))

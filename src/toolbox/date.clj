(ns skyscraper.utils.date
  (:require
    [clj-time.core :as t]
    [clj-time.coerce :as c]
    [clj-time.format :as f]))

(defn translate-date-str
  "Translate date string to new format. Example:
    (translate-date-str MM/dd/YYYY YYYY-MM-dd 03/04/1987)
    => 1987-03-04"
  [in-fmt out-fmt in]
  (f/unparse (f/formatter out-fmt)
             (f/parse (f/formatter in-fmt) in)))


(defn str->date
  "Given a format string and date string, unparse into Joda date."
  [format date-str]
  (c/to-date (f/parse (f/formatter format) date-str)))

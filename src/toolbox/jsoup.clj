(ns skyscraper.utils.jsoup
  (:import [org.jsoup Jsoup]))

(defn table->matrix
  "Given a Jsoup element with <tr>s and <tds>
  parses out tabular data and returns a matrix.
  Works best for simple tables without nesting."
  [table-element]
  (for [row (.select table-element "tr")]
    (for [col (.select row "td")]
      (.text col))))

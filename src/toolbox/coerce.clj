(ns skyscraper.utils.coerce)

(def valid-number-regex
  #"^[+-]?(\d*|\d{1,3}(,\d{3})*)(\.\d+)?\b$")

(defn numeric?
  [s]
  (not (or (nil? s)
           (nil? (re-find valid-number-regex s)))))

(defn clean-dec
  [s]
  (if (nil? (re-find #"^\.[0-9]+" s))
      s
      (apply str (cons \0 s))))

(defn parens->neg [s]
  (let [matches
        (re-matches #"(\()([\d\.]+)(\))" s)]
    (if-let [match (nth matches 2)]
      (str "-" match)
      s)))

(defn clean
  [s]
  (assert (string? s) (str "Input must be string. Input: " s))
  (-> s
      clojure.string/trim
      (clojure.string/replace #"[\$\,]" "")
      parens->neg
      clean-dec))

(defn str->dec [s]
 (if (empty? s)
   nil
   (let [cleaned (clean s)]
     (assert (numeric? (clean s)) (str "Input `" s "` must be numeric"))
       (bigdec (read-string cleaned)))))

(defn parse-long [s]
  (Long. (re-find #"\d+" s)))

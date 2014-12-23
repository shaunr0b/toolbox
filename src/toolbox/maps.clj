(ns skyscraper.utils.maps)

(defn remove-nils
  "Remove keys from map with nil values (preserves falses.)"
  [record]
  (into {} (remove (comp nil? second) record)))

(defn remove-nil-strings
  "Remove keys from map with nil values or nil strings"
  [record]
  (into {} (remove (comp empty? second) record)))

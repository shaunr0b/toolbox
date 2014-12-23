(ns skyscraper.utils.data)

(defn tuplify
  "Returns a vector of the vals at keys ks in map."
  [m ks]
  (mapv #(get m %) ks))

(defn maps->rel
  "Convert a collection of maps into a relation, returning
   the tuplification of each map by ks"
  [maps ks]
  (mapv #(tuplify % ks) maps))

(defn namespace-key
  "Turns :a :b into :a/b"
  [namespc k]
  (keyword (name namespc) (name k)))

(defn namespace-keys
  "Namespaces all keys in a map."
  [mp namespc]
  (->> mp
       (map (fn [[k v]]
              [(namespace-key namespc k) v]))
       (into {})))

(defn remove-keys
  "Remove map keys where values are empty"
  [pred mp]
  (reduce-kv
   (fn [m k v]
     (if (pred v)
       m
       (assoc m k v))) {} mp))

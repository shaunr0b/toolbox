(ns skyscraper.utils.selenium)

(defn- remove-nils [record]
  (into {} (filter second record)))

(defn cookie->map [cookie]
  (let [cookie (:cookie cookie)]
    (remove-nils
      {:name   (.getName cookie)
       :value  (.getValue cookie)
       :domain (.getDomain cookie)
       :path   (.getPath cookie)
       :expiry (.getExpiry cookie)
       :secure? (.isSecure cookie)})))

(comment
  (->> d
       cookies
       (map cookie->map)
       first
       :domain
       (str "http://")
       (to d2))

  (->> d
       cookies
       (map cookie->map)
       (map (partial add-cookie d2))))

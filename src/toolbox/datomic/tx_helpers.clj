(ns toolbox.datomic.tx-helpers
  (:require [datomic.api :as d]))

(defn- get-entity-tempid [tx-data attribute]
  (->
    (filter
      #(contains? % attribute)
      tx-data)
   first
   :db/id))

(defn transact-return-entity
  "Transact _tx-data_ via _conn_. Return entity
  of new attribute with unique ID _attribute_."
  [conn tx-data attribute]
  (let [tempid   (get-entity-tempid tx-data attribute)
        tx       @(d/transact conn tx-data)
        tempids  (:tempids tx)
        db       (d/db conn)
        eid      (d/resolve-tempid db tempids tempid)
        entity   (d/entity db eid)]
  entity))

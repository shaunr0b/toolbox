(defproject env/toolbox "0.1.0"
  :description "Common utilities for Clojure and Datomic"
  :plugins [[s3-wagon-private "1.1.2"]]
  :repositories [["releases" {:url "s3p://msg-jars/releases/" :creds :gpg}]]
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [])

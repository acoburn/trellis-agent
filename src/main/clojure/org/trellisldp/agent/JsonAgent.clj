;; Licensed under the Apache License, Version 2.0 (the "License");
;; you may not use this file except in compliance with the License.
;; You may obtain a copy of the License at
;;
;;     http://www.apache.org/licenses/LICENSE-2.0
;;
;; Unless required by applicable law or agreed to in writing, software
;; distributed under the License is distributed on an "AS IS" BASIS,
;; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
;; See the License for the specific language governing permissions and
;; limitations under the License.

(ns org.trellisldp.agent.JsonAgent
  (:import [java.util ArrayList]
           [java.util ServiceLoader]
           [org.apache.commons.rdf.api RDF])
  (:require [clojure.string :as str]
            [cheshire.core :refer :all])
  (:gen-class
    :init init
    :state state
    :name org.trellisldp.agent.JsonAgent
    :constructors {[String String] []}
    :implements [org.trellisldp.spi.AgentService]))

(def data (ref {}))

(def rdf (first (ServiceLoader/load RDF)))

(defn unprefix [prefix identifier]
  (str/replace-first (.getIRIString identifier) prefix ""))

(defn toIRI [identifier]
  (.createIRI rdf identifier))

(defn read! [file]
  (io!
    (parse-string (slurp file) true)))

(defn -init [file prefix]
  [[]
    (let [json (read! file)]
      (dosync
        (ref-set data json))
      (ref {:prefix prefix}))])

(defn -asAgent [this username]
  (.createIRI rdf (str (@(.state this) :prefix) username)))

(defn -isAdmin [this identifier]
  (some? (some (partial = (unprefix (@(.state this) :prefix) identifier)) (get @data :admin))))


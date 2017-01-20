;; Copyright Amherst College
;;
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

(ns edu.amherst.acdc.trellis.agent.JsonAgent
  (:import [java.util ArrayList]
           [java.util.stream Stream]
           [java.util ServiceLoader]
           [org.apache.commons.rdf.api RDF])
  (:require [clojure.string :as str]
            [cheshire.core :refer :all])
  (:gen-class
    :init init
    :state state
    :name edu.amherst.acdc.trellis.agent.JsonAgent
    :constructors {[String String] []}
    :implements [edu.amherst.acdc.trellis.spi.AgentService]))

(def data (ref {}))

(def rdf (first (ServiceLoader/load RDF)))

(defn unprefix [prefix identifier]
  (str/replace-first (.getIRIString identifier) prefix ""))

(defn toIRI [identifier]
  (.createIRI rdf identifier))

(defn -init
  ([file prefix] [[]
    (do
      (dosync
        (ref-set data (parse-string (slurp file) true)))
      (ref {:prefix prefix}))]))

(defn -asAgent [this username]
  (.createIRI rdf (str (@(.state this) :prefix) username)))

(defn -isAdmin [this identifier]
  (some? (some (partial = (unprefix (@(.state this) :prefix) identifier)) (get @data :admin))))

(defn -getGroups [this identifier]
  (let [prefix (@(.state this) :prefix)
        data (get @data (keyword (unprefix prefix identifier)))]
    (if (and (str/starts-with? (.getIRIString identifier) prefix) (some? data))
      (->> data (map toIRI) (ArrayList.) (.stream))
      (Stream/empty))))


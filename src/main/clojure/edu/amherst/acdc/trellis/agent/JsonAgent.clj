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
  (:import [java.util.stream Stream])
  (:import [java.util ServiceLoader])
  (:import [org.apache.commons.rdf.api IRI RDF])
  (:require [cheshire.core :refer :all])
  (:gen-class
    :init init
    :state state
    :name edu.amherst.acdc.trellis.agent.JsonAgent
    :constructors {[String String] []}
    :implements [edu.amherst.acdc.trellis.spi.AgentService]))

(def data (ref {}))

(defn createIRI [prefix username]
  (.createIRI (first (ServiceLoader/load RDF)) (str prefix username)))

(defn -init
  ([file prefix] [[]
    (dosync
      (ref-set data (parse-string (slurp file))))
    (ref {:prefix prefix})]))

(defn -asAgent [this username]
  (createIRI (str (@(.state this) :prefix) username)))

(defn -isAdmin [this identifier]
  (some (partial = (.getIRIString identifier)) (get @data :admin)))

(defn -getGroups [this identifier] (Stream/empty))


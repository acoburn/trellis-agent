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
  (:import [java.util ServiceLoader]
           [org.apache.commons.rdf.api RDF])
  (:require [clojure.string :as str])
  (:gen-class
    :init init
    :state state
    :name org.trellisldp.agent.JsonAgent
    :constructors {[String] []}
    :implements [org.trellisldp.spi.AgentService]))

(def rdf (first (ServiceLoader/load RDF)))

(defn toIRI [identifier]
  (.createIRI rdf identifier))

(defn -init [prefix]
  [[]
    (ref {:prefix prefix})])

(defn -asAgent [this username]
  (.createIRI rdf (str (@(.state this) :prefix) username)))


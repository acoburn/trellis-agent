(ns edu.amherst.acdc.trellis.agent.JsonAgent
  (:import [java.util.stream Stream])
  (:import [java.util ServiceLoader])
  (:import [org.apache.commons.rdf.api IRI RDF])
  (:import [edu.amherst.acdc.trellis.spi AgentService])
  (:gen-class
    :name edu.amherst.acdc.trellis.agent.JsonAgent
    :implements [AgentService]))

(defn createIRI [prefix username]
  (.createIRI (first (ServiceLoader/load RDF)) (str prefix username)))

(defn -asAgent [this username]
  (createIRI "info:trellis/user/" username))

(defn -isAdmin [this identifier] false)

(defn -getGroups [this identifier] (Stream/empty))


(ns edu.amherst.acdc.trellis.agent.JsonAgent-test
  (:import [org.apache.commons.rdf.api IRI RDF])
  (:import [java.util ServiceLoader])
  (:require [clojure.test :refer :all]
            [edu.amherst.acdc.trellis.agent.JsonAgent :refer :all]))

(deftest a-test
  (testing "Get identifier"
    (is (= (.createIRI (first (ServiceLoader/load RDF)) "info:trellis/user/test")
           (createIRI "info:trellis/user/" "test")))))

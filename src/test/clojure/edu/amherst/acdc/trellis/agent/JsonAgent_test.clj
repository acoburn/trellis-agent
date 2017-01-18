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

(ns edu.amherst.acdc.trellis.agent.JsonAgent-test
  (:import (edu.amherst.acdc.trellis.agent JsonAgent))
  (:import [org.apache.commons.rdf.api IRI RDF])
  (:import [java.util ServiceLoader])
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [cheshire.core :refer :all]
            [edu.amherst.acdc.trellis.agent.JsonAgent :refer :all]))

(deftest agent-test
  (testing "Test agent service"
    (let [service (JsonAgent. "build/resources/test/test.json" "info:user/")]
      (is (= (.asAgent service "acoburn") (.createIRI (rdf) "info:user/acoburn")))
      (is (= false (.isAdmin service (.asAgent service "bseeger"))))
      (is (= (.isAdmin service (.asAgent service "acoburn")))))))

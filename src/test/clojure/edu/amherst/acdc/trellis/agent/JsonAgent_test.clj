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
  (:import [edu.amherst.acdc.trellis.agent JsonAgent]
           [java.util.stream Collectors])
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [cheshire.core :refer :all]
            [edu.amherst.acdc.trellis.agent.JsonAgent :refer :all]))

(deftest agent-test
  (testing "Test agent service"
    (let [service (JsonAgent. "build/resources/test/test.json" "info:user/")
          acoburn (toIRI "info:user/acoburn")
          bseeger (toIRI "info:user/bseeger")
          group1 (toIRI "info:group/test1")
          foo (toIRI "info:user/foo")]
      (is (= (.asAgent service "acoburn") acoburn))
      (is (= false (.isAdmin service acoburn)))
      (is (.isAdmin service bseeger))
      (is (= 2 (.count (.getGroups service acoburn))))
      (is (= 1 (.count (.getGroups service bseeger))))
      (is (= 0 (.count (.getGroups service foo))))
      (is (.contains (.collect (.getGroups service acoburn) (Collectors/toSet)) group1)))))


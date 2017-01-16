(defproject trellis-service-agent-json "0.1.0-SNAPSHOT"
  :description "And agent service for a trellis repository"
  :url "http://example.com/FIXME"
  :license {:name "Apache 2"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.8.0"]
                 [org.apache.commons/commons-rdf-api "0.3.0-incubating"]
                 [edu.amherst.acdc/trellis-spi "0.1.0-SNAPSHOT"]]
  :profiles {:test {:dependencies [
                 [org.apache.commons/commons-rdf-simple "0.3.0-incubating"]]}})


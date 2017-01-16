(defproject trellis-agent "0.1.0-SNAPSHOT"
  :description "An agent service for a trellis repository"
  :url "https://github.com/acoburn/trellis"
  :license {:name "Apache 2"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
                 [org.clojure/clojure "1.8.0"]
                 [org.apache.commons/commons-rdf-api "0.3.0-incubating"]
                 [edu.amherst.acdc/trellis-spi "0.1.0-SNAPSHOT"]]
  :repositories {"sonartype snapshots" "https://oss.sonatype.org/content/repositories/snapshots"}
  :profiles {:test {:dependencies [
                 [org.apache.commons/commons-rdf-simple "0.3.0-incubating"]]}})


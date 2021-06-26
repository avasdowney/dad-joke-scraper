(defproject reddit-scraper "0.1.0-SNAPSHOT"
  :description "Reddit Scraper"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [enlive "1.1.1"]
                 [clj-http "3.12.0"]
                 [cheshire "5.9.0"]
                 [hiccup "1.0.5"]]
  :main ^:skip-aot reddit-scraper.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

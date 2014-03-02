(defproject wracer "0.1.0-SNAPSHOT"
  :description "alt! racing web with core.async/clojurescript"
  :url "https://github.com/tolitius/wracer"

  :source-paths ["src" "src/wracer"]
  :test-paths ["test"]

  :dependencies [[compojure "1.1.6"]
                 [ring "1.2.1"]
                 [org.clojure/clojurescript "0.0-2030"]
                 [org.clojure/clojure "1.5.1"]
                 [prismatic/dommy "0.1.2"]
                 [clj-http "0.9.0"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [hiccup "1.0.5"]]

  :plugins [[lein-ring "0.8.10"]
            [com.cemerick/austin "0.1.3"]
            [lein-cljsbuild "1.0.2"]]

  :hooks [leiningen.cljsbuild]

  :repl-options {:init-ns wracer}

  :cljsbuild {
    :builds [{:source-paths ["src/wracer/cljs"]
              :compiler {:output-to "resources/public/js/wracer.js"
                         :optimizations :whitespace
                         :pretty-print true
                         ;; :source-map "resources/public/js/wracer.js.map"
                         }}]}

  :ring {:handler wracer.handler/app}

  :profiles
    {:dev {:dependencies [[ring-mock "0.1.5"]
                          [javax.servlet/servlet-api "2.5"]]}})

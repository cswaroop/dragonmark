(defproject dragonmark/circulate "0.2.0"
  :description "Distributed CSP/core.async"
  :url "https://github.com/dragonmark/dragonmark"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.cognitect/transit-clj "0.8.247"  :exclusions [org.clojure/clojure]]
                 [dragonmark/util "0.1.1"  :exclusions [org.clojure/clojure]]
                 [com.cognitect/transit-cljs "0.8.184"  :exclusions [org.clojure/clojure]]
                 [prismatic/schema "0.2.4"  :exclusions [org.clojure/clojure]]
                 [org.clojure/clojurescript "0.0-2322"]
                 [org.clojure/core.async "0.1.338.0-5c5012-alpha"
                  :exclusions [org.clojure/clojure]]
                 ]

  :plugins [[codox "0.8.10"  :exclusions [org.clojure/clojure]]
            [com.cemerick/austin "0.1.4"  :exclusions [org.clojure/clojure]]
            [lein-cljsbuild "1.0.3"]
            [com.keminglabs/cljx "0.4.0"  :exclusions [org.clojure/clojure]]
            [com.cemerick/clojurescript.test "0.3.1"  :exclusions [org.clojure/clojure]]]

  :codox {:defaults {:doc/format :markdown}
          :sources ["target/generated/src"]
          :output-dir "doc/codox"
          :src-linenum-anchor-prefix "L"
          :src-uri-mapping {#"target/generated/src" #(str "src/" % "x")}}

  :jar-exclusions [#"\.cljx|\.swp|\.swo|\.DS_Store|\.props"]

  :cljx
  {:builds
   [{:source-paths ["src"], :output-path "target/generated/src", :rules :clj}
    {:source-paths ["test"], :output-path "target/generated/test", :rules :clj}
    {:source-paths ["src"], :output-path "target/generated/src", :rules :cljs}
    {:source-paths ["test"], :output-path "target/generated/test", :rules :cljs}]}
  :source-paths ["src" "target/generated/src"]
  :test-paths   ["test" "target/generated/test"]
  :hooks [cljx.hooks]
  :cljsbuild
  {:builds
   [{:source-paths ["target/generated/src" "target/generated/test"]
     :compiler {:output-to "target/main.js"}}]
   :test-commands {"unit-tests" ["phantomjs" :runner "target/main.js"]}}
  :aliases
  {"test-cljs" ["do" ["cljx" "once"] ["cljsbuild" "test"]]
   "test-all"  ["do" ["test"] ["cljsbuild" "test"]]}

  :profiles
  {:provided {:dependencies [[org.clojure/clojurescript "0.0-2268"]]}}

)

(defproject dragonmark "0.1.0-SNAPSHOT"
  :description "Distributed CSP/core.async"
  :url "http://dragonmark.org"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.cognitect/transit-clj "0.8.229"]
                 [com.cognitect/transit-cljs "0.8.137"]                 
                 [org.clojure/core.async "0.1.278.0-76b25b-alpha"]]

  :plugins [[codox "0.8.10"]
            [lein-cljsbuild "1.0.3"]
            [com.keminglabs/cljx "0.4.0"]
            [com.cemerick/clojurescript.test "0.3.1"]]

  :jar-exclusions [#"\.cljx|\.swp|\.swo|\.DS_Store"]

  :codox {:defaults {:doc/format :markdown}
          :sources ["target/generated/src"]
          :output-dir "doc/codox"
          :src-linenum-anchor-prefix "L"
          :src-uri-mapping {#"target/generated/src" #(str "src/" % "x")}}

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

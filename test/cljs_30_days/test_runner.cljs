;; This test runner is intended to be run from the command line
(ns cljs-30-days.test-runner
  (:require
    ;; require all the namespaces that you want to test
    [cljs-30-days.main-test]
    [figwheel.main.testing :refer [run-tests-async]]))

(defn -main [& args]
  (run-tests-async 5000))

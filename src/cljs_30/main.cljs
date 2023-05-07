(ns ^:figwheel-hooks cljs-30.main
  (:require [cljs-30.day-01-drum-kit :as d01]))

(defonce app-state (atom {:text "Hello world!"}))

(defn root []
  (.getElementById js/document "app"))

(defn render [root & components]
  (doseq [c components] (.append root c)))

(defn mount
  []
  (aset (root) "innerHTML" "")
  (render (root) (d01/show)))

;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  (mount)
  ;; optionally touch your app-state to force rerendering
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(defonce start-up (do (mount) true))

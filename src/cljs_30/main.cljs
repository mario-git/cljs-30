(ns ^:figwheel-hooks cljs-30.main
  (:require [cljs-30.day-01 :as d01]
             ["react-dom/client" :refer [createRoot]]
             [goog.dom :as gdom]
             [reagent.core :as r]))

(defonce app-state (atom {:text "Hello world!"}))

(defonce root (createRoot (gdom/getElement "app")))

(defn mount []
  (.render root (r/as-element [:f> d01/drum-kit])))

(defn ^:after-load on-reload [] (mount)
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

(defonce start-up (do (mount) true))

(ns ^:figwheel-hooks cljs-30.main
  (:require [clojure.string :as s]
            [cljs-30.day-01 :as d01]
            ["react-dom/client" :refer [createRoot]]
            [goog.dom :as gdom]
            [reagent.core :as r]))

(defonce app-state (atom {:text "Hello world!"}))

(defonce root (createRoot (gdom/getElement "app")))

(defn get-component-name [c] (-> c .-name (s/split "$") last))
(def challenges [d01/drum-kit])

(defn mount []
  (.render root (r/as-element
                 [:div
                  (map (fn [c] [:input {:type "button"
                                        :on-click #(js/alert "to be bound yet! :)")
                                        :value (get-component-name c)}])
                       challenges)]
                 #_[:f> d01/drum-kit])))

(defn ^:after-load on-reload [] (mount)
  ;; (swap! app-state update-in  [:__figwheel_counter] inc)
  )

(defonce start-up (do (mount) true))

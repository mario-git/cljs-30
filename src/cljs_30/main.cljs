(ns ^:figwheel-hooks cljs-30.main
  (:require [clojure.string :as s]
            [cljs-30.day-01 :as d01]
            [cljs-30.day-02 :as d02]
            [cljs-30.day-03 :as d03]
            ["react-dom/client" :refer [createRoot]]
            [goog.dom :as gdom]
            [react :as react]
            [reagent.core :as r]))

(def challenges [d01/drum-kit d02/css-js-clock d03/css-variables])

(def current-component (r/atom nil))

(defn get-component-name [c] (-> c .-name (s/split "$") last))

(defn main-component []
  [:div
   (map (fn [c]
          ^{:key c}
          [:input {:type "button"
                   :on-click #(reset! current-component c)
                   :value (get-component-name c)}])
        challenges)])

(defn body []
  (when (nil? @current-component) (reset! current-component main-component))
  (react/useEffect
   #(.addEventListener
     js/window
     "keydown"
     (fn [event]
       (when (= "Escape" (.-key event))
         (reset! current-component main-component)))
     js/undefined))
  [:f> @current-component])

(defn mount []
  (.render
   (createRoot (.-body js/document))
   (r/as-element [:f> body])))

(defn ^:after-load on-reload [] (mount)
  ;; (swap! app-state update-in  [:__figwheel_counter] inc)
  )

(defonce start-up (do (mount) true))

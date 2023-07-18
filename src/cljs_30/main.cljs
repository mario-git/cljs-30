(ns ^:figwheel-hooks cljs-30.main
  (:require [clojure.string :as s]
            [cljs-30.day-01 :refer [drum-kit]]
            [cljs-30.day-02 :refer [css-js-clock]]
            [cljs-30.day-03 :refer [css-variables]]
            [cljs-30.day-04-and-07 :refer [no-array-cardio]]
            [cljs-30.day-05 :refer [flex-panels]]
            [cljs-30.day-06 :refer [type-ahead]]
            ["react-dom/client" :refer [createRoot]]
            [goog.dom :as gdom]
            [react :as react]
            [reagent.core :as r]))

(def challenges [drum-kit css-js-clock css-variables no-array-cardio flex-panels type-ahead])

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
   (r/as-element [:f> body #_type-ahead])))

(defn ^:after-load on-reload [] (mount)
  ;; (swap! app-state update-in  [:__figwheel_counter] inc)
  )

(defonce start-up (do (mount) true))

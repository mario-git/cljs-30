(ns ^:figwheel-hooks cljs-30.main
  (:require [clojure.string :as s]
            [cljs-30.challenge-01 :refer [drum-kit]]
            [cljs-30.challenge-02 :refer [css-js-clock]]
            [cljs-30.challenge-03 :refer [css-variables]]
            [cljs-30.challenge-05 :refer [flex-panels]]
            [cljs-30.challenge-06 :refer [type-ahead]]
            [cljs-30.challenge-08 :refer [fun-with-canvas]]
            [cljs-30.challenge-10 :refer [hold-shift-check]]
            ["react-dom/client" :refer [createRoot]]
            [goog.dom :as gdom]
            [react :as react]
            [reagent.core :as r]))

(def challenges [drum-kit css-js-clock css-variables flex-panels type-ahead fun-with-canvas hold-shift-check])

(def current-component (r/atom nil))

(defn get-component-name [c] (-> c .-name (s/split "$") last))

(defn main-component []
  [:div
   (map (fn [c]
          ^{:key c}
          [:input {:type "button"
                   :on-click #(reset! current-component c)
                   :value (get-component-name c)}])
        challenges)
   [:div "Challenges skipped:"
    [:ul
     [:li "4&7: no array cardio for clojure devs"]
     [:li "9: no dev tools/console.log tricks"]]]])

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
   (r/as-element [:f> #_body hold-shift-check])))

(defn ^:after-load on-reload [] (mount)
  ;; (swap! app-state update-in  [:__figwheel_counter] inc)
  )

(defonce start-up (do (mount) true))

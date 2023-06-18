(ns cljs-30.day-02
  (:require [clojure.string :as s]
            [react :as react]))

(defn css-js-clock []
  [:div {:class "clock"}
   [:link {:href "css/day-02/style.css"
           :rel "stylesheet"
           :type "text/css"}]
   [:div {:class "clock-face"}
    [:div {:class "hand four-hand"}]
    [:div {:class "hand min-hand"}]
    [:div {:class "hand second-hand"}]]])

(ns cljs-30.day-02
  (:require [react :as react]
            [reagent.core :as r]))

(defn- time-now []
  (let [now (js/Date.)]
    {:h (.getHours now)
     :m (.getMinutes now)
     :s (.getSeconds now)}))

(defn- time-now-to-clock-degrees []
  (let [{:keys [h m s]} (time-now)]
    {:h (-> h (/ 12) (* 360) (+ 90))
     :m (-> m (/ 60) (* 360) (+ 90))
     :s (-> s (/ 60) (* 360) (+ 90))}))

(def time-state (r/atom (time-now-to-clock-degrees)))

(defn css-js-clock []
  (let [{:keys [h m s]} @time-state]
    (react/useEffect
     (fn []
       (let [i (.setInterval js/window #(reset! time-state (time-now-to-clock-degrees)) 1000)]
         (fn [] (js/clearInterval i)))))
    [:div {:class "clock"}
     [:link {:href "css/day-02/style.css"
             :rel "stylesheet"
             :type "text/css"}]
     [:div {:class "clock-face"}
      [:div {:class "hand four-hand" :style {:transform (str "rotate(" h "deg)")}}]
      [:div {:class "hand min-hand" :style {:transform (str "rotate(" m "deg)")}}]
      [:div {:class "hand second-hand" :style {:transform (str "rotate(" s "deg)")}}]]]))

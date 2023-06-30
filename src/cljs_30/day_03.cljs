(ns cljs-30.day-03
  (:require [react :as react]))

(defn- handle-update
  ([e set-state] (handle-update e set-state ""))
  ([e set-state unit]
   (let [value (-> e .-target .-value)
         css-var-name (str "--" (-> e .-target .-name))
         css-var-value (str value unit)]
     (set-state value)
     (-> js/document
         .-documentElement
         .-style
         (.setProperty css-var-name css-var-value)))))

(defn css-variables []
  (let [[spacing set-spacing] (react/useState "10")
        [blur set-blur] (react/useState "10")
        [colour set-colour] (react/useState "#ffc600")]
    [:div [:link {:href "css/day-03/style.css" :rel "stylesheet" :type "text/css"}]
     [:h2 "Update CSS Variables with " [:span {:class "hl"} "JS"]]
     [:div.controls
      [:label {:for "spacing"} "Spacing: "]
      [:input {:id "spacing" :type "range" :name "spacing" :min "10" :max "200" :value spacing
               :on-change (fn [e] (handle-update e set-spacing "px"))
               :on-mousemove (fn [e] (handle-update e set-spacing "px"))}]

      [:label {:for "blur"} "Blur: "]
      [:input {:id "blur" :type "range" :name "blur" :min "0" :max "25" :value blur
               :on-change (fn [e] (handle-update e set-blur "px"))
               :on-mousemove (fn [e] (handle-update e set-blur "px"))}]

      [:label {:for "base"} "Base Colour: "]
      [:input {:id "base" :type "color" :name "base" :value colour
               :on-change (fn [e] (handle-update e set-colour))
               :on-mousemove (fn [e] (handle-update e set-colour))}]]
     [:img {:src "css/day-03/duck.jpg"}]]))

(ns cljs-30.day-03)

(defn css-variables []
  [:div
   [:link {:href "css/day-03/style.css"
             :rel "stylesheet"
             :type "text/css"}]
   [:h2 "Update CSS Variables with "
    [:span {:class "hl"} "JS"]]
   [:div.controls
    [:label {:for "spacing"} "Spacing: "]
    ;TODO: restore valure (rather than defaultValue) when onchange handled
    [:input {:id "spacing" :type "range" :name "spacing" :min 10 :max 200 :default-value 10 :data-sizing "px"}]

    [:label {:for "blur"} "Blur: "]
    [:input {:id "blur" :type "range" :name "blur" :min 0 :max 25 :default-value 10 :data-sizing "px"}]

    [:label {:for "base"} "Base Colour: "]
    [:input {:id "base" :type "color" :name "base" :value "#ffc600"}]]

   [:img {:src "css/day-03/duck.jpg"}]])

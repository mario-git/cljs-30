(ns cljs-30.day-05
  (:require [react :as react]))

(def ^:private data
  [{:p1 "Hey" :p2 "Let's" :p3 "Dance" :class "panel panel1"}
   {:p1 "Give" :p2 "Take" :p3 "Receive" :class "panel panel2"}
   {:p1 "Experience" :p2 "It" :p3 "Today" :class "panel panel3"}
   {:p1 "Give" :p2 "All" :p3 "You can" :class "panel panel4"}
   {:p1 "Life" :p2 "In" :p3 "Motion" :class "panel panel5"}])

(defn- click-handler [e]
  (-> e .-target .-classList (.toggle "open")))

(defn- transition-end-handler [e]
  (when (-> e .-propertyName (clojure.string/includes? "flex"))
    (-> e .-target .-classList (.toggle "open-active"))))

(defn flex-panels []
  [:div.panels
   [:link {:href "css/day-05/style.css" :rel "stylesheet" :type "text/css"}]
   [:link {:href "https://fonts.googleapis.com/css?family=Amatic+SC" :rel "stylesheet" :type "text/css"}]
   (map (fn [{:keys [p1 p2 p3 class] :as item}]
          ;TODO: create state here and move away from handlers with .-target .-blah
          ^{:key item}
          [:div {:class class :on-click #(click-handler %) :on-transition-end #(transition-end-handler %)}
           [:p p1] [:p p2] [:p p3]] ) data)])

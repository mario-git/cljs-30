(ns cljs-30.challenge-05
  (:require [react :as react]))

(def ^:private data
  [{:p1 "Hey" :p2 "Let's" :p3 "Dance" :class "panel1"}
   {:p1 "Give" :p2 "Take" :p3 "Receive" :class "panel2"}
   {:p1 "Experience" :p2 "It" :p3 "Tochallenge" :class "panel3"}
   {:p1 "Give" :p2 "All" :p3 "You can" :class "panel4"}
   {:p1 "Life" :p2 "In" :p3 "Motion" :class "panel5"}])

(defn- new-state-fn [state value]
  (if (some #{value} state)
    (filter #(not= value %) state)
    (cons value state)))

(defn- panel [{:keys [p1 p2 p3 class]}]
  (let [[state set-state] (react/useState ["panel" class])]
    [:div {:class (->> state (interpose " ") (apply str))
           :on-click #(set-state (new-state-fn state "open"))
           :on-transition-end
           #(when (-> % .-propertyName (clojure.string/includes? "flex"))
             (set-state (new-state-fn state "open-active")))}
     [:p p1] [:p p2] [:p p3]]))

(defn flex-panels []
  [:div.panels
   [:link {:href "css/challenge-05/style.css" :rel "stylesheet" :type "text/css"}]
   [:link {:href "https://fonts.googleapis.com/css?family=Amatic+SC" :rel "stylesheet" :type "text/css"}]
   (map (fn [item] ^{:key item} [:f> panel item] ) data)])

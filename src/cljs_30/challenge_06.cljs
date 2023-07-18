(ns cljs-30.challenge-06
  (:require [react :as react]))

(def ^:private endpoint "https://gist.githubusercontent.com/Miserlou/c5cd8364bf9b2420bb29/raw/2bf258763cdddd704f8ffd3ea9a3e81d25e2c6f6/cities.json")
(def ^:private cities-state (atom []))
(def ^:private fetch-promise (-> (js/fetch endpoint) (.then #(.json %))))

(defn- format-thousands [n]
  (->> n str reverse (partition 3 3 " ") (interpose ",") flatten reverse (apply str) clojure.string/trim))

(defn- cities []
  (when (empty? @cities-state)
    (.then fetch-promise #(reset! cities-state %)))
  @cities-state)

(defn- find-cities [to-find]
  (let [pattern (re-pattern (str "(?i)" to-find))]
    (filter #(or (re-find pattern (.-city %)) (re-find pattern (.-state %))) (cities))))

(defn type-ahead []
  (let [[current-filter set-current-filter] (react/useState "")]
    [:form.search-form
     [:link {:href "css/challenge-06/style.css" :rel "stylesheet" :type "text/css"}]
     [:input.search
      {:type "text" :placeholder "City or State" :auto-focus true
       :on-change #(set-current-filter (-> % .-target .-value))
       :on-key-up #(set-current-filter (-> % .-target .-value))}]
     [:ul.suggestions
      (if (empty? current-filter)
        (map identity (list [:li "Filter for a city"] [:li "or a state"]))
        (map (fn [x]
               ;;TODO: investigate behaviour with destructuring vs .-city in function signature
               (let [city (.-city x)
                     state (.-state x)
                     population (.-population x)
                     li-key (interpose "-" [city state population])]
                 ^{:key li-key}
                 [:li
                  [:span.name (str city ", " state)]
                  [:span.population (format-thousands population)]]))
             (find-cities current-filter)))]]))

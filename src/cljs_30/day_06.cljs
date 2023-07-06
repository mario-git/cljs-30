(ns cljs-30.day-06)

(def ^:private endpoint "https://gist.githubusercontent.com/Miserlou/c5cd8364bf9b2420bb29/raw/2bf258763cdddd704f8ffd3ea9a3e81d25e2c6f6/cities.json")

(defn type-ahead []
  [:form.search-form
   [:link {:href "css/day-06/style.css" :rel "stylesheet" :type "text/css"}]
   [:input.search {:type "text" :placeholder "City or State"}]
   [:ul.suggestions
    [:li "Filer for a city"]
    [:li "or a state"]]])

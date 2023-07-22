(ns cljs-30.challenge-10
  (:require [react :as react]))

(def ^:private items ["This is an inbox layout."
                        "Check one item"
                        "Hold down your Shift key"
                        "Check a lower item"
                        "Everything in between should also be set to checked"
                        "Try do it without any libraries"
                        "Just regular JavaScript"
                        "Good Luck!"
                        "Don't forget to tweet your result!"])

(defn hold-shift-check []
  (let [[state-tbd set-state-tbd] (react/useState)]
    [:div {:class "inbox"}
     [:link {:href "css/challenge-10/style.css" :rel "stylesheet" :type "text/css"}]
     (map (fn [item]
            ^{:key item}
            [:div.item
             [:input {:type "checkbox"}]
             [:p item]])
          items)]))

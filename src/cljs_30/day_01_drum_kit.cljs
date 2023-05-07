(ns cljs-30.day_01-drum-kit
  (:require-macros [hiccups.core :as hiccups :refer [html]])
  (:require [hiccups.runtime :as hiccupsrt]))

(defn to-div-element [template]
  (doto (.createElement js/document "div")
    (aset "innerHTML" template)))

(defn show []
  (to-div-element (html [:link {:href "css/day-01/style.css"
                                :rel "stylesheet"
                                :type "text/css"}]
                        [:span "WIP :)"])))

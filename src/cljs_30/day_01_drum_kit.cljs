(ns cljs-30.day_01-drum-kit
  (:require-macros [hiccups.core :as hiccups :refer [html]])
  (:require [hiccups.runtime :as hiccupsrt]))

(def html-template
  (html [:link {:href "css/day-01/style.css"
                :rel "stylesheet"
                :type "text/css"}]
        [:div {:class "keys"}
         [:div {:data-key 65 :class "key"}
          [:kbd "A"] [:span {:class "sound"} "clap"]]
         [:div {:data-key 83 :class "key"}
          [:kbd "S"] [:span {:class "sound"} "hihat"]]
         [:div {:data-key 68 :class "key"}
          [:kbd "D"] [:span {:class "sound"} "kick"]]
         [:div {:data-key 70 :class "key"}
          [:kbd "F"] [:span {:class "sound"} "openhat"]]
         [:div {:data-key 71 :class "key"}
          [:kbd "G"] [:span {:class "sound"} "boom"]]
         [:div {:data-key 72 :class "key"}
          [:kbd "H"] [:span {:class "sound"} "ride"]]
         [:div {:data-key 74 :class "key"}
          [:kbd "J"] [:span {:class "sound"} "snare"]]
         [:div {:data-key 75 :class "key"}
          [:kbd "K"] [:span {:class "sound"} "tom"]]
         [:div {:data-key 76 :class "key"}
          [:kbd "L"] [:span {:class "sound"} "tink"]]]))

(defn to-div-element [template]
  (doto (.createElement js/document "div")
    (aset "innerHTML" template)))

(defn on-key-down-handler []
  (let [elems (array-seq (.getElementsByClassName js/document "key"))]
    (js/console.log "WIP :)" (-> elems first (aget "innerHTML")))))

(defn load []
  {:layout (to-div-element html-template) :logic-fn on-key-down-handler })

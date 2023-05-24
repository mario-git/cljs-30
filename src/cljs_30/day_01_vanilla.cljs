(ns cljs-30.day_01-vanilla
  (:require-macros [hiccups.core :as hiccups :refer [html]])
  (:require [clojure.string :as s]
            [hiccups.runtime :as hiccupsrt]))

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
          [:kbd "L"] [:span {:class "sound"} "tink"]]]
        [:audio {:data-key 65 :src "sounds/clap.wav"}]
        [:audio {:data-key 83 :src "sounds/hihat.wav"}]
        [:audio {:data-key 68 :src "sounds/kick.wav"}]
        [:audio {:data-key 70 :src "sounds/openhat.wav"}]
        [:audio {:data-key 71 :src "sounds/boom.wav"}]
        [:audio {:data-key 72 :src "sounds/ride.wav"}]
        [:audio {:data-key 74 :src "sounds/snare.wav"}]
        [:audio {:data-key 75 :src "sounds/tom.wav"}]
        [:audio {:data-key 76 :src "sounds/tink.wav"}]))

(defn- to-div-element [template]
  (doto (.createElement js/document "div")
    (aset "innerHTML" template)))

(defn- key-down-event-handler [e]
  (let [key-code (-> e .-key s/upper-case (.charCodeAt 0))
        audio (.querySelector js/document (str "audio[data-key=\"" key-code "\"]"))
        key-box (.querySelector js/document (str "div[data-key=\"" key-code "\"]"))]
    (when audio
      (-> key-box .-classList (.add "playing"))
      (.play audio))))

(defn- end-transition [e]
  (when (= "transform" (.-propertyName e))
    (-> e .-target .-classList (.remove "playing"))))

(defn- play-sound-handler []
  (.addEventListener js/window "keydown" key-down-event-handler)
  (doall (map #(.addEventListener % "transitionend" end-transition) (.querySelectorAll js/document ".key"))))

(defn load []
  {:layout (to-div-element html-template)
   :logic-fn play-sound-handler})

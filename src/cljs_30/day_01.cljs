(ns cljs-30.day_01
  (:require [clojure.string :as s]
            [react :as react]))

(def data [{:letter "A" :sound "clap"}
           {:letter "S" :sound "hihat"}
           {:letter "D" :sound "kick"}
           {:letter "F" :sound "openhat"}
           {:letter "G" :sound "boom"}
           {:letter "H" :sound "ride"}
           {:letter "J" :sound "snare"}
           {:letter "K" :sound "tom"}
           {:letter "L" :sound "tink"}])

; TODO: this will go
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

(defn- play-sound-handler [set-state-fn]
  (.addEventListener js/window "keydown" #(set-state-fn (-> % .-key s/upper-case) js/undefined))
  #_(doall (map #(.addEventListener % "transitionend" end-transition) (.querySelectorAll js/document ".key"))))

(defn drum-kit []
  (let [[state set-state-fn] (react/useState "")]
    (react/useEffect (fn [] (play-sound-handler set-state-fn)))
    [:div {:class "keys"}
     [:link {:href "css/day-01/style.css"
             :rel "stylesheet"
             :type "text/css"}]
     (map (fn [{:keys [letter sound] :as item}]
            ^{:key item}
            [:div {:data-key letter :class (cond-> "key" (= letter state) (str " playing"))}
             [:kbd letter] [:span {:class "sound"} sound]
             [:audio {:data-key letter :src (str "sounds/" sound ".wav")
                      :ref (fn [audio] (when (= letter state) (.play audio)))}]]) data)]))

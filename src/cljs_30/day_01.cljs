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
  (js/console.log "test")
  #_(.addEventListener js/window "keydown" key-down-event-handler)
  #_(doall (map #(.addEventListener % "transitionend" end-transition) (.querySelectorAll js/document ".key"))))

(defn drum-kit []
  #_(play-sound-handler)
  [:div {:class "keys" :on-key-down #(js/console.log "daje")}
   [:link {:href "css/day-01/style.css"
           :rel "stylesheet"
           :type "text/css"}]
   (map (fn [{:keys [letter sound] :as item}]
          ^{:key item}
          [:div {:data-key letter :class "key"}
           [:kbd letter] [:span {:class "sound"} sound]
           [:audio {:data-key letter :src (str "sounds/" sound ".wav")}]]) data)])

(ns cljs-30.challenge-01
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

(defn drum-kit []
  (let [[state set-state-fn] (react/useState "")]
    (react/useEffect (fn [] (.addEventListener js/window "keydown" #(set-state-fn (-> % .-key s/upper-case) js/undefined))))
    [:div {:class "keys"}
     [:link {:href "css/challenge-01/style.css"
             :rel "stylesheet"
             :type "text/css"}]
     (map (fn [{:keys [letter sound]}]
            (let [current? (= letter state)]
             ^{:key letter}
             [:div {:data-key letter :class (cond-> "key" current? (str " playing"))
                    :on-transition-end (fn [e]
                                         (when (and current? (= "transform" (.-propertyName e)))
                                           (set-state-fn "")))}
              [:kbd letter] [:span {:class "sound"} sound]
              [:audio {:data-key letter :src (str "sounds/" sound ".wav")
                       :ref (fn [audio] (when (and current? (some? audio))
                                          (set! (.-currentTime audio) 0)
                                          (.play audio)))}]])) data)]))

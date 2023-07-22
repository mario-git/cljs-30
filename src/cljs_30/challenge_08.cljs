(ns cljs-30.challenge-08
  (:require [react :as react]))

(defn fun-with-canvas []
  (let [[is-drawing? set-is-drawing] (react/useState false)
        [last-x set-last-x] (react/useState 0)
        [last-y set-last-y] (react/useState 0)
        [hue set-hue] (react/useState 0)
        [increasing? set-increasing] (react/useState false)
        ctx-ref (react/useRef)
        draw (fn [e]
               (let [ctx (.-current ctx-ref)
                     curr-x (-> e .-nativeEvent .-offsetX)
                     curr-y (-> e .-nativeEvent .-offsetY)]
                 (set! (.-strokeStyle ctx) (str "hsl(" hue ", 100%, 50%)"))
                 (.beginPath ctx)
                 (.moveTo ctx last-x last-y)
                 (.lineTo ctx curr-x curr-y)
                 (.stroke ctx)
                 (set-last-x curr-x)
                 (set-last-y curr-y)
                 (set-hue (if (>= hue 360) 0 (inc hue)))
                 (set! (.-lineWidth ctx) ((if increasing? inc dec) (.-lineWidth ctx)))
                 (when (> (.-lineWidth ctx) 100) (set-increasing false))
                 (when (<= (.-lineWidth ctx) 1) (set-increasing true))))]
    [:canvas
     {:on-mouse-down
      (fn [e]
        (set-is-drawing true)
        (set-last-x (-> e .-nativeEvent .-offsetX))
        (set-last-y (-> e .-nativeEvent .-offsetY)))
      :on-mouse-move #(when is-drawing? (draw %))
      :on-mouse-up #(set-is-drawing false)
      :on-mouse-out #(set-is-drawing false)
      :ref
      (fn [canvas]
        (when (and (not (.-current ctx-ref)) (some? canvas))
          (set! (.-width canvas) (.-innerWidth js/window))
          (set! (.-height canvas) (.-innerHeight js/window))
          (let [ctx (.getContext canvas "2d")]
            (set! (.-strokeStyle ctx) "#BADA55")
            (set! (.-lineJoin ctx) "round")
            (set! (.-lineCap ctx) "round")
            (set! (.-lineWidth ctx) 100)
            (set! (.-current ctx-ref) ctx))))}]))

(ns cljs-30.challenge-08
  (:require [react :as react]))

(defn fun-with-canvas []
  (let [[is-drawing? set-is-drawing] (react/useState false)
        [last-x set-last-x] (react/useState 0)
        [last-y set-last-y] (react/useState 0)
        [hue set-hue] (react/useState 0)
        [direction? set-direction] (react/useState true)
        ctx-ref (react/useRef)
        draw (fn [e]
               (let [ctx (.-current ctx-ref)
                     _ (js/console.log "uuu" ctx)
                     line-width (.-lineWidth ctx)
                     curr-x (-> e .-nativeEvent .-offsetX)
                     curr-y (-> e .-nativeEvent .-offsetY)]
                 ;TODO:
                 ;- fix size brush
                 ;- fix brush position
                 ;- tune strokeStyle here
                 (set! (.-strokeStyle ctx) (str "`hsl(" hue ", 100%, 50%)`"))
                 ;; (set! (.-strokeStyle ctx) "yellow")
                 (.beginPath ctx)
                 (.moveTo ctx last-x last-y)
                 (.lineTo ctx curr-x curr-y)
                 (.stroke ctx)
                 (set-last-x curr-x)
                 (set-last-y curr-y)
                 (js/console.log "666" line-width hue)
                 (set-hue (if (>= hue 360) 0 (inc hue)))
                 (when (or (>= line-width 100) (<= line-width 1))
                     (set-direction (not direction?)))
                 (set! (.-lineWidth ctx) ((if direction? inc dec) line-width))
                 ))]
    [:canvas {:style {:width (.-innerWidth js/window) :height (.-innerHeight js/window)}
              :on-mouse-down (fn [e]
                               (set-is-drawing true)
                               (set-last-x (-> e .-nativeEvent .-offsetX))
                               (set-last-y (-> e .-nativeEvent .-offsetY)))
              :on-mouse-move #(when is-drawing? (draw %))
              :on-mouse-up #(set-is-drawing false)
              :on-mouse-out #(set-is-drawing false)
              :ref (fn [canvas]
                     ;; TODO check if we need this
                     (when (and (not (.-current ctx-ref)) (some? canvas))
                       (js/console.log "GOCIIAT")
                       (let [ctx (.getContext canvas "2d")]
                         (set! (.-strokeStyle ctx) "#BADA55")
                         (set! (.-lineJoin ctx) "round")
                         (set! (.-lineCap ctx) "round")
                         (set! (.-lineWidth ctx) 100)
                         (set! (.-current ctx-ref) ctx))))}]))

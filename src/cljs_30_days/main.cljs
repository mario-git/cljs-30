(ns ^:figwheel-hooks cljs-30-days.main)

(defonce app-state (atom {:text "Hello world!"}))

(defn new-label []
  (doto (.createElement js/document "label")
    (aset "innerHTML" (js/Date.))))

(defn root []
  (.getElementById js/document "app"))

(defn render [root & components]
  (doseq [c components] (.append root c)))

(defn mount
  []
  (aset (root) "innerHTML" "")
  (render (root) (new-label) (.createElement js/document "br") (new-label)))

;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  (mount)
  ;; optionally touch your app-state to force rerendering
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(defonce start-up (do (mount) true))

(ns hoplon-proj.main
  (:require
    [hoplon-proj.view :as view]
    [hoplon.dom]))

(defn mount-components []
  (.replaceChildren (.getElementById js/document "app") (view/cuerpo)))

(defn start []
  (mount-components)
  (js/console.log "Starting..."))

(defn stop []
  (js/console.log "Stopping..."))

(defn init []
  (js/console.log "Initializing...")
  (start))

(ns hoplon-proj.view
  (:require
    [hoplon.core :as h]
    [javelin.core :as j]))

(j/defc hello-message "Hello Hoplon!")

(defn hello []
  (h/div
    (h/h1 hello-message)
    (h/button :click #(swap! hello-message str "!")
      "More !")))

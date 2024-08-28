(ns hoplon-proj.view
  (:require
    [hoplon.core :as h]
    [javelin.core :as j]))

(j/defc hello-message "LISP rules!")
(j/defc= message-char-count (count hello-message))
(j/defc= color (if (even? message-char-count) "blue" "red"))
(j/defc input "")

(j/defc history [])
(add-watch hello-message :history #(swap! history conj %3))
(j/cell= (js/console.log history))

(defn hello []
  (h/div
   (h/label "Ingrese su nombre")
   (h/br)
   (h/input :change (fn [e]  
                      (reset! input (-> e .-target .-value))
                      (js/console.log @input)))
    (h/h1 :mouseover #(swap! hello-message str "?") hello-message)
    (h/button :click #(swap! hello-message str "!")
      "More !")
   (h/p :style (j/cell= (str "color:" color))
        (h/text "The message has ~{message-char-count} chars."))
   (h/ul
    (h/loop-tpl :bindings [h history]
                (h/li (j/cell= (str "Message was: " h)))))))


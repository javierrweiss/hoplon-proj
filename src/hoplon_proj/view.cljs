(ns hoplon-proj.view
  (:require
    [hoplon.core :as h]
    [javelin.core :as j]))

(h/defelem formulario 
  [atributos hijos]
  (h/form atributos
          (h/div {:class "form-container"}
           (h/for-tpl [hijo hijos]
                      (h/div hijo)))))


(j/defc paciente-actual {:nombre "Pedro Martínez"
                         :historia-clinica 1452789
                         :historia-clinica-unica 75241
                         :cama "B"})

(defn paciente
  []
  (h/section {:id "seccion-paciente"}
             (h/img :src "img/paciente.png")
             (h/div {:class "paciente"}
                    (h/div
                     (h/label "Nombre: ") (h/span (:nombre @paciente-actual)))
                    (h/div
                     (h/label "HC: ") (h/span (:historia-clinica @paciente-actual)))
                    (h/div
                     (h/label "HCU: ") (h/span (:historia-clinica-unica @paciente-actual)))
                    (h/div
                     (h/label "Cama: ") (h/span (:cama @paciente-actual))))))

(defn encabezado
  []
  (h/header {:id "head"}
            (h/div {:class "cabecera"}
                   (h/div
                    (h/img :src "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fyt3.googleusercontent.com%2Fytc%2FAL5GRJXfvXFRc9d9H2QoRYqNlLGTR25uqJfrNdR3KMgN%3Ds900-c-k-c0x00ffffff-no-rj&f=1&nofb=1&ipt=9d6306a1f0cc649ab2d0cd28ffbf84f58d7bab0fe5465308bc50ec28adc3afb2&ipo=images"))
                   (h/div
                    (h/h1 "FICHA ANESTÉSICA")) 
                   (h/div {:class "menu"}
                          (h/div {:class "menu-items"}
                                 (h/a "💉 Grilla"))
                          (h/div {:class "menu-items"}
                                 (h/a "💉 Medicamentos"))))))
 
(j/defc patologias {})

(defn ingreso-patologias
  []
  (formulario {:id "patologias"} 
              (h/div {:class "patologia-reglon"}
                     (h/label "Patología actual"))
              (h/div {:class "patologia-reglon"}
                     (h/input
                      :type "text"
                      :value (:patologia-actual @patologias)
                      :change #(swap! patologias assoc :patologia-actual (-> % .-target .-value))))
              (h/div {:class "patologia-reglon"}
                     (h/label "Diagnostico"))
              (h/div {:class "patologia-reglon"}
                     (h/input 
                      :type "text"
                      :value (:diagnostico @patologias)
                      :change #(swap! patologias assoc :diagnostico (-> % .-target .-value))))
              (h/div {:class "patologia-reglon"}
                     (h/label "Operacion propuesta"))
              (h/div {:class "patologia-reglon"}
                     (h/input 
                      :type "text"
                      :value (:operacion-propuesta @patologias)
                      :change #(swap! patologias assoc :operacion-propuesta (-> % .-target .-value))))
              (h/div {:class "patologia-reglon"}
                     (h/label "Operacion realizada"))
              (h/div {:class "patologia-reglon"}
                     (h/input 
                      :type "text"
                      :value (:operacion-realizada @patologias)
                      :change #(swap! patologias assoc :operacion-realizada (-> % .-target .-value))))))

(j/defc medicos {})

(defn ingreso-personal-medico
  []
  (formulario {:id "personal-medico"} 
              (h/div 
               (h/label "Cirujano"))
              (h/div
               (h/input
                :type "text"
                :value (:cirujano @medicos)
                :change #(swap! medicos assoc :cirujano (-> % .-target .-value))))
              (h/div 
               (h/label "Anestesiólogo"))
              (h/div
               (h/input
                :type "text"
                :value (:anestesiologo @medicos)
                :change #(swap! medicos assoc :anestesiologo (-> % .-target .-value))))
              (h/div
               (h/label "Auxiliar"))
              (h/div
               (h/input
                :type "text"
                :value (:auxiliar @medicos)
                :change #(swap! medicos assoc :auxiliar (-> % .-target .-value))))
              (h/div
               (h/label "Ayudante"))
              (h/div
               (h/input
                :type "text"
                :value (:ayudante @medicos)
                :change #(swap! medicos assoc :ayudante (-> % .-target .-value))))))

(j/defc signos-vitales {})

(defn signos-vitales-paciente
  []
  )



(h/defelem ficha-anestesica [_ elems]
  (h/div {:class "grilla-ficha-anestesica"}
         (h/for-tpl [elem elems]
                    (h/div elem))))

(h/defelem cuerpo [_ _] 
 (h/main
  (encabezado)
  (paciente)
  (ficha-anestesica 
   (ingreso-patologias)
   (ingreso-personal-medico))))


;;;; DEBUG ;;;
  
  (j/defc history [])
  
  (defn debug
    [component]
    (add-watch component :history #(swap! history conj %3))
    (j/cell= (js/console.log history)))
  

  (debug patologias)
  
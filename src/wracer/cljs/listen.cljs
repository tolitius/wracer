(ns wracer.listen
  (:require [wracer :refer [race]]
            [dommy.core :as dom])
  (:require-macros [dommy.macros :refer [sel1]]))

(def enter-key 13)
(def $q-input (sel1 :.q-in))

(dom/listen! $q-input
             :keydown (fn [e] 
                        (if (= (.-keyCode e) enter-key) 
                          (race (dom/value $q-input)))))

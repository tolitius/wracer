(ns wracer.render
  (:require [wracer.tools :refer [info]]
            [clojure.string :refer [escape]]
            [dommy.core :as dom])
  (:require-macros
    [dommy.macros :refer [sel1]]))

(def enter-key 13)
(def host (.-origin (.-location js/window)))

(def $alert (sel1 :.alert))

(defn html-to-src [html]
  (str "data:text/html;charset=utf-8," (escape html nil)))

(defn render [el html]
    (dom/set-attr!  (sel1 el)
                    :src (html-to-src html)))

(defn show [els html]
  (doall (map #(render % html) 
              els)))

(defn searching [els]
  (show els (str "<img src=\"" host "/img/searching.gif\"/>")))

(defn clear [els]
  (show els ""))

(defn alert [& msg]
  (dom/set-html! $alert (apply str msg)))

(defn cleanup []
  (let [els [:.yahoo :.google :.bing]]
    (clear els)
    (alert "")
    ;; clear e-names
    ))

(defn winner [el html took-ms]
  (cleanup)
  (alert (subs (name el) 1) " won with time: " took-ms "ms")
  ;; TODO: clean up e-names, turn the winner on OR the timeout
  (render el html))

(defn show-timeout [ms]
  (cleanup)
  (alert "timed out. e.g. took longer that " ms "ms"))

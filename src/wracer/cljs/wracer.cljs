(ns wracer
  (:require [wracer.render :refer [searching render winner show-timeout]]
            [wracer.tools :refer [info]]
            [clojure.browser.repl]
            [goog.net.XhrIo :as xhr]
            [cljs.core.async :as async :refer [chan timeout <!]])
  (:require-macros
    [cljs.core.async.macros :refer [go alt!]]))

(def timeout-ms 1000)

(defn now [] (.now js/Date))
(defn took [start] (- (now) start))

(defn request [method url]
  (let [ch (chan)]
    (xhr/send url
              (fn [event]
                (let [res (-> event .-target .getResponseText)]
                  (go (>! ch res)))) ;; close! ?
              method)
    ch))

(defn GET [url]
  (request "GET" url))

(defn race [q]
  (searching [:.yahoo :.google :.bing])
  (let [t (timeout timeout-ms)
        start (now)]
    (go
      (alt! 
        (GET (str "/yahoo?q=" q))  ([v] (winner :.yahoo v (took start)))
        (GET (str "/bing?q=" q))   ([v] (winner :.bing v (took start)))
        (GET (str "/google?q=" q)) ([v] (winner :.google v (took start)))
        t                          ([v] (show-timeout timeout-ms))))))







;; (not in use) playground:

#_(defn search-all [q]
  (searching [:.yahoo :.google :.bing])
  (go
    (render :.google (<! (GET (str "/google?q=" q))))
    (render :.yahoo (<! (GET (str "/yahoo?q=" q))))
    (render :.bing (<! (GET (str "/bing?q=" q))))))

#_(defn cors-request 
  "CORS in action.."
  [method url]
  (let [xhr (js/XMLHttpRequest.)
        ch (chan)]
    (.open xhr method url true)
    (when xhr
      (set! (.-onload xhr) (fn []
                             (go (>! ch (.-responseText xhr)))))
      (set! (.-withCredentials xhr) true)
      (.send xhr))
    ch))


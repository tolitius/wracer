(ns wracer.handler
  (:require [wracer.layout :refer [say]]
            [clj-http.client :as http]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes

  (GET "/" [] (say "I am ready to be created!"))
 
  (GET "/google" [q] 
       (-> (http/get (str "https://www.google.com/search?q=" q)) :body))

  (GET "/bing" [q] 
       (-> (http/get (str "https://www.bing.com/search?q=" q)) :body))

  (GET "/yahoo" [q] 
       (-> (http/get (str "https://search.yahoo.com/search?p=" q)) :body))

  (route/resources "/")
  (route/not-found "not found"))

(def app
  (handler/site app-routes))

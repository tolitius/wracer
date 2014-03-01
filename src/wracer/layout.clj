(ns wracer.layout
  (:require
    [hiccup
      [page :refer [html5 include-js include-css]]]))

(defn with-css []
  (list
   (include-css "/css/wracer.css")))

(defn with-js []
  (list
   (include-js "/js/wracer.js")))

(defn say [content]
  (html5
    [:head
      [:title "wracer"]
      [:meta {"name" "viewport" "content" "width=device-width, initial-scale=1.0"}]
      (with-css)]
    [:body
     [:div.container
      [:div.alert]
      [:div.question
       [:input.q-in {:type "text" :placeholder "ask and race"}]]
      [:div.e-names
       [:p.e-name "Bing"]
       [:p.e-name "Google"]
       [:p.e-name "Yahoo"]]
      [:div.engines
       [:iframe.bing]
       [:iframe.google]
       [:iframe.yahoo]]]
      (with-js)]))

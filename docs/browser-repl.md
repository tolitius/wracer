##How do I connect wracer clojurescript repl to the browser?

###start the app with a browser repl attached to it

```clojure
(require '[wracer.brepl :refer [with-repl]])
(with-repl)
```

right after this is done, refresh/go to the web app in a browser, this will have it connect to this repl

###accessing dom elements via repl

bring in dommy (or your favorite clojurescript dom manipulation lib)

```clojure
(ns wracer.repl
  (:require [dommy.core :as dom])
  (:require-macros [dommy.macros :refer [sel1]]))
```

do the magic as usual

```clojure
wracer.repl=> (def $alert (sel1 :.alert))
#<[object HTMLDivElement]>

wracer.repl=> (dom/set-html! $alert "hi from repl")
#<[object HTMLDivElement]>
```

##connecting clojurescript repl to the browser

all the below are entered into a `wracer` repl (start `lein repl` before typing).

###start the app with a browser repl attached to it

```clojure
(require '[wracer.brepl :refer [with-repl]])
(with-repl)
```

it will start the app (e.g. no need to `lein ring server` in a separate terminal), and will host the repl

Right after this is done, refresh/go to the web app in a browser, this will have it connect to this hosted repl

###accessing DOM elements via repl

Bring in dommy (or your favorite clojurescript dom manipulation lib):

```clojure
(ns wracer.repl
  (:require [dommy.core :as dom])
  (:require-macros [dommy.macros :refer [sel1]]))
```

do the magic as usual:

```clojure
wracer.repl=> (def $alert (sel1 :.alert))
#<[object HTMLDivElement]>

wracer.repl=> (dom/set-html! $alert "hi from repl")
#<[object HTMLDivElement]>
```

##Connecting ClojureScript REPL to the browser

all here is entered into a `wracer` repl (e.g. start `lein repl` before typing)

###Start the app with a browser REPL attached to it

```clojure
(require '[wracer.brepl :refer [with-repl]])
(with-repl)
```

it will start the app (e.g. no need to `lein ring server` in a separate terminal), and will host the REPL

Right after it's done, _refresh/go to_ the web app in a browser, this will have it connect to this hosted REPL

###Accessing DOM elements via REPL

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

<p align="center">
  <img src="https://github.com/tolitius/wracer/raw/master/docs/hi-from-repl.png" alt="connecting browser to REPL"/>
</p>

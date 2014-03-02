# wracer

Visualizes one of the coolest [core.async](https://github.com/clojure/core.async) 
features: [alt!](http://clojure.github.io/core.async/#clojure.core.async/alt!), 
by "selecting" on several HTTP GET and a timeout channels, and rendering the winner's stats and HTML response.

HTTP GETs are also async, and merely put their responses on a channel, which they then return.

## show me

Here is the race ([clojurescript](https://github.com/tolitius/wracer/blob/master/src/wracer/cljs/wracer.cljs)):

```clojure
(defn race [q]
  (let [t (timeout timeout-ms)
        start (now)]
    (go
      (alt! 
        (GET (str "/yahoo?q=" q))  ([v] (winner :.yahoo v (took start)))
        (GET (str "/bing?q=" q))   ([v] (winner :.bing v (took start)))
        (GET (str "/google?q=" q)) ([v] (winner :.google v (took start)))
        t                          ([v] (show-timeout timeout-ms))))))
```

Once a winner is determined or a timeout channel returns nil ("times out"), the response is rendered:

<p align="center">
  <img src="https://github.com/tolitius/wracer/raw/master/docs/wracer-in-action.png" alt="wracer in action"/>
</p>

It of course has nothing to do with real world benchmarking of any kind, but it does serve as an interactive visual of "alt!" doing its magic.

## usage / start the app

```
lein ring server
```

## license

copyright © 2014 tolitius

distributed under the Eclipse Public License, the same as Clojure.

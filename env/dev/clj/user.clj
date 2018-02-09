(ns user
  (:require 
            [mount.core :as mount]
            [siilievents.figwheel :refer [start-fw stop-fw cljs]]
            [siilievents.core :refer [start-app]]))

(defn start []
  (mount/start-without #'siilievents.core/repl-server))

(defn stop []
  (mount/stop-except #'siilievents.core/repl-server))

(defn restart []
  (stop)
  (start))



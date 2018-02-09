(ns siilievents.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[siilievents started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[siilievents has shut down successfully]=-"))
   :middleware identity})

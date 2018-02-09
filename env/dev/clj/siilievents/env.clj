(ns siilievents.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [siilievents.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[siilievents started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[siilievents has shut down successfully]=-"))
   :middleware wrap-dev})

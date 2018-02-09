(ns siilievents.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [siilievents.layout :refer [error-page]]
            [siilievents.routes.home :refer [home-routes]]
            [compojure.route :as route]
            [siilievents.env :refer [defaults]]
            [mount.core :as mount]
            [siilievents.middleware :as middleware]))

(mount/defstate init-app
  :start ((or (:init defaults) identity))
  :stop  ((or (:stop defaults) identity)))

(mount/defstate app
  :start
  (middleware/wrap-base
    (routes
      (-> #'home-routes
          (wrap-routes middleware/wrap-csrf)
          (wrap-routes middleware/wrap-formats))
      (route/not-found
        (:body
          (error-page {:status 404
                       :title "page not found"}))))))

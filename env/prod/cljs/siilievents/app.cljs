(ns siilievents.app
  (:require [siilievents.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)

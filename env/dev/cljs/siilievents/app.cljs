(ns ^:figwheel-no-load siilievents.app
  (:require [siilievents.core :as core]
            [devtools.core :as devtools]))

(enable-console-print!)

(devtools/install!)

(core/init!)

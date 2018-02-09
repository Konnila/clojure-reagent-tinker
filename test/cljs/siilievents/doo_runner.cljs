(ns siilievents.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [siilievents.core-test]))

(doo-tests 'siilievents.core-test)


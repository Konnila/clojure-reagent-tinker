(ns siilievents.core
  (:require [reagent.core :as r]
            [reagent.session :as session]
            [secretary.core :as secretary :include-macros true]
            [goog.events :as events]
            [goog.history.EventType :as HistoryEventType]
            [markdown.core :refer [md->html]]
            [siilievents.ajax :refer [load-interceptors!]]
            [ajax.core :refer [GET POST]])
  (:import goog.History))

(def state 
  (r/atom {:events [{:eventid "ClojuTRE"
                     :eventdate "01/03/1740"
                     :placeid "Tampere"
                     :description "Lorem ipsum dolor sit amet"}
                     {:eventid "Moi"
                     :eventdate "01/03/1740"
                     :placeid "Helsinki"
                     :description "Heavy metal"}]
           :inputs {:eventid ""
                    :eventdate ""
                    :placeid ""}}))

(defn set-input-value! [id value]
  (swap! state assoc-in [:inputs id] value))       

(defn get-input-value [id]
  (get-in @state [:inputs id]))

(defn save-event [old-state]
  (assoc old-state :events (conj (:events old-state) (:inputs old-state))))
  
(defn add-event []
  (swap! state save-event ))


(defn input [label id type]
  [:label label
     [:input {:id id
              :value (get-input-value id)
              :type type
              :on-change #(set-input-value! id (-> % .-target .-value))}]])

(defn loggaa [stuff]
  (.log js/console stuff))

(defn textarea [label]
  [:label label
    [:textarea {}]])

(defn button [value on-click]
  [:button {:type "submit"
            :on-click on-click}
   value])

(defn event-listing []
  (let [eventdata (:events @state)]
    [:div.container
      [:ul
        (for [x eventdata]
          [:li (:eventid x)])]]))

(defn home-page []
  [:div.container
    (input "Event name" :eventid "text")
    (input "date" :eventdate "date")
    (input "place" :placeid "text")
    (textarea "Description")
    (button "Add" #(add-event))
    (event-listing)])

(def pages
  {:home #'home-page})

(defn page []
  [(pages (session/get :page))])

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

(secretary/defroute "/" []
  (session/put! :page :home))

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
        (events/listen
          HistoryEventType/NAVIGATE
          (fn [event]
              (secretary/dispatch! (.-token event))))
        (.setEnabled true)))

;; -------------------------
;; Initialize app
(defn mount-components []
  (r/render [#'page] (.getElementById js/document "app")))

(defn init! []
  (load-interceptors!)
  (hook-browser-navigation!)
  (mount-components))

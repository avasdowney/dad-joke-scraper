(ns reddit-scraper.core
  (:require [net.cgrand.enlive-html :as html]
            [clj-http.client :as client]))

;; We need a User-Agent to pretend we're a browser
(def h {"User-Agent" "Mozilla/5.0 (Windows NT 6.1;) Gecko/20100101 Firefox/13.0.1"})

;; pulls from java.net.url class to retrieve html of a url...
;; not needed as fetch-json used instead
(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))

;; sets url to pull from
(def ^:dynamic *dad-jokes-url* "https://www.reddit.com/r/dadjokes/hot.json")

;;;; ======================================================
;;;; BROKEN DOWN PROCESS OF GENERAL RETRIEVAL OF DAD JOKES
;;;; ======================================================

;; pull structured json file directly from reddit url
(defn fetch-json [url]
  (:body (client/get url {:headers h :as :json-strict})))

;; fetches all of the dad jokes, returns a list of the raw jokes
(defn dad-jokes-raw []
  (:children (:data (fetch-json *dad-jokes-url*))))

;; pulls all data from child array into a list
(defn dad-jokes-data []
  (map #(:data %) (dad-jokes-raw)))

;; given the raw data, pull out just what we want
(defn select-joke [data]
  (select-keys data [:title :selftext :author]))

;; show all of the jokes with just what want
(defn dad-jokes []
  (map #(select-joke %) (dad-jokes-data)))

;; retrieves a random joke from the jokes listed
(defn get-random-joke []
  (nth (dad-jokes) (rand-int 25)))

;;;; ================================
;;;; PARSE JOKES INTO SEPERATE PARTS
;;;; ================================

;; split joke into seperate parts
(defn split-joke []
  (def joke (get-random-joke))
  (def title (select-keys joke [:title]))
  (def selftext (select-keys joke [:selftext]))
  (def author (select-keys joke [:author]))
  (println title)
  (println selftext)
  (println author))
(ns advent-of-code.day13
  (:require [clojure.string :as str]))

(defn parse
  [input]
  (let [split-input (str/split-lines input)
        timestamp (Integer. (first split-input))
        bus-id-strings (str/split (second split-input) #",")
        bus-ids-enumerated (map vector (range) bus-id-strings)
        bus-ids-filtered (filter #(not= "x" (second %)) bus-ids-enumerated)
        bus-ids (map #(Integer. (second %)) bus-ids-filtered)
        indices (map first bus-ids-filtered)]
    [timestamp bus-ids indices]))

(defn departure-by-id
  "Calculate time to next departure for a bus id."
  [timestamp id]
  (- id (mod timestamp id)))

(defn all-departures-by-id
  "Calculate pairs containing id and time to that id's next departure."
  [timestamp ids]
  (let [departures (map #(departure-by-id timestamp %) ids)]
    (map vector ids departures)))

(defn part1
  [input-filename]
  (let [[timestamp bus-ids _] (parse (slurp input-filename))
        departures-by-id (all-departures-by-id timestamp bus-ids)]
    (reduce * (apply min-key second departures-by-id))))

(def test-input "939\n7,13,x,x,59,x,31,19")
(let [[timestamp ids indices] (parse test-input)]
  [timestamp ids indices])


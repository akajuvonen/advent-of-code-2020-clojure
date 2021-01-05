(ns advent-of-code.day15)

(defn- init-numbers-map
  [initial-numbers]
  (into {}
        (map vector initial-numbers (range 1 (inc (count initial-numbers))))))

(defn memory-game
  [initial-numbers n]
  (loop [numbers (init-numbers-map initial-numbers)
         turn (count initial-numbers)
         last-number 6]
    (if (>= turn n)
      last-number
      (if-let [last-spoken (get numbers last-number)]
        (let [turns-apart (- turn last-spoken)]
          (recur (assoc numbers last-number turn)
                 (inc turn)
                 turns-apart))
        (recur (assoc numbers last-number turn)
               (inc turn)
               0)))))

(comment
  (let [initial '(0 3 6)]
    (memory-game initial 10)))

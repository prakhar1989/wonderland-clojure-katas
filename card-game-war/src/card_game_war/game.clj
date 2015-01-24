(ns card-game-war.game
  (:gen-class))

(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards (for [suit suits rank ranks] [suit rank]))
(def card-value {:jack 11 :queen 12 :king 13 :ace 14
                 :heart 4 :diamond 3 :club 2 :spade 1})

(defn play-round [card1 card2]
  "returns true if card1 is higher than card2. false otherwise"
  (let [[s1 r1] card1 
        [s2 r2] card2
        v1 (if (keyword? r1) (r1 card-value) r1)
        v2 (if (keyword? r2) (r2 card-value) r2)]
    (if (= r1 r2) 
      (> (s1 card-value) (s2 card-value))
      (> v1 v2))))

(defn deal [cards]
  "shuffles the card amongst 2 players"
  (apply map vector (partition 2 (shuffle cards))))

(defn play-game [[player1-cards player2-cards]]
  "returns true if player1 wins, false if player2 wins"
  (let [card1 (first player1-cards)
        card2 (first player2-cards)]
    (cond
      (nil? card1) false
      (nil? card2) true
      (play-round card1 card2) ;; p1 wins round
        (play-game [(conj (rest player1-cards) card1 card2) 
                    (rest player2-cards)])
      :else ;; p2 wins round
        (play-game [(rest player1-cards) 
                    (conj (rest player2-cards) card1 card2)]))))

(play-game (deal cards))


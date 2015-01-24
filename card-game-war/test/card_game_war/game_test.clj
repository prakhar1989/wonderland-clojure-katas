(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))

(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (play-round [:spade 10] [:spade 2])))

  (testing "the highest rank wins the cards in the round"
    (is (not (play-round [:spade 2] [:spade 8]))))

  (testing "queens are higer rank than jacks"
    (is (play-round [:club :queen] [:club :jack])))

  (testing "kings are higer rank than queens"
    (is (play-round [:heart :king] [:heart :queen])))

  (testing "aces are higer rank than kings"
    (is (play-round [:spade :ace] [:spade :king])))

  (testing "if the ranks are equal, clubs beat spades"
    (is (play-round [4 :club] [4 :spade])))

  (testing "if the ranks are equal, diamonds beat clubs"
    (is (play-round [3 :diamond] [3 :club])))
  
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (play-round [5 :heart] [5 :diamond]))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (let [player1-cards [[:heart 4] [:club 2]]
          player2-cards [[:club 5] [:diamond 7]]]
      (is (play-game [player2-cards player1-cards]))
      (is (not (play-game [player1-cards player2-cards]))))
    (let [player1-cards [[:heart 4] [:club 2]]
          player2-cards [[:heart 2] [:club 7]]]
      (is (play-game [player2-cards player1-cards]))
      (is (not (play-game [player1-cards player2-cards]))))))


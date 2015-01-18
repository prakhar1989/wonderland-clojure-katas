(ns alphabet-cipher.coder)

(def alphabets (seq "abcdefghijklmnopqrstuvwxyz"))

(defn repeat-string [string n]
  "repeats a string n times"
  (loop [xs string ys [] i n]
    (cond
      (zero? i) (apply str ys)
      (empty? xs) (recur string ys i)
      :else (recur (rest xs) 
                   (conj ys (first xs)) 
                   (dec i)))))

(defn rotate-string [string n]
  "rotates a string n times to the right"
  (let [len (count string)
        [xs ys] (split-at (mod n len) string)]
    (apply str (concat ys xs))))

(defn get-encoded-char [c x]
  "return encoded char for char c and cipher char x"
  (let [i (.indexOf alphabets c)
        j (.indexOf alphabets x)
        rot (rotate-string alphabets i)]
    (first (rotate-string rot j))))

(defn get-decoded-char [c x]
  "return decoded char for char c and cipher char x"
  (let [i (.indexOf alphabets x)
        rot (rotate-string alphabets i)
        j (.indexOf (seq rot) c)]
    (nth alphabets j)))

(defn encode [secret msg]
  (let [cipher (repeat-string secret (count msg))]
    (apply str (map get-encoded-char msg cipher))))

(defn decode [secret msg]
  (let [cipher (repeat-string secret (count msg))]
    (apply str (map get-decoded-char msg cipher))))

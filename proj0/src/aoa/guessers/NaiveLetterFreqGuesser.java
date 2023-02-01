package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        Map<Character, Integer> freq = new TreeMap<>();
        for (String word: words) {
            for (int i = 0; i < word.length(); i++) {
                if (!freq.containsKey(word.charAt(i))) {
                    freq.put(word.charAt(i), 1);
                } else {
                    freq.put(word.charAt(i), freq.get(word.charAt(i)) + 1);
                }
            }
        }
        return freq;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        Map<Character, Integer> freq = this.getFrequencyMap();
        int curFreq = 0;
        char curChar = '?';
        for(char letter: freq.keySet()){
            if (!guesses.contains(letter) && freq.get(letter)>curFreq){
                curFreq = freq.get(letter);
                curChar = letter;
            }
        }
        if (curFreq==0){
            return '?';
        }
        return curChar;
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}

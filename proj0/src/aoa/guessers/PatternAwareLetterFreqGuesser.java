package aoa.guessers;

import aoa.utils.FileUtils;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {

        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        List <String> tyler = new ArrayList<>();
        for (String word: words){
            int botega = 0;
            if (pattern.length()==word.length()){
                botega = 1;
                List <Character> letters = new ArrayList<>();
                for(int i = 0; i<pattern.length(); i++){
                    if (pattern.charAt(i)!='-'){
                        if (pattern.charAt(i)!=word.charAt(i)) {
                            botega = 0;
                        }
                        letters.add(pattern.charAt(i));
                    }
                }

            }
            if (botega==1) {
                tyler.add(word);
            }
        }
        Map<Character, Integer> freq = new TreeMap<>();
        for (String word: tyler) {
            for (int i = 0; i < word.length(); i++) {
                if (!freq.containsKey(word.charAt(i))) {
                    freq.put(word.charAt(i), 1);
                } else {
                    freq.put(word.charAt(i), freq.get(word.charAt(i)) + 1);
                }
            }
        }
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
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}
package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
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
                for (char i: letters){
                    for(int a = 0; a<word.length(); a++){
                        if (word.charAt(a)==i){
                            if(pattern.charAt(a)!=i){
                                botega = 0;
                            }
                        }
                    }
                }

            }
            for(char breezy: guesses){
                for(int i = 0; i<word.length(); i++){
                    if (breezy==word.charAt(i)){
                        if (!pattern.contains(breezy+"")){
                            botega = 0;
                        }
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
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}

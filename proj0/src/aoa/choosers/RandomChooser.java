package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.List;
import java.util.ArrayList;


public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;
    private final List<String> words;

    public RandomChooser(int wordLength, String dictionaryFile) {
        pattern = "";
        if (wordLength<1){
            throw new IllegalArgumentException();
        }
        words = FileUtils.readWords(dictionaryFile);
        List<String> rager = new ArrayList<>();
        for(String word: words){
            if (word.length()==wordLength){
                rager.add(word);
            }
        }
        if (rager.size()==0){
            throw new IllegalStateException();
        }
        for (int i = 0; i<wordLength; i++){
            pattern+='-';
        }
        int numWords = rager.size();
        int randomlyChosenWordNumber = StdRandom.uniform(numWords);
        chosenWord = rager.get(randomlyChosenWordNumber);
    }

    @Override
    public int makeGuess(char letter) {
        int count = 0;
        String birds = "";
        for(int i = 0; i<pattern.length(); i++){
            if (chosenWord.charAt(i)==letter){
                birds = birds + letter;
                count +=1;
            }
            else if(pattern.charAt(i)!='-'){
                birds = birds + pattern.charAt(i);
            }
            else{
                birds = birds + '-';
            }
        }
        pattern = birds;
        return count;
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public String getWord() {
        return chosenWord;
    }
}

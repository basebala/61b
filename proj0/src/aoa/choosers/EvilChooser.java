package aoa.choosers;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

public class EvilChooser implements Chooser {
    private String pattern;
    private List<String> wordPool = new ArrayList<>();
    private final List<String> words;

    public EvilChooser(int wordLength, String dictionaryFile) {
        pattern = "";
        if (wordLength<1){
            throw new IllegalArgumentException();
        }
        words = FileUtils.readWords(dictionaryFile);
        for(String word: words){
            if (word.length()==wordLength){
                wordPool.add(word);
            }
        }
        if (wordPool.size()==0){
            throw new IllegalStateException();
        }
        for (int i = 0; i<wordLength; i++){
            pattern+='-';
        }
    }

    @Override
    public int makeGuess(char letter) {
        TreeMap<String, List> patterned = new TreeMap<>();
        for (String word: wordPool){
            List<String> arn = new ArrayList<>();
            String birds = "";
            for(int i = 0; i<word.length(); i++){
                if (word.charAt(i)==letter){
                    birds = birds + letter;
                }
                else{
                    birds = birds + pattern.charAt(i);
                }
            }
            if (patterned.containsKey(birds)){
                arn = patterned.get(birds);
                arn.add(word);
                patterned.put(birds, arn);
            }
            else{
                arn.add(word);
                patterned.put(birds, arn);
            }
        }
        String curS = "";
        int curLargest = 0;
        for(String start: patterned.keySet()) {
            if (patterned.get(start).size() > curLargest) {
                wordPool = patterned.get(start);
                curLargest = patterned.get(start).size();
                pattern = start;
            }
        }
        String chr = wordPool.get(0);
        int count = 0;
        for (int i = 0; i<chr.length(); i++){
            if (chr.charAt(i)==letter){
                count+=1;
            }
        }
        return count;
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public String getWord() {
        return wordPool.get(0);
    }
}

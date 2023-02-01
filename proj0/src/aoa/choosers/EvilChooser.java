package aoa.choosers;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

public class EvilChooser implements Chooser {
    private String pattern;
    private List<String> wordPool;
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
        List<String> arn = new ArrayList<>();
        for (String word: wordPool){
            String birds = "";
            for(int i = 0; i<word.length(); i++){
                if (word.charAt(i)==letter){
                    birds = birds + letter;
                }
                else if(pattern.charAt(i)!='-'){
                    birds = birds + pattern.charAt(i);
                }
            }
            if (patterned.containsKey(birds)){
                List<String> arn = patterned.get(birds);
                arn.add(birds)
                patterned.put(birds, patterned.get(birds));
            }
            else{
                patterned.put(birds, )
            }
        }
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

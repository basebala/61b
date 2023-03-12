package ngordnet.ngrams;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.algs4.In;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;
    private Map<String, TimeSeries> myMap;
    private TimeSeries myTime;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        myMap = new HashMap<String, TimeSeries>();
        myTime = new TimeSeries();
        In myIn = new In(wordsFilename);
        In myIn2 = new In(countsFilename);
        while (myIn.hasNextLine() && !myIn.isEmpty()){
            String curString = myIn.readString();
            int curYear = myIn.readInt();
            int curCount = myIn.readInt();
            int curSrc = myIn.readInt();
            place(curString, curYear, curCount);
        }
        while (myIn2.hasNextLine() && !myIn2.isEmpty()){
            String curString = myIn2.readLine();
            String [] myStrings = curString.split(",");
            int curYear = Integer.parseInt(myStrings[0]);
            double curCount = Double.parseDouble(myStrings[1]);
            myTime.put(curYear, curCount);
        }
    }
    private void place (String word, int year, double count){
        if (!myMap.containsKey(word)){
            myMap.put(word, new TimeSeries());
        }
        myMap.get(word).put(year, count);
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        return new TimeSeries(myMap.get(word), startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy,
     * not a link to this NGramMap's TimeSeries. In other words, changes made
     * to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word) {
        return new TimeSeries(myMap.get(word), MIN_YEAR, MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return new TimeSeries(myTime, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        return countHistory(word, startYear, endYear).dividedBy(myTime);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to
     * all words recorded in that year. If the word is not in the data files, return an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        return countHistory(word).dividedBy(myTime);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS
     * between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     * this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries mySum = new TimeSeries();
        for (String word: words){
            mySum = mySum.plus(weightHistory(word, startYear, endYear));
        }
        return mySum;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        return summedWeightHistory(words, MIN_YEAR, MAX_YEAR);
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}

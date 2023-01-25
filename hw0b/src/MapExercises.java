import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 1);
        for (int i = 2; i<27; i++){
            char c = (char)((int)('a') + i-1);
            map.put(c, i);
        }
        return map;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums){
        Map<Integer, Integer> map = new HashMap<>();
        for(int elem: nums){
            map.put(elem, elem*elem);
        }
        return map;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> crazyMap = new HashMap<>();
        for (String first: words){
            int cur = 0;
            for (String second: words){
                if (second==first){
                    cur+=1;
                }
            }
            crazyMap.put(first, cur);
        }
        return crazyMap;
    }
}

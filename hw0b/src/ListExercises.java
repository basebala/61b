import java.util.List;
import java.util.ArrayList;


public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int sum = 0;
        for (int elem: L){
            sum = sum + elem;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> lst = new ArrayList<Integer>();
        for (int elem: L) {
            if (elem % 2 == 0) {
                lst.add(elem);
            }
        }
        return lst;

    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> lst = new ArrayList<Integer>();
        for(int elem: L1){
            if (L2.contains(elem) && !lst.contains(elem)){
                lst.add(elem);
            }
        }
        return lst;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;
        for (String mo: words){
            for (int i = 0; i<mo.length(); i++) {
                if (mo.charAt(i)==c) {
                    count += 1;
                }
            }
        }
        return count;
    }
}

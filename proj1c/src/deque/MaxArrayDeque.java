package deque;

import java.util.Comparator;
public class MaxArrayDeque<T> extends ArrayDeque<T> {

    public static void main(String[] args) {
    }
    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        comp = c;
    }

    public T max() {
        if (this.size() == 0) {
            return null;
        }
        T curMax = null;
        for (T x : this) {
            if (curMax == null) {
                curMax = x;
            } else {
                if (comp.compare(curMax, x) < 0) {
                    curMax = x;
                }
            }
        }
        return curMax;
    }

    public T max(Comparator<T> c) {
        if (this.size() == 0) {
            return null;
        }
        T curMax = null;
        for (T x : this) {
            if (curMax == null) {
                curMax = x;
            } else {
                if (c.compare(curMax, x) < 0) {
                    curMax = x;
                }
            }
        }
        return curMax;
    }
}

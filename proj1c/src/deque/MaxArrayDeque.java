package deque;

import java.util.Comparator;
public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private static final int INITIAL_SIZE = 8;

    public static void main(String[] args) {
    }
    private T[] backingArray;
    private int frontIndex;
    private int backIndex;


    private int size;

    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        this.backingArray = (T[]) new Object[INITIAL_SIZE];
        this.frontIndex = 1;
        this.backIndex = 0;
        this.size = 0;
        comp = c;
    }

    public T max() {
        if (size == 0) {
            return null;
        }
        T curMax = null;
        for (T x : this) {
            if (curMax == null) {
                curMax = x;
            } else {
                if (comp.compare(curMax, x)<0) {
                    curMax = x;
                }
            }
        }
        return curMax;
    }

    public T max(Comparator<T> c) {
        if (size == 0) {
            return null;
        }
        T curMax = null;
        for (T x : this) {
            if (curMax == null) {
                curMax = x;
            } else {
                if (c.compare(curMax, x)<0) {
                    curMax = x;
                }
            }
        }
        return curMax;
    }
}

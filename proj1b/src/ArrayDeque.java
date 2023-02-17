import java.util.List;
import java.util.ArrayList;


public class ArrayDeque<T> implements Deque<T> {

    private static final int INITIAL_SIZE = 8;
    private static final int RESIZE_FACTOR = 4;

    public static void main(String[] args) {

    }
    private T[] backingArray;
    private int frontIndex;
    private int backIndex;


    private int size;

    public ArrayDeque() {
        this.backingArray = (T[]) new Object[INITIAL_SIZE];
        this.frontIndex = 1;
        this.backIndex = 0;
        this.size = 0;
    }

    @Override
    public void addFirst(T x) {
        if (size == backingArray.length) {
            resize(backingArray.length * 2);
            backingArray[backingArray.length - 1] = x;
            frontIndex = backingArray.length - 1;
        } else {
            if (frontIndex == 0) {
                backingArray[backingArray.length - 1] = x;
                frontIndex = backingArray.length - 1;
            } else {
                backingArray[frontIndex - 1] = x;
                frontIndex -= 1;
            }
        }
        size = size + 1;
    }

    @Override
    public void addLast(T x) {
        if (size == backingArray.length) {
            resize(backingArray.length * 2);
            backingArray[size] = x;
            backIndex = size;
        } else {
            if (backIndex == backingArray.length-1) {
                backIndex = 0;
            } else {
                backIndex = backIndex + 1;
            }
            backingArray[backIndex] = x;
        }
        size = size + 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int curIndex = frontIndex;
        int count = 0;
        while (count < size) {
            returnList.add(backingArray[curIndex]);
            count = count + 1;
            curIndex = curIndex + 1;
            if (curIndex == backingArray.length) {
                curIndex = 0;
            }
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T temp = backingArray[frontIndex];
        backingArray[frontIndex] = null;
        frontIndex += 1;
        if (frontIndex == backingArray.length) {
            frontIndex = 0;
        }
        size = size - 1;
        if (size < backingArray.length / RESIZE_FACTOR) {
            resize(backingArray.length / 2);
        }
        return temp;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T temp = backingArray[backIndex];
        backingArray[backIndex] = null;
        backIndex -= 1;
        if (backIndex == -1) {
            backIndex = backingArray.length - 1;
        }
        size = size - 1;
        if (size < backingArray.length / RESIZE_FACTOR) {
            resize(backingArray.length / 2);
        }
        return temp;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        if (frontIndex == 0) {
            return backingArray[index];
        } else {
            if (index < backingArray.length - frontIndex) {
                return backingArray[frontIndex + index];
            } else {
                return backingArray[backIndex - size + 1 + index];
            }
        }
    }

    public void resize(int newSize) {
        T[] temp = (T[]) new Object[newSize];
        int curIndex = frontIndex;
        int count = 0;
        for (int i = 0; i < size; i++) {
            temp[i] = backingArray[curIndex];
            curIndex += 1;
            if (curIndex == backingArray.length) {
                curIndex = 0;
            }
        }
        backingArray = temp;
        frontIndex = 0;
        backIndex = size - 1;
    }
}

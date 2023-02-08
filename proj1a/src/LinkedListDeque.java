import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque<T> implements Deque<T> {
    private Node sentinel;
    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addFirst(5);
        lld.addLast(2);
    }
    class Node {
        private Node prev;
        private Node next;
        private T value;

        public Node(T value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }


    public LinkedListDeque() {
        this.sentinel = new Node(null,null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
    }

    @Override
    public void addFirst(T x) {
        this.sentinel.next.prev = new Node(x, this.sentinel, this.sentinel.next);
        this.sentinel.next = this.sentinel.next.prev;
    }

    @Override
    public void addLast(T x) {
        this.sentinel.prev.next = new Node(x, this.sentinel.prev, this.sentinel);
        this.sentinel.prev = this.sentinel.prev.next;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node curElement = this.sentinel.next;
        while (curElement!=this.sentinel){
            returnList.add(curElement.value);
            curElement = curElement.next;
        }
        return returnList;

    }

    @Override
    public boolean isEmpty() {
        if (this.sentinel.next==this.sentinel){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        int count = 0;
        Node curElement = this.sentinel;
        while (curElement.next!=this.sentinel){
            curElement = curElement.next;
            count+=1;
        }
        return count;
    }

    @Override
    public T removeFirst() {
        if (this.sentinel.next==this.sentinel){
            return null;
        }
        T toBeReturned = this.sentinel.next.value;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        return toBeReturned;
    }

    @Override
    public T removeLast() {
        if(this.sentinel.next==this.sentinel){
            return null;
        }
        T toBeReturned = this.sentinel.prev.value;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        return toBeReturned;
    }

    @Override
    public T get(int index) {
        Node curElement = this.sentinel.next;
        int curIndex= 0;
        if (index<0){
            return null;
        }
        while (curIndex<index && curElement!=this.sentinel){
            curIndex+=1;
            curElement = curElement.next;
        }
        if (curElement==this.sentinel){
            return null;
        }
        else if(curIndex<0){
            return null;
        }
        else{
            return curElement.value;
        }
    }
    public T recursiveHelper(Node curNode, int curIndex){
        if (curNode==this.sentinel){
            return null;
        }
        if (curIndex==0){
            return curNode.value;
        }
        else{
            return recursiveHelper(curNode.next, curIndex-1);
        }

    }

    @Override
    public T getRecursive(int index) {
        if (index<0){
            return null;
        }
        return recursiveHelper(this.sentinel.next, index);
    }
}

package deque;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque<T> implements Deque<T> {
    private Node sentinel;
    private int size;
    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addFirst(1);
        lld.addLast(2);
        lld.addLast(2);
        Deque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addLast(2);
        lld1.addFirst(1);
        lld1.addLast(2);
        System.out.println(lld1.equals(lld));
        System.out.println(lld.toString());
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
        this.sentinel = new Node(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
        this.size = 0;
    }

    @Override
    public void addFirst(T x) {
        this.sentinel.next.prev = new Node(x, this.sentinel, this.sentinel.next);
        this.sentinel.next = this.sentinel.next.prev;
        this.size += 1;
    }

    @Override
    public void addLast(T x) {
        this.sentinel.prev.next = new Node(x, this.sentinel.prev, this.sentinel);
        this.sentinel.prev = this.sentinel.prev.next;
        this.size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node curElement = this.sentinel.next;
        while (curElement != this.sentinel) {
            returnList.add(curElement.value);
            curElement = curElement.next;
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
        if (this.size == 0) {
            return null;
        }
        T toBeReturned = this.sentinel.next.value;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        this.size -= 1;
        return toBeReturned;
    }

    @Override
    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        T toBeReturned = this.sentinel.prev.value;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        this.size -= 1;
        return toBeReturned;
    }

    @Override
    public T get(int index) {
        Node curElement = this.sentinel.next;
        int curIndex = 0;
        if (index < 0) {
            return null;
        }
        while (curIndex < index && curElement != this.sentinel) {
            curIndex += 1;
            curElement = curElement.next;
        }
        if (curElement == this.sentinel) {
            return null;
        } else if (curIndex < 0) {
            return null;
        } else {
            return curElement.value;
        }
    }
    public T recursiveHelper(Node curNode, int curIndex) {
        if (curNode == this.sentinel) {
            return null;
        }
        if (curIndex == 0) {
            return curNode.value;
        } else {
            return recursiveHelper(curNode.next, curIndex - 1);
        }

    }

    @Override
    public T getRecursive(int index) {
        if (index < 0) {
            return null;
        }
        return recursiveHelper(this.sentinel.next, index);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Deque uddaDeque) {
            if (uddaDeque.size() != this.size()) {
                return false;
            }
            if (this.size() == 0) {
                return true;
            }
            List<T> uddaToList = uddaDeque.toList();
            Node curNode = this.sentinel.next;
            for (T x: uddaToList) {
                if (!curNode.value.equals(x)) {
                    return false;
                }
                curNode = curNode.next;
            }
            return true;

        }
        return false;
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node wizPos;

        public LinkedListDequeIterator() {
            wizPos = sentinel;
        }

        public boolean hasNext() {
            return wizPos.next != sentinel;
        }

        public T next() {
            T returnItem = wizPos.next.value;
            wizPos = wizPos.next;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }
}

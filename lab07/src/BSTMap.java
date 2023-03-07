import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;
    private class Node {
        private K key;
        private V value;
        private Node left, right, top;
        private int size;

        public Node(K key, V value, Node top, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.top = top;
        }

        public void place(K newKey, V newValue) {
            if (this.key.compareTo(newKey) == 0) {
                this.value = newValue;
            } else if (this.key.compareTo(newKey) > 0) {
                if (left == null) {
                    left = new Node(newKey, newValue, this, 1);
                    this.update();
                } else {
                    left.place(newKey, newValue);
                }
            } else {
                if (right == null) {
                    right = new Node(newKey, newValue, this, 1);
                    this.update();
                } else {
                    right.place(newKey, newValue);
                }
            }
        }
        public void update() {
            size += 1;
            if (top != null) {
                top.update();
            }
        }
        public V check(K newKey) {
            if (this.key.compareTo(newKey) == 0) {
                return this.value;
            } else if (this.key.compareTo(newKey) > 0) {
                if (left == null) {
                    return null;
                } else {
                    return left.check(newKey);
                }
            } else {
                if (right == null) {
                    return null;
                } else {
                    return right.check(newKey);
                }
            }
        }
        public boolean checkTrue(K newKey) {
            if (this.key.compareTo(newKey) == 0) {
                return true;
            } else if (this.key.compareTo(newKey) > 0) {
                if (left == null) {
                    return false;
                } else {
                    return left.checkTrue(newKey);
                }
            } else {
                if (right == null) {
                    return false;
                } else {
                    return right.checkTrue(newKey);
                }
            }
        }
    }

    public BSTMap() {

    }
    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value, null, 1);
            return;
        }
        root.place(key, value);
    }

    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        return root.check(key);
    }

    @Override
    public boolean containsKey(K key) {
        return root != null && this.root.checkTrue(key);
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return this.root.size;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}

package hashmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public void put(K key, V value) {
        if ((double) size / capacity >= load) {
            buckets = resize(capacity * 2);
        }
        int i = key.hashCode();
        i = Math.floorMod(i, capacity);
        if (containsKey(key)) {
            for (Node j: buckets[i]) {
                if (j.key.equals(key)) {
                    j.value = value;
                }
            }
        } else {
            Node j = new Node(key, value);
            buckets[i].add(j);
            size += 1;
        }
    }

    @Override
    public V get(K key) {
        int i = key.hashCode();
        i = Math.floorMod(i, capacity);
        for (Node j: buckets[i]) {
            if (j.key.equals(key)) {
                return j.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int i = key.hashCode();
        i = Math.floorMod(i, capacity);
        for (Node j: buckets[i]) {
            if (j.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        load = LOAD_FACTOR;
        size = 0;
        capacity = INIT_CAPACITY;
        buckets = createTable(capacity);
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;

    private int capacity;

    private double load;

    private int size;

    private final double LOAD_FACTOR = .75;

    private final int INIT_CAPACITY = 16;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        capacity = INIT_CAPACITY;
        load = LOAD_FACTOR;
        size = 0;
        buckets = createTable(capacity);
    }

    public MyHashMap(int initialCapacity) {
        capacity = INIT_CAPACITY;
        load = LOAD_FACTOR;
        size = 0;
        buckets = createTable(initialCapacity);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        capacity = initialCapacity;
        load = loadFactor;
        size = 0;
        buckets = createTable(initialCapacity);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return null;
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] collect = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            collect[i] = createBucket();
        }
        return collect;
    }

    private Collection<Node>[] resize(int x) {
        capacity = x;
        Collection<Node>[] myCollection = createTable(capacity);
        for (int i = 0; i < buckets.length; i++) {
            for (Node j: buckets[i]) {
                int k = j.key.hashCode();
                k = Math.floorMod(k, capacity);
                myCollection[k].add(j);
            }
        }
        return myCollection;
    }

}

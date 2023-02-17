import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
//import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {
    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque<String> lld1 = new ArrayDeque<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        lld1.toList();
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(2, -1, 0, 1, 2).inOrder();
    }

    // Below, you'll write your own tests for LinkedListDeque.

    @Test
    public void testListEmpty() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        assertThat(lld1.toList()).containsExactly().inOrder();
    }
    @Test
    public void testEmptiness() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        assertThat(lld1.isEmpty()).isEqualTo(true);
        lld1.addFirst(2);
        assertThat(lld1.isEmpty()).isEqualTo(false);
    }
    @Test
    public void testSize() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        assertThat(lld1.size()).isEqualTo(0);
        lld1.addLast(2);
        assertThat(lld1.size()).isEqualTo(1);
    }
    @Test
    public void testGet() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        assertThat(lld1.get(0)).isEqualTo(null);
        lld1.addLast(2);
        assertThat(lld1.get(0)).isEqualTo(2);
        assertThat(lld1.get(2)).isEqualTo(null);
        assertThat(lld1.get(-1)).isEqualTo(null);
    }
    @Test
    public void testRemoveFirst() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        assertThat(lld1.removeFirst()).isEqualTo(null);
        assertThat(lld1.size()).isEqualTo(0);
        lld1.addLast(2);
        lld1.addFirst(1);
        lld1.addFirst(2);
        assertThat(lld1.removeFirst()).isEqualTo(2);
        assertThat(lld1.toList()).containsExactly(1, 2).inOrder();
    }
    @Test
    public void testRemoveLast() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        assertThat(lld1.removeLast()).isEqualTo(null);
        lld1.addLast(1);
        lld1.addFirst(2);
        lld1.addFirst(1);
        assertThat(lld1.removeLast()).isEqualTo(1);
        assertThat(lld1.toList()).containsExactly(1, 2).inOrder();
    }
    @Test
    public void testRand() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        assertThat(lld1.removeFirst()).isEqualTo(null);
        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addFirst(2);
        for (int i = 0; i < 2; i++) {
            lld1.removeFirst();
        }
        assertThat(lld1.toList()).containsExactly(2).inOrder();
        lld1.removeFirst();
        assertThat(lld1.toList()).isEmpty();
        lld1.addFirst(1);
        assertThat(lld1.toList()).containsExactly(1).inOrder();
    }
    @Test
    public void testRand2() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        assertThat(lld1.removeLast()).isEqualTo(null);
        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addFirst(2);
        for (int i = 0; i < 2; i++) {
            lld1.removeLast();
        }
        assertThat(lld1.toList()).containsExactly(2).inOrder();
        lld1.removeLast();
        assertThat(lld1.toList()).isEmpty();
        lld1.addFirst(1);
        assertThat(lld1.toList()).containsExactly(1).inOrder();
    }
}

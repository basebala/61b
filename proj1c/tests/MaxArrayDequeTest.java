import gh2.GuitarString;
import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;
import deque.LinkedListDeque;
import deque.MaxArrayDeque;
import deque.LinkedListDeque;
import deque.MaxArrayDeque;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {
    private class testComp implements Comparator<String> {

        @Override
        public int compare(String x, String y){
            if(x.length() < y.length()){
                return -1;
            }
            else if(x.length()==y.length()){
                return 0;
            }
            return 1;
        }
    }
    @Test
    public void testDivCalculations() {
        Comparator<String> divComp = new testComp();
        MaxArrayDeque<String> divDeck = new MaxArrayDeque<>(divComp);
        divDeck.addLast("div");
        divDeck.addFirst("niger");
        divDeck.addLast("ghghgh");
        assertThat(divDeck.max()).isEqualTo("ghghgh");
    }

}

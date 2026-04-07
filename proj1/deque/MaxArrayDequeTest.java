package deque;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    @Test
    public void testMaxWithIntegers() {
        // 创建整数比较器（自然顺序）
        Comparator<Integer> cmp = (a, b) -> a - b;
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(cmp);

        deque.addLast(3);
        deque.addLast(1);
        deque.addLast(4);
        deque.addLast(2);

        assertEquals(Integer.valueOf(4), deque.max());
    }

    @Test
    public void testMaxWithStringsByLength() {
        // 按字符串长度比较
        Comparator<String> lengthCmp = (a, b) -> a.length() - b.length();
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(lengthCmp);

        deque.addLast("a");
        deque.addLast("bbb");
        deque.addLast("cc");

        assertEquals("bbb", deque.max());
    }

    @Test
    public void testMaxWithStringsByLexicographical() {
        // 按字典顺序比较
        Comparator<String> lexCmp = String::compareTo;
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(lexCmp);

        deque.addLast("apple");
        deque.addLast("banana");
        deque.addLast("cherry");

        assertEquals("cherry", deque.max());
    }

    @Test
    public void testMaxWithCustomComparator() {
        Comparator<Integer> natural = (a, b) -> a - b;
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(natural);

        deque.addLast(3);
        deque.addLast(1);
        deque.addLast(4);
        deque.addLast(2);

        // 使用不同的比较器（按相反顺序，找最小值）
        Comparator<Integer> reverse = (a, b) -> b - a;
        assertEquals(Integer.valueOf(1), deque.max(reverse));
    }

    @Test
    public void testMaxOnEmptyDeque() {
        Comparator<Integer> cmp = (a, b) -> a - b;
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(cmp);

        assertNull(deque.max());
        assertNull(deque.max(cmp));
    }

    @Test
    public void testMaxWithDuplicateValues() {
        Comparator<Integer> cmp = (a, b) -> a - b;
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(cmp);

        deque.addLast(3);
        deque.addLast(5);
        deque.addLast(5);
        deque.addLast(2);

        // 有多个最大值时，返回任意一个都可以
        Integer max = deque.max();
        assertTrue(max == 5);
    }

    @Test
    public void testInheritedMethods() {
        Comparator<Integer> cmp = (a, b) -> a - b;
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(cmp);

        // 测试继承自 ArrayDeque 的方法是否正常工作
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(5);

        assertEquals(3, deque.size());
        assertEquals(Integer.valueOf(5), deque.get(0));
        assertEquals(Integer.valueOf(10), deque.get(1));
        assertEquals(Integer.valueOf(20), deque.get(2));

        assertEquals(Integer.valueOf(20), deque.removeLast());
        assertEquals(Integer.valueOf(5), deque.removeFirst());
        assertEquals(1, deque.size());
    }

    @Test
    public void testMaxWithOneElement() {
        Comparator<Integer> cmp = (a, b) -> a - b;
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(cmp);

        deque.addLast(42);
        assertEquals(Integer.valueOf(42), deque.max());
    }
}
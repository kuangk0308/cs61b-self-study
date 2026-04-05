package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  @Test
  public void testThreeAddThreeRemove(){
      AListNoResizing<Integer> correct = new AListNoResizing<>();
      BuggyAList<Integer> buggy = new BuggyAList<>();
      // 2. 添加相同的三个元素（比如 4, 5, 6）
      correct.addLast(4);
      buggy.addLast(4);

      correct.addLast(5);
      buggy.addLast(5);

      correct.addLast(6);
      buggy.addLast(6);

      // 3. 三次 removeLast，每次比较返回值
      int c1 = correct.removeLast();
      int b1 = buggy.removeLast();
      assertEquals(c1, b1);

      int c2 = correct.removeLast();
      int b2 = buggy.removeLast();
      assertEquals(c2, b2);

      int c3 = correct.removeLast();
      int b3 = buggy.removeLast();
      assertEquals(c3, b3);
  }

    @Test
    public void JUnit(){
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            }
        }
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();


        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                buggy.addLast(randVal);
                //System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int c = correct.size();
                int b = buggy.size();
                assertEquals(c, b);

               // System.out.println("size: " + size);
            }
            else if (operationNumber == 2) {
                // getLast - 有返回值，需要比较（但只能在 size > 0 时调用）
                if (correct.size() > 0) {  // 两边 size 应该相同，检查一边即可
                    int c = correct.getLast();
                    int b = buggy.getLast();
                    assertEquals(c, b);
                }

            }
            else if (operationNumber == 3) {
                // removeLast - 有返回值，需要比较（但只能在 size > 0 时调用）
                if (correct.size() > 0) {
                    int c = correct.removeLast();
                    int b = buggy.removeLast();
                    assertEquals(c, b);
                }
            }
        }
    }
}

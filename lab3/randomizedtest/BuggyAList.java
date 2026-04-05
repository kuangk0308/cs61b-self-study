package randomizedtest;

/** Array based list.
 *  @author Josh Hug
 */

//         0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 5

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/

public class BuggyAList<Item> {
    private Item[] items;
    private int size;

    /** Creates an empty list. */
    public BuggyAList() {
        items = (Item[]) new Object[1];
        size = 0;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i += 1) {
            a[i] = items[i];
        }
        items = a;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = x;
        size = size + 1;
    }

    /** Returns the item from the back of the list. */
    public Item getLast() {
        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public Item removeLast() {
        Item x = getLast();//应该先把要返回的值算好，再去判断需不需要减半，因为减半之后就不应该对链表进行操作了，否则就影响储存了
        int beforeSize = size;                 // 缩容前的 size
        items[size - 1] = null;
        size = size - 1;

        if (((size) < (items.length) / 4) && (size > 4)) {
        //size是存的数字的数量，lenth是数组的长度，我们应该在需要缩减的时候把数组长度减少而不是size
            resize((items.length) / 2);//应该是除2就行了，除4肯定不对,而且应该对真实长度除二
        }


        return x;
    }
}

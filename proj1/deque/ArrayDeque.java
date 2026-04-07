package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items; // 底层数组
    private int size;
    //我们用环形数组实现，构建两个不变量，用来确定下一次添加时前后的索引
    private int nextFirst;
    private int nextLast;

    public  ArrayDeque(){
        items = (T[]) new Object[8];//不能直接写T8,要强转一次
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }



    public int size(){
        return size;
    }

    public void printDeque(){
        if (isEmpty()) {
            System.out.println();
            return;
        }
//plusOne(nextFirst)是实际的第一个位置,然后由于循环，i可能会超出lenth。取模就好
        for(int i = plusOne(nextFirst);i<size+plusOne(nextFirst);i++){
            System.out.print(items[i%items.length] + " ");
        }
        System.out.println();
    }

    //写一个辅助函数来让索引向左移动（+1）
    private int minusOne(int index){
        return (index-1+items.length)%items.length;
    }

    //写一个辅助函数来让索引向右移动（-1）
    private int plusOne(int index){
        return (index+1)%items.length;
    }
    public void addFirst(T item){
        if (size == items.length){
            resize(items.length * 2);   // 扩容为原来的两倍
        }

        items[nextFirst] = item;
        size+=1;
        nextFirst = minusOne(nextFirst);

    }

    public void addLast(T item){
        if (size == items.length){
            resize(items.length * 2);   // 扩容为原来的两倍
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);     // 向右移动
        size++;
    }

    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        int x = plusOne(nextFirst);
        T t = items[x];
        items[x]=null;// 释放引用
        size-=1;
        nextFirst =plusOne(nextFirst);

        // 缩容检查
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }

        return t;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int x = minusOne(nextLast);
        T t = items[x];
        items[x] = null;
        nextLast = minusOne(nextLast);
        size--;

        // 缩容检查,注意砍半的是原始数组长度，不是size
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2 );
        }

        return t;
    }

    public T get(int index){
        if (index < 0 || index >= size) {
            return null;
        }
        int start = plusOne(nextFirst);  // 第一个元素的位置
        int pos = (start + index) % items.length;//相当于index是个相对位移
        return items[pos];
    }

    private void resize(int capacity){
        T[]newItems = (T[]) new Object[capacity];
        //遍历然后迁移
        int pos =0;
        for(int i = plusOne(nextFirst);i<size+plusOne(nextFirst);i++){
            newItems[pos] = items[i%items.length] ;
            pos+=1;
        }
        items = newItems;
        nextFirst = capacity-1;//无论扩还是缩，都会把之前首位的元素放到新数组实际的首位，因此addfirst就是整个数组容量减一（最后那个）
        nextLast = size;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof ArrayDeque)) return false;

        ArrayDeque<?> other = (ArrayDeque<?>) o;
        if (size != other.size) return false;

        for (int i = 0; i < size; i++) {
            T thisItem = get(i);
            Object otherItem = other.get(i);
            if (thisItem == null) {
                if (otherItem != null) return false;
            } else if (!thisItem.equals(otherItem)) {
                return false;
            }
        }
        return true;
    }


    public Iterator<T> iterator() {
        return new ArrayIterator();
    }
    private class ArrayIterator implements Iterator<T>{
        int index= 0;//就相当于get的index

        @Override
        public boolean hasNext(){
            return index < size;
        }
        @Override
        public T next() {
            return get(index++);

        }


    }
}


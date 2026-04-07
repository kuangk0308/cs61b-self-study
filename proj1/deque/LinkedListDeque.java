package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements  Deque<T> ,Iterable<T> {//T是泛化类型
    private class Node{
        T item;//即储存的数据
        Node prev;//前
        Node next;//后

        //节点的构造方法
        public Node(T item,Node prev,Node next){
            this.item = item;//this就是指这个节点自己
            this.prev = prev;
            this.next = next;
        }
    }

    //哨兵，此时只是创建了对象，还没有赋值，都是null
    private Node sentinelFront;
    private Node sentinelBack;
    private int size;// 元素数量

    //开始写LinkedListDeque的第一种构造方法：空初始化
    public LinkedListDeque(){
        sentinelFront = new Node(null,null,null);
        sentinelBack = new Node(null,null,null);

        sentinelFront.next = sentinelBack;
        sentinelBack.prev = sentinelFront;
        size = 0;
    }

    public int size(){
        return size;
    }


    public void addFirst(T item){
        //要做四件事：1头-》new；2new《-头；3new-》原头；4原头《-new
        //完成1，3
        Node newnode = new Node(item,sentinelFront,sentinelFront.next);
        sentinelFront.next.prev = newnode;//4
        sentinelFront.next = newnode;//2
        size+=1;
    }

    public void addLast(T item){
        Node newnode = new Node(item,sentinelBack.prev,sentinelBack);
        sentinelBack.prev.next = newnode;
        sentinelBack.prev = newnode;
        size+=1;
    }



    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        T x = sentinelFront.next.item;
        sentinelFront.next = sentinelFront.next.next;
        sentinelFront.next.prev = sentinelFront;
        size-=1;
        return x;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T x = sentinelBack.prev.item;
        sentinelBack.prev = sentinelBack.prev.prev;
        sentinelBack.prev.next = sentinelBack;
        size--;
        return x;
    }

    public void printDeque(){
        Node p = sentinelFront;
        while (p.next!=sentinelBack){
            p = p.next;
            System.out.print(p.item + " ");
        }
        System.out.println();
    }

    //非递归实现get
    public T get(int index){
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = sentinelFront.next;
        for (int i=0;i<index;i++){
            p=p.next;
        }
        return p.item;
    }
    //递归实现，注意需要写一个私有化方法传入node
    public T getRecursive(int index){
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinelFront.next,index);
    }

    private T getRecursiveHelper(Node node, int index){
        //初始条件与如何递归
        //初始
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next,index-1);
    }

    public boolean equals(Object o){
        //在二者是同一类型的情况下是可以直接比较的
        if (!(o instanceof Deque)) {
            return false;
        }
        //已确认是同一类型，转换为同一类型（因为系统仍然认为o是object）
        Deque<T> other = (Deque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        //逐个比较
        for (int i = 0; i < size(); i++) {
            T thisItem = this.get(i);
            T otherItem = other.get(i);
            if (thisItem == null) {
                if (otherItem != null) return false;
            } else if (!thisItem.equals(otherItem)) {
                return false;
            }
        }
        return true;
    }


    public Iterator<T> iterator() {
        // 返回一个 Iterator 对象
        return new LinkedListIterator();
    }
    // 定义类来实现 Iterator 接口
    private class LinkedListIterator implements Iterator<T>{

        private Node current = sentinelFront.next;
        //作用：判断是否还有下一个元素可以遍历。
        @Override
        public boolean hasNext(){
            return current!=sentinelBack;
        }

        public T next(){
            T x = current.item;
            current = current.next;
            return x;
        }
    }
}

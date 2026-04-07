package deque;
import java.util.Comparator;//比较器的包
/**Comparator<T> 是一个函数式接口，能比较两个 T 类型对象的大小
调用 c.compare(a, b) 返回：
负数：a < b
0：a == b
正数：a > b*/
//继承自arraydeque
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;  // 相当于实例化一个比较器后面用

    // 构造器
    public MaxArrayDeque(Comparator<T> c) {
        super();  // 调用父类 ArrayDeque 的构造器
        //然后实现自己的比较器设置
        this.comparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        // 使用 this.comparator
        int maxindex = 0;
        for (int i =0 ;i<this.size();i++){
            if(comparator.compare(this.get(i),this.get(maxindex))>0){
                maxindex = i;
            }
        }
        return this.get(maxindex);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) return null;
        int maxindex = 0;
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(i), get(maxindex)) > 0) {
                maxindex = i;
            }
        }
        return get(maxindex);
    }
}
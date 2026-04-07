package gh2;

// TODO: uncomment the following import once you're ready to start this portion
import deque.Deque;
import deque.LinkedListDeque;
// TODO: maybe more imports

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    //储存声音数据的缓冲区
    // TODO: uncomment the following line once you're ready to start this portion
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: // TODO: 创建一个容量为 SR / frequency 的缓冲区。你需要将除法的结果强制转换为 int 类型。
        //      为了提高精度，在强制转换之前应使用 Math.round() 函数。
        //      你最初应该用零填充你的缓冲区数组。
        int capacity = (int)Math.round(SR/frequency);
        buffer = new LinkedListDeque<>();
        for(int i=0;i<capacity;i++){
            buffer.addLast(0.0);
        }

    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        //// TODO: 将 buffer 中的所有元素出队，并用随机数替换它们，
        ////       随机数的范围在 -0.5 到 0.5 之间。你可以通过以下方式获得这样的随机数：
        ////       double r = Math.random() - 0.5;
        ////       确保你的随机数彼此不同。这并不意味着你需要检查这些数字是否彼此不同，
        ////       而是意味着你应该重复调用 Math.random() - 0.5 来为每个数组索引生成新的随机数。
        int capacity = buffer.size();
        //清空出队
        while (!buffer.isEmpty()){
            buffer.removeFirst();
        }
        for(int i=0;i<capacity;i++){
            double r = Math.random() - 0.5;
            buffer.addLast(r);

        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**
        //移除 Deque 中的第一个 double，并将其与 Deque 中的下一个 double 进行平均
        // 再乘以能量衰减因子 0.996（我们将这个整个结果称为 newDouble）。
        // 然后，将 newDouble 添加到 Deque 的末尾。
        double oldfirst = buffer.removeFirst();
        double newlast = (buffer.get(0)+oldfirst)*0.5*0.996;
        buffer.addLast(newlast);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.get(0);
    }
}
    // TODO: Remove all comments that say TODO when you're done.

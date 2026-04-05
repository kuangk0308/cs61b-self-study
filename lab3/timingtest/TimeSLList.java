package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        int [] testsize ={1000,2000,4000,8000,16000,64000,128000} ;
        for(int i=0;i<testsize.length;i++){
            SLList<Integer> list = new SLList<>();

            for(int j=0;j<testsize[i];j++){
                list.addLast(1);
            }
            Stopwatch sw = new Stopwatch();//创建一个秒表对象 sw,同时启动计时（从这一行开始计时）
            for(int k=0;k<10000;k++){
                list.getLast();
            }
            double time = sw.elapsedTime();//返回从秒表创建到现在经过的时间，单位是秒
            times.addLast(time);
            Ns.addLast(testsize[i]);
            opCounts.addLast(testsize[i]);
        }
        printTimingTable(Ns,times,opCounts);


    }

    }



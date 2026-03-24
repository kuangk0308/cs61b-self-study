public class maxnumarray {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int len = m.length;
        int i = 0;
        int maxnum = 0;
        while(i<len){
            if (maxnum<=m[i]){
                maxnum = m[i];
            }
            i+=1;
        }
        return maxnum;

    }

    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(max(numbers));
    }
}

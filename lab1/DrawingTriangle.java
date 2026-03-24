public class DrawingTriangle {
    public static void triangle(int SIZE) {
        int col = 1;
        int row = 1;
        while (row <= SIZE) {

            while (col <= row) {
                if (col == row) {
                    System.out.println('*');
                } else {
                    System.out.print('*');
                }
                col += 1;
            }
            row += 1;
            col = 1;
        }

    }

    public static void main(String[] args) {

        triangle(10);
    }
}
package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TBC {
    public static char[][] matrix;

    public static int areaCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int r = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());
        matrix = new char[r][c];
        for (int i = 0; i < matrix.length; i++) {
          matrix[i] = reader.readLine().toCharArray();
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 't'){
                    areaCount++;
                    findArea(row,col);
                }
            }
        }

        System.out.println(areaCount);
    }

    private static void findArea(int row, int col) {
        if (isOutOfBounds(row, col) || isNotTraversal(row, col)){
            return;
        }
        matrix[row][col] = 'V';


        findArea(row + 1, col);
        findArea(row, col + 1);
        findArea(row - 1, col);
        findArea(row, col - 1);
        findArea(row + 1, col + 1);
        findArea(row - 1, col - 1);
        findArea(row - 1, col + 1);
        findArea(row + 1, col - 1);
    }

    private static boolean isNotTraversal(int row, int col) {
        return matrix[row][col] == 'd' || matrix[row][col] == 'V';
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }
}

package RecursionAndCombinatorialProblems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AreasInMatrix {
    public static char[][] matrix;
    public static List<int[]> areas;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r = Integer.parseInt(scanner.nextLine());
        int c =  Integer.parseInt(scanner.nextLine());
        matrix = new char[r][c];
        for (int i = 0; i < r; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }

        areas = new ArrayList<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == '-'){
                    areas.add(new int[] {row, col, 0});
                    findArea(row,col);
                }
            }
        }

        System.out.printf("Total areas found: %d%n", areas.size());
//
//        AtomicInteger integer = new AtomicInteger(1);
//
//        areas.stream()
//                .sorted((f, s) -> Integer.compare(s[2], f[2]))
//                .forEach(a ->{
//                    System.out.printf("Area #%d at (%d, %d), size: %d%n", integer.getAndIncrement(), a[0], a[1], a[2]);
//                });

    }

    private static void findArea(int row, int col) {
        if (isOutOfBounds(row,col) || isNotTraversal(row, col)){
            return;
        }

        matrix[row][col] = 'V';

        areas.get(areas.size() - 1)[2]++;

        findArea(row + 1, col);
        findArea(row, col + 1);
        findArea(row - 1, col);
        findArea(row, col - 1);
    }

    private static boolean isNotTraversal(int row, int col) {
        return matrix[row][col] == 'V' || matrix[row][col] == '*';
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }
}

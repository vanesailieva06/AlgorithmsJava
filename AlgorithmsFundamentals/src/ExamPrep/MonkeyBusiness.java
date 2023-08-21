package ExamPrep;

import java.util.Arrays;
import java.util.Scanner;

public class MonkeyBusiness {
    public static int[] result;
    public static int[]numbers;
    public static int solutions = 0;
    public static StringBuilder builder = new StringBuilder();
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        result = new int[n + 1];
        numbers = new int[n + 1];
        for (int i = 1; i < numbers.length; i++) {
            numbers[i] = i;
        }
        variations(1);
        builder.append(String.format("Total Solutions: %d", solutions));
        System.out.println(builder.toString());
    }

    private static void variations(int index) {
        if (index == result.length){
            printResult();
            return;
        }
        result[index] = numbers[index];
        variations(index + 1);

        result[index] = -numbers[index];
        variations(index + 1);

    }

    private static void printResult() {
        int sum = Arrays.stream(result).sum();
        if (sum == 0) {
            solutions++;
            for (int i = 1; i < result.length; i++) {
                builder.append(String.format(result[i] > 0 ? "+" + result[i] : String.valueOf(result[i])))
                        .append(" ");
            }
            builder.append(System.lineSeparator());
        }
    }
}

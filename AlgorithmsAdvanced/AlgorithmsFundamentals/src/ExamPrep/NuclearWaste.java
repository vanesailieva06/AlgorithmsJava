package ExamPrep;

import java.util.Arrays;
import java.util.Scanner;

public class NuclearWaste {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] costs = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = Integer.parseInt(scanner.nextLine());

        int[] dp = new int[n + 1];
        int[] prev = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                if (j > costs.length) {
                    break;
                }
                int newValue = dp[i - j] + costs[j - 1];
                if (newValue < dp[i]) {
                    dp[i] = newValue;
                    prev[i] = j;
                }
            }
        }

        StringBuilder out = new StringBuilder();

        out.append("Cost: ")
                .append(dp[n])
                .append(System.lineSeparator());

        while (n > 0) {
            out.append(prev[n])
                    .append(" => ")
                    .append(costs[prev[n] - 1])
                    .append(System.lineSeparator());
            n -= prev[n];
        }

        System.out.println(out.toString());
    }
}

package DynamicProgramming;

import java.util.Scanner;

public class BinomialCoefficients {
    public static long[][] memory;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());
        memory =  new long[n + 1][k + 1];
        long binomial = calcBinomial(n, k);
        System.out.println(binomial);
    }

    private static long calcBinomial(int n, int k) {
        if (k == 0 || k == n){
            return 1;
        }
        if (memory[n][k] != 0){
            return memory[n][k];
        }
        return memory[n][k] = calcBinomial(n - 1, k) + calcBinomial(n - 1, k - 1);
    }
}

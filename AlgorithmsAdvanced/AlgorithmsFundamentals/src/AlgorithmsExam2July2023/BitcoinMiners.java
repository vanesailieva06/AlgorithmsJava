package AlgorithmsExam2July2023;

import java.util.Scanner;
import java.util.StringTokenizer;

public class BitcoinMiners {
    public static int[] arr;
    public static int nTransaction;
    public static int result;
    public static boolean[] used;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        nTransaction = Integer.parseInt(scanner.nextLine());
        int xTransaction = Integer.parseInt(scanner.nextLine());
        arr = new int[xTransaction];
        used = new boolean[nTransaction + 1];
        int binomial = binomial(nTransaction, xTransaction);
        System.out.println(binomial);

    }

    private static int binomial(int n, int k) {
        if (k > n) {
            return 0;
        }
        if (k == 0 || k == n) {
            return 1;
        }

        return binomial(n - 1, k - 1) + binomial(n - 1, k);
    }

}

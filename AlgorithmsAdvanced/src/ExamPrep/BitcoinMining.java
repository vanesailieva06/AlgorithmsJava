package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BitcoinMining {
    public static class Bitcoin{
        private String hash;
        private int size;
        private int fee;
        private String from;
        private String to;

        public Bitcoin(String hash, int size, int fee, String from, String to) {
            this.hash = hash;
            this.size = size;
            this.fee = fee;
            this.from = from;
            this.to = to;
        }

        public int getSize() {
            return size;
        }

        public String getHash() {
            return hash;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Bitcoin> bitcoins = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] properties = reader.readLine().split("\\s+");
            String hash = properties[0];
            int size = Integer.parseInt(properties[1]);
            int fee = Integer.parseInt(properties[2]);
            String from = properties[3];
            String to = properties[4];
            bitcoins.add(new Bitcoin(hash, size, fee, from, to));
        }

        int[][] dp = new int[bitcoins.size() + 1][bitcoins.size() + 1];
        boolean[][] taken = new boolean[bitcoins.size() + 1][bitcoins.size() + 1];

        for (int row = 1; row <= bitcoins.size() ; row++) {
            Bitcoin bitcoin = bitcoins.get(row - 1);
            for (int col = 0; col <= bitcoins.size() ; col++) {
                int excludedBitcoin = dp[row - 1][col];

                if (row - col < 0){
                    dp[row][col] = excludedBitcoin;
                }else {
                    int includedBitcoin = dp[row - 1][row - col] + bitcoin.fee;

                    if (excludedBitcoin > includedBitcoin){
                        dp[row][col] = excludedBitcoin;
                    }else{
                        dp[row][col] = includedBitcoin;
                        taken[row][col] = true;
                    }
                }
            }
        }
        Set<Bitcoin> outputBitcoins = new HashSet<>();
        int lastBitcoin = bitcoins.size();

        while (lastBitcoin > 0){
            if (taken[lastBitcoin][n]){
                Bitcoin bitcoin = bitcoins.get(lastBitcoin - 1);
                outputBitcoins.add(bitcoin);
                n--;
            }
            lastBitcoin--;
        }
        System.out.println("Total Size: " + outputBitcoins.stream().mapToInt(Bitcoin::getSize).sum());
        int feeAchieved = dp[bitcoins.size()][bitcoins.size()];
        System.out.println("Total Fees: " + feeAchieved);
        System.out.println(outputBitcoins.stream().map(Bitcoin::getHash)
                .map(String::valueOf)
                .collect(Collectors.joining(System.lineSeparator())));
    }
}

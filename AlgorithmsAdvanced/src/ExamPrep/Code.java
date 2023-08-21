package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Code {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] firstSequence = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] secondSequence = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

         int[][] dp = new int[firstSequence.length + 1][secondSequence.length + 1];

         StringBuilder builder = new StringBuilder();

        for (int i = 1; i <= firstSequence.length; i++) {
            for (int j = 1; j <= secondSequence.length; j++) {
                if (firstSequence[i - 1] == secondSequence[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                }else{
                    dp[i][j] = Math.max(
                            dp[i - 1][j], dp[i][j - 1]
                    );
                }
            }
        }


        List<Integer> result = new ArrayList<>();
        int row = firstSequence.length;
        int col = secondSequence.length;

        while (row > 0 && col > 0){
            if (firstSequence[row - 1] == secondSequence[col - 1]){
                result.add(firstSequence[row - 1]);
                row--;
                col--;
            }else if(dp[row - 1][col] > dp[row][col - 1]){
                row--;
            }else{
                col--;
            }
        }
        Collections.reverse(result);
        result.forEach(num -> builder.append(num + " "));
        builder.append(System.lineSeparator());
        builder.append(dp[firstSequence.length][secondSequence.length]);
        System.out.println(builder.toString().trim());
    }
}

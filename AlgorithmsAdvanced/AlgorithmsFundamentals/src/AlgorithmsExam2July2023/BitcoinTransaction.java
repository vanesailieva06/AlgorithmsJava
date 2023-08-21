package AlgorithmsExam2July2023;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BitcoinTransaction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] firstArr = scanner.nextLine().split("\\s+");
        String[] secondArr = scanner.nextLine().split("\\s+");

        String[] result;
        int maxLength = 0;
        int bestLength = 0;
        List<String> sequence = new ArrayList<>();
        if (Arrays.equals(firstArr, secondArr)){
            printArr(firstArr);
            return;
        }

        for (int i = 0; i < firstArr.length; i++) {
            for (int j = 0; j < secondArr.length; j++) {
                if (secondArr[j].equals(firstArr[i])) {
                   bestLength++;
                   maxLength = Math.max(maxLength, bestLength);
                   sequence.add(firstArr[i]);
                }
            }
        }
        result = new String[maxLength];
        Collections.sort(sequence);
        for (int i = 0; i < sequence.size(); i++) {
            result[i] = sequence.get(i);
        }

        printArr(result);
    }

    private static void printArr(String[] arr) {
        String out = Arrays.toString(arr).replace(", ", " ");
        System.out.println(out);
    }
}

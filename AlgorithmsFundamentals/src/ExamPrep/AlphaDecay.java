package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AlphaDecay {
    public static int[] arr;
    public static int[] numbers;

    public static boolean[] used;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        numbers = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int k = Integer.parseInt(reader.readLine());
        arr = new int[k];
        used = new boolean[numbers.length];
        combination(0, 1);

    }

    private static void combination(int index, int start) {
        if (index == arr.length){
            printArr();
        }else{
            for (int i = 0; i < numbers.length; i++) {
                if (!used[i]){
                    used[i] = true;
                    arr[index] = numbers[i];
                    combination(index + 1, i + 1);
                    used[i] = false;
                }

            }
        }
    }

    private static void printArr() {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SuperSet {
    public static int[] arr;
    public static int[] numbers;
    public static boolean[] used;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        numbers = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
        used = new boolean[numbers.length];
//        for (int number : numbers) {
//            System.out.println(number);
//        }
//        combination(0);
//
//        if (arr.length <= numbers.length) {
//            arr = new int[arr.length + 1];
//            combination(0);
//        }
        for (int i = 0; i <= numbers.length; i++) {
            arr = new int[i + 1];
            if (arr.length < numbers.length) {
            combination(0);
        }
        }
        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }

    private static void combination(int index) {
        if (index == arr.length){
            printArr();
        }else{
            for (int i = 0; i < numbers.length; i++) {
                if (!used[i]){
                    used[i] = true;
                    arr[index] = numbers[i];
                    combination(index + 1);
                    used[i] = false;
                }
            }

        }
    }

    private static void printArr() {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

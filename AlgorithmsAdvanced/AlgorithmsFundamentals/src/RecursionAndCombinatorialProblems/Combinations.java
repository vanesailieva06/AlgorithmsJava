package RecursionAndCombinatorialProblems;

import java.util.Scanner;

public class Combinations {
    public static int n;
    public static int[] arr;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());
        arr = new int[k];
        combination(0, 1);
    }

    private static void combination(int index, int start) {
        if (index == arr.length){
            printArr();
        }else{
            for (int i = start; i <= n; i++) {
                arr[index] = i;
                combination(index + 1, i);
            }
        }
    }
    private static void printArr() {
        for (int i = 0; i <= arr.length - 1; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

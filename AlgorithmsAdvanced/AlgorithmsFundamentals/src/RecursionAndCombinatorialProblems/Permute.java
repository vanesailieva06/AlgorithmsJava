package RecursionAndCombinatorialProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Permute {
    public static int[] arr;
    public static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        arr = new int[n];
        permute(0);
    }

    private static void permute(int index) {
        if (index == arr.length){
            printArr();
        }else{
            for (int i = 1; i <= n; i++) {
               arr[index] = i;
               permute(index + 1);
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

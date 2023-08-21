package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stairs {
    public static long[] memory;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int stairs = Integer.parseInt(reader.readLine());
        memory = new long[stairs + 1];


        System.out.println(climbStairs(stairs));
    }

    private static long climbStairs(int stairs) {
        if (stairs == 0){
            return 0;
        }
        if (stairs == 1){
            return 1;
        }
        if (stairs == 2){
            return 2;
        }
        if (memory[stairs] != 0){
            return memory[stairs];
        }


        return memory[stairs] = climbStairs(stairs - 1) + climbStairs(stairs - 2);
    }
}

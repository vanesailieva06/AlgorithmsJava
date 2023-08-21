package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClusterBorder {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] singleShipTime = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] pairShipTime = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] memory = new int[singleShipTime.length + 1];
        memory[0] = 0;
        memory[1] = singleShipTime[0];
        for (int i = 2; i <= singleShipTime.length; i++) {
            int singleTime = singleShipTime[i - 1] + memory[i - 1];
            int pairTime = pairShipTime[i - 2] + memory[i - 2];
            memory[i] = Math.min(singleTime, pairTime);
        }

        System.out.printf("Optimal Time: %d%n", memory[memory.length - 1]);
        List<String> lines = new ArrayList<>();

        int index = memory.length - 1;

        while (index > 0){
            if (memory[index - 1] + singleShipTime[index - 1] == memory[index]){
                lines.add(String.format("Single %d", index));
                index--;
            }else{
                lines.add(String.format("Pair of %d and %d", index - 1, index));
                index -= 2;
            }
        }

        Collections.reverse(lines);
        System.out.println(String.join(System.lineSeparator(), lines));
    }
}

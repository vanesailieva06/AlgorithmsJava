package RecursionAndCombinatorialProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Cinema {
    public static String[] seats;
    public static List<String> people;
    public static String[] combination;
    public static boolean[] used;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        people = Arrays.stream(reader.readLine().split(", ")).collect(Collectors.toList());
        seats = new String[people.size()];
        String line = reader.readLine();
        while (!line.equals("generate")){
            String[] tokens = line.split(" - ");
            String name = tokens[0];
            int position = Integer.parseInt(tokens[1]) - 1;
            seats[position] = name;
            people.remove(name);
            line = reader.readLine();
        }
        combination = new String[people.size()];
        used = new boolean[people.size()];
        permute(0);
    }

    private static void permute(int index) {
        if (index == people.size()){
            print();
        }else{
            for (int i = 0; i < people.size(); i++) {
                if (!used[i]) {
                    used[i] = true;
                    combination[index] = people.get(i);
                    permute(index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void print() {
        int index = 0;
        String[] output = new String[seats.length];
        for (int i = 0; i < output.length; i++) {
            if (seats[i] != null){
                output[i] = seats[i];
            }else{
                 output[i] = combination[index++];
            }
        }
        System.out.println(String.join(" ", output));
    }
}

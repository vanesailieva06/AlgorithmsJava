package ExamPrep;

import java.util.Arrays;
import java.util.Scanner;

public class Trains {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] arrivals = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .sorted()
                .toArray();
        double[] departures = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .sorted()
                .toArray();

        int platforms = 0;
        int maxPlatforms = 0;
        for (int arrive = 0 , depart = 0; arrive < arrivals.length;) {
            if (arrivals[arrive] < departures[depart]){
                platforms++;
                arrive++;

                maxPlatforms = Math.max(maxPlatforms, platforms);
            }
            else{
                platforms--;
                depart++;
            }

        }
        System.out.println(maxPlatforms);
    }
}

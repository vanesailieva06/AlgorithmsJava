package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Medivac {
    public static class Unit{
        private int unit;
        private int capacity;
        private int urgencyRating;

        public Unit(int unit, int capacity, int urgencyRating) {
            this.unit = unit;
            this.capacity = capacity;
            this.urgencyRating = urgencyRating;
        }

        public int getUnit() {
            return unit;
        }

        public int getCapacity() {
            return capacity;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int capacity = Integer.parseInt(reader.readLine());
        List<Unit> units = new ArrayList<>();
        String line = reader.readLine();

        while (!line.equals("Launch")){
            int[] properties = Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Unit unit = new Unit(properties[0], properties[1], properties[2]);
            units.add(unit);

            line = reader.readLine();
        }

        int[][] dp = new int[units.size() + 1][capacity + 1];
        boolean[][] taken = new boolean[units.size() + 1][capacity + 1];

        for (int currentUnit = 1; currentUnit <= units.size() ; currentUnit++) {
            Unit unit = units.get(currentUnit - 1);
            for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {
                int excludedUnit = dp[currentUnit - 1][currentCapacity];

                if (currentCapacity - unit.capacity < 0){
                    dp[currentUnit][currentCapacity] = excludedUnit;
                }else{
                    int includedUnit = dp[currentUnit - 1][currentCapacity - unit.capacity] + unit.urgencyRating;

                    if (excludedUnit > includedUnit){
                        dp[currentUnit][currentCapacity] = excludedUnit;
                    }else {
                        dp[currentUnit][currentCapacity] = includedUnit;
                        taken[currentUnit][currentCapacity] = true;
                    }
                }
            }
        }

        Set<Unit> outputUnits = new TreeSet<>(Comparator.comparingInt(u -> u.unit));
        int urgencyAchieved = dp[units.size()][capacity];
        int lastUnit = units.size();

        while (lastUnit > 0){
            if (taken[lastUnit][capacity]){
                Unit unit = units.get(lastUnit - 1);
                outputUnits.add(unit);
                capacity -= unit.capacity;
            }
            lastUnit--;
        }

        System.out.println(outputUnits.stream().mapToInt(Unit::getCapacity).sum());
        System.out.println(urgencyAchieved);
        System.out.println(outputUnits.stream().map(Unit::getUnit)
                .map(String::valueOf)
                .collect(Collectors.joining(System.lineSeparator())));
    }
}

package Exam20August2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SocialMediaTracker {
    public static Map<Character, Map<Character, Integer>> graph = new HashMap<>();
    private static Set<Character> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int edgesCount = Integer.parseInt(reader.readLine());

        Map<Character, int[]> distancesAndHops = new HashMap<>();

        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = reader.readLine().split("\\s+");

            Character userA = tokens[0].charAt(0);
            Character userB = tokens[1].charAt(0);
            int influence = Integer.parseInt(tokens[2]);

            graph.putIfAbsent(userA, new HashMap<>());
            graph.get(userA).put(userB, influence);

            distancesAndHops.put(userA, new int[]{Integer.MIN_VALUE, 0});
            distancesAndHops.put(userB, new int[]{Integer.MIN_VALUE, 0});
        }
        Character source = reader.readLine().charAt(0);
        Character destination = reader.readLine().charAt(0);

        distancesAndHops.get(source)[0] = 0;

        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (Character node : graph.keySet()) {
            topologicalSort(node, stack);
        }

        while (!stack.isEmpty()) {
            Character node = stack.pop();
            Map<Character, Integer> edge = graph.get(node);
            if (edge != null) {
                for (Character child : edge.keySet()) {
                    int influence = edge.get(child);
                    int newDistance = distancesAndHops.get(node)[0] + influence;
                    int currentDistance = distancesAndHops.get(child)[0];

                    int newHops = distancesAndHops.get(node)[1] + 1;
                    int currentHops = distancesAndHops.get(child)[1];

                    if (newDistance > currentDistance || (newDistance == currentDistance && newHops < currentHops)) {
                        distancesAndHops.get(child)[0] = newDistance;
                        distancesAndHops.get(child)[1] = newHops;
                    }
                }
            }
        }
        System.out.printf("(%d, %d)%n", distancesAndHops.get(destination)[0], distancesAndHops.get(destination)[1]);
    }

    private static void topologicalSort(Character node, ArrayDeque<Character> sorted) {
        if (visited.contains(node)) {
            return;
        }

        visited.add(node);

        Map<Character, Integer> edgesFrom = graph.get(node);

        if (edgesFrom != null) {
            for (Character child : edgesFrom.keySet()) {
                topologicalSort(child, sorted);
            }

            sorted.push(node);
        }
    }
}


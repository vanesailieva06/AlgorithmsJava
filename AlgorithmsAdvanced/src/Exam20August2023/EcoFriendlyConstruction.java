package Exam20August2023;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class EcoFriendlyConstruction {
    public static Map<String, Map<String, Integer>> graph = new HashMap<>();
    public static Map<String, String> parents = new HashMap<>();
    public static PriorityQueue<String[]> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int edges = Integer.parseInt(reader.readLine());

        queue = new PriorityQueue<>(Comparator.comparing(e -> graph.get(e[0]).get(e[1])));

        for (int i = 0; i < edges; i++) {
            String[] tokens = reader.readLine().split("\\s+");

            String from = tokens[0];
            String to = tokens[1];
            int price = Integer.parseInt(tokens[2]);

            if (tokens.length == 4){
                int additionalCost = Integer.parseInt(tokens[3]);
                price += additionalCost;
            }
            graph.putIfAbsent(from, new HashMap<>());
            graph.get(from).put(to, price);

            parents.put(from, from);
            parents.put(to, to);

            queue.offer(new String[]{from, to});
        }

        int totalCost = 0;

        while (!queue.isEmpty()){
            String[] edge = queue.poll();
            String source = edge[0];
            String destination = edge[1];

            String firstRoot = findRoot(source, parents);
            String secondRoot = findRoot(destination, parents);

            if (!firstRoot.equals(secondRoot)){
                totalCost += graph.get(source).get(destination);
                parents.put(secondRoot, firstRoot);
            }
        }
        System.out.println("Total cost of building highways: " + totalCost);
    }

    private static String findRoot(String node, Map<String, String> parents) {

        while (!parents.get(node).equals(node)){
            node = parents.get(node);
        }

        return node;
    }
}

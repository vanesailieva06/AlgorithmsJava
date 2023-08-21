package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BoringCompany {
    public static class Edge implements Comparable<Edge>{
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
    public static Map<Integer, List<Edge>> graph = new HashMap<>();
    public static int[] parents;
    public static int cost;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());
        int connectedDistricts = Integer.parseInt(reader.readLine());
        boolean[] connected = new boolean[nodes];
        parents = new int[nodes];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];
            Edge edge = new Edge(from, to, weight);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(edge);
        }
        for (int i = 0; i < connectedDistricts; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];

            connected[from] = connected[to] = true;
        }

        kuskal(connected);

        System.out.println("Minimum budget: " + cost);
    }

    private static void kuskal(boolean[] connected) {
        int budget = 0;
        PriorityQueue<Edge> edges =  graph.values()
                .stream().flatMap(List::stream)
                .filter(e -> (connected[e.from] && !connected[e.to]) || (!connected[e.from] && connected[e.to]))
                .collect(Collectors.toCollection(PriorityQueue::new));

        while (!edges.isEmpty()){
            Edge minEdge = edges.poll();
            int from = minEdge.from;
            int to = minEdge.to;
            int weight = minEdge.weight;

            int currentValue = -1;

            if (connected[from] && !connected[to]){
                connected[to] = true;
                currentValue = weight;
            }else if (!connected[from] && connected[to]){
                connected[from] = true;
                currentValue = weight;
            }

            edges =  graph.values()
                    .stream().flatMap(List::stream)
                    .filter(e -> (connected[e.from] && !connected[e.to]) || (!connected[e.from] && connected[e.to]))
                    .collect(Collectors.toCollection(PriorityQueue::new));

            if (currentValue != -1){
                cost += currentValue;
            }
        }
    }
}

package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Creep {

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
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());
        parents = new int[nodes];
        Arrays.fill(parents, -1);

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

        kruskal();

    }

    private static void kruskal() {
        StringBuilder builder = new StringBuilder();

        PriorityQueue<Edge> edges = graph.values().stream().flatMap(List::stream)
                .collect(Collectors.toCollection(PriorityQueue::new));

        int weight = 0;

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        while (!edges.isEmpty()){
            Edge minEdge = edges.poll();
            int firstRoot = findRoot(minEdge.from);
            int secondRoot = findRoot(minEdge.to);

            if (firstRoot != secondRoot) {
                builder.append(String.format("%d %d%n", minEdge.from, minEdge.to));
                weight += minEdge.weight;
                parents[secondRoot] = firstRoot;
            }

            for (int i = 0; i < parents.length; i++) {
                if (parents[i] == secondRoot) {
                    parents[i] = firstRoot;
                }
            }
        }

        System.out.println(builder.toString().trim());
        System.out.println(weight);
    }

    private static int findRoot(int node) {
        int root = parents[node];

        while (parents[node] != root) {
            root = parents[root];
        }

        return root;
    }
}

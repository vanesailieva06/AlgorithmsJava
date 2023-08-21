package graphs;

import java.util.*;
import java.util.stream.Collectors;

public class ModifiedKruskal {

    public static class Edge implements Comparable<Edge> {
        public int from;
        public int to;
        public int weight;

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

    public static Map<Integer, List<Edge>> graph = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int edges = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];
            Edge edge = new Edge(from, to, weight);
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(edge);
        }
        int[] parents = new int[nodes];

        StringBuilder builder = new StringBuilder();

        Arrays.fill(parents, -1);

        PriorityQueue<Edge> queue = graph.values().stream().flatMap(List::stream)
                .collect(Collectors.toCollection(PriorityQueue::new));

        int forestWeight = 0;

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        while (!queue.isEmpty()) {
            Edge minEdge = queue.poll();
            int firstRoot = findRoot(minEdge.from, parents);
            int secondRoot = findRoot(minEdge.to, parents);

            if (firstRoot != secondRoot) {
                builder.append(String.format("(%d %d) -> %d%n", minEdge.from, minEdge.to, minEdge.weight));
                forestWeight += minEdge.weight;
                parents[secondRoot] = firstRoot;
            }

            for (int i = 0; i < parents.length; i++) {
                if (parents[i] == secondRoot) {
                    parents[i] = firstRoot;
                }
            }
        }

        System.out.println("Minimum spanning forest weight: " + forestWeight);
        System.out.println(builder.toString());
    }

    private static int findRoot(int node, int[] parents) {
        int root = parents[node];

        while (parents[node] != root) {
            root = parents[root];
        }

        return root;
    }
}

package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ReaperMan {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());
        int[] input = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int source = input[0];
        int destination = input[1];

        int[][] graph = new int[nodes][nodes];
        int[] distances = new int[nodes];
        boolean[] visited = new boolean[nodes];
        int[] parents = new int[nodes];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        Arrays.fill(parents, -1);

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            graph[from][to] = weight;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(e -> distances[e]));

        queue.offer(source);

        while (!queue.isEmpty()){
            Integer node = queue.poll();
            visited[node] = true;

            for (int child = 0; child < graph[node].length; child++) {
                if (!visited[child] && graph[node][child] != 0){
                    queue.offer(child);

                    int newDistance = distances[node] + graph[node][child];

                    if (newDistance < distances[child]){
                        distances[child] = newDistance;
                        parents[child] = node;
                    }
                }
            }
        }

        Deque<Integer> path = new ArrayDeque<>();

        path.push(destination);

        int prevNode = parents[destination];

        while (prevNode != -1){
            path.push(prevNode);
            prevNode = parents[prevNode];
        }

        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println(distances[destination]);
    }
}

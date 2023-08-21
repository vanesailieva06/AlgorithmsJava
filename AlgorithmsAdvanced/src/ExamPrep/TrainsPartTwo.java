package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class TrainsPartTwo {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());
        int[] input = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int start = input[0];
        int end = input[1];

        int[][] graph = new int[nodes][nodes];
        int[] distances = new int[nodes];
        boolean[] visited = new boolean[nodes];
        int[] parents = new int[nodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        Arrays.fill(parents, -1);

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int capacity = tokens[2];

            graph[from][to] = capacity;
            graph[to][from] = capacity;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(e -> distances[e]));

        queue.offer(start);

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
        path.push(end);
        int prevNode = parents[end];

        while (prevNode != -1){
            path.push(prevNode);
            prevNode = parents[prevNode];

        }

        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println(distances[end]);
    }
}

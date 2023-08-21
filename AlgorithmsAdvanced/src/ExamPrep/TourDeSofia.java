package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TourDeSofia {
    public static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static boolean[] visited;
    private static int[] distances;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());
        int source = Integer.parseInt(reader.readLine());

        distances = new int[nodes];
        visited = new boolean[nodes];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
        }

        bfs(source);

        if (distances[source] != 0){
            System.out.println(distances[source]);
        }else {
            int visit = 0;

            for (boolean node : visited) {
                if (node){
                    visit++;
                }
            }
            System.out.println(visit);
        }
    }

    private static void bfs(int source) {

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(source);
        visited[source] = true;
        distances[source] = 0;

        while (!queue.isEmpty()){
            Integer parent = queue.poll();
            List<Integer> children = graph.get(parent);

            if (children != null){
                for (Integer child : children) {
                    if (!visited[child]){
                        visited[child] = true;
                        distances[child] = distances[parent] + 1;
                        queue.offer(child);
                    }else if (child == source && distances[child] == 0){
                        distances[child] = distances[parent] + 1;
                        return;
                    }
                }
            }
        }
    }
}

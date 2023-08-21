package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Molecules {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        int[][] graph = new int[nodes + 1][nodes + 1];
        boolean[] visited = new boolean[nodes + 1];
        for (int i = 0; i < edges; i++) {
            int[] edge = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = edge[0];
            int to = edge[1];
            graph[from][to] = 1;
        }
        int start = Integer.parseInt(reader.readLine());
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        while (!queue.isEmpty()){
            int node = queue.poll();
            visited[node] = true;
            for (int i = 0; i < graph[node].length; i++) {
                if (!visited[i] && graph[node][i] == 1){
                    queue.offer(i);
                }
            }
        }
        for (int i = 1; i < visited.length ; i++) {
            if (!visited[i]){
                System.out.print(i + " ");
            }
        }
    }
}

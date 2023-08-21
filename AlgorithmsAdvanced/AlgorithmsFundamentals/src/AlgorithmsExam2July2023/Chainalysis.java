package AlgorithmsExam2July2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Chainalysis {
    public static Map<String, Map<String, Integer>> graph = new HashMap<>();
    public static int groups = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String from = tokens[0];
            String to = tokens[1];
            int value = Integer.parseInt(tokens[2]);
            graph.putIfAbsent(from, new HashMap<>());
            graph.get(from).put(to, value);
        }

        Set<String> visited = new HashSet<>();

         for (String parent : graph.keySet()) {
            if (!visited.contains(parent)){
                dfs(parent, visited);
                groups++;
            }
         }

        System.out.println(groups);
    }

    private static void dfs(String source, Set<String> visited) {
        if (visited.contains(source)){
            return;
        }

        if (graph.get(source) == null){
            return;
        }
        visited.add(source);

        for (String child : graph.get(source).keySet()) {
            dfs(child, visited);
        }
    }
}

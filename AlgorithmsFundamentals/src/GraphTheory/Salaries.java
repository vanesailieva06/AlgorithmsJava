package GraphTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Salaries {

    public static List< List<Integer>> graph = new ArrayList<>();
    public static boolean[] visited;
    public static long[] salaries;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int employees = Integer.parseInt(scanner.nextLine());
        visited = new boolean[employees];
        salaries = new long[employees];
        int[] managerCount = new int[employees];

        for (int i = 0; i < employees; i++) {
            String line = scanner.nextLine();
            graph.add(new ArrayList<>());
            for (int emp = 0; emp < line.length(); emp++) {
                char symbol = line.charAt(emp);
                if (symbol == 'Y'){
                    graph.get(i).add(emp);
                    managerCount[emp]++;
                }
            }
        }

        List<Integer> managers = new ArrayList<>();

        for (int i = 0; i < managerCount.length; i++) {
            if (managerCount[i] == 0){
                managers.add(i);
            }
        }
        for (Integer source : managers) {
            dfs(source);
        }

        long sum = Arrays.stream(salaries).sum();
        System.out.println(sum);
    }

    private static void dfs(Integer node) {
        if (visited[node]){
            return;
        }
        visited[node] = true;
        for (Integer child : graph.get(node)) {
            if (!visited[child]){
                dfs(child);
                long sum = graph.get(child).stream()
                        .mapToLong(s -> salaries[s])
                        .sum();
                salaries[child] = sum == 0 ? 1 : sum;
            }
        }
        long sum = graph.get(node).stream()
                .mapToLong(s -> salaries[s])
                .sum();
        salaries[node] = sum == 0 ? 1 : sum;
    }
}

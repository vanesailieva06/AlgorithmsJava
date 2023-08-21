package GraphTheory;

import java.util.*;



public class CyclesInGraph {
    public static Map<String, List<String>> graph = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String source = null;
        while (!line.equals("End")){
            String[] tokens = line.split("-");
            if (source == null){
                source = tokens[0];
            }
            graph.putIfAbsent(tokens[0], new ArrayList<>());
            graph.get(tokens[0]).add(tokens[1]);
            line = scanner.nextLine();
        }
        String output = "";
        Set<String> visited = new HashSet<>();
        Set<String> cycles = new HashSet<>();

        try {
            for (String s : graph.keySet()) {
                if (!visited.contains(s)){
                    dfs(s, visited, cycles);
                    output = "Acyclic: Yes";
                }
            }
        }catch (IllegalStateException ex){
            output = ex.getMessage();
        }

        System.out.println(output);
    }

    private static void dfs(String source, Set<String> visited, Set<String> cycles) {
        if (cycles.contains(source)){
            throw new IllegalStateException("Acyclic: No");
        }
        if (visited.contains(source)){
            return;
        }

        visited.add(source);
        cycles.add(source);

        if (graph.get(source) == null){
            return;
        }

        for (String child : graph.get(source)) {
            dfs(child, visited, cycles);
        }

        cycles.remove(source);
    }
}

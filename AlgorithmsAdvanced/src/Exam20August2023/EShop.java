package Exam20August2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class EShop {
    public static class Item{
        private List<Integer> items;
        private int weight;
        private int value;

        public Item(List<Integer> items, int weight, int value) {
            this.items = items;
            this.weight = weight;
            this.value = value;
        }
    }
    public static boolean[][] itemsGraph;
    public static boolean[] visited;
    public static String[] itemsNames;
    public static int[] weights;
    public static int[] values;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());

        itemsNames = new String[nodes];
        weights = new int[nodes];
        values = new int[nodes];

        Map<String, Integer> itemIndices = new HashMap<>();
        for (int i = 0; i < nodes; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String name = tokens[0];
            int weight = Integer.parseInt(tokens[1]);
            int value = Integer.parseInt(tokens[2]);

            itemIndices.put(name, i);
            itemsNames[i] = name;
            weights[i] = weight;
            values[i] = value;
        }

        itemsGraph = new boolean[nodes][nodes];
        visited = new boolean[nodes];

        int pairs = Integer.parseInt(reader.readLine());

        while (pairs-- > 0){
            String[] input = reader.readLine().split("\\s+");
            int row = itemIndices.get(input[0]);
            int col = itemIndices.get(input[1]);

            itemsGraph[row][col] = true;
            itemsGraph[col][row] = true;
        }

        int capacity = Integer.parseInt(reader.readLine());

        List<Item> items = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            if (!visited[i]){
                Item connectedItem = bfs(i);
                items.add(connectedItem);
            }
        }

        int[][] dp = new int[items.size() + 1][capacity + 1];
        boolean[][] taken = new boolean[items.size() + 1][capacity + 1];

        for (int itemRow = 1; itemRow <= items.size(); itemRow++) {
            int currentWeight = items.get(itemRow - 1).weight;
            int currentValue = items.get(itemRow - 1).value;
            for (int capacityCol = 0; capacityCol <= capacity; capacityCol++) {
                int excluded = dp[itemRow - 1][capacityCol];

                if (capacityCol - currentWeight < 0){
                    dp[itemRow][capacityCol] = excluded;
                }else {
                    int included = dp[itemRow - 1][capacityCol - currentWeight] + currentValue;

                    if (excluded > included){
                        dp[itemRow][capacityCol] = excluded;
                    }else {
                        dp[itemRow][capacityCol] = included;
                        taken[itemRow][capacityCol] = true;
                    }
                }
            }
        }
        Set<String> result = new TreeSet<>(Comparator.comparing(itemIndices::get));

        int lastItem = items.size();

        while (lastItem > 0){
            if (taken[lastItem][capacity]){
                Item item = items.get(lastItem - 1);
                item.items.stream().map(name ->itemsNames[name]).forEach(result::add);
                capacity -= item.weight;
            }
            lastItem--;
        }

        System.out.println(String.join(System.lineSeparator(), result));
    }

    private static Item bfs(int i) {
        List<Integer> list = new ArrayList<>();
        int totalWeight = 0;
        int totalValue = 0;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(i);

        while (!queue.isEmpty()){
            Integer node = queue.poll();
            visited[node] = true;

            for (int child = 0; child < itemsGraph[node].length; child++) {
                if (!visited[child] && itemsGraph[node][child]){
                    visited[child] = true;
                    queue.offer(child);
                }
            }
            list.add(node);
            totalWeight += weights[node];
            totalValue = values[node];
        }

        return new Item(list, totalWeight, totalValue);
    }
}

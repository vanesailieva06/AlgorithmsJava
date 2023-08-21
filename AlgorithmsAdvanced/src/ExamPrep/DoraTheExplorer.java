package ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DoraTheExplorer {
    public static class Destination{
        private int firstCity;
        private int secondCity;
        private int minutes;

        public Destination(int firstCity, int secondCity, int minutes) {
            this.firstCity = firstCity;
            this.secondCity = secondCity;
            this.minutes = minutes;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int edges = Integer.parseInt(reader.readLine());
        List<Destination> destinations = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<Boolean> visited = new ArrayList<>();
        List<Integer> parents = new ArrayList<>();


        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split(", "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int minutes = tokens[2];

            destinations.add(new Destination(from, to, minutes));
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
        }

        int minutesOnStop = Integer.parseInt(reader.readLine());
        int source = Integer.parseInt(reader.readLine());
        int destination = Integer.parseInt(reader.readLine());

        distances.add(source, 0);

        for (int i = 0; i < graph.size(); i++) {

        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(distances::get));

        queue.offer(source);

        while (!queue.isEmpty()){
            Integer node = queue.poll();
            visited.add(node, true);

            for (int child = 0; child < graph.size(); child++) {
                if (!visited.get(child) && graph.get(node).get(child) != 0){
                    queue.offer(child);

                    int newDistance = distances.get(node) + graph.get(node).get(child);

                    if (newDistance < distances.get(child)){
                        distances.add(child, newDistance);
                        parents.add(child, node);
                    }
                }
            }
        }
        System.out.println();
    }
}

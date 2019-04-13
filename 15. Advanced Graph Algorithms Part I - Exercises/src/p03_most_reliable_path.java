import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class p03_most_reliable_path {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodeCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        String[] path = reader.readLine().split("\\s+");
        int start = Integer.parseInt(path[1]);
        int end = Integer.parseInt(path[3]);
        int edgeCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        int[][] graph = new int[nodeCount][nodeCount];

        for (int i = 0; i < edgeCount; i++) {
            String[] params = reader.readLine().split("\\s+");
            int from = Integer.parseInt(params[0]);
            int to = Integer.parseInt(params[1]);
            int reliability = Integer.parseInt(params[2]);

            graph[from][to] = -1 * reliability;
            graph[to][from] = -1 * reliability;
        }

        List<Integer> shortestPath = dijkstra(graph, start, end);
        double reliability = 1;
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            int from = shortestPath.get(i);
            int to = shortestPath.get(i + 1);

            reliability *= -1 * graph[from][to] * 0.01;
        }

        System.out.printf("Most reliable path reliability: %.2f%%%n", reliability * 100);
        System.out.println(shortestPath.stream().map(String::valueOf).collect(Collectors.joining(" -> ")));
    }

    private static List<Integer> dijkstra(int[][] graph, int sourceNode, int destinationNode) {
        List<Integer> shortestPath = new ArrayList<>();
        Integer[] distances = new Integer[graph.length];
        Integer[] prev = new Integer[graph.length];
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> distances[i]));

        for (int i = 0; i < distances.length; i++) {
            if (i == sourceNode) {
                distances[i] = 0;
            } else {
                distances[i] = Integer.MAX_VALUE;
            }
        }

        queue.add(sourceNode);
        while (!queue.isEmpty()) {
            int minNode = queue.poll();
            visited[minNode] = true;
            if (distances[minNode] == Integer.MAX_VALUE) {
                break;
            }

            for (int i = 0; i < graph.length; i++) {
                if (graph[minNode][i] != 0 && !visited[i]) {
                    queue.add(i);
                    int newDist = distances[minNode] + graph[minNode][i];
                    distances[i] = Math.min(distances[i], newDist);

                    if (newDist == distances[i]) {
                        prev[i] = minNode;
                        queue.remove(i);
                        queue.add(i);
                    }
                }
            }
        }

        if (distances[destinationNode] == Integer.MAX_VALUE) {
            return null;
        }

        while (prev[destinationNode] != null) {
            shortestPath.add(0, destinationNode);
            destinationNode = prev[destinationNode];
        }

        shortestPath.add(0, sourceNode);
        return shortestPath;
    }
}

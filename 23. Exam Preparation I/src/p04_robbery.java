import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class p04_robbery { // TODO: 1.5.2019 г. not working properly
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Boolean[] colors = Arrays.stream(reader.readLine()
                .replaceAll("[^wb]+", "")
                .split(""))
                .map(x -> x.equals("w"))
                .toArray(Boolean[]::new);

        int startingEnergy = Integer.parseInt(reader.readLine());
        int waitCost = Integer.parseInt(reader.readLine());
        int start = Integer.parseInt(reader.readLine());
        int end = Integer.parseInt(reader.readLine());
        int n = Integer.parseInt(reader.readLine());

        int[][] graph = new int[colors.length][colors.length];

        for (int i = 0; i < n; i++) {
            String[] params = reader.readLine().split("\\s+");
            int from = Integer.parseInt(params[0]);
            int to = Integer.parseInt(params[1]);
            int weight = Integer.parseInt(params[2]);

            graph[from][to] = weight;
        }

        int minEnergyCost = dijkstra(graph, start, end, waitCost, colors);

        if (startingEnergy - minEnergyCost > 0) {
            System.out.println(startingEnergy - minEnergyCost);
        } else {
            System.out.printf("Busted - need %d more energy", minEnergyCost - startingEnergy);
        }
    }

    private static int dijkstra(int[][] graph, int sourceNode, int destinationNode, int waitCost, Boolean[] colors) {
        Integer[] distances = new Integer[graph.length];
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
            if (distances[minNode] == Integer.MAX_VALUE || minNode == destinationNode) {
                break;
            }

            for (int i = 0; i < graph.length; i++) {
                if (graph[minNode][i] != 0 && !visited[i]) {
                    queue.add(i);
                    int newDist = distances[minNode] + graph[minNode][i];

                    if ((!colors[minNode] && !colors[i]) || (colors[minNode] && colors[i])) {
                        newDist += waitCost;
                        rotateColors(colors);
                    }

                    distances[i] = Math.min(distances[i], newDist);

                    queue.remove(i);
                    queue.add(i);
                }
            }
        }

        return distances[destinationNode];
    }

    private static void rotateColors(Boolean[] colors) {
        for (int i = 0; i < colors.length; i++) {
            colors[i] = !colors[i];
        }
    }
}

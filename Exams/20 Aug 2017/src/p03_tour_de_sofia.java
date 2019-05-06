import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class p03_tour_de_sofia {

    private static int[] dist;
    private static boolean[] visited;
    private static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int vertexCount = Integer.parseInt(reader.readLine());
        int edgeCount = Integer.parseInt(reader.readLine());
        int start = Integer.parseInt(reader.readLine());
        dist = new int[vertexCount];
        graph = new List[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgeCount; i++) {
            String[] params = reader.readLine().split("\\s+");
            int from = Integer.parseInt(params[0]);
            int to = Integer.parseInt(params[1]);

            graph[from].add(to);
        }

        bfs(start);

        boolean pathExists = false;
        int minDist = 0x7fffffff;
        for (int i = 0; i < vertexCount; i++) {
            if (visited[i] && graph[i].contains(start) && dist[i] + 1 < minDist) {
                minDist = dist[i] + 1;
                pathExists = true;
            }
        }

        if (pathExists) {
            System.out.println(minDist);
        } else {
            int count = 0;
            for (boolean b : visited) {
                if (b) count++;
            }

            System.out.println(count);
        }
    }

    private static void bfs(int start) {
        visited = new boolean[graph.length];
        dist[start] = 0;
        visited[start] = true;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (Integer child : graph[vertex]) {
                if (!visited[child]) {
                    dist[child] = dist[vertex] + 1;
                    visited[child] = true;
                    queue.offer(child);
                }
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class p02_protoss {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodeCount = Integer.parseInt(reader.readLine());
        List<Integer>[] graph = new List[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < nodeCount; i++) {
            char[] connections = reader.readLine().toCharArray();
            for (int j = 0; j < connections.length; j++) {
                if (connections[j] == 'Y') {
                    graph[i].add(j);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < nodeCount; i++) {
            int count = getHyperConnections(i, nodeCount, graph);
            if (count > max) {
                max = count;
            }
        }

        System.out.println(max);
    }

    private static int getHyperConnections(int node, int nodeCount, List<Integer>[] graph) {
        int count = 0;
        boolean[] visited = new boolean[nodeCount];
        visited[node] = true;

        Queue<Integer> queue = new ArrayDeque<>(graph[node]);
        for (Integer child : graph[node]) {
            queue.addAll(graph[child]);
        }

        Integer current = queue.poll();
        while (current != null) {
            if (!visited[current]) {
                visited[current] = true;
                count++;
            }

            current = queue.poll();
        }


        return count;
    }
}


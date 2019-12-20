import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        Graph graph = new Graph(n);
        while (m-- > 0) {
            int[] v = Arrays.stream(reader.readLine().split(" - ")).mapToInt(Integer::parseInt).toArray();
            graph.addEdge(v[0], v[1]);
        }
        System.out.println("Important streets:");
        graph.bridge();
    }
}

class Graph {
    private int nodes;
    private List<Integer>[] adj;
    private int time = 0;

    Graph(int nodes) {
        this.nodes = nodes;
        this.adj = new ArrayList[nodes];
        for (int i = 0; i < nodes; ++i) {
            this.adj[i] = new ArrayList<>();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    void bridgeUtil(int u, boolean[] visited, int[] disc, int[] low, int[] parent) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj[u]) {
            if (!visited[v]) {
                parent[v] = u;
                bridgeUtil(v, visited, disc, low, parent);

                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) {
                    System.out.println(u + " " + v);
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    void bridge() {
        boolean[] visited = new boolean[nodes];
        int[] disc = new int[nodes];
        int[] low = new int[nodes];
        int[] parent = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            parent[i] = -1;
            visited[i] = false;
        }

        for (int i = 0; i < nodes; i++) {
            if (!visited[i]) {
                bridgeUtil(i, visited, disc, low, parent);
            }
        }
    }
}

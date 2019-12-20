import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p04_cheap_town_tour {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            int[] params = Arrays.stream(reader.readLine().split(" - ")).mapToInt(Integer::parseInt).toArray();
            edges[i] = new Edge(params[0], params[1], params[2]);
        }

        System.out.printf("Total cost: %d", kruskal(n, edges).stream().mapToInt(k -> k.weight).sum());
    }

    public static List<Edge> kruskal(int numberOfVertices, Edge[] edges) {
        Arrays.sort(edges);
        int[] parents = new int[numberOfVertices];

        for (int i = 0; i < numberOfVertices; i++) {
            parents[i] = i;
        }

        List<Edge> mst = new ArrayList<>();
        for (Edge edge : edges) {
            int startNodeRoot = findRoot(edge.from, parents);
            int endNodeRoot = findRoot(edge.to, parents);

            if (startNodeRoot != endNodeRoot) {
                mst.add(edge);
                parents[endNodeRoot] = startNodeRoot;
            }
        }

        return mst;
    }

    public static int findRoot(int node, int[] parent) {
        while (parent[node] != node) {
            node = parent[node];
        }

        return node;
    }
}

class Edge implements Comparable<Edge> {

    int from, to, weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}
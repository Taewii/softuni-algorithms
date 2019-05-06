import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p04_chain_lightning { // TODO: 6.5.2019 г. seems to be too slow for some of the judge tests

    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static int[] damages;
    private static boolean[] hit;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int vertexCount = Integer.parseInt(reader.readLine());
        int edgeCount = Integer.parseInt(reader.readLine());
        int strikeCount = Integer.parseInt(reader.readLine());

        List<Edge> edges = new ArrayList<>();
        damages = new int[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edgeCount; i++) {
            String[] params = reader.readLine().split("\\s+");
            int start = Integer.parseInt(params[0]);
            int end = Integer.parseInt(params[1]);
            int weight = Integer.parseInt(params[2]);

            edges.add(new Edge(start, end, weight));
        }

        kruskal(vertexCount, edges);

        for (int i = 0; i < strikeCount; i++) {
            String[] params = reader.readLine().split("\\s+");
            int target = Integer.parseInt(params[0]);
            int damage = Integer.parseInt(params[1]);

            hit = new boolean[vertexCount];
            dfs(target, target, damage);
        }

        System.out.println(Arrays.stream(damages).max().orElse(0));
    }

    private static void dfs(int target, int parent, int damage) {
        damages[target] += damage;
        hit[target] = true;
        for (Integer child : graph.get(target)) {
            if (child != parent && !hit[child]) {
                dfs(child, parent, damage / 2);
            }
        }
    }

    private static void kruskal(int numberOfVertices, List<Edge> edges) {
        Collections.sort(edges);
        int[] parents = new int[numberOfVertices];

        for (int i = 0; i < numberOfVertices; i++) {
            parents[i] = i;
        }

        for (Edge edge : edges) {
            int startNodeRoot = findRoot(edge.start, parents);
            int endNodeRoot = findRoot(edge.end, parents);

            if (startNodeRoot != endNodeRoot) {
                graph.get(edge.start).add(edge.end);
                graph.get(edge.end).add(edge.start);
                parents[endNodeRoot] = startNodeRoot;
            }
        }
    }

    private static int findRoot(int node, int[] parent) {
        while (parent[node] != node) {
            node = parent[node];
        }

        return node;
    }

    private static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}

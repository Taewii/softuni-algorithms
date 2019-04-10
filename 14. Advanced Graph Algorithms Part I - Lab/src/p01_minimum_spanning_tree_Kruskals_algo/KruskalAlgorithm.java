package p01_minimum_spanning_tree_Kruskals_algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {
        Collections.sort(edges);
        int[] parents = new int[numberOfVertices];

        for (int i = 0; i < numberOfVertices; i++) {
            parents[i] = i;
        }

        List<Edge> mst = new ArrayList<>();
        for (Edge edge : edges) {
            int startNodeRoot = findRoot(edge.getStartNode(), parents);
            int endNodeRoot = findRoot(edge.getEndNode(), parents);

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

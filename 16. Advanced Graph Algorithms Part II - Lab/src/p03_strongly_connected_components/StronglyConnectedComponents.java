package p03_strongly_connected_components;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class StronglyConnectedComponents {

    private static List<Integer>[] graph;
    private static List<Integer>[] reversedGraph;
    private static boolean[] visited;
    private static Deque<Integer> stack = new ArrayDeque<>();

    private static List<List<Integer>> stronglyConnectedComponents;

    public static List<List<Integer>> findStronglyConnectedComponents(List<Integer>[] targetGraph) {
        graph = targetGraph;
        visited = new boolean[graph.length];

        buildReversedGraph();

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                dfs(node);
            }
        }

        stronglyConnectedComponents = new ArrayList<>();
        visited = new boolean[graph.length];

        while (!stack.isEmpty()) {
            Integer node = stack.pop();

            if (!visited[node]) {
                stronglyConnectedComponents.add(new ArrayList<>());
                reverseDfs(node);
            }
        }

        return stronglyConnectedComponents;
    }

    private static void buildReversedGraph() {
        reversedGraph = new List[graph.length];

        for (int node = 0; node < reversedGraph.length; node++) {
            reversedGraph[node] = new ArrayList<>();
        }

        for (int node = 0; node < graph.length; node++) {
            for (Integer child : graph[node]) {
                reversedGraph[child].add(node);
            }
        }
    }

    private static void reverseDfs(int node) {
        if (visited[node]) return;

        visited[node] = true;
        stronglyConnectedComponents.get(stronglyConnectedComponents.size() - 1).add(node);

        for (Integer child : reversedGraph[node]) {
            reverseDfs(child);
        }
    }

    private static void dfs(int node) {
        if (visited[node]) return;

        visited[node] = true;
        for (Integer child : graph[node]) {
            dfs(child);
        }

        stack.push(node);
    }
}

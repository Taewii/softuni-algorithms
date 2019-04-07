import java.util.*;

public class p02_topological_sort {
    public static void main(String[] args) {

    }

    static Collection<String> topSort(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        Set<String> cycleNodes = new HashSet<>();
        List<String> result = new LinkedList<>();

        for (String node : graph.keySet()) {
            dfs(node, visited, graph, cycleNodes, result);
        }

        return result;
    }

    private static void dfs(String node, Set<String> visited, Map<String,
            List<String>> graph, Set<String> cycleNodes, List<String> result) {

        if (cycleNodes.contains(node)) {
            throw new IllegalArgumentException();
        }

        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        cycleNodes.add(node);

        for (String child : graph.get(node)) {
            dfs(child, visited, graph, cycleNodes, result);
        }

        cycleNodes.remove(node);
        result.add(0, node);
    }
}

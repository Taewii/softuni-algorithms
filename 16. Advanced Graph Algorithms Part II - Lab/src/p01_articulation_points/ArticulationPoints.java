package p01_articulation_points;

import java.util.ArrayList;
import java.util.List;

public class ArticulationPoints {

    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int[] depths;
    private static int[] lowpoint;
    private static Integer[] parent;

    private static List<Integer> articulationPoints;

    public static List<Integer> findArticulationPoints(List<Integer>[] targetGraph) {
        graph = targetGraph;
        visited = new boolean[graph.length];
        depths = new int[graph.length];
        lowpoint = new int[graph.length];
        parent = new Integer[graph.length];
        articulationPoints = new ArrayList<>();

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                findArticulationPoints(node, 1);
            }
        }

        return articulationPoints;
    }

    private static void findArticulationPoints(int node, int depth) {
        visited[node] = true;
        depths[node] = depth;
        lowpoint[node] = depth;

        boolean isArticulationPoint = false;
        int childrenCount = 0;

        for (Integer child : graph[node]) {
            if (!visited[child]) {
                parent[child] = node;
                findArticulationPoints(child, depth + 1);
                childrenCount++;

                if (lowpoint[child] >= depths[node]) {
                    isArticulationPoint = true;
                }

                lowpoint[node] = Math.min(lowpoint[node], lowpoint[child]);
            } else if (!child.equals(parent[node])) {
                lowpoint[node] = Math.min(lowpoint[node], depths[child]);
            }
        }

        if ((parent[node] == null && childrenCount > 1) || (parent[node] != null && isArticulationPoint)) {
             articulationPoints.add(node);
        }
    }
}

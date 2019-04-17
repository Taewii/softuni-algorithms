package p02_max_flow;

import java.util.ArrayDeque;
import java.util.Queue;

public class EdmondsKarp {

    private static int[][] graph;
    private static Integer[] parent;

    public static int findMaxFlow(int[][] targetGraph) {
        graph = targetGraph;
        parent = new Integer[graph.length];

        int start = 0;
        int end = graph.length - 1;
        int maxFlow = 0;

        while (bfs(start, end)) {
            int pathFlow = Integer.MAX_VALUE;
            int currentNode = end;

            while (currentNode != start) {
                int prevNode = parent[currentNode];
                int currentFlow = graph[prevNode][currentNode];

                if (currentFlow > 0 && currentFlow < pathFlow) {
                    pathFlow = currentFlow;
                }

                currentNode = prevNode;
            }

            maxFlow += pathFlow;

            currentNode = end;
            while (currentNode != start) {
                int prevNode = parent[currentNode];
                graph[prevNode][currentNode] -= pathFlow;
                graph[currentNode][prevNode] += pathFlow;

                currentNode = prevNode;
            }
        }

        return maxFlow;
    }

    private static boolean bfs(int start, int end) {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            for (int child = 0; child < graph[node].length; child++) {
                if (graph[node][child] > 0 && !visited[child]) {
                    queue.offer(child);
                    parent[child] = node;
                    visited[child] = true;
                }
            }
        }

        return visited[end];
    }
}

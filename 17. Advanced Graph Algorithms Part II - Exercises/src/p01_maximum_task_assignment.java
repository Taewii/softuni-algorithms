import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p01_maximum_task_assignment {

    // 80/100 cus the ordering isn't the same on the first test.
    private static int[][] graph;
    private static Integer[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int people = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int tasks = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int nodes = people + tasks + 2;
        graph = new int[nodes][];
        parents = new Integer[graph.length];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new int[nodes];
        }

        for (int i = 0; i < people; i++) {
            graph[0][i + 1] = 1;
        }

        for (int i = 0; i < tasks; i++) {
            graph[i + people + 1][graph.length - 1] = 1;
        }

        for (int person = 0; person < people; person++) {
            char[] chars = reader.readLine().toCharArray();
            for (int task = 0; task < tasks; task++) {
                if (chars[task] == 'Y') {
                    graph[person + 1][task + people + 1] = 1;
                }
            }
        }

        int start = 0;
        int end = graph.length - 1;

        while (bfs(start, end)) {
            int currentNode = end;

            while (currentNode != start) {
                int prevNode = parents[currentNode];

                graph[prevNode][currentNode] = 0;
                graph[currentNode][prevNode] = 1;

                currentNode = prevNode;
            }
        }

        Set<String> result = getAssignedPeopleAndTasks(people, start, end);
        result.forEach(System.out::println);
    }

    private static Set<String> getAssignedPeopleAndTasks(int people, int start, int end) {
        Set<String> result = new TreeSet<>();
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(end);
        visited[end] = true;

        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            for (int child = 0; child < graph.length; child++) {
                if (!visited[child] && graph[node][child] > 0) {
                    queue.offer(child);
                    visited[child] = true;

                    if (node != end && child != end && child != start) {
                        result.add(String.format("%s-%d", (char) (child - 1 + 'A'), node - people));
                    }
                }
            }
        }

        return result;
    }

    private static boolean bfs(int start, int end) {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            for (int child = 0; child < graph.length; child++) {
                if (!visited[child] && graph[node][child] > 0) {
                    queue.offer(child);
                    visited[child] = true;
                    parents[child] = node;
                }
            }
        }

        return visited[end];
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p01_distance_between_vertices {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());

        Map<Integer, int[]> graph = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            int[] nodes = Arrays.stream(reader.readLine().split("[\\s:]+")).mapToInt(Integer::parseInt).toArray();
            graph.put(nodes[0], Arrays.stream(nodes).skip(1).toArray());
        }

        for (int i = 0; i < k; i++) {
            int[] ps = Arrays.stream(reader.readLine().split("-")).mapToInt(Integer::parseInt).toArray();
            int e1 = ps[0];
            int e2 = ps[1];

            System.out.printf("{%d, %d} -> %d%n", e1, e2, findPath(e1, e2, graph, new HashSet<>()));
        }
    }

    private static int findPath(int start, int end, Map<Integer, int[]> graph, Set<Integer> visited) {
        if (start == end) return 0;

        Queue<Integer> queue = new ArrayDeque<>();
        visited.add(start);
        queue.add(start);
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] polled = new int[size];
            for (int i = 0; i < size; i++) {
                polled[i] = queue.poll();
            }

            for (int node : polled) {
                for (int n : graph.get(node)) {
                    if (!visited.contains(n)) {
                        visited.add(n);
                        queue.offer(n);
                    }
                }
            }

            count++;
            if (queue.contains(end)) {
                return count;
            }
        }

        return -1;
    }
}

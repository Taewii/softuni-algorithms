import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p04_greatest_strategy {

    private static Map<Integer, Set<Integer>> graph = new HashMap<>();
    private static Map<Integer, Set<Integer>> disconnected = new HashMap<>();

    public static void main(String[] a) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] args = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int N = args[0];
        int M = args[1];
        int root = args[2];

        for (int i = 1; i <= N; i++) {
            graph.put(i, new HashSet<>());
            disconnected.put(i, new HashSet<>());
        }

        for (int i = 0; i < M; i++) {
            args = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int from = args[0];
            int to = args[1];

            graph.get(to).add(from);
            graph.get(from).add(to);

            disconnected.get(to).add(from);
            disconnected.get(from).add(to);
        }

        DFS(root, root, new HashSet<>());
        System.out.println(getMax());
    }

    private static int DFS(int x, int parent, Set<Integer> visited) {
        visited.add(x);
        int childCount = 0;
        for (Integer child : graph.get(x)) {
            if (!visited.contains(child) && child != parent) {
                int subtreeSize = DFS(child, x, visited);

                if (subtreeSize % 2 == 0) {
                    disconnected.get(x).remove(child);
                    disconnected.get(child).remove(x);
                }

                childCount += subtreeSize;
            }
        }

        return childCount + 1;
    }

    private static int getMax() {
        int max = 0;
        Set<Integer> visited = new HashSet<>();
        for (Integer x : disconnected.keySet()) {
            if (!visited.contains(x)) {
                int value = getValue(x, visited);

                if (value > max) {
                    max = value;
                }
            }
        }

        return max;
    }

    private static int getValue(Integer x, Set<Integer> visited) {
        visited.add(x);
        int value = 0;
        for (Integer child : disconnected.get(x)) {
            if (!visited.contains(child)) {
                value += getValue(child, visited);
            }
        }

        return value + x;
    }
}

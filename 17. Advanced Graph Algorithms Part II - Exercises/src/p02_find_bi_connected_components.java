import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class p02_find_bi_connected_components {

    // taken from https://github.com/AhmadElsagheer/Competitive-programming-library/blob/master/graphs/traversal/BiconnectedComponents.java

    private static List<Integer>[] graph, inBiComp;
    private static int[] dfs_low, dfs_num;
    private static int counter, biCompIdx;
    private static Deque<Integer> stack;
    private static List<List<Integer>> biconnectedComponents;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodeCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int edgeCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        graph = new List[nodeCount];
        for (int i = 0; i < nodeCount; ++i) {
            graph[i] = new ArrayList<>();
        }

        while (edgeCount-- > 0) {
            String[] tokens = reader.readLine().split("\\s+");
            int parent = Integer.parseInt(tokens[0]);
            int child = Integer.parseInt(tokens[1]);

            graph[parent].add(child);
            graph[child].add(parent);
        }

        dfs_low = new int[nodeCount];
        dfs_num = new int[nodeCount];
        stack = new ArrayDeque<>();
        biconnectedComponents = new ArrayList<>();
        inBiComp = new ArrayList[nodeCount];

        for (int i = 0; i < nodeCount; ++i) {
            inBiComp[i] = new ArrayList<>();
        }

        biConnectedComponents();
        System.out.printf("Number of bi-connected components: %d", biconnectedComponents.size());
    }

    private static void biConnectedComponents() {
        for (int i = 0; i < graph.length; ++i) {
            if (dfs_num[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    private static void dfs(int u, int p) {
        dfs_num[u] = dfs_low[u] = ++counter;
        stack.push(u);
        for (int child : graph[u]) {
            if (child == p) {
                continue;
            }

            if (dfs_num[child] == 0) {
                dfs(child, u);
                dfs_low[u] = Math.min(dfs_low[child], dfs_low[u]);

                if (dfs_low[child] >= dfs_num[u]) {
                    List<Integer> component = new ArrayList<>();
                    while (true) {
                        int w = stack.pop();
                        component.add(w);
                        inBiComp[w].add(biCompIdx);
                        if (w == child) {
                            break;
                        }
                    }

                    component.add(u);
                    inBiComp[u].add(biCompIdx);
                    biconnectedComponents.add(component);
                    ++biCompIdx;
                }
            } else {
                dfs_low[u] = Math.min(dfs_low[u], dfs_num[child]);
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class p04_combinatorial {

    private static int scc;

    // problem names are there to throw you off
    // 90/100 - 1 test fails for time
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        int timesItStoodConnected = 0;

        Set<Integer>[] graph = new Set[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new TreeSet<>();
        }

        for (int i = 1; i <= n; i++) {
            String[] params = reader.readLine().split("\\s+");

            for (String param : params) {
                graph[i].add(Integer.parseInt(param));
            }
        }

        getConnectedComponents(graph);
        if (scc != 1) {
            System.out.println(-2);
            return;
        }

        for (int i = 1; i <= n; i++) {
            Set<Integer> temp = graph[i];

            graph[i] = null;
            for (Integer child : temp) {
                if (child != i) {
                    graph[child].remove(i);
                }
            }

            getConnectedComponents(graph);
            if (scc == m) {
                System.out.println(i);
                return;
            } else if (scc == 1) {
                timesItStoodConnected++;
            }

            graph[i] = temp;
            for (Integer child : temp) {
                graph[child].add(i);
            }
        }

        if (timesItStoodConnected == n) {
            System.out.println(-1);
        } else {
            System.out.println(0);
        }
    }

    private static void getConnectedComponents(Set<Integer>[] graph) {
        boolean[] visited = new boolean[graph.length];
        scc = 0;

        for (int i = 1; i < graph.length; i++) {
            if (!visited[i] && graph[i] != null) {
                dfs(i, visited, graph);
                scc++;
            }
        }
    }

    private static void dfs(int n, boolean[] visited, Set<Integer>[] graph) {
        if (visited[n]) return;

        visited[n] = true;
        for (Integer integer : graph[n]) {
            dfs(integer, visited, graph);
        }
    }
}

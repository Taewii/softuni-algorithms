import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p03_lumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] params = reader.readLine().split("\\s+");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);

        Log[] logs = new Log[n + 1];
        List<Integer>[] graph = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            params = reader.readLine().split("\\s+");

            int x1 = Integer.parseInt(params[0]);
            int y1 = Integer.parseInt(params[1]);
            int x2 = Integer.parseInt(params[2]);
            int y2 = Integer.parseInt(params[3]);

            logs[i] = new Log(i, x1, y1, x2, y2);
            graph[i] = new ArrayList<>();

            for (int j = 1; j < i; j++) {
                if (logs[i].intersects(logs[j])) {
                    graph[i].add(logs[j].id);
                    graph[j].add(logs[i].id);
                }
            }
        }

        boolean[] visited = new boolean[n + 1];
        int[] components = new int[n + 1];
        int componentCount = 0;

        for (int i = 1; i < graph.length; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited, components, componentCount + i);
            }
        }

        StringBuilder result = new StringBuilder();
        while (m-- > 0) {
            params = reader.readLine().split("\\s+");
            int from = Integer.parseInt(params[0]);
            int to = Integer.parseInt(params[1]);

            result.append(components[from] == components[to] ? "YES" : "NO").append(System.lineSeparator());
        }

        System.out.println(result.toString());
    }

    private static void dfs(List<Integer>[] graph, int i, boolean[] visited, int[] components, int count) {
        components[i] = count;
        visited[i] = true;

        for (Integer log : graph[i]) {
            if (!visited[log]) {
                dfs(graph, log, visited, components, count);
            }
        }
    }

    static class Log {
        int id;
        int x1;
        int y1;
        int x2;
        int y2;

        Log(int id, int x1, int y1, int x2, int y2) {
            this.id = id;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        boolean intersects(Log log) {
            return this.x1 <= log.x2 && this.x2 >= log.x1 && this.y1 >= log.y2 && this.y2 <= log.y1;
        }
    }
}

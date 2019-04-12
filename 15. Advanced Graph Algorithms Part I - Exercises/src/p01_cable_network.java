import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class p01_cable_network {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int budget = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int nodes = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int edges = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        boolean[] connected = new boolean[nodes];
        List<Edge> unconnected = new ArrayList<>();

        for (int i = 0; i < edges; i++) {
            String[] params = reader.readLine().split("\\s+");
            int from = Integer.parseInt(params[0]);
            int to = Integer.parseInt(params[1]);
            int weight = Integer.parseInt(params[2]);

            if (params.length == 3) {
                unconnected.add(new Edge(from, to, weight));
            } else {
                connected[from] = true;
                connected[to] = true;
            }
        }

        unconnected.sort(Comparator.comparingInt(a -> a.weight));

        int budgetUsed = 0;
        for (int i = 0; i < unconnected.size(); i++) {
            Edge edge = unconnected.get(i);

            if (edge.weight + budgetUsed > budget) {
                break;
            }

            if ((connected[edge.to] && !connected[edge.from]) || (!connected[edge.to] && connected[edge.from])) {
                budgetUsed += edge.weight;
                connected[edge.to] = true;
                connected[edge.from] = true;
                i = 0;
            }
        }

        System.out.println("Budget used: " + budgetUsed);
    }
}

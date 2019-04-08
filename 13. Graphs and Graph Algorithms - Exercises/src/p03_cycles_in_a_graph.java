import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p03_cycles_in_a_graph {

    private static Map<String, List<String>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        while (line != null && !line.equals("")) {
            String[] connection = line.split("–");
            graph.putIfAbsent(connection[0], new ArrayList<>());
            graph.putIfAbsent(connection[1], new ArrayList<>());
            graph.get(connection[0]).add(connection[1]);
            graph.get(connection[1]).add(connection[0]);

            line = reader.readLine();
        }

        for (String node : graph.keySet()) {
            boolean cyclic = searchForCycles(node, new HashSet<>(), new HashSet<>(), null);
            if (cyclic) {
                System.out.println("Acyclic: No");
                return;
            }
        }
        System.out.println("Acyclic: Yes");
    }

    private static boolean searchForCycles(String node, Set<String> visited, Set<String> cycle, String parent) {
        boolean output = false;

        if (cycle.contains(node)) {
            return true;
        }

        if (!visited.contains(node)) {
            visited.add(node);
            cycle.add(node);
            if (graph.containsKey(node)) {
                for (String child : graph.get(node)) {
                    if (!child.equals(parent)) {
                        output = output || searchForCycles(child, visited, cycle, node);
                    }
                }
                cycle.remove(node);
            }
        }
        return output;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p03_message_sharing {

    private static int steps = 0;
    private static Set<String> last;
    private static Set<String> unreachable = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] names = reader.readLine().substring("People: ".length()).split("[,\\s]+");
        String[] connections = reader.readLine().substring("Connections: ".length()).split("[,\\s-]+");
        String[] start = reader.readLine().substring("Start: ".length()).split("[,\\s]+");

        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();

        for (String name : names) {
            graph.put(name, new ArrayList<>());
            visited.put(name, false);
        }

        for (int i = 0; i < connections.length - 1; i += 2) {
            String from = connections[i];
            String to = connections[i + 1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        bfs(visited, graph, start);

        boolean hasUnreachablePeople = false;
        for (String str : graph.keySet()) {
            if (!visited.get(str)) {
                unreachableBfs(visited, graph, str);
                hasUnreachablePeople = true;
            }
        }

        if (hasUnreachablePeople) {
            System.out.print("Cannot reach: ");
            System.out.print(String.join(", ", unreachable));
        } else {
            System.out.printf("All people reached in %d steps%n", steps);
            System.out.print("People at last step: ");
            System.out.print(String.join(", ", last));
        }
    }

    private static void unreachableBfs(Map<String, Boolean> visited, Map<String, List<String>> graph, String start) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        unreachable.add(start);

        while (!queue.isEmpty()) {
            String sender = queue.poll();

            for (String receiver : graph.get(sender)) {
                if (!visited.get(receiver)) {
                    visited.replace(receiver, true);
                    queue.offer(receiver);
                    unreachable.add(receiver);
                }
            }
        }
    }

    private static void bfs(Map<String, Boolean> visited, Map<String, List<String>> graph, String... start) {
        Queue<String> queue = new ArrayDeque<>();
        int count = start.length;
        for (String name : start) {
            queue.offer(name);
            visited.replace(name, true);
        }

        last = new TreeSet<>();
        while (!queue.isEmpty()) {
            String sender = queue.poll();
            last.add(sender);
            count--;

            for (String receiver : graph.get(sender)) {
                if (!visited.get(receiver)) {
                    visited.replace(receiver, true);
                    queue.offer(receiver);
                }
            }

            if (count <= 0 && !queue.isEmpty()) {
                steps++;
                count = queue.size();
                last = new TreeSet<>();
            }
        }
    }
}

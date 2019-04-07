import java.util.*;

public class p01_connected_components {

    public static void main(String[] args) {
        List<List<Integer>> graph = readGraph();

        getConnectedComponentsDocumentSolution(graph);

//        this is for the judge tests
//        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph);
//        for (Deque<Integer> connectedComponent : connectedComponents) {
//            System.out.println(connectedComponent);
//        }
    }

    private static List<List<Integer>> readGraph() {
        Scanner in = new Scanner(System.in);

        List<List<Integer>> graph = new ArrayList<>();
        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            List<Integer> connectedComponents = new ArrayList<>();

            String line = in.nextLine();
            if (line.equals("")) {
                graph.add(connectedComponents);
                continue;
            }
            String[] nodes = line.split(" ");

            for (String node : nodes) {
                connectedComponents.add(Integer.parseInt(node));
            }

            graph.add(connectedComponents);
        }

        return graph;
    }

    static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        List<Deque<Integer>> result = new ArrayList<>();
        Deque<Integer> deque;

        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                deque = new ArrayDeque<>();
                dfs(i, visited, graph, deque);
                result.add(deque);
            }
        }

        return result;
    }

    private static void getConnectedComponentsDocumentSolution(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        Deque<Integer> deque;

        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                deque = new ArrayDeque<>();
                System.out.print("Connected component: ");
                dfs(i, visited, graph, deque);
                System.out.print(deque.toString().replaceAll("[\\[\\],]", ""));
                System.out.println();
            }
        }
    }

    private static void dfs(int n, boolean[] visited, List<List<Integer>> graph, Deque<Integer> deque) {
        if (visited[n]) return;

        visited[n] = true;
        for (int integer : graph.get(n)) {
            dfs(integer, visited, graph, deque);
        }

        deque.offer(n);
    }
}

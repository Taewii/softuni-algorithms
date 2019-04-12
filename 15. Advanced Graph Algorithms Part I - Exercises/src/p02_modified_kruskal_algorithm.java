import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p02_modified_kruskal_algorithm {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodeCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int edgeCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        List<List<Integer>> forest = new ArrayList<>();
        Queue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        List<Integer> nodeTree = new ArrayList<>();

        for (int i = 0; i < nodeCount; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(i);
            forest.add(temp);
            nodeTree.add(i);
        }

        for (int i = 0; i < edgeCount; i++) {
            String[] params = reader.readLine().split("\\s+");
            edges.add(new Edge(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2])));
        }

        int mstWeight = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();

            int startNodeTree = nodeTree.get(edge.from);
            int endNodeTree = nodeTree.get(edge.to);

            if (startNodeTree != endNodeTree) {
                for (Integer node : forest.get(endNodeTree)) {
                    forest.get(startNodeTree).add(node);
                    nodeTree.set(node, startNodeTree);
                }
                forest.get(endNodeTree).clear();
                mstWeight += edge.weight;
            }
        }

        System.out.println("Minimum spanning forest weight: " + mstWeight);
    }
}
